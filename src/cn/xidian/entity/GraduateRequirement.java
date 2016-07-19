package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="graduaterequirement")
public class GraduateRequirement {//毕业要求的大指标点

	private Integer graReqId;//pk
	
	private String graReqTitle;
	private String graReqContent;
	
	@Id
	@GeneratedValue
	public Integer getGraReqId() {
		return graReqId;
	}
	
	
	public void setGraReqId(Integer graReqId) {
		this.graReqId = graReqId;
	}
	
	public String getGraReqTitle() {
		return graReqTitle;
	}


	public void setGraReqTitle(String graReqTitle) {
		this.graReqTitle = graReqTitle;
	}


	public String getGraReqContent() {
		return graReqContent;
	}
	public void setGraReqContent(String graReqContent) {
		this.graReqContent = graReqContent;
	}
	
	
	
}


