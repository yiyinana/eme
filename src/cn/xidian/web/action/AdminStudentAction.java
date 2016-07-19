package cn.xidian.web.action;

import java.io.File;
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

import cn.xidian.entity.Clazz;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentItem;
import cn.xidian.exception.StudentExistsException;
import cn.xidian.exception.StudentNotExistException;
import cn.xidian.service.AdminStudentService;
import cn.xidian.service.ClazzService;
import cn.xidian.web.bean.AdminStuLimits;
import cn.xidian.web.service.UploadActionService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value = "AdminStudentAction")
@Scope("prototype")
public class AdminStudentAction extends ActionSupport implements RequestAware,SessionAware{
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Student s;
	private List<Student> students = new LinkedList<Student>();
	private String schNum;
	private String name;
	private Integer clazzId;
	private Clazz clazz = new Clazz();
	private List<Clazz> allClazz;
	private AdminStuLimits limits;
	/* 上传头像 */
	private String uploadUrl;
	private File file;
	/* 上传头像 */
	private List<StudentItem> items = new LinkedList<StudentItem>();
	private StudentItem item;
	private AdminStudentService adminStudentService;

	@Resource(name = "adminStudentServiceImpl")
	public void setAdminStudentService(AdminStudentService adminStudentService) {
		this.adminStudentService = adminStudentService;
	}

	private ClazzService clazzService;

	@Resource(name = "clazzServiceImpl")
	public void setClazzService(ClazzService clazzService) {
		this.clazzService = clazzService;
	}

	public String addStudent() {
		try {
			clazz.setClaId(clazzId);
			s.setClazz(clazz);
			s.setIsDelete(1);
			boolean isSuccess1 = adminStudentService.addStudent(s);
			boolean isSuccess2 = uploadFile();
			if (isSuccess1 && isSuccess2) {
				request.put("Message", "添加学生成功！");
			} else {
				request.put("Message", "对不起，添加失败！");
			}
		} catch (StudentExistsException e) {
			request.put("Message", e.getMessage());
		}
		allClazz = clazzService.findAllCla();
		return "admin";
	}

	/* 上传头像 */
	public boolean uploadFile() {
		UploadActionService uas = new UploadActionService();
		try {
			String fileName = s.getStuSchNum() + ".jpg";
			uas.upload(file, uploadUrl, fileName); // 自定义方法调用
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* 上传头像 */
	/* 管理员查找学生信息 */
	public String selectAllStus() {
		students = adminStudentService.selectAllStus();
		allClazz = clazzService.findAllCla();
		return "admin";
	}

	public String selectAllClazz() {
		allClazz = clazzService.findAllCla();
		return "admin";
	}
	
	public String searchStudents() {

		Set<Student> ts = adminStudentService.selectStuLimits(limits);
		Iterator<Student> it = ts.iterator();
		while (it.hasNext()) {
			students.add(it.next());
		}
		allClazz = clazzService.findAllCla();
		if (students.size() == 0) {
			this.addActionError("对不起，未找到相关信息！");
		}

		return "admin";
	}

	public String selectStudentBySchNum() {
		session.put("stuSchNum", schNum);
		s = adminStudentService.selectStudentBySchNum(schNum);
		allClazz = clazzService.findAllCla();
		setItems(adminStudentService.selectStuItemsBySchNum(schNum));
		return "admin";
	}

	public String deleteBySchNum() {
		try {
			boolean isSuccess = adminStudentService.deleteStudent(schNum);
			if (isSuccess) {
				request.put("Message", "删除成功！");
			} else {
				request.put("Message", "删除失败！");
			}
		} catch (StudentNotExistException e) {
			request.put("Message", e.getMessage());
		}
		return "adminStudentList";
	}

	public String modifyBySchNum() {
		try {
			clazz.setClaId(clazzId);
			s.setClazz(clazz);
			boolean isSuccess = adminStudentService.updateStudent(s);
			if (file != null)
				uploadFile();
			if (isSuccess) {
				request.put("Message", "修改成功！");
			} else {
				request.put("Message", "修改失败！");
			}
		} catch (StudentNotExistException e) {
			request.put("Message", e.getMessage());
		}
		return "admin";
	}
	// 管理员评价学生获奖项目
		public String judgeStuItem() {
			schNum = session.get("stuSchNum").toString();
			
			boolean isSuccess = adminStudentService.judgeStuItem(item);
			if (isSuccess) {
				request.put("Message", "审核成功");

			} else {
				request.put("Message", "审核失败");
			}
			return "adminStudentDetail";
		}
	public Map<String, Object> getRequest() {
		return request;
	}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Integer getClazzId() {
		return clazzId;
	}

	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public String getSchNum() {
		return schNum;
	}

	public void setSchNum(String schNum) {
		this.schNum = schNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdminStuLimits getLimits() {
		return limits;
	}

	public void setLimits(AdminStuLimits limits) {
		this.limits = limits;
	}

	public List<Clazz> getAllClazz() {
		return allClazz;
	}

	public void setAllClazz(List<Clazz> allClazz) {
		this.allClazz = allClazz;
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

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public StudentItem getItem() {
		return item;
	}

	public void setItem(StudentItem item) {
		this.item = item;
	}

	public List<StudentItem> getItems() {
		return items;
	}

	public void setItems(List<StudentItem> items) {
		this.items = items;
	}
}
