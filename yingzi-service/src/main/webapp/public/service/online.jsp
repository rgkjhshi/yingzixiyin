<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path=request.getContextPath();    
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
<title>线上咨询</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <div class="main layout">
        <div style="margin: 30px 0;line-height: 2em;color: #666;">
            <!-- <textarea class="contentform" placeholder="请输入您要咨询的内容（500）字以内"></textarea> -->
            您正在申请进行线上咨询。<br/>线上咨询统一收费为59元/次，请通过微信支付进行付费。
        </div>
        <div class="choose_btn">确定付费</div>
    </div>
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
</body>
</html>