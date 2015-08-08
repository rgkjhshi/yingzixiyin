<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
        <%@ include file="../parts/foot.jsp" %> 
        <script src="../js/chat.js"></script>
    <!--<script src="../js/dialogtips.js"></script>-->
    <script>
        $("#message").addClass("cur");
        $("#message_all").show();
        $("#sms_all").addClass("active");
    </script>
    </body>
</html>
