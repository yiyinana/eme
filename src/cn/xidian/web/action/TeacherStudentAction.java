package cn.xidian.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.xidian.entity.Clazz;
import cn.xidian.entity.EvaluateResult;
import cn.xidian.entity.ItemEvaluatePoint;
import cn.xidian.entity.ItemEvaluateScore;
import cn.xidian.entity.ItemEvaluateType;
import cn.xidian.entity.ItemFile;
import cn.xidian.entity.MaxEva;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;
import cn.xidian.entity.StudentItem;
import cn.xidian.entity.Teacher;
import cn.xidian.entity.User;
import cn.xidian.exception.StudentExistsException;
import cn.xidian.exception.StudentNotExistException;
import cn.xidian.service.AdminStudentService;
import cn.xidian.service.ClazzService;
import cn.xidian.service.StudentItemService;
import cn.xidian.service.TeacherService;
import cn.xidian.service.TeacherStudentService;
import cn.xidian.web.bean.AdminStuLimits;
import cn.xidian.web.service.UploadActionService;

@SuppressWarnings("serial")
@Component(value = "TeacherStudentAction")
@Scope("prototype")
public class TeacherStudentAction extends ActionSupport implements RequestAware {
	private List<Student> students;
	private Student s;
	private List<StudentItem> items = new LinkedList<StudentItem>();
	private String schNum;
	private Clazz clazz = new Clazz();
	private Integer clazzId;
	private List<ItemFile> allFile;
	private StudentItem item;
	private Integer itemId;
	private List<Clazz> allClazz;

	private AdminStuLimits limits;
	private Teacher teacher;

	// 学生评估汇总添加
	private EvaluateResult evaluateResult;
	private Integer evaluateResultId;
	private MaxEva maxEva;
	private String schoolYear;
	/* 上传头像 */
	private String uploadUrl;
	private File file;
	/* 上传头像 */
	// 项目信息
	private ItemEvaluateType itemEvaluateType;
	private ItemEvaluatePoint itemEvaluatePoint;
	private ItemEvaluateScore itemEvaluateScore;

	private Map<String, Object> request;
	Map<String, Object> session = ActionContext.getContext().getSession();
	// 获取登陆者的账号
	User tUser = (User) session.get("tUser");

	private StudentItemService studentItemService;

	@Resource(name = "studentItemServiceImpl")
	public void setStudentItemService(StudentItemService studentItemService) {
		this.studentItemService = studentItemService;
	}

	AdminStudentService adminStudentService;

	@Resource(name = "adminStudentServiceImpl")
	public void setAdminStudentService(AdminStudentService adminStudentService) {
		this.adminStudentService = adminStudentService;
	}

	TeacherStudentService teacherStudentService;

	@Resource(name = "teacherStudentServiceImpl")
	public void setTeacherStudentService(TeacherStudentService teacherStudentService) {
		this.teacherStudentService = teacherStudentService;
	}

	TeacherService teacherService;

	@Resource(name = "teacherServiceImpl")
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	private ClazzService clazzService;

	@Resource(name = "clazzServiceImpl")
	public void setClazzService(ClazzService clazzService) {
		this.clazzService = clazzService;
	}

	/* 教师查找学生信息列表 */
	public String selectChargeStus() {
		String tchrSchNum = tUser.getSchNum();
		teacher = teacherService.selectInfBySchNum(tchrSchNum);
		setAllClazz(teacherStudentService.findChargeCla(teacher.getTchrId()));
		List<Student> s = new ArrayList<Student>();
		for (Clazz element : allClazz) {
			s.addAll(teacherStudentService.selectChargeStus(element.getClaId()));
		}
		students = s;
		return "teacher";
	}

	// 按条件查询
	public String selectStusByLimits() {
		String tchrSchNum = tUser.getSchNum();
		teacher = teacherService.selectInfBySchNum(tchrSchNum);
		setAllClazz(teacherStudentService.findChargeCla(teacher.getTchrId()));
		Set<Student> stus = teacherStudentService.selectStuLimits(limits, allClazz);
		List<Student> list = new LinkedList<Student>(stus);
		students = list;
		System.out.println(students.size());
		if (students.size() == 0) {

			this.addActionError("对不起，未找到相关信息！");
		}
		return "teacher";
	}

