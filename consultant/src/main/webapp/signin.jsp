<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>咨询师登录-英姿吸引</title>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="favicon.ico" rel="shortcut icon">
        <link href="css/base.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-1.9.1.min.js"></script>
        <style>
            body{background: #f8f8f8;}
            .copyright{
                position:absolute;
                bottom:0;
                left:0;
            }
        </style>
    </head>
    <body>
        <div class="header" role="navigation">
            <div class="layout">
                <img height="20" alt="Brand" src="images/logo.png">
                <ul class="signin">
                    <li>
                        <a>登录</a>
                    </li>
                    <li>
                        <a href="signup.jsp">注册</a>
                    </li>
                    <li class='con_admin'>咨询师管理后台</li>
                </ul>
            </div>
        </div>
        <div class="wrap">
            <div class="layout">
                <div class="signupcontent" style="margin:0 auto;float:none;">
                    <div class="regist_tab">
                        <ul>
                            <a href="signup.jsp"><li><span>手机注册</span></li></a>
                            <li class="cur"><span>立即登录</span></li>
                        </ul>
                        <p class="cur_in"></p>
                    </div>
                    <fieldset>
                        <div>
                            <div class="sign_title">手机号码：<span class="redstar">*</span></div>
                            <div class="sign_content">
                                <input data-validate="tel" data-tip="手机号码" id="phone" type="text" />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">密码：<span class="redstar">*</span></div>
                            <div class="sign_content">
                                <input data-validate="notnull" data-tip="密码" id="pwd" type="password" />
                                <span class="tips"></span>
                            </div>
                        </div>
                        <div class="btn" id="signin">登录</div>
                    </fieldset>
                </div>
                <!--<div class="rightbar">
                    还没有英姿账号？<br/>点击这里<a class="redlink" href="signup.jsp">快速注册</a>吧！
                </div>-->
            </div>
        </div>
        <%@ include file="./parts/foot.jsp" %>
    <script src="js/main_consultant.js"></script>
    </body>
</html>