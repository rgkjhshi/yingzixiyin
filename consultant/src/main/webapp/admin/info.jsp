<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>个人信息-英姿吸引</title>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="../favicon.ico" rel="shortcut icon">
        <link href="../css/base.css" rel="stylesheet" type="text/css">
        <script src="../js/jquery-1.9.1.min.js"></script>
    </head>
    <body>
        <%@ include file="../parts/head.jsp" %> 
        <div class="info">
            <div class="layout">
                <%@ include file="../parts/left.jsp" %> 
            	<div class="maincontent">
            		<div class="subtitle">完善个人信息</div>
                    <div class="uc-tips">
                        <div class="bd">
                            <b>公告：</b>
                            <div class="uc-tips-ctn">
                                <a>请尽快完善个人信息，以便管理员审核。</a>
                            </div>
                        </div>
                    </div>
                    <form id="infoform">
                        <div>
                            <div class="sign_title">昵称：</div>
                            <div class="sign_content">
                                <input type="text" data-validate="notnull" data-tip="昵称" name="nickname" id="nickname" />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">真实姓名：</div>
                            <div class="sign_content">
                                <input type="text" data-validate="notnull" data-tip="真实姓名" name="name" id="name" />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">性别：</div>
                            <div class="sign_content">
                                <label class="radio_la">
                                    <div id="male"><i></i></div>
                                    <input class="gender" type="radio" name="gender" value="1" hidden="hidden" />男
                                </label>
                                <label class="radio_la">
                                    <div id="female"><i></i></div>
                                    <input class="gender" type="radio" name="gender" value="2" hidden="hidden" />女
                                </label>
                                <span class="tips gender_tips"></span>
                            </div>
                        </div>
                        <script>
                            $(".gender").on("click",function(){
                                var v = $("input[name='gender']:checked").val();
                                if(v==1){
                                    $("#male i").show();
                                    $("#female i").hide();
                                }else{
                                    $("#male i").hide();
                                    $("#female i").show();
                                }
                            });
                        </script>
                        <div>
                            <div class="sign_title">手机号码：</div>
                            <div class="sign_content">
                                <input type="text" readonly data-validate="phone" data-tip="手机号码" name="phone" id="phone" />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">年龄：</div>
                            <div class="sign_content">
                                <input type="text" data-validate="number" data-tip="年龄" name="age" id="age"  />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">E-mail：</div>
                            <div class="sign_content">
                                <input id="email" name="email" type="text" data-validate="email" data-tip="Email" />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">支付宝账号：</div>
                            <div class="sign_content">
                                <input type="text" name="alipay" data-validate="notnull" data-tip="支付宝账号" id="alipay" />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">专业背景：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="professional" data-validate="notnull" data-tip="专业背景" id="professional"></textarea>
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">受训背景：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="background" data-validate="notnull" data-tip="受训背景" id="background"></textarea>
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">咨询范围：</div>
                            <div class="sign_content">
                                <select name="rangeType">
                                    <option value="0">婚姻咨询</option>
                                    <option value="1">情感咨询</option>
                                    <option value="2">心理咨询</option>
                                </select>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">咨询单价：</div>
                            <div class="sign_content">
                                <input id="price" name="price" type="text" data-validate="number" data-tip="咨询单价" />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">可预约时间：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="bookTime" data-validate="notnull" data-tip="可预约时间" id="bookTime"></textarea>
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">地址：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="address" data-validate="notnull" data-tip="地址" id="address"></textarea>
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">个人简介：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="introduce" data-validate="notnull" data-tip="个人简介" id="introduce"></textarea>
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">个性签名：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="signature" data-validate="notnull" data-tip="个性签名" id="signature"></textarea>
                                <span class="tips"></span>
                            </div>
                        </div>
                    </form>
                    <div class="btn" id="moreinfo">保存</div>
            	</div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
    <script src="../js/main_consultant.js"></script>
    <script>
        $("#info").addClass("cur");
        $("#info_all").show();
        $("#baseinfo").addClass("active");
    </script>
    </body>
</html>
