package com.yingzi.web.utils;

import javax.servlet.http.HttpServletRequest;

import com.yingzixiyin.api.dto.UserInfo;

public class SessionUtil {
	public final static String LOGIN_USER_KEY = "WX_LOGIN_USER";

	public static void setLoginUserToSession(HttpServletRequest request,
			UserInfo userInfo) {
		if (userInfo != null) {
			request.getSession().setAttribute(LOGIN_USER_KEY, userInfo);
		}
	}

	public static void removeLoginUserToSession(HttpServletRequest request) {
		request.getSession().removeAttribute(LOGIN_USER_KEY);
	}

	public static UserInfo getLoginUserToSession(HttpServletRequest request) {
		return (UserInfo) request.getSession(false).getAttribute(LOGIN_USER_KEY);
	}
}
