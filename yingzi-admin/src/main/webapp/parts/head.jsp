<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path_tmp=request.getContextPath();
%>
<div class="header" role="navigation">
    <div class="layout">
        <img height="20" alt="Brand" src="../images/logo.png">
        <ul class="signin">
            <li>欢迎你，<b>${YZ_LOGIN_ADMIN_USER.username }</b>&nbsp;|&nbsp;<a href="<%=path_tmp %>/admin/logout.do">注销</a></li>
        </ul>
    </div>
</div>