<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
<!--
	$(".finalb").html('<a href="${pageContext.request.contextPath }/bbs/${bcontent.id }/block">${bcontent.title }</a>');
//-->
</script>
<%--内容展示 --%>
<div class="panel">
	<div class="panel-head bg-main">
		标题：<strong>${bcontent.title }</strong> <span style="float: right;"> 发帖时间：<fmt:formatDate
				value="${bcontent.createTime }" pattern="yyyy-MM-dd HH:mm" />&ensp;&ensp; 累计阅读次数：<span
			class="badge">${bcontent.readTimes }</span>
		</span>
	</div>
	<div class="panel-body">
		<div class="line-small"
			style="border-bottom: #0088aa 1px solid; background-color: #F7F9FB; height: 100%; min-height: 300px;">
			<div class="x3" style="text-align: center; border-right: #F0F0F0 1px solid;">
				<img alt="" src="${pageContext.request.contextPath }/${bcontent.createUser.headImage}"
					width="64px;" height="64px;">
				<table class="table">
					<tr>
						<td>用户昵称</td>
						<td>${bcontent.createUser.nickName}</td>
					</tr>
					<tr>
						<td>用户QQ</td>
						<td>${bcontent.createUser.userqq}</td>
					</tr>
					<tr>
						<td>上次登录时间</td>
						<td><fmt:formatDate value="${bcontent.createUser.lastLoginTime}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td>用户发帖数</td>
						<td>${bcontent.createUser.bbsCount}</td>
					</tr>
					<tr>
						<td>用户生日</td>
						<td><fmt:formatDate value="${bcontent.createUser.birthday}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td>出身地</td>
						<td>${bcontent.createUser.birthLocation}</td>
					</tr>
					<tr>
						<td>居住地</td>
						<td>${bcontent.createUser.location}</td>
					</tr>
				</table>
			</div>
			<div class="x9" style="background-color: #FFF; min-height: 300px; padding: 5px 20px;">
				发帖时间：
				<fmt:formatDate value="${bcontent.createTime }" pattern="yyyy-MM-dd HH:mm" />
				&ensp;&ensp; 修改时间：
				<fmt:formatDate value="${bcontent.modifyTime }" pattern="yyyy-MM-dd HH:mm" />
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
				<c:if test="${(!empty attachs && bcontent.access) }">
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
				<c:if test="${!bcontent.access }">
					<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">内容已被作者隐藏</div>
				</c:if>
				<c:if test="${bcontent.access }">
					<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">${bcontent.content }</div>
				</c:if>


			</div>
		</div>
		<%--内容展示 --%>




		<%--评论部分了 --%>
		<c:forEach var="item" items="${pager.datas }">
			<div class="line-small"
				style="border-bottom: #0088aa 1px solid; background-color: #F7F9FB; height: 100%; min-height: 300px;">
				<div class="x3" style="text-align: center; border-right: #F0F0F0 1px solid;">
					<img alt="" src="${pageContext.request.contextPath }/${item.createUser.headImage}"
						width="64px;" height="64px;">
					<table class="table">
						<tr>
							<td>用户昵称</td>
							<td>${item.createUser.nickName}</td>
						</tr>
						<tr>
							<td>用户QQ</td>
							<td>${item.createUser.userqq}</td>
						</tr>
						<tr>
							<td>上次登录时间</td>
							<td><fmt:formatDate value="${item.createUser.lastLoginTime}" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<td>用户发帖数</td>
							<td>${item.createUser.bbsCount}</td>
						</tr>
						<tr>
							<td>用户生日</td>
							<td><fmt:formatDate value="${item.createUser.birthday}" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<td>出身地</td>
							<td>${item.createUser.birthLocation}</td>
						</tr>
						<tr>
							<td>居住地</td>
							<td>${item.createUser.location}</td>
						</tr>
					</table>
				</div>
				<div class="x9" style="background-color: #FFF; min-height: 300px; padding: 5px 20px;">
					发帖时间：
					<fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm" />
					&ensp;&ensp; 修改时间：
					<fmt:formatDate value="${item.modifyTime }" pattern="yyyy-MM-dd HH:mm" />
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
					
					<c:if test="${item.access }">
						<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">${item.content }</div>
					</c:if>
					<c:if test="${!item.access }">
						<div style="text-indent: 2em; line-height: 20px;word-break: break-all;">内容已被回复者隐藏</div>
					</c:if>
				</div>
			</div>
		</c:forEach>

		<%--分页 --%>
		<div class="sepLine"></div>
		<%--分页面板 --%>
		<%@include file="card/pagerLabel.jsp"%>
		<%--分页结束 --%>

	</div>
</div>