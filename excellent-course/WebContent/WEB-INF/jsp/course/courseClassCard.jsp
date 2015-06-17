<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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