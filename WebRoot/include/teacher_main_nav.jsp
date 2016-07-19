<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
	<div class="navbar navbar-static-top">
			<div class="navbar-inner nav-main">
				<div class="div-brand">
					<a class="brand">机械制造及其自动化</a></div>
				<ul class="nav">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">个人资料&nbsp;</a>
						<ul class="dropdown-menu">
							<li><a href="Teacher_Information_1_selectInfByNum">基本资料</a></li>
							<!-- <li><a href="Teacher_Information_2_selectAward">资格荣誉</a></li> -->
							<li><a href="Teacher_Information_Modify_selectInfByNum">编辑个人资料</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">教学管理&nbsp;</a>
						<ul class="dropdown-menu">
							<li><a href="Teacher_Management_1_selectTchrCourse">课程查询</a></li> 
							<li><a href="Teacher_Management_2_selectTchrCourse">课程大纲管理</a></li>
							<li><a href="Teacher_Management_3_selectTchrCourse">课程评价设计</a></li>
							<li><a href="Teacher_Management_4_excute">课程成绩录入</a></li>
							<li><a href="Teacher_Management_5_excute">课程达成评价</a></li>
						</ul>
					</li>
					<s:if test="#session.tUser.isManager==1">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">达成评价&nbsp;</a>
						<ul class="dropdown-menu">
							<li><a href="TeacherCourse_Manager_0_excute">课程评价查询</a></li>
							<li><a href="TeacherCourse_Manager_1_selectAllPoints">专业达成评价</a></li>
							<li><a href="TeacherCourse_Manager_2_excute">学年达成度查询</a></li>
						</ul>
					</li>
					</s:if>
					
					<s:if test="#session.tUser.isCounselor==1">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">学生管理&nbsp;</a>
						<ul class="dropdown-menu">
							<li><a href="TeacherStudent_List_1_selectChargeStus">学生项目管理评估</a></li>
							<li><a href="TeacherStudent_Eva_Summary_selectChargeStus">学生项目评估汇总</a></li>
							
						</ul>
					</li>
					</s:if>
				</ul>
			</div>
		</div>