package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.AdminDao;
import cn.xidian.entity.Admin;

@Component
public class AdminDaoImpl implements AdminDao {

	private SessionFactory sessionFactory;
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean findBySchNumAndPwd(String schNum, String pwd) {
		String sql = "from Admin a where a.adminSchNum=? and a.adminPwd=?";
		Query query = currentSession().createQuery(sql);
		query.setString(0, schNum).setString(1, pwd);
		Admin admin = (Admin) query.uniqueResult();
		if (admin != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Admin findBySchNum(String schNum) {
		String sql = "from Admin a where a.adminSchNum=?";
		Query query = currentSession().createQuery(sql);
		query.setString(0, schNum);
		Admin admin =  (Admin) query.uniqueResult();
		return admin;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findAllAdmins() {
		List<Admin> admins = new LinkedList<Admin>();
		String sql = "from Admin where isDelete=1";
		Query query = currentSession().createQuery(sql);
		admins = query.list();
		return admins;
	}

	@Override
	public boolean deleteAdminBySchNum(String schNum) {
		String hql="delete from Admin a where a.adminSchNum=?";
		Query query = currentSession().createQuery(hql).setString(0, schNum);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		String sql = "update Admin a set a.adminPwd=? where a.adminSchNum=?";
		Query query = currentSession().createQuery(sql);
		query.setString(0, admin.getAdminPwd())
				.setString(1, admin.getAdminSchNum()).executeUpdate();
		return true;
	}

	@Override
	public boolean addAdmin(Admin admin) {
		currentSession().save(admin);
		return true;
	}


}
