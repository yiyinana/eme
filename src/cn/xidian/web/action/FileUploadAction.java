package cn.xidian.web.action;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.xidian.entity.Clazz;
import cn.xidian.entity.Course;
import cn.xidian.entity.User;
import cn.xidian.exception.ClazzNotExistException;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.exception.CursOrTermNotMatchException;
import cn.xidian.exception.CursRulesNotExistException;
import cn.xidian.exception.StudentNotExistException;
import cn.xidian.exception.TchingTargetNotExistException;
import cn.xidian.exception.TeacherNotExistException;
import cn.xidian.service.ClazzCoursePointService;
import cn.xidian.service.ClazzService;
import cn.xidian.service.StudentPerformanceHandleService;
import cn.xidian.service.TeacherCourseService;
import cn.xidian.service.TeachingTargetEvaluateService;
import cn.xidian.service.TeachingTargetService;
import cn.xidian.utils.GetTermUtils;
import cn.xidian.web.bean.B1;
import cn.xidian.web.bean.B2;
import cn.xidian.web.service.UploadActionService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileUploadAction extends ActionSupport implements RequestAware,
		SessionAware {
	/* 上传文件存放的目录 */
	private String uploadUrl;
	private String term = GetTermUtils.getCurrentTerm();
	private String course;
	private String clazz;
	private String fileName;
	private List<Clazz> allClazz;
	private Map<String, Object> request;
	private Map<String, Object> session;

	/* 上传文件的集合 */
	private File file; // 必须与表单元素的NAME属性值一致

	private List<B1> claCursB1s = new LinkedList<B1>();
	private List<B2> claCursB2s = new LinkedList<B2>();
	private List<Course> tchrCourses;

	private StudentPerformanceHandleService studentPerformanceHandleService;

	@Resource(name = "studentPerformanceHandleServiceImpl")
	public void setStudentPerformanceHandleService(
			StudentPerformanceHandleService studentPerformanceHandleService) {
		this.studentPerformanceHandleService = studentPerformanceHandleService;
	}

	TeachingTargetService teachingTargetService;

	@Resource(name = "teachingTargetServiceImpl")
	public void setTeachingTargetService(
			TeachingTargetService teachingTargetService) {
		this.teachingTargetService = teachingTargetService;
	}
	
	private TeacherCourseService teacherCourseService;

	@Resource(name = "teacherCourseServiceImpl")
	public void setTeacherCourseService(
			TeacherCourseService teacherCourseService) {
		this.teacherCourseService = teacherCourseService;
	}
	
	private ClazzService clazzService;

	@Resource(name = "clazzServiceImpl")
	public void setClazzService(ClazzService clazzService) {
		this.clazzService = clazzService;
	}

	TeachingTargetEvaluateService teachingTargetEvaluateService;

	@Resource(name = "teachingTargetEvaluateServiceImpl")
	public void setTeachingTargetEvaluateService(
			TeachingTargetEvaluateService teachingTargetEvaluateService) {
		this.teachingTargetEvaluateService = teachingTargetEvaluateService;
	}

	ClazzCoursePointService clazzCoursePointService;

	@Resource(name = "clazzCoursePointServiceImpl")
	public void setClazzCoursePointService(
			ClazzCoursePointService clazzCoursePointService) {
		this.clazzCoursePointService = clazzCoursePointService;
	}

	public String uploadFile() {

		String realPath = ServletActionContext.getServletContext().getRealPath(
				"");

		UploadActionService uas = new UploadActionService();
		try {
			String filename;
			int index1 = fileName.lastIndexOf(".");
			int index2 = fileName.length();
			String postf = fileName.substring(index1 + 1, index2);
			if (postf.equals("xlsx")) {
				filename = term + "_" + course + "_" + clazz + ".xlsx";
			} else
				filename = term + "_" + course + "_" + clazz + ".xls";
			uas.upload(file, uploadUrl, filename); // 自定义方法调用
			
			studentPerformanceHandleService.handleExcel(realPath + "/excel/"
					+ filename);
			request.put("Message", "上传成功");
			
		} catch (CourseNotExistException e) {
			request.put("Message", e.getMessage());
		} catch (StudentNotExistException e) {
			request.put("Message", e.getMessage());
		} catch (CursOrTermNotMatchException e) {
			request.put("Message", e.getMessage());
		} catch (ClazzNotExistException e) {
			request.put("Message", e.getMessage());
		} catch (TchingTargetNotExistException e) {
			request.put("Message", e.getMessage());
		} catch (CursRulesNotExistException e) {
			request.put("Message", e.getMessage());
		} catch (TeacherNotExistException e) {
			request.put("Message", e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allClazz = clazzService.findAllCla();
		User t = (User) session.get("tUser");
		tchrCourses = teacherCourseService.selectByNumAndTerm(t.getSchNum());
		return "success";
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public List<Clazz> getAllClazz() {
		return allClazz;
	}

	public void setAllClazz(List<Clazz> allClazz) {
		this.allClazz = allClazz;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public List<B1> getClaCursB1s() {
		return claCursB1s;
	}

	public void setClaCursB1s(List<B1> claCursB1s) {
		this.claCursB1s = claCursB1s;
	}

	public List<B2> getClaCursB2s() {
		return claCursB2s;
	}

	public void setClaCursB2s(List<B2> claCursB2s) {
		this.claCursB2s = claCursB2s;
	}

	public List<Course> getTchrCourses() {
		return tchrCourses;
	}

	public void setTchrCourses(List<Course> tchrCourses) {
		this.tchrCourses = tchrCourses;
	}

}