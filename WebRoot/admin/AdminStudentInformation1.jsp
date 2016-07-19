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

<title>教师信息</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/admin.css" />
<link rel="stylesheet" href="css/student_information.css" />
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
	function isEmpty() {
		var score = document.getElementById("input-score");
		var note = document.getElementById("input-note");

		if (score.value.toString().trim().length != 0
				&& note.value.toString().trim().length != 0) 
		{
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
	function numJudge() {
		var obj=document.getElementById("input-score").value;
		document.getElementById("input-score").value=obj.replace(/[^\d.]/g,'')
		var num=parseFloat(obj);
		if(num > 12.0){
		alert("分数应小于12！");
		document.getElementById("input-score").value="";
		}
	}
	//获取项目的编号
	function getItemId(obj) {
		var itemId = obj.id;
		document.getElementById("input-itemId").value = itemId;
	}
	//根据所选操作的类型的不同控制界面的显示
	function typeChange() {
		var type = document.getElementById("input-type").value;
		if (type == "已驳回") {
			document.getElementById("input-score").readOnly = true;
			document.getElementById("input-score").value = "0";
		
		} else {
			document.getElementById("input-score").readOnly = false;
			document.getElementById("input-score").value = "";
		}
	}
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
		<div class="container">
			<div class="row">
				<div class="span10 offset1">
					<div class="div-module">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="admin/AdminHome1.jsp">首页</a><img class="image-path-2" src="img/zhexian.jpg"/><a
							href="AdminStudent_List_1_selectAllStus">学生信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>学生详情
					</h6>
				</div>
							<!-- 学生基本信息 -->
							<div class="div-tchr-basic-inf">								
								<div class="div-inf-tbl">
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>学号</td>
											<td><s:property value="s.stuSchNum"/></td>
											<td>姓名</td>
											<td><s:property value="s.stuName"/></td>
											<td class="img-stu" rowspan=4><img
												src="stuImg/<s:property value="s.stuSchNum"/>.jpg" />
											</td>
										</tr>
										<tr>
											<td>性别</td>
											<td><s:if test="%{s.stuGender==true}">男</s:if> 
										<s:else>女</s:else></td>
											<td>生日</td>
											<td><s:property value="s.stuBirthday"/></td>
										</tr>
										<tr>
											<td>籍贯</td>
											<td><s:property value="s.stuNativePlace"/></td>
											<td>民族</td>
											<td><s:property value="s.stuNation"/></td>
										</tr>
										<tr>
											<td>身份证</td>
											<td><s:property value="s.stuIdentNum"/></td>
											<td>班级</td>
											<td><s:property value="s.clazz.claName"/></td>
										</tr>
										<tr>
											<td>入学日期</td>
											<td><s:property value="s.stuAttendDate"/></td>
											<td>学制</td>
											<td colspan=2><s:property value="s.stuSchLength"/></td>
										</tr>
										<tr>
											<td>手机</td>
											<td><s:property value="s.stuPhone"/></td>
											<td>宿舍电话</td>
											<td colspan=2><s:property value="s.stuDomiPhone"/></td>
										</tr>
										<tr>
											<td>邮箱</td>
											<td><s:property value="s.stuMail"/></td>
											<td>通信地址</td>
											<td colspan=2><s:property value="s.stuCommAddr"/></td>
										</tr>
									</tbody>
								</table>
							</div>
							</div>
							<div class="div-inf-tbl">
					<h5>获奖情况</h5>
					<div class="div-tchr-self-intr-content">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>项目编号</th>
									<th>项目名称</th>
									<th>审核状态</th>
									<th>得分</th>
									<th>审核</th>
									<th>查看</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="items" var="i">
									<tr>
										<td><s:property value="#i.itemNum" /></td>
										<td><s:property value="#i.itemName" /></td>
										<td><s:property value="#i.itemState" /></td>
										<td><s:property value="#i.itemScore" /></td>
										<td><a  data-toggle="modal" data-target="#myModal" id="<s:property value="#i.stuItemId" />" onclick="getItemId(this)">审核</a></td>
													<td><a href="Student_Award_Info_selectItemInfo?itemId=<s:property value="#i.stuItemId"/>">详情</a></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
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
								<form action="AdminStudent_Information_1_judgeStuItem"
									method="post" class="form-horizontal form-add"
									enctype="multipart/form-data"
									onsubmit="javascript:return isEmpty(1)">
									<div class="control-group">
										<label class="control-label">操作类型：</label>
										<div class="controls">
											<select id="input-type" name="item.itemState"
												onchange="typeChange()">
												<option value="已审核">项目审核</option>
												<option value="已驳回">项目驳回</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">项目评分：</label>
										<div class="controls">
											<input id="input-score" type="text" name="item.itemScore"
												onkeyup="numJudge()"
												placeholder="请输入數字：0~12">
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
							<div class="div-inf-tbl">
								<h5>自我介绍</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.selfIntroduce"/></label>
								</div>
							</div>
							<div class="div-inf-tbl">
								<h5>英文自我介绍</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.selfEngIntroduce"/></label>
								</div>
							</div>
							<!-- 学生基本信息完 -->
							<!-- 学生目标 -->
							<div class="div-inf-tbl">
								<h5>短期目标</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.shortGoal"/></label>
								</div>
								<h5>中期目标</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.midGoal"/></label>
								</div>
								<h5>长期目标</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.longGoal"/></label>
								</div>
							</div>
							<!-- 学生目标历完 -->
					</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
