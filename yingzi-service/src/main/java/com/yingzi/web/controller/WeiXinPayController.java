package com.yingzi.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import ytx.org.apache.http.client.utils.HttpClientUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.gson.WeChat;
import com.gson.bean.PreOrder;
import com.gson.bean.WeChatBuyPost;
import com.gson.oauth.Oauth;
import com.gson.oauth.Pay;
import com.gson.util.HttpKit;
import com.gson.util.Tools;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.yingzi.web.annotation.PowerCheck;
import com.yingzi.web.dto.PayNotifyResultDto;
import com.yingzi.web.dto.PayOrderResultDto;
import com.yingzi.web.enums.AgeEnum;
import com.yingzi.web.enums.PowerCheckEnum;
import com.yingzi.web.helper.WeixinOauthHelper;
import com.yingzi.web.utils.JsonUtil;
import com.yingzi.web.utils.MD5Utils;
import com.yingzi.web.utils.NetUtils;
import com.yingzi.web.utils.RandomUtil;
import com.yingzi.web.utils.ResponseUtils;
import com.yingzi.web.utils.SessionUtil;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.ConsultantResponseDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.UserConsultantInfo;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.enums.ConsultTypeEnum;
import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.StatusEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.RecordFacade;
import com.yingzixiyin.api.facade.UserFacade;

/***
 * 微信支付相关接口类
 * 
 * @author lkzlee
 * 
 */
@Controller
@RequestMapping("/wxpay")
public class WeiXinPayController {
	private static Logger logger = LoggerFactory
			.getLogger(WeiXinPayController.class);

	@Resource
	private ConsultantFacade consultantFacade;
	@Resource
	private UserFacade userFacade;
	@Resource
	private RecordFacade recordFacade;
	@Resource
	private WeixinOauthHelper weixinOauthHelper;

	// 微信返回 fail 失败，success 成功
	private static final String STATUC_SUCCESS = "SUCCESS";
	private static final String STATUC_FAIL = "FAIL";

	@RequestMapping(value = "pay_notify.do")
	public void payNotify(HttpServletRequest request,
			HttpServletResponse response) {
		ServletInputStream in;
		try {
			in = request.getInputStream();
			// 转换微信post过来的xml内容
			XStream xs = new XStream(new DomDriver());
			xs.alias("xml", PayNotifyResultDto.class);
			String xmlMsg = Tools.inputStream2String(in);
			logger.info("@收到微信通知消息："+xmlMsg);
			PayNotifyResultDto notifyResult = (PayNotifyResultDto) xs.fromXML(xmlMsg);
			
			boolean signResult = isRightStatusAndSign(notifyResult);
			
			String result=STATUC_SUCCESS;
			logger.info("@微信验证签名和状态结果："+signResult);
			if(signResult){
				RecordQueryRequestDto requestDto=new RecordQueryRequestDto();
				requestDto.setRecordNonce(notifyResult.getOut_trade_no());
				RecordInfo recordInfo= recordFacade.queryOne(requestDto);
				if(recordInfo==null){
					logger.error("@查询不到微信支付的咨询记录，咨询标记 record_nonce="+notifyResult.getOut_trade_no());
					return;
				}
				recordInfo.setIsPaid(YesOrNoEnum.YES);
				recordFacade.update(recordInfo);
				logger.info("@微信支付流程完成，更新咨询记录为已支付状态,recordId="+recordInfo.getId());
			}
			else{
				result=STATUC_FAIL;
			}
			String responseXml = getResultMessage(result);
			logger.info("@响应微信通知的消息:"+responseXml);
			ResponseUtils.renderXMLText(response, responseXml);
		} catch (IOException e) {
			logger.info("@网络IO异常",e);
		}
	
	}

