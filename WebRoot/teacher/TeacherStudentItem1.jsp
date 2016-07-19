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

<title>学生列表</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
<link rel="stylesheet" href="css/student_goal.css" />
<link rel="stylesheet" href="css/student_item_file.css" />

</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/tchrLeftBar.jsp"%>
				<div class="span9">
					<div class="span9 div-content-white-bgr">
						<div class="div-inf-bar">
							<label>项目信息</label>
						</div>
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
										<td><s:property
												value="itemEvaluatePoint.itemEvaPointName" /></td>
									</tr>
									<tr>
										<td>奖项等级</td>
										<td><s:property
												value="itemEvaluateScore.itemEvaScoreName" /></td>
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

							<table class="table table-bordered">
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
														onclick="openWindow(this)">预览</a>
												</s:if> <s:else>
													<span>不能预览</span>
												</s:else></td>
											<td><a
												href="FileDown_downStuFile?fileName=<s:property value="#s.fileName" />&saveFileName=<s:property value="#s.saveFileName" />">下载</a></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							<input type="text" id="item-itemEvaScore"
								value="<s:property value="itemEvaluateScore.itemEvaScore" />"
								style="display: none"> <input type="text"
								id="item-itemMemberScore"
								value="<s:property value="itemEvaluateScore.itemMemberScore" />"
								style="display: none"> <input type="text"
								id="item-itemPrizeObject"
								value="<s:property value="item.itemPrizeObject" />"
								style="display: none">
						</div>
						<div style="position: relative;  left:70%">
							<input type="button" class="btn" data-toggle="modal"
								data-target="#myModal"
								id="<s:property value="item.stuItemId" />"
								onclick="getItemId(this)" value="项目审核" >
						</div>
					</div>
					<!-- 模态框，用于添加参与活动信息 -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true"
						style="display: none">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">×</button>
									<h5 class="modal-title">项目审核</h5>
								</div>
								<div class="modal-body">
									<form action="TeacherStudent_Information_1_judgeStuItem"
										method="post" class="form-horizontal form-add"
										enctype="multipart/form-data"
										onsubmit="javascript:return isEmpty(1)">
										<div class="control-group">
											<label class="control-label">操作类型：</label>
											<div class="controls">
												<select id="input-type" name="item.itemState"
													onchange="typeChange()">
													<option value="已审核">评分</option>
													<option value="已驳回">驳回</option>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">项目评分：</label>
											<div class="controls">
												<input id="input-score" type="text" name="item.itemScore"
													readOnly onkeyup="numJudge()" placeholder="请输入数字">&nbsp;&nbsp;<select
													id="scoreList" style="width: 60px; display: none"
													onchange="putScore()"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">审核意见：</label>
											<div>
												<textarea id="input-note" name="item.note"
													class="textarea-modal"></textarea>
												<input type="text" class="hidden" name="item.stuItemId"
													id="input-itemId">
											</div>
										</div>
										<div class="div-btn">
											<input type="submit" value="提交" class="btn">
										</div>
									</form>
								</div>

							</div>
						</div>
					</div>
					<!-- 模态框，用于添加参与活动信息完 -->
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
	<script type="text/javascript">
		var msg = "${requestScope.Message}";
		if (msg != "") {
			alert(msg);
		}
	<%request.removeAttribute("Message");%>
		//显示后将request里的Message清空，防止回退时重复显示。

		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
	<script>
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
		function isEmpty() {
			var score = document.getElementById("input-score");
			var note = document.getElementById("input-note");

			if (score.value.toString().trim().length != 0
					&& note.value.toString().trim().length != 0) {
				return true;
			} else {
				if (score.value.toString().trim().length == 0) {
					score.focus();
				}
				if (note.value.toString().trim().length == 0) {
					note.focus();
				}
				alert("输入框不能为空！");
				return false;
			}
		}
		//判断输入的数字是否符合要求
		function numJudge() {
			var obj = document.getElementById("input-score").value;
			document.getElementById("input-score").value = obj.replace(
					/[^\d.]/g, '')
			/* var num = parseFloat(obj);
			if (num > 12.0) {
				alert("分数应小于12！");
				document.getElementById("input-score").value = "";
			} */
		}
		//获取项目的编号
		function getItemId(obj) {
			var itemId = obj.id;
			document.getElementById("input-itemId").value = itemId;
			var itemEvaScore = document.getElementById("item-itemEvaScore").value;
			var itemMemberScore = document.getElementById("item-itemMemberScore").value;
			var itemPrizeObject = document
					.getElementById("item-itemPrizeObject").value;
			if (itemEvaScore.indexOf("-") >= 0) {
				var min = itemEvaScore.substr(0, itemEvaScore.indexOf("-"));
				var max = itemEvaScore.substr(itemEvaScore.indexOf("-") + 1);
				$("#scoreList").html("");
				for (var i = min; i <= max; i++) {
					document.getElementById("scoreList").style.display = "";
					$("#scoreList").append(
							"<option value="+i+">" + i+ "</option>");
				}
				var judgeScore = (parseInt(min) + parseInt(max)) / 2;

			} else {
				var judgeScore = itemEvaScore;
			}
			if (itemPrizeObject == "个人") {
				document.getElementById("input-score").value = judgeScore;
			} else {
				document.getElementById("input-score").value = itemMemberScore;
			}
		}
		//根据所选操作的类型的不同控制界面的显示
		function typeChange() {
			var type = document.getElementById("input-type").value;
			var itemEvaScore = document.getElementById("item-itemEvaScore").value;
			var itemMemberScore = document.getElementById("item-itemMemberScore").value;
			var itemPrizeObject = document
					.getElementById("item-itemPrizeObject").value;
			if (itemEvaScore.indexOf("-") >= 0) {
				var min = itemEvaScore.substr(0, itemEvaScore.indexOf("-"));
				var max = itemEvaScore.substr(itemEvaScore.indexOf("-") + 1);
				var judgeScore = (parseInt(min) + parseInt(max)) / 2;
			} else {
				var judgeScore = itemEvaScore;
			}
			if (type == "已驳回") {
				document.getElementById("input-score").value = "无效";
			} else {
				if (itemPrizeObject == "个人") {
					document.getElementById("input-score").value = judgeScore;
				} else {
					document.getElementById("input-score").value = itemMemberScore;
				}

			}
		}
		//操作select给项目打分
		function putScore() {
			var putScore = document.getElementById("scoreList").value;
			document.getElementById("input-score").value = putScore;
		}
	</script>
</body>
</html>
