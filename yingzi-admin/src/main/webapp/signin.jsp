<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path=request.getContextPath();    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>管理员登录-英姿吸引</title>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="favicon.ico" rel="shortcut icon">
        <link href="css/base.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-1.9.1.min.js"></script>
        <style>
            .copyright{
                position:absolute;
                left: 0;
                bottom:0;
            }
            fieldset{
                min-height: 280px;
            }
        </style>
    </head>
    <body>
        <div class="header" role="navigation">
            <div class="layout">
                <img height="20" alt="Brand" src="images/logo.png">
            </div>
        </div>
        <div class="wrap">
            <div class="layout">
                <div class="signupcontent">
                    <div class="regist_tab_admin">
                        <ul>
                            <li class="cur"><span>立即登录</span></li>
                        </ul>
                        <p class="cur_all"></p>
                    </div>
                    <fieldset>
                        <div>
                            <div class="sign_title">手机号码：</div>
                            <div class="sign_content">
                                <input data-validate="tel" data-tip="手机号码" id="phone" type="text" />
                            </div>
                        </div>
                        <div>
                            <div class="sign_title">密码：</div>
                            <div class="sign_content">
                                <input data-validate="notnull" data-tip="密码" id="pwd" type="password" />
                            </div>
                        </div>
                        <div class="btn" id="signin">登录</div>
                        <span class="tips"></span>
                    </fieldset>
                </div>
                <div class="rightbar">
                </div>
            </div>
        </div>
        <%@ include file="parts/foot.jsp" %> 
    <script src="js/main_admin.js"></script>
    </body>
</html>