<%@page import="com.yingzixiyin.api.dto.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%
	String path=request.getContextPath();   
	request.setAttribute("path", path);
%>
<%
	String isbind = ""+session.getAttribute("isbind");
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
<title>咨询师详情</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <c:if test="${consultant!=null}">
    	<div class="main">
	        <ul class="consultants layout">
	            <li class="items">
	            	<a href="#">
	            	<img src='
		            	<c:if test="${empty consultant.avatar}">
				        	${path}/images/test.png
				        </c:if>
				        <c:if test="${(not empty consultant.avatar) }">
				        	${consultant.avatar}
				        </c:if>
	            	' />
	            		<div class="info">
	            			<div class="name">${consultant.name }</div>
	            			<div class="desc">
	            				${consultant.signature}
	            			</div>
	            		</div>
	            	</a>
	            </li>
	            <li>
	            	<div class="sub">个人简介</div>
	            	<div class="moreinfo">${consultant.introduce}</div>
	            </li>
	            <li>
	            	<div class="sub">咨询类型</div>
	            	<div class="moreinfo">${consultant.rangeType}</div>
	            </li>
	            <li>
	            	<div class="sub">专业背景</div>
	            	<div class="moreinfo">${consultant.professional}</div>
	            </li>
	            <li>
	            	<div class="sub">受训背景</div>
	            	<div class="moreinfo">${consultant.background}</div>
	            </li>
	            <li>
	            	<div class="sub">咨询时间</div>
	            	<div class="moreinfo">${consultant.bookTime}</div>
	            </li>
	            <li>
	            	<div class="sub">咨询价格</div>
	            	<div class="moreinfo">线上聊天咨询：${consultant.price} 元/次</div>
	            </li>
	            <li style="margin:40px 0;">
	    			<a class="choose_btn" id="onlinebtn" data-type="online" data-id="${consultant.id}">发起咨询</a>
	    			<a class="choose_btn" id="offlinebtn" data-type="offline" data-id="${consultant.id}">面对面咨询</a>
	    		</li>
	        </ul>
	    </div> 
    </c:if>
    
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
<script type="text/javascript">
    //检测是否绑定手机号
    var isbind = function(){//type：咨询类型（线上or线下）
        var id = $(this).attr("data-id");
        var type = $(this).attr("data-type");
        var url = "";
        var to = "consultant_"+type+".do?consultant_id="+id;
        var isbind = "<%=isbind %>";
        console.log(isbind);
        if(isbind!=""&&isbind!=null&&!isbind){
            window.location.href = to;
        }else{
            window.location.href = "/weixin/public/service/bind.jsp?id="+id+"&type="+type;
        }
    }

    $("#onlinebtn,#offlinebtn").on("click",isbind);
</script>
</body>
</html>