<%@page import="java.util.Date"%>
<%@page import="io.shuqi.graduation.utils.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
	#main{width: 1024px;margin: 20px auto auto auto;}
</style>
</head>
<body>
	<div id="main">
		<form method="post" class="form form-x form-auto form-tips" id="publishNotification" onsubmit="return false;">
			<div class="form-group">
				<div class="label">
					<label>公告标题</label>
				</div>
				<div class="field">
					<input name="id" value="${notification.id }" type="hidden"/>
					<input class="input" size="110" type="text" name="title" value="${notification.title }" data-validate="required:必填" placeholder="公告标题" />
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>关键字</label>
				</div>
				<div class="field">
					<input class="input" size="110" type="text" name="key" value="${notification.nitificationKey }" data-validate="required:必填" placeholder="关键字,多个关键字使用'|'分割" />
				</div>
			</div>
		<%-- 	<div class="form-group">
				<div class="label">
					<label>发布时间</label>
				</div>
				<div class="field">
					<input onclick="laydate()" class="input" size="110" type="text" name="createTime" value='<fmt:formatDate value="${news.createTime }" pattern="yyyy-MM-dd"/>' data-validate="required:必填" placeholder="发布时间"/>
				</div>
			</div> --%>
			<div class="form-group">
			   <div class="label"><label for="upfile">公告展示图片</label></div>
			    <div class="field">
			     <c:if test="${empty notification.ntfImage }">
			      <img alt="" id="upurl" width="0px;" height="0px;" style="visibility:hidden;" src="">
			      <input type="hidden" id="images" name="images" value="" /><input type="button" id="image1" value="选择图片" /><label>请使用1200*400的图片</label>
			    </c:if>
			    <c:if test="${!empty notification.ntfImage }">
			      <img alt="" id="upurl" width="800px" height="300px;"  src="${notification.ntfImage }">
			      <input type="hidden" id="images" name="images" value="${notification.ntfImage }" /><input type="button" id="image1" value="选择图片" /><label>请使用1200*400的图片</label>
			    </c:if>
			    </div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>公告内容</label>
				</div>
				<div class="field">
					<textarea name="content" style="width: 800px; height: 500px;">${notification.content }</textarea>
				</div>
			</div>
			<div class="form-button">
				<input class="button bg-main" type="submit" onclick="publishNotification()" value="提    交"> <input class="button bg-yellow" type="reset" value="重    置">
			</div>
		</form>
		<script type="text/javascript">
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
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
								$("#upurl").attr("src",url).css({"visibility":"visible","width":"800px","height":"300px"});
								editor.hideDialog();
							}
						});
					});
				});
			});
			function publishNotification(){
				var id = encodeURI($("[name='id']").val());
				var title = encodeURI($("[name='title']").val());
				var key = encodeURI($("[name='key']").val());
				var images = encodeURI($("[name='images']").val());
				var content = encodeURI(editor.html());
				if(title.length<=0||content.length<=0){
					dialog({title:"操作失败",content:"请填写必填项", width:400}).show();
					return false;
				}
				var postData = "title="+title+"&key="+key+"&images="+images+"&content="+content;
				$.ajax({url:"${pageContext.request.contextPath}/info/edit/"+id+"/notification?date="+new Date().getTime(),data : postData,type:"POST",success:function(data){
					if(data.success){
						var teacherOperatorDialog =dialog({title:"修改成功"
							,content:data.msg
							, width:400
							,okValue: '确定'
						    ,ok: function () {
						    	teacherOperatorDialog.close();
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
