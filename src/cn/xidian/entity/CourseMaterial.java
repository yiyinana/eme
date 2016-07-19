package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="coursematerial")
public class CourseMaterial {

	private Integer cmId;//pk
	
	private Course course;//fk
	
	private String cmAuthor;
	private String cmName;
	private String cmPublisher;
	private String cmPubYear;
	
	@Id
	@GeneratedValue
	public Integer getCmId() {
		return cmId;
	}
	
	
	@ManyToOne
	@JoinColumn(name="cursId")
	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public String getCmAuthor() {
		return cmAuthor;
	}
	public String getCmName() {
		return cmName;
	}
	public String getCmPublisher() {
		return cmPublisher;
	}
	public String getCmPubYear() {
		return cmPubYear;
	}
	public void setCmAuthor(String cmAuthor) {
		this.cmAuthor = cmAuthor;
	}
	public void setCmId(Integer cmId) {
		this.cmId = cmId;
	}
	public void setCmName(String cmName) {
		this.cmName = cmName;
	}
	public void setCmPublisher(String cmPublisher) {
		this.cmPublisher = cmPublisher;
	}
	public void setCmPubYear(String cmPubYear) {
		this.cmPubYear = cmPubYear;
	}
	
	
	
	
}
