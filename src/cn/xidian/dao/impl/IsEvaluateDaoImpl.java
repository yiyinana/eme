package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.IsEvaluateDao;
import cn.xidian.entity.IsEvaluate;

@Component("isEvaluateDaoImpl")
public class IsEvaluateDaoImpl implements IsEvaluateDao {
	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public IsEvaluate findByCursAndClazz(Integer cursId, Integer claId) {
		IsEvaluate isevaluate = new IsEvaluate();
		String sql = "from IsEvaluate i where i.course.cursId=? and i.clazz.claId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId).setInteger(1, claId);
		isevaluate = (IsEvaluate) query.uniqueResult();
		return isevaluate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IsEvaluate> findByCursId(Integer cursId) {
		List<IsEvaluate> isEvaluate = new LinkedList<IsEvaluate>();
		String sql = "from IsEvaluate i where i.course.cursId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId);
		isEvaluate.addAll(query.list());
		return isEvaluate;
	}

	@Override
	public boolean updateIsevaluate(IsEvaluate isevaluate) {
		currentSession().update(isevaluate);
		return true;
	}

	@Override
	public boolean addIsevaluate(IsEvaluate isevaluate) {
		currentSession().save(isevaluate);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IsEvaluate> selectByCursIdAndGrade(Integer cursId, String grade) {
		List<IsEvaluate> isEvaluate = new LinkedList<IsEvaluate>();
		String sql = "from IsEvaluate i where i.course.cursId=? and i.clazz.claGrade=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId).setString(1, grade);
		isEvaluate.addAll(query.list());
		return isEvaluate;
	}

}
