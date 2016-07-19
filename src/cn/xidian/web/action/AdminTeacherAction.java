package cn.xidian.web.action;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.Teacher;
import cn.xidian.entity.TeacherExperiment;
import cn.xidian.exception.TeacherExistsException;
import cn.xidian.exception.TeacherNotExistException;
import cn.xidian.service.AdminTeacherService;
import cn.xidian.service.TeacherExperimentService;
import cn.xidian.web.bean.AdminTchrLimits;
import cn.xidian.web.service.UploadActionService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value = "AdminTeacherAction")
@Scope("prototype")
public class AdminTeacherAction extends ActionSupport implements RequestAware {
	private Map<String, Object> request;
	private Teacher teacher;
	private String tchrSchNum;
	private List<Teacher> teachers = new LinkedList<Teacher>();
	private AdminTchrLimits limits;
	private List<TeacherExperiment> experiments = new LinkedList<TeacherExperiment>();
	/* 上传头像 */
	private String uploadUrl;
	private File file;
	/* 上传头像 */

	private AdminTeacherService adminTeacherService;

	@Resource(name = "adminTeacherServiceImpl")
	public void setAdminTeacherService(AdminTeacherService adminTeacherService) {
		this.adminTeacherService = adminTeacherService;
	}

	/* 管理员添加教师信息 */
	public String addTeacher() {
		try {
			teacher.setIsDelete(1);//初始为未删除
			teacher.setIsManager(0);//初始为不是专业负责人
			boolean isSuccess1 = adminTeacherService.addTeacher(teacher);
			boolean isSuccess2 = uploadFile();
			if (isSuccess1 && isSuccess2) {
				request.put("Message", "添加教师成功！");
			} else {
				request.put("Message", "对不起，添加失败！");
			}
		} catch (TeacherExistsException e) {
			request.put("Message", e.getMessage());
		}
		return "admin";
	}

	/* 上传头像 */
	public boolean uploadFile() {
		UploadActionService uas = new UploadActionService();
		try {
			String fileName = teacher.getTchrSchNum() + ".jpg";
			uas.upload(file, uploadUrl, fileName); // 自定义方法调用
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* 上传头像 */
	/* 管理员添加教师信息完 */
	private TeacherExperimentService teacherExperimentService;

	@Resource(name = "teacherExperimentServiceImpl")
	public void setTeacherExperimentService(
			TeacherExperimentService teacherExperimentService) {
		this.teacherExperimentService = teacherExperimentService;
	}

	/* 管理员查找教师信息 */
	public String selectTeacherBySchNum() {
		teacher = adminTeacherService.selectTeacherBySchNum(tchrSchNum);
		experiments = teacherExperimentService.selectByTchrNum(tchrSchNum);
		return "admin";
	}

	public String searchTeachers() {
		Set<Teacher> ts = adminTeacherService.selectUnderLimits(limits);

		Iterator<Teacher> it = ts.iterator();
		while (it.hasNext()) {
			teachers.add(it.next());
		}

		if (teachers.size() == 0) {
			this.addActionError("对不起，未找到相关信息！");
		}

		return "admin";
	}

	public String selectAllTchrs() {
		teachers = adminTeacherService.selectAllTchrs();
		return "admin";
	}

	/* 管理员查找教师信息完 */

	/* 管理员删除教师信息 */
	public String deleteBySchNum() {
		try {
			boolean aa = adminTeacherService.deleteTeacher(tchrSchNum);
			if (aa) {
				request.put("Message", "删除成功！");
			} else {
				request.put("Message", "删除失败！");
			}
		} catch (TeacherNotExistException e) {
			request.put("Message", e.getMessage());
		}
		return "adminTeacherList";
	}

	/* 管理员删除教师信息完 */

	/* 管理员更新教师信息 */
	public String modifyBySchNum() {
		try {
			boolean aa = adminTeacherService.updateTeacher(teacher);
			if(file != null) uploadFile();
			if (aa) {
				request.put("Message", "修改成功！");
			} else {
				request.put("Message", "修改失败！");
			}
		} catch (TeacherNotExistException e) {
			request.put("Message", e.getMessage());
		}
		return "admin";
	}

	/* 管理员更新教师信息完 */

	public Map<String, Object> getRequest() {
		return request;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getTchrSchNum() {
		return tchrSchNum;
	}

	public void setTchrSchNum(String tchrSchNum) {
		this.tchrSchNum = tchrSchNum;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public AdminTchrLimits getLimits() {
		return limits;
	}

	public void setLimits(AdminTchrLimits limits) {
		this.limits = limits;
	}

	public List<TeacherExperiment> getExperiments() {
		return experiments;
	}

	public void setExperiments(List<TeacherExperiment> experiments) {
		this.experiments = experiments;
	}	
	
}
