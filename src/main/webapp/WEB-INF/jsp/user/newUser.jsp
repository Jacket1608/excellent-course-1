<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--jquery --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
<%--拼图的js --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
<%--拼图的IE8兼容 --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>

<div style="width: 600px; margin: 0px auto;">
	<form method="post" class="form form-x " id="newUser" onsubmit="return newUser()">
		<div class="form-group">
			<div class="label">
				<label>姓名</label>
			</div>
			<div class="field">
				<input class="input" type="text" name="realName" placeholder="输入真实姓名" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>登录名</label>
			</div>
			<div class="field">
				<input class="input" type="text" name="loginName" data-validate="required:必填" placeholder="输入登录名" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>邮箱</label>
			</div>
			<div class="field">
				<input class="input" type="text" name="email" data-validate="required:必填,email:邮箱格式不正确" placeholder="重复输入用户邮箱" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>性别</label>
			</div>
			<div class="field">
				<label><input type="radio" name="gender" checked data-validate="radio:请选择" value="男" />男</label>
				<label><input type="radio"  data-validate="radio:请选择" name="gender" value="女" />女</label>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>用户类型</label>
			</div>
			<div class="field">
				<label><input type="radio" checked data-validate="radio:请选择" name="userType" value="student" />学生</label>
				<label><input type="radio" name="userType"  data-validate="radio:请选择" value="teacher" />教师</label>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>密码</label>
			</div>
			<div class="field">
				<input class="input" type="password" name="password" data-validate="required:必填" placeholder="输入密码" />
			</div>
		</div>
		<div class="form-button">
			<input class="button bg-main" type="submit" value="提    交"> <input class="button bg-yellow" type="reset" value="重    置">
		</div>

	</form>
</div>
<script type="text/javascript">
	//新建用户
	
	function newUser() {
		if ($("#personalInfo>.check-error").length > 0)
			return false;
		$.ajax({
			url : 'user/newUser',
			data : $('#newUser').serialize(),
			type : "POST",
			success : function(data) {
				if (data.success) {
					var msgDialog = dialog({
						title : "操作成功",
						content : data.msg,
						width : 400,
					}).show();
					show("newUser");
					setTimeout(function() {
						msgDialog.close();
					}, 2000);
				} else {
					dialog({
						title : "操作失败",
						content : data.msg,
						width : 300,
						okValue : '确定',
						ok : function() {
						}
					}).show();
				}
			}
		});
		return false;
	}
</script>