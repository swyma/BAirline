package DAO;


import java.util.ArrayList;
import java.util.List;

import PO.Refundrecord;

public interface IRefundRecordDAO {

	//插入记录
	public boolean add(Refundrecord refundrecord);
	
	//更新记录
	public boolean update(String hql);
	
	//删除记录
	public boolean delete(String hql);
	
	//是否有退票
	public boolean refundOrnot(String hql);
	
	//取出退票表中的相关信息
	public List get_refundInfo(String hql);
}
