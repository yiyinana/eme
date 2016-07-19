package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="indicatorpoint")
public class IndicatorPoint {//毕业要求的小指标点

	private Integer indPointId;//pk
	
	private GraduateRequirement graReq;//fk
	
	private String indPointNum;
	private String indPointContent;
	
	@Id
	@GeneratedValue
	public Integer getIndPointId() {
		return indPointId;
	}
	
	@ManyToOne
	@JoinColumn(name="graReqId")
	public GraduateRequirement getGraReq() {
		return graReq;
	}
	
	

	public String getIndPointNum() {
		return indPointNum;
	}

	public void setIndPointNum(String indPointNum) {
		this.indPointNum = indPointNum;
	}

	public String getIndPointContent() {
		return indPointContent;
	}


	public void setGraReq(GraduateRequirement graReq) {
		this.graReq = graReq;
	}

	public void setIndPointContent(String indPointContent) {
		this.indPointContent = indPointContent;
	}

	public void setIndPointId(Integer indPointId) {
		this.indPointId = indPointId;
	}
	
	
	
}
