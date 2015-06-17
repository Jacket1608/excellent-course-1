<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sp"%>

<%--jquery --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
<%--拼图的js --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
<%--拼图的IE8兼容 --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>
<%--日期选择控件 --%>
<script charset="utf-8" src="${pageContext.request.contextPath}/commons/dateSelector/laydate.js"></script>
<div style="width: 800px; margin: 0px auto;">
	<form method="post" class="form form-tips form-x " id="personalInfo" onsubmit="return modifyPersonalInfo()">
		<div class="form-group">
			<div class="label">
				<label>真实姓名</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.realName}" name="realName" data-validate="length#<15:姓名介于0-15之间"  />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>昵称</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.nickName}" name="nickName" data-validate="length#<15:昵称介于0-15之间"  />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>QQ</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.userqq}" name="userqq" data-validate="qq:QQ号格式不正确" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>电话</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.phoneNumber}" name="phoneNumber" data-validate="number:电话号码不对"  />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>邮箱</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.email}" name="email" data-validate="required:必填,email:邮箱格式不对"  />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>简介</label>
			</div>
			<div class="field">
				<textarea class="input" name="description">${user.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>居住地</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.location}" name="location"  />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>出生地</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.birthLocation}" name="birthLocation"  />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>生日</label>
			</div>
			<div class="field">
				<input type="text" class="input" onclick="laydate()" value="${user.birthday}" name="birthday" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>性别</label>
			</div>
			<div class="field">
				<label><input type="radio" name="gender" ${user.gender eq '男'?"checked":"" } data-validate="radio:请选择" value="男" />男</label>
				<label>&ensp;&ensp;<input type="radio" ${user.gender eq '女'?"checked":"" } data-validate="radio:请选择" name="gender" value="女" />女</label>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>个人主页</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.personalPage}" name="personalPage" data-validate="url"  />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>个性签名</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.personalTitle}" name="personalTitle" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>个人爱好</label>
			</div>
			<div class="field">
				<input type="text" class="input" value="${user.personalFavorite}" name="personalFavorite"  />
			</div>
		</div>
		 <div class="form-button">
		 	<input class="button bg-main" type="submit" value="提    交">
		 	<input class="button bg-yellow" type="reset" value="重    置">
		 </div>
	</form>
</div>