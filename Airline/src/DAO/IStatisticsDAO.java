package DAO;

import java.util.List;

public interface IStatisticsDAO {
	//月销售额
	public List QueryMonth();
	//年的销售额
	public List QueryYear();
	
}
