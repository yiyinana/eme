package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="itemevaluatepoint")
public class ItemEvaluatePoint {

	private Integer itemEvaPointId;
	private String  itemEvaPointName;
	
	
	private ItemEvaluateType itemEvaluateType;
	
	@Id
	@GeneratedValue
	public Integer getItemEvaPointId() {
		return itemEvaPointId;
	}
	public void setItemEvaPointId(Integer itemEvaPointId) {
		this.itemEvaPointId = itemEvaPointId;
	}
	public String getItemEvaPointName() {
		return itemEvaPointName;
	}
	public void setItemEvaPointName(String itemEvaPointName) {
		this.itemEvaPointName = itemEvaPointName;
	}
	
	@ManyToOne
	@JoinColumn(name="itemEvaTypeId")
	public ItemEvaluateType getItemEvaluateType() {
		return itemEvaluateType;
	}
	public void setItemEvaluateType(ItemEvaluateType itemEvaluateType) {
		this.itemEvaluateType = itemEvaluateType;
	}
	
	
	
	
}
