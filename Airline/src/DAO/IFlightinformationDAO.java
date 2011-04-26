/*==============*/
/* 编辑者：叶茂安 */
/*============*/

package DAO;

import java.util.List;

import PO.Flightinformation;

public interface IFlightinformationDAO {
	
	// 查询所有航班公司信息
	public List QueryAll(int start, int limit);
	
	//查询相应的航班信息
	public List QueryInf(String sql);
	
	// 获取记录总数
	public int QueryCount();
	
	//获取相应舱位的人数
	public int GetNumber(String sql);
	
	//获取相应的记录总数（分页）
	public int QueryCounts(String sql);
	
	//是否可插入与更新
	public boolean addornot(String hql);
	
	//插入记录
	public boolean add(Flightinformation flightinfomation);
	
	//更新记录
	public boolean update(String hql);
	
	//删除记录
	public boolean delete(String hql);
	
	//取得航空代码
	public List GetcomCode();
	
	//取得飞机机型
	public List GetairType();
	
	/* ----杨海亮  修改   2010 4 10   */
	//取得航班信息在客户订票信息中显示
	public  List fliInformationShow(String sql);
	
}

