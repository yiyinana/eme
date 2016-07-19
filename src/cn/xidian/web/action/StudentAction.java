package cn.xidian.web.action;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.ItemEvaluatePoint;
import cn.xidian.entity.ItemEvaluateScore;
import cn.xidian.entity.ItemEvaluateType;
import cn.xidian.entity.ItemFile;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentActivity;
import cn.xidian.entity.StudentCourse;
import cn.xidian.entity.StudentItem;
import cn.xidian.entity.User;
import cn.xidian.service.StudentActivityService;
import cn.xidian.service.StudentCourseService;
import cn.xidian.service.StudentItemService;
import cn.xidian.service.StudentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value = "StudentAction")
@Scope("prototype")
public class StudentAction extends ActionSupport implements RequestAware {
	private Student s;
	private Map<String, Object> request;
	private String clazz;
	private String password;
	// 学生目标
	private String shortGoal;
	private String midGoal;
	private String longGoal;
	// 参加活动
	private List<StudentActivity> orgActivities = new LinkedList<StudentActivity>();// 组织活动
	private List<StudentActivity> attendActivities = new LinkedList<StudentActivity>();// 参与活动
	private List<StudentActivity> socialActivities = new LinkedList<StudentActivity>();// 社会实践
	private StudentActivity activity;
	private Integer actId;
	// 参与项目及获奖
	private List<StudentItem> items = new LinkedList<StudentItem>();// 社会实践
	private List<ItemFile> itemFiles = new LinkedList<ItemFile>();
	private StudentItem item;
	private Integer itemId;
	// 成绩查询
	private String year;
	private String term;
	private List<StudentCourse> stuCurs;
	// 文件上传
	private File[] file;
	private String[] fileFileName;
	private List<ItemFile> allFile;
	
	// 学生项目的管理
	private List<ItemEvaluateType> itemEvaluateTypes;
	private List<ItemEvaluatePoint> itemEvaluatePoints;
	private List<ItemEvaluateScore> itemEvaluateScores;
	private ItemEvaluateType itemEvaluateType;
	private ItemEvaluatePoint itemEvaluatePoint;
	private ItemEvaluateScore itemEvaluateScore;
	
	//学生评估管理
	private List<StudentCourse> studentCourses;
	
	Map<String, Object> session = ActionContext.getContext().getSession();
	User tUser = (User) session.get("tUser");

	private StudentService studentService;

	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	private StudentActivityService studentActivityService;

	@Resource(name = "studentActivityServiceImpl")
	public void setStudentActivityService(StudentActivityService studentActivityService) {
		this.studentActivityService = studentActivityService;
	}

	private StudentItemService studentItemService;

	@Resource(name = "studentItemServiceImpl")
	public void setStudentItemService(StudentItemService studentItemService) {
		this.studentItemService = studentItemService;
	}

	private StudentCourseService studentCourseService;

	@Resource(name = "studentCourseServiceImpl")
	public void setStudentCourseService(StudentCourseService studentCourseService) {
		this.studentCourseService = studentCourseService;
	}

	public String selectBasicByNum() {
		String schNum = tUser.getSchNum();
		s = studentService.selectInfBySchNum(schNum);
		clazz = s.getClazz().getClaName();
		return "student";
	}

	/* 修改界面获得学生信息 */
	public String selectInfByNum() {
		String schNum = tUser.getSchNum();
		s = studentService.selectInfBySchNum(schNum);
		/* awards = teacherService.loadAwardBySchNum(schNum); */
		return "student";
	}

	public String modifyBasicInf() {
		String schNum = tUser.getSchNum();
		s.setStuSchNum(schNum);
		boolean isSuccess = studentService.updateInfBySchNum(s);
		// s = ssi.selectInfBySchNum(schNum);
		if (isSuccess) {
			request.put("Message", "修改成功！");
			return "student";
		} else {
			request.put("Message", "对不起，修改失败！");
			return "student";
		}
	}

	public String modifyPassword() {
		String schNum = tUser.getSchNum();
		boolean isSuccess = studentService.modifyPassword(schNum, password);
		s = studentService.selectInfBySchNum(schNum);
		if (isSuccess) {
			request.put("Message", "修改密码成功！");
			return "student";
		} else {
			request.put("Message", "对不起，修改密码失败！");
			return "student";
		}
	}

