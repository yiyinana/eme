package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.TeacherDao;
import cn.xidian.dao.TeacherExperimentDao;
import cn.xidian.entity.Teacher;
import cn.xidian.entity.TeacherExperiment;
import cn.xidian.exception.TeacherNotExistException;
import cn.xidian.service.TeacherExperimentService;

@Component
public class TeacherExperimentServiceImpl implements TeacherExperimentService {

	private TeacherDao teacherDao;

	@Resource(name = "teacherDaoImpl")
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	private TeacherExperimentDao teacherExperimentDao;

	@Resource(name = "teacherExperimentDaoImpl")
	public void setTeacherExperimentDao(
			TeacherExperimentDao teacherExperimentDao) {
		this.teacherExperimentDao = teacherExperimentDao;
	}

	@Override
	public boolean addByTchrNum(TeacherExperiment experiment, String tchrNum) {
		Teacher tempTeacher = teacherDao.findBySchNum(tchrNum);
		if (tempTeacher == null) {
			throw new TeacherNotExistException("教师不存在！");
		}
		experiment.setTeacher(tempTeacher);
		teacherExperimentDao.add(experiment);
		return true;
	}

	@Override
	public List<TeacherExperiment> selectByTchrNum(String tchrNum) {
		Teacher tempTeacher = teacherDao.findBySchNum(tchrNum);
		if (tempTeacher == null) {
			throw new TeacherNotExistException("教师不存在！");
		}
		return teacherExperimentDao.selectByTchrNum(tchrNum);
	}

	@Override
	public boolean deleteById(Integer expId) {
		return teacherExperimentDao.deleteById(expId);
	}

}
