<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div style="text-align: center;">
	<ul class="pagination border-main">
		<li class="${pager.currentPage==1?'disabled':'' } "><c:if test="${pager.currentPage==1}">
				<a href="javascript:void(0)">«</a>
			</c:if> <c:if test="${pager.currentPage!=1}">
				<a href="javascript:showPage(${pager.currentPage-1},${pager.pagesize},'${souresType}')">«</a>
			</c:if></li>
		<c:forEach
			begin="${pager.currentPage-pager.currentPage%10==0?pager.currentPage-pager.currentPage%10+1:pager.currentPage-pager.currentPage%10}"
			step="1" end="${pager.currentPage-pager.currentPage%10+9}" var="index">
			<li class="${pager.currentPage==index?'active':'' } ${index>pager.totalPage?'disabled':'' }"><c:if
					test="${index>pager.totalPage}">
					<a href="javascript:void(0)">${index }</a>
				</c:if> <c:if test="${index<=pager.totalPage}">
					<a href="javascript:showPage(${index},${pager.pagesize},'${souresType}')">${index }</a>
				</c:if></li>
		</c:forEach>
		<li class="${pager.currentPage==pager.totalPage?'disabled':'' }"><c:if
				test="${pager.currentPage==pager.totalPage}">
				<a href="javascript:void(0)">»</a>
			</c:if> <c:if test="${pager.currentPage!=pager.totalPage}">
				<a href="javascript:showPage(${pager.currentPage+1},${pager.pagesize},'${souresType}')">»</a>
			</c:if></li>
	</ul>
</div>