	// 设置教师查看学生信息
	public String selectStudentBySchNum() {
		session.put("stuSchNum", schNum);
		s = adminStudentService.selectStudentBySchNum(schNum);
		setAllClazz(clazzService.findAllCla());
		setItems(adminStudentService.selectStuItemsBySchNum(schNum));
		return "teacher";
	}

	// 添加新学生
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
		setAllClazz(clazzService.findAllCla());
		return "teacherStudentList";
	}

	// 查找所有班级
	public String selectAllClazz() {
		setAllClazz(clazzService.findAllCla());
		return "teacher";
	}

	// 查看学生获奖情况详情
	public String selectItemInfo() {
		allFile = studentItemService.selectItemFile(itemId);
		item = studentItemService.selectItemInfo(itemId);
		itemEvaluateType = studentItemService.selectItemEvaType(item.getItemEvaluateType().getItemEvaTypeId());
		itemEvaluatePoint = studentItemService.selectItemEvaPoint(item.getItemEvaluatePoint().getItemEvaPointId());
		itemEvaluateScore = studentItemService.selectItemEvaScore(item.getItemEvaluateScore().getItemEvaScoreId());
		return "teacher";
	}

	// 班主任评价学生获奖项目
	public String judgeStuItem() {
		schNum = session.get("stuSchNum").toString();

		boolean isSuccess = adminStudentService.judgeStuItem(item);
		if (isSuccess) {
			request.put("Message", "审核成功");

		} else {
			request.put("Message", "审核失败");
		}
		return "teacherStudentDetail";
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

	/* 上传头像完 */

	// 删除学生
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
		return "teacherStudentList";
	}

	// 修改学生信息
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
		return "teacherStudentList";
	}

	public String selectEvaluateResultById() {
		evaluateResult = teacherStudentService.selectEvaluateResultById(evaluateResultId);
		maxEva=teacherStudentService.selectMaxEva(schoolYear);
		return "teacher";
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public List<StudentItem> getItems() {
		return items;
	}

	public void setItems(List<StudentItem> items) {
		this.items = items;
	}

	public String getSchNum() {
		return schNum;
	}

	public void setSchNum(String schNum) {
		this.schNum = schNum;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Integer getClazzId() {
		return clazzId;
	}

	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}

	public List<ItemFile> getAllFile() {
		return allFile;
	}

	public void setAllFile(List<ItemFile> allFile) {
		this.allFile = allFile;
	}

	public StudentItem getItem() {
		return item;
	}

	public void setItem(StudentItem item) {
		this.item = item;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public List<Clazz> getAllClazz() {
		return allClazz;
	}

	public void setAllClazz(List<Clazz> allClazz) {
		this.allClazz = allClazz;
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

	public AdminStuLimits getLimits() {
		return limits;
	}

	public void setLimits(AdminStuLimits limits) {
		this.limits = limits;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public ItemEvaluateType getItemEvaluateType() {
		return itemEvaluateType;
	}

	public void setItemEvaluateType(ItemEvaluateType itemEvaluateType) {
		this.itemEvaluateType = itemEvaluateType;
	}

	public ItemEvaluatePoint getItemEvaluatePoint() {
		return itemEvaluatePoint;
	}

	public void setItemEvaluatePoint(ItemEvaluatePoint itemEvaluatePoint) {
		this.itemEvaluatePoint = itemEvaluatePoint;
	}

	public ItemEvaluateScore getItemEvaluateScore() {
		return itemEvaluateScore;
	}

	public void setItemEvaluateScore(ItemEvaluateScore itemEvaluateScore) {
		this.itemEvaluateScore = itemEvaluateScore;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public EvaluateResult getEvaluateResult() {
		return evaluateResult;
	}

	public void setEvaluateResult(EvaluateResult evaluateResult) {
		this.evaluateResult = evaluateResult;
	}

	public Integer getEvaluateResultId() {
		return evaluateResultId;
	}

	public void setEvaluateResultId(Integer evaluateResultId) {
		this.evaluateResultId = evaluateResultId;
	}

	public MaxEva getMaxEva() {
		return maxEva;
	}

	public void setMaxEva(MaxEva maxEva) {
		this.maxEva = maxEva;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

}
