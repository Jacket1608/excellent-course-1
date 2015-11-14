<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/pintucomps/pintuer.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/kindeditor/themes/default/default.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/commons/kindeditor/kindeditor-all.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/commons/kindeditor/lang/zh_CN.js"></script>
	
	<%--弹窗口主键 --%>
	<script src="${pageContext.request.contextPath}/commons/artDialog/dialog-min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/artDialog/ui-dialog.css">
	<style type="text/css">
		#main{width: 880px;margin: 20px auto auto auto;}
	</style>
</head>
<body>
<div id="main">
		<form action="" class="form form-x  form-tips" onsubmit="return addCourse()">
			<div class="form-group">
				<div class="label">
					<label>科目名称</label>
				</div>
				<div class="field">
					<input type="text" size="110" class="input" name="name" />
				</div>
			</div>
			
			<div class="form-group">
				<div class="label">
					<label>课程归类</label>
				</div>
				<div class="field">
					<select class="input" name="courseLevel">
						<option value="-1">无</option>
						<c:forEach var="item" items="${courseLevels }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<div class="label">
					<label>课程描述</label>
				</div>
				<div class="field">
					<textarea name="description" style="width: 750px; height: 300px;"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="label">
					<label>教师队伍</label>
				</div>
				<div class="field">
					<textarea name="teacherTeam" style="width: 750px; height: 300px;"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="label">
					<label>教学内容</label>
				</div>
				<div class="field">
					<textarea name="teachContent" style="width: 750px; height: 300px;"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="label">
					<label>教学方式</label>
				</div>
				<div class="field">
					<textarea name="teachMethod" style="width: 750px; height: 300px;"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="label">
					<label>教学环境</label>
				</div>
				<div class="field">
					<textarea name="teachEnvironment" style="width: 750px; height: 300px;"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="label">
					<label>教学大纲</label>
				</div>
				<div class="field">
					<textarea name="teachOutline" style="width: 750px; height: 300px;"></textarea>
				</div>
			</div>
			<div class="form-button">
				<input class="button bg-main" type="submit" value="提    交"> <input class="button bg-yellow" type="reset" value="重    置">
			</div>
			
		</form>
	<script>
	var description,teacherTeam,teachContent,teachMethod,teachEnvironment,teachOutline;
	var uploadJsonPath = "${pageContext.request.contextPath}/info/imageFileUpload";
	var fileManagerJsonPath = "${pageContext.request.contextPath}/info/imageFileManager";
	var allowItems= [
						'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
						'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
						'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
						'superscript', 'clearhtml', 'quickformat', 'selectall', '|',
						'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
						  'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
						'anchor', 'link', 'unlink', 'fullscreen'
					];
	KindEditor.ready(function(K) {
		description = K.create('textarea[name="description"]', {
			allowFileManager : true,
			allowUpload:true,
			uploadJson:uploadJsonPath,
			fileManagerJson:fileManagerJsonPath,
			//编辑器的功能按钮
			items : allowItems
		});
		teacherTeam = K.create('textarea[name="teacherTeam"]', {
			allowFileManager : true,
			allowUpload:true,
			uploadJson:uploadJsonPath,
			fileManagerJson:fileManagerJsonPath,
			//编辑器的功能按钮
			items : allowItems
		});
		teachContent = K.create('textarea[name="teachContent"]', {
			allowFileManager : true,
			allowUpload:true,
			uploadJson:uploadJsonPath,
			fileManagerJson:fileManagerJsonPath,
			//编辑器的功能按钮
			items : allowItems
		});
		teachMethod = K.create('textarea[name="teachMethod"]', {
			allowFileManager : true,
			allowUpload:true,
			uploadJson:uploadJsonPath,
			fileManagerJson:fileManagerJsonPath,
			//编辑器的功能按钮
			items : allowItems
		});
		teachEnvironment = K.create('textarea[name="teachEnvironment"]', {
			allowFileManager : true,
			allowUpload:true,
			uploadJson:uploadJsonPath,
			fileManagerJson:fileManagerJsonPath,
			//编辑器的功能按钮
			items : allowItems
		});
		teachOutline = K.create('textarea[name="teachOutline"]', {
			allowFileManager : true,
			allowUpload:true,
			uploadJson:uploadJsonPath,
			fileManagerJson:fileManagerJsonPath,
			//编辑器的功能按钮
			items : allowItems
		});
	});
	
		function addCourse() {
			//先验证
			var courseLevel = encodeURI($("[name='courseLevel']").val());
			if(courseLevel.length<=0||courseLevel<0){
				dialog({title:"操作失败",content:"请选择课程归类", width:400}).show();
				return false;
			}
			var name = encodeURI($("[name='name']").val());
			var descriptionh = encodeURI(description.html());
			var teacherTeamh = encodeURI(teacherTeam.html());
			var teachContenth = encodeURI(teachContent.html());
			var teachMethodh = encodeURI(teachMethod.html());
			var teachEnvironmenth = encodeURI(teachEnvironment.html());
			var teachOutlineh = encodeURI(teachOutline.html());
			//description,teacherTeam,teachContent,teachMethod,teachEnvironment,teachOutline
			if(courseLevel.length<=0||name.length<=0||descriptionh.length<=0||teacherTeamh.length<=0||teachContenth.length<=0||teachMethodh.length<=0||teachEnvironmenth.length<=0||teachOutlineh.length<=0){
				dialog({title:"操作失败",content:"请填写表单的每一项", width:400}).show();
				return false;
			}
			//提交数据
			$.ajax({
				url : "${pageContext.request.contextPath}/course/addCourse?date=" + new Date().getTime(),
				data:"name="+name+"&courseLevel="+courseLevel+"&description="+descriptionh+"&teacherTeam="+teacherTeamh+"&teachContent="+teachContenth+"&teachMethod="+teachMethodh+"&teachEnvironment="+teachEnvironmenth+"&teachOutline="+teachOutlineh,
				type : "POST",
				success : function(data) {
					if(data.success){
						dialog({title:"操作成功",content:data.msg, width:400,okVal:"确认",ok:function(){window.close();}}).show();
					}else{
						dialog({title:"操作失败",content:data.msg, width:400}).show();
					}
				}
			});
			return false;
		}
	</script>
</div>
</body>
</html>