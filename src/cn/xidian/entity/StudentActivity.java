package cn.xidian.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="studentactivity")
public class StudentActivity {
	
	private Integer stuActId;//pk
	
	private Student student;//fk
	
	private Date actTime;//活动时间
	private String actName;//活动名称
	private String unit;//主办单位
	private String duty;//职责
	private Boolean state; //状态
	private String type;//活动类型
	
	@Id
	@GeneratedValue
	public Integer getStuActId() {
		return stuActId;
	}
	
	@ManyToOne
	@JoinColumn(name="stuId")
	public Student getStudent() {
		return student;
	}
	
	public void setStuActId(Integer stuActId) {
		this.stuActId = stuActId;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Temporal(TemporalType.DATE)
	public Date getActTime() {
		return actTime;
	}
	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
