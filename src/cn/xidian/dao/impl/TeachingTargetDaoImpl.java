package cn.xidian.dao.impl;


import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.TeachingTargetDao;
import cn.xidian.entity.TeachingTarget;
@Component("teachingTargetDaoImpl")
public class TeachingTargetDaoImpl implements TeachingTargetDao{
	
	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean addTchingTarget(TeachingTarget targrt) {
		currentSession().save(targrt);
		return true;
	}

	@Override
	public boolean updateTchingTarget(TeachingTarget target) {
		String sql = "update TeachingTarget tt set tt.tchtargetMidTargetValue=?, tt.tchtargetFinTargetValue=?, tt.tchtargetHomeworkTargetValue=?,"
				+ "tt.tchtargetExpTargetValue=?, tt.tchtargetClassTargetValue=? "
				+ "where tt.tchTargetId=?";
		Query query = currentSession().createQuery(sql);
		query.setDouble(0, target.getTchtargetMidTargetValue())
		.setDouble(1, target.getTchtargetFinTargetValue())
		.setDouble(2, target.getTchtargetHomeworkTargetValue())
		.setDouble(3, target.getTchtargetExpTargetValue())
		.setDouble(4, target.getTchtargetClassTargetValue())
		.setInteger(5, target.getTchTargetId()).executeUpdate();
		return true;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TeachingTarget> selectByCourse(String cursNum) {
//		List<TeachingTarget> tts = new LinkedList<TeachingTarget>();
//		String sql = "from TeachingTarget tt where cursId = (select cursId from Course as cursId where cursNum = ? and isDelete=1) order by tchTargetId asc";
//		Query query = currentSession().createQuery(sql).setString(0, cursNum);
//		tts.addAll(query.list());
//		return tts;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeachingTarget> selectByCursId(Integer cursId) {
		List<TeachingTarget> tts = new LinkedList<TeachingTarget>();
		String sql = "from TeachingTarget tt where cursId = ? order by tchTargetId asc";
		Query query = currentSession().createQuery(sql).setInteger(0, cursId);
		tts.addAll(query.list());
		return tts;
	}

	@Override
	public boolean deleteTchingTarget(TeachingTarget targrt) {
		currentSession().delete(targrt);
		return true;
	}
	
}
