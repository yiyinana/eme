package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemevaluatescore")
public class ItemEvaluateScore {
	private Integer itemEvaScoreId;
	private String itemEvaScoreName;
	private String itemEvaScore;
	private ItemEvaluatePoint itemEvaluatePoint;
	private Integer isTeam;
	private String  itemHeaderScore;
	private String  itemMemberScore;
	private String  itemEvaNote;
	

	@Id
	@GeneratedValue
	public Integer getItemEvaScoreId() {
		return itemEvaScoreId;
	}

	public void setItemEvaScoreId(Integer itemEvaScoreId) {
		this.itemEvaScoreId = itemEvaScoreId;
	}

	public String getItemEvaScoreName() {
		return itemEvaScoreName;
	}

	public void setItemEvaScoreName(String itemEvaScoreName) {
		this.itemEvaScoreName = itemEvaScoreName;
	}

	public String getItemEvaScore() {
		return itemEvaScore;
	}

	public void setItemEvaScore(String itemEvaScore) {
		this.itemEvaScore = itemEvaScore;
	}

	@ManyToOne
	@JoinColumn(name = "itemEvaPointId")
	public ItemEvaluatePoint getItemEvaluatePoint() {
		return itemEvaluatePoint;
	}

	public void setItemEvaluatePoint(ItemEvaluatePoint itemEvaluatePoint) {
		this.itemEvaluatePoint = itemEvaluatePoint;
	}

	public Integer getIsTeam() {
		return isTeam;
	}

	public void setIsTeam(Integer isTeam) {
		this.isTeam = isTeam;
	}

	public String getItemHeaderScore() {
		return itemHeaderScore;
	}

	public void setItemHeaderScore(String itemHeaderScore) {
		this.itemHeaderScore = itemHeaderScore;
	}

	public String getItemMemberScore() {
		return itemMemberScore;
	}

	public void setItemMemberScore(String itemMemberScore) {
		this.itemMemberScore = itemMemberScore;
	}

	public String getItemEvaNote() {
		return itemEvaNote;
	}

	public void setItemEvaNote(String itemEvaNote) {
		this.itemEvaNote = itemEvaNote;
	}
	
	

}
