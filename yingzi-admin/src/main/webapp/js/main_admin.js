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
                tips.text("密码格式有误，请输入6－12位字母或数字");
                return false;
            } else {
                tips.text("");
                return true;
            }
        }else if(vid == "pwd2"){
            // re = /\S+/;
            // if (!re.test(str)) {
            //     tips.text("不可以为空");
            //     return false;
            // } else {
            //     tips.text("");
            //     return true;
            // }
                var pwd = $("#pwd").val();
                var pwd2 = $("#pwd2").val();
                if(pwd == pwd2){
                    return true;
                }else{
                    tips.text("两次输入的密码不一致");
                }
            }
        
    }

    var tips = function(type,text){
        var tips;
        if(type=="success"){
            tips = $("<div class='fdtips successtip'>"+text+"</div>");
        }else if(type == "error"){
            tips = $("<div class='fdtips errortip'>"+text+"</div>");
        }
        $(tips).appendTo($("body")).fadeOut(3000);
    }

    // 登录
    var signin = function () {

        //检查数据
        if (check("phone") && check("pwd")) {
            //提交数据
            var phone = $.trim($("#phone").val());
            var pwd = $.trim($("#pwd").val());
            var loginURL="admin/login.do";
            $.post(loginURL, {"phone": phone, "password": pwd}, function (data) {
                if (data.status==0||data.status=="0") {
                    window.location.href = "consultant/query_consultants.do";//跳转到后台管理页面
                } else {
                    $("input").val("");
                    $(".tips").text(data.message);
                }
            });
        }
    };

    $("#signin").on("click", signin);

    //安全中心－修改密码
    var modPwd = function () {
        var oldpwd = $.trim($("#oldpwd").val());
        var pwd = $.trim($("#pwd").val());

        //检查数据格式
        if(oldpwd == ""){
            $(".tips").text("请输入原始密码");
            return false;
        }
        if (check("pwd") && check("pwd2")) {
            //提交数据
            var url="changePasswd.do";
             $.post(url,{"oldpwd":oldpwd,"password":pwd},function(data){
                $("input").val("");
                if(data.returnCode==0||data.returnCode=="0"){
                    $("input").val("");
                    $(".tips").text("密码修改成功");
                }else{
                    $("input").val("");
                    $(".tips").text(data.returnMessage);
                }
             });
             
        }
    }

    $("#modPwd").on("click",modPwd);

    //删除咨询师
    var delConsultant = function(){
        var id = $(this).attr("data-id");
        var url = "../consultant/cdelete.do";
        var _this = $(this);
        $.get(url,{'id':id},function(data){
            var s = data.returnCode;
            if(data.returnCode==0||data.returnCode=="0"){
                _this.parents("tr").remove();
                alert(data.returnMessage);
            }else{
                alert(data.returnMessage);
            }
        });
    }

    $(".delConsultant").on("click",delConsultant);

    //咨询师审核
    var checkConsultant = function(type){
        var id = $(this).parent("div").attr("data-id");
        if(type==2){

        }else if(type==3){

        }
        var url = "../consultant/ccheck.do";
        // $.post(url,{'id':id},function(data){
        //     var s = data.returnCode;
        //     if(data.returnCode==0||data.returnCode=="0"){
        //         alert("success");
        //         $(this).parent("tr").remove();
        //         console.log($(this).parent("tr"));
        //         alert(data.returnMessage);
        //     }else{
        //         alert(data.returnMessage);
        //     }
        // });
    }

    $(".accept").on("click",checkConsultant(2));
    $(".refuse").on("click",checkConsultant(3));

})();
