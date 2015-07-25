<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
<title>消费记录</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path%>/css/base.css">
<link href="<%=path%>/favicon.ico" rel="shortcut icon"></head>
<script type="text/javascript" src="<%=path%>/js/zepto.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/touch.js"></script>
<body>
    <div class="header">
        消费记录
    </div>
    <div class="main">
    	<ul class="layout">
    		<c:forEach items="${userRecords}" var="r">
    			<li class="record">
	    			<div class="sub">时间：<fmt:formatDate value="${r.createTime}" pattern="yyyy-MM-dd"/></div>
	    			<div>
	    				<span class="half"><strong>咨询师：</strong>${r.consultantName }</span>
	    				<span class="half"><strong>消费：</strong><fmt:formatNumber value="${r.price }" pattern="##.##" minFractionDigits="2"></fmt:formatNumber>元</span></div>
	    			<div><strong>咨询方式：</strong>${r.consultType.desc }</div>
	    			<div><strong>状态：</strong>${r.isCompleted.value==0?"未结束":"已结束" }</div>
    			</li>
    		</c:forEach>
    		<!-- <li class="record">
    			<div class="sub">时间：2015-04-23</div>
    			<div>
    				<span class="half"><strong>咨询师：</strong>张三</span><span class="half"><strong>消费：</strong>59元</span></div>
    			<div><strong>咨询方式：</strong>面对面咨询</div>
    			<div><strong>状态：</strong>已结束</div>
    		</li>
    		<li class="record">
    			<div class="sub">时间：2015-04-23</div>
    			<div>
    				<span class="half"><strong>咨询师：</strong>张三</span><span class="half"><strong>消费：</strong>300元</span></div>
    			<div><strong>咨询方式：</strong>线上谘询</div>
    			<div><strong>状态：</strong>咨询中</div>
    		</li>

    		<li class="record">
    			<div class="sub">时间：2015-04-23</div>
    			<div>
    				<span class="half"><strong>咨询师：</strong>张三</span><span class="half"><strong>消费：</strong>400元</span></div>
    			<div><strong>咨询方式：</strong>面对面咨询</div>
    			<div><strong>状态：</strong>已结束</div>
    		</li>

    		<li class="record">
    			<div class="sub">时间：2015-04-23</div>
    			<div>
    				<span class="half"><strong>咨询师：</strong>张三</span><span class="half"><strong>消费：</strong>519元</span></div>
    			<div><strong>咨询方式：</strong>面对面咨询</div>
    			<div><strong>状态：</strong>已结束</div>
    		</li> -->
    	</ul>
    </div>
<script type="text/javascript" src="<%=path%>/js/main_wechat.js"></script>
</body>
</html>