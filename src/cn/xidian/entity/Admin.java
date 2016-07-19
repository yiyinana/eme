package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {

	private Integer adminId;
	private String adminSchNum;
	private String adminName;
	private String adminPwd;
	private Integer isDelete;
	
	@Id
	@GeneratedValue
	public Integer getAdminId() {
		return adminId;
	}
	
	public String getAdminName() {
		return adminName;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public String getAdminSchNum() {
		return adminSchNum;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public void setAdminSchNum(String adminSchNum) {
		this.adminSchNum = adminSchNum;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
	
}
