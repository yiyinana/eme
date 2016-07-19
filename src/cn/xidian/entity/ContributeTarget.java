package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

@Entity
@Table(name = "contribute_target")
public class ContributeTarget {

	private Integer conTarId;// pk

	private TeachingTarget teachingTarget;// fk
	private IndicatorPoint indicatorPoint;// fk

	private Double conTarValue;

	public ContributeTarget() {
		super();
	}

	@Id
	@GeneratedValue
	public Integer getConTarId() {
		return conTarId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tchTargetId")
	public TeachingTarget getTeachingTarget() {
		return teachingTarget;
	}

	@ManyToOne
	@JoinColumn(name = "indPointId")
	public IndicatorPoint getIndicatorPoint() {
		return indicatorPoint;
	}

	public Double getConTarValue() {
		return conTarValue;
	}

	public void setConTarId(Integer conTarId) {
		this.conTarId = conTarId;
	}

	public void setConTarValue(Double conTarValue) {
		this.conTarValue = conTarValue;
	}

	public void setIndicatorPoint(IndicatorPoint indicatorPoint) {
		this.indicatorPoint = indicatorPoint;
	}

	public void setTeachingTarget(TeachingTarget teachingTarget) {
		this.teachingTarget = teachingTarget;
	}

}
