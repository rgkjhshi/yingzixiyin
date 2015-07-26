<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>消息管理-英姿吸引</title>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="../favicon.ico" rel="shortcut icon">
        <link href="../css/base.css" rel="stylesheet" type="text/css">
        <script src="../js/jquery-1.9.1.min.js"></script>
    </head>
    <body>
        <div class="header" role="navigation">
            <div class="layout">
                <img height="20" alt="Brand" src="../images/logo.png">
                <ul class="signin">
                    <li>
                        <a href="../signin.jsp">登录</a>
                    </li>
                    <li>
                        <a href="../signup.jsp">注册</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="info">
            <div class="layout">
            	<div class="leftnav">
            		<ul class="leftitem">
            			<a href="info.jsp"><li class="detail"><i>&nbsp;</i>个人信息</li></a>
            			<a href="secure.jsp"><li class="secure"><i>&nbsp;</i>安全中心</li></a>
            			<a><li class="message cur"><i>&nbsp;</i>消息管理</li></a>
                        <ul class="sms">
                            <a><li class="active">全部咨询</li></a>
                            <a href="message_unread.jsp"><li>进行中的咨询</li></a>
                            <a href="message_read.jsp"><li>已结束的咨询</li></a>
                        </ul>
            		</ul>
            	</div>
            	<div class="maincontent">
            		<div class="subtitle">全部消息</div>
                    <div class="message_box">
                        <div class="message_item" node-type="item">
                            <div class="ms_header">
                                <div class="ms_name">user name</div>
                                <div class="ms_time">7月10日 18:20</div>
                            </div>
                            <div class="ms_content">
                                <div>哈哈，这里是发送的信息噢</div>
                                <div class="ms_new">new</div>
                                <div class="ms_ing">进行中</div>
                            </div>
                        </div>
                        <div class="message_item" node-type="item">
                            <div class="ms_header">
                                <div class="ms_name">user name</div>
                                <div class="ms_time">7月10日 18:20</div>
                            </div>
                            <div class="ms_content">
                                <div>哈哈，这里是发送的信息噢</div>
                                <div class="ms_end">已结束</div>
                            </div>
                        </div>
                        <div class="message_item" node-type="item">
                            <div class="ms_header">
                                <div class="ms_name">user name</div>
                                <div class="ms_time">7月10日 18:20</div>
                            </div>
                            <div class="ms_content">
                                <div>哈哈，这里是发送的信息噢</div>
                                <div class="ms_end">已结束</div>
                            </div>
                        </div>
                    </div>
            	</div>
            </div>
        </div>
        <div class="copyright">
            <div class="layout">
                <p>Copyright © 2015 英姿吸引
                    <a style="margin-left:10px;" href="http://www.miitbeian.gov.cn/" target="_blank">京ICP备 15035175号－1</a>
                </p>
            </div>
        </div>
    <script src="../js/dialogtips.js"></script>
    </body>
</html>
