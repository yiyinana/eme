package cn.xidian.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseDao;
import cn.xidian.dao.ClazzCoursePointDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.ClazzCoursePoint;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.service.ClazzCoursePointService;

@Component
public class ClazzCoursePointServiceImpl implements ClazzCoursePointService {

	private ClazzCoursePointDao clazzCoursePointDao;

	@Resource(name = "clazzCoursePointDaoImpl")
	public void setClazzCoursePointDao(ClazzCoursePointDao clazzCoursePointDao) {
		this.clazzCoursePointDao = clazzCoursePointDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public List<IndicatorPoint> selectPointByCursId(Integer id) {
		List<IndicatorPoint> points = new LinkedList<IndicatorPoint>();
		List<ClazzCoursePoint> cursPoints = clazzCoursePointDao
				.selectByCursId(id);
		for (int i = 0; i < cursPoints.size(); i++) {
			IndicatorPoint point = cursPoints.get(i).getIndPoint();
			points.add(point);
		}
		return points;
	}

	@Override
	public List<ClazzCoursePoint> selectBycursNameAndTerm(String cursName) {
		List<ClazzCoursePoint> pointlist = new LinkedList<ClazzCoursePoint>();
		pointlist = clazzCoursePointDao.findByCursNameAndTerm(cursName);
		return pointlist;
	}

	@Override
	public boolean addByCursId(List<IndicatorPoint> points, Integer cursId) {
		Course courseTemp = courseDao.findById(cursId);
		if (courseTemp == null) {
			throw new CourseNotExistException("要更新的课程不存在");
		}
		for (int i = 0; i < points.size(); i++) {
			IndicatorPoint point = (IndicatorPoint) points.get(i);
			ClazzCoursePoint cp = new ClazzCoursePoint();
			cp.setCourse(courseTemp);
			cp.setIndPoint(point);
			clazzCoursePointDao.addByCursNum(cp);
		}
		return true;
	}

	@Override
	public boolean modifyByCursId(List<IndicatorPoint> points, Integer cursId) {
		Course courseTemp = courseDao.findById(cursId);
		if (courseTemp == null) {
			throw new CourseNotExistException("要更新的课程不存在");
		}
		clazzCoursePointDao.deleteByCursId(cursId);
		for (int i = 0; i < points.size(); i++) {
			IndicatorPoint point = (IndicatorPoint) points.get(i);
			ClazzCoursePoint cp = new ClazzCoursePoint();
			cp.setCourse(courseTemp);
			cp.setIndPoint(point);
			clazzCoursePointDao.addByCursNum(cp);
		}
		return true;
	}

	@Override
	public List<ClazzCoursePoint> selectByCursAndClazz(String cursName,String claName) {
		List<ClazzCoursePoint> pointlist = new LinkedList<ClazzCoursePoint>();
		pointlist = clazzCoursePointDao.findByCursAndClazz(cursName, 
				claName);
		return pointlist;
	}
}
