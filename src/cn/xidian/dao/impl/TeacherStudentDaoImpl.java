package cn.xidian.dao.impl;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.xidian.dao.TeacherStudentDao;
import cn.xidian.entity.Clazz;
import cn.xidian.entity.EvaluateResult;
import cn.xidian.entity.MaxEva;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;
import cn.xidian.web.bean.AdminStuLimits;

@Component
public class TeacherStudentDaoImpl implements TeacherStudentDao {

	private SessionFactory sessionFactory;

	@Override
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Clazz> selectChargeCla(Integer id) {
		// TODO Auto-generated method stub
		String sql = "from Clazz where tchrId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, id);
		List<Clazz> clazzs = query.list();
		return clazzs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> selectChargeStus(Integer id) {
		// TODO Auto-generated method stub
		String sql = "from Student where claId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, id);
		List<Student> students = query.list();
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> selectStuLimits(AdminStuLimits limits, List<Clazz> clazzs) {
		// TODO Auto-generated method stub
		if (limits == null) {
			return null;
		}
		Set<Student> students = new LinkedHashSet<Student>();
		Integer i = 0;
		StringBuffer sb = new StringBuffer();
		for (Clazz element : clazzs) {
			if (i < clazzs.size()) {
				if (i != clazzs.size() - 1) {
					sb.append(element.getClaId() + ",");
					i++;
				}else{
					sb.append(element.getClaId());
				}
			}
		}
		String clazzRange = sb.toString();
		String sql = "from Student where 1=1";
		if (limits.getStuClazz() != null) {
			sql += " and claId=" + limits.getStuClazz();
			if (!limits.getStuSchNum().equals("")) {
				sql += " and stuSchNum=" + limits.getStuSchNum();
				if (!limits.getStuName().equals("")) {
					sql += " and stuName='" + limits.getStuName() + "'";
				}
			} else {
				if (!limits.getStuName().equals("")) {
					sql += " and stuName='" + limits.getStuName() + "'";
				}
			}
		} else {
			sql += " and claId in (" + clazzRange + ")";
			if (!limits.getStuSchNum().equals("")) {
				sql += " and stuSchNum=" + limits.getStuSchNum();
				if (!limits.getStuName().equals("")) {
					sql += " and stuName='" + limits.getStuName() + "'";
				}
			} else {
				if (!limits.getStuName().equals("")) {
					sql += " and stuName='" + limits.getStuName() + "'";
				}
			}
		}
		Query query = currentSession().createQuery(sql);
		students.addAll(query.list());
		return students;
	}

	@Override
	public boolean addEvaScore(EvaluateResult evaluateResult) {
		// TODO Auto-generated method stub
		currentSession().save(evaluateResult);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EvaluateResult> selectSummaryEva(Integer claId, String schoolYear) {
		// TODO Auto-generated method stub
		String sql="from EvaluateResult where claId=? and schoolYear=? ";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, claId);
		query.setString(1, schoolYear);
		List<EvaluateResult> evaluateResults=query.list();
		return evaluateResults;
	}

	@Override
	public Clazz selectClazzById(Integer id) {
		// TODO Auto-generated method stub
		String sql="from Clazz where claId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, id);
		Clazz clazz=(Clazz) query.uniqueResult();
		return clazz;
	}

	@Override
	public boolean deleteEvas(Integer claId, String schoolYear) {
		// TODO Auto-generated method stub
		String sql="delete from EvaluateResult  where claId=? and schoolYear=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, claId);
		query.setString(1, schoolYear);
		query.executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> selectStuGrades(Integer stuId, String schoolYear) {
		// TODO Auto-generated method stub
		String sql="from StudentCourse where stuId=? and schoolYear=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, stuId);
		query.setString(1, schoolYear);
		List<StudentCourse> studentCourses=query.list();
		return studentCourses;
	}

	@Override
	public EvaluateResult selectEvaluateResultById(Integer id) {
		// TODO Auto-generated method stub
		String sql="from EvaluateResult where evaluateResultId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, id);
		EvaluateResult evaluateResult=(EvaluateResult) query.uniqueResult();
		return evaluateResult;
	}

	@Override
	public EvaluateResult selectMaxEva(String schoolYear,Integer i) {
		// TODO Auto-generated method stub
		String sql1="from EvaluateResult where schoolYear=? order by M"+i+" desc";
		Query query=currentSession().createQuery(sql1);
		query.setString(0, schoolYear);
		List<EvaluateResult> evaluateResults=query.list();
		System.out.println(evaluateResults.get(0).getM1());
		return evaluateResults.get(0);
	}

}
