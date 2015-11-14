<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--帖子列表开始 --%>
<c:forEach var="item" items="${pager.datas }">
	<div class="list-link">
		<a href="${pageContext.request.contextPath}/bbs/${item.id}/content"> ${item.title} <span class="float-right  "> ${empty item.createUser.nickName?item.createUser.loginName:item.createUser.nickName }&ensp;&ensp; <fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm" />&ensp;&ensp; <span class="badge bg-main">${item.readTimes }</span>
		</span>
		</a>
	</div>
</c:forEach>
<%--帖子列表结束 --%>
<%--分页面板 --%>
<%@include file="card/pagerLabel.jsp"%>