	private String getResultMessage(String result) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<return_code><![CDATA["+result+"]]></return_code>");
		sb.append("<return_msg><![CDATA[OK]]></return_msg>");
		sb.append("</xml>");
		return sb.toString();
	}

	private boolean isRightStatusAndSign(PayNotifyResultDto notifyResult)
			throws UnsupportedEncodingException {
		if(STATUC_FAIL.equals(notifyResult.getResult_code())
			||STATUC_FAIL.equals(notifyResult.getReturn_code())){
			return false;
		}
		Map<String, String> paras = new HashMap<String, String>();
		 paras.put("appid", notifyResult.getAppid());
		 paras.put("bank_type", notifyResult.getBank_type());
		 paras.put("cash_fee", notifyResult.getCash_fee());
		 paras.put("device_info", notifyResult.getDevice_info());
		 paras.put("fee_type", notifyResult.getFee_type());
		 paras.put("is_subscribe", notifyResult.getIs_subscribe());
		 paras.put("mch_id", notifyResult.getMch_id());
		 paras.put("nonce_str", notifyResult.getNonce_str());
		 paras.put("openid", notifyResult.getOpenid());
		 paras.put("out_trade_no", notifyResult.getOut_trade_no());
		 paras.put("result_code", notifyResult.getResult_code());
		 paras.put("return_code", notifyResult.getReturn_code());
		 paras.put("time_end", notifyResult.getTime_end());
		 paras.put("total_fee", notifyResult.getTotal_fee());
		 paras.put("trade_type", notifyResult.getTrade_type());
		 paras.put("transaction_id", notifyResult.getTransaction_id());
		boolean signResult=Pay.verifySign(paras,notifyResult.getSign());
		return signResult;
	}

	@RequestMapping(value = "prepay.do")
	@PowerCheck(type = PowerCheckEnum.LOGIN)
	public String payByOnline(HttpServletRequest request,
			HttpServletResponse response, Long consultant_id,
			Integer counsultantType, String payType) throws IOException {
		logger.info("---用户支付接口页面----");
		String response_page = "public/service/pay";
		response_page = doPayWeixin(request, consultant_id, counsultantType);
		return response_page;
	}

	private String doPayWeixin(HttpServletRequest request, Long consultant_id,
			Integer counsultantType) throws UnsupportedEncodingException {
		ConsultantQueryRequestDto cqrDto = new ConsultantQueryRequestDto();
		cqrDto.setId(consultant_id);
		cqrDto.setStatus(StatusEnum.ACCEPTED);
		ConsultantInfo cinfo = consultantFacade.queryOne(cqrDto);
		if (cinfo == null) {
			logger.error("咨询师不存在，请检查你的参数");
			return "public/service/sort";

		}
		UserInfo user = SessionUtil.getLoginUserToSession(request);
		if (user == null) {
			logger.error("用户登录超时不存在，请重新打开微信客户端");
			return "public/service/sort";
		}
		RecordInfo recordInfo = new RecordInfo();
		recordInfo.setConsultantId(consultant_id);
		recordInfo.setConsultType(ConsultTypeEnum
				.toEnum(counsultantType == null ? -1 : counsultantType));
		recordInfo.setCreateTime(new Date());
		recordInfo.setIsCompleted(YesOrNoEnum.NO);
		recordInfo.setIsPaid(YesOrNoEnum.NO);
		recordInfo.setIsReplied(YesOrNoEnum.NO);
		recordInfo.setUserId(user.getId());
		// recordInfo.setUserId(1l);
		recordInfo.setRecordNonce(RandomUtil.generateOrderId());
		// 添加 咨询记录
		recordFacade.add(recordInfo);
		// 做异常处理
		PreOrder preOrder = buildPreOrderVo(request, cinfo, recordInfo);
		try {
			XStream xs = new XStream(new DomDriver());
			xs.alias("xml", PreOrder.class);

			logger.info("@请求内容：" + Pay.PRE_ORDER_URL + "?"
					+ preOrder.serializeToXml());
			String resultString = HttpKit.post(Pay.PRE_ORDER_URL,
					preOrder.serializeToXml());
			xs.alias("xml", PayOrderResultDto.class);
			PayOrderResultDto payResult = (PayOrderResultDto) xs
					.fromXML(resultString);

			logger.info("@微信下单，返回结果： " + payResult);

			// 参数
			String timeStamp = System.currentTimeMillis() + "";
			String nonce_str = RandomUtil.generateCode();
			Map<String, String> signMap = Maps.newHashMap();
			signMap.put("timeStamp", timeStamp);
			signMap.put("appId", preOrder.getAppid());
			signMap.put("package", "prepay_id=" + payResult.getPrepay_id());
			signMap.put("nonceStr", nonce_str);
			signMap.put("signType", "MD5");
			String paySign = Pay.packageSign(signMap); // 构造签名
			request.setAttribute("appId", preOrder.getAppid());
			request.setAttribute("timeStamp", timeStamp);
			request.setAttribute("nonceStr", nonce_str);
			request.setAttribute("packageStr",
					"prepay_id=" + payResult.getPrepay_id());
			request.setAttribute("signType", "MD5");
			request.setAttribute("paySign", paySign);
		} catch (Exception e) {
			logger.info("weixin 下单异常", e);
		}
		return "public/service/pay";
	}
	/**
	 * 获取ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		if (request == null)
			return "";
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private PreOrder buildPreOrderVo(HttpServletRequest request,
			ConsultantInfo cinfo, RecordInfo recordInfo)
			throws UnsupportedEncodingException {
		String out_trade_no = recordInfo.getRecordNonce();
//		String fee_type = "CNY";
		int total_fee = 0;
		if (null == cinfo || null == cinfo.getPrice()) {
			return null;
		}
		if (ConsultTypeEnum.CHAT.equals(recordInfo.getConsultType())) {
			total_fee = cinfo.getPrice().multiply(new BigDecimal(100))
					.intValue();
		} else if (ConsultTypeEnum.FACE_TO_FACE.equals(recordInfo
				.getConsultType())) {
			total_fee = cinfo.getFacePrice().multiply(new BigDecimal(100))
					.intValue();
		} else if (ConsultTypeEnum.VIDEO.equals(recordInfo.getConsultType())) {
			total_fee = cinfo.getVideoPrice().multiply(new BigDecimal(100))
					.intValue();
		}
//		total_fee=1;
		String spbill_create_ip = NetUtils.getRemoteHost(request);
		String trade_type = "JSAPI";
		String notify_url = WeChat.getNotifyUrl();
//		String limit_pay = "no_credit";
		String openid = SessionUtil.getLoginUserToSession(request).getOpenId();
		// String openid="oGBeWt-feUNN9UJzt7YHJM3VnzKc";
//		String product_id = RandomUtil.generateProductId();

		Map<String, String> signMap = Maps.newHashMap();
		signMap.put("appid", WeChat.getAppId());
		signMap.put("mch_id", WeChat.getMch_id());
		signMap.put("device_info", "WEB");
		String nonce_str = RandomUtil.generateCode();
		signMap.put("nonce_str", nonce_str);
		String body = "情感咨询服务";
		signMap.put("body", body);
		signMap.put("out_trade_no", out_trade_no);
//		signMap.put("fee_type", fee_type);
		signMap.put("total_fee", total_fee + "");
		signMap.put("spbill_create_ip", spbill_create_ip);
		signMap.put("notify_url", notify_url);
		signMap.put("trade_type", trade_type);
//		signMap.put("product_id", product_id);
//		signMap.put("limit_pay", limit_pay);
		signMap.put("openid", openid);
		String sign = Pay.packageSign(signMap);
		PreOrder po = new PreOrder(WeChat.getAppId(), WeChat.getMch_id(),
				"WEB", nonce_str, sign, body, out_trade_no, total_fee,
				spbill_create_ip, notify_url, trade_type, openid);
		return po;
	}
}
