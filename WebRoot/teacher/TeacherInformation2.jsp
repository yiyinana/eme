<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'teacher_information_1.jsp' starting page</title>

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/tchrLeftBar.jsp"%>
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr">
						<!-- 研究成果 -->
							<div class="div-tchr-rsch-fin">
								<div class="div-inf-bar">
									<label>研究成果</label>
								</div>
								<div class="div-inf-tbl">
									<h5>主持项目</h5>
									<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>项目名称</th>
											<th>项目类型</th>
											<th>时间</th>
											<th>资金</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="awards" var="a">
										<tr>
											<td width="30%"><s:property value="#a.name"/></td>
											<td width="21%"><s:property value="#a.type"/></td>
											<td width="14%"><s:property value="#a.time"/></td>
											<td width="14%"></td>
											<td><a>修改</a>&nbsp;&nbsp;<a>删除</a></td>
										</tr>
									</s:iterator>
									</tbody>
								</table>
								</div>

								<div class="div-inf-tbl">
									<h5>论文、著作</h5>
									<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>论文/著作</th>
											<th>类型</th>
											<th>刊物/出版社</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
								</div>
								<div class="div-inf-tbl">
									<h5>获奖、鉴定、转让、专利等成果</h5>
									<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>名称</th>
											<th>类型</th>
											<th>编号</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
								</div>
							</div>
							<!-- 研究成果完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88-41+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
						