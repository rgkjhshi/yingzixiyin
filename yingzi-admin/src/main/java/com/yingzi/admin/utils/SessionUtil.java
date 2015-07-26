package com.yingzi.admin.utils;

import javax.servlet.http.HttpServletRequest;

import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.core.entity.Admin;

public class SessionUtil {
	public final static String LOGIN_ADMIN_KEY = "YZ_LOGIN_ADMIN_USER";

	public static void setLoginAdminToSession(HttpServletRequest request,
			Admin userInfo) {
		if (userInfo != null) {
			request.getSession().setAttribute(LOGIN_ADMIN_KEY, userInfo);
		}
	}

	public static void removeLoginAdminToSession(HttpServletRequest request) {
		request.getSession().removeAttribute(LOGIN_ADMIN_KEY);
	}

	public static Admin getLoginAdminToSession(HttpServletRequest request) {
		return (Admin) request.getSession().getAttribute(LOGIN_ADMIN_KEY);
	}
}
