package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="clazz_course_point")
public class ClazzCoursePoint {//课程-指标点对应关系
	
	private Integer clazzCursPointId;//pk
	
	private Course course;//fk
	private IndicatorPoint indPoint;//fk
	private Clazz clazz;// fk
	
	private Double targetTarValue;//各指标点达成度目标值
	
	private Double a2;
	private Double b2;
	
	@Id
	@GeneratedValue
	public Integer getClazzCursPointId() {
		return clazzCursPointId;
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
	@ManyToOne
	@JoinColumn(name = "claId")
	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public void setClazzCursPointId(Integer clazzCursPointId) {
		this.clazzCursPointId = clazzCursPointId;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setIndPoint(IndicatorPoint indPoint) {
		this.indPoint = indPoint;
	}
	

	public Double getTargetTarValue() {
		return targetTarValue;
	}

	public void setTargetTarValue(Double targetTarValue) {
		this.targetTarValue = targetTarValue;
	}

	public Double getA2() {
		return a2;
	}

	public void setA2(Double a2) {
		this.a2 = a2;
	}

	public Double getB2() {
		return b2;
	}

	public void setB2(Double b2) {
		this.b2 = b2;
	}
	
	
}
