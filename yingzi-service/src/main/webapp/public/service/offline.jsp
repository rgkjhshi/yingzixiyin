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
<title>线下咨询</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <div class="main">
        <ul class="consultants layout">
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
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
            </li>
            <li>
            	<div class="sub">预约时间</div>
                <div class="dinfo">每周三8:00-18:00</div>
            	<div class="sub">咨询地点</div>
                <div class="dinfo">北京市海淀区西土城路10号北邮科技大厦3层601室</div>
                <div class="sub">收费标准</div>
                <div class="dinfo">300元／次</div>
            </li>
            <li>
                <div class="choose_btn">预约</div>
            </li>
        </ul>
    </div>
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
</body>
</html>