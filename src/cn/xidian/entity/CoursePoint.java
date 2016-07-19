package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="course_point")
public class CoursePoint {//课程-指标点对应关系,冗余表
	
	private Integer cursPointId;//pk
	
	private Course course;//fk
	private IndicatorPoint indPoint;//fk
			
	@Id
	@GeneratedValue
	public Integer getCursPointId() {
		return cursPointId;
	}
	
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}
	@ManyToOne
	@JoinColumn(name="indPointId")
	public IndicatorPoint getIndPoint() {
		return indPoint;
	}

	public void setCursPointId(Integer cursPointId) {
		this.cursPointId = cursPointId;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setIndPoint(IndicatorPoint indPoint) {
		this.indPoint = indPoint;
	}

}
