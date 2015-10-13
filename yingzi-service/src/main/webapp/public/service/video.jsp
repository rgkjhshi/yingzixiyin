<%@ page import="java.util.List"%>
<%@page import="com.yingzixiyin.api.enums.ConsultTypeEnum"%>
<%@ page import="com.yingzixiyin.api.dto.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path=request.getContextPath();   
	request.setAttribute("path", path);
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
<title>视频咨询</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <div class="main">
        <ul class="consultants layout" style="padding-bottom:50px;">
            <li class="items">
            	<a href="detail.html">
            		<img src='
		            	<c:if test="${empty cinfo.avatar}">
				        	${path}/images/test.png
				        </c:if>
				        <c:if test="${(not empty cinfo.avatar) }">
				        	${cinfo.avatar}
				        </c:if>
	            	' />
            		<div class="info">
            			<div class="name">${cinfo.name }</div>
            			<div class="desc">
            				${cinfo.introduce }
            			</div>
            		</div>
            	</a>
            </li>
            <li>
            	<div class="sub">预约时间</div>
                <div class="dinfo">${cinfo.bookTime}</div>
            	<div class="sub">咨询地点</div>
                <div class="dinfo">${cinfo.address }</div>
                <div class="sub">收费标准</div>
                <div class="dinfo"><fmt:formatNumber value="${cinfo.videoPrice }" pattern="##.##" minFractionDigits="2"></fmt:formatNumber>元／次</div>
            </li>
            <li style="margin: 30px 0;line-height: 2em;color: #666;">
                视频咨询请您在线上付款预约，客服将在第一时间与您沟通具体时间地点。
            </li>
            <li>
                 <a class="choose_btn" href="<%=path%>/wxpay/prepay.do?consultant_id=${cinfo.id}&counsultantType=<%=ConsultTypeEnum.VIDEO.getValue()%>">付款预约</a>
            </li>
        </ul>
    </div>
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
</body>
</html>