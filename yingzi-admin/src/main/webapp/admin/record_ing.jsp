<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path=request.getContextPath();    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>消息管理-英姿吸引</title>
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
            		<div class="subtitle">进行中的咨询</div>
                    <table>
                        <tr>
                            <th>咨询师</th><th>用户</th><th>开始时间</th><th>是否付款</th><th>是否回复</th><th>咨询类型</th><th>操作</th>
                        </tr>
                        <c:forEach items="${recordsList}" var="cinfo">
                        	 <tr>
                            	<td>${cinfo.consultantName }</td>
                            	<td>${cinfo.userPhone }</td>
                            	<td><fmt:formatDate value="${ cinfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>${cinfo.isPaid }</td>
                                <td>${cinfo.isReplied }</td>
                                <td>${cinfo.consultType.desc }</td>
                            	<td class='func ${cinfo.isCompleted.value==0?"tooff":"toon" }'>
                            		<c:if test="${cinfo.isCompleted.value==0}">
                            			<a id='endRecord' data-id="${cinfo.id}" data-status="1">结束</a>
                            		</c:if>
                            		<c:if test="${cinfo.isCompleted.value==1}">
                            			<a id='startRecord' data-id="${cinfo.id}" data-status="0">重新开启</a>
                            		</c:if>
                            	</td>
                        	</tr>
                        </c:forEach>
                    </table>
                    ${page }
            	</div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
        <script>
            $(".message").addClass("cur");
            $("#record_all").show();
            $(".re_ing li").addClass("active");
        </script>
        <script src="../js/main_admin.js"></script>
    </body>
</html>