<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>关于我们</title>
<%@include file="../templet/commons.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/index/index.css">
<script type="text/javascript">
	$(function() {
		//导航栏变化
		$(".nav-menu>li").removeClass("active");
		$($(".nav-menu>li")[5]).addClass("active");
	});
</script>
</head>
<body>
	<div id="container">
		<%--页头 --%>
		<%@include file="../templet/head.jsp"%>
		<%--面包屑 --%>
		<div class="tocenter">
			<ul class="bread bg-main">
				<li><a href="${pageContext.request.contextPath }/index" class="icon-home"> 首页</a></li>
				<li><a href="${pageContext.request.contextPath }/about/index">关于</a></li>
			</ul>
		</div>
		<%--面包屑 --%>
		<div class="tocenter keypoint bg fadein-bottom  radius-big">
			<h1>精品课程中心!</h1>
			<p>精品课程一个高端知识的集散地</p>
			<p>
				<a href="${pageContext.request.contextPath }/index">
					<button class="button bg-main">开始智慧之旅</button>
				</a>
			</p>
		</div>
		<div class="sepLine"></div>
		<div class="tocenter keypoint bg fadein-bottom  radius-big" id="link">
			<h1>联系我们</h1>
			<p>
				电话：12333312121<br /> email:xxx@123.com<br /> 地址：中国.四川.自贡<br />
			</p>
		</div>

		<div class="tocenter fadein-bottom  radius-big" id="message">
			<form action="" class="form form-tips" id="newsComments" onsubmit="return newsComment()">
				<div class="form-group" style="padding: 20px;">
					<div class="label">
						<label>留言板：</label>
					</div>
					<div class="field">
						<input type="hidden" name="newsid" value="${pager.datas[0].id }" />
						<textarea class="input" id="newsCommentsContent" name="content" rows="7"></textarea>
					</div>
				</div>
				<div class="form-button" style="padding: 20px;">
					<input style="float: right;" class="button bg-main" type="submit" value="提    交">
				</div>
			</form>
		</div>
		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>

	</div>
</body>
</html>
