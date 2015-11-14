<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div style="border-left:#00AA88 1em solid;padding-left: 5px;"><h1 style="display: inline-block;">最新公告 </h1> </div>
					<div class="sepLine"></div>
					<ul class="list-group">
					<c:forEach var="newsitem" items="${pager.datas }">
							<li>
							<div class="media media-x">
							  <a class='float-left' href="${pageContext.request.contextPath }/info/view/${newsitem.id}/notification"> 
							  		<img src="${newsitem.ntfImage}" width="100px;" height="100ppx;" class="radius" alt="${newsitem.title}" title="${newsitem.title}">
							  </a>
							  <div class="media-body">
							    <strong> 
							    	标题：<a  href="${pageContext.request.contextPath }/info/view/${newsitem.id}/notification"> ${newsitem.title}</a>
							    	<span style="float: right;">
								   	作者： <a href="javascript:showPersonInfo(${newsitem.createUser.id })" >${empty newsitem.createUser.nickName?newsitem.createUser.loginName:newsitem.createUser.nickName }</a>
							    	&ensp;时间：<fmt:formatDate value="${newsitem.createTime }" pattern="yyyy-MM-dd"/>
							    	</span>
							    </strong>
							    <span style="display: inline-block;height: 80px;text-indent: 2em;width: 350px;overflow: hidden;">
							    		${newsitem.content}
							    </span>
							    </div>
							</div>
							</li>
					</c:forEach>
					</ul>
					<div class="sepLine"></div>
					<div style="text-align: center;">
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
				</div>