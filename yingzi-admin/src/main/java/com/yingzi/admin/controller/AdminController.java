package com.yingzi.admin.controller;

import java.io.IOException;

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
import com.yingzi.admin.utils.JsonUtil;
import com.yingzi.admin.utils.ResponseUtils;
import com.yingzi.admin.utils.SessionUtil;
import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.AdminQueryRequestDto;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.facade.AdminFacade;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	Logger log=LoggerFactory.getLogger(AdminController.class);
	@Resource
	private AdminFacade adminFacade;
	/**
	 * 用户登录接口
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login.do")
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
	/**
	 * 退出登录，清空session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/loginout.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public String login(HttpServletRequest request,HttpServletResponse response){
		log.info("---用户退出登录--");
		SessionUtil.removeLoginAdminToSession(request);
		return LoginConstant.NO_LOGIN_URL;
	}
	/**
	 * 修改密码，传入原来密码和新密码
	 * @param request
	 * @param response
	 * @param oldpwd
	 * @param password
	 */
	@RequestMapping("/changePasswd.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public void changePassword(HttpServletRequest request,HttpServletResponse response,String oldpwd,String password){
		log.info("---用户修改密码--");
		BaseResponseDto responseDto=new BaseResponseDto();
		responseDto.setReturnCode(1);
		responseDto.setReturnMessage("密码修改成功");
		
		AdminInfo admin=SessionUtil.getLoginAdminToSession(request);
		if(StringUtils.isEmpty(oldpwd)||!oldpwd.equals(admin.getPassword())){
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage("原密码不正确");
			try {
				ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
			} catch (IOException e) {
				log.error("网络异常",e);
			}
			return;
		}
		//更新Admin值
		admin.setPassword(password);
		adminFacade.update(admin);
		try {
			ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
		} catch (IOException e) {
			log.error("网络异常",e);
		}
	}
}
