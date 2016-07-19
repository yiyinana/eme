package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentItemDao;
import cn.xidian.entity.ItemEvaluatePoint;
import cn.xidian.entity.ItemEvaluateScore;
import cn.xidian.entity.ItemEvaluateType;
import cn.xidian.entity.ItemFile;
import cn.xidian.entity.StudentItem;

@Component("studentItemDaoImpl")
public class StudentItemDaoImpl implements StudentItemDao {

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean deleteItemById(Integer id) {

		// 删除项目信息
		String hql = "delete from StudentItem s where s.stuItemId=?";
		Query query = currentSession().createQuery(hql).setInteger(0, id);
		query.executeUpdate();
		return true;
	}

	public boolean deleteFileById(Integer id) {
		// 删除数据库中存储的文件信息
		String sql = "delete from ItemFile where stuItemId=?";
		Query query1 = currentSession().createQuery(sql);
		query1.setInteger(0, id);
		query1.executeUpdate();
		return true;
	}

	@Override
	public boolean add(StudentItem item) {
		currentSession().save(item);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentItem> selectByStuNum(String stuNum) {
		List<StudentItem> items = new LinkedList<StudentItem>();
		String sql = "from StudentItem s where stuId=(select stuId from Student as stuId where stuSchNum=? and isDelete=1)";
		Query query = currentSession().createQuery(sql).setString(0, stuNum);
		items.addAll(query.list());
		return items;
	}

	@Override
	public boolean judgeStuItem(StudentItem item) {
		// TODO Auto-generated method stub
		String sql = "update StudentItem s set s.itemScore=?,s.itemState=?,s.note=? where s.stuItemId=?";
		Query query = currentSession().createQuery(sql);
		query.setString(0, item.getItemScore());
		query.setString(1, item.getItemState());
		query.setString(2, item.getNote());
		query.setInteger(3, item.getStuItemId());
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean saveAttachment(ItemFile itemFile) {
		// TODO Auto-generated method stub
		currentSession().save(itemFile);
		return true;
	}

	@Override
	public StudentItem selectItemInfo(Integer itemId) {
		// TODO Auto-generated method stub
		String sql = "from StudentItem where stuItemId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, itemId);
		StudentItem studentItem = (StudentItem) query.uniqueResult();
		return studentItem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemFile> selectItemFile(Integer itemId) {
		// TODO Auto-generated method stub
		String sql = "from ItemFile where StuItemId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, itemId);
		List<ItemFile> fileList = new LinkedList<ItemFile>();
		fileList.addAll(query.list());
		return fileList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemEvaluateType> selectItemEvaTypes() {
		// TODO Auto-generated method stub
		String sql = "from ItemEvaluateType";
		Query query = currentSession().createQuery(sql);
		List<ItemEvaluateType> itemTypeList = new LinkedList<ItemEvaluateType>();
		itemTypeList.addAll(query.list());
		return itemTypeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemEvaluatePoint> selectItemEvaPoints(Integer id) {
		// TODO Auto-generated method stub
		String sql = "from ItemEvaluatePoint where itemEvaTypeId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, id);
		List<ItemEvaluatePoint> itemPointList = new LinkedList<ItemEvaluatePoint>();
		itemPointList.addAll(query.list());
		return itemPointList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemEvaluateScore> selectItemEvaScoresByPointId(Integer id) {
		// TODO Auto-generated method stub
		List<ItemEvaluateScore> scoreList = new LinkedList<ItemEvaluateScore>();
		String sql = "from ItemEvaluateScore where itemEvaPointId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, id);
		scoreList.addAll(query.list());
		return scoreList;
	}

	@Override
	public ItemEvaluateType selectItemEvaType(Integer id) {
		// TODO Auto-generated method stub
		String sql = "from ItemEvaluateType where itemEvaTypeId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, id);
		ItemEvaluateType itemEvaluateType = (ItemEvaluateType) query.uniqueResult();
		return itemEvaluateType;
	}

	@Override
	public ItemEvaluatePoint selectItemEvaPoint(Integer id) {
		// TODO Auto-generated method stub
		String sql="from ItemEvaluatePoint where itemEvaPointId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, id);
		ItemEvaluatePoint itemEvaluatePoint=(ItemEvaluatePoint) query.uniqueResult();
		return itemEvaluatePoint;
	}

	@Override
	public ItemEvaluateScore selectItemEvaScore(Integer id) {
		// TODO Auto-generated method stub
		String sql="from ItemEvaluateScore where itemEvaScoreId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, id);
		ItemEvaluateScore itemEvaluateScore=(ItemEvaluateScore) query.uniqueResult();
		return itemEvaluateScore;
	}

}
