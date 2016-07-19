package cn.xidian.web.action;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.Course;
import cn.xidian.entity.Department;
import cn.xidian.entity.Teacher;
import cn.xidian.exception.CourseExistsException;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.service.AdminCourseService;
import cn.xidian.service.AdminTeacherService;
import cn.xidian.service.CourseService;
import cn.xidian.service.DepartmentService;
import cn.xidian.web.bean.AdminCursLimits;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value = "AdminCourseAction")
@Scope("prototype")
public class AdminCourseAction extends ActionSupport implements RequestAware,
		SessionAware {
	private Course course = new Course();
	
	private List<Course> courses = new LinkedList<Course>();
	private List<Teacher> teachers = new LinkedList<Teacher>();
	private AdminCursLimits limits;
	private List<Department> departments;
	private Integer cursId;

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

	/* 添加课程基本信息 */
	public String addCursBasicInf() {
		try {
			course.setIsDelete(1);
			boolean isSuccess = courseService.addCursBasicInf(course);
			if (isSuccess) {
				request.put("Message", "课程基本信息设置成功！");
				//Course curs = courseService.findByNum(course.getCursNum());
				//session.put("courseId", curs.getCursId());
			} else {
				request.put("Message", "添加失败！");
			}
		} catch (CourseExistsException e) {
			request.put("Message", e.getMessage());
			return "adminAddCourse";
		}
		return "adminCourseList";
	}

	/* 修改课程基本信息 */
	public String updateCursBasicInf() {
		try {
			Integer cId = (Integer) session.get("cursId");
			course.setCursId(cId);
			courseService.updateByAdmin(course);
			departments = departmentService.selectAllDepts();
			teachers = adminTeacherService.selectAllTchrs();
			request.put("Message", "更新课程基本信息成功！");
		} catch (CourseNotExistException e) {
			request.put("Message", e.getMessage());
		}
		return "admin";
	}

	private AdminCourseService adminCourseService;

	@Resource(name = "adminCourseServiceImpl")
	public void setAdminCourseService(AdminCourseService adminCourseService) {
		this.adminCourseService = adminCourseService;
	}

	/* 调出所有课程 */
	public String selectAllCurs() {
		Set<Course> cs = adminCourseService.selectAllCurs();
		Iterator<Course> it = cs.iterator();
		while (it.hasNext()) {
			Course c = it.next();
			courses.add(c);
		}
		return "admin";
	}

	/* 按条件查找课程 */
	public String searchCourses() {
		Set<Course> cs = adminCourseService.selectCursLimits(limits);
		Iterator<Course> it = cs.iterator();
		while (it.hasNext()) {
			courses.add(it.next());
		}

		if (courses.size() == 0) {
			this.addActionError("对不起，未找到相关信息！");
		}
		return "admin";
	}


	// 删除课程
	public String deleteByCursId() {
		boolean isSuccess = adminCourseService.deleteByCursId(cursId);
		if (isSuccess)
			request.put("Message", "删除成功！");
		else
			request.put("Message", "对不起，删除失败！");

		return "adminCourseList";
	}

	public String selectByCursId() {
		course = courseService.findById(cursId);
		teachers = adminTeacherService.selectAllTchrs();
		departments = departmentService.selectAllDepts();
		session.put("cursId", cursId);
		return "admin";
	}

	public String selectByCursId1() {
		Integer cId = (Integer) session.get("cursId");
		course = courseService.findById(cId);
		departments = departmentService.selectAllDepts();
		return "admin";
	}
	
	private AdminTeacherService adminTeacherService;

	@Resource(name = "adminTeacherServiceImpl")
	public void setAdminTeacherService(AdminTeacherService adminTeacherService) {
		this.adminTeacherService = adminTeacherService;
	}
	public String selectDeptmentAndThr() {
		teachers = adminTeacherService.selectAllTchrs();
		departments = departmentService.selectAllDepts();
		return "admin";
	}



	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public AdminCursLimits getLimits() {
		return limits;
	}

	public void setLimits(AdminCursLimits limits) {
		this.limits = limits;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Integer getCursId() {
		return cursId;
	}

	public void setCursId(Integer cursId) {
		this.cursId = cursId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String excute() {
		return "admin";
	}
}
