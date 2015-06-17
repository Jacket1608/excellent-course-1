<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>知识社区</title>
<%@include file="../templet/commons.jsp"%>
<script type="text/javascript">
	$(function() {
		//导航栏变化
		$(".nav-menu>li").removeClass("active");
		$($(".nav-menu>li")[2]).addClass("active");
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
			  <li><a href="${pageContext.request.contextPath }/bbs/index">知识社区</a></li>
			</ul>
		</div>
		<%--面包屑 --%>
		<%--内容布局 --%>
		<div class="tocenter">
			<div class="line-small">
				<div class="x3 fadein-left">
					<%--热门附件 --%>
					<%@include file="card/attachPanel.jsp"%>
					<div class="sepLine"></div>
					<%--最新课程面板 --%>
					<%@include file="card/newClassDetailPanel.jsp"%>
				</div>

				<div class="x9 fadein-right">
					<%--父版块 --%>
					<c:forEach var="bbitem" items="${bbsBlocks }">
						<div class="panel border-main">
							<div class="panel-head bg-main">${bbitem.name }</div>
							<div class="panel-body">
								<%--子版块 --%>
								<c:if test="${!empty bbitem.children }">
									<c:forEach var="bbcitem" items="${bbitem.children }">
										<div class="media media-x">
											<a class="float-left" href="${pageContext.request.contextPath }/bbs/${bbcitem.id}/block">
												<img src="${pageContext.request.contextPath }/commons/img/bbs.png" width="64px"
												height="64px" class="radius" title="${bbcitem.name }">
											</a>
											<div class="media-body" style="">
												<strong><a class="float-left"
													href="${pageContext.request.contextPath }/bbs/${bbcitem.id}/block">${bbcitem.name }</a></strong><br>
												<div style="height: 40px; text-indent: 2em; overflow: hidden;">
													${bbcitem.description }</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
								<%--子版块结束 --%>
							</div>
						</div>
						<div class="sepLine"></div>
					</c:forEach>
					<%--父版块结束 --%>
				</div>

			</div>
		</div>

		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>

	</div>
</body>
</html>
