package cn.xidian.web.action;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList.Member2.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.xidian.entity.Clazz;
import cn.xidian.entity.EvaluateResult;
import cn.xidian.entity.ItemEvaluatePoint;
import cn.xidian.entity.ItemEvaluateScore;
import cn.xidian.entity.MaxEva;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;
import cn.xidian.entity.StudentItem;
import cn.xidian.entity.User;
import cn.xidian.service.StudentItemService;
import cn.xidian.service.StudentService;
import cn.xidian.service.TeacherStudentService;

@SuppressWarnings("serial")
@Component(value = "JsonAction")
@Scope("prototype")
public class JsonAction extends ActionSupport implements RequestAware {
	private List<ItemEvaluateScore> itemEvaluateScores;
	private List<ItemEvaluatePoint> itemEvaluatePoints;
	private ItemEvaluateScore itemEvaluateScore;
	private Integer pointId;
	private Integer itemTypeId;
	private Integer gradeId;

	// 汇总添加
	private List<Student> stus;
	private Integer clazz;
	private List<StudentItem> items;
	private StudentItemService studentItemService;
	private Clazz cla;
	private String schoolYear;
	private List<EvaluateResult> evaluateResults;
	private EvaluateResult evaluateResult;
	private Integer size;
	private List<StudentCourse> studentCourses;
	private Student s;
	private MaxEva maxEva;

	Map<String, Object> session = ActionContext.getContext().getSession();
	User tUser = (User) session.get("tUser");

	@Resource(name = "studentItemServiceImpl")
	public void setStudentItemService(StudentItemService studentItemService) {
		this.studentItemService = studentItemService;
	}

	private StudentService studentService;

	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	private TeacherStudentService teacherStudentService;

	@Resource(name = "teacherStudentServiceImpl")
	public void setTeacherStudentService(TeacherStudentService teacherStudentService) {
		this.teacherStudentService = teacherStudentService;
	}

	public String selectItemEvaScores() {
		itemEvaluateScores = studentItemService.selectItemEvaScoresByPointId(pointId);
		return "list";
	}

	public String selectItemEvaPoint() {
		itemEvaluatePoints = studentItemService.selectItemEvaPoints(itemTypeId);
		return "list";
	}

	public String selectItemEvaScore() {
		itemEvaluateScore = studentItemService.selectItemEvaScore(gradeId);
		return "list";
	}

	public String evaluateSummaryByClazz() {

		stus = teacherStudentService.selectChargeStus(clazz);
		cla = teacherStudentService.selectClazzById(clazz);
		evaluateResults = teacherStudentService.selectSummaryEva(clazz, schoolYear);
		size = evaluateResults.size();
		if (size != 0) {
			teacherStudentService.deleteEvas(clazz, schoolYear);
		}
		for (Student element : stus) {
			Double M1 = 0.0;
			Double M2 = 0.0;
			Double M3 = 0.0;
			Double M4 = 0.0;
			Double M5 = 0.0;
			EvaluateResult evaluateResult = new EvaluateResult();
			String sch = element.getStuSchNum();
			M2 += countGrade(element, schoolYear);
			items = studentItemService.selectByStuNum(sch);
			for (StudentItem st : items) {
				switch (st.getItemEvaluateType().getItemEvaTypeId()) {
				case 1:
					M1 += Double.parseDouble(st.getItemScore());
					break;
				case 2:
					M2 += Double.parseDouble(st.getItemScore());
					break;
				case 3:
					M3 += Double.parseDouble(st.getItemScore());
					break;
				case 4:
					M4 += Double.parseDouble(st.getItemScore());
					break;
				case 5:
					M5 += Double.parseDouble(st.getItemScore());
					break;
				}

			}
			evaluateResult.setM1(M1);
			evaluateResult.setM2(M2);
			evaluateResult.setM3(M3);
			evaluateResult.setM4(M4);
			evaluateResult.setM5(M5);
			evaluateResult.setSchoolYear(schoolYear);
			evaluateResult.setClazz(cla);
			evaluateResult.setStudent(element);
			teacherStudentService.addEvaScore(evaluateResult);
		}
		return "list";
	}

	public String selectSummaryEva() {
		evaluateResults = teacherStudentService.selectSummaryEva(clazz, schoolYear);
		size = evaluateResults.size();
		return "list";
	}

	public Double countGrade(Student stu, String schoolYear) {
		Double allCredit = 0.0;
		Double allCreditAndScore = 0.0;
		studentCourses = teacherStudentService.selectStuGrades(stu.getStuId(), schoolYear);
		for (StudentCourse st : studentCourses) {
			st.getCourse().getCursCredit();
			st.getFinEvaValue();
			allCredit += st.getCourse().getCursCredit();
			allCreditAndScore += st.getCourse().getCursCredit() * st.getFinEvaValue();
		}
		Double average = allCreditAndScore / allCredit;
		return average;
	}

	public String selectStuCourseGrades() {
		String schNum = tUser.getSchNum();
		s = studentService.selectInfBySchNum(schNum);
		if (schoolYear.equals("-")) {
			studentCourses = studentService.selectStuAllGradesById(s.getStuId());
		} else {
			studentCourses = teacherStudentService.selectStuGrades(s.getStuId(), schoolYear);
		}
		return "list";
	}

	public String selectEvaluateResult() {
		String schNum = tUser.getSchNum();
		s = studentService.selectInfBySchNum(schNum);
		evaluateResult = studentService.selectEvaluateResult(s.getStuId(), schoolYear);
		maxEva=teacherStudentService.selectMaxEva(schoolYear);
		return "list";
	}

	public List<ItemEvaluateScore> getItemEvaluateScores() {
		return itemEvaluateScores;
	}

	public void setItemEvaluateScores(List<ItemEvaluateScore> itemEvaluateScores) {
		this.itemEvaluateScores = itemEvaluateScores;
	}

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public Integer getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public List<ItemEvaluatePoint> getItemEvaluatePoints() {
		return itemEvaluatePoints;
	}

	public void setItemEvaluatePoints(List<ItemEvaluatePoint> itemEvaluatePoints) {
		this.itemEvaluatePoints = itemEvaluatePoints;
	}

	public ItemEvaluateScore getItemEvaluateScore() {
		return itemEvaluateScore;
	}

	public void setItemEvaluateScore(ItemEvaluateScore itemEvaluateScore) {
		this.itemEvaluateScore = itemEvaluateScore;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public List<Student> getStus() {
		return stus;
	}

	public void setStus(List<Student> stus) {
		this.stus = stus;
	}

	public Integer getClazz() {
		return clazz;
	}

	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}

	public List<StudentItem> getItems() {
		return items;
	}

	public void setItems(List<StudentItem> items) {
		this.items = items;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public Clazz getCla() {
		return cla;
	}

	public void setCla(Clazz cla) {
		this.cla = cla;
	}

	public List<EvaluateResult> getEvaluateResults() {
		return evaluateResults;
	}

	public void setEvaluateResults(List<EvaluateResult> evaluateResults) {
		this.evaluateResults = evaluateResults;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public EvaluateResult getEvaluateResult() {
		return evaluateResult;
	}

	public void setEvaluateResult(EvaluateResult evaluateResult) {
		this.evaluateResult = evaluateResult;
	}

	public MaxEva getMaxEva() {
		return maxEva;
	}

	public void setMaxEva(MaxEva maxEva) {
		this.maxEva = maxEva;
	}

}
