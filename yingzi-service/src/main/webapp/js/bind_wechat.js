/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-08-03 17:01:19
 * @version $Id$
 */
(function(){
	var checkPhone = function(phone){
		var reg = /^1\d{10}$/;

		if(!re.test(phone)){
			return false;
		}else{
			return true;
		}
	}

	var getCode = function(phone) {
		var phone = '';
		var url = '<%=path %>/user/getCheckCodeApi.do';
		if(checkPhone(phone)){
			$.post(url,{'phone':phone},function(data){
				if(data.status == 0){
					// 按钮处理
					$("#phone").attr("disabled", "disabled");
        			$("#getcode").html("<span id='count'>60</span> 秒后重新获取").unbind("click");
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
					alert(data.message);
					$("#phone").val("");
				}
			});
		}else{
			alert("手机号码格式有误！");
		}
	}

	var bindPhone = function(){
		var phone = $.trim($("#phone").val());
		var code = $.trim($("#code").val());
		var url = window.location.href;
		var para = url.split('?')[1].split('&');
		var l = para.length;
		var obj;
		for(var i = 0;i<l;i++){
			obj[para[i].split('=')[0]] = para[i].split('=')[1];
		}
		var to = "consultant_"+obj["type"]+".do?consultant_id="+obj["id"]; 
		if(checkPhone(phone)){
			$.post("<%=path %>/user/bindPhone.do",{'phone':phone,'checkCode':code},function(data){
				if(data.status == 0){
					alert("绑定成功，正在为您跳转");
					window.location.href = to;
				}else{
					alert(data.message);
				}
			});
		}else{
			alert("手机号码格式有误！");
		}
	}

	$("#bindphone").on("click",bindPhone);
	$("#getcode").on("click",getCode);

})();
