package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.TeachingTargetEvaluateDao;
import cn.xidian.entity.TeachingTargetEvaluate;

@Component("teachingTargetEvaluateDaoImpl")
public class TeachingTargetEvaluateDaoImpl implements TeachingTargetEvaluateDao {

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addTchingTargetEvaValue(TeachingTargetEvaluate targetEva) {
		currentSession().save(targetEva);
		return true;
	}

	@Override
	public boolean updateTchingTargetEvaValue(TeachingTargetEvaluate targetEva) {
		String sql = "update TeachingTargetEvaluate tt set tt.tchtargetMidEvaValue=?, tt.tchtargetFinEvaValue=?, tt.tchtargetWorkEvaValue=?,"
				+ "tt.tchtargetExpEvaValue=?, tt.tchtargetClassEvaValue=?,tt.a1=?, tt.b1=? "
				+ "where tt.clazz.claId=? and tt.teachingTarget.tchTargetId=?";
		Query query = currentSession().createQuery(sql);
		query.setDouble(0, targetEva.getTchtargetMidEvaValue())
				.setDouble(1, targetEva.getTchtargetFinEvaValue())
				.setDouble(2, targetEva.getTchtargetWorkEvaValue())
				.setDouble(3, targetEva.getTchtargetExpEvaValue())
				.setDouble(4, targetEva.getTchtargetClassEvaValue())
				.setDouble(5, targetEva.getA1())
				.setDouble(6, targetEva.getB1())
				.setInteger(7, targetEva.getClazz().getClaId())
				.setInteger(8, targetEva.getTeachingTarget().getTchTargetId())
				.executeUpdate();
		return true;
	}

	@Override
	public TeachingTargetEvaluate selectByClazzIdAndTargetId(Integer clazzId,
			Integer targetId) {
		String hql = "from TeachingTargetEvaluate tt where tt.clazz.claId=? and tt.teachingTarget.tchTargetId=? order by tchTarEvaId asc";
		Query query = currentSession().createQuery(hql);
		query.setInteger(0, clazzId).setInteger(1, targetId);
		TeachingTargetEvaluate tte = (TeachingTargetEvaluate) query
				.uniqueResult();
		return tte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeachingTargetEvaluate> selectByCursNameAndGrade(
			String cursName,String grade) {
		List<TeachingTargetEvaluate> ttelist = new LinkedList<TeachingTargetEvaluate>();
		// 测试id为空时不报错，和直接传数组不太一样
		String sql = "from TeachingTargetEvaluate tt where tt.teachingTarget.tchTargetId in "
				+ "(select t.tchTargetId from TeachingTarget t where t.course.cursName=? and t.course.isDelete=1) and tt.clazz.claGrade=?";
		Query query = currentSession().createQuery(sql);
		query.setString(0, cursName).setString(1, grade);
		ttelist.addAll(query.list());
		return ttelist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeachingTargetEvaluate> selectByCursAndClazz(String cursName,
			 String claName) {
		List<TeachingTargetEvaluate> ttelist = new LinkedList<TeachingTargetEvaluate>();
		String sql = "from TeachingTargetEvaluate tt where tt.teachingTarget.tchTargetId in "
				+ "(select t.tchTargetId from TeachingTarget t where t.course.cursName=? "
				+ "and t.course.isDelete=1) and tt.clazz.claName=?";
		Query query = currentSession().createQuery(sql);
		query.setString(0, cursName)
				.setString(1, claName);
		ttelist.addAll(query.list());
		return ttelist;
	}

}
