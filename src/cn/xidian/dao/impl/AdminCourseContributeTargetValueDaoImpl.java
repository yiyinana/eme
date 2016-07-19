package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.AdminCourseContributeTargetValueDao;
import cn.xidian.entity.ContributeTarget;

@Component("adminCourseContributeTargetValueDaoImpl")
public class AdminCourseContributeTargetValueDaoImpl implements AdminCourseContributeTargetValueDao{

	private SessionFactory sessionFactory;
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean saveContributeTargetValue(ContributeTarget ct) {
		currentSession().save(ct);
		currentSession().clear();
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ContributeTarget> selectByTargetId(Integer targetId) {
		List<ContributeTarget> cts = new LinkedList<ContributeTarget>();
		String sql = "from ContributeTarget ct where ct.teachingTarget.tchTargetId=?";
		Query query = currentSession().createQuery(sql).setInteger(0, targetId);
		cts.addAll(query.list());
		return cts;
	}
	@Override
	public boolean deleteByTargetId(Integer tarId) {
		String hql = "delete from ContributeTarget where tchTargetId=?";
		Query query= currentSession().createQuery(hql);
		query.setInteger(0, tarId);
		query.executeUpdate();
		currentSession().clear();
		return true;
	}

}
