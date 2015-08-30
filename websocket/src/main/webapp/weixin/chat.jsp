<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String path=request.getContextPath();
    String session_toPhone = "" + session.getAttribute("toPhone"); //从session里把a拿出来，并赋值给phone
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
<link rel="stylesheet" href="../css/weixin.css">
<link href="../favicon.ico" rel="shortcut icon"></head>
<script type="text/javascript" src="../js/zepto.min.js"></script>
<script src="../js/sockjs-0.3.min.js"></script>
<body>
    <div class="main layout" style="position: relative;width: 100%;height: 100%;">
        <ul class="consultants ms_detail" id="console" style="position: absolute;bottom: 0;overflow: scroll;height: 100%;padding: 20px 15px 50px 15px;width: 100%;box-sizing: border-box;">
            <!-- <li class="ms_my">
                <div class="ms_name">我</div>
                <div class="ms_content">咨询师您好。</div>
            </li>
            <li class="ms_rp">
                <div class="ms_name">咨询师张三</div>
                <div class="ms_content">您好，很高兴为您解决问题。</div>
            </li>
 -->        </ul>
        <div class="ms_send">
            <input class="ms_send_content" type="text" id="message" placeholder="请输入您的问题" /><span class="ms_send_btn" id='echo'>发送</span>
            <!-- 
            咨询已结束
            -->         
        </div>
    </div>
<script type="text/javascript" src="../js/wechat.js"></script>
</body>
</html>