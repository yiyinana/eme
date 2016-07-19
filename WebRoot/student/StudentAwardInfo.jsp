<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>学生获奖内容</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/student_goal.css" />
<link rel="stylesheet" href="css/student_item_file.css" />
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/student_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/stuLeftBar.jsp"%>
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr" style=" min-height:440px ">
							<div class="div-inf-bar">
								<label>项目信息</label>
							</div>
							<div class="div-tchr-basic-inf">
								<div class="div-inf-tbl">
									<table class="table table-bordered ">
										<tbody>
											<tr>
												<td width="120px">项目编号</td>
												<td><s:property value="item.itemNum" /></td>
												<td width="120px">项目名称</td>
												<td><s:property value="item.itemName" /></td>
											</tr>
											<tr>
												<td>项目类别</td>
												<td><s:property value="itemEvaluateType.itemEvaTypeName" /></td>
												<td>评价指标点</td>
												<td><s:property value="itemEvaluatePoint.itemEvaPointName" /></td>
											</tr>
											<tr>
												<td>奖项等级</td>
												<td><s:property value="itemEvaluateScore.itemEvaScoreName" /></td>
												<td>主办单位</td>
												<td><s:property value="item.itemUnit" /></td>
											</tr>
											<tr>
												<td>项目表彰对象</td>
												<td><s:property value="item.itemPrizeObject" /></td>
												<td>项目参与角色</td>
												<td><s:property value="item.itemRole" /></td>

											</tr>
											<tr>
												<td>审核状态</td>
												<td><s:property value="item.itemState" /></td>
												<td>审核得分</td>
												<td><s:property value="item.itemScore" /></td>
											</tr>
											<tr>
												
												<td>审核意见</td>
												<td colspan=3><s:property value="item.note" /></td>
											</tr>
										</tbody>
									</table>

									<table class="table table-bordered ">
										<thead>
											<tr>
												<th>附件</th>
												<th>预览</th>
												<th>下载</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="allFile" var="s">
												<tr>
													<td><s:property value="#s.fileName" /></td>
													<td><s:if
															test="#s.fileType=='jpg' || #s.fileType=='png'">
															<a id="<s:property value="#s.saveFileName" />"
																onclick="openWindow(this)" >预览</a>
														</s:if> <s:else>
															<span>不能预览</span>
														</s:else></td>
													<td><a
														href="FileDown_downStuFile?fileName=<s:property value="#s.fileName" />&saveFileName=<s:property value="#s.saveFileName" />">下载</a></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 显示大图片 -->
	<div id="showBigPhoto" class="white_content">
		<img id="exit" src="img/closelabel.gif" onClick="closeWindow()"
			alt="退出显示" title="退出显示">
		<div class="photo">
			<img id="bigPhoto" />
		</div>
	</div>

	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>

	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});

		var msg = "${requestScope.Message}";
		if (msg != "") {
			alert(msg);
		}
	<%request.removeAttribute("Message");%>
		//显示后将request里的Message清空，防止回退时重复显示。

		//图片的预览
		function openWindow(obj) {
			document.getElementById("bigPhoto").src = "upload/" + obj.id;
			document.getElementById('showBigPhoto').style.display = 'block';
			document.getElementsByTagName('footer')[0].style.display = 'none';
			document.getElementsByClassName('container')[0].style.display = 'none';
		}
		function closeWindow() {
			document.getElementById('showBigPhoto').style.display = 'none';
			document.getElementsByTagName('footer')[0].style.display = 'block';
			document.getElementsByClassName('container')[0].style.display = 'block';
		}
	</script>
</body>
</html>
