package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="majortargetvalue")
public class MajorTargetValue {//专业达成度，即该专业一届每个指标点的达成度评价值

	private Integer majTarValId;//pk
	
	private IndicatorPoint point;//fk
	
	private String grade;
	private Double value;
	
	
	@Id
	@GeneratedValue
	public Integer getMajTarValId() {
		return majTarValId;
	}
	
	@OneToOne
	@JoinColumn(name="indPointId")
	public IndicatorPoint getPoint() {
		return point;
	}
	
	
	public void setMajTarValId(Integer majTarValId) {
		this.majTarValId = majTarValId;
	}
	public void setPoint(IndicatorPoint point) {
		this.point = point;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}
