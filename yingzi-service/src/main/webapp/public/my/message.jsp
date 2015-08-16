<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="format-detection"content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>我的消息</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path%>/css/base.css">
<link href="<%=path%>/favicon.ico" rel="shortcut icon"></head>
<script type="text/javascript" src="<%=path%>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path%>/js/touch.js"></script> --%>
<body>
    <div class="main layout">
    	<ul class="consultants ms_record">
    		<c:forEach items="${myMessages}" var="m">
    			<li class='record unend <c:out value="${m[\"is_read\"]==0 ?\"new_ms\":\"\"}"></c:out>'>
    				<form id="chatform" method="post" name="chatform" action='${ chaturl}'>
                        <input type="hidden" name="phone" value="${WX_LOGIN_USER.phone}" />
                        <input type="hidden" name="toPhone" value='<c:out value="${m[\"phone\"]}"></c:out>' />
                        <input type="hidden" name="recordId" value='<c:out value="${m[\"record_id\"]}"></c:out>' />
                    </form>
	        		<a>
	        			<div class="message">
		        			来自<span class="red_font">
		        				<c:out value="${m[\"name\"]}"></c:out>
		        			</span>的消息&nbsp;&nbsp;<c:out value="${m[\"msgcount\"]}"></c:out>
		        			<span class="ms_status">
		        			<c:out value="${m[\"is_read\"]==0 ?\"未读\":\"&nbsp;\"}"></c:out>
		        			</span>
		        		</div>
		        		<i></i>
		        	</a>
    			</li>
    		</c:forEach>
       	</ul>
    </div>
<script type="text/javascript" src="<%=path%>/js/main_wechat.js"></script>
<script>
    //打开咨询详情弹窗
    $(".unend").on("click",function(){
        $(this).find("form")[0].submit();
    });
</script>
</body>
</html>