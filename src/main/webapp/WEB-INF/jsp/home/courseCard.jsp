<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach var="item" items="${pager.datas }">

	<div class="panel fadein-right">
		<div class="panel-head bg-main">
			<strong><a href="${pageContext.request.contextPath }/course/view/${item.course.id }/course">${item.course.name }</a></strong>
		</div>
		<div class="panel-body" style="text-indent: 2em;">
			<p class="bg-main bg-inverse">${item.course.name }的相关课程
				<span class="badge bg-white">${fn:length(item.courseClasses) }</span>
			</p>
			<c:if test="${!empty item.courseClasses }">
				<ul class="list-media list-underline">
					<c:forEach var="ccitem" items="${item.courseClasses }">
						<li>
							<div class="media media-x">
								<a class="float-left" href="${pageContext.request.contextPath }/course/view/${ccitem.id }/courseClass"> <img width="64px" height="64px;" src="${ccitem.courseClassImage }" class="radius" title="${ccitem.title }">
								</a>
								<div class="media-body">
									<div>
											课程名称：
											<a href="${pageContext.request.contextPath }/course/view/${ccitem.id }/courseClass" style="font-weight: bold;text-shadow: blue;">
												${ccitem.title }
											</a>&ensp;&ensp;&ensp;
											发布时间：<fmt:formatDate value="${ccitem.craeteTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&ensp;&ensp;&ensp;
											教师：${empty ccitem.createUser.nickName?ccitem.createUser.loginName:ccitem.createUser.nickName }
									</div>
									
									<p style="word-wrap: break-word;" class="descriptionContent">${ccitem.description }</p>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty item.courseClasses }">
				<h1>暂无课程信息！</h1>
			</c:if>
			<p class="bg-main bg-inverse">${item.course.name }简介</p>
		<p class="descriptionContent">${item.course.description }
		</p>
		</div>
	</div>
	<div class="sepLine"></div>
</c:forEach>

<%--分页面板 --%>
	<div class="sepLine"></div>
<%@include file="card/pagerLabel.jsp"%>
