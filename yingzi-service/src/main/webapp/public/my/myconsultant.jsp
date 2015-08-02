<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
	String path=request.getContextPath();    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="format-detection"content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>我的咨询师</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" href="<%=path%>/css/base.css">
<link href="<%=path%>/favicon.ico" rel="shortcut icon"></head>
<script type="text/javascript" src="<%=path%>/js/zepto.min.js"></script>
<%-- <script type="text/javascript" src="<%=path%>/js/touch.js"></script> --%>
<body>
    <div class="main layout">
    	<div class="sub">我咨询过的咨询师</div>
    	<ul class="consultants">
    	<c:forEach items="${ visited}" var="cinfo">
    		<li class="record">
    			<a href="<%=path%>/consultant/consultant_deatil.do?id=${cinfo.id}">
					<img src='
		            	<c:if test="${empty cinfo.avatar}">
				        	${path}/images/test.png
				        </c:if>
				        <c:if test="${(not empty cinfo.avatar) }">
				        	${cinfo.avatar}
				        </c:if>
	            	' />
            		<div class="info">
            			<div class="name">${cinfo.name }</div>
            			<div class="desc">
            				${cinfo.introduce }
            			</div>
            		</div>
            	</a>
    		</li>
    	</c:forEach>
    		<%-- <li class="record">
    			<a href="consultant_deatil.do?id=${cinfo.id}">
            		<img src="<%=path%>/images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
    		</li>
    		<li class="record">
    			<a href="../service/detail.html">
            		<img src="<%=path%>/images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
    		</li>
    		<li class="record">
    			<a href="../service/detail.html">
            		<img src="<%=path%>/images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
    		</li> --%>
    	</ul>

		<div class="sub">我收藏的咨询师</div>
    	<ul class="consultants">
    		<c:forEach items="${ collected }" var="cinfo">
    			<li class="record">
	    			<a href="<%=path%>/consultant/consultant_deatil.do?id=${cinfo.id}">
	            		<img src='${(not empty cinfo.avatar)?cinfo.avatar:pageContext.request.contextPath+"/images/test.png"}' />
	            		<div class="info">
	            			<div class="name">${cinfo.name }</div>
	            			<div class="desc">
	            				${cinfo.introduce }
	            			</div>
	            		</div>
	            	</a>
    			</li>
    		</c:forEach>
    		<%-- <li class="record">
    			<a href="../service/detail.html">
            		<img src="<%=path%>/images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
    		</li>
    		<li class="record">
    			<a href="../service/detail.html">
            		<img src="<%=path%>/images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
    		</li>
    		<li class="record">
    			<a href="../service/detail.html">
            		<img src="<%=path%>/images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
    		</li>
    		<li class="record">
    			<a href="../service/detail.html">
               		<img src="<%=path%>/images/test.png" />
            		<div class="info">
            			<div class="name">张三</div>
            			<div class="desc">
            				这里是咨询师的介绍，比如他的年龄、经历什么的，总之可以写好多好多东西，记得overflow。
            			</div>
            		</div>
            	</a>
    		</li> --%>
    	</ul>    	
    </div>
<script type="text/javascript" src="<%=path%>/js/main_wechat.js"></script>
</body>
</html>