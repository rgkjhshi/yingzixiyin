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
</head>
<body>
    <div class="main" style="height:100%">
        <ul class="pro layout">
            <li onclick="javascript:window.location.href='<%=path %>/consultant/get_consultants.do?ctype=<%=RangeTypeEnum.ONE.getValue()%>'" style="background: #03C5E8;">
                <a>解救单身状态</a>
                <i></i>
                <div>单身情感问题咨询</div>
            </li>
            <li onclick="javascript:window.location.href='<%=path %>/consultant/get_consultants.do?ctype=<%=RangeTypeEnum.TWO.getValue()%>'" style="background:#FF5CA5;text-align:right;">
                <i style="background: url(../../images/arrow-right.png) no-repeat;background-size: 40px;background-position: right top;"></i>
                <a style="color:#FEE600;">让恋爱更幸福</a>
                <div style="color:#FFF0A4;">恋爱情感问题咨询</div>
            </li>
            <li onclick="javascript:window.location.href='<%=path %>/consultant/get_consultants.do?ctype=<%=RangeTypeEnum.THREE.getValue()%>'" style="background:#FEE600;">
                <a style="color:#9501FA;">夫妻那点事儿</a>
                <i></i>
                <div style="color:#BF45C3;">夫妻情感咨询</div>
            </li>
            <%-- <li><a href="<%=path %>/consultant/get_consultants.do?ctype=<%=RangeTypeEnum.FOUR.getValue()%>">夫妻关系困惑</a></li> --%>
        </ul>
    </div>
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
</body>
</html>