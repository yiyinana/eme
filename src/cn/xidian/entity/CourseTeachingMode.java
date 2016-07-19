package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="courseteachingmode")
public class CourseTeachingMode {

	private Integer teacModId;//pk
	
	private Course course;//fk
	
	private String cursContent;
	private String period;
	private String teacMethod;
	
	
	@Id
	@GeneratedValue
	public Integer getTeacModId() {
		return teacModId;
	}
	
	
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public String getCursContent() {
		return cursContent;
	}
	public String getPeriod() {
		return period;
	}
	public String getTeacMethod() {
		return teacMethod;
	}
	public void setCursContent(String cursContent) {
		this.cursContent = cursContent;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public void setTeacMethod(String teacMethod) {
		this.teacMethod = teacMethod;
	}
	public void setTeacModId(Integer teacModId) {
		this.teacModId = teacModId;
	}
	
	
	
}
