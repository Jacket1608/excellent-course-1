<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--jquery --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
<%--拼图的js --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/pintuer.js"></script>
<%--拼图的IE8兼容 --%>
<script src="${pageContext.request.contextPath}/commons/pintucomps/respond.js"></script>
<div style="width: 600px; margin: 0px auto;">
	<div class="panel">
		<div class="panel-body">
			<form method="post" id="bbsBlock" onsubmit="return editbb()">
				<div class="form-group">
					<div class="label">
						<label for="name">版块名称</label>
					</div>
					<div class="field">
						<input type="hidden" id="id" name="id" size="30" value="${bbsBlock.id }" /> <input type="text"
							class="input" id="name" name="name" size="30" value="${bbsBlock.name }" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="description">版块介绍</label>
					</div>
					<div class="field">
						<textarea class="input" rows="5" cols="50" id="description" name="description">${bbsBlock.description }</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="blockRules">版块规则</label>
					</div>
					<div class="field">
						<textarea class="input" rows="5" cols="50" id="blockRules" name="blockRules">${bbsBlock.blockRules }</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="cookies">父版块</label>
					</div>
					<div class="field">
						<select class="input" name="parentId" ${fn:length(bbsBlock.children)>0?"disabled":"" }
							${fn:length(bbsBlock.children) }>
							<option value="-1">无</option>
							<c:forEach var="item" items="${parentBbs }">
								<option value="${item.id }"
									${!empty bbsBlock.parent?(item.id==bbsBlock.parent.id?"selected":""):"" }>${item.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-button">
					<input class="button bg-main" type="submit" value="提    交"> <input
						class="button bg-yellow" type="reset" value="重    置">
				</div>
			</form>
		</div>
	</div>
</div>