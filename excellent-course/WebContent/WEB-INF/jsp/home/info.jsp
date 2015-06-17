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
		<%--热门新闻轮播 --%>
		<%-- <%@include file="card/newsPicPlay.jsp"%> --%>
		<%--面包屑 --%>
		<div class="tocenter">
			<ul class="bread bg-main">
			  <li><a href="${pageContext.request.contextPath }/index" class="icon-home"> 首页</a></li>
			  <li><a href="${pageContext.request.contextPath }/info/index">信息中心</a></li>
			</ul>
		</div>
		<%--面包屑 --%>
		<%--内容布局 --%>
		<div class="tocenter">
			<div class="line-small">
				<div class="x3 fadein-left">
					<%--热门附件 --%>
					<%@include file="card/attachPanel.jsp" %>
					<div class="sepLine"></div>
					<%--最新课程面板 --%>
					<%@include file="card/newClassDetailPanel.jsp" %>
					<div class="sepLine"></div>
					<%--最新社区讨论面板 --%>
					<%@include file="card/newBbsContent.jsp" %>
				</div>

				<div class="x9 fadein-right">
					<div style="border-left: #00AA88 1em solid; padding-left: 5px;">
						<h1 style="display: inline-block;">最新新闻</h1>
						<a href="javascript:showInfoMore('newsmore')" style="float: right;">更多</a>
					</div>
					<div class="sepLine"></div>
					<ul class="list-group">
						<c:forEach var="newsitem" items="${newses }">
							<li>
								<div class="media media-x">
									<a class='float-left'
										href="${pageContext.request.contextPath }/info/view/${newsitem.id}/news"> <img
										src="${newsitem.images}" width="100px;" height="100ppx;" class="radius"
										alt="${newsitem.title}" title="${newsitem.title}">
									</a>
									<div class="media-body">
										<strong> 标题：<a
											href="${pageContext.request.contextPath }/info/view/${newsitem.id}/news">
												${newsitem.title}</a> <span style="float: right;"> 作者： <a
												href="javascript:showPersonInfo(${newsitem.createUser.id })">${empty newsitem.createUser.nickName?newsitem.createUser.loginName:newsitem.createUser.nickName }</a>
												&ensp;时间：<fmt:formatDate value="${newsitem.createTime }" pattern="yyyy-MM-dd" />
										</span>
										</strong> <span
											style="display: inline-block; height: 80px; text-indent: 2em; width: 350px; overflow: hidden;">
											${newsitem.content} </span>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>

					<div class="sepLine"></div>
					<div style="border-left: #00AA88 1em solid; padding-left: 5px;">
						<h1 style="display: inline-block;">最新公告</h1>
						<a href="javascript:showInfoMore('notificationmore')" style="float: right;">更多</a>
					</div>
					<div class="sepLine"></div>
					<ul class="list-group">
						<c:forEach var="newsitem" items="${notifications }">
							<li>
								<div class="media media-x">
									<a class='float-left'
										href="${pageContext.request.contextPath }/info/view/${newsitem.id}/nofication"> <img
										src="${newsitem.ntfImage}" width="100px;" height="100ppx;" class="radius"
										alt="${newsitem.title}" title="${newsitem.title}">
									</a>
									<div class="media-body">
										<strong> 标题：<a
											href="${pageContext.request.contextPath }/info/view/${newsitem.id}/notification">
												${newsitem.title}</a> <span style="float: right;"> 作者： <a
												href="javascript:showPersonInfo(${newsitem.createUser.id })">${empty newsitem.createUser.nickName?newsitem.createUser.loginName:newsitem.createUser.nickName }</a>
												&ensp;时间：<fmt:formatDate value="${newsitem.createTime }" pattern="yyyy-MM-dd" />
										</span>
										</strong> <span
											style="display: inline-block; height: 80px; text-indent: 2em; width: 350px; overflow: hidden;">
											${newsitem.content} </span>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>

				</div>

			</div>
		</div>

		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>

	</div>
</body>
</html>
