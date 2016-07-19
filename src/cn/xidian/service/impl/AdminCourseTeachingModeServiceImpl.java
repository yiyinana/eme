package cn.xidian.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseDao;
import cn.xidian.dao.CourseTeachingModeDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.CourseTeachingMode;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.service.AdminCourseTeachingModeService;

@Component
public class AdminCourseTeachingModeServiceImpl implements
		AdminCourseTeachingModeService {

	private CourseTeachingModeDao courseTeachingModeDao;

	@Resource(name = "courseTeachingModeDaoImpl")
	public void setCourseTeachingModeDao(
			CourseTeachingModeDao courseTeachingModeDao) {
		this.courseTeachingModeDao = courseTeachingModeDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public boolean addTchingModeByCursId(List<CourseTeachingMode> ctms,
			Integer cursId) {
		Course courseTemp = courseDao.findById(cursId);
		if (courseTemp == null) {
			throw new CourseNotExistException("要更新的课程不存在");
		}
		for (int i = 0; i < ctms.size(); i++) {
			CourseTeachingMode ctm = (CourseTeachingMode) ctms.get(i);
			ctm.setCourse(courseTemp);
			courseTeachingModeDao.addCursTchingMode(ctm);
		}
		return true;
	}

	@Override
	public List<CourseTeachingMode> selectCursTchingMode(String cursNum) {
		return courseTeachingModeDao.selectByCourse(cursNum);
	}

	@Override
	public List<CourseTeachingMode> selectTchingModeByCursId(Integer id) {
		return courseTeachingModeDao.selectTchingModeByCursId(id);
	}
	
	@Override
	public boolean updateByCursId(List<CourseTeachingMode> ctms, Integer cursId) {
		// 先delete，后add
		boolean isSuccess = false;
		isSuccess = courseTeachingModeDao.deleteByCursId(cursId);
		if (isSuccess) {
			Course courseTemp = courseDao.findById(cursId);
			Iterator<CourseTeachingMode> itCtms = ctms.iterator();
			CourseTeachingMode ctm = null;
			while (itCtms.hasNext()) {
				ctm = itCtms.next();
				ctm.setCourse(courseTemp);
				courseTeachingModeDao.addCursTchingMode(ctm);
			}
		}
		return true;
	}


}
