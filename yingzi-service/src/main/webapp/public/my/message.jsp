<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
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
<title>我的消息</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path%>/css/base.css">
<link href="<%=path%>/favicon.ico" rel="shortcut icon"></head>
<script type="text/javascript" src="<%=path%>/js/zepto.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/touch.js"></script>
<body>
    <div class="header">
        我的消息
    </div>
    <div class="main layout">
    	<ul class="consultants ms_record">
    		<c:forEach items="${myMessages}" var="m">
    		<c:out value="${m[\"completed\"]}"></c:out>
    			<li class='record unend <c:out value="${m[\"is_read\"]==0 ?\"new_ms\":\"\"}"></c:out>'>
	        		<a href="getmessages.do?consultantId=<c:out value='${m[\"consultant_id\"]}'></c:out>">
	        			<div class="message">
		        			来自<span class="red_font">
		        				<c:out value="${m[\"username\"]}"></c:out>
		        			</span>的消息(<c:out value="${m[\"msgcount\"]}"></c:out>)
		        			<span class="ms_status">
		        			<c:out value="${m[\"is_read\"]==0 ?\"未读\":\"已读\"}"></c:out>
		        			</span>
		        		</div>
		        		<i></i>
		        	</a>
    			</li>
    		</c:forEach>
    		
    		<!-- <li class="record unend">
        		<a href="messagedetail.html">
        			<div class="message">
	        			来自<span class="red_font">张三</span>的消息
	        			<span class="ms_status">咨询中</span>
	        		</div>
	        		<i></i>
	        	</a>
    		</li>
    		<li class="record">
        		<a href="messagedetail.html">        			
        			<div class="message">
	        			来自<span class="red_font">张三</span>的消息
	        			<span class="ms_status">已结束</span>
	        		</div>
	        		<i></i>
	        	</a>    		
	        </li>
    		<li class="record">
        		<a href="messagedetail.html">
        			<div class="message">
	        			来自<span class="red_font">张三</span>的消息
	        			<span class="ms_status">已结束</span>
	        		</div>
	        		<i></i>
	        	</a>
    		</li>
    		<li class="record">
        		<a href="messagedetail.html">        			
        			<div class="message">
	        			来自<span class="red_font">张三</span>的消息
	        			<span class="ms_status">已结束</spa已结束n>
	        		</div>
	        		<i></i>
	        	</a>
    		</li> -->
       	</ul>
    </div>
<script type="text/javascript" src="<%=path%>/js/main_wechat.js"></script>
</body>
</html>