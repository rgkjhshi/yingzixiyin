<%@page import="com.yingzixiyin.api.dto.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
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
<title>咨询师详情</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <div class="header">
        <i></i>
        咨询师详情
    </div>
    <c:if test="${consultant!=null}">
    	<div class="main">
	        <ul class="consultants layout">
	            <li class="items">
	            	<a href="#">
	            		<img src="<%=path %>/images/test.png" />
	            		<div class="info">
	            			<div class="name">${consultant.name }</div>
	            			<div class="desc">
	            				${consultant.introduce }
	            			</div>
	            		</div>
	            	</a>
	            </li>
	            <li>
	            	<div class="sub">${consultant.background}</div>
	            	<div class="moreinfo">选择英姿，选择更专业的咨询师，让我们教会您如何去爱，去照顾家庭、爱人，并且提升自己的情感能力，为自己创造一个更好的生活。您值得被爱，更需要好好宠爱自己。英姿吸引——帮您找到身边专业的情感咨询师。选择英姿，选择更专业的咨询师，让我们教会您如何去爱，去照顾家庭、爱人，并且提升自己的情感能力，为自己创造一个更好的生活。您值得被爱，更需要好好宠爱自己。英姿吸引——帮您找到身边专业的情感咨询师。</div>
	            </li>
	            <li>
	    			<a class="choose_btn" href="consultant_online.do?consultant_id=${consultant.id}">发起咨询</a>
	    			<a class="choose_btn" href="consultant_offline.do?consultant_id=${consultant.id}">面对面咨询</a>
	    		</li>
	        </ul>
	    </div> 
    </c:if>
    
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
</body>
</html>