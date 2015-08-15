<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String left_path=request.getContextPath(); 
%>
<style>
#info_all,#message_all{
    display: none;
}
</style>
<div class="leftnav">
    <ul class="leftitem">
        <a href="info.jsp"><li id="info" class="detail"><i>&nbsp;</i>个人信息</li></a>
        <ul class="infomation" id="info_all">
            <a href="info.jsp"><li id="baseinfo">基本信息</li></a>
            <a href="avatar.jsp"><li id="avatarinfo">头像管理</li></a>
        </ul>
        <a href="secure.jsp"><li id="secure" class="secure"><i>&nbsp;</i>安全中心</li></a>
        <a href="<%=left_path %>/admin/queryRecordApi.htm?status=0"><li id="messagebtn" class="message"><i>&nbsp;</i>消息管理</li></a>
        <ul class="sms" id="message_all">
            <!-- <a href="message.jsp"><li id="sms_all">全部咨询</li></a> -->
            <a href="<%=left_path %>/admin/queryRecordApi.htm?status=0"><li id="sms_ing">进行中的咨询</li></a>
            <a href="<%=left_path %>/admin/queryRecordApi.htm?status=1"><li id="sms_end">已结束的咨询</li></a>
        </ul>
    </ul>
</div>
