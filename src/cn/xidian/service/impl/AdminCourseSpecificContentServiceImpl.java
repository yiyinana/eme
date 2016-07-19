package cn.xidian.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseDao;
import cn.xidian.dao.CourseSpecificContentDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.CourseSpecificContent;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.service.AdminCourseSpecificContentService;

@Component
public class AdminCourseSpecificContentServiceImpl implements
		AdminCourseSpecificContentService {

	private CourseSpecificContentDao courseSpecificContentDao;

	@Resource(name = "courseSpecificContentDaoImpl")
	public void setCourseSpecificContentDao(
			CourseSpecificContentDao courseSpecificContentDao) {
		this.courseSpecificContentDao = courseSpecificContentDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public boolean addSpecificContentByCursId(List<CourseSpecificContent> sc,
			Integer cursId) {
		Course courseTemp = courseDao.findById(cursId);
		if (courseTemp == null) {
			throw new CourseNotExistException("要更新的课程不存在");
		}
		for (int i = 0; i < sc.size(); i++) {
			CourseSpecificContent csc = (CourseSpecificContent) sc.get(i);
			csc.setCourse(courseTemp);
			courseSpecificContentDao.addSpecificContent(csc);
		}
		return true;

	}

	@Override
	public List<CourseSpecificContent> selectCursSpecificContentByCursId(
			Integer id) {
		return courseSpecificContentDao.selectCursSpecificContentByCursId(id);
	}
	
	@Override
	public boolean updateByCursId(List<CourseSpecificContent> sc, Integer cursId) {
		// 先delete，后add
		boolean isSuccess = false;
		isSuccess = courseSpecificContentDao.deleteByCursId(cursId);
		if (isSuccess) {
			Course courseTemp = courseDao.findById(cursId);
			Iterator<CourseSpecificContent> itSc = sc.iterator();
			CourseSpecificContent csc = null;
			while (itSc.hasNext()) {
				csc = itSc.next();
				csc.setCourse(courseTemp);
				courseSpecificContentDao.addSpecificContent(csc);
			}
		}
		return true;
	}

}
