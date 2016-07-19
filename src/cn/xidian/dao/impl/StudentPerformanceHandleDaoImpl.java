package cn.xidian.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentPerformanceHandleDao;
import cn.xidian.entity.StudentCourse;

@Component("studentPerformanceHandleDaoImpl")
public class StudentPerformanceHandleDaoImpl implements
		StudentPerformanceHandleDao {

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addStuCurs(StudentCourse studentCourse) {
		currentSession().save(studentCourse);
		return true;
	}

	@Override
	public boolean updateStuCurs(StudentCourse studentCourse) {
		String sql = "update StudentCourse sc "
				+ "set sc.midEvaValue=?, sc.finEvaValue=?, sc.workEvaValue=?, "
				+ "sc.expEvaValue=?, sc.classEvaValue=?, sc.evaValue=?"
				+ "where sc.student.stuId=? and sc.course.cursId =?";
		Query query = currentSession().createQuery(sql);
		query.setDouble(0, studentCourse.getMidEvaValue());
		query.setDouble(1, studentCourse.getFinEvaValue());
		query.setDouble(2, studentCourse.getWorkEvaValue());
		query.setDouble(3, studentCourse.getExpEvaValue());
		query.setDouble(4, studentCourse.getClassEvaValue());
		query.setDouble(5, studentCourse.getEvaValue());
		query.setInteger(6, studentCourse.getStudent().getStuId());
		query.setInteger(7, studentCourse.getCourse().getCursId());
		query.executeUpdate();
		return true;
	}
}
