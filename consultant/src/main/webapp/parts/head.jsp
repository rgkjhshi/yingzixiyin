<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String session_phone = session.getAttribute("session_phone").toString(); //从session里把a拿出来，并赋值给phone
%>
<%
    String head_path=request.getContextPath();
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
                <li class="logout" style="cursor:pointer;"><a style="cursor:pointer;">退出</a></li>
            <% } %>
            <li class="con_admin">咨询师管理后台</li>
        </ul>
    </div>
</div>
<script type="text/javascript">
    $(".logout").on("click",function(){
        $.get("<%=head_path %>/admin/logoutApi.htm",function(data){
            if(data.status==0){
            	alert('退出成功！');
            	window.location.href="/consultant/signin.jsp";
            }
        });
    });
</script>