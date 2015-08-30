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
        <style>
            .sign_title{
                vertical-align: top;
            }
            .exg_div{
                line-height: 2em;
                font-size: 12px;
            }
            .exg_btn{
                color:blue;
                cursor: pointer;
            }
            .exg{
                display: none;
                color:#999;
            }
        </style>
    </head>
    <body>
        <%@ include file="../parts/head.jsp" %> 
        <div class="info">
            <div class="layout">
                <%@ include file="../parts/left.jsp" %> 
            	<div class="maincontent" style="padding-bottom:100px;">
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
                                <input type="text" disabled="disabled" data-validate="phone" data-tip="手机号码" name="phone" id="phone" />
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
                            <div class="sign_title">咨询范围：</div>
                            <div class="sign_content">
                                <select name="rangeType">
                                    <option value="1">解救单身状态</option>
                                    <option value="2">让恋爱更幸福</option>
                                    <option value="3">夫妻那点事儿</option>
                                </select>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">文字咨询：</div>
                            <div class="sign_content" style="font-size:0.85rem;">
                                <!-- readonly="readonly" disabled="disabled" -->
                                <input id="price" name="price" type="text" data-validate="number" data-tip="咨询单价" style="width:20px;"/>&nbsp;&nbsp;元/次
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">视频咨询：</div>
                            <div class="sign_content" style="font-size:0.85rem;">
                                <input id="videoPrice" name="videoPrice" type="text" data-validate="number" data-tip="视频咨询单价" style="width:40px;"/>&nbsp;&nbsp;元/次
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">面对面咨询：</div>
                            <div class="sign_content" style="font-size:0.85rem;">
                                <input id="facePrice" name="facePrice" type="text" data-validate="number" data-tip="面对面咨询单价" style="width:40px;"/>&nbsp;&nbsp;元/次
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">咨询特长：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="speciality" data-validate="notnull" data-tip="咨询特长" id="speciality" placeholder="单身情感问题，婚恋情感，失恋，离婚，家暴"></textarea>
                                <span class="tips"></span>
                                <div class="exg_div">
                                    <div class="exg_btn">查看示例</div>
                                    <div class="exg">单身情感问题，婚恋情感，失恋，离婚，家暴</div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">专业背景：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="professional" data-validate="notnull" data-tip="专业背景" id="professional" placeholder="情感咨询，恋爱咨询，婚姻咨询，婚姻与性咨询，艾瑞克森催眠疗法，人本疗法"></textarea>
                                <span class="tips"></span>
                                <div class="exg_div">
                                    <div class="exg_btn">查看示例</div>
                                    <div class="exg">情感咨询，恋爱咨询，婚姻咨询，婚姻与性咨询，艾瑞克森催眠疗法，人本疗法</div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">受训背景：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="background" data-validate="notnull" data-tip="受训背景" id="background" placeholder="中科院心理所临床心理学在职研究生，国家注册二级心理咨询师，国家注册婚姻家庭咨询师"></textarea>
                                <span class="tips"></span>
                                <div class="exg_div">
                                    <div class="exg_btn">查看示例</div>
                                    <div class="exg">中科院心理所临床心理学在职研究生，国家注册二级心理咨询师，国家注册婚姻家庭咨询师</div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">可预约时间：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="bookTime" data-validate="notnull" data-tip="可预约时间" id="bookTime" placeholder="周六、周日"></textarea>
                                <span class="tips"></span>
                                <div class="exg_div">
                                    <div class="exg_btn">查看示例</div>
                                    <div class="exg">周六、周日</div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">地址：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="address" data-validate="notnull" data-tip="地址" id="address" placeholder="北京市朝阳区朝外大街10号"></textarea>
                                <span class="tips"></span>
                                <div class="exg_div">
                                    <div class="exg_btn">查看示例</div>
                                    <div class="exg">北京市朝阳区朝外大街10号</div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">个人简介：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="introduce" data-validate="notnull" data-tip="个人简介" id="introduce" style="height:13em;" placeholder="中科院心理所临床心理学在职研究生，国家注册二级心理咨询师，国家注册婚姻家庭咨询师（不得少于150字）"></textarea>
                                <span class="tips"></span>
                                <div class="exg_div">
                                    <div class="exg_btn">查看示例</div>
                                    <div class="exg">中科院心理所临床心理学在职研究生，国家注册二级心理咨询师，国家注册婚姻家庭咨询师（不得少于150字）</div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">个性签名：</div>
                            <div class="sign_content">
                                <textarea class="form_border" name="signature" data-validate="notnull" data-tip="个性签名" id="signature"></textarea>
                                <span class="tips"></span>
                            </div>
                        </div>
                        <input class="form_border" name="status" type="hidden" id="status" />
                        

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
        //检查是否更改价格、地址信息
        $("#price,#videoPrice,#facePrice,#address").change(function(){
            if($("#status").val()=="2"){
                $("#status").val("1");
                alert("您修改了价格或地址信息，需要管理员重新审核");
            }else if($("#status").val()=="0"){
                $("#status").val("1");
            }
        });
        //示例显示与隐藏
        $(".exg_btn").on("click",function(){
            var txt = $(this).text();
            if (txt=="查看示例"){
                $(this).text("收起");
            }else{
                $(this).text("查看示例");
            }
            $(this).siblings(".exg").toggle();
        });
    </script>
    </body>
</html>
