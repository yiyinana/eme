package cn.xidian.service.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseDao;
import cn.xidian.dao.TeachingTargetDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.TeachingTarget;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.service.TeachingTargetService;

@Component
public class TeachingTargetServiceImpl implements TeachingTargetService {

	private TeachingTargetDao teachingTargetDao;

	@Resource(name = "teachingTargetDaoImpl")
	public void setTeachingTargetDao(TeachingTargetDao teachingTargetDao) {
		this.teachingTargetDao = teachingTargetDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public boolean addTchingTargetByCursId(List<TeachingTarget> targets,
			Integer cursId) {
		Course courseTemp = courseDao.findById(cursId);
		if (courseTemp == null) {
			throw new CourseNotExistException("要更新的课程不存在");
		}
		for (int i = 0; i < targets.size(); i++) {
			TeachingTarget tt = (TeachingTarget) targets.get(i);
			tt.setCourse(courseTemp);
			teachingTargetDao.addTchingTarget(tt);
		}
		return true;
	}

	@Override
	public boolean updateTchingTargetValue(List<TeachingTarget> targets) {
		for (int i = 0; i < targets.size(); i++) {
			TeachingTarget tt = (TeachingTarget) targets.get(i);
			teachingTargetDao.updateTchingTarget(tt);
		}
		return true;
	}

	@Override
	public List<TeachingTarget> selectByCursId(Integer cursId) {
		return teachingTargetDao.selectByCursId(cursId);
	}

	@Override
	public List<TeachingTarget> selectByCursName(String cursName) {
		Course courseTemp = courseDao.findByNameAndTerm(cursName);
		if (courseTemp == null) {
			throw new CourseNotExistException("课程不存在");
		}
		return teachingTargetDao.selectByCursId(courseTemp.getCursId());
	}
	
	@Override
	public boolean updateByCursId(List<TeachingTarget> targets, Integer cursId) {
		// 先delete(必须以对象形式删除)，后add
		List<TeachingTarget> targetsTemp = new LinkedList<TeachingTarget>();
		targetsTemp = teachingTargetDao.selectByCursId(cursId);
		Iterator<TeachingTarget> itTargetsTemp = targetsTemp.iterator();
		while (itTargetsTemp.hasNext()) {
			teachingTargetDao.deleteTchingTarget(itTargetsTemp.next());
		}
		Course courseTemp = courseDao.findById(cursId);
		Iterator<TeachingTarget> itTarget = targets.iterator();
		TeachingTarget teachingTarget = null;
		while (itTarget.hasNext()) {
			teachingTarget = itTarget.next();
			teachingTarget.setCourse(courseTemp);
			teachingTargetDao.addTchingTarget(teachingTarget);
		}
		return true;
	}

	@Override
	public boolean updateValue(List<TeachingTarget> targets) {
		// 根据tchTargetId更新value
		Iterator<TeachingTarget> itTarget = targets.iterator();
		while (itTarget.hasNext()) {
			teachingTargetDao.updateTchingTarget(itTarget.next());
		}
		return true;
	}

}
