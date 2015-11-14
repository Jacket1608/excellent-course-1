<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt"  prefix="fmt"%>
<div class="line-big">
		<div class="xm3">
			<div class="panel border-back">
            	<div class="panel-body text-center">
                	<img src="${pageContext.request.contextPath }/${sessionScope.currentUser.headImage }" width="120" class="radius-circle" /><br />
                    ${currentUserName }
                </div>
                <div class="panel-foot bg-mix border-back">您好！${currentUserName }，上次登录时间为：${lastLogintime } 。</div>
            </div>
            <br />
		</div>
		<div class="xm9">
		
		</div>
	</div>	