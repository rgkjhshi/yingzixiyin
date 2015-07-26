package com.yingzi.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yingzi.admin.annotation.PowerCheck;
import com.yingzi.admin.enums.PowerCheckEnum;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	Logger log=LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping("/login.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public String login(HttpServletRequest request,HttpServletResponse response,String username,String password){
		log.info("---后台用户进行登录");
		return "index";
	}
}
