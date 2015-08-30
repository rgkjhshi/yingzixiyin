/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-08-03 17:01:19
 * @version $Id$
 */
(function(){
	var checkPhone = function(phone){
		var reg = /^1\d{10}$/;
		if(!reg.test(phone)){
			return false;
		}else{
			return true;
		}
		
	}

	var getCode = function(phone) {
		var phone = $.trim($("#phone").val());
		var url = $(this).attr("data-url");
//		alert("phone:"+phone+"\nurl="+url);
		if(checkPhone(phone)){
			$.post(url,{'phone':phone},function(_data){
				var data=$.parseJSON(_data);
				if(data.returnCode == 0||data.returnCode == "0"){
					
					// 按钮处理
					$("#phone").attr("disabled", "disabled");
        			$("#getcode").html("<i id='count'>60</i> 秒后重新获取").unbind("click");
					// 倒计时
			        var count = $("#count");
			        var timedown = function () {
			            var text = Number(count.text()) - 1;
			            count.text(text);
			            if (text == 0) {
			                clearInterval(countdown);
			                $("#getcode").html("获取验证码").bind("click", getCode);
			            }
			        }
			        var countdown = setInterval(timedown, "1000");
					// alert("已发送");
				}else{
					alert(data.returnMessage);
					//$("#phone").val("");
				}
			});
		}else{
			alert("手机号码格式有误！");
		}
	}

	var bindPhone = function(){
		var phone = $.trim($("#phone").val());
		var code = $.trim($("#code").val());
		var openId = $.trim($("#openId").val());
		var url = window.location.href;
//		alert(url);
		var para = url.split('?')[1].split('&');
		var postUrl = $(this).attr("data-url");
		var l = para.length;
		var obj={};
		for(var i = 0;i<l;i++){
			obj[para[i].split('=')[0]] = para[i].split('=')[1];
		}
		var to = $("#bindphone").attr("data-goto")+obj["type"]+".do?consultant_id="+obj["id"]+"&openId="+openId; 
		if(checkPhone(phone)){
			$.post(postUrl,{'phone':phone,'checkCode':code,'openId':openId},function(_data){
				var data=$.parseJSON(_data);
				if(data.returnCode == 0||data.returnCode == "0"){
					alert("绑定成功，正在为您跳转");
					window.location.href = to;
				}else{
					alert(data.returnMessage);
				}
			});
		}else{
			alert("手机号码格式有误！");
		}
	}

	$("#bindphone").on("click",bindPhone);
	$("#getcode").on("click",getCode);

})();
