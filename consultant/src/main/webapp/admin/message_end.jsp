<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>未读消息-英姿吸引</title>
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
            		<div class="subtitle">未读消息</div>
            		 <div class="message_box">
            		   	<c:forEach items="${recordsList}" var="r">
            		   		<div class="message_item" node-type="item">
                            <div class="ms_header">
                                <div class="ms_name">${r.consultantName }</div>
                                <div class="ms_time"><fmt:formatDate value="${ r.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                            </div>
                            <div class="ms_content">
                                <div>哈哈，这里是发送的信息噢</div>
                                <div class="ms_new">new</div>
                                <div class="ms_ing">${r.isCompleted.value==0?"进行中":"已结束" }</div>
                            </div>
                        	</div>
            		   	</c:forEach>
              			${page}
                    </div>
            	</div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
    <script>
    //聊天跳转
    function showDialog(phone,tophone,id){//传入弹窗类型：咨询详情   mid：咨询id
        var url = "http://" + window.location.host + "/websocket/chat.do";
        $.post(url,{'phone':phone,'tophone':tophone,'id':id},function(){

        });
    }
    //打开咨询详情弹窗
    $(".message_item").on("click",function(){
        var phone = '13167394537';
        var toPhone = '13504330637';
        var recordId = '1';
        showDialog(phone,tophone,id);
    });
    </script>
    <script>
        $("#messagebtn").addClass("cur");
        $("#message_all").show();
        $("#sms_end").addClass("active");
    </script>
    </body>
</html>
