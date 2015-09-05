<%@page import="com.yingzixiyin.api.dto.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
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
    	<div class="main" style="padding-bottom:50px;">
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
	            			<div class="name">${consultant.name }<span><img src="../images/star.png" /><span id="favor" style="margin-left:0;" data-favor="">收藏</span></span></div>
	            			<div class="desc">
	            				地址：${consultant.address}<br/>¥ ${consultant.videoPrice} 元/次
	            			</div>
	            		</div>
	            		<div class="sig">${consultant.speciality}</div>
	            	</a>
	            </li>
	            <li>
	            	<div class="sub">个人简介</div>
	            	<div class="moreinfo moreintro" style="height:9em;overflow:hidden;">${consultant.introduce}</div>
	            	<div id="showmore" style="color:#307CD6;" data-type="more">阅读更多</div>
	            	<script>
	            		function showmore(){
	            			var type = $(this).attr("data-type");
	            			if(type=="more"){
	            				$(".moreintro").css("height","auto");
	            				$("#showmore").text("收起").attr("data-type","less");
	            			}else if(type=="less"){
	            				$(".moreintro").css("height","9em");
	            				$("#showmore").text("阅读更多").attr("data-type","more");
	            			}
	            		}
	            		$("#showmore").on("click",showmore);
	            	</script>
	            </li>
	            <li>
	            	<div class="sub">咨询类型</div>
	            	<div class="moreinfo">${consultant.rangeType.desc}</div>
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
	            	<div class="moreinfo">线上聊天咨询：${consultant.price} 元/次<br/>视频咨询：${consultant.videoPrice} 元/次<br/>面对面咨询：${consultant.facePrice} 元/次</div>
	            </li>
	            <li>
	            	<div class="sub">个性签名</div>
	            	<div class="moreinfo">${consultant.signature}</div>
	            </li>
	            <li style="margin:40px 0;">
	    			<a class="choose_btn" id="onlinebtn" data-type="online" data-id="${consultant.id}">线上聊天咨询</a>
	    			<a class="choose_btn" id="offlinebtn" data-type="offline" data-id="${consultant.id}">预约面对面</a>
	    			<a class="choose_btn" id="videobtn" data-type="video" data-id="${consultant.id}">视频咨询</a>
	    		</li>
	        </ul>
	    </div> 
    </c:if>
    
<script type="text/javascript" src="<%=path %>/js/main_wechat.js"></script>
<script type="text/javascript">
	//检测是否收藏咨询师
	var isfavor = function(){
		$.get("<%=path %>/consultant/isCollected.do?id="+$("#onlinebtn").attr("data-id"),function(data){
			if(data.returnCode==0){
				if(data.isCollect==1){//已收藏
					$("#favor").html("已收藏<span id='cancelfavor'>取消</span>");
					$("#favor").attr("data-favor","2");
				}else{
					$("#favor").attr("data-favor","1");
				}
			}else{
				alert(data.returnMessage);
			}
		});		
	}
	isfavor();
	//收藏咨询师
	$("#favor").on("click",function(){
		var favor = $("#favor").attr("data-favor");
		//判断是否已收藏咨询师
		if(favor=="1"){
			$.get("<%=path %>/consultant/collect_consultant.do?id="+$("#onlinebtn").attr("data-id"),function(data){
				console.log(data);
				if(data.returnCode==0){
					$("#favor").html("已收藏<span id='cancelfavor'>取消</span>");
					$("#favor").attr("data-favor","2");
				}else{
					alert(data.returnMessage);
				}
			});
		}	
	});
	//取消收藏
	$("#favor").on("click","#cancelfavor",function(){
		$.get("<%=path %>/consultant/cancel_consultant.do?id="+$("#onlinebtn").attr("data-id"),function(data){
			console.log(data);
			if(data.returnCode==0){
				$("#favor").html("收藏");
				$("#favor").attr("data-favor","1");
			}else{
				alert(data.returnMessage);
			}
		});
	});
</script>
<script type="text/javascript">
    //检测是否绑定手机号
    var isbind = function(){//type：咨询类型（线上or线下）
        var id = $(this).attr("data-id");
        var type = $(this).attr("data-type");
        var url = "";
        var to = "consultant_"+type+".do?consultant_id="+id;
        var isbind = "${WX_LOGIN_USER.isBind.value}";
        if(isbind=="1"){
            window.location.href = to;
        }else{
            window.location.href = "<%=path%>/public/service/bind.jsp?id="+id+"&type="+type+"&openId=${WX_LOGIN_USER.openId}";
        }
    }

    $("#onlinebtn,#offlinebtn,#videobtn").on("click",isbind);
</script>
</body>
</html>