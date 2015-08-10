<%@ page import="java.util.List"%>
<%@ page import="com.yingzixiyin.api.dto.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<%
	String path=request.getContextPath();
	request.setAttribute("path", path);
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="format-detection"content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>为您寻找匹配的咨询师</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <div class="main">
    	<div class="choose_btn layout" id="choose">优化筛选</div>
        <ul class="consultants layout" id="consultant_list">
        <c:forEach items="${consultants}" var="cinfo">
        	<li class="items">
            	<a href="consultant_deatil.do?id=${cinfo.id}">
            	<img src='
	            	<c:if test="${empty cinfo.avatar}">
			        	${path}/images/test.png
			        </c:if>
			        <c:if test="${(not empty cinfo.avatar) }">
			        	${cinfo.avatar}
			        </c:if>
            	' />
            		<div class="info">
            			<div class="name nameall">${cinfo.name }</div>
            			<div class="desc descall">${cinfo.age}岁，¥ ${cinfo.videoPrice}&nbsp;元/次，地址：${cinfo.address}<br/>${cinfo.speciality}</div>
            		</div>
                    <div class="sig">签名：${cinfo.signature}</div>
            	</a>
            </li>
        </c:forEach>
        </ul>
    </div>
    <div class="layer" id="choose_layer"></div>
    <div class="choose_dialog" id="choose_dialog">
        <i class="choose_close"></i>
        <div class="sub">筛选条件</div>
        <ul>
            <li>
                <div class="condi">性别：</div>
                <label><input type="radio" value="9" checked="checked" name="gender" />不限</label>
                <label><input type="radio" value="1" name="gender" />男</label>
                <label><input type="radio" value="2" name="gender" />女</label>
            </li>
            <li>
                <div class="condi">年龄：</div>
                <label><input type="radio" value="0" checked="checked" name="age" />不限</label>
                <label><input type="radio" value="1" name="age" />30岁以下</label>
                <label><input type="radio" value="2" name="age" />31-50岁</label>
                <label><input type="radio" value="3" name="age" />51岁以上</label>
                <input type="hidden" name="ctype" id="ctype" value="${ctype}">
            </li>
            <li class="choose_min_btn" id="choose_g_a">筛选</li>
        </ul>
    </div>
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
</body>
</html>