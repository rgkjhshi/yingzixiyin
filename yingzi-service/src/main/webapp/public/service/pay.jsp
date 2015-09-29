<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>微信支付页面</title>
	</head>
<%-- 	<body>
	公众号：${appId}<br/>
	时间戳：${timeStamp}<br/>
	随机串：${nonceStr}<br/>
	扩展包：${packageStr}<br/>
	签名方式：${signType}<br/>
	微信签名：${paySign}<br/>
	</body> --%>
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
		           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		        	   alert("支付成功");
		           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
		           else if(res.err_msg == "get_brand_wcpay_request:cancel" ){
		        	   alert("支付取消");
		           }
		           else{
		        	   alert("支付失败");
		           }
		       }
		   ); 
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