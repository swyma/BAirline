/*==============*/
/* 编辑者：叶茂安 */
/*============*/

package Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import DAO.FlightinformationDAOImpl;
import DAO.IFlightinformationDAO;
import PO.Flightinformation;

public class FlightinformationServiceImpl implements IActionService {
	private byte status;
	// start、limit分页传送数据
	private int start;
	private int limit;
	private long fliAutoid;
	//航空公司代码
	private String comCode;
	//座位及人数
	private String airCode;
	private String fliEveryday;
	private String fliNo;
	private double fliDiscount;
	private String fliBaddress;
	private String fliAaddress;
	private String fliBtime;
	private String fliAtime;
	private short fliFnumber;
	private short fliCnumber;
	private short fliYnumber;
	private double fliFfare;
	private double fliCfare;
	private double fliYfare;
	private String fliRefundtime;
	private String fliRefund;

	// 定义DAO接口实现类
	private IFlightinformationDAO flightinformation = new FlightinformationDAOImpl();

	/*
	 * 前后台数据的部分处理
	 */
	public byte handler(String str){
		//byte status;
		//if(!("".equals(str) || str==null)){
			if(str.equals("正常") || str.equals("运行")){
				status=1;
			}else if(str.equals("关闭") || str.equals("取消")){
				status=0;
			} 
		//}
		return status;
	}
	/*
	 * 2011-03-18 叶茂安 补全函数 timeHandler() 1----->01
	 */
	public String timeHanlder(int str) {
		StringBuffer sb = new StringBuffer();
		if (str < 10) {
			String result = (sb.append("0").append(str).toString());
			return result;
		} else {
			return String.valueOf(str);
		}
	}

	/** 得到需要的正确时间的字符串形式 */

	public String getStrtime(String time) {
		String hours = time.substring(0, 2);
		String minutes = time.substring(3, 5);
		Date date = new Date();
		date.setHours(new Integer(hours));
		date.setMinutes(new Integer(minutes));
		// 时间格式为：2011-03-17 12：00：00
		return date.toLocaleString();
	}

	/*
	 * formatTime:格式化时间 2011-09-01 12：30：00
	 */
	public Calendar formatTime(String time) {

		Date date = new Date();
		String hours = time.substring(0, 2);
		String minutes = time.substring(3, 5);

		// 将匹配的字符串加载到date中
		date.setHours(new Integer(hours));
		date.setMinutes(new Integer(minutes));
		date.setSeconds(0);

		// Calendar对象
		Calendar cal = Calendar.getInstance();
		cal.clear(); // 如果不删除还会附带一些没用的时间信息
		cal.setTime(date); // 将date加载到cal中，返回的结果是：2011-03-11 12：00：00
		return cal;
	}

	/*
	 * =======================-----setter and getter
	 * method-----==========================
	 */
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getAirCode() {
		return airCode;
	}

	public void setAirCode(String airCode) {
		this.airCode = airCode;
	}

	public long getFliAutoid() {
		return fliAutoid;
	}

	public void setFliAutoid(long fliAutoid) {
		this.fliAutoid = fliAutoid;
	}

	public String getFliEveryday() {
		return fliEveryday;
	}

	public void setFliEveryday(String fliEveryday) {
		this.fliEveryday = fliEveryday;
	}

	public String getFliNo() {
		return fliNo;
	}

	public void setFliNo(String fliNo) {
		this.fliNo = fliNo;
	}

	public double getFliDiscount() {
		return fliDiscount;
	}

	public void setFliDiscount(double fliDiscount) {
		this.fliDiscount = fliDiscount;
	}

	public String getFliBaddress() {
		return fliBaddress;
	}

	public void setFliBaddress(String fliBaddress) {
		this.fliBaddress = fliBaddress;
	}

	public String getFliAaddress() {
		return fliAaddress;
	}

	public void setFliAaddress(String fliAaddress) {
		this.fliAaddress = fliAaddress;
	}

