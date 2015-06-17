<%@page import="io.shuqi.graduation.enumtype.EnumMimeType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			  <li><a href="${pageContext.request.contextPath }/bbs/${bbsBlock.id }/block">${bbsBlock.name }</a></li>
			</ul>
		</div>
		<%--面包屑 --%>
		<%--内容布局 --%>
		<div class="tocenter">
			<div class="line-small">
				<div class="x3 fadein-left">
					<%--热门附件 --%>
					<%@include file="card/attachPanel.jsp"%>
					<div class="sepLine"></div>
					<%--最新课程面板 --%>
					<%@include file="card/newClassDetailPanel.jsp"%>
				</div>

				<div class="x9 fadein-right">
				  <blockquote class="border-main">
				  	  <strong>版块规定</strong>
					  <p>${bbsBlock.description }</p>
				  	  <strong>版块介绍</strong>
					  <p>${bbsBlock.description }</p>
				  </blockquote>
				  <div class="sepLine"></div>
				<div id="bbsContents">
					<%@include file="bbsBlockPager.jsp"%>
				</div>
					<%--发帖的富文本框 --%>
					<div class="sepLine"></div>
					<div style="height: 700px; padding: 0px 10px;">
						<form method="post" id="fatie" onsubmit="return fatie()">
						  <input type="hidden" name="blockId" value="${bbsBlock.id }"/>
						  <div class="form-group">
						    <div class="label"><label for="username">帖子标题</label></div>
						    <div class="field">
						      <input type="text" class="input" id="title" name="title" size="30"/>
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="label"><label for="username">上传附件</label></div>
						    <div class="field">
						     	<input type="button" id="insertfile" value="选择文件" />
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="label"><label for="username">帖子内容</label></div>
						    <div class="field">
								<textarea id="content" style="width: 100%; height: 500px;"></textarea>
						    </div>
						  </div>
						  <div class="form-button">
								<input class="button bg-main" type="submit" value="发   帖"> 
							</div>
						</form>
					</div>
					<%--发帖的富文本框结束 --%>
				</div>

			</div>
		</div>

		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>

	</div>
</body>
</html>
