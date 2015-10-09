/**
 *
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-07-19 15:00:49
 * @version $Id$
 */
(function () {

    var currentURL = window.location.href;
    var getCheckCodeURL = "http://" + window.location.host + "/consultant/getCheckCodeApi.htm";
    var loginURL = "http://" + window.location.host + "/consultant/loginApi.htm";
    var registerUrl = "http://" + window.location.host + "/consultant/registerApi.htm";
    var infoURL = "http://" + window.location.host + "/consultant/admin/queryInfoApi.htm";
    var changePwdURL = "http://" + window.location.host + "/consultant/admin/changePasswordApi.htm";
    var updateInfoURL = "http://" +window.location.host + "/consultant/admin/updateInfoApi.htm";

    // 格式验证
    var check = function (vid) {//传入id和默认的提示
        if (!vid) {
            return true;
        }
        var tip = $("#" + vid).attr("data-tip") || "";
        var name = $("#" + vid).attr("data-validate") || "default";
        var type = {
            "tel": [/^1\d{10}$/, "请输入正确的" + tip],
            "pwd": [/^[\d_a-zA-Z]{6,12}$/, "请输入6-12位包含数字、字母或下划线的密码"],
            "notnull": [/\S+/, tip + "不能为空"],
            "tooless": [/\S+/, tip + "不能为空"],
            "email": [/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/, tip + "格式不正确"],
            "number": [/^\d+(\.\d+)?$/, "请输入正确的" + tip],
            "default": [/\S+/, "不能为空"]
        }
        if (vid == "pwd2") {
            var pwd2 = $("#pwd2").val();
            var pwd = $("#pwd").val();
            if (pwd2 != pwd) {
                $("#pwd2").siblings(".tips").text("两次输入的密码不一致");
                return false;
            } else {
                return true;
            }
        } else if(vid == "introduce"){
            var content = $.trim($("#introduce").val());
            var l = content.length;
            if(l<150){
                alert("个人简介不得少于150字！");
                return false;
            }else{
                return true;
            }
        } else {
            var re = type[name][0];
            var tips = $("#" + vid).siblings(".tips");
            var str = $("#" + vid).val();

            if (!re.test(str)) {
                tips.text(type[name][1]);
                return false;
            } else {
                tips.text("");
                return true;
            }
        }
    }

    $(".sign_content input,.sign_content textarea")
        .on("blur", function () {
            var vid = $(this).attr("id");
            check(vid);
        })
        .on("focus", function () {
            $(this).siblings(".tips").text("");
        });


    // 咨询师注册


    // 更换验证码
    var changeCode = function(){
        var _this = $("#yanzheng_btn");
        var canClick = _this.attr("data-boo");
        if(canClick==1){
            var one = Math.floor(Math.random()*10).toString();
            var two = Math.floor(Math.random()*10).toString();
            var three = Math.floor(Math.random()*10).toString();
            var four = Math.floor(Math.random()*10).toString();
            var yanzhengcode = one+two+three+four;
            $("#yanzheng_btn").text(yanzhengcode);
        }
    }

    $("#yanzheng_btn").on("click",changeCode);

    // 短信验证码获取
    var getCode = function () {
        var phone = $.trim($("#phone").val());
        var tip = $("#phone").attr("data-tip");
        var name = $("#phone").attr("data-validate");
        // 验证手机号码合法性
        if (!check("phone")) {
            return;
        }

        if(!check("yanzhengcode")){
            return;
        }

        var yanzhengcode = $("#yanzheng_btn").text();
        var yanzhenginput = $("#yanzhengcode").val();

        if(yanzhenginput!=yanzhengcode){
            alert("验证码错误");
            return;
        }

        $("#yanzheng_btn").attr("data-boo","0");
        $("#phone").attr("disabled", "disabled");
        $("#yanzhengcode").attr("disabled", "disabled");
        $("#sms_btn").html("<span id='count'>60</span> 秒后重新获取").unbind("click");

        // 倒计时
        var count = $("#count");
        var timedown = function () {
            var text = Number(count.text()) - 1;
            count.text(text);
            if (text == 0) {
                clearInterval(countdown);
                $("#sms_btn").html("获取短信验证码").bind("click", getCode);
            }
        }
        var countdown = setInterval(timedown, "1000");

        // 请求验证码
        $.post(getCheckCodeURL, {"phone": phone}, function () {});
    };

    $("#sms_btn").on("click", getCode);

    // 提交注册表单
    var signup = function () {
        //检查短信验证码
        var sms_code = $.trim($("#sms_code").val());
        //检查数据格式
        if (check("phone") && check("sms_code") && check("pwd") && check("pwd2")) {
            //提交数据
            var phone = $.trim($("#phone").val());
            var pwd = $.trim($("#pwd").val());
            $.post(registerUrl, {"phone": phone, "password": pwd, "checkCode": sms_code}, function (data) {
                if (data.status==0||data.status=="0") {
                    alert("注册成功，现在为您跳转到登录页面！");
                    window.location.href = 'http://'+window.location.host+"/consultant/signin.jsp";//跳转到后台管理页面
                } else {
                    var tips = $("<div class='fdtips errortip'>"+data.message+"</div>");
                    $(tips).appendTo($("body")).fadeOut(3000);
                }
            });
        }
    };

    $("#signup").on("click", signup);


    // 咨询师登录
    var signin = function () {

        //检查数据
        if (check("phone") && check("pwd")) {
            //提交数据
            var phone = $.trim($("#phone").val());
            var pwd = $.trim($("#pwd").val());
            $.post(loginURL, {"phone": phone, "password": pwd}, function (data) {
                if (data.status==0||data.status=="0") {
                    window.location.href = 'http://'+window.location.host+"/consultant/admin/info.jsp";//跳转到后台管理页面
                } else {
                    $("input").val("");
                    var tips = $("<div class='fdtips errortip'>"+data.message+"</div>");
                    $(tips).appendTo($("body")).fadeOut(3000);
                }
            });
        }
    };

    $("#signin").on("click", signin);


    //完善个人信息
    var moreinfo = function () {
        var gender = $("input[name=gender]:checked").val();
        if (!gender) {
            $(".gender_tips").text("请选择您的性别");
            return;
        }
        
        //检查数据
        if (check("nickname") && check("name") && check("age") && check("email") && check("alipay") && check("professional") && check("background") && check("price") && check("bookTime") && check("address") && check("introduce")&&check("speciality")&& check("signature")&&check("videoPrice")&&check("facePrice")) {

             
            var newstatus = $("#status").val();
            if(newstatus!="2"){
                $("#status").val("1");
            }             

            //格式化数据
            var _data = $("#infoform").serialize();  

            //提交数据
            $.post(updateInfoURL,_data,function(data){

                if(data.status=="0"||data.status==0){
                    if(newstatus=="2"||newstatus==2){
                        var tips = $("<div class='fdtips successtip'>修改成功！</div>");
                    }else{
                        $("input").attr("disabled","disabled");
                        $("textarea").attr("disabled","disabled");
                        $("select").attr("disabled","disabled");
                        $("#moreinfo").remove();
                        $(".uc-tips-ctn").text("您的信息正在由管理员审核中");
                        var tips = $("<div class='fdtips successtip'>提交成功，等待审核！</div>");
                    }
                }else{
                    var tips = $("<div class='fdtips errortip'>" + data.message + "</div>");
                }
                $(tips).appendTo($("body")).fadeOut(3000);
            });
            
        }
    };

    $("#moreinfo").on("click", moreinfo);

    //安全中心－修改密码
    var modPwd = function () {

        //检查数据格式
        if (check("oldpwd") && check("pwd") && check("pwd2")) {
            //提交数据
            var oldPassword = $("#oldpwd").val();
            var newPassword = $("#pwd").val();
            
            $.post(changePwdURL,{"oldPassword":oldPassword,"newPassword":newPassword},function(data){
                $("input").val("");
                if(data.status==0||data.status=="0"){
                    var tips = $("<div class='fdtips successtip'>密码修改成功！</div>");
                }else{
                    var tips = $("<div class='fdtips errortip'>"+data.message+"</div>");
                }
                $(tips).appendTo($("body")).fadeOut(3000);
            });
             
        }
    }

    $("#modPwd").on("click",modPwd);  

    //查询个人基本信息
    var infoQuery = function (){
        $.post(infoURL,function(data){
            if(data.status==0||data.status=="0"){

                var _data = data.data;
                var l = _data.length;
                if(_data.status=="1"||_data.status==1){
                    $("input").attr("disabled","disabled");
                    $("textarea").attr("disabled","disabled");
                    $("select").attr("disabled","disabled");
                    $("#moreinfo").remove();
                    $(".uc-tips-ctn").text("您的信息正在由管理员审核中");
                } else if(_data.status=="2"||_data.status==2){
                    $(".uc-tips-ctn").text("恭喜您，您的信息已经审核通过");
                } else if(_data.status=="3"||_data.status==3){
                    $(".uc-tips-ctn").text("抱歉，您的信息审核未通过，请联系客服");
                }
                $("#status").val(_data.status);
                for(var i in _data){
                    if(i=="gender"){
                        $("input[name=gender][value="+_data[i]+"]").attr("checked","checked");
                        var v = _data[i];
                        if(v==1){
                            $("#male i").show();
                            $("#female i").hide();
                        }else if(v==2){
                            $("#male i").hide();
                            $("#female i").show();
                        }
                    }else if(i=="rangeType"){
                        $("select[name=rangeType] option[value="+_data[i]+"]").attr("selected",true);
                    }else if(i=="status"){
                    	console.log(_data.status);
                        
                    }else{
                        if($("#"+i)&&$("#"+i).length>0){
                            $("#"+i).val(_data[i]);
                        }
                    }  
                }
            }else{
                var tips = $("<div class='fdtips errortip'>"+data.message+"</div>");
                $(tips).appendTo($("body")).fadeOut(3000);
            }
        });
    }

    if(currentURL.indexOf('admin/info')>0){
        infoQuery();
    }

    //查询个人基本信息
    var avatarQuery = function (){
        $.post(infoURL,function(data){
            if(data.status==0||data.status=="0"){
                var _data = data.data;
                var avatarurl = _data["avatar"];
                if(avatarurl!=""){
                    $(".avatar_preview").css("background-image","url("+avatarurl+")");
                    $("#preview").show();
                }
            }else{
                // var tips = $("<div class='fdtips errortip'>"+data.message+"</div>");
                // $(tips).appendTo($("body")).fadeOut(3000);
            }
        });
    }

    if(currentURL.indexOf('admin/avatar')>0){
        avatarQuery();
    }
    // 头像上传
    var uploadimg = function(){
        var path = $("#filepath").val();
        console.log(path);
        
        if(path!=""){
            // document.getElementById("avatarform").submit();
            $("#avatarform").ajaxSubmit({
                success:function(data){
                    console.log(data);
                    if(data.status==0){
                        alert("上传成功");
                        $("#upload_btn").hide();
                    }else{
                        alert(data.message);
                        window.location.reload();
                    }
                },
                error:function(data){
                    console.log(data);
                    alert("上传失败");
                    window.location.reload();
                }
            });

        }else{
            alert("头像不能为空");
            window.location.reload();
        }
    }

    $("#upload_btn").on("click",uploadimg);


})();
