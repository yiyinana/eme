package cn.xidian.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
	
@Entity
@Table(name="teachingtarget")
public class TeachingTarget {
	
	private Integer tchTargetId;// pk
	
	private Course course;// fk   多对一(course)
	
	private String tchTarContent;//管理员设置教学目标内容
	

	private Set<TeachingTargetEvaluate> tte = new HashSet<TeachingTargetEvaluate>();
	private Set<ContributeTarget> ct = new HashSet<ContributeTarget>();
	
	//期望值(管理员设置)
	private Double tchtargetMidTargetValue;
	private Double tchtargetFinTargetValue;
	private Double tchtargetHomeworkTargetValue;
	private Double tchtargetExpTargetValue;
	private Double tchtargetClassTargetValue;
	//private Double tchtargetTargetValue;//五项加和
	
	@Id
	@GeneratedValue
	public Integer getTchTargetId() {
		return tchTargetId;
	}
	
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}
	

	

	public String getTchTarContent() {
		return tchTarContent;
	}

	public void setTchTarContent(String tchTarContent) {
		this.tchTarContent = tchTarContent;
	}

	public Double getTchtargetClassTargetValue() {
		return tchtargetClassTargetValue;
	}
	public Double getTchtargetExpTargetValue() {
		return tchtargetExpTargetValue;
	}
	public Double getTchtargetFinTargetValue() {
		return tchtargetFinTargetValue;
	}
	public Double getTchtargetHomeworkTargetValue() {
		return tchtargetHomeworkTargetValue;
	}
	public Double getTchtargetMidTargetValue() {
		return tchtargetMidTargetValue;
	}
	/*public Double getTchtargetTargetValue() {
		return tchtargetTargetValue;
	}*/
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setTchtargetClassTargetValue(Double tchtargetClassTargetValue) {
		this.tchtargetClassTargetValue = tchtargetClassTargetValue;
	}
	public void setTchTargetContent(String tchTargetContent) {
		this.tchTarContent = tchTargetContent;
	}
	public void setTchtargetExpTargetValue(Double tchtargetExpTargetValue) {
		this.tchtargetExpTargetValue = tchtargetExpTargetValue;
	}
	public void setTchtargetFinTargetValue(Double tchtargetFinTargetValue) {
		this.tchtargetFinTargetValue = tchtargetFinTargetValue;
	}
	public void setTchtargetHomeworkTargetValue(Double tchtargetHomeworkTargetValue) {
		this.tchtargetHomeworkTargetValue = tchtargetHomeworkTargetValue;
	}
	public void setTchTargetId(Integer tchTargetId) {
		this.tchTargetId = tchTargetId;
	}
	public void setTchtargetMidTargetValue(Double tchtargetMidTargetValue) {
		this.tchtargetMidTargetValue = tchtargetMidTargetValue;
	}
	/*public void setTchtargetTargetValue(Double tchtargetTargetValue) {
		this.tchtargetTargetValue = tchtargetTargetValue;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teachingTarget")
	public Set<TeachingTargetEvaluate> getTte() {
		return tte;
	}

	public void setTte(Set<TeachingTargetEvaluate> tte) {
		this.tte = tte;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teachingTarget")
	public Set<ContributeTarget> getCt() {
		return ct;
	}

	public void setCt(Set<ContributeTarget> ct) {
		this.ct = ct;
	}

	
}
