<%@page import="io.shuqi.graduation.enumtype.EnumMimeType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>课程中心</title>
<%@include file="../templet/commons.jsp"%>
</head>
<body>
	<div id="container">
		<%--页头 --%>
		<%@include file="../templet/head.jsp"%>
		<script type="text/javascript">
			//导航栏变化
			$(".nav-menu>li").removeClass("active");
			$($(".nav-menu>li")[1]).addClass("active");
		</script>

		<%--内容布局 --%>
		<div class="tocenter">
			<div class="line-small">
				<div class="x3 fadein-left">
				<div class="fixed " id="coursenav">
					<ul>
						<li class="active"><a href="#d1">科目介绍</a></li>
						<li><a href="#d2">教学大纲</a></li>
						<li><a href="#d3">相关课程</a></li>
						<li><a href="#d4">教师团队</a></li>
						<li><a href="#d5">教学方式</a></li>
						<li><a href="#d6">教学内容</a></li>
						<li><a href="#d7">教学环境</a></li>
					</ul>
				</div>
				</div>

				<div class="x9 fadein-right" id="courseBody">
					<div class="panel" id="d1">
					  <div class="panel-head bg-main">科目介绍</div>
					  <div class="panel-body" style="word-break: break-all;">
					  		${course.description }
					  </div>
					</div>
					<div class="sepLine"></div>
					<div class="panel" id="d2">
					  <div class="panel-head bg-main">教学大纲</div>
					  <div class="panel-body" style="word-break: break-all;">
					  		${course.teachOutline }
					  </div>
					</div>
						<div class="sepLine"></div>
					<div class="panel" id="d3">
					  <div class="panel-head bg-main">相关课程</div>
					  <div class="panel-body" style="word-break: break-all;">
					  		
					  		<c:if test="${!empty courseClasses }">
								<ul class="list-media list-underline">
									<c:forEach var="ccitem" items="${courseClasses }">
										<li>
											<div class="media media-x">
												<a class="float-left" href="${pageContext.request.contextPath }/course/view/${ccitem.id }/courseClass"> <img width="64px" height="64px;" src="${ccitem.courseClassImage }" class="radius" title="${ccitem.title }">
												</a>
												<div class="media-body">
													<div>
															课程名称：
															<a href="${pageContext.request.contextPath }/course/view/${ccitem.id }/courseClass" style="font-weight: bold;text-shadow: blue;">
																${ccitem.title }
															</a>&ensp;&ensp;&ensp;
															发布时间：<fmt:formatDate value="${ccitem.craeteTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&ensp;&ensp;&ensp;
															教师：${empty ccitem.createUser.nickName?ccitem.createUser.loginName:ccitem.createUser.nickName }
													</div>
													
													<p style="word-wrap: break-word;" class="descriptionContent">${ccitem.description }</p>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>
							</c:if>
							<c:if test="${empty courseClasses }">
								<h1>暂无课程信息！</h1>
							</c:if>
					  		
					  </div>
					</div>
						<div class="sepLine"></div>
					<div class="panel" id="d4">
					  <div class="panel-head bg-main">教师团队</div>
					  <div class="panel-body" style="word-break: break-all;">
					  		${course.teacherTeam }
					  </div>
					</div>
						<div class="sepLine"></div>
					<div class="panel" id="d5">
					  <div class="panel-head bg-main">教学方式</div>
					  <div class="panel-body" style="word-break: break-all;">
					  		${course.teachMethod }
					  </div>
					</div>
						<div class="sepLine"></div>
					<div class="panel" id="d6">
					  <div class="panel-head bg-main">教学内容</div>
					  <div class="panel-body" style="word-break: break-all;">
					  		${course.teachContent }
					  </div>
					</div>
						<div class="sepLine"></div>
					<div class="panel" id="d7">
					  <div class="panel-head bg-main">教学环境</div>
					  <div class="panel-body" style="word-break: break-all;">
					  		${course.teachEnvironment }
					  </div>
					</div>
				</div>

			</div>
		</div>

		<%--页脚 --%>
		<%@include file="../templet/footer.jsp"%>
		<%@include file="../templet/topdown.jsp"%>

	</div>
</body>
</html>