	public String getFliBtime() {
		return fliBtime;
	}

	public void setFliBtime(String fliBtime) {
		this.fliBtime = fliBtime;
	}

	public String getFliAtime() {
		return fliAtime;
	}

	public void setFliAtime(String fliAtime) {
		this.fliAtime = fliAtime;
	}

	public short getFliFnumber() {
		return fliFnumber;
	}

	public void setFliFnumber(short fliFnumber) {
		this.fliFnumber = fliFnumber;
	}

	public short getFliCnumber() {
		return fliCnumber;
	}

	public void setFliCnumber(short fliCnumber) {
		this.fliCnumber = fliCnumber;
	}

	public short getFliYnumber() {
		return fliYnumber;
	}

	public void setFliYnumber(short fliYnumber) {
		this.fliYnumber = fliYnumber;
	}

	public double getFliFfare() {
		return fliFfare;
	}

	public void setFliFfare(double fliFfare) {
		this.fliFfare = fliFfare;
	}

	public double getFliCfare() {
		return fliCfare;
	}

	public void setFliCfare(double fliCfare) {
		this.fliCfare = fliCfare;
	}

	public double getFliYfare() {
		return fliYfare;
	}

	public void setFliYfare(double fliYfare) {
		this.fliYfare = fliYfare;
	}

	public String getFliRefundtime() {
		return fliRefundtime;
	}

	public void setFliRefundtime(String fliRefundtime) {
		this.fliRefundtime = fliRefundtime;
	}

	public String getFliRefund() {
		return fliRefund;
	}

	public void setFliRefund(String fliRefund) {
		this.fliRefund = fliRefund;
	}

	public IFlightinformationDAO getFlightinformation() {
		return flightinformation;
	}

	public void setFlightinformation(IFlightinformationDAO flightinformation) {
		this.flightinformation = flightinformation;
	}

	/*
	 * ==========================================================================
	 * ======
	 */

