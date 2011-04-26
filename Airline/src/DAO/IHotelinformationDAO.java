package DAO;

//编辑者：李招意
import java.util.List;

import PO.Hotelinformation;

public interface IHotelinformationDAO {
	
	public boolean checkName(String hql);
	public String checkCity(String hql);
	
	// 查询所有酒店信息
	public List QueryAll(int start, int limit);

	// 获取记录总数
	public int QueryCount();

	//插入记录
	public boolean add(Hotelinformation hotelinformation);

	//更新记录
	public boolean update(String hql);

	//删除记录
	public boolean delete(String hql);

	//查询记录
	public List QueryHotelinformation(String hql);

	//查询相应总数
	public int QueryCount(String hql);
	
	
}