	public String updateShortGoal() {
		String schNum = tUser.getSchNum();
		s = studentService.selectInfBySchNum(schNum);
		s.setShortGoal(shortGoal);
		studentService.updateGoal(s);
		request.put("Message", "修改成功！");
		return "student";
	}

	public String updateMidGoal() {
		String schNum = tUser.getSchNum();
		s = studentService.selectInfBySchNum(schNum);
		s.setMidGoal(midGoal);
		studentService.updateGoal(s);
		request.put("Message", "修改成功！");
		return "student";
	}

	public String updateLongGoal() {
		String schNum = tUser.getSchNum();
		s = studentService.selectInfBySchNum(schNum);
		s.setLongGoal(longGoal);
		studentService.updateGoal(s);
		request.put("Message", "修改成功！");
		return "student";
	}

	/* 学生活动 */
	public String selectActivity() {
		String schNum = tUser.getSchNum();
		List<StudentActivity> sa = studentActivityService.selectByStuNum(schNum);
		for (int i = 0; i < sa.size(); i++) {
			if (sa.get(i).getType().equals("组织活动")) {
				orgActivities.add(sa.get(i));
			}
			if (sa.get(i).getType().equals("参与活动")) {
				attendActivities.add(sa.get(i));
			}
			if (sa.get(i).getType().equals("社会实践")) {
				socialActivities.add(sa.get(i));
			}
		}
		return "student";
	}

	public String deleteActivity() {
		studentActivityService.deleteById(actId);
		request.put("Message", "删除成功！");
		return "stuActList";
	}

	public String addActivity() {
		String schNum = tUser.getSchNum();
		activity.setStudent(studentService.selectInfBySchNum(schNum));
		studentActivityService.add(activity);
		request.put("Message", "添加成功！");
		return "stuActList";
	}

	/* 学生参与项目及获奖 */
	public String selectItem() {
		String schNum = tUser.getSchNum();
		items = studentItemService.selectByStuNum(schNum);
		return "student";
	}

	public String deleteItem() {
		// 判断是否能够删除
		item = studentItemService.selectItemInfo(itemId);
		if (!item.getItemScore().toString().equals("0")) {
			request.put("Message", "已审核，不允许删除！");
		}
		// 删除图片
		String realPath = ServletActionContext.getServletContext().getRealPath("/upload");// 实际路径
		itemFiles = studentItemService.selectItemFile(itemId);
		if (studentItemService.deleteFileById(itemId)) {
			if (studentItemService.deleteItemById(itemId)) {
				for (ItemFile element : itemFiles) {
					File file = new File(realPath + "/" + element.getSaveFileName());
					if (file.exists()) {
						file.delete();
					}
				}
				request.put("Message", "删除成功！");
			} else {
				request.put("Message", "删除失败！");
			}
		} else {
			request.put("Message", "删除失败！");
		}
		return "stuItemList";
	}

	public String addItem() throws Exception {
		item.setItemState("待审核");
		item.setNote("无");
		item.setItemScore("0");	
		Date date=new Date();
		item.setItemSubmitDate(date);
		String schNum = tUser.getSchNum();
		s = studentService.selectInfBySchNum(schNum);
		item.setStudent(s);
		studentItemService.add(item);
		if (file != null) {
			fileUpload(item);
		}
		request.put("Message", "添加成功！");
		return "stuItemList";
	}

	public void fileUpload(StudentItem item) throws Exception {
		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		String realPath = ServletActionContext.getServletContext().getRealPath("/upload");// 实际路径
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String schNum = tUser.getSchNum();
		String fileNewName = schNum + '_' + format.format(new Date());// 给文件新的存储名字
		File savedir = new File(realPath);
		if (!savedir.getParentFile().exists())
			savedir.getParentFile().mkdirs();
		for (int i = 0; i < file.length; i++) {
			ItemFile itemFile = new ItemFile();// 实例化ItemFile
			File savefile = new File(savedir, fileNewName + '_' + fileFileName[i]);
			String suffix = fileFileName[i].substring(fileFileName[i].lastIndexOf(".") + 1);
			FileUtils.copyFile(file[i], savefile);
			itemFile.setFileName(fileFileName[i]);
			itemFile.setSaveFileName(fileNewName + '_' + fileFileName[i]);
			itemFile.setStudentItem(item);
			itemFile.setFileType(suffix);
			studentItemService.saveAttachment(itemFile);
		}

	}

