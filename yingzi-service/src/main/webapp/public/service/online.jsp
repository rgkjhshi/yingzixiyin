<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<title>线上咨询</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path %>/css/base.css">
<link href="<%=path %>/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path %>/js/touch.js"></script> --%>
</head>
<body>
    <div class="main layout">
        <div>
            <textarea class="contentform" placeholder="请输入您要咨询的内容（500）字以内"></textarea>
        </div>
        <div class="choose_btn">付费并发送</div>
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