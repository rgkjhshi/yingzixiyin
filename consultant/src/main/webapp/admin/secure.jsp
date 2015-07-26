<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>安全中心-英姿吸引</title>
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
            		<div class="subtitle">修改密码</div>
                    <div>
                        <div class="sign_title">原密码：</div>
                        <div class="sign_content">
                            <input type="password" data-validate="notnull" data-tip="原始密码" id="oldpwd" />
                            <span class="tips"></span>
                        </div>
                    </div>
                    <div>
                        <div class="sign_title">新密码：</div>
                        <div class="sign_content">
                            <input data-validate="pwd" data-tip="密码" id="pwd" type="password" />
                            <span class="tips">请输入6-12位包含数字、字母或下划线的密码</span>
                        </div>
                    </div>
                    <div>
                        <div class="sign_title">确认密码：</div>
                        <div class="sign_content">
                            <input type="password" data-validate="pwd2" id="pwd2" />
                            <span class="tips"></span>
                        </div>
                    </div>
                    <div class="btn" id="modPwd">保存</div>
            	</div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
    <script src="../js/main_consultant.js"></script>
    <script>
        $("#secure").addClass("cur");
    </script>
    </body>
</html>
