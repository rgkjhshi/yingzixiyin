package com.yingzi.admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.common.collect.Maps;
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
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,String phone,String password){
		log.info("---后台用户进行登录---");
		Map<String, Object> map = Maps.newHashMap();
		if(StringUtils.isEmpty(phone)){
			map.put("status", -1);
			map.put("message", "登录手机号码为空");
			return new ModelAndView(new MappingJackson2JsonView(), map);
		}
		AdminQueryRequestDto aqrDto=new AdminQueryRequestDto();
		aqrDto.setUsername(phone);
		aqrDto.setPassword(password);
		AdminInfo adminInfo=adminFacade.queryOne(aqrDto);
		if(adminInfo==null){
			map.put("status", -1);
			map.put("message", "登录手机号不存在或密码错误");
			return new ModelAndView(new MappingJackson2JsonView(), map);
		}
		SessionUtil.setLoginAdminToSession(request, adminInfo);
		map.put("status", 0);
		map.put("message", "登录成功");
		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
	/**
	 * 退出登录，清空session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout.do")
	@PowerCheck(type=PowerCheckEnum.LOGIN)
	public String login(HttpServletRequest request,HttpServletResponse response){
		log.info("---用户退出登录--");
		SessionUtil.removeLoginAdminToSession(request);
		return "redirect:/"+LoginConstant.NO_LOGIN_URL;
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
		responseDto.setReturnCode(0);
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
		responseDto=adminFacade.update(admin);
		try {
			ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
		} catch (IOException e) {
			log.error("网络异常",e);
		}
	}
}
