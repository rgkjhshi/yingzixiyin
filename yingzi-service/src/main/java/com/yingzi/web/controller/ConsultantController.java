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
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.gson.WeChat;
import com.gson.bean.PreOrder;
import com.gson.oauth.Oauth;
import com.gson.oauth.Pay;
import com.gson.util.HttpKit;
import com.yingzi.web.annotation.PowerCheck;
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
import com.yingzixiyin.api.dto.RecordInfo;
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
 * 咨询师相关接口业务类
 * @author lkzlee
 *
 */
@Controller
@RequestMapping("/consultant")
public class ConsultantController {
	private static Logger logger = LoggerFactory.getLogger(ConsultantController.class);
	
	@Resource
	private ConsultantFacade consultantFacade;
	@Resource
	private UserFacade userFacade;
	@Resource
	private RecordFacade recordFacade;
	@Resource
	private WeixinOauthHelper weixinOauthHelper;
	/**
	 * 根据咨询类型获取咨询师列表
	 * @param request
	 * @param response
	 * @param ctype
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="get_consultants.do")
	public String getConsultant(HttpServletRequest request,HttpServletResponse response,Integer ctype) throws IOException{
		logger.info("---用户调用得到咨询师接口页面----");
		String response_page="public/service/consultant";
		if(ctype==null){
			return response_page;
		}
		RangeTypeEnum consultantType=RangeTypeEnum.toEnum(ctype);
		if(consultantType==null){
			return response_page;
		}
		ConsultantQueryRequestDto rcrDto=new ConsultantQueryRequestDto();
		rcrDto.setRangeType(consultantType);
		rcrDto.setStatus(StatusEnum.ACCEPTED);
		ConsultantQueryResponseDto resDto=  consultantFacade.query(rcrDto); 
		request.setAttribute("consultants", resDto.getConsultantInfoList());
		request.setAttribute("ctype", ctype);
		return response_page;
	}
	private boolean checkValidParams(Object ...params){
		if(params==null) return false;
		for(Object obj:params){
			if(obj instanceof String){
				String para=(String) obj;
				if(StringUtils.isEmpty(para)){
					return false;
				}
			}
			if(obj instanceof Integer){
				Integer para=(Integer) obj;
				if(para==null){
					return false;
				}
			}
			
		}
		return true;
	}
	/**
	 * 咨询类型+条件筛选获取咨询师列表
	 * @param request
	 * @param response
	 * @param ctype
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="query_consultants.do")
	public void queryConsultantsByCondition(HttpServletRequest request,HttpServletResponse response,Integer ctype,Integer gender,Integer age) throws IOException{
		logger.info("---用户调用条件筛选咨询师接口页面|ctype="+ctype+"|gender="+gender+"|age="+age);
		if(!checkValidParams(ctype,gender,age)){
			return ;
		}
		RangeTypeEnum consultantType=RangeTypeEnum.toEnum(ctype);
		AgeEnum ageEnum=AgeEnum.toEnum(age);
		GenderTypeEnum genderEnum=GenderTypeEnum.toEnum(gender);
		ConsultantQueryRequestDto rcrDto=new ConsultantQueryRequestDto();
		rcrDto.setRangeType(consultantType);
		rcrDto.setGender(genderEnum);
		rcrDto.setStatus(StatusEnum.ACCEPTED);
		if(ageEnum!=null){
			rcrDto.setMinAge(ageEnum.getMinAge());
			rcrDto.setMaxAge(ageEnum.getMaxAge());
		}
//		logger.info("请求参数："+rcrDto);
		ConsultantQueryResponseDto resDto=  consultantFacade.query(rcrDto); 
//		logger.info("--条件筛选得到的咨询师列表为："+resDto.getConsultantInfoList());
		try{
			String res=JsonUtil.getJsonByConsultantQueryResponseDto(resDto,"password","alipay");
			logger.info("---查询到的咨询师列表为："+res);
			ResponseUtils.renderJsonText(response, res);
		}catch(Exception e){
			logger.error("咨询师列表解析结果出错",e);
		}
	
	}
	/**
	 * 根据咨询类型获取咨询师列表
	 * @param request
	 * @param response
	 * @param ctype
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="consultant_deatil.do")
	public String getConsultantInfor(HttpServletRequest request,HttpServletResponse response,Long id) throws IOException{
		logger.info("---用户调用咨询师详细信息接口页面----");
		String response_page="public/service/detail";
		if(id==null){
			return response_page;
		}
		ConsultantQueryRequestDto rcrDto=new ConsultantQueryRequestDto();
		rcrDto.setId(id);
		rcrDto.setStatus(StatusEnum.ACCEPTED);
		ConsultantInfo cqrDto=consultantFacade.queryOne(rcrDto);
		if(cqrDto!=null){
			request.setAttribute("consultant", cqrDto);
		}
		return response_page;
	}
	/**
	 * 收藏某个咨询师功能接口
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="collect_consultant.do")
	@PowerCheck
	public void collectConsultantInfor(HttpServletRequest request,HttpServletResponse response,Long id) throws IOException{
		logger.info("---用户调用咨询师详细信息接口页面----");
		BaseResponseDto responseDto=new BaseResponseDto();
		if(id==null){
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage("咨询师收藏ID为空");
			ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
			return;
		}
		UserInfo user=SessionUtil.getLoginUserToSession(request);
		
		if(user==null){
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage("用户未登录或登录超时");
			ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
			return;
		}
		if(StringUtils.isNotEmpty(user.getCollected())){
			user.setCollected(user.getCollected()+","+id);
		}
		else{
			user.setCollected(id+"");
		}
		responseDto=userFacade.update(user);
		SessionUtil.setLoginUserToSession(request, user);
		ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
	}
	@RequestMapping(value="consultant_yingzi.do")
	public String weixinToYingziCounsultant(HttpServletRequest request,HttpServletResponse response,String code) throws IOException{
		logger.info("---用户调用进入咨询师页面|code="+code);
		String response_page="public/service/sort";
		if(StringUtils.isEmpty(code)){
			return response_page;
		}
		boolean flag=weixinOauthHelper.oauthAndLogin(request, code);
		if(!flag){
			logger.error("---微信调转consultant_yingzi.do页面，认证失败");
		}
		return response_page;
	}
	@RequestMapping(value="consultant_online.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public String consultantByOnline(HttpServletRequest request,HttpServletResponse response,Long consultant_id) throws IOException{
		logger.info("---用户调用咨询师进行线上咨询信息接口页面----");
		String response_page="public/service/online";
		ConsultantQueryRequestDto cqrDto=new ConsultantQueryRequestDto();
		cqrDto.setId(consultant_id);
		cqrDto.setStatus(StatusEnum.ACCEPTED);
		ConsultantInfo cinfo=consultantFacade.queryOne(cqrDto);
		if(cinfo==null){
			//做异常处理
		}
		return response_page;
	}

	@RequestMapping(value = "prepay.do")
	@PowerCheck(type = PowerCheckEnum.LOGIN)
	public String payByOnline(HttpServletRequest request,
			HttpServletResponse response, Long consultant_id,
			Integer counsultantType, String payType) throws IOException {
		logger.info("---用户支付接口页面----");
		String response_page = "public/service/pay";
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
		// 添加 咨询记录
		recordFacade.add(recordInfo);
		// 做异常处理
		PreOrder pro = buildPreOrderVo(request, cinfo);
		String xml = "";
		try {
			xml = pro.serializeToXml();
//			HttpKit.post(url, s)
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("得到预下单请求微信xml：" + xml);
		return response_page;
	}
	@RequestMapping(value="consultant_offline.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public String consultantByOffline(HttpServletRequest request,HttpServletResponse response,Long consultant_id) throws IOException{
		logger.info("---用户调用咨询师进行线下咨询信息接口页面----");
		String response_page="public/service/offline";
		ConsultantQueryRequestDto cqrDto=new ConsultantQueryRequestDto();
		cqrDto.setId(consultant_id);
		cqrDto.setStatus(StatusEnum.ACCEPTED);
		ConsultantInfo cinfo=consultantFacade.queryOne(cqrDto);
		if(cinfo!=null){
			request.setAttribute("cinfo", cinfo);
		}
		return response_page;
	}
	@RequestMapping(value="consultant_video.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public String consultantByVideo(HttpServletRequest request,HttpServletResponse response,Long consultant_id) throws IOException{
		logger.info("---用户调用咨询师进行线下视频咨询信息接口页面----");
		String response_page="public/service/video";
		ConsultantQueryRequestDto cqrDto=new ConsultantQueryRequestDto();
		cqrDto.setId(consultant_id);
		cqrDto.setStatus(StatusEnum.ACCEPTED);
		ConsultantInfo cinfo=consultantFacade.queryOne(cqrDto);
		if(cinfo!=null){
			request.setAttribute("cinfo", cinfo);
		}
		return response_page;
	}
	@RequestMapping(value="isCollected.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public ModelAndView isCollectConsultant(HttpServletRequest request,HttpServletResponse response,String id) throws IOException{
		logger.info("---用户调用咨询师是否收藏接口页面----");
		Map<String,Object> map=Maps.newHashMap();
		UserInfo user=SessionUtil.getLoginUserToSession(request);
		UserQueryRequestDto requestDto=new UserQueryRequestDto();
		requestDto.setId(user.getId());
		requestDto.setOpenId(user.getOpenId());
		requestDto.setPhone(user.getPhone());
		user=userFacade.queryOne(requestDto);
		if(user==null){
			map.put("returnCode", -1);
			map.put("returnMessage", "用户未登录或登录超时");
			return new ModelAndView(new MappingJackson2JsonView(),map);
		}
		String collects=user.getCollected();
//		logger.info("咨询师收藏的咨询师id："+id+"|collects："+collects);
		if(StringUtils.isEmpty(collects)||!collects.contains(id)){
			map.put("returnCode", 0);
			map.put("returnMessage", "未收藏该咨询师");
			map.put("isCollect",0);
			return new ModelAndView(new MappingJackson2JsonView(),map);
		}
		map.put("returnCode", 0);
		map.put("returnMessage", "已收藏该咨询师");
		map.put("isCollect",1);
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}
	private PreOrder buildPreOrderVo(HttpServletRequest request,ConsultantInfo cinfo) throws UnsupportedEncodingException{
		String appid=WeChat.getAppId();
		String mch_id=WeChat.getMch_id();
		String device_info="WEB";
		String nonce_str=RandomUtil.generateCode();
		String body="线上情感咨询";
		Map<String,String> signMap=Maps.newHashMap();
		signMap.put("appid", appid);
		signMap.put("mch_id", mch_id);
		signMap.put("device_info", device_info);
		signMap.put("nonce_str", nonce_str);
		signMap.put("body", body);
		String sign=MD5Utils.getMD5(Pay.createSign(signMap, true));
		String detail="具体情感咨询用户线上和咨询师收费沟通";
//		String attach="附加信息";
		String out_trade_no=RandomUtil.generateOrderId();
		String fee_type="CNY";
		int total_fee= 0;
		if(null==cinfo||null==cinfo.getPrice()){
			return null;
		}
		total_fee=cinfo.getPrice().multiply(new BigDecimal(100)).intValue();
		String spbill_create_ip=NetUtils.getRemoteHost(request);
		String trade_type="JSAPI";
		String notify_url=WeChat.getNotifyUrl();
		String limit_pay="no_credit";
		String openid=SessionUtil.getLoginUserToSession(request).getOpenId();
		String product_id=RandomUtil.generateProductId();
		PreOrder po=new PreOrder(appid, mch_id, device_info, nonce_str,
								sign, body, out_trade_no, fee_type, total_fee, 
								spbill_create_ip, notify_url, trade_type, product_id, 
								limit_pay, openid);
		return po;
	}
}
