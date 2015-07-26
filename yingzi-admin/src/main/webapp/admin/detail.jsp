<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
	String path=request.getContextPath();    
%>
<!DOCTYPE html>
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
            </div>
        </div>
        <div class="info">
            <div class="layout">
                <div class="leftnav">
                    <ul class="leftitem">
                        <a href="secure.html"><li class="secure"><i>&nbsp;</i>个人中心</li></a>
                        <a><li class="detail cur"><i>&nbsp;</i>咨询师管理</li></a>
                        <a href="record.html"><li class="message"><i>&nbsp;</i>咨询管理</li></a>
                    </ul>
                </div>
            	<div class="maincontent">
            		<div class="subtitle">咨询师详情</div>
                    <table id="detailtable">
                        <tr><th>姓名</th><td>zhangsan</td></tr>
                        <tr><th>性别</th><td>男</td></tr>
                        <tr><th>手机号码</th><td></td></tr>
                        <tr><th>昵称</th><td></td></tr>
                        <tr><th>真实姓名</th><td></td></tr>
                        <tr><th>年龄</th><td></td></tr>
                        <tr><th>E-mail</th><td></td></tr>
                        <tr><th>支付宝账号</th><td></td></tr>
                        <tr><th>专业背景</th><td></td></tr>
                        <tr><th>受训背景</th><td></td></tr>
                        <tr><th>咨询范围</th><td></td></tr>
                        <tr><th>咨询单价</th><td></td></tr>
                        <tr><th>可预约时间</th><td></td></tr>
                        <tr><th>地址</th><td></td></tr>
                        <tr><th>个人简介</th><td></td></tr>
                        <tr><th>个性签名</th><td></td></tr>
                    </table>
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
    </body>

</html>