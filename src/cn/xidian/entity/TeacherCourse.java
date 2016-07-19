package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="teacher_course")
public class TeacherCourse {
	
	private Integer tchrCursId;//pk
	
	private Teacher teacher;//fk
	private Course course;//fk
	
	@Id
	@GeneratedValue
	public Integer getTchrCursId() {
		return tchrCursId;
	}
	@ManyToOne
	@JoinColumn(name="tchrId")
	public Teacher getTeacher() {
		return teacher;
	}
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}
	public void setTchrCursId(Integer tchrCursId) {
		this.tchrCursId = tchrCursId;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	

}
