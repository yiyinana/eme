package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.ItemEvaluatePoint;
import cn.xidian.entity.ItemEvaluateScore;
import cn.xidian.entity.ItemEvaluateType;
import cn.xidian.entity.ItemFile;
import cn.xidian.entity.StudentItem;

public interface StudentItemService {

	boolean deleteItemById(Integer id);

	boolean deleteFileById(Integer id);

	boolean add(StudentItem item);

	List<StudentItem> selectByStuNum(String stuNum);

	boolean saveAttachment(ItemFile itemFile);

	StudentItem selectItemInfo(Integer itemId);

	List<ItemFile> selectItemFile(Integer itemId);

	List<ItemEvaluateType> selectItemEvaTypes();

	List<ItemEvaluatePoint> selectItemEvaPoints(Integer id);

	List<ItemEvaluateScore> selectItemEvaScoresByPointId(Integer id);

	ItemEvaluateType selectItemEvaType(Integer id);
	
	ItemEvaluatePoint selectItemEvaPoint(Integer id);
	
	ItemEvaluateScore selectItemEvaScore(Integer id);
}
