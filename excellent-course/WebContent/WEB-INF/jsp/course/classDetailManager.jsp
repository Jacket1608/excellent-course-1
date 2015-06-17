<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<table class="table">
	<tr>
		<th>课程标题</th>
		<th>所属课程</th>
		<th>发布时间</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pager.datas }" var="item">
		<tr>
			<td>${item.title }</td>
			<td>${item.courseClass.title }</td>
			<td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd" /></td>
			<td>
				<div style="width: 100px;">
					<a href="javascript:editClasssDetail('${item.id }')" class="button button-small bg-sub">编辑</a>
					<a href="${pageContext.request.contextPath }/course/view/${item.id }/classDetail"
						target="_blank" class="button button-small bg-sub">查看</a>
				</div>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="4" style="text-align: center;">
			<ul class="pagination border-main">
				<li class="${pager.currentPage==1?'disabled':'' } "><c:if test="${pager.currentPage==1}">
						<a href="javascript:void(0)">«</a>
					</c:if> <c:if test="${pager.currentPage!=1}">
						<a href="javascript:showPage(${pager.currentPage-1},${pager.pagesize},'${souresType}')">«</a>
					</c:if></li>
				<c:forEach
					begin="${pager.currentPage-pager.currentPage%10==0?pager.currentPage-pager.currentPage%10+1:pager.currentPage-pager.currentPage%10}"
					step="1" end="${pager.currentPage-pager.currentPage%10+9}" var="index">
					<li class="${pager.currentPage==index?'active':'' } ${index>pager.totalPage?'disabled':'' }">
						<c:if test="${index>pager.totalPage}">
							<a href="javascript:void(0)">${index }</a>
						</c:if> <c:if test="${index<=pager.totalPage}">
							<a href="javascript:showPage(${index},${pager.pagesize},'${souresType}')">${index }</a>
						</c:if>
					</li>
				</c:forEach>
				<li class="${pager.currentPage==pager.totalPage?'disabled':'' }"><c:if
						test="${pager.currentPage==pager.totalPage}">
						<a href="javascript:void(0)">»</a>
					</c:if> <c:if test="${pager.currentPage!=pager.totalPage}">
						<a href="javascript:showPage(${pager.currentPage+1},${pager.pagesize},'${souresType}')">»</a>
					</c:if></li>
			</ul>
		</td>
	</tr>
</table>