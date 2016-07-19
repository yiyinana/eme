package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import cn.xidian.dao.CourseReferenceBooksDao;
import cn.xidian.entity.CourseReferenceBooks;

@Component("referenceBooksDaoImpl")
public class CourseReferenceBooksDaoImpl implements CourseReferenceBooksDao {

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addReferenceBooks(CourseReferenceBooks courseReferenceBooks) {
		currentSession().save(courseReferenceBooks);
		return true;
	}

	@Override
	public boolean updateReferenceBooks(
			CourseReferenceBooks courseReferenceBooks) {
		currentSession().update(courseReferenceBooks);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseReferenceBooks> selectByCourse(Integer cursId) {
		List<CourseReferenceBooks> crbs = new LinkedList<CourseReferenceBooks>();
		String sql = "from CourseReferenceBooks where cursId = ?";
		Query query = currentSession().createQuery(sql).setInteger(0, cursId);
		crbs.addAll(query.list());
		return crbs;
	}
	
	@Override
	public boolean deleteByCursId(Integer cursId) {
		String sql = "delete from CourseReferenceBooks c where c.course.cursId = ?";
		Query query = currentSession().createQuery(sql).setInteger(0, cursId);
		query.executeUpdate();
		return true;
	}


}
