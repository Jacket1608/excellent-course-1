<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%--jquery --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
<%--拼图的js --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
<%--拼图的IE8兼容 --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>
<style>
<!--
.tab .tab-nav li {
	width: 90px;
}
-->
</style>
<div class="tab">
	<div class="tab-head">
		<ul class="tab-nav">
			<li class="active"><a href="#tab-Confirm">待认证</a></li>
			<li><a href="#tab-access">已认证</a></li>
			<li><a href="#tab-noaccess">已停用</a></li>
		</ul>
	</div>
	<div class="tab-body">
		<div class="tab-panel active" id="tab-Confirm">
			<table class="table table-hover table-striped">
				<tr>
					<th>教师姓名</th>
					<th>教师登录名</th>
					<th>教师邮箱</th>
					<th>教师教师介绍</th>
					<th>教师操作</th>
				</tr>
				<c:forEach var="item" items="${  confirmTeacherItems}">
					<tr>
						<td>${ item.realName}</td>
						<td>${item.loginName}</td>
						<td>${item.email}</td>
						<td>${item.description}&ensp;</td>
						<td>
							<div style="width: 100px;">
								<a href="javascript:confirmTeacher('${item.id}','${ item.realName}','${item.loginName}')"
									class="button button-small bg-sub">通过</a> <a
									href="javascript:refuseTeacher('${item.id}','${ item.realName}','${item.loginName}')"
									class="button button-small bg-sub">拒绝</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="tab-panel" id="tab-access">
			<table class="table table-hover table-striped">
				<tr>
					<th>教师姓名</th>
					<th>教师登录名</th>
					<th>教师邮箱</th>
					<th>教师教师介绍</th>
					<th>教师操作</th>
				</tr>
				<c:forEach var="item" items="${ confirmedTeacherItems}">
					<tr>
						<td>${ item.realName}</td>
						<td>${item.loginName}</td>
						<td>${item.email}</td>
						<td>${item.description}&ensp;</td>
						<td>
							<div style="width: 130px;">
								<a href="javascript:stopUser('${item.id}','${ item.realName}','${item.loginName}')"
									class="button button-small bg-sub">停用</a> <a
									href="javascript:resetPassword('${item.id}','${ item.realName}','${item.loginName}')"
									class="button button-small bg-sub">重置密码</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="tab-panel" id="tab-noaccess">
			<table class="table table-hover table-striped">
				<tr>
					<th>教师姓名</th>
					<th>教师登录名</th>
					<th>教师邮箱</th>
					<th>教师教师介绍</th>
					<th>教师操作</th>
				</tr>
				<c:forEach var="item" items="${ noAccessTeacherItems}">
					<tr>
						<td>${ item.realName}</td>
						<td>${item.loginName}</td>
						<td>${item.email}</td>
						<td>${item.description}&ensp;</td>
						<td><a
							href="javascript:restartUser('${item.id}','${ item.realName}','${item.loginName}')"
							class="button button-small bg-sub">启用</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>