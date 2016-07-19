package cn.xidian.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.ClazzCoursePointDao;
import cn.xidian.dao.ClazzDao;
import cn.xidian.dao.CourseDao;
import cn.xidian.dao.CoursePointDao;
import cn.xidian.dao.GradeCoursePointDao;
import cn.xidian.dao.TeachingTargetEvaluateDao;
import cn.xidian.entity.Clazz;
import cn.xidian.entity.ClazzCoursePoint;
import cn.xidian.entity.Course;
import cn.xidian.entity.CoursePoint;
import cn.xidian.entity.GradeCoursePoint;
import cn.xidian.entity.TeachingTargetEvaluate;
import cn.xidian.exception.ClazzCoursePointNotExistException;
import cn.xidian.exception.CourseExistsException;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.service.CourseService;

@Component
public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	private ClazzDao clazzDao;

	@Resource(name = "clazzDaoImpl")
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}

	private TeachingTargetEvaluateDao teachingTargetEvaluateDao;

	@Resource(name = "teachingTargetEvaluateDaoImpl")
	public void setTeachingTargetEvaluateDao(
			TeachingTargetEvaluateDao teachingTargetEvaluateDao) {
		this.teachingTargetEvaluateDao = teachingTargetEvaluateDao;
	}

	private CoursePointDao coursePointDao;

	@Resource(name = "coursePointDaoImpl")
	public void setCoursePointDao(CoursePointDao coursePointDao) {
		this.coursePointDao = coursePointDao;
	}

	private GradeCoursePointDao gradeCoursePointDao;

	@Resource(name = "gradeCoursePointDaoImpl")
	public void setGradeCoursePointDao(GradeCoursePointDao gradeCoursePointDao) {
		this.gradeCoursePointDao = gradeCoursePointDao;
	}

	private ClazzCoursePointDao clazzCoursePointDao;

	@Resource(name = "clazzCoursePointDaoImpl")
	public void setClazzCoursePointDao(ClazzCoursePointDao clazzCoursePointDao) {
		this.clazzCoursePointDao = clazzCoursePointDao;
	}

	@Override
	public boolean addCursBasicInf(Course course) {
		Course courseTemp1 = courseDao.findByNum(course.getCursNum());
		Course courseTemp2 = courseDao.findByNameAndTerm(course.getCursName());
		if (courseTemp1 != null || courseTemp2 != null) {
			throw new CourseExistsException("该课程已存在，请勿重复添加");
		}

		return courseDao.addByAdmin(course);
	}

	@Override
	public Course findByNum(String cursNum) {
		return courseDao.findByNum(cursNum);
	}

	@Override
	public Course findById(Integer cursId) {
		return courseDao.findById(cursId);
	}

	@Override
	public boolean updateByAdmin(Course course) {
		Course courseTemp = courseDao.findById(course.getCursId());
		if (courseTemp == null) {
			throw new CourseNotExistException("课程不存在");
		}
		return courseDao.updateByAdmin(course);
	}

	@Override
	public List<TeachingTargetEvaluate> selectByCursNameAndGrade(String cursName,String grade) {
		List<TeachingTargetEvaluate> ttelist = new LinkedList<TeachingTargetEvaluate>();
		ttelist = teachingTargetEvaluateDao.selectByCursNameAndGrade(cursName,grade);
		return ttelist;
	}

	@Override
	public List<Course> selectByCurTerm() {
		return courseDao.findByTerm();
	}

	@Override
	public boolean caculateCursTargetValueByCursIdAndGrade(Integer cursId,
			String grade) {

		List<ClazzCoursePoint> ccps = new LinkedList<ClazzCoursePoint>();// 找出该年级门课程所有评价值
		List<Clazz> clazzs = clazzDao.selectByGrade(grade);
		for (int i = 0; i < clazzs.size(); i++) {
			ccps.addAll(clazzCoursePointDao.selectByCursAndClazzId(cursId,
					clazzs.get(i).getClaId()));
		}
		List<CoursePoint> cps = coursePointDao.selectByCursId(cursId);// 找出该课程对应的所有毕业要求指标点

		if (ccps.size() == 0) {
			throw new ClazzCoursePointNotExistException("未找到可以参与评价的数据");
		}

		Integer n = ccps.size() / cps.size();// 总共有几个班级

		for (int i = 0; i < cps.size(); i++) {// 课程对指标点的达成度评价值等于每个班级该门课程评价值的平均值
			Double evaluateValue = 0.0;
			for (int j = 0; j < ccps.size(); j++) {
				if (ccps.get(j).getIndPoint().getIndPointId() == cps.get(i)
						.getIndPoint().getIndPointId()) {
					evaluateValue += ccps.get(j).getB2();
				}
			}

			List<GradeCoursePoint> gcpTemp = gradeCoursePointDao
					.selectByCursIdAndPointId(cursId, cps.get(i).getIndPoint()
							.getIndPointId());
			if (gcpTemp.size() > 0) {
				for (int k = 0; k < gcpTemp.size(); k++) {
					gradeCoursePointDao.delete(gcpTemp.get(k));
				}
			}

			Course course = new Course();
			course.setCursId(cursId);
			GradeCoursePoint gcp = new GradeCoursePoint();// 某年级某课程某指标点达成度评价值
			gcp.setCourse(course);
			gcp.setPoint(cps.get(i).getIndPoint());
			gcp.setGrade(grade);
			gcp.setCursEvaValue(evaluateValue / n);
			gradeCoursePointDao.save(gcp);
		}

		return true;
	}

	@Override
	public boolean updateByTchr(Course course) {
		Course courseTemp = courseDao.findById(course.getCursId());
		if (courseTemp == null) {
			throw new CourseNotExistException("课程不存在");
		}
		return courseDao.updateByTchr(course);
	}

	@Override
	public Course findByName(String cursName) {
		return courseDao.findByName(cursName);
	}

	@Override
	public boolean updateCourseNote(Course course) {
		return courseDao.updateCursNote(course);
	}
}
