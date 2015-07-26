package com.yingzi.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yingzi.admin.annotation.PowerCheck;
import com.yingzi.admin.enums.PowerCheckEnum;
import com.yingzi.admin.utils.SessionUtil;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.core.entity.Admin;

public class PowerCheckInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory
			.getLogger(PowerCheckInterceptor.class);
	private static final String LOGIN_URL = "q.jsp";  
	public String[] allowUrls;// 还没发现可以直接配置不拦截的资源，所以在代码里面来排除

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURI().replace(
				request.getContextPath(), "");
		logger.info("------访问：" + requestUrl);
		if (null != allowUrls && allowUrls.length >= 1) {
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		}
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod method = (HandlerMethod) handler;
			PowerCheck check = method.getMethodAnnotation(PowerCheck.class);
			if (check == null) {
				return true;
			}
			PowerCheckEnum pce = check.type();
			if (pce.getId() == PowerCheckEnum.LOGIN.getId()) {
				Admin user = SessionUtil.getLoginAdminToSession(request);
				if (user == null) {
					response.sendRedirect(request.getContextPath()+"/"+LOGIN_URL);
					return false;
				}
			} else if (pce.getId() == PowerCheckEnum.POWER.getId()) {
				// 做一些权限处理
			}
		}
		return true;
	}

}
