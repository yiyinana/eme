<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>登陆页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/login.css" />

<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
	function isNull() {
		var userName = document.getElementById("inputName").value;
		var password = document.getElementById("inputPassword").value;
		if (userName == "" || password == "") {
			alert("用户名或密码不能为空！");
			return false;
		} else
			return true;
	}
</script>
</head>

<body>
	<div class="row-fluid">
		<div class="span7">
			<img class="schoolLogoImg" alt="logo" src="img/homelogo.png">
		</div>
		<div class="span5">
			<img class="sysNameImg" alt="logo" src="img/sysName.png">
		</div>
	</div>

	<div class="row-fluid">
		<!--左边-->
		<div class="span8" id="leftDiv">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab1" data-toggle="tab">专业介绍</a>
					</li>
					<li><a href="#tab2" data-toggle="tab">培养目标</a></li>
					<li><a href="#tab3" data-toggle="tab">课程体系</a></li>
					<li><a href="#tab4" data-toggle="tab">毕业要求</a></li>
					<li><a href="#tab5" data-toggle="tab">达成成果</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="tab1">
						<div class="div-major-inf">
							<h5>机械设计制造及其自动化</h5>
							<p class="major-inf-title">Mechanical Design, Manufacturing
								and Automation</p>
							<p class="major-inf-title">&nbsp;&nbsp;&nbsp;</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“机械设计制造及其自动化专业”的前身是创建于1960年我校雷达技术系的“雷达机械结构与工艺专业”，
								1963年更名为“无线电设备结构计与工艺专
								业”，这是国内最早建立以机为主、电结合的交叉与边缘学科专之一。1979年学校成立机械电子系后以“无线电设备结构与工艺专业”名称招生，
								1999年学校组建机电工程学院，根据国家教育部专业目录调整，专业更名为“机械设计制造及其自动化专业”。</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本专业分别于1986年和1996年被电子部、陕西省和信息产业部遴选为部、省级重点学科，2005年被
								评为“陕西省名牌专业”，2008年入选为省级特色专业，2010年入选为国家级特色专业，2014年“机械设计制造及其自动化专业”遴选为省级“专业综合改革试点”项目。</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本专业所属的学科基础厚重，现拥有机械工程一级学科的博士后科研流动站和机械制造及其自动化、机械
								电子工程、机械设计及理论等博士点、硕士点；建有“电子装备结构设计”教育部重点实验室、“电子装备机电耦合基础理论与关键技术”111基地。本专业所属学科中“先进电子机械工程”是我
								校“2011协同创新计划”、“985优势学科创新平台”、和“211工程”重点建设的学科之一。近年来，学院承担了多项国际合作、“973”项目、“863计划”、国家自然科学基金重大、重点项目、
								国防预研等课题。并获得国家科技进步二等奖3项，省部级科学技术奖10余项。</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本专业师资力量雄厚，现有全职教师54人，其中院士2人，国家杰出青年基金获得者1人，973项目首席科学家1人，
								教育部新世纪优秀人才2人，教育部教指委委员2人，洪堡学者2人。专任教师中，教授16人、副教授及高工30人、博士生导师13人，硕士生导师26人。</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支撑本专业实验教学有西安电子科技大国家级综合性工程训练实验教学示范中心、电工子实验教学和省级物理示范中
								心、计算机基础教学实验中心 。本专业目前建有陕西省实验教学示范中心1个（机械电子工程教学中心），拥有实验使用面积 1440
								平方米，仪器、设备台套数840台（套），总资产 800 余万元，开 设工程力学、机械计等专业课程 70 余项实验项目。</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目前在校就读本专业本科生
								777 名，近五年本专业学生共有 105 人在美国大学生数建模竞赛、全国大学生电子竞赛、 机械创新设计等活动中获国际竞赛一等奖
								1项、二等奖 1项；国家级学科竞赛一等奖 5项、二等奖 2项；省级学科竞赛特等奖 1项、一等奖 13 项、二等奖 17
								项、三等奖 8项。</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;截至2015年，本专业培养了各层次毕业生6000余名，毕业生的就率保持在98%左右，多数毕业生在跨国公司、
								国家重点企事单位从技术和管理工作，本专业已成为国内电子信息行业知名度较高、颇有影响的科学研究和教学人才重要培养基地。</p>
						</div>
					</div>
					<div class="tab-pane" id="tab2">
						<div class="div-major-inf">
							<h5>机械设计制造及其自动化专业的培养目标为：</h5>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;旨在培养满足创新型国家发展需要、基础知识厚实、
								工程践能力强、有组织能力和国际视野的机械领域创新型人才，能从事机械设计制造及自动化领域中的设计制造、科学研究、应用开发、运行管理和经营销售等方面能力的机电一体化复合型高级人才。</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过3-5年实际工作的锻炼，期望达到：</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）良好的社会责任感、职业道德水准和敬精神，有意愿能力服务国家社会；</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）较强的工程创新能力，成为科研院所及企事业单位中坚量和务骨干；</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）能够独立承担复杂机电产品和装备的设计、制造、测试、研发等工作；</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（4）能够在团队协作中作为初级领导或者重要成员有效地发挥作用；</p>
							<p class="major-inf-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（5）具有广阔的国际视野，能够通过继续教育或其他学习途径拓展自己的知识或能力。</p>
						</div>
					</div>
					<div class="tab-pane" id="tab3">
						<div class="div-major-inf">
							<h5>机械设计制造及其自动化专业毕业生应修满174+22个学分。</h5>
							<table class="table table-bordered">
								<tr>
									<td class="classify"><p>公共基础课</p>
										<p>共69.5个学分</p>
										<p>应修69.5个学分</p></td>
									<td class="course"><s:iterator
											value="courses.{?#this.cursProperty=='公共基础课'}" var="c">
											<a
												href="Course_Detail_1_selectByCursId?cursId=<s:property value="#c.cursId"/>"><s:property
													value="#c.cursName" /></a>
										</s:iterator></td>
								</tr>
								<tr>
									<td><p>学科基础课</p>
										<p>共42.5个学分</p>
										<p>应修42.5个学分</p></td>
									<td class="course"><s:iterator
											value="courses.{?#this.cursProperty=='学科基础课'}" var="c">
											<a
												href="Course_Detail_1_selectByCursId?cursId=<s:property value="#c.cursId"/>"><s:property
													value="#c.cursName" /></a>
										</s:iterator></td>
								</tr>
								<tr>
									<td><p>专业核心课</p>
										<p>共16个学分</p>
										<p>应修10个学分</p></td>
									<td class="course"><s:iterator
											value="courses.{?#this.cursProperty=='专业核心课'}" var="c">
											<a
												href="Course_Detail_1_selectByCursId?cursId=<s:property value="#c.cursId"/>"><s:property
													value="#c.cursName" /></a>
										</s:iterator></td>
								</tr>
								<tr>
									<td><p>专业选修课</p>
										<p>共52个学分</p>
										<p>应修22个学分</p></td>
									<td class="course"><s:iterator
											value="courses.{?#this.cursProperty=='专业选修课'}" var="c">
											<a
												href="Course_Detail_1_selectByCursId?cursId=<s:property value="#c.cursId"/>"><s:property
													value="#c.cursName" /></a>
										</s:iterator></td>
								</tr>
								<tr>
									<td><p>实习、实践类课程</p>
										<p>共30个学分</p>
										<p>应修30个学分</p></td>
									<td class="course"><s:iterator
											value="courses.{?#this.cursProperty=='实习、实践类课程'}" var="c">
											<a
												href="Course_Detail_1_selectByCursId?cursId=<s:property value="#c.cursId"/>"><s:property
													value="#c.cursName" /></a>
										</s:iterator></td>
								</tr>
								<tr>
									<td><p>基础素质培养课</p>
										<p>共23个学分</p>
										<p>应修22个学分</p></td>
									<td class="course"><s:iterator
											value="courses.{?#this.cursProperty=='基础素质培养课'}" var="c">
											<a
												href="Course_Detail_1_selectByCursId?cursId=<s:property value="#c.cursId"/>"><s:property
													value="#c.cursName" /></a>
										</s:iterator></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="tab-pane" id="tab4">
						<div class="div-major-inf">
							<h5>机械设计制造及其自动化专业的毕业要求为：</h5>
							<s:iterator value="requirements" var="r" status="s">
								<p class="major-inf-content">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（
									<s:property value="#s.index+1" />
									）
									<s:property value="#r.graReqTitle" />
									：
									<s:property value="#r.graReqContent" />
								</p>
							</s:iterator>
						</div>
					</div>
					<div class="tab-pane" id="tab5">
					<div class="div-major-inf">
						<p>
							<a href="target2011.html">2011级</a>
						</p>
						<p>
							<a href="target2010.html">2010级</a>
						</p>
						<p>
							<a href="target2009.html">2009级</a>
						</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 右边登录窗口 -->
		<div class="span4" id="rightDiv">
			<div class="row">
				<div class="span8 offset3" id="loginDiv">
					<div class="loginLblDiv">
						<label class="loginLbl">用户登录</label>
					</div>
					<form class="form-horizontal" id="loginForm" name="loginForm"
						action="loginAction" onsubmit="javascript:return isNull()"
						method="post">
						<div class="control-group">
							<label class="control-label" for="inputName">用户名：</label>
							<div class="controls">
								<input class="small-input" type="text" id="inputName"
									name="user.schNum" placeholder="用户名">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">密&nbsp;&nbsp;&nbsp;码：</label>
							<div class="controls">
								<input class="small-input" type="password" id="inputPassword"
									name="user.pwd" placeholder="密码">
							</div>
						</div>
						<div class="control-group">
							<label class="lable-identy"><input name="identity"  checked="checked"
								type="radio" value="teacher" />教师 </label> <label class="lable-identy"><input
								name="identity" type="radio" value="student" />学生
							</label>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="submit" class="btn" value="登录"> <input
									type="submit" class="btn" value="忘记密码">
							</div>
						</div>
					</form>
					<hr />
					<div class="loginNoteDiv">
						<p>
							如提示“账号”或“密码”错误，请填写账号、姓名、电话、身份证号、发邮件到信息处邮箱。 <br>
						<address>信息处邮箱：xdxxc@mail.xidian.edu.cn</address>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery1.12.1.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>
