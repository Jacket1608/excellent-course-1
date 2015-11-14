<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--热门新闻轮播 --%>
<div class="tocenter fadein-top">
	<div class="banner">
		<div class="carousel">
			<c:forEach items="${hotNewses }" var="hotnews" varStatus="stu">
				<c:if test="${!empty hotnews.images }">
					<div class="item" style="height: 300px;">
						<a href="${pageContext.request.contextPath }/info/view/${hotnews.id}/news"> <img
							title="${hotnews.title}" src="${hotnews.images }">
						</a>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>