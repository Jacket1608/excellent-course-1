<%@page import="io.shuqi.graduation.enumtype.EnumMimeType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>知识社区</title>
		<%@include file="../templet/commons.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="${pageContext.request.contextPath}/commons/kindeditor/kindeditor-all.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath}/commons/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/bbs/bbs.js"></script>
</head>
<body>
	<div id="container">
		<%--页头 --%>
		<%@include file="../templet/head.jsp"%>
		<script type="text/javascript">
			//导航栏变化
			$(".nav-menu>li").removeClass("active");
			$($(".nav-menu>li")[2]).addClass("active");
		</script>
		<%--面包屑 --%>
		<div class="tocenter">
			<ul class="bread bg-main">
			  <li><a href="${pageContext.request.contextPath }/index" class="icon-home"> 首页</a></li>
			  <li><a href="${pageContext.request.contextPath }/bbs/index">知识社区</a></li>
			  <li><a href="${pageContext.request.contextPath }/bbs/${bcontent.bbsBlock.id }/block">${bcontent.bbsBlock.name }</a></li>
			  <li class="finalb"></li>
			</ul>
		</div>
		<%--面包屑 --%>
		<%--内容布局 --%>
		<div class="tocenter">
			 <blockquote class="border-main">
				  	  <strong>版块规定</strong>
					  <p>${bcontent.bbsBlock.description }</p>
				  	 <%--  <strong>版块介绍</strong>
					  <p>${bcontent.bbsBlock.description }</p> --%>
				  </blockquote>
				  <div class="sepLine"></div>
			<div id="bbsContent">
				<%@include file="bbsContentCommentsPager.jsp"%>
			</div>
				<%--发帖的富文本框 --%>
					<div class="sepLine fadein-bottom"></div>
					<div style=" padding: 0px 10px;">
						<form method="post" id="fatie" onsubmit="return feedback()">
						 <input name="bcontentId" type="hidden" value="${bcontent.id }" />
						  <div class="form-group">
						    <div class="label"><label for="username">回复内容</label></div>
						    <div class="field">
								<textarea id="contentComments" style="width: 100%; height: 200px;"></textarea>
						    </div>
						  </div>
						  <div class="form-button">
								<input class="button bg-main" type="submit" value="评   论"> 
							</div>
						</form>
					</div>
					<%--发帖的富文本框结束 --%>
					
		</div>

		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>

	</div>
</body>
</html>
