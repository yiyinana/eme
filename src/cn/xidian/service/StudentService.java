package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.EvaluateResult;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;

public interface StudentService {

	boolean loginValidate(String stuSchNum, String stuPwd);

	Student selectInfBySchNum(String stuSchNum);

	boolean updateInfBySchNum(Student student);
	
	boolean updateGoal(Student student);
	
	boolean modifyPassword(String schNum,String pwd);

	List<StudentCourse> selectStuAllGradesById(Integer id);
	
	EvaluateResult selectEvaluateResult(Integer stuId,String schoolYear);
}