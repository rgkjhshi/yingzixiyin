package com.yingzi.web.helper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.gson.oauth.Oauth;
import com.yingzi.web.controller.ConsultantController;
import com.yingzi.web.utils.JsonUtil;
import com.yingzi.web.utils.SessionUtil;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.facade.UserFacade;
@Component("weixinOauthHelper")
public class WeixinOauthHelper {
	private static Logger logger = LoggerFactory.getLogger(ConsultantController.class);
	@Resource
	private UserFacade userFacade;
	
	public boolean oauthAndLogin(HttpServletRequest request,String code){
		boolean flag=true;
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
				userInfo=userFacade.queryOne(uqrDto);
			}
			userInfo=userFacade.queryOne(uqrDto);
			SessionUtil.setLoginUserToSession(request, userInfo);
			
		} catch (Exception e) {
			logger.error("微信认证异常", e);
			flag=false;
		}
		return flag;
	}
}
