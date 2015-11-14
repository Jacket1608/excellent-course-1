<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="line-big">
	<div class="xm3">
		<div class="panel border-back">
			<div class="panel-body text-center">
				<img src="${pageContext.request.contextPath }/${sessionScope.currentUser.headImage }"
					width="120" class="radius-circle" /><br /> ${currentUserName }
			</div>
			<div class="panel-foot bg-mix border-back">您好！${currentUserName }，上次登录时间为：${lastLogintime }
				。</div>
		</div>
		<br />
		<div class="panel">
			<div class="panel-head">
				<strong>站点信息统计</strong>
			</div>
			<ul class="list-group">
				<c:forEach var="info" items="${siteInfoCountList }">
					<li><span class="float-right badge bg-main">${info.count }</span><span
						class="${info.ico_class }"></span>${info.name }</li>
				</c:forEach>
			</ul>
		</div>
		<br />
	</div>
	<div class="xm9">
		<div class="panel">
			<div class="panel-head">
				<strong>系统信息</strong>
			</div>
			<table class="table">
				<tr>
					<th colspan="2">服务器信息</th>
					<th colspan="2">系统信息</th>
				</tr>
				<tr>
					<td width="110" align="right">操作系统：</td>
					<td><%=System.getProperty("os.name")%></td>
					<td width="90" align="right">系统开发：</td>
					<td><a href="http://www.cnblogs.com/shuqi/" target="_blank">shuqi</a></td>
				</tr>
				<tr>
					<td align="right">Web服务器：</td>
					<td>Tomcat</td>
					<td align="right">主页：</td>
					<td><a href="${pageContext.request.contextPath }/index" target="_blank">主页</a></td>
				</tr>
				<tr>
					<td align="right">程序语言：</td>
					<td>Java</td>
					<td align="right">Java版本</td>
					<td><%=System.getProperty("java.version")%></td>
				</tr>
				<tr>
					<td align="right">数据库：</td>
					<td>MySQL</td>
					<td align="right">&ensp;</td>
					<td>&ensp;
				</tr>
			</table>
		</div>
	</div>
</div>
