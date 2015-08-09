package com.yingzi.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ExecutionError;
import com.gson.oauth.Oauth;
import com.yingzi.web.annotation.PowerCheck;
import com.yingzi.web.enums.AgeEnum;
import com.yingzi.web.enums.PowerCheckEnum;
import com.yingzi.web.helper.WeixinOauthHelper;
import com.yingzi.web.utils.JsonUtil;
import com.yingzi.web.utils.ResponseUtils;
import com.yingzi.web.utils.SessionUtil;
import com.yingzi.web.views.ConsumeRecordsInfo;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.CodeInfo;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.CodeFacade;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.MessageFacade;
import com.yingzixiyin.api.facade.RecordFacade;
import com.yingzixiyin.api.facade.UserFacade;
/***
 * 咨询师相关接口业务类
 * @author lkzlee
 *
 */
@Controller
@RequestMapping("/user")
public class UserCenterController {
	private static Logger logger = LoggerFactory.getLogger(UserCenterController.class);
	
	private static final String CHAT_URL="http://localhost:8080/websocket/chat.do";
	
	@Resource
	private ConsultantFacade consultantFacade;
	@Resource
	private UserFacade userFacade;
	@Resource
	private RecordFacade recordFacade;
	@Resource
	private MessageFacade messageFacade;
	@Resource
	private CodeFacade codeFacade;
	@Resource
	private WeixinOauthHelper weixinOauthHelper;
	/**
	 * 根据咨询用户收藏的咨询师id获取咨询师列表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	
	@RequestMapping(value="collected_consultants.do")
	public void collectedConsultant(HttpServletRequest request,HttpServletResponse response,String openId) throws IOException{
		logger.info("---用户收藏过的咨询师页面----");
		//重新从数据库查询一遍，防止刚登陆用户看不到收藏过的咨询师数据
		UserQueryRequestDto uqrDto=new UserQueryRequestDto();
		uqrDto.setOpenId(openId);
		UserInfo user=userFacade.queryOne(uqrDto);
		ConsultantQueryResponseDto  cqrDto=consultantFacade.queryByIds(user.getCollected());
		try{
			String res=JsonUtil.getJsonByConsultantQueryResponseDto(cqrDto, "password","alipay");
			ResponseUtils.renderJsonText(response, res);
		} catch(Exception e){
			logger.error("得到用户收藏列表异常",e);
		}
	}
	/**
	 * 根据咨询用户咨询过的的咨询师id获取咨询师列表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	@RequestMapping(value="visited_consultants.do")
	public void visitedConsultant(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("---用户咨询过的咨询师页面----");
		//重新从数据库查询一遍，防止刚登陆用户看不到咨询过的咨询师数据
		UserInfo user=SessionUtil.getLoginUserToSession(request);
		UserQueryRequestDto uqrDto=new UserQueryRequestDto();
		uqrDto.setOpenId(user.getOpenId());
		user=userFacade.queryOne(uqrDto);
		ConsultantQueryResponseDto  cqrDto=consultantFacade.queryByIds(user.getVisited());
		try{
			String res=JsonUtil.getJsonByConsultantQueryResponseDto(cqrDto, "password","alipay");
			ResponseUtils.renderJsonText(response, res);
		} catch(Exception e){
			logger.error("得到用户咨询过的咨询师列表异常",e);
		}
	}
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	@RequestMapping(value="consume_records.do")
	public void consumeConsultantRecord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("---用户咨询的消费记录页面----");
		RecordQueryResponseDto resqrDto=queryUserConsumeRecords(request);
		try{
			String res=JsonUtil.getJsonByRecordQueryResponseDto(resqrDto);
			ResponseUtils.renderJsonText(response, res);
		} catch(Exception e){
			logger.error("得到用户咨询消费记录列表异常",e);
		}
	}
	/**
	 * 提取查询用户消费记录公共代码
	 * @param request
	 * @return
	 */
	private RecordQueryResponseDto queryUserConsumeRecords(HttpServletRequest request){
		// 重新从数据库查询一遍，防止刚登陆用户看不到咨询过的咨询师数据
		UserInfo user = SessionUtil.getLoginUserToSession(request);
		RecordQueryRequestDto rqrDto = new RecordQueryRequestDto();
		rqrDto.setUserId(user.getId());
		RecordQueryResponseDto resqrDto = recordFacade.query(rqrDto);
		return resqrDto;
	}
	/***
	 * 该接口主要流程：
	 * 1.微信跳转到该接口下，进行认证，
	 * 2.认证成功后，识别获取用户openId
	 * 3.使用openId查询用户并登陆，
	 * 4.查询用户消费记录，
	 * 5.跳转会用户消费记录界面
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="wx_consume_records.do")
	public String wxToConsumeConsultantRecord(HttpServletRequest request,HttpServletResponse response,String code) throws IOException{
		logger.info("---用户咨询的消费记录页面----");
		String response_page="public/my/record";
		boolean flag=weixinOauthHelper.oauthAndLogin(request, code);
		if(flag){
			RecordQueryResponseDto resqrDto=queryUserConsumeRecords(request);
			List<ConsumeRecordsInfo> userRecords=new ArrayList<ConsumeRecordsInfo>();
			if(resqrDto!=null&&resqrDto.getRecordInfoList()!=null){
				for(RecordInfo rinfo:resqrDto.getRecordInfoList()){
					ConsultantQueryRequestDto rcrDto=new ConsultantQueryRequestDto();
					rcrDto.setId(rinfo.getConsultantId());
					ConsultantInfo cqrDto=consultantFacade.queryOne(rcrDto);
					ConsumeRecordsInfo crInfo=buildConsumeRecordsInfo(rinfo,cqrDto);	
					if(crInfo!=null){
						userRecords.add(crInfo);
					}
				}
			}
			request.setAttribute("userRecords",userRecords);
			logger.debug("获取到消费记录："+userRecords);
		}
		return response_page;
	}
	private ConsumeRecordsInfo buildConsumeRecordsInfo(RecordInfo rinfo,
			ConsultantInfo cqrDto) {
		if(rinfo==null||cqrDto==null){
			return null;
		}
		ConsumeRecordsInfo crInfo=new ConsumeRecordsInfo();
		crInfo.setConsultantId(rinfo.getConsultantId());
		crInfo.setConsultantName(cqrDto.getName());
		crInfo.setConsultType(rinfo.getConsultType());
		crInfo.setCreateTime(rinfo.getCreateTime());
		crInfo.setId(rinfo.getId());
		crInfo.setIsCompleted(rinfo.getIsCompleted());
		crInfo.setIsPaid(rinfo.getIsPaid());
		crInfo.setIsReplied(rinfo.getIsReplied());
		crInfo.setUserId(rinfo.getUserId());
		crInfo.setPrice(cqrDto.getPrice());
		crInfo.setAvatar(cqrDto.getAvatar());
		crInfo.setSignature(cqrDto.getSignature());
		crInfo.setRangeType(cqrDto.getRangeType());
		crInfo.setIntroduce(cqrDto.getIntroduce());
		return crInfo;
	}
	private UserInfo queryUserInfo(HttpServletRequest request){
		//重新从数据库查询一遍，防止刚登陆用户看不到咨询过的咨询师数据
		UserInfo user=SessionUtil.getLoginUserToSession(request);
		UserQueryRequestDto uqrDto=new UserQueryRequestDto();
		uqrDto.setOpenId(user.getOpenId());
		user=userFacade.queryOne(uqrDto);
		return user;
	}
	/***
	 * 该接口主要流程：
	 * 1.微信跳转到该接口下，进行认证，
	 * 2.认证成功后，识别获取用户openId
	 * 3.使用openId查询用户并登陆，
	 * 4.查询用户收藏的咨询师和咨询过的咨询师，
	 * 5.跳转回我的咨询师界面
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="wx_consultant_records.do")
	public String wxToConsultantRecord(HttpServletRequest request,HttpServletResponse response,String code) throws IOException{
		logger.info("---用户咨询收藏的咨询师和咨询过的咨询师记录页面----");
		String response_page="public/my/myconsultant";
		boolean flag=weixinOauthHelper.oauthAndLogin(request, code);
		if(flag){
			UserInfo user=queryUserInfo(request);
			ConsultantQueryResponseDto  cqrDtoVisited=consultantFacade.queryByIds(user.getVisited());
			ConsultantQueryResponseDto  cqrDtoCollected=consultantFacade.queryByIds(user.getCollected());
			request.setAttribute("visited", cqrDtoVisited.getConsultantInfoList());
			request.setAttribute("collected", cqrDtoCollected.getConsultantInfoList());
		}
		return response_page;
	}
	/***
	 * 该接口主要流程：
	 * 1.微信跳转到该接口下，进行认证，
	 * 2.认证成功后，识别获取用户openId
	 * 3.使用openId查询用户并登陆，
	 * 4.查询用户和咨询师的未读消息
	 * 5.跳转回我的咨询师界面
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="wx_message_records.do")
	public String messageRecord(HttpServletRequest request,HttpServletResponse response,String code) throws IOException{
		logger.info("---用户咨询的消息记录页面----");
		//用户未读信息
		String response_page="public/my/message";
		boolean flag=weixinOauthHelper.oauthAndLogin(request, code);
		if(flag){
			UserInfo user=SessionUtil.getLoginUserToSession(request);
			List<Map<String,Object>> message=messageFacade.queryConsultantAndMessageCountByUserId(user.getId());
			if(message!=null){
				request.setAttribute("myMessages", message);
				request.setAttribute("chaturl", CHAT_URL);
			}
		}
		return response_page;
	}
	 // 获取验证码
	@PowerCheck(type=PowerCheckEnum.LOGIN)
    @RequestMapping(value="getCheckCodeApi.do")
	public ModelAndView getCheckCode(HttpServletRequest request,
			HttpServletResponse response, String phone) throws IOException {
		logger.info("--获取验证码getCheckCodeApi.do, phone={}", phone);
		// 获取验证码
		BaseResponseDto responseDto = codeFacade.sendCode(phone);
		logger.info("--调用发送短信结果：" + responseDto.getReturnCode());
//		ResponseUtils.renderJsonText(response,
//				JsonUtil.getJsonText(responseDto));
		Map<String,Object> map=Maps.newHashMap();
		map.put("returnCode", responseDto.getReturnCode());
		map.put("returnMessage", responseDto.getReturnMessage());
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}
    @PowerCheck
	@RequestMapping(value="bindPhone.do")
	public ModelAndView bindPhone(HttpServletRequest request,
			HttpServletResponse response, String checkCode, String phone,String openId)
			throws IOException {

		logger.info("bindPhone.do,phone={},checkCode={},openId={}", phone, checkCode,openId);
		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode(checkCode);
		codeInfo.setPhone(phone);
		Map<String, Object> map = Maps.newHashMap();
		BaseResponseDto responseDto = codeFacade.checkCode(codeInfo);
		if (!responseDto.isSuccess()) {
			map.put("returnCode", responseDto.getReturnCode());
			map.put("returnMessage", responseDto.getReturnMessage());
			return new ModelAndView(new MappingJackson2JsonView(),map);

		}
		logger.info("----用户绑定手机，验证码通过---");
		UserQueryRequestDto uqrDto = new UserQueryRequestDto();
		uqrDto.setOpenId(openId);
		UserInfo user = userFacade.queryOne(uqrDto);
		if (user == null) {
			map.put("returnCode", responseDto.getReturnCode());
			map.put("returnMessage", responseDto.getReturnMessage());
			return new ModelAndView(new MappingJackson2JsonView(),map);
		}
		user.setPhone(phone);
		user.setIsBind(YesOrNoEnum.YES);
		responseDto = userFacade.update(user);
		map.put("returnCode", responseDto.getReturnCode());
		map.put("returnMessage", responseDto.getReturnMessage());
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}

}
