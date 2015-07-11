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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Collections2;
import com.gson.WeChat;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.ConsultantRequestDto;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.facade.ConsultantFacade;

@Controller
@RequestMapping("/weixin")
public class ConsultantController {
	private static Logger logger = LoggerFactory.getLogger(ConsultantController.class);
	
	@Resource
	private ConsultantFacade consultantFacade;
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
		ConsultantRequestDto rcrDto=new ConsultantRequestDto();
		rcrDto.setRangeType(consultantType);
		ConsultantQueryResponseDto resDto=  consultantFacade.query(rcrDto); 
		request.setAttribute("consultants", resDto.getConsultantList());
		return response_page;
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
	public String getConsultantInfor(HttpServletRequest request,HttpServletResponse response,String id) throws IOException{
		logger.info("---用户调用咨询师详细信息接口页面----");
		String response_page="public/service/detail";
		if(StringUtils.isEmpty(id)){
			return response_page;
		}
		ConsultantQueryResponseDto cqrDto=consultantFacade.queryByIds(id);
		if(cqrDto!=null){
			List<ConsultantInfo> infoList= cqrDto.getConsultantList();
			if(infoList!=null&&infoList.size()>0){
				request.setAttribute("consultant", infoList.get(0));
			}
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
