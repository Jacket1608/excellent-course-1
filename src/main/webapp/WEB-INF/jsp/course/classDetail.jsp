<%@page import="io.shuqi.graduation.enumtype.EnumMimeType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>课程中心</title>
<%@include file="../templet/commons.jsp"%>
</head>
<body>
	<div id="container">
		<%--页头 --%>
		<%@include file="../templet/head.jsp"%>
		<script type="text/javascript">
			//导航栏变化
			$(".nav-menu>li").removeClass("active");
			$($(".nav-menu>li")[1]).addClass("active");
		</script>
		<input type="hidden" name="courseClassId" value="${courseClass.id }" />
		<%-- 此处存在一个课程的大图 --%>
		<div class="tocenter fadein-top" style="height: 300px; overflow: hidden;">
			<img title="${courseClass.title}" width="1024px" src="${courseClass.courseClassImage }">
		</div>
		<input type="hidden" value="${courseClass.id}" name="courseClassId" />
		<%--面包屑 --%>
		<div class="tocenter">
			<ul class="bread bg-main">
			  <li><a href="${pageContext.request.contextPath }/index" class="icon-home"> 首页</a></li>
			  <li><a href="${pageContext.request.contextPath }/course/index">课程中心</a></li>
			  <li><a href="${pageContext.request.contextPath }/course/view/${courseClass.id }/courseClass">${courseClass.title}</a></li>
			  <li class="finalb"></li>
			</ul>
		</div>
		<%--面包屑 --%>
		<%--内容布局 --%>
		<div class="tocenter">
			<div class="line-big">
				<div class="x3">
					<%--课程相关资料 --%>
					<%@include file="card/aboutAttach.jsp" %>
					<div class="sepLine"></div>
					<%--最新社区讨论面板 --%>
					<%@include file="../home/card/newBbsContent.jsp" %>
				</div>
				<div class="x9 fadein-bottom">
					<%@include file="classDetailCrad.jsp"%>
				</div>
			</div>
		</div>

		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>

	</div>
</body>
</html>
