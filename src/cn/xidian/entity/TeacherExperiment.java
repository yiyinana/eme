package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="teacherexperiment")
public class TeacherExperiment {//教师经历（学历履历、主持项目等）

	private Integer tchrExpId;//pk
	
	private Teacher teacher;//fk
	
	private String school;//学校或单位
	private String position;//职位
	private String time;//时间
	
	@Id
	@GeneratedValue
	public Integer getTchrExpId() {
		return tchrExpId;
	}
	
	@ManyToOne
	@JoinColumn(name = "tchrId")
	public Teacher getTeacher() {
		return teacher;
	}
	
	
	public void setTchrExpId(Integer tchrExpId) {
		this.tchrExpId = tchrExpId;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
}
