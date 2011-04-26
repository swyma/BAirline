package DAO;

//编辑者：李招意
import java.util.List;

import PO.Flightcompany;
import PO.Manager;



public interface IManagerDAO {
	
	public boolean checkName(String hql);
	public String checkPwd(String hql);
	//public boolean checkCode(String hql);
	
	// 查询所有人员信息
	public List QueryAll(int start, int limit);

	// 获取记录总数
	public int QueryCount();

	//插入记录
	public boolean add(Manager manager);

	//更新记录
	public boolean update(String hql);

	//删除记录
	public boolean delete(String hql);

	//查询记录
	public List QueryManager(String hql);

	//查询相应总数
	public int QueryCount(String hql);
	
	
}


