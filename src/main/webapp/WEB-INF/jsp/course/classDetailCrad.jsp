<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
<!--
	$(".finalb").html('<a href="${pageContext.request.contextPath }/course/view/${pager.datas[0].id }/classDetail">${pager.datas[0].title }</a>');
//-->
</script>
<div class="detail" style="padding: 0px 10px;">
	<h1 class="title">${pager.datas[0].title }</h1>
	<div class="sepLine"></div>
	<div class="info">
		<%--基本信息 --%>
		发布人：<a href="javascript:showPersonInfo(${pager.datas[0].createUser.id })">${empty pager.datas[0].createUser.nickName?pager.datas[0].createUser.loginName:pager.datas[0].createUser.nickName }</a> &ensp;时间：
		<fmt:formatDate value="${pager.datas[0].createTime }" pattern="yyyy-MM-dd hh:mm" />

		<%--分享代码 --%>
		<span style="float: right;">
			<!-- JiaThis Button BEGIN -->
			<div class="jiathis_style">
				<a class="jiathis_button_qzone"></a>
				<a class="jiathis_button_tsina"></a>
				<a class="jiathis_button_tqq"></a>
				<a class="jiathis_button_weixin"></a>
				<a class="jiathis_button_renren"></a>
				<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
				<a class="jiathis_counter_style"></a>
			</div>
			<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
			<!-- JiaThis Button END -->
		</span>
	</div>
	<div class="sepLine"></div>
	<c:if test="${!empty attachsThis }">
				课程附件：
				<ul class="list-group">
					<c:forEach items="${attachsThis }" var="attachitem">
						<li class="fadein-left"><img title="<c:out value="${attachitem.fileName }"/>"
							src="${pageContext.request.contextPath }/${attachitem.fileImages }"> <a
							style="word-break: break-all;"
							href="${pageContext.request.contextPath }/attach/download/${attachitem.id}" target="_blank"><c:out
									value="${attachitem.fileName }" /></a></li>
					</c:forEach>
				</ul>
					课程内容：
			</c:if>
	<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">
		${pager.datas[0].content }
	</div>
</div>
<%--分页面板 --%>
<div class="sepLine fadein-bottom"></div>
<%@include file="../home/card/pagerLabel.jsp"%>
<%--分页结束 --%>



<%--课程内容评论 --%>
<div class="sepLine"></div>
<c:if test="${!empty pager.datas }">
		<div>
			<form action="" class="form form-tips" id="newsComments" onsubmit="return classDetailComment()">
				<div class="form-group" style="padding: 20px;">
					<div class="label">
						<label>评论：</label>
					</div>
					<div class="field">
						<input type="hidden" name="classDetailid" value="${pager.datas[0].id }" />
						<textarea class="input" id="classDetailCommentsContent" name="content" rows="7"></textarea>
					</div>
				</div>
				<div class="form-button" style="padding: 20px;">
					<input style="float: right;" class="button bg-main" type="submit" value="提    交">
				</div>
			</form>
		</div>
	<div class="sepLine"></div>
	<div style="clear: both; padding: 10px" id="classDetailComments">
		<c:if test="${!empty classDetailCommentses }">
			<c:forEach var="item" items="${classDetailCommentses }">
				<blockquote class="border-main">
					<div class="media media-x">
						<a class="float-left" href="javascript:void(0)"><img src="${pageContext.request.contextPath }/${item.createUser.headImage }" width="64px" height="64px;" class="radius" alt="..."></a>
						<div class="media-body">
							<p>
								评论人：<c:out value="${empty item.createUser.nickName?item.createUser.loginName:item.createUser.nickName }"></c:out>&ensp;&ensp;&ensp;&ensp;时间：
								<fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</p>
							<p style="word-break: break-all;text-indent: 2em;"><c:out value="${item.content }"></c:out></p>
						</div>
					</div>
				 </blockquote>
			</c:forEach>
		</c:if>
	</div>
</c:if>