<%@page import="io.shuqi.graduation.enumtype.EnumMimeType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>信息中心</title>
<%@include file="../templet/commons.jsp"%>
		<script type="text/javascript">
		$(function(){
			//导航栏变化
			$(".nav-menu>li").removeClass("active");
			$($(".nav-menu>li")[4]).addClass("active");
		});
		</script>
</head>
<body>
	<div id="container">
		<%--页头 --%>
		<%@include file="../templet/head.jsp"%>
		<%--面包屑 --%>
		<div class="tocenter">
			<ul class="bread bg-main">
			  <li><a href="${pageContext.request.contextPath }/index" class="icon-home"> 首页</a></li>
			  <li><a href="${pageContext.request.contextPath }/info/index">信息中心</a></li>
			  <li class="finalb"></li>
			</ul>
		</div>
		<%--面包屑 --%>
		<%--内容布局 --%>
		<div class="tocenter">
			<div class="line-small">
				<div class="x3">
				<%--热门附件 --%>
					<%@include file="card/attachPanel.jsp"%>
					<div class="sepLine"></div>
					<%--最新课程面板 --%>
					<%@include file="card/newClassDetailPanel.jsp"%>
					<div class="sepLine"></div>
					<%--最新社区讨论面板 --%>
					<%@include file="card/newBbsContent.jsp" %>
				</div>
				<div class="x9">
					<%@include file="../home/viewInfoCard.jsp"%>
				</div>	
			</div>
		</div>
		
		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>
		
	</div>
</body>
</html>
