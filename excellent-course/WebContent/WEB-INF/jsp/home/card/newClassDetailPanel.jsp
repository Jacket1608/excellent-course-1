<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="panel border-main">
	<div class="panel-head  border-main bg-main">
		<strong>最新课程</strong><a href="${pageContext.request.contextPath }/course/index"
			style="float: right;">更多</a>
	</div>
	<div class="panel-body">
		<!-- 最新课程列表 -->
		<%@include file="newClassDetailList.jsp"%>
	</div>
</div>