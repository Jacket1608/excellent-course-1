<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
<!--
$(".finalb").html('<a href="${pageContext.request.contextPath }/info/view/${pager.datas[0].id }/${souresType}">${pager.datas[0].title }</a>');
//-->
</script>
<%--如果不存在 --%>
<c:if test="${empty pager.datas }">
	<div class="keypoint bg">
		<h1>404</h1>
		<p>sorry you view page not find!</p>
		<p>
			<a href="${pageContext.request.contextPath }/info/index">view others</a>
		</p>
	</div>
</c:if>
<%--存在 --%>
<c:if test="${!empty pager.datas }">
	<%--新闻 --%>
	<c:if test="${souresType=='news' }">
		<div class="detail" style="padding: 0px 10px;">
			<h1 class="title">${pager.datas[0].title }</h1>
			<div class="sepLine"></div>
			<div class="info">
				<%--基本信息 --%>
				发布人：<a href="javascript:showPersonInfo(${pager.datas[0].createUser.id })">${empty pager.datas[0].createUser.nickName?pager.datas[0].createUser.loginName:pager.datas[0].createUser.nickName }</a>
				&ensp;时间：
				<fmt:formatDate value="${pager.datas[0].createTime }" pattern="yyyy-MM-dd hh:mm" />

				<%--分享代码 --%>
				<span style="float: right; margin-right: 10px;"> <!-- JiaThis Button BEGIN -->
					<div class="jiathis_style">
						<a class="jiathis_button_qzone"></a> <a class="jiathis_button_tsina"></a> <a
							class="jiathis_button_tqq"></a> <a class="jiathis_button_weixin"></a> <a
							class="jiathis_button_renren"></a> <a href="http://www.jiathis.com/share"
							class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a> <a
							class="jiathis_counter_style"></a>
					</div> <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
					<!-- JiaThis Button END -->
				</span>
			</div>
			<div class="sepLine"></div>
			<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">
				<img width="700px;" title="${pager.datas[0].title }" src="${pager.datas[0].images }"/><br/>
				${pager.datas[0].content }
			</div>
		</div>
	</c:if>
	<%--公告 --%>
	<c:if test="${souresType=='notification' }">
		<div class="detail" style="padding: 0px 10px;">
			<h1 class="title">${pager.datas[0].title }</h1>
			<div class="sepLine"></div>
			<div class="info">
				<%--基本信息 --%>
				发布人：<a href="javascript:showPersonInfo(${pager.datas[0].createUser.id })">${empty pager.datas[0].createUser.nickName?pager.datas[0].createUser.loginName:pager.datas[0].createUser.nickName }</a>
				&ensp;时间：
				<fmt:formatDate value="${pager.datas[0].createTime }" pattern="yyyy-MM-dd hh:mm" />

				<%--分享代码 --%>
				<span style="float: right; margin-right: 10px;"> <!-- JiaThis Button BEGIN -->
					<div class="jiathis_style">
						<a class="jiathis_button_qzone"></a> <a class="jiathis_button_tsina"></a> <a
							class="jiathis_button_tqq"></a> <a class="jiathis_button_weixin"></a> <a
							class="jiathis_button_renren"></a> <a href="http://www.jiathis.com/share"
							class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a> <a
							class="jiathis_counter_style"></a>
					</div> <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
					<!-- JiaThis Button END -->
				</span>
			</div>
			<div class="sepLine"></div>
			<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">
				<P>
					<img width="700px;" title="${pager.datas[0].title }" src="${pager.datas[0].ntfImage }"/><br/>
					${pager.datas[0].content }
				</P>
			</div>
		</div>
	</c:if>
</c:if>

<%--分页面板 --%>
<div class="sepLine"></div>
<%@include file="card/pagerLabel.jsp"%>

<%--新闻评论 --%>
<div class="sepLine"></div>
<c:if test="${!empty pager.datas }">
	<c:if test="${souresType=='news' }">
		<div>
			<form action="" class="form form-tips" id="newsComments" onsubmit="return newsComment()">
				<div class="form-group" style="padding: 20px;">
					<div class="label">
						<label>评论：</label>
					</div>
					<div class="field">
						<input type="hidden" name="newsid" value="${pager.datas[0].id }" />
						<textarea class="input" id="newsCommentsContent" name="content" rows="7"></textarea>
					</div>
				</div>
				<div class="form-button" style="padding: 20px;">
					<input style="float: right;" class="button bg-main" type="submit" value="提    交">
				</div>
			</form>
		</div>
	</c:if>
	<div class="sepLine"></div>
	<div style="clear: both; padding: 10px" id="newsCommentsblock">
		<c:if test="${!empty newsComments }">
			<c:forEach var="item" items="${newsComments }">
				<blockquote class="border-main">
					<div class="media media-x">
						<a class="float-left" href="javascript:void(0)"><img
							src="${pageContext.request.contextPath }/${item.createUser.headImage }" width="64px"
							height="64px;" class="radius" alt="..."></a>
						<div class="media-body">
							<p>
								评论人：<c:out value="${empty item.createUser.nickName?item.createUser.loginName:item.createUser.nickName }"></c:out>&ensp;&ensp;&ensp;&ensp;时间：
								<fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</p>
							<p style="word-break: break-all; text-indent: 2em;"><c:out value="${item.content }"></c:out></p>
						</div>
					</div>
				</blockquote>
			</c:forEach>
		</c:if>
	</div>
</c:if>
