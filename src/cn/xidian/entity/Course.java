package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	private Integer cursId;// pk

	private Department dept;// fk
	private Teacher teacher;// fk，课程负责人，每门课程对应一个负责的老师
	
	private String cursNum;
	private String cursName;
	private String cursEngName;
	private Boolean isRequired;
	private Double cursCredit;
	private String cursClassHour;//学时
	private String cursTerm;//开设学期
	private String cursCurrTerm;//15年秋
	private String cursProperty;//课程性质：专业基础课
	private String cursApplMajor;//适用专业：
	private String cursPreCourses;//先修课程
	private String cursIntro;//课程简介
	private String cursNote1;
	private String cursNote2;
	private Integer isDelete;//状态，是否已经被删除
	private String type;//课程类型，一般课程：normal，实验课程：experiment，毕业设计：graduation-project。根据不同课程类型显示不同表头
	
	
	@Id
	@GeneratedValue
	public Integer getCursId() {
		return cursId;
	}

	@OneToOne
	@JoinColumn(name="deptId")
	public Department getDept() {
		return dept;
	}
	
	@OneToOne
	@JoinColumn(name="tchrId")
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getCursNote1() {
		return cursNote1;
	}

	public void setCursNote1(String cursNote1) {
		this.cursNote1 = cursNote1;
	}

	public String getCursNote2() {
		return cursNote2;
	}

	public void setCursNote2(String cursNote2) {
		this.cursNote2 = cursNote2;
	}

	public String getCursProperty() {
		return cursProperty;
	}

	public void setCursProperty(String cursProperty) {
		this.cursProperty = cursProperty;
	}

	public String getCursApplMajor() {
		return cursApplMajor;
	}

	public void setCursApplMajor(String cursApplMajor) {
		this.cursApplMajor = cursApplMajor;
	}

	public String getCursPreCourses() {
		return cursPreCourses;
	}

	public void setCursPreCourses(String cursPreCourses) {
		this.cursPreCourses = cursPreCourses;
	}

	public String getCursIntro() {
		return cursIntro;
	}

	public void setCursIntro(String cursIntro) {
		this.cursIntro = cursIntro;
	}

	public String getCursCurrTerm() {
		return cursCurrTerm;
	}

	public void setCursCurrTerm(String cursCurrTerm) {
		this.cursCurrTerm = cursCurrTerm;
	}

	public String getCursClassHour() {
		return cursClassHour;
	}
	public Double getCursCredit() {
		return cursCredit;
	}
	public String getCursEngName() {
		return cursEngName;
	}
	public String getCursName() {
		return cursName;
	}
	public String getCursNum() {
		return cursNum;
	}
	public String getCursTerm() {
		return cursTerm;
	}
	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setCursClassHour(String cursClassHour) {
		this.cursClassHour = cursClassHour;
	}
	public void setCursCredit(Double cursCredit) {
		this.cursCredit = cursCredit;
	}
	public void setCursEngName(String cursEngName) {
		this.cursEngName = cursEngName;
	}
	public void setCursId(Integer cursId) {
		this.cursId = cursId;
	}
	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	public void setCursNum(String cursNum) {
		this.cursNum = cursNum;
	}
	public void setCursTerm(String cursTerm) {
		this.cursTerm = cursTerm;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
