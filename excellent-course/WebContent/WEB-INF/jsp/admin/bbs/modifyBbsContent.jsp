<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>知识社区</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/commons/pintucomps/pintuer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/artDialog/ui-dialog.css">
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
		<script src="${pageContext.request.contextPath}/commons/artDialog/dialog-min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/commons/kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="${pageContext.request.contextPath}/commons/kindeditor/kindeditor-all.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath}/commons/kindeditor/lang/zh_CN.js"></script>
</head>
<body>
<div style="width: 100%;">
	<div style="width: 1024px;margin: 0px auto;">
	<%--发帖的富文本框 --%>
	<div class="sepLine"></div>
	<div style="padding: 0px 10px;">
		<form method="post" id="fatie" onsubmit="return fatie()">
		  <div class="form-group">
		    <div class="label"><label for="username">帖子标题</label></div>
		    <div class="field">
		      <input type="text" class="input" id="title" name="title" value="${bbsContent.title }" size="30"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="label"><label for="username">上传附件</label></div>
		    <div class="field">
		     	<input type="button" id="insertfile" value="选择文件" />
		    	<c:if test="${!empty attachs }">
		    	<c:forEach var="item" items="${attachs }">
		    		<div class="fileItem">
		    			<input type="hidden" value="${item.id }" name="attachIds">
		    			<img width="16px" height="16px" src="${ pageContext.request.contextPath}/${item.fileImages }">
		    			<label>${item.fileName }</label>
		    			<button onclick="deleteItem(this)">删除</button>
		    		</div>
		    	</c:forEach>
		    	</c:if>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="label"><label for="username">帖子内容</label></div>
		    <div class="field">
				<textarea id="content" style="width: 100%; height: 400px;">${bbsContent.content }</textarea>
		    </div>
		  </div>
		  <div class="form-button">
				<input class="button bg-main" type="submit" value="发   帖"> 
			</div>
		</form>
	</div>
	<%--发帖的富文本框结束 --%>
	</div>
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
							'undo', 'redo', '|',  'code', 'cut', 'copy', 'paste',
							'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
							'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
							'superscript', 'clearhtml', 'quickformat', 'selectall', '|',
							'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
							'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
							  'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
							'anchor', 'link', 'unlink', 'fullscreen'
						]
			});
			
			//上传附件
			var uploadbutton = K.uploadbutton({
				button : K('#insertfile')[0],
				fieldName : 'attach',
				url : '${pageContext.request.contextPath}/attach/upload/attach?dir=bbs',
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
		});
		/**
		 * 发帖
		 */
		function fatie(){
			var title =$("#title").val();
			var content = encodeURI(editor.html());
			if(!(content.length>0 && title.length>0)){
				dialog({title:"操作失败",content:"请填标题和内容", width:400}).show();
				return false;
			}else{
				$.ajax({url:"${pageContext.request.contextPath}/course/edit/${bbsContent.id }/tie?date="+new Date().getTime()
					,data:$("#fatie").serialize()+"&content="+content
					,type:"post"
					,success:function(data){
						if(data.success){
							window.location.href = "${pageContext.request.contextPath}/bbs/${bbsContent.id }/content";
						}else{
							dialog({title:"发帖失败",content:data.msg, width:400}).show();
						}
					}
				});
			}
			return false;
		}
		function deleteItem(obj){
			$(obj).parent("div").remove();
		}
	</script>
</div>

</body>
</html>