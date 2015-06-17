<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="renderer" content="webkit">
	<title>网站后台管理</title>
	<%--拼图的样式 --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/pintucomps/pintuer.css">
	<%--artDialog --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/artDialog/ui-dialog.css">
	<%--拼图的样式 --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/admin.css">
	<%--jquery --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
	<%--拼图的js --%>
	<script src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
	<%--拼图的IE8兼容 --%>
	<script src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>
	<script src="${pageContext.request.contextPath}/commons/artDialog/dialog-min.js"></script>
	<%--admin.js --%>
	<script src="${pageContext.request.contextPath}/static/admin/admin.js"></script>
</head>

<body>
	<div class="lefter">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/admin"><img src="${pageContext.request.contextPath}/commons/img/logomini.png" alt="后台管理系统" /></a>
		</div>
	</div>
	<div class="righter nav-navicon" id="admin-nav">
		<div class="mainer">
			<div class="admin-navbar">
				<span class="float-right">
					<a class="button button-little bg-main" href="${pageContext.request.contextPath}/index" target="_blank">前台首页</a>
					<a class="button button-little bg-yellow" href="${pageContext.request.contextPath}/loginout">注销登录</a>
				</span>
				<%--导航栏 --%>
				<ul class="nav nav-inline admin-nav">
					<li class="active"><a href="javascript:show('baseInfoCount')" class="icon-home">开始</a>
						<ul>
							<li><a href="javascript:show('baseInfoCount')">基本信息统计</a></li>
							<li><a href="javascript:show('mytie')">我的帖子</a></li>
							<li><a href="javascript:show('modifyPassword')">修改密码</a></li>
							<li><a href="javascript:show('myNewsComments')">我的新闻评论</a></li>
							<li><a href="javascript:show('mytieFeedback')">我的论坛回复</a></li>
							<li><a href="javascript:show('personalInfo')">个人信息修改</a></li>
						</ul>
					</li>
					<c:if test="${currentUser.confirm }">
					<li class=""><a href="javascript:show('notifyManager')" class="icon-home">内容管理</a>
						<ul>
							<li><a href="javascript:show('notifyManager')">公告管理</a></li>
							<li><a href="javascript:show('courseClassManager')">课程管理</a></li>
							<li><a href="javascript:show('classDetailManager')">课程内容管理</a></li>
							<li><a href="javascript:show('publishCourseClass')">发布课程</a></li>
							<li><a href="javascript:show('publishNotification')">发布公告</a></li>
							<li><a href="javascript:show('publishCourseClassDetail')">发布课程内容</a></li>
						</ul>
					</li>
					</c:if>
				</ul>
			</div>
			
			
			
			<div class="admin-bread">
				<%-- 欢迎--%>
				<span>您好${currentUserName }欢迎您的登录</span>
				<%-- 面包屑--%>
				<ul class="bread">
					<li><a href="${pageContext.request.contextPath}/admin" class="icon-home"> 开始</a></li>
					<li>后台首页</li>
				</ul>
			</div>
		</div>
	</div>
	<%--中间内容部分 --%>
	<div class="admin">
		<%@include file="teacher/baseInfoCount.jsp" %>
	</div>


</body>
</html>
