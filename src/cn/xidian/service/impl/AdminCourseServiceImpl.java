package cn.xidian.service.impl;

import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseDao;
import cn.xidian.entity.Course;
import cn.xidian.service.AdminCourseService;
import cn.xidian.web.bean.AdminCursLimits;

@Component
public class AdminCourseServiceImpl implements AdminCourseService {

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Set<Course> selectAllCurs() {
		return courseDao.findAllCurs();
	}

	@Override
	public Set<Course> selectCursLimits(AdminCursLimits limits) {

		if (limits == null) {
			return null;
		}
		
		Set<Course> retCourses = new LinkedHashSet<Course>();
		retCourses.add(courseDao.findByNum(limits.getCursNum()));
		retCourses.add(courseDao.findByNameAndTerm(limits.getCursName()));
		retCourses.remove(null);

		Set<Object> set = new LinkedHashSet<Object>();
		Course course;
		String cursNum = limits.getCursNum();
		if (!(cursNum == null || "".equals(cursNum))) {
			Iterator<Course> iterator1 = retCourses.iterator();
			while (iterator1.hasNext()) {
				course = iterator1.next();
				if (!(cursNum.equals(course.getCursNum()))) {
					set.add(course);
				}
			}
			retCourses.removeAll(set);
			set.clear();
		}
		String cursName = limits.getCursName();
		if (!(cursName == null || "".equals(cursName))) {
			Iterator<Course> iterator2 = retCourses.iterator();
			while (iterator2.hasNext()) {
				course = iterator2.next();
				if (!(cursName.equals(course.getCursName()))) {
					set.add(course);
				}
			}
			retCourses.removeAll(set);
			set.clear();
		}

		retCourses.remove(null);
		return retCourses;
	}


	@Override
	public boolean deleteByCursId(Integer cursId) {
		return courseDao.deleteByCursId(cursId);
	}
	
	@Override
	public boolean updateCursNoteById(Course course) {
		return courseDao.updateCursNoteById(course);
	}

}
