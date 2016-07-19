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
@Table(name = "teacher")
public class Teacher{

	private Integer tchrId;// pk

	private Department dept;// fk


	private String tchrSchNum;//
	private String tchrPwd;
	private String tchrName;
	private Boolean tchrGender;
	private Date tchrBirthday;//
	private String tchrDegree;//最高学位
	private String tchrTitle;//专业技术职务，及职称
	private String tchrMajor;//所学专业
	private String tchrGraduateSchool;//毕业院校
	private Date tchrAttendDate;//任职时间
	private String tchrPhone;
	private String tchrFax;
	private String tchrEmail;
	private String tchrOfficeAddr;
	private String tchrSelflIntroduction;
	private String tchrIdentNum;
	private String tchrNation;
	private Integer isManager;//是否为专业负责人
	private Integer isDelete;//0为已删除，1为未删除
	private Integer isCounselor;//判断是否为班主任
	
	@Id
	@GeneratedValue
	public Integer getTchrId() {
		return tchrId;
	}
	
	
	@ManyToOne
	@JoinColumn(name="deptId")
	public Department getDept() {
		return dept;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getTchrBirthday() {
		return tchrBirthday;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getTchrAttendDate() {
		return tchrAttendDate;
	}
	
	public void setTchrId(Integer tchrId) {
		this.tchrId = tchrId;
	}
	
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public String getTchrSchNum() {
		return tchrSchNum;
	}
	public void setTchrSchNum(String tchrSchNum) {
		this.tchrSchNum = tchrSchNum;
	}
	public String getTchrPwd() {
		return tchrPwd;
	}
	public void setTchrPwd(String tchrPwd) {
		this.tchrPwd = tchrPwd;
	}
	public String getTchrName() {
		return tchrName;
	}
	public void setTchrName(String tchrName) {
		this.tchrName = tchrName;
	}
	public Boolean getTchrGender() {
		return tchrGender;
	}
	public void setTchrGender(Boolean tchrGender) {
		this.tchrGender = tchrGender;
	}
	public void setTchrBirthday(Date tchrBirthday) {
		this.tchrBirthday = tchrBirthday;
	}
	public String getTchrDegree() {
		return tchrDegree;
	}
	public void setTchrDegree(String tchrDegree) {
		this.tchrDegree = tchrDegree;
	}
	public String getTchrTitle() {
		return tchrTitle;
	}
	public void setTchrTitle(String tchrTitle) {
		this.tchrTitle = tchrTitle;
	}
	public String getTchrMajor() {
		return tchrMajor;
	}
	public void setTchrMajor(String tchrMajor) {
		this.tchrMajor = tchrMajor;
	}
	public String getTchrGraduateSchool() {
		return tchrGraduateSchool;
	}
	public void setTchrGraduateSchool(String tchrGraduateSchool) {
		this.tchrGraduateSchool = tchrGraduateSchool;
	}
	public void setTchrAttendDate(Date tchrAttendDate) {
		this.tchrAttendDate = tchrAttendDate;
	}
	public String getTchrPhone() {
		return tchrPhone;
	}
	public void setTchrPhone(String tchrPhone) {
		this.tchrPhone = tchrPhone;
	}
	public String getTchrFax() {
		return tchrFax;
	}
	public void setTchrFax(String tchrFax) {
		this.tchrFax = tchrFax;
	}
	public String getTchrEmail() {
		return tchrEmail;
	}
	public void setTchrEmail(String tchrEmail) {
		this.tchrEmail = tchrEmail;
	}
	public String getTchrOfficeAddr() {
		return tchrOfficeAddr;
	}
	public void setTchrOfficeAddr(String tchrOfficeAddr) {
		this.tchrOfficeAddr = tchrOfficeAddr;
	}
	public String getTchrSelflIntroduction() {
		return tchrSelflIntroduction;
	}
	public void setTchrSelflIntroduction(String tchrSelflIntroduction) {
		this.tchrSelflIntroduction = tchrSelflIntroduction;
	}
	public String getTchrIdentNum() {
		return tchrIdentNum;
	}
	public void setTchrIdentNum(String tchrIdentNum) {
		this.tchrIdentNum = tchrIdentNum;
	}
	public String getTchrNation() {
		return tchrNation;
	}
	public void setTchrNation(String tchrNation) {
		this.tchrNation = tchrNation;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}


	public Integer getIsManager() {
		return isManager;
	}


	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}


	public Integer getIsCounselor() {
		return isCounselor;
	}


	public void setIsCounselor(Integer isCounselor) {
		this.isCounselor = isCounselor;
	}
	
	
	
}
