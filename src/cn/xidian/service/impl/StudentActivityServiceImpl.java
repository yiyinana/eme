package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentActivityDao;
import cn.xidian.entity.StudentActivity;
import cn.xidian.service.StudentActivityService;

@Component("studentActivityServiceImpl")
public class StudentActivityServiceImpl implements StudentActivityService {

	private StudentActivityDao studentActivityDao;

	@Resource(name = "studentActivityDaoImpl")
	public void setStudentActivityDao(StudentActivityDao studentActivityDao) {
		this.studentActivityDao = studentActivityDao;
	}

	@Override
	public boolean deleteById(Integer id) {
		return studentActivityDao.deleteById(id);
	}

	@Override
	public boolean add(StudentActivity activity) {
		return studentActivityDao.add(activity);
	}

	@Override
	public List<StudentActivity> selectByStuNum(String stuNum) {
		return studentActivityDao.selectByStuNum(stuNum);
	}

}
