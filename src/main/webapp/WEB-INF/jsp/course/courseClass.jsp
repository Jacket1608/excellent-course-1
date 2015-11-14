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
		<%--面包屑 --%>
		<div class="tocenter">
			<ul class="bread bg-main">
			  <li><a href="${pageContext.request.contextPath }/index" class="icon-home"> 首页</a></li>
			  <li><a href="${pageContext.request.contextPath }/course/index">课程中心</a></li>
			  <li><a href="${pageContext.request.contextPath }/course/view/${courseClass.id }/courseClass">${courseClass.title}</a></li>
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

					<div class="panel border-main">
						<div class="panel-body bg-main">
							<strong>课程介绍</strong>
						</div>
						<div class="panel-body">
							<p>
								课程&lt;&lt;${courseClass.title }&gt;&gt;是由 <a href="javascript:showPersonInfo(${courseClass.createUser.id })">${empty courseClass.createUser.nickName?courseClass.createUser.loginName:courseClass.createUser.nickName }</a> 老师于
								<fmt:formatDate value="${courseClass.craeteTime }" pattern="yyyy-MM-dd HH:mm" />
								发布。
							</p>
							<div style="text-indent: 2em; word-break: break-all;">${courseClass.description }</div>
						</div>
					</div>
					<div class="sepLine"></div>
					<div class="panel border-main" id="classDetailDiiv">
						<div class="panel-body bg-main">
							<strong>课程的内容</strong>
						</div>
						<div class="panel-body">
							<ul class="list-group">
								<c:forEach var="cdtitem" items="${pager.datas }">
									<li class="fadein-right"><a href="${pageContext.request.contextPath }/course/view/${cdtitem.id }/classDetail">${cdtitem.title }</a> <span style="float: right;"> <fmt:formatDate value="${cdtitem.createTime }" pattern="yyyy-MM-dd" />&ensp; <a href="javascript:showPersonInfo(${cdtitem.createUser.id })" style="float: right;"> ${empty cdtitem.createUser.nickName?cdtitem.createUser.loginName:cdtitem.createUser.nickName }</a>
									</span></li>
								</c:forEach>
							</ul>
							<%--分页 --%>
							<div class="sepLine fadein-bottom"></div>
							<div style="text-align: center;">
								<ul class="pagination border-main">
									<li class="${pager.currentPage==1?'disabled':'' } "><c:if test="${pager.currentPage==1}">
											<a href="javascript:void(0)">«</a>
										</c:if> <c:if test="${pager.currentPage!=1}">
											<a href="javascript:showPage(${pager.currentPage-1},${pager.pagesize},'${souresType}')">«</a>
										</c:if></li>
									<c:forEach begin="${pager.currentPage-pager.currentPage%10==0?pager.currentPage-pager.currentPage%10+1:pager.currentPage-pager.currentPage%10}" step="1" end="${pager.currentPage-pager.currentPage%10+9}" var="index">
										<li class="${pager.currentPage==index?'active':'' } ${index>pager.totalPage?'disabled':'' }"><c:if test="${index>pager.totalPage}">
												<a href="javascript:void(0)">${index }</a>
											</c:if> <c:if test="${index<=pager.totalPage}">
												<a href="javascript:showPage(${index},${pager.pagesize},'${souresType}')">${index }</a>
											</c:if></li>
									</c:forEach>
									<li class="${pager.currentPage==pager.totalPage?'disabled':'' }"><c:if test="${pager.currentPage==pager.totalPage}">
											<a href="javascript:void(0)">»</a>
										</c:if> <c:if test="${pager.currentPage!=pager.totalPage}">
											<a href="javascript:showPage(${pager.currentPage+1},${pager.pagesize},'${souresType}')">»</a>
										</c:if></li>
								</ul>
							</div>
							<%--分页结束 --%>

						</div>
					</div>

				</div>

			</div>
		</div>

		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>

	</div>
</body>
</html>
