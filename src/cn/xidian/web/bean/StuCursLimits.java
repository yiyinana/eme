package cn.xidian.web.bean;

public class StuCursLimits {

	private String tchrSchNum;
	private String claName;
	private String cursName;

	public StuCursLimits() {
		super();
	}

	public StuCursLimits(String tchrSchNum, String claName, String cursName) {
		this.tchrSchNum = tchrSchNum;
		this.claName = claName;
		this.cursName = cursName;
	}

	public String getTchrSchNum() {
		return tchrSchNum;
	}

	public void setTchrSchNum(String tchrSchNum) {
		this.tchrSchNum = tchrSchNum;
	}

	public String getClaName() {
		return claName;
	}

	public void setClaName(String claName) {
		this.claName = claName;
	}

	public String getCursName() {
		return cursName;
	}

	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
}
