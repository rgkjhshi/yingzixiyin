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
import com.gson.oauth.Oauth;
import com.yingzi.web.enums.AgeEnum;
import com.yingzi.web.utils.JsonUtil;
import com.yingzi.web.utils.ResponseUtils;
import com.yingzi.web.utils.SessionUtil;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.UserFacade;
/***
 * 咨询师相关接口业务类
 * @author lkzlee
 *
 */
@Controller
@RequestMapping("/weixin")
public class ConsultantController {
	private static Logger logger = LoggerFactory.getLogger(ConsultantController.class);
	
	@Resource
	private ConsultantFacade consultantFacade;
	@Resource
	private UserFacade userFacade;
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
		ConsultantInfo cqrDto=consultantFacade.queryOne(rcrDto);
		if(cqrDto!=null){
			request.setAttribute("consultant", cqrDto);
		}
		return response_page;
	}
	@RequestMapping(value="consultant_yingzi.do")
	public String weixinToYingziCounsultant(HttpServletRequest request,HttpServletResponse response,String code) throws IOException{
		logger.info("---用户调用进入咨询师页面|code="+code);
		String response_page="public/service/sort";
		if(StringUtils.isEmpty(code)){
			return response_page;
		}
		
		try {
			Oauth oauth=new Oauth();
			String token = oauth.getToken(code);
			logger.info("----查询得到用户token="+token);
			JsonNode node = JsonUtil.StringToJsonNode(token);
			String openId = node.get("openid").asText();
			//get openId
			//do something
			logger.info("----查询得到用户OpenId="+openId);
			UserQueryRequestDto uqrDto=new UserQueryRequestDto();
			uqrDto.setOpenId(openId);
			UserInfo userInfo=userFacade.queryOne(uqrDto);
			if(userInfo==null){
				userInfo=new UserInfo();
				userInfo.setOpenId(openId);
				userFacade.add(userInfo);
			}
			SessionUtil.setLoginUserToSession(request, userInfo);
			
		} catch (Exception e) {
			logger.error("调用consultant_yingzi.do异常", e);
		}
	   
	
		return response_page;
	}
	@RequestMapping(value="consultant_online.do")
	public String consultantByOnline(HttpServletRequest request,HttpServletResponse response,String consultant_id) throws IOException{
		logger.info("---用户调用咨询师进行线上咨询信息接口页面----");
		String response_page="public/service/online";
		
		return response_page;
	}
	@RequestMapping(value="consultant_offline.do")
	public String consultantByOffline(HttpServletRequest request,HttpServletResponse response,String consultant_id) throws IOException{
		logger.info("---用户调用咨询师进行线下咨询信息接口页面----");
		String response_page="public/service/offline";
		
		return response_page;
	}
}
