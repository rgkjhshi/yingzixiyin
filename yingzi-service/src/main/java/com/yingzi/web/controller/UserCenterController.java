package com.yingzi.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.util.concurrent.ExecutionError;
import com.gson.oauth.Oauth;
import com.yingzi.web.annotation.PowerCheck;
import com.yingzi.web.enums.AgeEnum;
import com.yingzi.web.enums.PowerCheckEnum;
import com.yingzi.web.helper.WeixinOauthHelper;
import com.yingzi.web.utils.JsonUtil;
import com.yingzi.web.utils.ResponseUtils;
import com.yingzi.web.utils.SessionUtil;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
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
@RequestMapping("/user")
public class UserCenterController {
	private static Logger logger = LoggerFactory.getLogger(UserCenterController.class);
	
	@Resource
	private ConsultantFacade consultantFacade;
	@Resource
	private UserFacade userFacade;
	@Resource
	private RecordFacade recordFacade;
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
	@PowerCheck
	public void collectedConsultant(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("---用户收藏过的咨询师页面----");
		//重新从数据库查询一遍，防止刚登陆用户看不到收藏过的咨询师数据
		UserInfo user=SessionUtil.getLoginUserToSession(request);
		UserQueryRequestDto uqrDto=new UserQueryRequestDto();
		uqrDto.setOpenId(user.getOpenId());
		user=userFacade.queryOne(uqrDto);
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
			request.setAttribute("userRecords", resqrDto.getRecordInfoList());
		}
		return response_page;
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
			RecordQueryRequestDto rqrDto=new RecordQueryRequestDto();
			rqrDto.setIsPaid(YesOrNoEnum.YES);
			rqrDto.setUserId(user.getId());
			RecordQueryResponseDto cqrDto=recordFacade.query(rqrDto);
			
		}
		return response_page;
	}
}
