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
					<input class="input" size="110" type="text" name="title" data-validate="required:必填" />
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>所属课程</label>
				</div>
				<div class="field">
					<select class="input" name="courseClass">
							<option value="-1">无</option>
						<c:forEach var="item" items="${courseClasses }">
							<option value="${item.id }">${item.title }</option>
						</c:forEach>
						</select>
				</div>
			</div>
		<!-- 	<div class="form-group">
			   <div class="label"><label for="upfile">视频文件</label></div>
			    <div class="field">
			    	<input type="button" id="insertvfile" value="选择文件" />
			    </div>
			</div> -->
			<div class="form-group">
			   <div class="label"><label for="upfile">附件</label></div>
			    <div class="field">
			    	<input type="button" id="insertfile" value="选择文件" />
			    </div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>内容</label>
				</div>
				<div class="field">
					<textarea name="" id="content" style="width: 750px; height: 500px;"></textarea>
				</div>
			</div>
			<div class="form-button">
				<input class="button bg-main" type="submit" value="提    交"> <input class="button bg-yellow" type="reset" value="重    置">
			</div>
		</form>
		<script type="text/javascript">
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('#content', {
					//是否允许上传文件
					allowFileManager : false,
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
				//附件
				var uploadbutton = K.uploadbutton({
					button : K('#insertfile')[0],
					fieldName : 'attach',
					url : '${pageContext.request.contextPath}/attach/upload/attach?dir=attach',
					afterUpload : function(data) {
						if (data.error === 0) {
							var html = '<div class="fileItem"><input type="hidden" value="'+data.attachId+'" name="attachIds"/><img width="16px" height="16px" src="'+data.fileImages+'"/><label>'+data.fileName+'</label><button onclick="deleteItem(this)">删除</button></div>';
							$("#insertfile").before(html);
						} else {
							dialog({title:"操作失败",content:"上传文件失败<br/>"+data.message, width:400}).show();
						}
					},
					afterError : function(str) {
						dialog({title:"操作失败",content:"上传文件失败", width:400}).show();
					}
				});
				uploadbutton.fileBox.change(function(e) {
					uploadbutton.submit();
				});
				
				//视频附件
			/* 	var uploadbutton2 = K.uploadbutton({
					button : K('#insertvfile')[0],
					fieldName : 'video',
					url : '${pageContext.request.contextPath}/attach/upload/attach?dir=media',
					afterUpload : function(data) {
						debugger;
						if (data.error === 0) {
							//var url = K.formatUrl(data.url, 'absolute');
							//var abfimages = K.formatUrl(data.fileImages, 'absolute');
							var html = '<div class="fileItem"><input type="hidden" value="'+data.attachId+'" name="video"/><img width="16px" height="16px" src="'+data.fileImages+'"/><label>'+data.fileName+'</label><button onclick="deleteItem(this)">删除</button></div>';
							$("#insertvfile").before(html);
						} else {
							dialog({title:"操作失败",content:"上传文件失败<br/>"+data.message, width:400}).show();
						}
					},
					afterError : function(str) {
						dialog({title:"操作失败",content:"上传文件失败", width:400}).show();
					}
				});
				uploadbutton2.fileBox.change(function(e) {
					uploadbutton.submit();
				}); */
				
			});
			
			function publishNews(){
				var courseClass = encodeURI($("[name='courseClass']").val());
				if(courseClass.length<=0||courseClass==-1){
					dialog({title:"操作失败",content:"请选择所属课程", width:400}).show();
				}
				var title = encodeURI($("[name='title']").val());
				var content = encodeURI(editor.html());
				if(title.length<=0||content.length<=0){
					dialog({title:"操作失败",content:"请填写必填项", width:400}).show();
					return false;
				}
				var postData = $("#publishNews").serialize()+"&content="+content;
				
				$.ajax({url:"${pageContext.request.contextPath}/course/publishCourseClassDetail?date="+new Date().getTime(),data : postData,type:"POST",success:function(data){
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
			function deleteItem(obj){
				$(obj).parent("div").remove();
			}
		</script>
	</div>
</body>
</html>
