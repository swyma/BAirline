package DAO;

//编辑者：周永丰
import java.util.List;

import PO.Flightcompany;

//航空公司信息接口
public interface IFlightcompanyDAO {
	// 查询所有航空公司信息
	public List QueryAll(int start, int limit);

	// 获取记录总数
	public int QueryCount();
	
	//插入记录
	public boolean add(Flightcompany flightcompany);
	
	//更新记录
	public boolean update(String hql);
	
	//删除记录
	public boolean delete(String hql);
}
