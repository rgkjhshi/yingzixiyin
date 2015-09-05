<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>已结束的咨询-英姿吸引</title>
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
            		<div class="subtitle">已结束的咨询</div>
            		 <div class="message_box">
            		   	<c:forEach items="${recordList}" var="r">
            		   		<div class="message_item" node-type="item">
                            <div class="ms_header">
                                <div class="ms_name" data-phone="${r.userPhone }"></div>
                                <div class="ms_time"><fmt:formatDate value="${ r.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                            </div>
                            <div class="ms_content">
                                <div>&nbsp;</div>
                                <div class="ms_ing">${r.isCompleted.value==0?"进行中":"已结束" }</div>
                            </div>
                        	</div>
            		   	</c:forEach>
              			${page}
                    </div>
                    <script>
                        var l = $(".ms_name").length;
                        for(var i =0;i<l;i++){
                            var a = $($(".ms_name")[i]).attr("data-phone");
                            var a1 = a.substring(0,3)+"****"+a.substring(7);
                            $($(".ms_name")[i]).text(a1);
                        }
                    </script>
            	</div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
    <script>
        $("#messagebtn").addClass("cur");
        $("#message_all").show();
        $("#sms_end").addClass("active");
    </script>
    </body>
</html>
