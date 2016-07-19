package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="itemfile")
public class ItemFile {
	private Integer itemFileId;//pk
	
	private StudentItem studentItem;//fk
	
	private String fileName;
	private String saveFileName;
	private String fileType;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Id
	@GeneratedValue
	public Integer getItemFileId(){
		return itemFileId;
	}

	public void setItemFileId(Integer itemFileId) {
		this.itemFileId = itemFileId;
	}
	public String getFileName() {
		return fileName;
	}

	@ManyToOne
	@JoinColumn(name="stuItemId")
	public StudentItem getStudentItem() {
		return studentItem;
	}

	public void setStudentItem(StudentItem studentItem) {
		this.studentItem = studentItem;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}


}
