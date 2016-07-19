package cn.xidian.entity;

public class User {

	private String schNum;
	private String userName;
	private String pwd;
	private Identity identity;
	private Integer isManager;
	private Integer isCounselor;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSchNum() {
		return schNum;
	}
	public void setSchNum(String schNum) {
		this.schNum = schNum;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
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
