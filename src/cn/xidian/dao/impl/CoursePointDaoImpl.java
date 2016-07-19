package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.CoursePointDao;
import cn.xidian.entity.CoursePoint;

@Component("coursePointDaoImpl")
public class CoursePointDaoImpl implements CoursePointDao{
	
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
	public List<CoursePoint> selectByCursId(Integer cursId) {
		List<CoursePoint> points = new LinkedList<CoursePoint>();
		String sql = "from CoursePoint c where c.course.cursId=?";
		Query query = currentSession().createQuery(sql).setInteger(0,
				cursId);
		points.addAll(query.list());
		return points;
	}

	@Override
	public boolean delete(CoursePoint point) {
		currentSession().delete(point);
		return true;
	}

	@Override
	public boolean add(CoursePoint point) {
		currentSession().save(point);
		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CoursePoint> selectByPointId(Integer pointId) {
		List<CoursePoint> points = new LinkedList<CoursePoint>();
		String sql = "from CoursePoint c where c.indPoint.indPointId=?";
		Query query = currentSession().createQuery(sql).setInteger(0,
				pointId);
		points.addAll(query.list());
		return points;
	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CoursePoint> selectByGradeAndPointId(Integer pointId,
//			String grade) {
//		List<CoursePoint> points = new LinkedList<CoursePoint>();
//		String sql = "from CoursePoint c where c.indPoint.indPointId=? and ";
//		Query query = currentSession().createQuery(sql).setInteger(0,
//				pointId);
//		points.addAll(query.list());
//		return points;
//	}

}
