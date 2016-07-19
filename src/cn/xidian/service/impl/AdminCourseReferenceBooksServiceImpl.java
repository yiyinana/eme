package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseDao;
import cn.xidian.dao.CourseReferenceBooksDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.CourseReferenceBooks;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.service.AdminCourseReferenceBooksService;

@Component
public class AdminCourseReferenceBooksServiceImpl implements
		AdminCourseReferenceBooksService {

	private CourseReferenceBooksDao referenceBooksDao;

	@Resource(name = "referenceBooksDaoImpl")
	public void setReferenceBooksDao(CourseReferenceBooksDao referenceBooksDao) {
		this.referenceBooksDao = referenceBooksDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public boolean addRefBookByCursId(List<CourseReferenceBooks> crbs,
			Integer cursId) {
		Course courseTemp = courseDao.findById(cursId);
		if (courseTemp == null) {
			throw new CourseNotExistException("要更新的课程不存在");
		}
		for (int i = 0; i < crbs.size(); i++) {
			CourseReferenceBooks crb = (CourseReferenceBooks) crbs.get(i);
			crb.setCourse(courseTemp);
			referenceBooksDao.addReferenceBooks(crb);
		}
		return true;
	}

	@Override
	public List<CourseReferenceBooks> selectReferenceBooks(Integer cursId) {
		return referenceBooksDao.selectByCourse(cursId);
	}

	@Override
	public boolean updateByCursId(List<CourseReferenceBooks> crbs,
			Integer cursId) {
		// 先delete，后add
		referenceBooksDao.deleteByCursId(cursId);
		Course courseTemp = courseDao.findById(cursId);
		for (int i = 0; i < crbs.size(); i++) {
			CourseReferenceBooks crb = crbs.get(i);
			crb.setCourse(courseTemp);
			referenceBooksDao.addReferenceBooks(crb);
		}

		return true;
	}
}
