package cn.xidian.web.action;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.Course;
import cn.xidian.entity.GraduateRequirement;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.service.AdminCourseService;
import cn.xidian.service.MajorInformationService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value = "MajorAction")
@Scope("prototype")
public class MajorAction extends ActionSupport{

	private List<GraduateRequirement> requirements;
	private List<IndicatorPoint> points;
	private Integer reqId;
	
	private List<Course> courses = new LinkedList<Course>();
	
	private MajorInformationService majorInformationService;
	@Resource(name="majorInformationServiceImpl")
	public void setMajorInformationService(MajorInformationService majorInformationService) {
		this.majorInformationService = majorInformationService;
	}
	
	private AdminCourseService adminCourseService;
	@Resource(name="adminCourseServiceImpl")
	public void setAdminCourseService(AdminCourseService adminCourseService) {
		this.adminCourseService = adminCourseService;
	}
	
	
	public String selectMajorInf(){
		requirements = majorInformationService.selectGraReq();
		Set<Course> cs = adminCourseService.selectAllCurs();
		Iterator<Course> it = cs.iterator();
		while (it.hasNext()) {
			Course c = it.next();
			courses.add(c);
		}
		return "login";
	}
	

	public String selectPointByReqId(){
		points = majorInformationService.selectPointByReqId(reqId);
		return "login";
	}
	
	
	public List<GraduateRequirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<GraduateRequirement> requirements) {
		this.requirements = requirements;
	}
	public List<IndicatorPoint> getPoints() {
		return points;
	}
	public void setPoints(List<IndicatorPoint> points) {
		this.points = points;
	}

	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
