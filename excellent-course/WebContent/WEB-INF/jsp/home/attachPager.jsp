<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- pager -->
<c:if test="${!empty pager.datas }">
	<ul class="list-group fadein-right">
		<c:forEach items="${pager.datas }" var="attachitem">
			<li><img title="<c:out value="${attachitem.fileName }"/>"
				src="${pageContext.request.contextPath }/${attachitem.fileImages }"> <a
				href="${pageContext.request.contextPath }/attach/download/${attachitem.id}" target="_blank"><c:out
						value="${attachitem.fileName }" /></a> <span style="float: right;"> <fmt:formatDate
						value="${attachitem.createTime }" pattern="yyyy-MM-dd HH:mm" /> <a
					href="javascript:showPersonInfo(${attachitem.createUser.id })">${empty attachitem.createUser.nickName?attachitem.createUser.loginName:attachitem.createUser.nickName }</a>
			</span></li>
		</c:forEach>
	</ul>
</c:if>

<div class="sepLine"></div>
<%--分页面板 --%>
<%@include file="card/pagerLabel.jsp"%>
