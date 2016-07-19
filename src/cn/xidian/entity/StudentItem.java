package cn.xidian.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studentitem")
public class StudentItem {// 学生参加的项目，获奖等

	private Integer stuItemId;// pk

	
	private String itemNum;// 项目编号
	private String itemName;// 项目名称
	private String itemUnit;// 主办单位
	private String itemPrizeObject;// 项目获奖对象
	private String itemRole;// 是否是项目负责人或第一作者
	private String itemScore;// 得分
	private String note;// 备注
	private String itemState;// 项目审核状态
	private Date itemSubmitDate;
	private Student student;// fk
	private ItemEvaluateType itemEvaluateType;//fk
	private ItemEvaluatePoint itemEvaluatePoint;//fk
	private ItemEvaluateScore itemEvaluateScore;//fk 
	
	/*private String itemType;// 项目类型
	private String itemPoint;// 项目评价点
	private String itemGrade;// 项目等级
	private String itemAward;// 获奖情况
	private String itemContent;// 项目的内容
*/
	@Id
	@GeneratedValue
	public Integer getStuItemId() {
		return stuItemId;
	}

	@ManyToOne
	@JoinColumn(name = "stuId")
	public Student getStudent() {
		return student;
	}

	public void setStuItemId(Integer stuItemId) {
		this.stuItemId = stuItemId;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public String getItemScore() {
		return itemScore;
	}

	public void setItemScore(String itemScore) {
		this.itemScore = itemScore;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getItemState() {
		return itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}


	public String getItemPrizeObject() {
		return itemPrizeObject;
	}

	public void setItemPrizeObject(String itemPrizeObject) {
		this.itemPrizeObject = itemPrizeObject;
	}

	public String getItemRole() {
		return itemRole;
	}

	public void setItemRole(String itemRole) {
		this.itemRole = itemRole;
	}

	@ManyToOne
	@JoinColumn(name = "itemEvaTypeId")
	public ItemEvaluateType getItemEvaluateType() {
		return itemEvaluateType;
	}
	public void setItemEvaluateType(ItemEvaluateType itemEvaluateType) {
		this.itemEvaluateType = itemEvaluateType;
	}
	
	@ManyToOne
	@JoinColumn(name = "itemEvaPointId")
	public ItemEvaluatePoint getItemEvaluatePoint() {
		return itemEvaluatePoint;
	}
	public void setItemEvaluatePoint(ItemEvaluatePoint itemEvaluatePoint) {
		this.itemEvaluatePoint = itemEvaluatePoint;
	}
	
	@ManyToOne
	@JoinColumn(name = "itemEvaScoreId")
	public ItemEvaluateScore getItemEvaluateScore() {
		return itemEvaluateScore;
	}

	public void setItemEvaluateScore(ItemEvaluateScore itemEvaluateScore) {
		this.itemEvaluateScore = itemEvaluateScore;
	}

	public Date getItemSubmitDate() {
		return itemSubmitDate;
	}

	public void setItemSubmitDate(Date itemSubmitDate) {
		this.itemSubmitDate = itemSubmitDate;
	}
}
