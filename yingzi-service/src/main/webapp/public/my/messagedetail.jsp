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
<title>消息详情</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path%>/css/base.css">
<link href="<%=path%>/favicon.ico" rel="shortcut icon"></head>
<script type="text/javascript" src="<%=path%>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path%>/js/touch.js"></script> --%>
<body>
    <div class="main layout">
    	<ul class="consultants ms_detail">
    		<li class="ms_my">
    			<div class="ms_name">我</div>
    			<div class="ms_content">咨询师您好。</div>
    		</li>
    		<li class="ms_my">
    			<div class="ms_name">我</div>
    			<div class="ms_content">我想咨询一个问题。</div>
    		</li>
    		<li class="ms_rp">
    			<div class="ms_name">咨询师张三</div>
    			<div class="ms_content">您好，很高兴为您解决问题。</div>
    		</li>
    		<li class="ms_my">
    			<div class="ms_name">我</div>
    			<div class="ms_content">我最近心情不好，常常对周围的人发火，自己也找不到原因，我这到底是怎么了呢，请您帮我分析一下。</div>
    		</li>
    		<li class="ms_rp">
    			<div class="ms_name">咨询师张三</div>
    			<div class="ms_content">你这叫没事儿闲的。</div>
    		</li>
    		<li class="ms_rp">
    			<div class="ms_name">咨询师张三</div>
    			<div class="ms_content">是不是傻。</div>
    		</li>
    		<li class="ms_my">
    			<div class="ms_name">我</div>
    			<div class="ms_content">我想咨询一个问题。</div>
    		</li>
    		<li class="ms_rp">
    			<div class="ms_name">咨询师张三</div>
    			<div class="ms_content">您好，很高兴为您解决问题。</div>
    		</li>
    		<li class="ms_my">
    			<div class="ms_name">我</div>
    			<div class="ms_content">我最近心情不好，常常对周围的人发火，自己也找不到原因，我这到底是怎么了呢，请您帮我分析一下。</div>
    		</li>
    		<li class="ms_rp">
    			<div class="ms_name">咨询师张三</div>
    			<div class="ms_content">你这叫没事儿闲的。</div>
    		</li>
    		<li class="ms_rp">
    			<div class="ms_name">咨询师张三</div>
    			<div class="ms_content">是不是傻。</div>
    		</li>
       	</ul>
       	<div class="ms_send">
       		<input class="ms_send_content" type="text" placeholder="请输入您的问题" /><span class="ms_send_btn">发送</span>
            <!-- 
            咨询已结束
            -->       	
        </div>
    </div>
<script type="text/javascript" src="<%=path%>/js/main_wechat.js"></script>
</body>
</html>