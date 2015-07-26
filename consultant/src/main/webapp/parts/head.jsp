<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String session_phone = session.getAttribute("session_phone").toString(); //从session里把a拿出来，并赋值给phone
%>
<div class="header" role="navigation">
    <div class="layout">
        <img height="20" alt="Brand" src="../images/logo.png">
        <ul class="signin">
            <% if(session_phone==""){ %>
                <li><a href="../signin.jsp">登录</a></li>
                <li><a href="../signup.jsp">注册</a></li>
            <% }else{ %>
                <li><a><%=session_phone %></a></li>
                <li><a>退出</a></li>
            <% } %>
        </ul>
    </div>
</div>