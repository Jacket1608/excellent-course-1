<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--  最新公告 --%>
<ul class="list-group">
	<c:forEach var="ntfitem" items="${notifications }">
		<li class="fadein-right"><a
			href="${pageContext.request.contextPath }/info/view/${ntfitem.id}/notification">${ntfitem.title }</a>
			<span class="badge">${ntfitem.readTimes }</span> <span style="float: right;"> <fmt:formatDate
					value="${ntfitem.createTime }" pattern="yyyy-MM-dd" />&ensp; <a
				href="javascript:showPersonInfo(${ntfitem.createUser.id })" style="float: right;">${empty ntfitem.createUser.nickName?ntfitem.createUser.loginName:ntfitem.createUser.nickName }</a>
		</span>
			<div style="clear: both;"></div></li>
	</c:forEach>
</ul>