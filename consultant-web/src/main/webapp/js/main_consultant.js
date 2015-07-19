/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-07-19 15:00:49
 * @version $Id$
 */
(function(){
	
	// 格式验证
	var check = function(name,vid,tip){
		var tip = tip||"";
		var type = {
			"tel":[/^1\d{10}$/,"请输入正确的"+tip],
			// "pwd":[/\S+/,"密码不能为空"], 待修改
			"notnull":[/\S+/,tip+"不能为空"],
			"email":[/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/,tip+"格式不正确"],
			"number":[/^\d+(\.\d+)?$/,"请输入正确的"+tip]
		}
		if(name=="pwd2"){
			var pwd2 = $("#pwd2").val();
			var pwd = $("#pwd").val();
			if(pwd2 != pwd){
				$("#pwd2").siblings(".tips").text("两次输入的密码不一致");
				return false;
			}else{
				return true;
			}
		}else{
			var re = type[name][0];
			var tips = $("#"+vid).siblings(".tips");
			var str = $("#"+vid).val();

			if(!re.test(str)){
				tips.text(type[name][1]);
				return false;
			}else{
				tips.text("");
				return true;
			}
		}	
	}

	$(".sign_content input")
		.on("blur",function(){
			var name = $(this).attr("data-validate");
			var vid = $(this).attr("id");
			var tip = $(this).attr("data-tip");
			check(name,vid,tip);
		})
		.on("focus",function(){
			$(this).siblings(".tips").text("");
		});


	// 咨询师注册

	// 短信验证码获取
	var getCode = function(){
		var phone_num = $.trim($("#phone_num").val());
		var tip = $("#phone_num").attr("data-tip");
		var name = $("#phone_num").attr("data-validate");
		// 验证手机号码合法性
		if(!check(name,"phone_num",tip)){
			return;
		}

		$("#phone_num").attr("disabled","disabled");
		$("#sms_btn").html("<span id='count'>60</span> 秒后重新获取").unbind("click");

		// 倒计时
		var count = $("#count");
		var timedown = function(){
			var text = Number(count.text())-1;
			count.text(text);
			if(text==0){
				clearInterval(countdown);
				$("#sms_btn").html("获取短信验证码").bind("click",getCode);
			}
		}
		var countdown = setInterval(timedown,"1000");

		// 请求验证码
		// url:接口地址
		// "phone_num":改为接口中的参数名

		// $.post(url,{"phone_num":phone_num},function(){});
	};

	$("#sms_btn").on("click",getCode);

	// 提交注册表单
	var signup = function(){
		//检查短信验证码
		var sms_code = $.trim($("#sms_code").val());
		/*
		$.post(url,{"sms_code":sms_code},function(data){
			if(!data.status){
				("#sms_code").siblings(".tips").text("短信验证码输入有误");
				return;
			}
		});
		*/

		//检查其他数据格式
		if(check("tel","phone_num","手机号码")&&check("notnull","sms_code","验证码")&&check("notnull","pwd","密码")&&check("pwd2","pwd2")){
			//提交数据
			var phone_num = $.trim($("#phone_num").val());
			var pwd = $.trim($("#pwd").val());
			/*
			$.post(url,{"phone_num":phone_num,"pwd",pwd},function(data){
				if(!data.status){
					alert(data.errorCode);//输出错误原因
				}else{
					alert("注册成功，请完善您的个人信息！")
					window.location.href="admin/info.html";//跳转到后台管理页面
				}
			});
			*/
		}
	};

	$("#signup").on("click",signup);


	// 咨询师登录
	var signin = function(){

		//检查数据
		if(check("tel","phone_num","手机号码")&&check("notnull","pwd","密码")){
			//提交数据
			var phone_num = $.trim($("#phone_num").val());
			var pwd = $.trim($("#pwd").val());
			/*
			$.post(url,{"phone_num":phone_num,"pwd",pwd},function(data){
				if(!data.status){
					alert(data.errorCode);//输出错误原因
				}else{
					window.location.href="admin/info.html";//跳转到后台管理页面
				}
			});
			*/
		}
	}

	$("#signin").on("click",signin);

})();
