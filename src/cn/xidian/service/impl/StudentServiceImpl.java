package cn.xidian.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentDao;
import cn.xidian.entity.EvaluateResult;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;
import cn.xidian.service.StudentService;
import cn.xidian.utils.ServiceUtils;

@Component
public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;

	@Resource(name = "studentDaoImpl")
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public boolean loginValidate(String stuSchNum, String stuPwd) {
		try {
			return studentDao.findBySchNumAndPwd(stuSchNum,
					ServiceUtils.md5(stuPwd));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Student selectInfBySchNum(String stuSchNum) {
		return studentDao.findBySchNum(stuSchNum);
	}

	@Override
	public boolean updateInfBySchNum(Student student) {
		return studentDao.updateByStudent(student);
	}

	@Override
	public boolean updateGoal(Student student) {
		// TODO Auto-generated method stub
		return studentDao.updateGoal(student);
	}

	@Override
	public boolean modifyPassword(String schNum, String pwd){
		String passwordString = "";
		try {
			passwordString = ServiceUtils.md5(pwd);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentDao.modifyPassword(schNum,passwordString);
	}

	@Override
	public List<StudentCourse> selectStuAllGradesById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.selectStuAllGradesById(id);
	}

	@Override
	public EvaluateResult selectEvaluateResult(Integer stuId, String schoolYear) {
		// TODO Auto-generated method stub
		return studentDao.selectEvaluateResult(stuId,schoolYear);
	}

}
