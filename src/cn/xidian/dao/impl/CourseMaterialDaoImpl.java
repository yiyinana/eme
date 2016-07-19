package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseMaterialDao;
import cn.xidian.entity.CourseMaterial;


@Component("courseMaterialDaoImpl")
public class CourseMaterialDaoImpl implements CourseMaterialDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean deleteByCursId(Integer cursId) {
		String sql = "delete CourseMaterial cm where cm.course.cursId=?";
		Query query = currentSession().createQuery(sql).setInteger(0, cursId);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean save(CourseMaterial material) {
		currentSession().save(material);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseMaterial> findByCursId(Integer cursId) {
		List<CourseMaterial> cms = new LinkedList<CourseMaterial>();
		String sql = "from CourseMaterial where cursId = ? order by cmId asc";
		Query query = currentSession().createQuery(sql).setInteger(0, cursId);
		cms.addAll(query.list());
		return cms;	
	}

}
