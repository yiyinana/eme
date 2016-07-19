package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="coursereferencebooks")
public class CourseReferenceBooks {

	private Integer crbId;//pk
	
	private Course course;//fk
	
	private String crbAuthor;
	private String crbName;
	private String crbPublisher;
	private String crbPubYear;
	
	@Id
	@GeneratedValue
	public Integer getCrbId() {
		return crbId;
	}
	
	
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public String getCrbAuthor() {
		return crbAuthor;
	}
	public String getCrbName() {
		return crbName;
	}
	public String getCrbPublisher() {
		return crbPublisher;
	}
	public String getCrbPubYear() {
		return crbPubYear;
	}
	public void setCrbAuthor(String crbAuthor) {
		this.crbAuthor = crbAuthor;
	}
	public void setCrbId(Integer crbId) {
		this.crbId = crbId;
	}
	public void setCrbName(String crbName) {
		this.crbName = crbName;
	}
	public void setCrbPublisher(String crbPublisher) {
		this.crbPublisher = crbPublisher;
	}
	public void setCrbPubYear(String crbPubYear) {
		this.crbPubYear = crbPubYear;
	}
	
	
	
	
}
