package cn.xidian.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.IndicatorPointDao;
import cn.xidian.entity.IndicatorPoint;

@Component("indicatorPointDaoImpl")
public class IndicatorPointDaoImpl implements IndicatorPointDao{
	
	private SessionFactory sessionFactory;
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<IndicatorPoint> selectIndPointByParentId(Integer id) {
		String sql = "from IndicatorPoint i where i.graReq=? order by indPointId asc";
		Query query = currentSession().createQuery(sql).setInteger(0, id);
		@SuppressWarnings("unchecked")
		List<IndicatorPoint> indicatorPoints = query.list();
		return indicatorPoints;
	}
	@Override
	public IndicatorPoint selectIndPointById(Integer id) {
		String sql = "from IndicatorPoint i where i.indPointId=? order by indPointId asc";
		Query query = currentSession().createQuery(sql).setInteger(0, id);
		IndicatorPoint indicatorPoint = (IndicatorPoint) query.uniqueResult();
		return indicatorPoint;
	}

}
