package cn.xidian.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="student")
public class Student{


	private Integer stuId; //pk
	
	private Department dept; //fk
	private Clazz clazz; //fk
		
	private String stuSchNum;
	private String stuPwd;
	private String stuName;
	private Boolean stuGender;
	private Date stuBirthday;
	private String stuNativePlace;
	private String stuNation;
	private String stuMajor;
	private Date stuAttendDate;
	private Integer stuSchLength;
	private String stuPhone;
	private String stuDomiPhone;
	private String stuMail;
	private String stuCommAddr;
	private String stuIdentNum;
	private String selfIntroduce;//自我介绍
	private String selfEngIntroduce;//自我介绍英文版
	private String shortGoal;//短期目标
	private String midGoal;//中期目标
	private String longGoal;//长期目标
	private Integer isDelete;//是否已被删除
	
	@Id
	@GeneratedValue
	public Integer getStuId() {
		return this.stuId;
	}
	
	@ManyToOne
	@JoinColumn(name="claId")
	public Clazz getClazz() {
		return clazz;
	}

	@ManyToOne
	@JoinColumn(name="deptId")
	public Department getDept() {
		return dept;
	}

	@Temporal(TemporalType.DATE)
	public Date getStuAttendDate() {
		return this.stuAttendDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getStuBirthday() {
		return this.stuBirthday;
	}

	
	public String getStuIdentNum() {
		return stuIdentNum;
	}

	public void setStuIdentNum(String stuIdentNum) {
		this.stuIdentNum = stuIdentNum;
	}

	public String getStuCommAddr() {
		return this.stuCommAddr;
	}

	public String getStuDomiPhone() {
		return this.stuDomiPhone;
	}

	public Boolean getStuGender() {
		return this.stuGender;
	}


	public String getStuMail() {
		return this.stuMail;
	}

	public String getStuMajor() {
		return this.stuMajor;
	}

	public String getStuName() {
		return this.stuName;
	}


	public String getStuNation() {
		return this.stuNation;
	}

	public String getStuNativePlace() {
		return this.stuNativePlace;
	}

	public String getStuPhone() {
		return this.stuPhone;
	}

	public String getStuPwd() {
		return stuPwd;
	}

	public Integer getStuSchLength() {
		return this.stuSchLength;
	}

	public String getStuSchNum() {
		return this.stuSchNum;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public void setStuAttendDate(Date stuAttendDate) {
		this.stuAttendDate = stuAttendDate;
	}

	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	public void setStuCommAddr(String stuCommAddr) {
		this.stuCommAddr = stuCommAddr;
	}

	public void setStuDomiPhone(String stuDomiPhone) {
		this.stuDomiPhone = stuDomiPhone;
	}

	public void setStuGender(Boolean stuGender) {
		this.stuGender = stuGender;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public void setStuMail(String stuMail) {
		this.stuMail = stuMail;
	}

	public void setStuMajor(String stuMajor) {
		this.stuMajor = stuMajor;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public void setStuNation(String stuNation) {
		this.stuNation = stuNation;
	}

	public void setStuNativePlace(String stuNativePlace) {
		this.stuNativePlace = stuNativePlace;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}


	public void setStuSchLength(Integer stuSchLength) {
		this.stuSchLength = stuSchLength;
	}

	public void setStuSchNum(String stuSchNum) {
		this.stuSchNum = stuSchNum;
	}

	public String getSelfIntroduce() {
		return selfIntroduce;
	}

	public void setSelfIntroduce(String selfIntroduce) {
		this.selfIntroduce = selfIntroduce;
	}

	public String getSelfEngIntroduce() {
		return selfEngIntroduce;
	}

	public void setSelfEngIntroduce(String selfEngIntroduce) {
		this.selfEngIntroduce = selfEngIntroduce;
	}

	public String getShortGoal() {
		return shortGoal;
	}

	public void setShortGoal(String shortGoal) {
		this.shortGoal = shortGoal;
	}

	public String getMidGoal() {
		return midGoal;
	}

	public void setMidGoal(String midGoal) {
		this.midGoal = midGoal;
	}

	public String getLongGoal() {
		return longGoal;
	}

	public void setLongGoal(String longGoal) {
		this.longGoal = longGoal;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}