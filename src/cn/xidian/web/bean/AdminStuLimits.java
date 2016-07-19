package cn.xidian.web.bean;

public class AdminStuLimits {
	private String stuSchNum;
	private String stuName;
	private Integer stuClazz;
	
	public AdminStuLimits() {
		super();
	}
	
	public AdminStuLimits(String stuSchNum, String stuName,
			Integer stuClazz) {
		this.stuSchNum = stuSchNum;
		this.stuName = stuName;
		this.stuClazz = stuClazz;
	}
	
	public String getStuSchNum() {
		return stuSchNum;
	}
	public void setStuSchNum(String stuSchNum) {
		this.stuSchNum = stuSchNum;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Integer getStuClazz() {
		return stuClazz;
	}

	public void setStuClazz(Integer stuClazz) {
		this.stuClazz = stuClazz;
	}
	
}
