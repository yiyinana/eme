<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
	<header class="main-header">
		<div class="div-header">
			<div>
				<img src="img/schoollogo.png" class="img-school-logo" />
			</div>
			<div class="div-header-right">
				<label>欢迎您：<s:property value="#session.tUser.userName"/></label>&nbsp;&nbsp;<a href="adminLoginOutAction">退出</a>
			</div>
		</div>
	</header>
	