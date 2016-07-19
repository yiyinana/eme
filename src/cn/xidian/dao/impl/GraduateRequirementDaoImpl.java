package cn.xidian.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.GraduateRequirementDao;
import cn.xidian.entity.GraduateRequirement;

@Component("graduateRequirementDaoImpl")
public class GraduateRequirementDaoImpl implements GraduateRequirementDao{
	private SessionFactory sessionFactory;
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public List<GraduateRequirement> selectAllGraReq() {
		String hql = "from GraduateRequirement order by graReqId asc";
		Query query = currentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<GraduateRequirement> graduateRequirement = query.list();
		return graduateRequirement;
	}
}
