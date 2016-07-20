package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.GradeCoursePointDao;
import cn.xidian.entity.GradeCoursePoint;

@Component("gradeCoursePointDaoImpl")
public class GradeCoursePointDaoImpl implements GradeCoursePointDao{

	private SessionFactory sessionFactory;
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(GradeCoursePoint ccp) {
		currentSession().save(ccp);
		return true;
	}

	@Override
	public boolean delete(GradeCoursePoint ccp) {
		currentSession().delete(ccp);
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeCoursePoint> selectByCursIdAndPointId(Integer cursId,
			Integer pointId) {
		List<GradeCoursePoint> gcp = new LinkedList<GradeCoursePoint>();
		String sql = "from GradeCoursePoint g where g.course.cursId=? and g.point.indPointId=? and g.isDelete=0";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId).setInteger(1, pointId);
		gcp.addAll(query.list());
		return gcp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeCoursePoint> selectByPointId(Integer pointId) {
		List<GradeCoursePoint> gcp = new LinkedList<GradeCoursePoint>();
		String sql = "from GradeCoursePoint g where g.point.indPointId=? and g.isDelete=0";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, pointId);
		gcp.addAll(query.list());
		return gcp;
	}
	
	@Override
	public boolean updateCursPowerByCursPointIdAndGrade(
			GradeCoursePoint gradeCursPoints, String grade) {
		String sql = "update GradeCoursePoint g "
				+ "set g.cursPower=? "
				+ "where g.point.indPointId=? and g.course.cursId=? and g.grade=? and g.isDelete=0";
		Query query = currentSession().createQuery(sql);
		query.setDouble(0, gradeCursPoints.getCursPower());
		query.setInteger(1, gradeCursPoints.getPoint().getIndPointId());
		query.setInteger(2, gradeCursPoints.getCourse().getCursId());
		query.setString(3, grade);
		
		query.executeUpdate();
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeCoursePoint> selectByCursIdAndGrade(Integer cursId,
			String grade) {
		List<GradeCoursePoint> gcp = new LinkedList<GradeCoursePoint>();
		String sql = "from GradeCoursePoint g where g.course.cursId=? and g.grade=? and g.isDelete=0";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId).setString(1, grade);
		gcp.addAll(query.list());
		return gcp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeCoursePoint> selectByPointIdAndGrade(Integer pointId,
			String grade) {
		List<GradeCoursePoint> gcp = new LinkedList<GradeCoursePoint>();
		String sql = "from GradeCoursePoint g where g.point.indPointId=? and grade=? and isDelete=0";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, pointId).setString(1, grade);
		gcp.addAll(query.list());
		return gcp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeCoursePoint> selectByGrade(String grade) {
		List<GradeCoursePoint> gcp = new LinkedList<GradeCoursePoint>();
		String sql = "from GradeCoursePoint g where g.grade=? and isDelete=0";
		Query query = currentSession().createQuery(sql);
		query.setString(0, grade);
		gcp.addAll(query.list());
		return gcp;
	}
	
	@Override
	public boolean deleteByCursId(Integer cursId) {
		String sql = "update GradeCoursePoint g set g.isDelete=1 where g.course.cursId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId);
		query.executeUpdate();
		return true;
	}

}
