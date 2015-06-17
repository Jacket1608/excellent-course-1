<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>主页</title>
<%@include file="../templet/commons.jsp"%>
</head>
<body>
	<div id="container">
		<%--页头 --%>
		<%@include file="../templet/head.jsp"%>
		<%--热门新闻轮播 --%>
		<%@include file="card/newsPicPlay.jsp"%>
		<div class="tocenter">
			<ul class="bread bg-main">
			  <li><a href="${pageContext.request.contextPath }/index" class="icon-home"> 首页</a></li>
			</ul>
		</div>
		<%--内容布局 --%>
		<div class="tocenter">
			<div class="line-big">
				<div class="x6">
					<%--热门附件 --%>
					<%@include file="card/attachPanel.jsp" %>
					<div class="sepLine"></div>
					<%--最新新闻面板 --%>
					<%@include file="card/newNewsPanel.jsp" %>
				</div>
				
				<div class="x6">
					<div class="tab">
						<div class="tab-head text-left">
							<span class="tab-more"><a href="#">更多</a></span>
							<ul class="tab-nav">
								<li class="active"><a href="#tab-start3">最新课程</a></li>
								<li><a href="#tab-css3">最新公告</a></li>
								<li><a href="#tab-units3">社区top10</a></li>
							</ul>
						</div>
						<div class="tab-body border-main" >
							<div class="tab-panel active" id="tab-start3">
								<!-- 最新课程列表 -->
								<%@include file="card/newClassDetailList.jsp" %>
							</div>
							
							<div class="tab-panel" id="tab-css3">
								<%--  最新公告 --%>
								<%@include file="card/newNotificationList.jsp" %>
							</div>
							<div class="tab-panel" id="tab-units3">
								<%-- 社区top10 --%>
								<%@include file="card/newBbsContentTopList.jsp" %>
							</div>
						</div>
					</div>
					<div class="sepLine"></div>
					<%--最新社区讨论面板 --%>
					<%@include file="card/newBbsContent.jsp" %>
				</div>
			</div>
		</div>
		<%--内容布局结束 --%>
		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>
		<%--页脚结束 --%>
		
	</div>
</body>
</html>
