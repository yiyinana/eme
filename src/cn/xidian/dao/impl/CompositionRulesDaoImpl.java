package cn.xidian.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.CompositionRulesDao;
import cn.xidian.entity.CompositionRules;

@Component("compositionRulesDaoImpl")
public class CompositionRulesDaoImpl implements CompositionRulesDao {
	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addCompRules(CompositionRules compositionRules) {
		currentSession().save(compositionRules);
		return true;
	}

	@Override
	public boolean updateCompRules(CompositionRules compositionRules) {
		String sql = "update CompositionRules c "
				+ "set c.midTermPer=?, c.finalExamPer=?, c.homeworkResultPer=?, c.expResultPer=?, c.clazzPer=? "
				+ "where c.course.cursId=?";
		Query query = currentSession().createQuery(sql);
		query.setDouble(0, compositionRules.getMidTermPer());
		query.setDouble(1, compositionRules.getFinalExamPer());
		query.setDouble(2, compositionRules.getHomeworkResultPer());
		query.setDouble(3, compositionRules.getExpResultPer());
		query.setDouble(4, compositionRules.getClazzPer());
		query.setInteger(5, compositionRules.getCourse().getCursId());
		
		query.executeUpdate();
		return true;
	}

	@Override
	public CompositionRules selectByCourse(String cursNum) {
		CompositionRules crs = new CompositionRules();
		String sql = "from CompositionRules where cursId = (select cursId from Course as cursId where cursNum=? and isDelete=1)";
		Query query = currentSession().createQuery(sql).setString(0, cursNum);
		crs = (CompositionRules)query.uniqueResult();
		return crs;
	}

	@Override
	public CompositionRules selectCompRulesByCursId(Integer cursId) {
		CompositionRules crs = new CompositionRules();
		String sql = "from CompositionRules where cursId = ?";
		Query query = currentSession().createQuery(sql).setInteger(0, cursId);
		crs = (CompositionRules)query.uniqueResult();
		return crs;
	}

}
