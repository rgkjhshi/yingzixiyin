<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path=request.getContextPath();   
	request.setAttribute("path", path);
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
<title>线下咨询</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <div class="main">
        <ul class="consultants layout">
            <li class="items">
            	<a href="detail.html">
            		<img src='
		            	<c:if test="${empty cinfo.avatar}">
				        	${path}/images/test.png
				        </c:if>
				        <c:if test="${(not empty cinfo.avatar) }">
				        	${cinfo.avatar}
				        </c:if>
	            	' />
            		<div class="info">
            			<div class="name">${cinfo.name }</div>
            			<div class="desc">
            				${cinfo.introduce }
            			</div>
            		</div>
            	</a>
            </li>
            <li>
            	<div class="sub">预约时间</div>
                <div class="dinfo">${cinfo.bookTime}</div>
            	<div class="sub">咨询地点</div>
                <div class="dinfo">${cinfo.address }</div>
                <div class="sub">收费标准</div>
                <div class="dinfo"><fmt:formatNumber value="${cinfo.price }" pattern="##.##" minFractionDigits="2"></fmt:formatNumber>元／次</div>
            </li>
            <li>
                <div class="choose_btn">预约</div>
            </li>
        </ul>
    </div>
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
<script type="text/javascript">
    //检测是否绑定手机号
    var isbind = function(type){//type：咨询类型（线上or线下）
        var id = $("this").attr("data-id");
        var url = "";
        var to = "";
        if(type=="online"){
            to = "consultant_online.do?consultant_id="+id;
        }else{
            to = "consultant_offline.do?consultant_id="+id;
        }
        // $.get(url,function(data){
        //  if(data.stasuts == 0){
        //      window.location.href = to;
        //  }else{
        //      window.location.href = "bind.jsp?id="+id+"&type="+type;
        //  }
        // });
        window.location.href = "bind.jsp&id="+id+"&type="+type;
    }

    $("#onlinebtn").on("click",isbind("online"));
    $("#offlinebtn").on("click",isbind("offline"));
</script>
</body>
</html>