package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="coursespecificcontent")
public class CourseSpecificContent {

	private Integer cscId;//pk
	
	private Course course;//fk
	
	private String cscChapter;
	private String cscClassHour;//学时
	private String cscTarget;//支撑的教学目标
	private String cscGoal;
	private String cscBasRequ;
	private String cscStudyEmpha;
	private String cscStudyDiffi;
	private String cscHomework;
	
	@Id
	@GeneratedValue
	public Integer getCscId() {
		return cscId;
	}

	
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public String getCscBasRequ() {
		return cscBasRequ;
	}
	public String getCscChapter() {
		return cscChapter;
	}
	
	public String getCscTarget() {
		return cscTarget;
	}


	public void setCscTarget(String cscTarget) {
		this.cscTarget = cscTarget;
	}
	

	public String getCscClassHour() {
		return cscClassHour;
	}


	public void setCscClassHour(String cscClassHour) {
		this.cscClassHour = cscClassHour;
	}


	public String getCscGoal() {
		return cscGoal;
	}
	public String getCscHomework() {
		return cscHomework;
	}
	public String getCscStudyDiffi() {
		return cscStudyDiffi;
	}
	public String getCscStudyEmpha() {
		return cscStudyEmpha;
	}
	public void setCscBasRequ(String cscBasRequ) {
		this.cscBasRequ = cscBasRequ;
	}
	public void setCscChapter(String cscChapter) {
		this.cscChapter = cscChapter;
	}
	public void setCscGoal(String cscGoal) {
		this.cscGoal = cscGoal;
	}
	public void setCscHomework(String cscHomework) {
		this.cscHomework = cscHomework;
	}
	public void setCscId(Integer cscId) {
		this.cscId = cscId;
	}
	public void setCscStudyDiffi(String cscStudyDiffi) {
		this.cscStudyDiffi = cscStudyDiffi;
	}
	public void setCscStudyEmpha(String cscStudyEmpha) {
		this.cscStudyEmpha = cscStudyEmpha;
	}
	
	
	
}
