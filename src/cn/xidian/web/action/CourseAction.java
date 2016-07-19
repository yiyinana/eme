package cn.xidian.web.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.CompositionRules;
import cn.xidian.entity.ContributeTarget;
import cn.xidian.entity.Course;
import cn.xidian.entity.CourseMaterial;
import cn.xidian.entity.CourseReferenceBooks;
import cn.xidian.entity.CourseSpecificContent;
import cn.xidian.entity.CourseTeachingMode;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.entity.TeachingTarget;
import cn.xidian.entity.User;
import cn.xidian.service.AdminCompositionRulesService;
import cn.xidian.service.AdminCourseContributeTargetValueService;
import cn.xidian.service.AdminCourseReferenceBooksService;
import cn.xidian.service.AdminCourseSpecificContentService;
import cn.xidian.service.AdminCourseTeachingModeService;
import cn.xidian.service.CourseMaterialService;
import cn.xidian.service.CoursePointService;
import cn.xidian.service.CourseService;
import cn.xidian.service.TeachingTargetService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value = "CourseAction")
@Scope("prototype")
public class CourseAction extends ActionSupport implements RequestAware,
		SessionAware {
	private Course course = new Course();
	private Integer cursId;
	private List<TeachingTarget> targets;
	private List<CourseSpecificContent> csc;
	private List<CourseTeachingMode> ctm;
	private List<CourseMaterial> cm;
	private List<CourseReferenceBooks> crb;
	private String note1;
	private String note2;
	private CompositionRules rules;
	private List<IndicatorPoint> indicatorPoints = new LinkedList<IndicatorPoint>();// 毕业要求指标点
	private List<ContributeTarget> contributeTargets;
		
	
	private Map<String, Object> request;
	private Map<String, Object> session;

	private CourseService courseService;

	@Resource(name = "courseServiceImpl")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String selectByCursId() {
		course = courseService.findById(cursId);
		session.put("cursId", cursId);
		return "course";
	}

	public String selectBasicInfByCursId() {
		Integer id = (Integer) session.get("cursId");
		if (id != null) {
			course = courseService.findById(id);
			return "course";
		} else {
				return "false";
		
		}
	}

	// 返回按钮
	public String goBack() {
		User user = (User)session.get("tUser");
		
		if(user != null){
			String identify = user.getIdentity().toString();
			if(identify.equals("ADMIN")){
				return "adminCourseList";
			}
			if(identify.equals("TEACHER")){
				return "teacherCourseList";
			}
		}
		return "false";
	}

	private TeachingTargetService teachingTargetService;

	@Resource(name = "teachingTargetServiceImpl")
	public void setTeachingTargetService(
			TeachingTargetService teachingTargetService) {
		this.teachingTargetService = teachingTargetService;
	}

	// 查找课程教学目标
	public String selectTchingTargetByCursId() {
		Integer id = (Integer) session.get("cursId");
		if (id != null) {
			course = courseService.findById(id);
			targets = teachingTargetService.selectByCursId(id);
			return "course";
		} else {
				return "false";
			}
	}

	private AdminCourseSpecificContentService adminCourseSpecificContentService;

	@Resource(name = "adminCourseSpecificContentServiceImpl")
	public void setAdminCourseSpecificContentService(
			AdminCourseSpecificContentService adminCourseSpecificContentService) {
		this.adminCourseSpecificContentService = adminCourseSpecificContentService;
	}

	// 查找课程具体内容
	public String selectSpeContentCursId() {
		Integer id = (Integer) session.get("cursId");
		if (id != null) {
			course = courseService.findById(id);
			csc = adminCourseSpecificContentService
					.selectCursSpecificContentByCursId(id);
			return "course";
		} else {
				return "false";
			}
	}

	private AdminCourseTeachingModeService adminCourseTeachingModeService;

	@Resource(name = "adminCourseTeachingModeServiceImpl")
	public void setAdminCourseTeachingModeService(
			AdminCourseTeachingModeService adminCourseTeachingModeService) {
		this.adminCourseTeachingModeService = adminCourseTeachingModeService;
	}
	// 查找教学安排
	public String selectTchingModeByCursId() {
		Integer id = (Integer) session.get("cursId");
		if (id != null) {
			course = courseService.findById(id);
			ctm = adminCourseTeachingModeService.selectTchingModeByCursId(id);
			return "course";
		} else {
				return "false";
			}
	}
	
	private AdminCompositionRulesService adminCompsitionRulesService;
	@Resource(name="adminCompositionRulesServiceImpl")
	public void setAdminCompositionRulesService(
			AdminCompositionRulesService adminCompsitionRulesService) {
		this.adminCompsitionRulesService = adminCompsitionRulesService;
	}
	private CoursePointService coursePointService;
	@Resource(name = "coursePointServiceImpl")
	public void setCoursePointService(CoursePointService coursePointService) {
		this.coursePointService = coursePointService;
	}
	private AdminCourseContributeTargetValueService adminCourseContributeTargetValueService;
	@Resource(name = "adminCourseContributeTargetValueServiceImpl")
	public void setAdminCourseContributeTargetValueService(AdminCourseContributeTargetValueService adminCourseContributeTargetValueService) {
		this.adminCourseContributeTargetValueService = adminCourseContributeTargetValueService;
	}
	//评分规则，目标与毕业要求指标点对应关系
	public String selectPerRuleByCursId(){
		Integer id = (Integer) session.get("cursId");
		if (id != null) {
			course = courseService.findById(id);
			rules = adminCompsitionRulesService.selectCompRulesByCursId(id);
			targets = teachingTargetService.selectByCursId(id);
			indicatorPoints = coursePointService.selectPointByCursId(id);
			contributeTargets = adminCourseContributeTargetValueService.selectByCursId(id);
			return "course";
		} else {
				return "false";
			}
	}
	
	
	private AdminCourseReferenceBooksService adminCourseReferenceBooksService;
	
	@Resource(name = "adminCourseReferenceBooksServiceImpl")
	public void setAdminCourseReferenceBooksService(
			AdminCourseReferenceBooksService adminCourseReferenceBooksService) {
		this.adminCourseReferenceBooksService = adminCourseReferenceBooksService;
	}
	private CourseMaterialService courseMaterialService;
	@Resource(name = "courseMaterialServiceImpl")
	public void setCourseMaterialService(CourseMaterialService courseMaterialService) {
		this.courseMaterialService = courseMaterialService;
	}
	//查找教材及参考书目
	public String selectBookByCursId() {
		Integer id = (Integer) session.get("cursId");
		if (id != null) {
			course = courseService.findById(id);
			cm = courseMaterialService.findByCursId(id);
			crb = adminCourseReferenceBooksService.selectReferenceBooks(id);
			return "course";
		} else {
				return "false";
		}
	}
	
	//查找课程说明
		public String selectNoteByCursId() {
			Integer id = (Integer) session.get("cursId");
			if (id != null) {
				course = courseService.findById(id);
				return "course";
			} else {
					return "false";
			}
		}
	
	
	public String excute() {
		return "course";
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
	
	

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}
	
	

	public CompositionRules getRules() {
		return rules;
	}

	public void setRules(CompositionRules rules) {
		this.rules = rules;
	}
	
	public List<IndicatorPoint> getIndicatorPoints() {
		return indicatorPoints;
	}

	public void setIndicatorPoints(List<IndicatorPoint> indicatorPoints) {
		this.indicatorPoints = indicatorPoints;
	}
	
	public List<ContributeTarget> getContributeTargets() {
		return contributeTargets;
	}

	public void setContributeTargets(List<ContributeTarget> contributeTargets) {
		this.contributeTargets = contributeTargets;
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
}
