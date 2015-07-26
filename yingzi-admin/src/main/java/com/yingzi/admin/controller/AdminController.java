package com.yingzi.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yingzi.admin.annotation.PowerCheck;
import com.yingzi.admin.constant.LoginConstant;
import com.yingzi.admin.enums.PowerCheckEnum;
import com.yingzi.admin.utils.SessionUtil;
import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.AdminQueryRequestDto;
import com.yingzixiyin.api.facade.AdminFacade;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	Logger log=LoggerFactory.getLogger(AdminController.class);
	@Resource
	private AdminFacade adminFacade;
	@RequestMapping("/login.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public String login(HttpServletRequest request,HttpServletResponse response,String username,String password){
		log.info("---后台用户进行登录---");
		if(StringUtils.isEmpty(username)){
			return LoginConstant.NO_LOGIN_URL;
		}
		AdminQueryRequestDto aqrDto=new AdminQueryRequestDto();
		aqrDto.setUsername(username);
		aqrDto.setPassword(password);
		AdminInfo adminInfo=adminFacade.queryOne(aqrDto);
		if(adminInfo==null){
			return LoginConstant.NO_LOGIN_URL;
		}
		SessionUtil.setLoginAdminToSession(request, adminInfo);
		return "index";
	}
	@RequestMapping("/loginout.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public String login(HttpServletRequest request,HttpServletResponse response){
		SessionUtil.removeLoginAdminToSession(request);
		return LoginConstant.NO_LOGIN_URL;
	}
}
