package com.yingzi.admin.filter;


import com.google.common.collect.Maps;
import com.yingzi.admin.constant.LoginConstant;
import com.yingzi.admin.utils.SessionUtil;
import com.yingzixiyin.api.dto.AdminInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

/**
 * 页面拦截器
 */
@Component("pageFilter")
public class PageFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(PageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        AdminInfo adminInfo=SessionUtil.getLoginAdminToSession(request);
        String url=request.getRequestURI();
        if(!StringUtils.isEmpty(url)&&url.endsWith(".do")){
        	filterChain.doFilter(request, response);
        	return;
        }
        if(null == adminInfo) {
            logger.info("用户未登陆,访问的url："+request.getRequestURI());
            response.sendRedirect(request.getContextPath()+"/"+LoginConstant.NO_LOGIN_URL);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}