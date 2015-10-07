<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>微信支付</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
		<meta name="format-detection"content="telephone=no">
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<script type="text/javascript" src="<%=path %>/js/zepto.min.js"></script>
		<style>
			#close{
				position: relative;
			    margin-top: 50px;
			    width: 160px;
			    left: 50%;
			    margin-left: -80px;
			    text-align: center;
			    background-color: #eee;
			    padding: 15px 0;
			    color: #666;
			    border-radius: 30px;
			    border: 1px solid #e1e1e1;
			}
		</style>
	</head>
<%-- 	<body>
	公众号：${appId}<br/>
	时间戳：${timeStamp}<br/>
	随机串：${nonceStr}<br/>
	扩展包：${packageStr}<br/>
	签名方式：${signType}<br/>
	微信签名：${paySign}<br/>
	</body> --%>
	<body>
		<div style="padding:0 20px;">
			<h1 class="result" style="text-align: center;font-weight: bold;color: #44b549;margin: 60px 0;">正在支付...</h1>
			<p class="detail" style="line-height: 2em;font-size: 16px;color: #666;"></p>
			<div id="close">关闭</div>
		</div>
	</body>
	<script type="text/javascript">
		function onBridgeReady(){
		    WeixinJSBridge.invoke(
		       'getBrandWCPayRequest', {
		           "appId":"${appId}",     //公众号名称，由商户传入     
		           "timeStamp":"${timeStamp}",         //时间戳，自1970年以来的秒数     
		           "nonceStr": "${nonceStr}", //随机串     
		           "package":"${packageStr}",     
		           "signType":"${signType}",         //微信签名方式：     
		           "paySign":"${paySign}" //微信签名 
		       },
		       function(res){    
		       		// alert(res.err_msg);
		            if(res.err_msg.indexOf('ok')>0) {/* == "get_brand_wcpay_request:ok"*/ 
		            	
		            	$(".result").text("支付成功");
		            	$(".detail").html("线上聊天咨询请到咨询室与咨询师开始咨询吧！面对面咨询和视频咨询请联系客服预约时间、地点。");
		        	    // alert("支付成功！");
		            }    // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
		            else if(res.err_msg == "get_brand_wcpay_request:cancel" ){
		            	$(".result").text("支付取消");
		            	$(".detail").html("您的支付已取消，点击下方按钮关闭网页");
		        	   // alert("支付取消");
		            }else{
		            	$(".result").text("支付失败");
		            	$(".detail").html("支付出现问题啦，请稍后再试。点击下方按钮关闭网页");
		        	   // alert("支付失败");
		            }
		        	
		       }
		    );
			$("#close").on("click",function(){
				// window.close();
				WeixinJSBridge.call('closeWindow');
			});
		}
		if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
		   onBridgeReady();
		}
	</script>
</html>