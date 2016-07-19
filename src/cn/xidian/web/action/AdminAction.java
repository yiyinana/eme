package cn.xidian.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.Admin;
import cn.xidian.entity.MajorInformation;
import cn.xidian.entity.Teacher;
import cn.xidian.entity.User;
import cn.xidian.exception.AdminExistsException;
import cn.xidian.exception.AdminNotExistsException;
import cn.xidian.exception.ClazzNotExistException;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.exception.StudentNotExistException;
import cn.xidian.exception.TeacherNotExistException;
import cn.xidian.service.AdminService;
import cn.xidian.service.AdminStudentService;
import cn.xidian.service.AdminTeacherService;
import cn.xidian.service.MajorInformationService;
import cn.xidian.web.service.UploadActionService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value = "AdminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements RequestAware,
		SessionAware {

	private List<Admin> admins = new LinkedList<Admin>();
	private String adminSchNum;
	public String adminPwd;
	private Admin admin;
	private List<Teacher> teachers;
	private List<String> managerId;
	private List<Teacher> managers;
	/* 上传文件 */
	private String uploadUrl;
	private String fileName;
	private File file; // 必须与表单元素的NAME属性值一致
	// 专业信息设置
	private MajorInformation majorInformation;
	
	private Map<String, Object> request;
	private Map<String, Object> session;

	private AdminService adminService;

	@Resource(name = "adminServiceImpl")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	private MajorInformationService majorInformationService;

	@Resource(name = "majorInformationServiceImpl")
	public void setMajorInformationService(MajorInformationService majorInformationService) {
		this.majorInformationService = majorInformationService;
	}
	
	private AdminStudentService adminStudentService;

	@Resource(name = "adminStudentServiceImpl")
	public void setAdminStudentService(AdminStudentService adminStudentService) {
		this.adminStudentService = adminStudentService;
	}

	private AdminTeacherService adminTeacherService;

	@Resource(name = "adminTeacherServiceImpl")
	public void setAdminTeacherService(AdminTeacherService adminTeacherService) {
		this.adminTeacherService = adminTeacherService;
	}

	public String selectAllAdmin() {
		admins = adminService.selectAllAdmin();
		return "admin";
	}

	public String updatePwd() {
		User user = (User) session.get("tUser");
		String number = user.getSchNum();
		boolean aa = adminService.updateAdminBySchNumAndPwd(number, adminPwd);
		if (aa) {
			request.put("Message", "修改密码成功！");
		} else
			request.put("Message", "修改密码失败！");
		return "adminAdminList";
	}

	public String deleteBySchNum() {
		try {
			if (adminSchNum.equals(((User) session.get("tUser")).getSchNum()
					.toString())) {
				request.put("Message", "对不起，您不能删除自己！");
				return "adminAdminList";
			}
			boolean aa = adminService.deleteAdminBySchNum(adminSchNum);
			if (aa) {
				request.put("Message", "删除成功！");
			} else
				request.put("Message", "删除失败！");

		} catch (AdminNotExistsException e) {
			request.put("Message", e.getMessage());
		}
		return "adminAdminList";
	}

	public String addAdmin() {
		try {
			admin.setIsDelete(1);
			boolean isSuccess = adminService.addAdmin(admin);
			if (isSuccess) {
				request.put("Message", "添加管理员成功！");
			} else
				request.put("Message", "添加失败！");

		} catch (AdminExistsException e) {
			request.put("Message", e.getMessage());
		}
		return "adminAdminListR";
	}

	/*选择所有老师*/
	public String selectAllTchrs(){
		teachers = adminTeacherService.selectAllTchrs();
		managers = adminTeacherService.selectAllManagers();
		majorInformation = majorInformationService.selectMajorInfo();
		return "admin";
	}
	
	/*设置专业负责人*/
	public String addMajorManager(){
		List<Integer> mId = new LinkedList<Integer>();
		for(int i=0;i<managerId.size();i++){
			int id = Integer.parseInt(managerId.get(i));
			mId.add(id);
		}
		boolean isSuccess = adminTeacherService.updateManager(mId);
		if(isSuccess){
			teachers = adminTeacherService.selectAllTchrs();
			managers = adminTeacherService.selectAllManagers();
			request.put("Message", "设置成功！");
		}
		return "admin";
	}
	
	/* 批量导入学生信息 */
	public String addStuExcel() {
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"");

		UploadActionService uas = new UploadActionService();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");// 设置日期格式
			Random rand = new Random();
			Integer randNum = rand.nextInt(100);
			String filename = df.format(new Date()) + randNum.toString();// 文件名为当前时间加上一个随机数
			int index1 = fileName.lastIndexOf(".");
			int index2 = fileName.length();
			String postf = fileName.substring(index1 + 1, index2);
			if (postf.equals("xlsx")) {
				filename += ".xlsx";
			} else
				filename += ".xls";
			uas.upload(file, uploadUrl, filename); // 自定义方法调用
			adminStudentService.addStuExcel(realPath + uploadUrl+"/" + filename);
			request.put("Message", "上传成功！");
		}catch(StudentNotExistException e){
			request.put("Message", e.getMessage());
		}catch(ClazzNotExistException e){
			request.put("Message", e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "admin";
	}

	/* 批量导入学生-课程 */
	public String addStuCursExcel() {
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"");

		UploadActionService uas = new UploadActionService();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");// 设置日期格式
			Random rand = new Random();
			Integer randNum = rand.nextInt(100);
			String filename = df.format(new Date()) + randNum.toString();// 文件名为当前时间加上一个随机数
			int index1 = fileName.lastIndexOf(".");
			int index2 = fileName.length();
			String postf = fileName.substring(index1 + 1, index2);
			if (postf.equals("xlsx")) {
				filename += ".xlsx";
			} else
				filename += ".xls";
			uas.upload(file, uploadUrl, filename); // 自定义方法调用
			adminStudentService.addStuCursExcel(realPath + uploadUrl+"/" + filename);
			request.put("Message", "上传成功！");
		} catch(StudentNotExistException e){
			request.put("Message", e.getMessage());
		}catch(CourseNotExistException e){
			request.put("Message", e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin";
	}

	/* 批量导入教师信息 */
	public String addTchrExcel() {
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"");

		UploadActionService uas = new UploadActionService();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");// 设置日期格式
			Random rand = new Random();
			Integer randNum = rand.nextInt(100);
			String filename = df.format(new Date()) + randNum.toString();// 文件名为当前时间加上一个随机数
			int index1 = fileName.lastIndexOf(".");
			int index2 = fileName.length();
			String postf = fileName.substring(index1 + 1, index2);
			if (postf.equals("xlsx")) {
				filename += ".xlsx";
			} else
				filename += ".xls";
			uas.upload(file, uploadUrl, filename); // 自定义方法调用
			adminTeacherService.addTchrExcel(realPath + uploadUrl+"/" + filename);
			request.put("Message", "上传成功！");
		} catch(TeacherNotExistException e){
			request.put("Message", e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin";
	}

	/* 批量导入教师-课程 */
	public String addTchrCursExcel() {
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"");

		UploadActionService uas = new UploadActionService();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");// 设置日期格式
			Random rand = new Random();
			Integer randNum = rand.nextInt(100);
			String filename = df.format(new Date()) + randNum.toString();// 文件名为当前时间加上一个随机数
			int index1 = fileName.lastIndexOf(".");
			int index2 = fileName.length();
			String postf = fileName.substring(index1 + 1, index2);
			if (postf.equals("xlsx")) {
				filename += ".xlsx";
			} else
				filename += ".xls";
			uas.upload(file, uploadUrl, filename); // 自定义方法调用
			adminTeacherService.addTchrCursExcel(realPath + uploadUrl+"/" + filename);
			request.put("Message", "上传成功！");
		} catch(TeacherNotExistException e){
			request.put("Message", e.getMessage());
		}catch(CourseNotExistException e){
			request.put("Message", e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin";
	}
	public String updateMajorInfo() {
		boolean majorInfo = majorInformationService.updateMajorInfo(majorInformation);
		if (majorInfo) {
			request.put("Message", "提交成功！");
		} else {
			request.put("Message", "提交失敗！");
		}
		return "adminMajorInfo";
	}

	
	public List<Admin> getAdmins() {
		return admins;
	}

	public String getAdminSchNum() {
		return adminSchNum;
	}

	public void setAdminSchNum(String adminSchNum) {
		this.adminSchNum = adminSchNum;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public List<String> getManagerId() {
		return managerId;
	}

	public void setManagerId(List<String> managerId) {
		this.managerId = managerId;
	}
	
	public List<Teacher> getManagers() {
		return managers;
	}

	public void setManagers(List<Teacher> managers) {
		this.managers = managers;
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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String excute() {
		return "admin";
	}

	public MajorInformation getMajorInformation() {
		return majorInformation;
	}

	public void setMajorInformation(MajorInformation majorInformation) {
		this.majorInformation = majorInformation;
	}
}
