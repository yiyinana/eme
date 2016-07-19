package cn.xidian.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.TeacherExperimentDao;
import cn.xidian.entity.TeacherExperiment;

@Component("teacherExperimentDaoImpl")
public class TeacherExperimentDaoImpl implements TeacherExperimentDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean add(TeacherExperiment experiment) {
		currentSession().save(experiment);
		return true;
	}

	@Override
	public List<TeacherExperiment> selectByTchrNum(String tchrNum) {
		String hql = "from TeacherExperiment where tchrId = (select tchrId from Teacher as tchrId where tchrSchNum=? and isDelete=1) order by tchrExpId asc";
		Query query = currentSession().createQuery(hql).setString(0, tchrNum);
		@SuppressWarnings("unchecked")
		List<TeacherExperiment> teacherExperiments = query.list();
		return teacherExperiments;
	}

	@Override
	public boolean deleteById(Integer expId) {
		String hql="delete from TeacherExperiment where tchrExpId=?";
		Query query = currentSession().createQuery(hql).setInteger(0, expId);
		query.executeUpdate();
		return true;
	}

}
