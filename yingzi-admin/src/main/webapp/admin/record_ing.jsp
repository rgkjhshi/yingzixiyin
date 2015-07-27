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
            		<div class="subtitle">进行中的咨询</div>
                    <table>
                        <tr>
                            <th>咨询师</th><th>用户</th><th>开始时间</th><th>操作</th>
                        </tr>
                        <tr>
                            <td>zhangsan</td><td>小白</td><td>2015-07-12 11:23:00</td><td><a>结束</a></td>
                        </tr>
                        <tr>
                            <td>lisi</td><td>二傻瓜</td><td>2015-07-13 11:23:00</td><td><a>重新开启</a></td>
                        </tr>
                    </table>
            	</div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
        <script>
            $(".message").addClass("cur");
            $("#record_all").show();
            $(".re_ing li").addClass("active");
        </script>
    </body>
</html>