package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.TeacherCourseDao;
import cn.xidian.entity.TeacherCourse;

@Component("teacherCourseDaoImpl")
public class TeacherCourseDaoImpl implements TeacherCourseDao {

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherCourse> findByNumAndTerm(String tchrNum) {
		List<TeacherCourse> tchrCurs = new LinkedList<TeacherCourse>();
		String sql = "from TeacherCourse tc where tc.teacher.tchrSchNum=? and tc.teacher.isDelete=1 and tc.course.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, tchrNum);
		tchrCurs.addAll(query.list());
		return tchrCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherCourse> findAlltchrCurs() {
		List<TeacherCourse> tchrCurs = new LinkedList<TeacherCourse>();
		String sql = "from TeacherCourse tc where tc.course.isDelete=1 and tc.teacher.isDelete=1 ";
		Query query = currentSession().createQuery(sql);
		tchrCurs.addAll(query.list());
		return tchrCurs;
	}

}
