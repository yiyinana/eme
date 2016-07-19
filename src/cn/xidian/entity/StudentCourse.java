package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="student_course")
public class StudentCourse {  //学生课程表
	
	private Integer stuCursId;//pk
	private Student student;//fk
	private Course course;//fk
		
	private Double MidEvaValue;//期中成绩
	private Double FinEvaValue;//期末成绩
	private Double WorkEvaValue;//作业成绩
	private Double ExpEvaValue;//实验成绩
	private Double ClassEvaValue;//课堂表现成绩
	private Double EvaValue;//综合成绩
	private String SchoolYear;
	
	@Id
	@GeneratedValue
	public Integer getStuCursId() {
		return stuCursId;
	}
	
	@ManyToOne
	@JoinColumn(name="stuId")
	public Student getStudent() {
		return student;
	}
	
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}
	
	
	public Double getMidEvaValue() {
		return MidEvaValue;
	}
	public void setMidEvaValue(Double midEvaValue) {
		MidEvaValue = midEvaValue;
	}
	public Double getFinEvaValue() {
		return FinEvaValue;
	}
	public void setFinEvaValue(Double finEvaValue) {
		FinEvaValue = finEvaValue;
	}
	public Double getWorkEvaValue() {
		return WorkEvaValue;
	}
	public void setWorkEvaValue(Double workEvaValue) {
		WorkEvaValue = workEvaValue;
	}
	public Double getExpEvaValue() {
		return ExpEvaValue;
	}
	public void setExpEvaValue(Double expEvaValue) {
		ExpEvaValue = expEvaValue;
	}
	public Double getClassEvaValue() {
		return ClassEvaValue;
	}
	public void setClassEvaValue(Double classEvaValue) {
		ClassEvaValue = classEvaValue;
	}
	public Double getEvaValue() {
		return EvaValue;
	}
	public void setEvaValue(Double evaValue) {
		EvaValue = evaValue;
	}
	
	public void setStuCursId(Integer stuCursId) {
		this.stuCursId = stuCursId;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSchoolYear() {
		return SchoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		SchoolYear = schoolYear;
	}
	
	
	
}

