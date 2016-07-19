package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseSpecificContentDao;
import cn.xidian.entity.CourseSpecificContent;

@Component("courseSpecificContentDaoImpl")
public class CourseSpecificContentDaoImpl implements CourseSpecificContentDao{
	
	private SessionFactory sessionFactory;
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean addSpecificContent(CourseSpecificContent csc) {
		currentSession().save(csc);
		return true;
	}
	@Override
	public boolean updateSpecificContent(CourseSpecificContent csc) {
		currentSession().update(csc);
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseSpecificContent> selectByCourse(String cursNum) {
		List<CourseSpecificContent> cscs = new LinkedList<CourseSpecificContent>();
		String sql = "from CourseSpecificContent where cursId = (select cursId from Course where cursNum=? and isDelete=1)";
		Query query = currentSession().createQuery(sql).setString(0, cursNum);
		cscs.addAll(query.list());
		return cscs;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseSpecificContent> selectCursSpecificContentByCursId(
			Integer id) {
		List<CourseSpecificContent> cscs = new LinkedList<CourseSpecificContent>();
		String sql = "from CourseSpecificContent where cursId = ?";
		Query query = currentSession().createQuery(sql).setInteger(0, id);
		cscs.addAll(query.list());
		return cscs;
	}
	
	@Override
	public boolean deleteByCursId(Integer cursId) {
		String sql = "delete from CourseSpecificContent c where c.course.cursId = ?";
		Query query = currentSession().createQuery(sql).setInteger(0, cursId);
		query.executeUpdate();
		return true;
	}

}
