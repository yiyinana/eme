package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.MajorTargetValueDao;
import cn.xidian.entity.MajorTargetValue;

@Component("majorTargetValueDaoImpl")
public class MajorTargetValueDaoImpl implements MajorTargetValueDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public MajorTargetValue selectByPointAndTerm(String currentTerm,
			Integer pointId) {
		String sql = "from MajorTargetValue m where m.point.indPointId=? and m.grade=?";
		Query query = currentSession().createQuery(sql).setInteger(0,
				pointId).setString(1, currentTerm);
		MajorTargetValue mtv = (MajorTargetValue)query.uniqueResult();
		return mtv;
	}

	@Override
	public boolean add(MajorTargetValue mtv) {
		currentSession().save(mtv);
		return true;
	}

	@Override
	public boolean delete(MajorTargetValue mtv) {
		currentSession().delete(mtv);
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MajorTargetValue> selectByGrade(String grade) {
		List<MajorTargetValue> mtv = new LinkedList<MajorTargetValue>();
		String sql = "from MajorTargetValue m where m.grade=?";
		Query query = currentSession().createQuery(sql).setString(0, grade);
		mtv.addAll(query.list());
		return mtv;
	}

}
