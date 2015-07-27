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
        <%@ include file="../parts/head.jsp" %> 
        <div class="info">
            <div class="layout">
                <%@ include file="../parts/left.jsp" %> 
                <div class="maincontent">
                    <div class="subtitle">咨询师列表</div>
                    <table>
                        <tr><th>姓名</th><th>性别</th><th>状态</th><th>操作</th></tr>
                        <tr><td>zhangsan</td><td>男</td><td>未审核</td><td><a href="detail.html">详情</a><a>删除</a></td></tr>
                        <tr><td>lisi</td><td>男</td><td>审核通过</td><td><a href="detail.html">详情</a><a>删除</a></td></tr>
                    </table>
                </div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
        <script>
            $(".detail").addClass("cur");
        </script>
    </body>

</html>