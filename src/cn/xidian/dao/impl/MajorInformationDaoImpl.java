package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.MajorInformationDao;
import cn.xidian.entity.GraduateRequirement;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.entity.MajorInformation;


@Component("majorInformationDaoImpl")
public class MajorInformationDaoImpl implements MajorInformationDao{
	
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
	public List<GraduateRequirement> selectGraReq() {
		List<GraduateRequirement> requirements = new LinkedList<GraduateRequirement>();
		String sql = "from GraduateRequirement";
		Query query = currentSession().createQuery(sql);
		requirements.addAll(query.list());
		return requirements;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IndicatorPoint> selectPointByReqId(Integer reqId) {
		List<IndicatorPoint> points = new LinkedList<IndicatorPoint>();
		String sql = "from IndicatorPoint where indPointId=?";
		Query query = currentSession().createQuery(sql).setInteger(0, reqId);
		points.addAll(query.list());
		return points;
	}

	@Override
	public MajorInformation selectMajorInfo() {
		// TODO Auto-generated method stub
		MajorInformation majorInfo=null;
		String sql="from MajorInformation";
		Query query=currentSession().createQuery(sql);
		majorInfo=(MajorInformation) query.uniqueResult();
		return majorInfo;
	}

	@Override
	public boolean addMajorInfo(MajorInformation majorInformation) {
		// TODO Auto-generated method stub
		currentSession().save(majorInformation);
		return true;
	}

	@Override
	public boolean updateMajorInfo(MajorInformation majorInformation) {
		// TODO Auto-generated method stub
		String sql="update MajorInformation set majorIntr=?,eduTarget=?";
		Query query=currentSession().createQuery(sql);
		query.setString(0, majorInformation.getMajorIntr());
		query.setString(1, majorInformation.getEduTarget());
		query.executeUpdate();
		return true;
	}
	
	
}