	// 学生查询成绩
	public String selectStuPer() {
		String schNum = tUser.getSchNum();
		String currTerm = year + "-" + term;
		stuCurs = studentCourseService.selectByNumTerm(schNum, currTerm);
		if (stuCurs.size() == 0) {
			request.put("Message", "没有找到相关信息！");
		}
		return "student";
	}

	// 查看学生获奖情况详情
	public String selectItemInfo() {
		allFile = studentItemService.selectItemFile(itemId);
		item = studentItemService.selectItemInfo(itemId);
		itemEvaluateType=studentItemService.selectItemEvaType(item.getItemEvaluateType().getItemEvaTypeId());
		itemEvaluatePoint=studentItemService.selectItemEvaPoint(item.getItemEvaluatePoint().getItemEvaPointId());
		itemEvaluateScore=studentItemService.selectItemEvaScore(item.getItemEvaluateScore().getItemEvaScoreId());
		return "student";
	}

	
	// 在学生项目添加时显示项目类别下拉框
	public String selectItemEvaType() {
		itemEvaluateTypes = studentItemService.selectItemEvaTypes();
		itemEvaluatePoints=studentItemService.selectItemEvaPoints(1);
		itemEvaluateScores=studentItemService.selectItemEvaScoresByPointId(1);
		return "student";
	}
	//查询学生成绩
		public String selectStuAllGradesById() {
			String schNum = tUser.getSchNum();
			s=studentService.selectInfBySchNum(schNum);
			studentCourses=studentService.selectStuAllGradesById(s.getStuId());
			return "student";
		}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getShortGoal() {
		return shortGoal;
	}

	public void setShortGoal(String shortGoal) {
		this.shortGoal = shortGoal;
	}

	public String getMidGoal() {
		return midGoal;
	}

	public void setMidGoal(String midGoal) {
		this.midGoal = midGoal;
	}

	public String getLongGoal() {
		return longGoal;
	}

	public void setLongGoal(String longGoal) {
		this.longGoal = longGoal;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<StudentActivity> getOrgActivities() {
		return orgActivities;
	}

	public void setOrgActivities(List<StudentActivity> orgActivities) {
		this.orgActivities = orgActivities;
	}

	public List<StudentActivity> getAttendActivities() {
		return attendActivities;
	}

	public void setAttendActivities(List<StudentActivity> attendActivities) {
		this.attendActivities = attendActivities;
	}

	public List<StudentActivity> getSocialActivities() {
		return socialActivities;
	}

	public void setSocialActivities(List<StudentActivity> socialActivities) {
		this.socialActivities = socialActivities;
	}

	public StudentActivity getActivity() {
		return activity;
	}

	public void setActivity(StudentActivity activity) {
		this.activity = activity;
	}

	public List<StudentItem> getItems() {
		return items;
	}

	public void setItems(List<StudentItem> items) {
		this.items = items;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<StudentCourse> getStuCurs() {
		return stuCurs;
	}

	public void setStuCurs(List<StudentCourse> stuCurs) {
		this.stuCurs = stuCurs;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String excute() {
		return "student";
	}

	public List<ItemFile> getItemFiles() {
		return itemFiles;
	}

	public void setItemFiles(List<ItemFile> itemFiles) {
		this.itemFiles = itemFiles;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<ItemFile> getAllFile() {
		return allFile;
	}

	public void setAllFile(List<ItemFile> allFile) {
		this.allFile = allFile;
	}

	public List<ItemEvaluateType> getItemEvaluateTypes() {
		return itemEvaluateTypes;
	}

	public void setItemEvaluateTypes(List<ItemEvaluateType> itemEvaluateTypes) {
		this.itemEvaluateTypes = itemEvaluateTypes;
	}

	public List<ItemEvaluatePoint> getItemEvaluatePoints() {
		return itemEvaluatePoints;
	}

	public void setItemEvaluatePoints(List<ItemEvaluatePoint> itemEvaluatePoints) {
		this.itemEvaluatePoints = itemEvaluatePoints;
	}

	public List<ItemEvaluateScore> getItemEvaluateScores() {
		return itemEvaluateScores;
	}

	public void setItemEvaluateScores(List<ItemEvaluateScore> itemEvaluateScores) {
		this.itemEvaluateScores = itemEvaluateScores;
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

	public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

}
