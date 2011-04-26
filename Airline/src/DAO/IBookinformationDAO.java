package DAO;
//编辑者：杨海亮

import java.util.List;

import PO.Bookinformation;

public interface IBookinformationDAO {
	// 查询所有订票信息
	public List QueryAll(int start, int limit);

	// 获取记录总数
	public int QueryCount();
	
	//插入记录
	public boolean add(Bookinformation flightcompany);
	
	//更新记录
	public boolean update(String hql);
	
	//删除记录
	public boolean delete(String hql);
	
	//获得航空公司名称
	public List search_comName(String hql);
	
	//获得订票客户的身份证号码
	public List search_cusId(String hql); 
	
	// 获得特定条件的客户信息
	public List search_information(String hql);
	

	//获得订各个舱位的总人数
	public int Booking_FCY_Number(String hql);
    
	
	//个人信息修改
	public boolean individual_update(String hql);
	
	//密码修改
	public boolean password_update(String hql);
	
	
	//取得相应航班的折扣
	public double get_fliDiscount(String hql);
	
	//取得FCY各舱位的价格
	public double getflifare(String hql);
	
	
}
