package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="grade_course_point")
public class GradeCoursePoint {//某年级某课程某指标点的达成度评价值
	
	private Integer gcpId;//pk
	
	private IndicatorPoint point;//fk
	private Course course;//fk
	
	private String grade;//哪一级，16.5.9新增
	private Double cursPower;//课程对毕业要求指标点的权重值
	private Double cursEvaValue;//课程指标点评价值
	
	@Id
	@GeneratedValue
	public Integer getGcpId() {
		return gcpId;
	}
	
	@ManyToOne
	@JoinColumn(name="indPointId")
	public IndicatorPoint getPoint() {
		return point;
	}
	
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}
	
	
	public void setGcpId(Integer gcpId) {
		this.gcpId = gcpId;
	}
	public void setPoint(IndicatorPoint point) {
		this.point = point;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Double getCursPower() {
		return cursPower;
	}
	public void setCursPower(Double cursPower) {
		this.cursPower = cursPower;
	}
	public Double getCursEvaValue() {
		return cursEvaValue;
	}
	public void setCursEvaValue(Double cursEvaValue) {
		this.cursEvaValue = cursEvaValue;
	}
	
	
	
	
	

}
