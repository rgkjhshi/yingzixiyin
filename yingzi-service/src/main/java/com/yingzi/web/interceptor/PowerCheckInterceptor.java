package com.yingzi.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yingzi.web.annotation.PowerCheck;
import com.yingzi.web.controller.ConsultantController;
import com.yingzi.web.enums.PowerCheckEnum;
import com.yingzi.web.utils.JsonUtil;
import com.yingzi.web.utils.ResponseUtils;
import com.yingzi.web.utils.SessionUtil;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.UserInfo;

public class PowerCheckInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory
			.getLogger(ConsultantController.class);

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
				UserInfo user = SessionUtil.getLoginUserToSession(request);
				if (user == null) {
					BaseResponseDto brDto = new BaseResponseDto();
					brDto.setReturnCode(-1);
					brDto.setReturnMessage("未登录，请从微信客户端访问");
					String res = JsonUtil.getJsonText(brDto);
					ResponseUtils.renderJsonText(response, res);
					logger.info("----用户微信登录为空，进行拦截--");
					return false;
				}
			} else if (pce.getId() == PowerCheckEnum.POWER.getId()) {
				// 做一些权限处理
			}
		}
		return true;
	}

}