	public String execute() throws Exception {
		// 获取参数
		HttpServletRequest request = ServletActionContext.getRequest();
		// 响应数据
		HttpServletResponse response = ServletActionContext.getResponse();

		// 编制响应的格式
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		/*
		 * 查询所有飞机的机型号码
		 */
		if (type.equals("add_airtype")) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			List list = flightinformation.GetairType();
			for (int i = 0; i < list.size(); i++) {
				sb.append("['").append(list.get(i)).append("','")
						.append(list.get(i)).append("']");
				if (i < list.size() - 1) {
					sb.append(",");
				}
			}
			sb.append("]");
			response.getWriter().write(sb.toString());
		}
		/*
		 * 查询所有航空公司的航空代码
		 */
		else if (type.equals("add_comcode")) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			List list = flightinformation.GetcomCode();
			// 拼凑一个json形式的数组
			for (int i = 0; i < list.size(); i++) {
				sb.append("['").append(list.get(i)).append("','")
						.append(list.get(i)).append("']");
				if (i < list.size() - 1) {
					sb.append(",");
				}
			}
			sb.append("]");
			// 数据传送
			response.getWriter().write(sb.toString());
		}

		/*
		 * ===================================查询所有================================
		 * ==
		 */
		else if (type.equals("queryall")) {

			JSONArray json = JSONArray.fromObject(flightinformation.QueryAll(
					getStart(), getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + flightinformation.QueryCount()
							+ ",\"root\":" + json.toString() + "}");
			// 拼凑json数组

		}

		/* ==================================根据输入值查询============================ */
		else if (type.equals("query")) {
			// 查询数据
			String hql = "select new map(" + "p.fliAutoid as fliAutoid,"
					+ "p.comCode as comCode," + "p.airCode as airCode,"
					+ "p.fliEveryday as fliEveryday," + "p.fliNo as fliNo,"
					+ "p.fliDiscount as fliDiscount,"
					+ "p.fliBaddress as fliBaddress,"
					+ "p.fliAaddress as fliAaddress,"
					+ "SUBSTRING(p.fliBtime,12,5) as fliBtime,"
					+ "SUBSTRING(p.fliAtime,12,5) as fliAtime,"
					+ "p.fliFnumber as fliFnumber,"
					+ "p.fliCnumber as fliCnumber,"
					+ "p.fliYnumber as fliYnumber," + "p.fliFfare as fliFfare,"
					+ "p.fliCfare as fliCfare," + "p.fliYfare as fliYfare,"
					+ "p.fliRefundtime as fliRefundtime,"
					+ "p.fliRefund as fliRefund) "
					+ "from Flightinformation p "
					+ "where p.flag=1 and p.comCode like '%" + getComCode()
					+ "%'" + "and p.fliNo like '%" + getFliNo() + "%'"
					+ "and p.fliBaddress like '%" + getFliBaddress() + "%'"
					+ "and p.fliAaddress like '%" + getFliAaddress()
					+ "%'"
					// substring(p.fliBtime,12,5)------>05:00
					+ "and SUBSTRING(p.fliBtime,12,5) like '%" + getFliBtime()
					+ "%'" + "and SUBSTRING(p.fliAtime,12,5) like '%"
					+ getFliAtime() + "%'";
			// 取得相应的总数
			String count_sql = "select count(p.fliAutoid) from Flightinformation p "
					+ "where p.flag=1 and p.comCode like '%"
					+ getComCode()
					+ "%'"
					+ "and p.fliNo like '%"
					+ getFliNo()
					+ "%'"
					+ "and p.fliBaddress like '%"
					+ getFliBaddress()
					+ "%'"
					+ "and p.fliAaddress like '%"
					+ getFliAaddress()
					+ "%'"
					+ "and SUBSTRING(p.fliBtime,12,5) like '%"
					+ getFliBtime()
					+ "%'"
					+ "and SUBSTRING(p.fliAtime,12,5) like '%"
					+ getFliAtime() + "%'";
			// json数组，数据传送
			JSONArray json = JSONArray.fromObject(flightinformation
					.QueryInf(hql));
			response.getWriter().write(
					"{\"totalCount\":"
							+ flightinformation.QueryCounts(count_sql)
							+ ",\"root\":" + json.toString() + "}");
		}

		else if(type.equals("auto_insert")){
			//Json格式："param":value
			String f_sql = "select p.airFnumber from Airtype p where p.flag=1 and p.airCode='"+ getAirCode() + "'";
			String c_sql = "select p.airCnumber from Airtype p where p.flag=1 and p.airCode='"+ getAirCode() + "'";
			String y_sql = "select p.airYnumber from Airtype p where p.flag=1 and p.airCode='"+ getAirCode() + "'";
		
			StringBuffer num=new StringBuffer();
			//[{'id':value}]
			//[{id:'value'}]
			//
			num.append("[{'Fnumber':")
					.append(flightinformation.GetNumber(f_sql))
					.append(",'Cnumber':")
					.append(flightinformation.GetNumber(c_sql))
					.append(",'Ynumber':")
					.append(flightinformation.GetNumber(y_sql)).append("}]");
			response.getWriter().write(num.toString());
		}
		/*
		 * =========================================增加信息==========================
		 * =====================
		 */
		else if (type.equals("add")) {

			// 如果出现航班信息都一样的话，则拒绝插入
			// sql语句为查找（航空代码，起飞时间，起飞地点，航班号）
			//
			/*String f_sql = "select p.airFnumber from Airtype p where p.flag=1 and p.airCode='"
					+ getAirCode() + "'";
			String c_sql = "select p.airCnumber from Airtype p where p.flag=1 and p.airCode='"
					+ getAirCode() + "'";
			String y_sql = "select p.airYnumber from Airtype p where p.flag=1 and p.airCode='"
					+ getAirCode() + "'";*/

			String sql = "select count(p.fliAutoid) from Flightinformation p "
					+ "where p.comCode='" + getComCode() + "' and p.airCode='"
					+ getAirCode() + "' and p.fliBtime='" + getFliBtime()
					+ "' and p.fliBaddress='" + getFliBaddress()
					+ "' and p.fliNo='" + getFliNo() + "' and p.flag=1";
			// 判断能否插入
			boolean addornot = flightinformation.addornot(sql);
			// 不能插入返回的结果
			if (!addornot) {
				response.getWriter().write("{success:false}");
			} else {
				// 插入相应的值
				Flightinformation obj = new Flightinformation();
				obj.setComCode(getComCode());
				obj.setAirCode(getAirCode());
				obj.setFliEveryday(getFliEveryday());
				obj.setFliNo(getFliNo());
				obj.setFliDiscount(getFliDiscount());
				obj.setFliBaddress(getFliBaddress());
				obj.setFliAaddress(getFliAaddress());
				obj.setFliBtime(formatTime(getFliBtime()));
				obj.setFliAtime(formatTime(getFliAtime()));
				obj.setFliFnumber(getFliFnumber());
				obj.setFliCnumber(getFliCnumber());
				obj.setFliYnumber(getFliYnumber());
				obj.setFliFfare(getFliFfare());
				obj.setFliCfare(getFliCfare());
				obj.setFliYfare(getFliYfare());
				//obj.setFliRefundtime(String.valueOf(handler(getFliRefundtime())));
				obj.setFliRefundtime(getFliRefundtime());
				obj.setFliRefund(getFliRefund());
				//obj.setFliRefund(String.valueOf(handler(String.valueOf(getFliRefund()))));
				obj.setFlag((byte) 1);
				boolean a = flightinformation.add(obj);
				if (!a) {
					response.getWriter().write("{success:false}");
				}
			}
		}
		/*
		 * =========================================更新信息==========================
		 * =====================
		 */
		else if (type.equals("update")) {
			// 想法：如果修改关键字段后的出现五个关键字段完成一样的话就不给插入，并提示错误
			/*
			 * String sql =
			 * "select count(p.fliAutoid) from Flightinformation p " +
			 * "where p.fliBtime='" + getFliBtime() + "' and p.fliBaddress='" +
			 * getFliBaddress() + "' and p.flag=1"; // 判断能否更新
			 * System.out.println(sql); //此时如果为false则说明有数据，不能更新
			 * 那么！addornot则为true
			 * ,此时执行response.getWriter().write("{success:false}"); //否则执行更新操作
			 * boolean addornot = flightinformation.addornot(sql);
			 * System.out.println(addornot); // 不能更新返回的结果 if (!addornot) {
			 * response.getWriter().write("{success:false}"); } else {
			 */
			// 但是很可惜，实现不了，原因其实很简单就可以分析出来
			/*
			 * 如果你们有好的解决方案可以交流一下
			 */
			//Date m = new Date();
			/*
			 * 叶茂安 2011-04-20 
			 * 更新要解决的问题：
			 * 		1、	如果航班取消的话，就要同时update订票表
			 * 			解决方法：	1、是删除信息还是？
			 * 						2、对应的字段问题（个人觉得这个处理方法好，因为涉及退款问题）
			 */
			boolean a,b = false;
			//更新其它相应的字段信息（订票表的信息）	叶茂安 	2011-04-11
			/**
			 * 
			 */
			String book_update_hql="update Bookinformation b set b.flagPass=1 where b.comCode='"+getComCode()+"'";
			String bookinfo_update_hql="update Bookinformation b set b.comCode='" +
					getComCode()+"',b.booEveryday='" +
							getFliEveryday()+"',b.booNo='" +
									getFliNo()+"',b.booAaddress='" +
											getFliBaddress()+"',b.booBaddress='" +
													getFliAaddress()+"',b.booBtime='" +
													getStrtime(getFliBtime())+"',b.booAtime='" +
													getStrtime(getFliAtime())+"' where b.comCode='" +
															getComCode()+"' and b.flagPass=0";
			/**
			 * 	更新的时候如果订票表已经有人订票了，更新处理方法：
			 * 	1、不能更新座位、单价、折扣的信息
			 */
			
			String flight_update_hql = "update Flightinformation p set " + "p.comCode='"
				+ getComCode() + "',p.airCode='" + getAirCode()
				+ "',p.fliEveryday='" + getFliEveryday() + "',p.fliNo='"
				+ getFliNo() + "',p.fliDiscount='" + getFliDiscount()
				+ "',p.fliBaddress='" + getFliBaddress()
				+ "',p.fliAaddress='" + getFliAaddress() + "',p.fliBtime='"
				+ getStrtime(getFliBtime()) + "',p.fliAtime='"
				+ getStrtime(getFliAtime()) + "',p.fliFnumber='"
				+ getFliFnumber() + "',p.fliCnumber='" + getFliCnumber()
				+ "',p.fliYnumber='" + getFliYnumber() + "',p.fliFfare='"
				+ getFliFfare() + "',p.fliCfare='" + getFliCfare()
				+ "',p.fliYfare='" + getFliYfare() + "',p.fliRefundtime='"
				+ getFliRefundtime() + "',p.fliRefund='" + getFliRefund()
				+ "' where p.fliAutoid=" + getFliAutoid();
			if("取消".equals(getFliRefundtime()) || "关闭".equals(getFliRefund())){
				a = flightinformation.update(book_update_hql);
				b = flightinformation.update(flight_update_hql);
			}else{
				a = flightinformation.update(flight_update_hql);
				b = flightinformation.update(bookinfo_update_hql);
			}
			/*
			 * 		2、	如果航班起飞了呢？
			 */
			if (!(a && b)) {
				response.getWriter().write("{success:false}");
			}
			/* } */
		}
		/*
		 * =========================================删除信息==========================
		 * =====================
		 */
		else if (type.equals("delete")) {
			String hql = "update Flightinformation p set p.flag = 0 where p.fliAutoid="
					+ getFliAutoid();
			boolean a = flightinformation.delete(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			}
		}
		/* ======================= 取得航班信息在客户订票信息中显示=========================  */
		/* ----杨海亮  修改   2010 4 10   */
		else if(type.equals("fliInfo")){
			// 查询数据
			String hql = "select new map(" + "p.fliAutoid as fliAutoid,"
					+ "p.comCode as comCode," + "p.airCode as airCode,"
					+ "p.fliEveryday as fliEveryday," + "p.fliNo as fliNo,"
					+ "p.fliDiscount as fliDiscount,"
					+ "p.fliBaddress as fliBaddress,"
					+ "p.fliAaddress as fliAaddress,"
					+ "SUBSTRING(p.fliBtime,12,5) as fliBtime,"
					+ "SUBSTRING(p.fliAtime,12,5) as fliAtime,"
					+ "p.fliFnumber as fliFnumber,"
					+ "p.fliCnumber as fliCnumber,"
					+ "p.fliYnumber as fliYnumber," + "p.fliFfare as fliFfare,"
					+ "p.fliCfare as fliCfare," + "p.fliYfare as fliYfare,"
					+ "p.fliRefundtime as fliRefundtime,"
					+ "p.fliRefund as fliRefund) "
					+ "from Flightinformation p "
					+ "where p.flag=1 and p.fliRefundtime='正常' and p.fliRefund='运行'";
			// 取得相应的总数
			String count_sql = "select count(p.fliAutoid) from Flightinformation p "
					+ "where p.flag=1 and p.fliRefundtime='正常' and p.fliRefund='运行'";
					
			// json数组，数据传送
			JSONArray json = JSONArray.fromObject(flightinformation
					.QueryInf(hql));
			response.getWriter().write(
					"{\"totalCount\":"
							+ flightinformation.QueryCounts(count_sql)
							+ ",\"root\":" + json.toString() + "}");
		}
		return null;
	}

}
