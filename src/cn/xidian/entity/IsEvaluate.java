package cn.xidian.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="isevaluate")
public class IsEvaluate {//冗余设计，记录某个班级的某门课程是否已评定，以及评定人，评定时间
	
	private Integer evaId;
	
	private Course course;
	private Clazz clazz;
	
	private Teacher teacher;
	private Date evaDate;
	
	@Id
	@GeneratedValue
	public Integer getEvaId() {
		return evaId;
	}
	
	@OneToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}
	
	@OneToOne
	@JoinColumn(name="claId")
	public Clazz getClazz() {
		return clazz;
	}
	
	@OneToOne
	@JoinColumn(name="tchrId")
	public Teacher getTeacher() {
		return teacher;
	}
	@Temporal(TemporalType.DATE)
	public Date getEvaDate() {
		return evaDate;
	}

	public void setEvaId(Integer evaId) {
		this.evaId = evaId;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public void setEvaDate(Date evaDate) {
		this.evaDate = evaDate;
	}
	
	
	
}
