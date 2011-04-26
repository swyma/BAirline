package DAO;

//编辑者：卢伟灵
import java.util.List;

import PO.Airtype;

//飞机机型信息接口
public interface IAirtypeDAO {
	// 查询所有飞机机型信息
	public List QueryAll(int start, int limit);

	//查询相应的飞机机型信息
	public List QueryInf(String sql);
	
	// 获取记录总数
	public int QueryCount();
	
	//获取相应的记录总数（分页）
	public int QueryCounts(String sql);
	
	//插入记录
	public boolean add(Airtype airtype);
	
	//更新记录
	public boolean update(String hql);
	
	//删除记录
	public boolean delete(String hql);
	
}
