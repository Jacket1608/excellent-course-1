<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%--顶部div --%>
<div class="tocenter topLogin fadein-top">
	<div class="layout bg-black bg-main hidden-l">
		<div class="container-layout height-big">
			<c:if test="${!empty sessionScope.currentUser}">
				<span class="float-right"> <a
					href="javascript:showPersonInfo('${sessionScope.currentUser.id }')">${sessionScope.displayName }</a>
					<a href="${pageContext.request.contextPath }/admin">我的后台</a> <a
					href="${pageContext.request.contextPath }/loginout">注销</a> <span class="text-little text-gray">|</span>
					<a href="${pageContext.request.contextPath }/about/index#link">联系</a>
				</span> 欢迎 &ensp;<a href="javascript:showPersonInfo('${sessionScope.currentUser.id }')">${sessionScope.displayName }</a>&ensp; 来到精品课程中心
						</c:if>
			<c:if test="${empty sessionScope.currentUser}">
				<span class="float-right"> <a href="${pageContext.request.contextPath }/register">注册</a>
					<span class="text-little text-gray">|</span> <a
					href="${pageContext.request.contextPath }/login">登录</a> <span class="text-little text-gray">|</span>
					<a href="${pageContext.request.contextPath }/about/index#link">联系</a>
				</span> 欢迎你来到精品课程中心
						</c:if>
		</div>
	</div>
</div>
<div class="sepLine"></div>
<%--导航栏 --%>
<div class="top_nav tocenter fadein-top">
	<button class="button icon-navicon" data-target="#nav-tabs4"></button>
	<ul class="nav nav-menu nav-big nav-inline nav-tabs nav-right nav-navicon" id="nav-tabs4">
		<li class="active"><a href="${pageContext.request.contextPath }/index">首页</a></li>
		<li><a href="${pageContext.request.contextPath }/course/index">课堂中心</a></li>
		<li><a href="${pageContext.request.contextPath }/bbs/index">知识社区</a></li>
		<li><a href="${pageContext.request.contextPath }/attach/index">资源中心</a></li>
		<li><a href="${pageContext.request.contextPath }/info/index">信息中心</a></li>
		<li><a href="${pageContext.request.contextPath }/about/index">关于我们</a></li>
	</ul>
</div>
<div class="sepLine"></div>