/**
 * 论坛发帖富文本框初始化
 */
var editor;
KindEditor.ready(function(K) {
	editor = K.create('#content', {
		//是否允许上传文件
		allowFileManager : false,
		allowUpload:true,
		//图片上传的json
		uploadJson:basePath+"/info/imageFileUpload",
		//文件管理的json
		fileManagerJson:basePath+"/info/imageFileManager",
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
		url : basePath+'/attach/upload/attach?dir=bbs',
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
	debugger;
	var blockId =$("[name='blockId']").val();
	var title =$("#title").val();
	var content = encodeURI(editor.html());
	if(!(blockId.length>0  && content.length>0 && title.length>0)){
		dialog({title:"操作失败",content:"请填标题和内容", width:400}).show();
		return false;
	}else{
		$.ajax({url:basePath+"/bbs/add/bbsContent?date="+new Date().getTime()
			,data:$("#fatie").serialize()+"&content="+content
			,type:"post"
			,success:function(data){
				if(data.success){
					window.location.href = basePath+"/bbs/"+data.id+"/content";
				}else{
					dialog({title:"发帖失败",content:data.msg, width:400}).show();
				}
			}
		});
	}
	return false;
}


/**
 * 论坛发帖富文本框初始化
 */
var contentComments;
KindEditor.ready(function(K) {
	contentComments = K.create('#contentComments', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
	
});
function feedback(){
	var bcontentId =$("[name='bcontentId']").val();
	var content = encodeURI(contentComments.html());
	if(content.length==0){
		dialog({title:"操作失败",content:"请填内容", width:400}).show();
		return false;
	}else{
		$.ajax({url:basePath+"/bbs/add/bbsContentComments?date="+new Date().getTime()
			,data:"bcontentId="+bcontentId+"&content="+content
			,type:"post"
			,success:function(data){
				if(data.success){
					window.location.reload();
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
