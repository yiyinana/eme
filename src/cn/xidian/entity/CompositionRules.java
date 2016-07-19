package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="compositionrules")
public class CompositionRules {

	private Integer compRuleId;// pk

	private Course course;// fk

	private Double midTermPer;
	private Double finalExamPer;
	private Double homeworkResultPer;
	private Double expResultPer;
	private Double clazzPer;
	
	public CompositionRules(){
		super();
	}
	
	@Id
	@GeneratedValue
	public Integer getCompRuleId() {
		return compRuleId;
	}
	
	@OneToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}
	
	
	
	public Double getClazzPer() {
		return clazzPer;
	}
	public Double getExpResultPer() {
		return expResultPer;
	}
	public Double getFinalExamPer() {
		return finalExamPer;
	}
	public Double getHomeworkResultPer() {
		return homeworkResultPer;
	}
	public Double getMidTermPer() {
		return midTermPer;
	}
	public void setClazzPer(Double clazzPer) {
		this.clazzPer = clazzPer;
	}
	public void setCompRuleId(Integer compRuleId) {
		this.compRuleId = compRuleId;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setExpResultPer(Double expResultPer) {
		this.expResultPer = expResultPer;
	}
	public void setFinalExamPer(Double finalExamPer) {
		this.finalExamPer = finalExamPer;
	}
	public void setHomeworkResultPer(Double homeworkResultPer) {
		this.homeworkResultPer = homeworkResultPer;
	}
	public void setMidTermPer(Double midTermPer) {
		this.midTermPer = midTermPer;
	}

	
	
}
