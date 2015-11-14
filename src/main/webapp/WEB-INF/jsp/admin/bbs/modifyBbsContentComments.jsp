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
	
	<div class="panel">
	<div class="panel-head bg-main">
		标题：<strong>${bbsContent.title }</strong> <span style="float: right;"> 发帖时间：<fmt:formatDate
				value="${bbsContent.createTime }" pattern="yyyy-MM-dd HH:mm" />&ensp;&ensp; 累计阅读次数：<span
			class="badge">${bbsContent.readTimes }</span>
		</span>
	</div>
	<div class="panel-body">
		<div class="line-small"
			style="border-bottom: #0088aa 1px solid; background-color: #F7F9FB; height: 100%; min-height: 300px;">
			<div class="x3" style="text-align: center; border-right: #F0F0F0 1px solid;">
				<img alt="" src="${pageContext.request.contextPath }/${bbsContent.createUser.headImage}"
					width="64px;" height="64px;">
				<table class="table">
					<tr>
						<td>用户昵称</td>
						<td>${bbsContent.createUser.nickName}</td>
					</tr>
					<tr>
						<td>用户QQ</td>
						<td>${bbsContent.createUser.userqq}</td>
					</tr>
					<tr>
						<td>上次登录时间</td>
						<td><fmt:formatDate value="${bbsContent.createUser.lastLoginTime}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td>用户发帖数</td>
						<td>${bbsContent.createUser.bbsCount}</td>
					</tr>
					<tr>
						<td>用户生日</td>
						<td><fmt:formatDate value="${bbsContent.createUser.birthday}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td>出身地</td>
						<td>${bbsContent.createUser.birthLocation}</td>
					</tr>
					<tr>
						<td>居住地</td>
						<td>${bbsContent.createUser.location}</td>
					</tr>
				</table>
			</div>
			<div class="x9" style="background-color: #FFF; min-height: 300px; padding: 5px 20px;">
				发帖时间：
				<fmt:formatDate value="${bbsContent.createTime }" pattern="yyyy-MM-dd HH:mm" />
				&ensp;&ensp; 修改时间：
				<fmt:formatDate value="${bbsContent.modifyTime }" pattern="yyyy-MM-dd HH:mm" />
				&ensp;&ensp;
				<!-- JiaThis Button BEGIN -->
				<div style="clear: both; height: 10px;"></div>
				<div class="jiathis_style">
					<span class="jiathis_txt">分享到：</span> <a class="jiathis_button_qzone">QQ空间</a> <a
						class="jiathis_button_tsina">新浪微博</a> <a class="jiathis_button_tqq">腾讯微博</a> <a
						class="jiathis_button_weixin">微信</a> <a href="http://www.jiathis.com/share"
						class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a> <a
						class="jiathis_counter_style"></a>
				</div>
				<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
				<!-- JiaThis Button END -->
				<div style="clear: both; height: 10px;"></div>
				<c:if test="${!empty attachs }">
					帖子附件：
					<ul class="list-group">
						<c:forEach items="${attachs }" var="attachitem">
							<li class="fadein-left"><img title="<c:out value="${attachitem.fileName }"/>"
								src="${pageContext.request.contextPath }/${attachitem.fileImages }"> <a
								style="word-break: break-all;"
								href="${pageContext.request.contextPath }/attach/download/${attachitem.id}" target="_blank"><c:out
										value="${attachitem.fileName }" /></a></li>
						</c:forEach>
					</ul>
						帖子内容：
				</c:if>
				<c:if test="${bbsContent.access }">
					<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">${bbsContent.content }</div>
				</c:if>
				<c:if test="${!bbsContent.access }">
					<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">内容已被作者隐藏</div>
				</c:if>


			</div>
		</div>
		<%--内容展示 --%>
	</div></div>
	
	<%--发帖的富文本框 --%>
	<div class="sepLine fadein-bottom"></div>
	<div style=" padding: 0px 10px;">
		<form method="post" id="fatie" onsubmit="return feedback()">
		  <div class="form-group">
		    <div class="label"><label for="username">回复内容</label></div>
		    <div class="field">
				<textarea id="contentComments" style="width: 100%; height: 200px;">${bbsContentComments.content }</textarea>
		    </div>
		  </div>
		  <div class="form-button">
				<input class="button bg-main" type="submit" value="修   改"> 
			</div>
		</form>
	</div>
	<%--发帖的富文本框结束 --%>
	</div>
	<script type="text/javascript">
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
				var content = encodeURI(contentComments.html());
				if(content.length==0){
					dialog({title:"操作失败",content:"请填内容", width:400}).show();
					return false;
				}else{
					$.ajax({url:"${pageContext.request.contextPath}/course/edit/${bbsContentComments.id }/tieComments?date="+new Date().getTime()
						,data:"content="+content
						,type:"post"
						,success:function(data){
							if(data.success){
								window.location.href="${pageContext.request.contextPath }/bbs/${bbsContentComments.bbsContent.id }/content";
							}else{
								dialog({title:"修改失败",content:data.msg, width:400}).show();
							}
						}
					});
				}
				return false;
			}
	</script>
	
</div>

</body>
</html>