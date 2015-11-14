<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/pintucomps/pintuer.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
<script src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/kindeditor/themes/default/default.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/commons/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/commons/kindeditor/lang/zh_CN.js"></script>

<%--日期选择控件 --%>
<script charset="utf-8" src="${pageContext.request.contextPath}/commons/dateSelector/laydate.js"></script>
<%--弹窗口主键 --%>
<script src="${pageContext.request.contextPath}/commons/artDialog/dialog-min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/artDialog/ui-dialog.css">
<style type="text/css">
	#main{width: 900px;margin: 20px auto auto auto;}
</style>
</head>
<body>
	<div id="main">
		<form method="post" class="form form-x  form-tips" id="publishNews" onsubmit="return publishNews()">
			<div class="form-group">
				<div class="label">
					<label>课程标题</label>
				</div>
				<div class="field">
					<input class="input" size="110" type="hidden" name="id" value="${courseClass.id }" data-validate="required:必填" />
					<input class="input" size="110" type="text" name="title" value="${courseClass.title }" data-validate="required:必填" />
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>所属科目</label>
				</div>
				<div class="field">
					<select class="input" name="course">
							<option value="-1">无</option>
						<c:forEach var="item" items="${courses }">
							<option value="${item.id }" ${item.id==courseClass.course.id?"selected":"" }>${item.name }</option>
						</c:forEach>
						</select>
				</div>
			</div>
			<div class="form-group">
			   <div class="label"><label for="upfile">课程展示图片</label></div>
			    <div class="field">
			    <c:if test="${!empty courseClass.courseClassImage }">
			      <img alt="" id="upurl" width="200px" height="200px;" src="${courseClass.courseClassImage }">
			      <input type="hidden" id="images" name="courseClassImage" value="${courseClass.courseClassImage }" /><input type="button" id="image1" value="选择图片" /><label>请使用400*400的图片</label>
			    </c:if>
			    <c:if test="${empty courseClass.courseClassImage }">
			      <img alt="" id="upurl" width="0" height="0" style="visibility:hidden;" src="">
			      <input type="hidden" id="images" name="courseClassImage" value="" /><input type="button" id="image1" value="选择图片" /><label>请使用400*400的图片</label>
			    </c:if>
			    </div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>课程介绍</label>
				</div>
				<div class="field">
					<textarea name="description" style="width: 750px; height: 500px;">${courseClass.description }</textarea>
				</div>
			</div>
			<div class="form-button">
				<input class="button bg-main" type="submit" value="提    交"> <input class="button bg-yellow" type="reset" value="重    置">
			</div>
		</form>
		<script type="text/javascript">
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="description"]', {
					//是否允许上传文件
					allowFileManager : true,
					allowUpload:true,
					//图片上传的json
					uploadJson:"${pageContext.request.contextPath}/info/imageFileUpload",
					//文件管理的json
					fileManagerJson:"${pageContext.request.contextPath}/info/imageFileManager",
					//编辑器的功能按钮
					items : [
								'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
								'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
								'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
								'superscript', 'clearhtml', 'quickformat', 'selectall', '|',
								'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
								'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
								  'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
								'anchor', 'link', 'unlink', 'fullscreen'
							],
					skinType:'qq'
				});
				K('#image1').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url1').val(),
							clickFn : function(url, title, width, height, border, align) {
								K('#images').val(url);
								$("#upurl").attr("src",url).css({"visibility":"visible","width":"200px","height":"200px"});
								editor.hideDialog();
							}
						});
					});
				});
			});
			function publishNews(){
				var course = encodeURI($("[name='course']").val());
				var id = encodeURI($("[name='id']").val());
				if(course.length<=0||course==-1){
					dialog({title:"操作失败",content:"请选择课程科目", width:400}).show();
				}
				var title = encodeURI($("[name='title']").val());
				var courseClassImage = encodeURI($("[name='courseClassImage']").val());
				var description = encodeURI(editor.html());
				if(title.length<=0||courseClassImage.length<=0||description.length<=0){
					dialog({title:"操作失败",content:"请填写必填项", width:400}).show();
					return false;
				}
				var postData = "title="+title+"&course="+course+"&courseClassImage="+courseClassImage+"&description="+description;
				$.ajax({url:"${pageContext.request.contextPath}/course/editCourseClass/"+id+"?date="+new Date().getTime(),data : postData,type:"POST",success:function(data){
					if(data.success){
						var OperatorDialog =dialog({title:"发布成功"
							,content:data.msg
							, width:400
							,okValue: '确定'
						    ,ok: function () {
						    	OperatorDialog.close();
								window.close();
						    }}).show();
					}else{
						dialog({title:"操作失败",content:data.msg, width:400}).show();
					}
				}});
				return false;
			}
		</script>
	</div>
</body>
</html>
