package cn.xidian.web.bean;

public class AdminCursLimits {
	private String cursNum;
	private String cursName;
	
	public AdminCursLimits() {
		super();
	}
	
	public AdminCursLimits(String cursNum, String cursName) {
		this.cursNum = cursNum;
		this.cursName = cursName;
	}
	
	public String getCursNum() {
		return cursNum;
	}
	public void setCursNum(String cursNum) {
		this.cursNum = cursNum;
	}
	public String getCursName() {
		return cursName;
	}
	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	
	
}
