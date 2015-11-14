<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
	<table class="table">
		<tr>
			<th>所属帖子</th>
			<th>评论内容</th>
			<th>发布时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pager.datas }" var="item">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/bbs/${item.bbsContent.id }/content" target="_blank">${item.bbsContent.title }</a>
			</td>
			<td>
				<div style="width: 400px;height: 40px;overflow: hidden;">
					<c:out value="${item.content }"></c:out>
				</div>
			</td>
			<td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd"/></td>
			<td>
				<div>
				<c:if test="${item.access }">
					<a href="javascript:bbsAjax(${item.id },'tieComments','hidden')" class="button button-small bg-sub">隐藏</a>
					<a href="javascript:bbsAjax(${item.id },'tieComments','edit')" class="button button-small bg-sub">编辑</a>
					<a href="${pageContext.request.contextPath }/bbs/${item.bbsContent.id }/content" target="_blank" class="button button-small bg-sub">查看原帖</a>
				</c:if>
				<c:if test="${!item.access }">
					<a href="javascript:bbsAjax(${item.id },'tieComments','show')" class="button button-small bg-sub">显示</a>
				</c:if>
				</div>
			</td>
		</tr>
		</c:forEach> 
		<tr>
			<td colspan="5" style="text-align: center;">
				<ul class="pagination border-main">
				  <li class="${pager.currentPage==1?'disabled':'' } ">
				  	<c:if test="${pager.currentPage==1}">
				  		<a href="javascript:void(0)">«</a>
				  	</c:if>
				  	<c:if test="${pager.currentPage!=1}">
				  		<a href="javascript:showPage(${pager.currentPage-1},${pager.pagesize},'${souresType}')">«</a>
				  	</c:if>
				  </li>
				  <c:forEach begin="${pager.currentPage-pager.currentPage%10==0?pager.currentPage-pager.currentPage%10+1:pager.currentPage-pager.currentPage%10}" step="1" end="${pager.currentPage-pager.currentPage%10+9}" var="index">
				  	<li class="${pager.currentPage==index?'active':'' } ${index>pager.totalPage?'disabled':'' }">
				  		<c:if test="${index>pager.totalPage}">
				  			<a href="javascript:void(0)">${index }</a>
				  		</c:if>
				  		<c:if test="${index<=pager.totalPage}">
				  			<a href="javascript:showPage(${index},${pager.pagesize},'${souresType}')">${index }</a>
				  		</c:if>
				  	</li>
				  </c:forEach>
				  <li class="${pager.currentPage==pager.totalPage?'disabled':'' }">
				  	<c:if test="${pager.currentPage==pager.totalPage}">
				  		<a href="javascript:void(0)">»</a>
				  	</c:if>
				  	<c:if test="${pager.currentPage!=pager.totalPage}">
				  		<a href="javascript:showPage(${pager.currentPage+1},${pager.pagesize},'${souresType}')">»</a>
				  	</c:if>
				  	</li>
				</ul>
			</td>
		</tr>
	</table>