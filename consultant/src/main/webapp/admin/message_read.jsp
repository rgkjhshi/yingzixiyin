<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>已读消息-英姿吸引</title>
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
            		<div class="subtitle">已读消息</div>
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
    <script>
        $("#message").addClass("cur");
        $("#message_all").show();
        $("#sms_ing").addClass("active");
    </script>
    </body>
</html>
