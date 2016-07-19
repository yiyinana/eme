package cn.xidian.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="majorinformation")
public class MajorInformation {
	
	private Integer majorInfId;
	
	private String majorIntr;
	private String eduTarget;
	private String graduateDemand;
	
	public MajorInformation(){
		super();
	}
	
	@Id
	@GeneratedValue
	public Integer getMajorInfId() {
		return majorInfId;
	}
	public void setMajorInfId(Integer majorInfId) {
		this.majorInfId = majorInfId;
	}
	
	@Lob   
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="text")  
	public String getMajorIntr() {
		return majorIntr;
	}
	public void setMajorIntr(String majorIntr) {
		this.majorIntr = majorIntr;
	}
	@Lob   
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="text")  
	public String getEduTarget() {
		return eduTarget;
	}
	public void setEduTarget(String eduTarget) {
		this.eduTarget = eduTarget;
	}
	@Lob   
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="text")  
	public String getGraduateDemand() {
		return graduateDemand;
	}
	public void setGraduateDemand(String graduateDemand) {
		this.graduateDemand = graduateDemand;
	}
	
	
		
	
}
