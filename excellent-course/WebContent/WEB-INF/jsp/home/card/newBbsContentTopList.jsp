<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 社区top10 --%>
<ul class="list-group">
	<c:forEach items="${bbsContentTops }" var="bbcitem">
		<li class="fadein-right"><img alt="" width="16px" height="16px"
			src="${pageContext.request.contextPath }/commons/img/mimeType/webpage.png"> <a
			href="${pageContext.request.contextPath }/bbs/${bbcitem.id }/content" style="word-break: break-all;">${bbcitem.title }</a> <span
			class="badge">${bbcitem.readTimes }</span> <span style="float: right;"> <fmt:formatDate
					value="${bbcitem.createTime }" pattern="yyyy-MM-dd" />&ensp; <a
				href="javascript:showPersonInfo(${bbcitem.createUser.id })" style="float: right;">${bbcitem.createUser.loginName }</a>
		</span>
			<div style="clear: both;"></div></li>
	</c:forEach>
</ul>