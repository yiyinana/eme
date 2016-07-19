package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itemevaluatetype")
public class ItemEvaluateType {
	private Integer itemEvaTypeId;
	private String  itemEvaTypeName;
	private String itemEvaTypeMark;
	
	@Id
	@GeneratedValue
	public Integer getItemEvaTypeId() {
		return itemEvaTypeId;
	}
	public void setItemEvaTypeId(Integer itemEvaTypeId) {
		this.itemEvaTypeId = itemEvaTypeId;
	}
	public String getItemEvaTypeName() {
		return itemEvaTypeName;
	}
	public void setItemEvaTypeName(String itemEvaTypeName) {
		this.itemEvaTypeName = itemEvaTypeName;
	}
	public String getItemEvaTypeMark() {
		return itemEvaTypeMark;
	}
	public void setItemEvaTypeMark(String itemEvaTypeMark) {
		this.itemEvaTypeMark = itemEvaTypeMark;
	}

}
