package cn.xidian.web.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.CompositionRules;
import cn.xidian.entity.ContributeTarget;
import cn.xidian.entity.Course;
import cn.xidian.entity.CourseMaterial;
import cn.xidian.entity.CoursePoint;
import cn.xidian.entity.CourseReferenceBooks;
import cn.xidian.entity.CourseSpecificContent;
import cn.xidian.entity.CourseTeachingMode;
import cn.xidian.entity.Department;
import cn.xidian.entity.GradeCoursePoint;
import cn.xidian.entity.GraduateRequirement;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.entity.MajorTargetValue;
import cn.xidian.entity.TeachingTarget;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.exception.GradeCoursePointNotExistException;
import cn.xidian.service.AdminCompositionRulesService;
import cn.xidian.service.AdminCourseContributeTargetValueService;
import cn.xidian.service.AdminCourseReferenceBooksService;
import cn.xidian.service.AdminCourseSpecificContentService;
import cn.xidian.service.AdminCourseTeachingModeService;
import cn.xidian.service.CourseMaterialService;
import cn.xidian.service.CoursePointService;
import cn.xidian.service.CourseService;
import cn.xidian.service.DepartmentService;
import cn.xidian.service.GradeCoursePointService;
import cn.xidian.service.GraduateRequirementService;
import cn.xidian.service.IndicatorPointService;
import cn.xidian.service.MajorTargetValueService;
import cn.xidian.service.TeachingTargetService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value = "TeacherCourseAction")
@Scope("prototype")
public class TeacherCourseAction extends ActionSupport implements RequestAware,
		SessionAware {
	private Course course = new Course();
	private Integer cursId;
	private List<Department> departments;
	private List<TeachingTarget> targets = new LinkedList<TeachingTarget>();
	private List<CourseSpecificContent> csc = new LinkedList<CourseSpecificContent>();
	private List<CourseTeachingMode> ctm;
	private CompositionRules compositionRules = new CompositionRules();
	private List<CourseReferenceBooks> crb = new LinkedList<CourseReferenceBooks>();
	private List<CourseMaterial> cm = new LinkedList<CourseMaterial>();
	private List<List<IndicatorPoint>> indicatorPoints = new LinkedList<List<IndicatorPoint>>();// 毕业要求指标点
	private List<IndicatorPoint> indicatorPoint = new LinkedList<IndicatorPoint>();// 毕业要求指标点
	private List<GraduateRequirement> requirement = new LinkedList<GraduateRequirement>();// 毕业要求指标点
	private List<ContributeTarget> contributeTargets = new LinkedList<ContributeTarget>();
	private List<CoursePoint> coursePoints = new LinkedList<CoursePoint>();
	private Integer indPointId;
	private String grade;
	private List<GradeCoursePoint> gradeCoursePoints = new LinkedList<GradeCoursePoint>();
	private List<MajorTargetValue> majorTargetValues = new LinkedList<MajorTargetValue>();
	private Set<Course> courses;
	

	private Map<String, Object> request;
	private Map<String, Object> session;

	private CourseService courseService;

	@Resource(name = "courseServiceImpl")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	private DepartmentService departmentService;

	@Resource(name = "departmentServiceImpl")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private TeachingTargetService teachingTargetService;

	@Resource(name = "teachingTargetServiceImpl")
	public void setTeachingTargetService(
			TeachingTargetService teachingTargetService) {
		this.teachingTargetService = teachingTargetService;
	}

	private AdminCompositionRulesService adminCompositionRulesService;

	@Resource(name = "adminCompositionRulesServiceImpl")
	public void setAdminCompositionRulesService(
			AdminCompositionRulesService adminCompositionRulesService) {
		this.adminCompositionRulesService = adminCompositionRulesService;
	}

	private AdminCourseSpecificContentService adminCourseSpecificContentService;

	@Resource(name = "adminCourseSpecificContentServiceImpl")
	public void setAdminCourseSpecificContentService(
			AdminCourseSpecificContentService adminCourseSpecificContentService) {
		this.adminCourseSpecificContentService = adminCourseSpecificContentService;
	}
	
	private MajorTargetValueService majorTargetValueService;

	@Resource(name = "majorTargetValueServiceImpl")
	public void setMajorTargetValueService(
			MajorTargetValueService majorTargetValueService) {
		this.majorTargetValueService = majorTargetValueService;
	}

	private AdminCourseTeachingModeService adminCourseTeachingModeService;

	@Resource(name = "adminCourseTeachingModeServiceImpl")
	public void setAdminCourseTeachingModeService(
			AdminCourseTeachingModeService adminCourseTeachingModeService) {
		this.adminCourseTeachingModeService = adminCourseTeachingModeService;
	}

	private IndicatorPointService indicatorPointService;

	@Resource(name = "indicatorPointServiceImpl")
	public void setIndicatorPointService(
			IndicatorPointService indicatorPointService) {
		this.indicatorPointService = indicatorPointService;
	}

	private AdminCourseContributeTargetValueService adminCourseContributeTargetValueService;

	@Resource(name = "adminCourseContributeTargetValueServiceImpl")
	public void setAdminCourseContributeTargetValueService(
			AdminCourseContributeTargetValueService adminCourseContributeTargetValueService) {
		this.adminCourseContributeTargetValueService = adminCourseContributeTargetValueService;
	}

	private CoursePointService coursePointService;

	@Resource(name = "coursePointServiceImpl")
	public void setCoursePointService(CoursePointService coursePointService) {
		this.coursePointService = coursePointService;
	}
	
	private GradeCoursePointService gradeCoursePointService;

	@Resource(name = "gradeCoursePointServiceImpl")
	public void setGradeCoursePointService(GradeCoursePointService gradeCoursePointService) {
		this.gradeCoursePointService = gradeCoursePointService;
	}

	private AdminCourseReferenceBooksService adminCourseReferenceBooksService;

	@Resource(name = "adminCourseReferenceBooksServiceImpl")
	public void setAdminCourseReferenceBooksService(
			AdminCourseReferenceBooksService adminCourseReferenceBooksService) {
		this.adminCourseReferenceBooksService = adminCourseReferenceBooksService;
	}

	private CourseMaterialService courseMaterialService;

	@Resource(name = "courseMaterialServiceImpl")
	public void setCourseMaterialService(
			CourseMaterialService courseMaterialService) {
		this.courseMaterialService = courseMaterialService;
	}

	// 选择、修改课程基本信息
	public String selectByCursId() {
		if (cursId == null) {
			cursId = (Integer) session.get("cursId");
		}
		course = courseService.findById(cursId);
		departments = departmentService.selectAllDepts();
		session.put("cursId", cursId);
		return "teacher";
	}

	public String updateCursBasicInf() {
		try {
			Integer cId = (Integer) session.get("cursId");
			course.setCursId(cId);
			courseService.updateByTchr(course);
			departments = departmentService.selectAllDepts();
			request.put("Message", "更新课程基本信息成功！");
		} catch (CourseNotExistException e) {
			request.put("Message", e.getMessage());
		}
		return "teacher";
	}

	// 修改课程目标，先查再改
	public String selectTargetByCursId() {
		Integer cId = (Integer) session.get("cursId");
		course = courseService.findById(cId);
		compositionRules = adminCompositionRulesService
				.selectCompRulesByCursId(cId);// 待开发
		targets = teachingTargetService.selectByCursId(cId);// 4.27，课程大纲修改与评价设计修改分开
		return "teacher";
	}

	public String selectTarByCursId() {
		session.put("cursId", cursId);
		compositionRules = adminCompositionRulesService
				.selectCompRulesByCursId(cursId);// 待开发
		course = courseService.findById(cursId);
		targets = teachingTargetService.selectByCursId(cursId);// 4.27，课程大纲修改与评价设计修改分开
		return "teacher";
	}

	public String modifyTargetByCursId() {
		Integer cId = (Integer) session.get("cursId");
		course = courseService.findById(cId);
		if (cId != null) {
			compositionRules = adminCompositionRulesService
					.selectCompRulesByCursId(cId);// 待开发
			boolean isSuccess = teachingTargetService.updateByCursId(targets,
					cId);
			if (isSuccess) {
				request.put("Message", "更新成功！");
			} else
				request.put("Message", "更新失败！");
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	public String modifyTargetValueByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			boolean isSuccess1 = adminCompositionRulesService.updateByCursId(
					compositionRules, cId);
			boolean isSuccess2 = teachingTargetService.updateValue(targets);
			if (isSuccess1 && isSuccess2) {
				request.put("Message", "更新成功！");
			} else
				request.put("Message", "更新失败！");
			course = courseService.findById(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}

	}

	// 修改课程具体内容，先查再改
	public String selectSpeContentByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			csc = adminCourseSpecificContentService
					.selectCursSpecificContentByCursId(cId);
			course = courseService.findById(cId);
			targets = teachingTargetService.selectByCursId(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	public String modifySpcConByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			boolean isSuccess = adminCourseSpecificContentService
					.updateByCursId(csc, cId);
			targets = teachingTargetService.selectByCursId(cId);
			if (isSuccess) {
				request.put("Message", "更新成功！");
			} else
				request.put("Message", "更新失败！");
			course = courseService.findById(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	// 修改课程教学方式，先查再改
	public String selectTchModeByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			course = courseService.findById(cId);
			ctm = adminCourseTeachingModeService.selectTchingModeByCursId(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	public String modifyTchModeByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			boolean isSuccess = adminCourseTeachingModeService.updateByCursId(
					ctm, cId);
			if (isSuccess) {
				request.put("Message", "更新成功！");
			} else
				request.put("Message", "更新失败！");
			course = courseService.findById(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	private GraduateRequirementService graduateRequirementService;

	@Resource(name = "graduateRequirementServiceImpl")
	public void setGraduateRequirementService(
			GraduateRequirementService graduateRequirementService) {
		this.graduateRequirementService = graduateRequirementService;
	}

	// 修改课程指标点，先查再改
	public String selectAllPoints() {
		indicatorPoints = indicatorPointService.selectAllIndPoint();
		requirement = graduateRequirementService.selectAll();
		if(cursId != null){
			session.put("cursId", cursId);
			course = courseService.findById(cursId);
		}
		return "teacher";
	}
	
	public String selectTargetAndPointById() {
		Integer cId = Integer.parseInt(session.get("cursId").toString());
		if (cId != null) {
			targets = teachingTargetService.selectByCursId(cId);
			coursePointService.modifyByCursId(indicatorPoint, cId);
			course = courseService.findById(cId);
			for (int i = 0; i < indicatorPoint.size(); i++) {
				IndicatorPoint iPoint = indicatorPointService
						.selectIndPointById(indicatorPoint.get(i)
								.getIndPointId());
				indicatorPoint.set(i, iPoint);
			}
			indicatorPoints = indicatorPointService.selectAllIndPoint();// 4.24，课程大纲修改和课程评价设计修改分开
			contributeTargets = adminCourseContributeTargetValueService.selectByCursId(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	public String modifyPointByCursId() {
		Integer cId = Integer.parseInt(session.get("cursId").toString());
		if (cId != null) {
			boolean isSuccess = adminCourseContributeTargetValueService
					.updateContributeTargetValue(contributeTargets, cId);
			if (isSuccess) {
				request.put("Message", "保存成功！");
			} else {
				request.put("Message", "保存失败！");
			}
			course = courseService.findById(cId);
			targets = teachingTargetService.selectByCursId(cId);// 4.27，课程大纲和课程评价设计分开
			indicatorPoint = coursePointService.selectPointByCursId(cId);
			contributeTargets = adminCourseContributeTargetValueService.selectByCursId(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	// 修改课程教材和参考书目，先查再改
	public String selectBookByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			// material = courseService.findById(cId).getCursMaterial();
			course = courseService.findById(cId);
			cm = courseMaterialService.findByCursId(cId);
			crb = adminCourseReferenceBooksService.selectReferenceBooks(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	public String modifyBookByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			boolean isSuccess1 = adminCourseReferenceBooksService
					.updateByCursId(crb, cId);
			boolean isSuccess2 = courseMaterialService.updateMaterialByCursId(
					cm, cId);
			course = courseService.findById(cId);
			if (isSuccess1 && isSuccess2) {
				request.put("Message", "更新成功！");
			} else
				request.put("Message", "更新失败！");
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	// 修改课程说明，先查再改
	public String selectNoteByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			
			course = courseService.findById(cId);
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}

	public String modifyNoteByCursId() {
		Integer cId = (Integer) session.get("cursId");
		if (cId != null) {
			course.setCursId(cId);
			boolean isSuccess = courseService.updateCourseNote(course);
			
			if (isSuccess) {
				request.put("Message", "更新成功！");
			} else
				request.put("Message", "更新失败！");
			return "teacher";
		} else {
			return "teacherCourseList";
		}
	}
	
	

	public String selectGradeCursPointByPointIdAndGrade() {
		gradeCoursePoints = gradeCoursePointService.selectByPointIdAndGrade(indPointId,grade);
		if(gradeCoursePoints.size()==0){
			request.put("Message", "对不起，没有找到相关记录");
		}
		return "teacherManager1";
	}

	public String updateCursPowerByGradeCursPointIdAndGrade() {
		indPointId = gradeCoursePoints.get(0).getPoint().getIndPointId();
		boolean isSuccess = gradeCoursePointService.updateCursPowerByGradeCursPointIdAndGrade(gradeCoursePoints, grade);
		if (isSuccess) {
			request.put("Message", "评价成功");
		} else {
			request.put("Message", "评价失败");
		}
		return selectGradeCursPointByPointIdAndGrade();
	}
	
	//查找所有已经完成评价的课程
	public String selectEvaluatedCursByYear() {
		try {
			courses = gradeCoursePointService.selectByGrade(grade);
		} catch (GradeCoursePointNotExistException e) {
			request.put("Message", e.getMessage());
		}
		return "teacher";
	}
	
	//查找所有已经完成评价的指标点
	public String selectEvaluatedPointByYear() {
		majorTargetValues = majorTargetValueService.selectByGrade(grade);
		List<GradeCoursePoint> gcp = new LinkedList<GradeCoursePoint>();
		for (int i = 0; i < majorTargetValues.size(); i++) {
			gcp = gradeCoursePointService.selectByPointIdAndGrade(majorTargetValues.get(i).getPoint().getIndPointId(), grade);
			gradeCoursePoints.addAll(gcp);
		}
		return "teacher";
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getCursId() {
		return cursId;
	}

	public void setCursId(Integer cursId) {
		this.cursId = cursId;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<TeachingTarget> getTargets() {
		return targets;
	}

	public void setTargets(List<TeachingTarget> targets) {
		this.targets = targets;
	}

	public List<CourseSpecificContent> getCsc() {
		return csc;
	}

	public void setCsc(List<CourseSpecificContent> csc) {
		this.csc = csc;
	}

	public List<CourseTeachingMode> getCtm() {
		return ctm;
	}

	public void setCtm(List<CourseTeachingMode> ctm) {
		this.ctm = ctm;
	}

	public CompositionRules getCompositionRules() {
		return compositionRules;
	}

	public void setCompositionRules(CompositionRules compositionRules) {
		this.compositionRules = compositionRules;
	}

	public List<CourseMaterial> getCm() {
		return cm;
	}

	public void setCm(List<CourseMaterial> cm) {
		this.cm = cm;
	}

	public List<CourseReferenceBooks> getCrb() {
		return crb;
	}

	public void setCrb(List<CourseReferenceBooks> crb) {
		this.crb = crb;
	}

	public List<List<IndicatorPoint>> getIndicatorPoints() {
		return indicatorPoints;
	}

	public void setIndicatorPoints(List<List<IndicatorPoint>> indicatorPoints) {
		this.indicatorPoints = indicatorPoints;
	}

	public List<IndicatorPoint> getIndicatorPoint() {
		return indicatorPoint;
	}

	public void setIndicatorPoint(List<IndicatorPoint> indicatorPoint) {
		this.indicatorPoint = indicatorPoint;
	}

	public List<GraduateRequirement> getRequirement() {
		return requirement;
	}

	public void setRequirement(List<GraduateRequirement> requirement) {
		this.requirement = requirement;
	}

	public List<ContributeTarget> getContributeTargets() {
		return contributeTargets;
	}

	public void setContributeTargets(List<ContributeTarget> contributeTargets) {
		this.contributeTargets = contributeTargets;
	}

	public List<CoursePoint> getCoursePoints() {
		return coursePoints;
	}

	public void setCoursePoints(List<CoursePoint> coursePoints) {
		this.coursePoints = coursePoints;
	}

	public Integer getIndPointId() {
		return indPointId;
	}

	public void setIndPointId(Integer indPointId) {
		this.indPointId = indPointId;
	}
	

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	

	public List<GradeCoursePoint> getGradeCoursePoints() {
		return gradeCoursePoints;
	}

	public void setGradeCoursePoints(List<GradeCoursePoint> gradeCoursePoints) {
		this.gradeCoursePoints = gradeCoursePoints;
	}
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public List<MajorTargetValue> getMajorTargetValues() {
		return majorTargetValues;
	}

	public void setMajorTargetValues(List<MajorTargetValue> majorTargetValues) {
		this.majorTargetValues = majorTargetValues;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String excute() {
		return "teacher";
	}

}
