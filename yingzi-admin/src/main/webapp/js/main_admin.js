/**
 *
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-07-26 15:00:49
 * @version $Id$
 */
(function () {

    // 格式验证
    var check = function (vid) {//传入id和默认的提示
        var re;
        var str = $("#" + vid).val();
        var tips = $(".tips");
        if(vid=="phone"){
            re = /^1\d{10}$/;
            if (!re.test(str)) {
                tips.text("请输入正确的手机号码");
                return false;
            } else {
                tips.text("");
                return true;
            }
        }else if(vid=="pwd"){
            re = /^[\d_a-zA-Z]{6,12}$/;
            if (!re.test(str)) {
                tips.text("请输入6-12位包含数字、字母或下划线的密码");
                return false;
            } else {
                tips.text("");
                return true;
            }
        }else{
            return true;
        }
        
    }


    // 咨询师登录
    var signin = function () {

        //检查数据
        if (check("phone") && check("pwd")) {
            //提交数据
            var phone = $.trim($("#phone").val());
            var pwd = $.trim($("#pwd").val());
            loginURL="admin/login.do";
            $.post(loginURL, {"phone": phone, "password": pwd}, function (data) {
                if (data.status==0||data.status=="0") {
                    window.location.href = "admin/secure.jsp";//跳转到后台管理页面
                } else {
                    alert(data.message);
                }
            });
        }
    };

    $("#signin").on("click", signin);

    //安全中心－修改密码
    var modPwd = function () {

        //检查数据格式
        if (check("oldpwd") && check("pwd") && check("pwd2")) {
            //提交数据
            var oldpwd = $("#oldpwd").val();
            var pwd = $("#pwd").val();
            url="changePasswd.do";
             $.post(url,{"oldpwd":oldpwd,"password":pwd},function(data){
	           /*  if(data.status==0||data.status=="0"){
	            	 alert(data.message);//输出错误原因
	             }else{
	            	 alert("密码修改成功！")
	             }
	             window.location.href="admin/secure.jsp";*/
            	 alert(data.returnMessage);
             });
             
        }
    }

    $("#modPwd").on("click",modPwd);    

})();
