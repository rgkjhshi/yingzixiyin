<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>进行中的咨询-英姿吸引</title>
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
            		   <div class="message_box">
            		   	<c:forEach items="${recordList}" var="r">
            		   		<div class="message_item" node-type="item">
                                <form id="chatform" method="post" name="chatform" action="/websocket/chat.do">
                                    <input type="hidden" name="phone" value="${r.consultantPhone}" />
                                    <input type="hidden" name="toPhone" value="${r.userPhone}" />
                                    <input type="hidden" name="recordId" value="${r.id}" />
                                </form>
                                <div class="ms_header">
                                    <div class="ms_name" data-phone="${r.userPhone }"></div>
                                    <div class="ms_time"><fmt:formatDate value="${ r.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                </div>
                                <div class="ms_content">
                                    <div style="color:#ff0000;">
                                        <c:if test="${r.consultantUnReadMessageCount>0}">
                                            ${r.consultantUnReadMessageCount}条未读消息
                                        </c:if>
                                    </div>
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
    //打开咨询详情弹窗
    $(".message_item").on("click",function(){
        $(this).find("form")[0].submit();
    });
    </script>
    <script>
        $("#messagebtn").addClass("cur");
        $("#message_all").show();
        $("#sms_ing").addClass("active");
    </script>
    </body>
</html>
