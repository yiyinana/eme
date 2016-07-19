package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.DepartmentDao;
import cn.xidian.entity.Department;

@Component("departmentDaoImpl")
public class DepartmentDaoImpl implements DepartmentDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> selectAllDepts() {
		List<Department> dpts = new LinkedList<Department>();
		String sql = "from Department";
		Query query = currentSession().createQuery(sql);
		dpts.addAll(query.list());
		return dpts;
	}

}
