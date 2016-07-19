package cn.xidian.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "evaluateresult")
public class EvaluateResult {
	private Integer evaluateResultId;
	private Double M1;
	private Double M2;
	private Double M3;
	private Double M4;
	private Double M5;
	private Student student;
	private String schoolYear;
	private Clazz clazz;
	
	@Id
	@GeneratedValue
	public Integer getEvaluateResultId() {
		return evaluateResultId;
	}

	public void setEvaluateResultId(Integer evaluateResultId) {
		this.evaluateResultId = evaluateResultId;
	}
	

	public Double getM1() {
		return M1;
	}

	public void setM1(Double m1) {
		M1 = m1;
	}

	public Double getM2() {
		return M2;
	}

	public void setM2(Double m2) {
		M2 = m2;
	}

	public Double getM3() {
		return M3;
	}

	public void setM3(Double m3) {
		M3 = m3;
	}

	public Double getM4() {
		return M4;
	}

	public void setM4(Double m4) {
		M4 = m4;
	}

	public Double getM5() {
		return M5;
	}

	public void setM5(Double m5) {
		M5 = m5;
	}

	@ManyToOne
	@JoinColumn(name="stuId")
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	@ManyToOne
	@JoinColumn(name="claId")
	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	
	

}
