<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 最新课程列表 --%>
<ul class="list-group">
	<c:forEach var="cdtitem" items="${classDetails }">
		<li class="fadein-right"><a
			href="${pageContext.request.contextPath }/course/view/${cdtitem.id }/classDetail">${cdtitem.title }</a>
			<span style="float: right;"> <fmt:formatDate value="${cdtitem.createTime }"
					pattern="yyyy-MM-dd" />&ensp; <a href="javascript:showPersonInfo(${cdtitem.createUser.id })"
				style="float: right;">${empty cdtitem.createUser.nickName?cdtitem.createUser.loginName:cdtitem.createUser.nickName }</a>
		</span>
			<div style="clear: both;"></div></li>
	</c:forEach>
</ul>
