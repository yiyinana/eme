package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseTeachingModeDao;
import cn.xidian.entity.CourseTeachingMode;

@Component("courseTeachingModeDaoImpl")
public class CourseTeachingModeDaoImpl implements CourseTeachingModeDao{
	
	private SessionFactory sessionFactory;
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean addCursTchingMode(CourseTeachingMode ctm) {
		currentSession().save(ctm);
		return true;
	}
	@Override
	public boolean updateCursTchingMode(CourseTeachingMode ctm) {
		currentSession().update(ctm);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseTeachingMode> selectByCourse(String cursNum) {
		List<CourseTeachingMode> crbs = new LinkedList<CourseTeachingMode>();
		String sql = "from CourseTeachingMode where cursId = (select cursId from Course as cursId where cursNum=? and isDelete=1)";
		Query query = currentSession().createQuery(sql).setString(0, cursNum);
		crbs.addAll(query.list());
		return crbs;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseTeachingMode> selectTchingModeByCursId(Integer id) {
		List<CourseTeachingMode> crbs = new LinkedList<CourseTeachingMode>();
		String sql = "from CourseTeachingMode where cursId = ?";
		Query query = currentSession().createQuery(sql).setInteger(0, id);
		crbs.addAll(query.list());
		return crbs;
	}

	
	@Override
	public boolean deleteByCursId(Integer cursId) {
		String sql = "delete from CourseTeachingMode c where c.course.cursId = ?";
		Query query = currentSession().createQuery(sql).setInteger(0, cursId);
		query.executeUpdate();
		return true;
	}

}
