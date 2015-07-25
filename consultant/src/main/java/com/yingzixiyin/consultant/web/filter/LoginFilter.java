package com.yingzixiyin.consultant.web.filter;


import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 登陆拦截器
 */
@Component("loginFilter")
public class LoginFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(null == session.getAttribute("session_phone")) {
            logger.info("用户未登陆,session_phone={}", session.getAttribute("session_phone"));
            response.reset();
            response.setCharacterEncoding("UTF-8");
            Map<String, Object> map = Maps.newHashMap();
            map.put("status", -1);
            map.put("message", "用户未登陆");
            response.setHeader("Content-type","application/json;charset=UTF-8");
            response.getOutputStream().write(new Gson().toJson(map).getBytes("UTF-8"));
        } else {
            request.setAttribute("phone", session.getAttribute("session_phone"));
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}