<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
#record_all{
    display: none;
}
</style>
<%
	String left_path=request.getContextPath();
%>
<div class="leftnav">
    <ul class="leftitem">
        <a href="<%=left_path%>/admin/secure.jsp"><li class="secure"><i>&nbsp;</i>个人中心</li></a>
        <a href="<%=left_path%>/consultant/query_consultants.do"><li class="detail"><i>&nbsp;</i>咨询师管理</li></a>
        <a href="<%=left_path%>/record/query_records.do?status=0"><li class="message"><i>&nbsp;</i>咨询管理</li></a>
        <ul class="sms" id='record_all'>
            <a href="<%=left_path%>/record/query_records.do?status=0" class="re_ing"><li>进行中的咨询</li></a>
            <a href="<%=left_path%>/record/query_records.do?status=1" class="re_end"><li>已结束的咨询</li></a>
        </ul>
    </ul>
</div>