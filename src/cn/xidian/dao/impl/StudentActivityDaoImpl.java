package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentActivityDao;
import cn.xidian.entity.StudentActivity;

@Component("studentActivityDaoImpl")
public class StudentActivityDaoImpl implements StudentActivityDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean deleteById(Integer id) {
		String hql = "delete from StudentActivity s where s.stuActId=?";
		Query query = currentSession().createQuery(hql).setInteger(0, id);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean add(StudentActivity activity) {
		currentSession().save(activity);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentActivity> selectByStuNum(String stuNum) {
		List<StudentActivity> activities = new LinkedList<StudentActivity>();
		String sql = "from StudentActivity s where stuId=(select stuId from Student as stuId where stuSchNum=?)";
		Query query = currentSession().createQuery(sql).setString(0, stuNum);
		activities.addAll(query.list());
		return activities;
		
	}

}
