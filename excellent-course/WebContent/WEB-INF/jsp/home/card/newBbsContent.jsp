<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--最新社区讨论面板 --%>
<div class="panel border-main">
	<div class="panel-head  border-main bg-main">
		<strong>最新社区讨论</strong><a href="${pageContext.request.contextPath }/bbs/index"
			style="float: right;">更多</a>
	</div>
	<ul class="list-group">
		<c:forEach items="${bbsContents }" var="bbcitem">
			<li class="fadein-right"><img alt="" width="16px" height="16px"
				src="${pageContext.request.contextPath }/commons/img/mimeType/webpage.png"> <a
				href="${pageContext.request.contextPath }/bbs/${bbcitem.id }/content" style="word-break: break-all;">${bbcitem.title }</a> <span
				class="badge">${bbcitem.readTimes }</span> <span style="float: right;"> <fmt:formatDate
						value="${bbcitem.createTime }" pattern="yyyy-MM-dd" />&ensp; <a
					href="javascript:showPersonInfo(${bbcitem.createUser.id })">${bbcitem.createUser.loginName }</a>
			</span>
				<div style="clear: both;"></div></li>
		</c:forEach>
	</ul>
</div>