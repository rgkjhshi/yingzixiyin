<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.yingzixiyin.api.enums.*"%>
<%
	String path=request.getContextPath();    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="format-detection"content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>问题分类</title>
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <div class="main">
        <ul class="pro layout">
            <li><a href="<%=path %>/consultant/get_consultants.do?ctype=<%=RangeTypeEnum.ONE.getValue()%>">恋爱关系困惑</a></li>
            <li><a href="<%=path %>/consultant/get_consultants.do?ctype=<%=RangeTypeEnum.TWO.getValue()%>">情侣关系与维护</a></li>
            <li><a href="<%=path %>/consultant/get_consultants.do?ctype=<%=RangeTypeEnum.THREE.getValue()%>">婚前关系问题</a></li>
            <li><a href="<%=path %>/consultant/get_consultants.do?ctype=<%=RangeTypeEnum.FOUR.getValue()%>">夫妻关系困惑</a></li>
        </ul>
    </div>
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
</body>
</html>