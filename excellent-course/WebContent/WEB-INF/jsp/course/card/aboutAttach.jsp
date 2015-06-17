<%@page import="io.shuqi.graduation.enumtype.EnumMimeType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="panel border-main">
	<div class="panel-head  border-main bg-main">
		<strong>课程相关资料</strong><a href="${pageContext.request.contextPath }/attach/index"
			style="float: right;">更多</a>
	</div>
	<ul class="list-group">
		<c:forEach items="${attachs }" var="attachitem">
			<li class="fadein-left"><img title="<c:out value="${attachitem.fileName }"/>"
				src="${pageContext.request.contextPath }/${attachitem.fileImages }"> <a
				style="word-break: break-all;"
				href="${pageContext.request.contextPath }/attach/download/${attachitem.id}" target="_blank"><c:out
						value="${attachitem.fileName }" /></a> <span style="float: right;"> <a
					href="javascript:showPersonInfo(${attachitem.createUser.id })">${empty attachitem.createUser.nickName?attachitem.createUser.loginName:attachitem.createUser.nickName }</a>
			</span>
				<div style="clear: both;"></div></li>
		</c:forEach>
	</ul>
</div>