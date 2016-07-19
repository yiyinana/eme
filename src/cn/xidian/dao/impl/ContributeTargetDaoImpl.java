package cn.xidian.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.ContributeTargetDao;
import cn.xidian.entity.ContributeTarget;

@Component("contributeTargetDaoImpl")
public class ContributeTargetDaoImpl implements ContributeTargetDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<ContributeTarget> selectByTarget(Integer ttId) {
		String hql = "from ContributeTarget ct where tchTargetId=? order by conTarId asc";
		Query query = currentSession().createQuery(hql).setInteger(0, ttId);
		@SuppressWarnings("unchecked")
		List<ContributeTarget> contributeTargets = query.list();
		return contributeTargets;
	}

}
