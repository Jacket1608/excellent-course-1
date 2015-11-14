<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%--拼图的js --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
<%--拼图的IE8兼容 --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>
<div class="line-small">
	<div class="x3">
		<div class="panel border-main bg-main">
			<div class="panel-head  border-main bg-main">版块列表</div>
			<div class="panel-body">
				<div class="">
					<c:forEach var="item" items="${list }" varStatus="stu">
						<div class="panel" style="margin-bottom: 5px;">
							<div class="panel-head">
								<a href="javascript:editBbsBlock('${item.id }')">${item.name }</a>
							</div>
							<c:if test="${!empty item.children }">
								<div class="panel-body" style="padding: 0px 0px 0px 15px;">
									<div class="list-link" style="border: 0px;">
										<c:forEach var="child" items="${item.children }">
											<a href="javascript:editBbsBlock('${child.id }')">${child.name }</a>
										</c:forEach>
									</div>
								</div>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="x9">
		<div class="panel border-main">
			<div class="panel-head  border-main bg-main">请选择一个版块操作</div>
			<div class="panel-body" id="bbsBlockMain">
				<p>
					如果设置一个版块没有父版块，则表示为顶级版块，是用来存放子版块的，其本身不做版块。<br /> 1、顶级版块不能发帖，其只是用来存放子版块的<br />
					2、子版块也不要设置为其他子版块的父版块<br />
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function editBbsBlock(id) {
			$.ajax({
				url : "bbs/editBbsBlock/" + id + "?date="
						+ new Date().getTime(),
				success : function(data) {
					$("#bbsBlockMain").html(data);
				}
			});
		}
		function editbb() {
			$.ajax({
				url : "bbs/editBbsBlock?date=" + new Date().getTime(),
				data : $("#bbsBlock").serialize(),
				type : "post",
				success : function(data) {
					if (data.success) {
						var msgDialog = dialog({
							title : "操作成功",
							content : data.msg,
							width : 400,
						}).show();
						show("bbsBlockManager");
						setTimeout(function() {
							msgDialog.close();
						}, 2000);
					} else {
						dialog({
							title : "操作失败",
							content : data.msg,
							width : 300,
							okValue : '确定',
							ok : function() {
							}
						}).show();
					}
				}
			});
			return false;
		}
	</script>
</div>