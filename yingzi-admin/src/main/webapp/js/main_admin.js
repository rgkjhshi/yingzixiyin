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
        if(confirm("确定要删除该咨询师吗？")){
            $.post(url,{'id':id},function(data){
                var s = data.returnCode;
                if(data.returnCode==0||data.returnCode=="0"){
                    _this.parents("tr").remove();
                    alert(data.returnMessage);
                }else{
                    alert(data.returnMessage);
                }
            });
        }
    }

    $(".delConsultant").on("click",delConsultant);

    //咨询师审核
    var checkConsultant = function(type){
        if(type=="2"||type==2){
            _this = $(".accept");
        }else{
            _this = $(".refuse");
        }
        var id = _this.parent("div").attr("data-id");
        var url = "../consultant/ccheck.do";
        var obj = {
            "id":id,
            "professional":$("#professional").val(),
            "background":$("#background").val(),
            "bookTime":$("#bookTime").val(),
            "address":$("#address").val(),
            "introduce":$("#introduce").val(),
            "speciality":$("#speciality").val(),
            "signature":$("#signature").val(),
            "status":type 
        };
        $.post(url,obj,function(data){
            console.log(data);
            if(data.returnCode==0||data.returnCode=="0"){
                alert(data.returnMessage);
                window.location.reload();
            }else{
                alert(data.returnMessage);
            }
        });
    }

    $(".accept").on("click",function(){checkConsultant(2)});
    $(".refuse").on("click",function(){checkConsultant(3)});

    //咨询管理
    var recordManage = function(){
        var status = $(this).attr("data-status");
        var id = $(this).attr("data-id");
        var url = "../record/change_state.do";
        $.post(url,{'status':status,'id':id},function(data){
            console.log(data);
            if(data.returnCode == 0){
                alert("修改成功！");
                window.location.reload();
            }else{
                alert(data.returnMessage);
            }
        });
    };

    $("#startRecord,#endRecord").on("click",recordManage);

})();
