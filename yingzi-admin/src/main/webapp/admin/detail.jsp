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
        <%@ include file="../parts/head.jsp" %> 
        <div class="info">
            <div class="layout">
                <%@ include file="../parts/left.jsp" %> 
            	<div class="maincontent">
            		<div class="subtitle">咨询师详情</div>
                    <table id="detailtable">
                        <tr><th>姓名</th><td>${cinfo.name }</td></tr>
                        <tr><th>头像</th><td><img src="${cinfo.avatar }" style="width:120px;height:120px;" /></td></tr>
                        <tr><th>性别</th><td>${cinfo.gender.desc }</td></tr>
                        <tr><th>手机号码</th><td>${cinfo.phone }</td></tr>
                        <!-- <tr><th>昵称</th><td>${cinfo.nickname }</td></tr> -->
                        <!-- <tr><th>真实姓名</th><td></td></tr> -->
                        <tr><th>年龄</th><td>${cinfo.age }</td></tr>
                        <tr><th>E-mail</th><td>${cinfo.email }</td></tr>
                        <tr><th>支付宝账号</th><td>${cinfo.alipay }</td></tr>
                        <tr><th>咨询范围</th><td>${cinfo.rangeType.desc }</td></tr>
                        <tr><th>聊天咨询</th><td><fmt:formatNumber value="${cinfo.price }" pattern="##.##" minFractionDigits="2"></fmt:formatNumber></td></tr>
                        <tr><th>视频咨询</th><td><fmt:formatNumber value="${cinfo.videoPrice }" pattern="##.##" minFractionDigits="2"></fmt:formatNumber></td></tr>
                        <tr><th>面对面咨询</th><td><fmt:formatNumber value="${cinfo.facePrice }" pattern="##.##" minFractionDigits="2"></fmt:formatNumber></td></tr>
                        <tr><th>咨询特长</th><td><textarea id="speciality">${cinfo.speciality }</textarea></td></tr>
                        <tr><th>专业背景</th><td><textarea id="professional">${cinfo.professional }</textarea></td></tr>
                        <tr><th>受训背景</th><td><textarea id="background">${cinfo.background }</textarea></td></tr>
                        <tr><th>可预约时间</th><td><textarea id="bookTime">${cinfo.bookTime}</textarea></td></tr>
                        <tr><th>地址</th><td><textarea id="address">${cinfo.address }</textarea></td></tr>
                        <tr><th>个人简介</th><td><textarea id="introduce" style="height:13em">${cinfo.introduce }</textarea></td></tr>
                        <tr><th>个性签名</th><td><textarea id="signature">${cinfo.signature }</textarea></td></tr>
                        <tr><th>状态</th><td id="status" data-status="${cinfo.status.value }">${cinfo.status.desc }</td></tr>
                    </table>
            	</div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
        <script>
            $(".detail").addClass("cur");
            var status = $.trim($("#status").attr("data-status"));
            if (status != '0'&&status!=0){
                // <a class='edit'>编辑</a>
                var btn = $("<div data-id='${cinfo.id}' class='checkbtns'><a class='accept'>保存并审核通过</a><a class='refuse'>审核不通过</a></div>");
                $(".maincontent").append(btn);
            }
        </script>
        <script src="../js/main_admin.js"></script>
    </body>

</html>