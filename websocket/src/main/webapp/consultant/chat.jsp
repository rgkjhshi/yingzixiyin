<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String session_toPhone = "" + session.getAttribute("toPhone"); //从session里把a拿出来，并赋值给phone
String recordId = "" + session.getAttribute("recordId");
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
        <script src="../js/sockjs-0.3.min.js"></script>
    </head>
    <body>
        <%@ include file="../parts/head.jsp" %> 
        <div class="info">
            <div class="layout">
                <%@ include file="../parts/left.jsp" %> 
                <div class="maincontent">
                    <div class="detail_box">
                        <!-- <i id="closeDialogBtn"></i> -->
                        <div class="dialog_header" node-type="header">来自&nbsp;<strong><%=session_toPhone %></strong>&nbsp;的咨询</div>
                        <div class="dialog_body">
                            <div class="dialog_content">
                                <ul id="console">
                                </ul>
                            </div>
                            <div class="dialog_reply">
                                <div class="reply_inner">
                                    <textarea class="reply_content" id="message"></textarea>
                                    <a class="close_chat" data-recordId="<%=recordId %>">结束咨询</a>
                                    <a class="send_btn" id='echo'>发送</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
    <script src="../js/chat.js"></script>
    <script>
        $("#message").addClass("cur");
        $("#message_all").show();
    </script>
    </body>
</html>