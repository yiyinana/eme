package cn.xidian.web.bean;

public class AdminTchrLimits {

	private String tchrSchNum;
	private String tchrName;
	private String tchrTitle;
	
	
	public AdminTchrLimits() {
		super();
	}
	
	public AdminTchrLimits(String tchrSchNum, String tchrName,
			String tchrTitle) {
		this.tchrSchNum = tchrSchNum;
		this.tchrName = tchrName;
		this.tchrTitle = tchrTitle;
	}
	
	
	public String getTchrSchNum() {
		return tchrSchNum;
	}
	public void setTchrSchNum(String tchrSchNum) {
		this.tchrSchNum = tchrSchNum;
	}
	public String getTchrName() {
		return tchrName;
	}
	public void setTchrName(String tchrName) {
		this.tchrName = tchrName;
	}
	public String getTchrTitle() {
		return tchrTitle;
	}
	public void setTchrTitle(String tchrTitle) {
		this.tchrTitle = tchrTitle;
	}
	
	
	
}
