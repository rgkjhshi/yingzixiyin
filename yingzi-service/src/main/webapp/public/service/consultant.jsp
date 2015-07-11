<%@page import="java.util.List"%>
<%@page import="com.yingzixiyin.api.dto.*"%>
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
<title>为您寻找匹配的咨询师</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/touch.js"></script>
</head>
<body>
    <div class="header">
        <i></i>
        匹配咨询师
    </div>
    <div class="main">
    	<div class="choose_btn layout" id="choose">优化筛选</div>
        <ul class="consultants layout">
        <c:forEach items="${consultants}" var="cinfo">
        	<li class="items">
            	<a href="consultant_deatil.do?id=${cinfo.id}">
            		<img src="<%=path %>/images/test.png" />
            		<div class="info">
            			<div class="name">${cinfo.name }</div>
            			<div class="desc">
            				${cinfo.introduce}
            			</div>
            		</div>
            	</a>
            </li>
        </c:forEach>
        <!--   <li class="items">
            	<a href="detail.html">
            		<img src="../../images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
            </li>
            <li class="items">
            	<a href="detail.html">
            		<img src="../../images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
            </li>
            <li class="items">
            	<a href="detail.html">
            		<img src="../../images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
            </li>
            <li class="items">
            	<a href="detail.html">
            		<img src="../../images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
            </li>
            <li class="items">
            	<a href="detail.html">
            		<img src="../../images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
            </li>
            <li class="items">
            	<a href="detail.html">
            		<img src="../../images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
            </li>
            <li class="items">
            	<a href="detail.html">
            		<img src="../../images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
            </li> -->
        </ul>
    </div>
    <div class="layer" id="choose_layer"></div>
    <div class="choose_dialog" id="choose_dialog">
        <div class="sub">筛选条件</div>
        <ul>
            <li>
                <div class="condi">性别：</div>
                <label><input type="radio" value="0" checked="checked" />不限</label>
                <label><input type="radio" value="1" />男</label>
                <label><input type="radio" value="2" />女</label>
            </li>
            <li>
                <div class="condi">年龄：</div>
                <label><input type="checkbox" value="0" checked="checked" />不限</label>
                <label><input type="checkbox" value="1" />30岁以下</label>
                <label><input type="checkbox" value="2" />31-50岁</label>
                <label><input type="checkbox" value="3" />51岁以上</label>
            </li>
            <li class="choose_min_btn">筛选</li>
        </ul>
    </div>
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
</body>
</html>