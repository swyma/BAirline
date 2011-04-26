package DAO;

import java.util.List;

import PO.Faretype;

public interface IFaretypeDAO {
	public List queryall();
	
	public boolean update(String hql);
	public boolean add(Faretype fare);
	public boolean del(String hql);
	public List QueryFaretype(String hql);
	public int QueryCount();
	//查询相应总数
	public int QueryCount(String hql);
	//取得航空公司
	public List GetcomCode();
}
