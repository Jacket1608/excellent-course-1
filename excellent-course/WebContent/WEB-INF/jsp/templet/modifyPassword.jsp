<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--jquery --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
<%--拼图的js --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
<%--拼图的IE8兼容 --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>
<div style="width: 600px; margin: 0px auto;">
	<form method="post" class="form form-x" id="modifyPassword" onsubmit="return modifyPassword()">
		<div class="form-group">
			<div class="label">
				<label>原密码</label>
			</div>
			<div class="field">
				<input class="input" type="password" name="password" data-validate="required:必填" placeholder="输入原密码" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>新密码</label>
			</div>
			<div class="field">
				<input class="input" type="password" name="newpasseord" data-validate="required:必填" placeholder="输入新密码" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>重复密码</label>
			</div>
			<div class="field">
				<input class="input" type="password" name="renewpassword" data-validate="required:必填" placeholder="重复输入新密码" />
			</div>
		</div>
		<div class="form-button">
			<input class="button bg-main" type="submit" value="提    交"> <input class="button bg-yellow" type="reset" value="重    置">
		</div>

	</form>
</div>