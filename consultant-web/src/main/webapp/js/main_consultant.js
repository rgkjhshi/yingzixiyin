/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-07-19 15:00:49
 * @version $Id$
 */
(function(){
	
	// 格式验证
	var check = function(vid){//传入id和默认的提示
		var tip = $("#"+vid).attr("data-tip")||"";
		var name = $("#"+vid).attr("data-validate")||"default";
		var type = {
			"tel":[/^1\d{10}$/,"请输入正确的"+tip],
			"pwd":[/^[\d_a-zA-Z]{6,12}$/,"请输入6-12位包含数字、字母或下划线的密码"],
			"notnull":[/\S+/,tip+"不能为空"],
			"email":[/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/,tip+"格式不正确"],
			"number":[/^\d+(\.\d+)?$/,"请输入正确的"+tip],
			"default":[/\S+/,"不能为空"]
		}
		if(vid=="pwd2"){
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

	$(".sign_content input,.sign_content textarea")
		.on("blur",function(){
			var vid = $(this).attr("id");
			check(vid);
		})
		.on("focus",function(){
			$(this).siblings(".tips").text("");
		});


	// 咨询师注册

	// 短信验证码获取
	var getCode = function(){
		var phone = $.trim($("#phone").val());
		var tip = $("#phone").attr("data-tip");
		var name = $("#phone").attr("data-validate");
		// 验证手机号码合法性
		if(!check("phone")){
			return;
		}

		$("#phone").attr("disabled","disabled");
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

		// $.post(url,{"phone":phone},function(){});
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
		if(check("phone")&&check("sms_code")&&check("pwd")&&check("pwd2")){
			//提交数据
			var phone = $.trim($("#phone").val());
			var pwd = $.trim($("#pwd").val());
			/*
			$.post(url,{"phone":phone,"password",pwd},function(data){
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
		if(check("phone")&&check("pwd")){
			//提交数据
			var phone = $.trim($("#phone").val());
			var pwd = $.trim($("#pwd").val());
			/*
			$.post(url,{"phone":phone,"pwd",pwd},function(data){
				if(!data.status){
					alert(data.errorCode);//输出错误原因
				}else{
					window.location.href="admin/info.html";//跳转到后台管理页面
				}
			});
			*/
		}
	};

	$("#signin").on("click",signin);


	//完善个人信息
	var moreinfo = function(){
		var gender = $("input[name=gender]:checked").val();
		if(!gender){
			$(".gender_tips").text("请选择您的性别");
			return;
		}
		//检查数据
		if(check("nickname")&&check("name")&&check("age")&&check("email")&&check("alipay")&&check("professional")&&check("background")&&check("price")&&check("book_time")&&check("address")&&check("introduce")&&check("signature")){
			
			//格式化数据
			var _data = decodeURIComponent("{'"+$("#infoform").serialize()+"'}");
			//将form serialize格式转换为json
			var reg1 = new RegExp("=","g");
			var reg2 = new RegExp("&","g"); 
			var _newdata=_data.replace(reg1,"':'").replace(reg2,"','");  

			//提交数据
			/*
			$.post(url,_newdata,function(data){
				if(!data.status){
					alert(data.errorCode);//输出错误原因
				}else{
					alert("个人资料提交成功，请耐心等待审核！");
					window.location.href="admin/info.html";//刷新页面
					//锁定个人信息，审核完成之前不允许修改，待完成
				}
			});
			*/
		}
	};

	$("#moreinfo").on("click",moreinfo);

})();
