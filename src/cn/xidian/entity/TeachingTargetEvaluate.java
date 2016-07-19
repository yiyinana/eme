package cn.xidian.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teachingtargetevaluate")
public class TeachingTargetEvaluate {

	private Integer TchTarEvaId;// pk

	private TeachingTarget teachingTarget;// fk
	private Clazz clazz;// fk

	// 评价值(老师传附件时计算)
	private Double tchtargetMidEvaValue;
	private Double tchtargetFinEvaValue;
	private Double tchtargetWorkEvaValue;
	private Double tchtargetExpEvaValue;
	private Double tchtargetClassEvaValue;

	private Double a1;
	private Double b1;

	@Id
	@GeneratedValue
	public Integer getTchTarEvaId() {
		return TchTarEvaId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tchTargetId")
	public TeachingTarget getTeachingTarget() {
		return teachingTarget;
	}

	@ManyToOne
	@JoinColumn(name = "claId")
	public Clazz getClazz() {
		return clazz;
	}

	public void setTchTarEvaId(Integer tchTarEvaId) {
		TchTarEvaId = tchTarEvaId;
	}

	public void setTeachingTarget(TeachingTarget teachingTarget) {
		this.teachingTarget = teachingTarget;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Double getTchtargetMidEvaValue() {
		return tchtargetMidEvaValue;
	}

	public void setTchtargetMidEvaValue(Double tchtargetMidEvaValue) {
		this.tchtargetMidEvaValue = tchtargetMidEvaValue;
	}

	public Double getTchtargetFinEvaValue() {
		return tchtargetFinEvaValue;
	}

	public void setTchtargetFinEvaValue(Double tchtargetFinEvaValue) {
		this.tchtargetFinEvaValue = tchtargetFinEvaValue;
	}

	public Double getTchtargetWorkEvaValue() {
		return tchtargetWorkEvaValue;
	}

	public void setTchtargetWorkEvaValue(Double tchtargetWorkEvaValue) {
		this.tchtargetWorkEvaValue = tchtargetWorkEvaValue;
	}

	public Double getTchtargetExpEvaValue() {
		return tchtargetExpEvaValue;
	}

	public void setTchtargetExpEvaValue(Double tchtargetExpEvaValue) {
		this.tchtargetExpEvaValue = tchtargetExpEvaValue;
	}

	public Double getTchtargetClassEvaValue() {
		return tchtargetClassEvaValue;
	}

	public void setTchtargetClassEvaValue(Double tchtargetClassEvaValue) {
		this.tchtargetClassEvaValue = tchtargetClassEvaValue;
	}

	public Double getA1() {
		return a1;
	}

	public void setA1(Double a1) {
		this.a1 = a1;
	}

	public Double getB1() {
		return b1;
	}

	public void setB1(Double b1) {
		this.b1 = b1;
	}

}
