<%@page import="com.yingzixiyin.api.dto.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%
	String path=request.getContextPath();   
	request.setAttribute("path", path);
	String openId=request.getParameter("openId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="format-detection"content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>绑定手机</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body style="background:#ececec;">
	<div class="main">
		<div class="warntips"><i></i>您还未绑定手机</div>
		<div class="bind_input">
			<input type="text" id="phone" placeholder="11位手机号码" maxlength="11"/>
			<span id="getcode" data-url="<%=path %>/user/getCheckCodeApi.do">获取验证码</span>
		</div>
		<div class="bind_input">
			<input type="text" id="code" placeholder="输入短信验证码" />
		</div>
		<div class="bind_btn">
			<a id="bindphone" data-url="<%=path %>/user/bindPhone.do" data-goto="<%=path %>/consultant/consultant_">绑定手机</a>
			<input id="openId" type="hidden" value="<%=openId%>">
		</div>
    </div>
<script type="text/javascript" src="<%=path %>/js/bind_wechat.js"></script>
</body>
</html>