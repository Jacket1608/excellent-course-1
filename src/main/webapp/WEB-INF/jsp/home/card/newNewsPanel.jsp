<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--最新新闻面板 --%>
<div class="panel border-main">
	<div class="panel-head  border-main bg-main">
		<strong>最新新闻</strong><a href="${pageContext.request.contextPath }/info/index"
			style="float: right;">更多</a>
	</div>
	<div class="panel-body">

		<c:forEach var="newsitem" items="${newses }">
			<div class="media media-x fadein-left">
				<a class='float-left' href="${pageContext.request.contextPath }/info/view/${newsitem.id}/news">
					<img src="${newsitem.images}" width="100px;" height="100ppx;" class="radius"
					alt="${newsitem.title}" title="${newsitem.title}">
				</a>
				<div class="media-body">
					<strong> <a href="${pageContext.request.contextPath }/info/view/${newsitem.id}/news">
							${newsitem.title}</a>
					</strong> <span
						style="display: inline-block; height: 80px; text-indent: 2em; width: 350px; overflow: hidden;">
						${newsitem.content} </span>
				</div>
			</div>
		</c:forEach>
	</div>
</div>