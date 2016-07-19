package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.ClazzDao;
import cn.xidian.entity.Clazz;

@Component("clazzDaoImpl")
public class ClazzDaoImpl implements ClazzDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Clazz selectByName(String clazzName) {
		String sql = "from Clazz c where c.claName=?";
		Query query = currentSession().createQuery(sql);
		query.setString(0, clazzName);
		Clazz clazz = (Clazz) query.uniqueResult();
		return clazz;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Clazz> findAllCla() {
		List<Clazz> clazz = new LinkedList<Clazz>();
		String sql = "from Clazz";
		Query query = currentSession().createQuery(sql);
		clazz.addAll(query.list());
		return clazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Clazz> selectByGrade(String grade) {
		List<Clazz> clazzs = new LinkedList<Clazz>();
		String sql = "from Clazz c where c.claGrade=?";
		Query query = currentSession().createQuery(sql).setString(0, grade);
		clazzs.addAll(query.list());
		return clazzs;
	}

}
