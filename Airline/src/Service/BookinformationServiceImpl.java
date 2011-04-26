package Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import DAO.BookinformationDAOImpl;
import DAO.FlightcompanyDAOImpl;
import DAO.IBookinformationDAO;
import DAO.IFlightcompanyDAO;
import DAO.IRefundRecordDAO;
import DAO.RefundRecordDAOImpl;
import PO.Bookinformation;
import PO.Refundrecord;
import DAO.FlightinformationDAOImpl;
import DAO.IFlightinformationDAO;

//编辑者：杨海亮
public class BookinformationServiceImpl implements
		IBookinformationActionService {
	// start、limit分页传送数据
	private int start;
	private int limit;
	// 字段信息
	private Long booAutoid;
	private String comCode;
	private String cusId;
	private String booEveryday;
	private String booNo;
	private String booBaddress;
	private String booAaddress;
	private String booBtime;
	private String booAtime;
	private String booBerth;
	private Short booNumber;
	private Double booFare;
	private String booTime;
	private Byte flagPay;
	private Byte flagType;
	private Byte flagPass;

	// 个人信息修改
	private String useraccount;
	private String userid;
	private String userphone;
	private String useremail;
	// 密码修改
	private String username;
	private String adminnewpassword;

	// 定义DAO接口实现类
	private IBookinformationDAO bookinformation = new BookinformationDAOImpl();
	private IRefundRecordDAO refundrecord = new RefundRecordDAOImpl();
	private IFlightinformationDAO flightinformation = new FlightinformationDAOImpl();

	/*
	 * 时间处理方法 2011-04-20 叶茂安
	 */
	/*
	 * 传过来的格式是2011-04-10 13:21:31 格式化为 2011-09-01 12：30：00
	 */
	public Calendar localFormat(String time) {
		Date date = new Date();
		String year = time.substring(0, 4);
		String month = time.substring(5, 7);
		String day = time.substring(8, 10);
		String hours = time.substring(11, 13);
		String minutes = time.substring(14, 16);
		String secondes = time.substring(17, 19);

		// 将匹配的字符串加载到date中
		date.setYear(new Integer(year)-1900);
		date.setMonth(new Integer(month));
		date.setDate(new Integer(day));
		date.setHours(new Integer(hours));
		date.setMinutes(new Integer(minutes));
		date.setSeconds(new Integer(secondes));

		// Calendar对象
		Calendar cal = Calendar.getInstance();
		cal.clear(); // 如果不删除还会附带一些没用的时间信息
		cal.setTime(date); // 将date加载到cal中，返回的结果是：2011-03-11 12：00：00
		return cal;
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

	// 取得F舱位的人数
	public int get_FNumber(String comcode, String boono, String boobaddress,
			String boobtime) {
		String f_sql = "select p.fliFnumber from Flightinformation p where p.flag=1 and p.comCode='"
				+ comcode
				+ "'"
				+ "and p.fliNo='"
				+ boono
				+ "' and p.fliBaddress ='"
				+ boobaddress
				+ "' and SUBSTRING(p.fliBtime,12,5) like '" + boobtime + "'";
		return flightinformation.GetNumber(f_sql);
	}

	// 取得C舱位的人数
	public int get_CNumber(String comcode, String boono, String boobaddress,
			String boobtime) {
		String c_sql = "select p.fliCnumber from Flightinformation p where p.flag=1 and p.comCode='"
				+ comcode
				+ "'"
				+ "and p.fliNo='"
				+ boono
				+ "' and p.fliBaddress='"
				+ boobaddress
				+ "' and SUBSTRING(p.fliBtime,12,5) like '" + boobtime + "'";
		return flightinformation.GetNumber(c_sql);

	}

	// 取得Y舱位的人数
	public int get_YNumber(String comcode, String boono, String boobaddress,
			String boobtime) {
		String y_sql = "select p.fliYnumber from Flightinformation p where p.flag=1 and p.comCode='"
				+ comcode
				+ "'"
				+ "and p.fliNo='"
				+ boono
				+ "' and p.fliBaddress='"
				+ boobaddress
				+ "' and SUBSTRING(p.fliBtime,12,5) like '" + boobtime + "'";
		return flightinformation.GetNumber(y_sql);
	}

	public int get_booking_FCY_Number(String comcode, String boono,
			String boobaddress, String boobtime, String booberth) {
		String hql = "select count(p.booAutoid) from Bookinformatin p where p.flagPass=0 and p.comCode="
				+ comcode
				+ " and p.booNo="
				+ boono
				+ " and p.booBaddress="
				+ boobaddress
				+ " and SUBSTRING(p.booBtime,12,5) like '"
				+ boobtime
				+ "' and p.booBerth=" + booberth;
		return bookinformation.Booking_FCY_Number(hql);
	}

	// <用于客户订票金额的显示 2011 4 8 >
	// 取得F舱位的价格
	public double get_Ffare(String comcode, String boono, String boobaddress,
			String boobtime) {
		String f_sql = "select p.fliFfare from Flightinformation p where p.flag=1 and p.comCode='"
				+ comcode
				+ "'"
				+ "and p.fliNo='"
				+ boono
				+ "' and p.fliBaddress='"
				+ boobaddress
				+ "' and SUBSTRING(p.fliBtime,12,5) like '" + boobtime + "'";

		System.out.println("bookinformation.getflifare(f_sql)=="
				+ bookinformation.getflifare(f_sql));

		return bookinformation.getflifare(f_sql);
	}

	// 取得C舱位的价格
	public double get_Cfare(String comcode, String boono, String boobaddress,
			String boobtime) {
		String c_sql = "select p.fliCfare from Flightinformation p where p.flag=1 and p.comCode='"
				+ comcode
				+ "'"
				+ "and p.fliNo='"
				+ boono
				+ "' and p.fliBaddress='"
				+ boobaddress
				+ "' and SUBSTRING(p.fliBtime,12,5) like '" + boobtime + "'";
		return bookinformation.getflifare(c_sql);
	}

	// 取得Y舱位的价格
	public double get_Yfare(String comcode, String boono, String boobaddress,
			String boobtime) {
		String y_sql = "select p.fliYfare from Flightinformation p where p.flag=1 and p.comCode='"
				+ comcode
				+ "'"
				+ "and p.fliNo='"
				+ boono
				+ "' and p.fliBaddress='"
				+ boobaddress
				+ "' and SUBSTRING(p.fliBtime,12,5) like '" + boobtime + "'";
		return bookinformation.getflifare(y_sql);
	}

	// 取得航班折扣
	public double get_fliFare(String comcode, String boono, String boobaddress,
			String boobtime) {
		String sql = "select p.fliDiscount from Flightinformation p where p.flag=1 and p.comCode='"
				+ comcode
				+ "'"
				+ "and p.fliNo='"
				+ boono
				+ "' and p.fliBaddress='"
				+ boobaddress
				+ "' and SUBSTRING(p.fliBtime,12,5) like '" + boobtime + "'";
		return bookinformation.get_fliDiscount(sql);
	}

	// 取得座位号方法
	public int getFCYNumber(String comcode, String boono, String boobaddress,
			String boobtime, String booberth) {
		int FCYNumber = 0; // 用来保存各个舱位座位号的临时变量
		// 得到各个舱位的人数信息
		BookinformationServiceImpl booking = new BookinformationServiceImpl();
		int FNumber = booking
				.get_FNumber(comcode, boono, boobaddress, boobtime);
		int CNumber = booking
				.get_CNumber(comcode, boono, boobaddress, boobtime);
		int YNumber = booking
				.get_YNumber(comcode, boono, boobaddress, boobtime);

		/* 2011 3 25修改 */

		/*
		 * 退票实现思想： 客户订票之前先去退票表中查看是否有人退票，若有则将已退票客户的座位安排给此客户, 否则继续按顺序安排客户座位
		 */
		/*
		 * String hql=
		 * "select new map(count(a.booAutoid) as count_number,b.booNumber  as boo_number) from Bookinformation a,Refundrecord b where a.booAutoid=b.booAutoid"
		 * + " and a.comCode='"+getComCode()+"' and a.booNo='"+getBooNo()+
		 * "' and a.booBaddress='"+getBooBaddress()
		 * +"' and a.booBtime='"+getBooBtime
		 * ()+"' and a.booBerth='"+getBooBerth()+"'";
		 */
		String hql = "select new map(count(a.booAutoid) as count_number,b.booNumber  as boo_number) from Bookinformation a,Refundrecord b where a.booAutoid=b.booAutoid"
				+ " and a.comCode='"
				+ comcode
				+ "' and a.booNo='"
				+ boono
				+ "' and a.booBaddress='"
				+ boobaddress
				+ "' and SUBSTRING(a.booBtime,12,5) like'"
				+ boobtime
				+ "' and a.booBerth='" + booberth + "'";

		JSONArray json1 = JSONArray
				.fromObject(refundrecord.get_refundInfo(hql));
		Integer booAutoid_number = Integer.parseInt((json1.getJSONObject(0)
				.get("count_number").toString()));

		/* System.out.println("booAutoid_number--->"+booAutoid_number); */

		if (booAutoid_number != 0) {

			JSONArray json2 = JSONArray.fromObject(refundrecord
					.get_refundInfo(hql));
			Integer Number = Integer.parseInt(json2.getJSONObject(0)
					.get("boo_number").toString());
			FCYNumber = Number;
		} else {
			// 如果退票表中没有客户退票，则按顺序给客户安排座位号
			// 打印显示各个航班的人数信息
			// 在客户下一次订票时，先删除之前保存在退票表中的信息
			/*
			 * String sql=
			 * "delete from Refundrecord r,Bookinformation b where r.cusId=b.cusId and b.comCode='"
			 * +getComCode()
			 * +"' and b.booNo='"+getBooNo()+"' and b.booEveryday='"
			 * +getBooEveryday()
			 * +"' and b.booBaddress='"+getBooBaddress()+"' and b.booBtime='"
			 * +getBooBtime()+"'";
			 */

			// json 格式: [{'id':'value' }] [{id:'value' }] [{'id':value}]
			if ("头等舱".equals(booberth)) {
				if (FNumber > 0) {
					FCYNumber = FNumber;
				} else if (FNumber == 0) {
					FCYNumber = 0;
				}
			} else if ("经济舱".equals(booberth)) {
				if (CNumber > 0) {
					FCYNumber = CNumber;
				} else if (CNumber == 0) {
					FCYNumber = 0;
				}
			} else {
				if (YNumber > 0) {
					FCYNumber = YNumber;
				} else if (YNumber == 0) {
					FCYNumber = 0;
				}
			}
		}
		return FCYNumber;
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		// 获取参数
		HttpServletRequest request = ServletActionContext.getRequest();
		// 响应数据
		HttpServletResponse response = ServletActionContext.getResponse();

		// 编制响应的格式
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");

		// 查询所有
		if (type.equals("QueryAll")) {
			JSONArray json = JSONArray.fromObject(bookinformation.QueryAll(
					getStart(), getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + bookinformation.QueryCount()
							+ ",\"root\":" + json.toString() + "}");
		}
		// 增加信息
		else if (type.equals("add")) {
			Bookinformation obj = new Bookinformation();
			// 订票之前先检查在一定的条件下客户是否订票
			String book_sql = "select new map(count(p.cusId) as cusid_number) from Bookinformation p where p.flagPass=0 and p.comCode='"
					+ getComCode()
					+ "' and p.booNo='"
					+ getBooNo()
					+ "' and p.booEveryday='"
					+ getBooEveryday()
					+ "' and p.booBaddress='"
					+ getBooBaddress()
					+ "' and SUBSTRING(p.booBtime,12,5) like '"
					+ getBooBtime()
					+ "' and p.cusId='"
					+ getCusId()
					+"' and p.flagPass=0";

			JSONArray json_book = JSONArray.fromObject(bookinformation
					.search_cusId(book_sql));
			int book_cusId_number = Integer.parseInt(json_book.getJSONObject(0)
					.get("cusid_number").toString());

			// System.out.println("book_cusId--->"+book_cusId_number);

			if (book_cusId_number == 0) {
				obj.setComCode(getComCode());
				obj.setCusId(getCusId());
				obj.setBooNo(getBooNo());
				obj.setBooEveryday(getBooEveryday());
				obj.setBooBaddress(getBooBaddress());
				obj.setBooAaddress(getBooAaddress());
				/*
				 * 2011-04-20 叶茂安 修改内容：1、将string 将为 calendar 2、补全方法
				 * 3、将时间本地化，插入数据库
				 */

				obj.setBooBtime(formatTime(getBooBtime()));
				obj.setBooAtime(formatTime(getBooAtime()));
				obj.setBooBerth(getBooBerth());
				obj.setBooNumber(getBooNumber());
				// obj.setBooTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				// .format(getBooTime()));
				// obj.setBooTime(getStrtime(getBooTime()));
				obj.setBooTime(localFormat(getBooTime()));
				obj.setBooFare(getBooFare());
				obj.setFlagType((byte) 0);
				// obj.setFlagPay(getFlagPay());
				obj.setFlagPay((byte) 1);
				obj.setFlagPass((byte) 0);
				boolean a = bookinformation.add(obj);
				if (!a) { // 不能订票
					response.getWriter().write("{success:false}");
				} else {
					// 增加票的同时更新舱位人数 还要删除退票表中相应的客户退票信息 同时还要给客户加上相应的积分

					// 用来查找退票表中的信息
					String hql = "select new map(count(a.booAutoid) as count_number ,b.booAutoid as boo_autoid,b.booNumber as boo_number) from Bookinformation a,Refundrecord b where a.booAutoid=b.booAutoid"
							+ " and a.comCode='"
							+ getComCode()
							+ "' and a.booNo='"
							+ getBooNo()
							+ "' and a.booBaddress='"
							+ getBooBaddress()
							+ "' and SUBSTRING(a.booBtime,12,5) like '"
							+ getBooBtime()
							+ "' and a.booBerth='" + getBooBerth() + "'";

					// 用来判断是否有人退票

					JSONArray json1 = JSONArray.fromObject(refundrecord
							.get_refundInfo(hql));

					// Object
					// booAutoid_number=json1.getJSONObject(0).get("count_number");
					Integer booAutoid_number = Integer.parseInt((json1
							.getJSONObject(0).get("count_number").toString()));

					if (booAutoid_number != 0) { // 有人订票时则删除退票表中相应的退票信息
						Integer booautoid = Integer
								.parseInt((json1.getJSONObject(0).get(
										"boo_autoid").toString()));
						// 取出退票表中相应的座位号
						Integer boonumber = Integer
								.parseInt((json1.getJSONObject(0).get(
										"boo_number").toString()));

						String sql = "delete from Refundrecord b where b.booAutoid='"
								+ booautoid
								+ "' and b.booNumber='"
								+ boonumber
								+ "'";
						refundrecord.delete(sql);

					}

					// 更新舱位人数
					int FNumber = get_FNumber(getComCode(), getBooNo(),
							getBooBaddress(), getBooBtime());
					int CNumber = get_CNumber(getComCode(), getBooNo(),
							getBooBaddress(), getBooBtime());
					int YNumber = get_YNumber(getComCode(), getBooNo(),
							getBooBaddress(), getBooBtime());
					if ("头等舱".equals(getBooBerth())) {
						if (FNumber > 0) { // 如果还有座位，则允许客户订票
							FNumber = FNumber - 1;
							// 更新表flightinformation中F舱位人数的信息
							String hql_FNumber = "update Flightinformation p set p.fliFnumber='"
									+ FNumber
									+ "' where p.comCode='"
									+ getComCode()
									+ "' and p.fliNo='"
									+ getBooNo()
									+ "' and p.fliBaddress='"
									+ getBooBaddress()
									+ "' and SUBSTRING(p.fliBtime,12,5) like '"
									+ getBooBtime() + "'";
							flightinformation.update(hql_FNumber);
						}

					} else if ("经济舱".equals(getBooBerth())) {
						if (CNumber > 0) { // 如果还有座位，则允许客户订票
							CNumber = CNumber - 1;
							// 更新表flightinformation中C舱位人数的信息
							String hql_CNumber = "update Flightinformation p set p.fliCnumber='"
									+ CNumber
									+ "' where p.comCode='"
									+ getComCode()
									+ "' and p.fliNo='"
									+ getBooNo()
									+ "' and p.fliBaddress='"
									+ getBooBaddress()
									+ "' and SUBSTRING(p.fliBtime,12,5) like '"
									+ getBooBtime() + "'";
							flightinformation.update(hql_CNumber);
						}
					} else {
						if (YNumber > 0) { // 如果还有座位，则允许客户订票
							YNumber = YNumber - 1;
							// 更新表flightinformation中Y舱位人数的信息
							String hql_YNumber = "update Flightinformation p set p.fliYnumber='"
									+ YNumber
									+ "' where p.comCode='"
									+ getComCode()
									+ "' and p.fliNo='"
									+ getBooNo()
									+ "' and p.fliBaddress='"
									+ getBooBaddress()
									+ "' and SUBSTRING(p.fliBtime,12,5) like '"
									+ getBooBtime() + "'";
							flightinformation.update(hql_YNumber);
						}
					}

					// 功能暂没有实现
					/*
					 * //给相应的客户加上积分 如果用户积分表中已经有此客户，则进行更新客户积分信息,否则插入客户积分信息 //
					 * 步骤1：首先取出用户积分表中客户身份id String sql3=
					 * "select new map(count(p.cusId) as cusId) from Customer p,Bookinformation b where p.cusId=b.cusId and b.cusId='"
					 * +getCusId()+"'"; JSONArray
					 * json3=JSONArray.fromObject(bookinformation
					 * .search_cusId(sql3)); int
					 * cusid=Integer.parseInt(json3.getJSONObject
					 * (0).get("cusId").toString());
					 * System.out.println("cusid--->"+cusid);
					 * 
					 * if(cusid!=0){ //客户有积分 ，则先取出积分之后更新积分信息
					 * //步骤2：取出相应客户在客户积分表中的信息 String
					 * sql2="select c.cusIntegral from Customer c where c.cusId='"
					 * +getCusId()+"'"; int
					 * integral=customer.GetcusIntegral(sql2);
					 * System.out.println("integral-->"+integral);
					 * 
					 * String
					 * sql5="update Customer p set p.cusIntegral='"+(integral
					 * +10)+"'where p.cusId='"+getCusId()+"'";
					 * customer.update(sql5); }else{ //插入积分信息 Customer cus=new
					 * Customer(); cus.setCusId(getCusId());
					 * cus.setCusIntegral(10); customer.add(cus);
					 * 
					 * }
					 */

				}
				String str = "订票成功！";
				response.getWriter().write(str);
			}// 客户已经订票
			else {
				String str = "对不起，该客户已经订票!";
				response.getWriter().write(str);
			}
		}
		// 更新信息 退票
		else if (type.equals("update")) {
			String hql = "update Bookinformation p set p.flagPass=1 where p.booAutoid='"
					+ getBooAutoid() + "'";

			int FNumber = get_FNumber(getComCode(), getBooNo(),
					getBooBaddress(), getBooBtime());
			int CNumber = get_CNumber(getComCode(), getBooNo(),
					getBooBaddress(), getBooBtime());
			int YNumber = get_YNumber(getComCode(), getBooNo(),
					getBooBaddress(), getBooBtime());

			boolean a = bookinformation.update(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {

				// 退票的同时把退票信息保存在退票表中
				Refundrecord ref = new Refundrecord();
				ref.setBooAutoid(getBooAutoid());
				ref.setBooNumber(getBooNumber());
				boolean b = refundrecord.add(ref);
				if (!b) {
					response.getWriter().write("{success:false}");
				}

				// 同时在相应的航班信息中修改座位信息
				if ("头等舱".equals(getBooBerth())) {
					if (FNumber >= 0) {
						FNumber = FNumber + 1;
						// 更新表flightinformation中F舱位人数的信息
						String hql_FNumber = "update Flightinformation p set p.fliFnumber='"
								+ FNumber
								+ "' where p.comCode='"
								+ getComCode()
								+ "' and p.fliNo='"
								+ getBooNo()
								+ "' and p.fliBaddress='"
								+ getBooBaddress()
								+ "' and SUBSTRING(p.fliBtime,12,5) like '" + getBooBtime() + "'";
						flightinformation.update(hql_FNumber);
					}

				} else if ("经济舱".equals(getBooBerth())) {
					if (CNumber >= 0) {
						CNumber = CNumber + 1;
						// 更新表flightinformation中C舱位人数的信息
						String hql_CNumber = "update Flightinformation p set p.fliCnumber='"
								+ CNumber
								+ "' where p.comCode='"
								+ getComCode()
								+ "' and p.fliNo='"
								+ getBooNo()
								+ "' and p.fliBaddress='"
								+ getBooBaddress()
								+ "' and SUBSTRING(p.fliBtime,12,5) like '" + getBooBtime() + "'";
						flightinformation.update(hql_CNumber);
					}
				} else {
					if (YNumber >= 0) {
						YNumber = YNumber + 1;
						// 更新表flightinformation中Y舱位人数的信息
						String hql_YNumber = "update Flightinformation p set p.fliYnumber='"
								+ YNumber
								+ "' where p.comCode='"
								+ getComCode()
								+ "' and p.fliNo='"
								+ getBooNo()
								+ "' and p.fliBaddress='"
								+ getBooBaddress()
								+ "' and SUBSTRING(p.fliBtime,12,5) like '" + getBooBtime() + "'";
						flightinformation.update(hql_YNumber);
					}
				}

				// 退票的同时还要减去客户相应的积分

			}
		}
		/*
		 * // 获得订票客户的身份证号码
		else if (type.equals("search_cusId")) {
			String hql = "select p.cusId from Bookinformation p";
			List list = bookinformation.search_cusId(hql);
			StringBuffer json = new StringBuffer();
			// 拼凑一个json形式的数组
			json.append("[");
			for (int i = 0; i < list.size(); i++) {
				json.append("['").append(list.get(i)).append("','")
						.append(list.get(i)).append("']");
				if (i < list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");
			// 数据传送
			response.getWriter().write(json.toString());

		}
		*/
		// 获得特定条件的客户信息
		else if (type.equals("search_cusId_information")) {
			String hql = "SELECT new map(p.booAutoid as booAutoid,p.cusId as cusId,p.comCode as comCode,"
					+ "p.booEveryday as booEveryday,p.booNo as booNo,p.booBaddress as booBaddress,p.booAaddress as booAaddress,"
					+ "SUBSTRING(p.booBtime,12,5) as booBtime,SUBSTRING(p.booAtime,12,5) as booAtime,p.booBerth as booBerth,"
					+ "p.booNumber as booNumber,SUBSTRING(p.booTime,12,5) as booTime,p.booFare as booFare,"
					+ "p.flagPay as flagPay,p.flagPass as flagPass) "
					+ "FROM Bookinformation p where p.cusId='" + getCusId()+"' or p.booNo='"+getBooNo()
					+"' or p.comCode='"+getComCode()+"' and p.flagPass=0";
			JSONArray json = JSONArray.fromObject(bookinformation
					.search_information(hql));
			response.getWriter().write("{\"root\":" + json.toString() + "}");
		}
		// ******************************* 用于搜索航空公司
		else if (type.equals("search_comName")) {
			String hql = "select p.comName from Flightcompany p where p.flag=1";
			List list = bookinformation.search_comName(hql);
			StringBuffer json = new StringBuffer();
			// 拼凑一个json形式的数组
			json.append("[");
			for (int i = 0; i < list.size(); i++) {
				json.append("['").append(list.get(i)).append("','")
						.append(list.get(i)).append("']");
				if (i < list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");
			// 数据传送
			response.getWriter().write(json.toString());
		}
		// 个人信息修改
		else if (type.equals("individual_update")) {
			String hql = "update Manager p set  p.manId='"
					+ Long.parseLong(getUserid()) + "',p.manTelnumber='"
					+ getUserphone() + "',p.manEmail='" + getUseremail()
					+ "' where p.manAccount='" + getUseraccount() + "'";
			boolean a = bookinformation.individual_update(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		// 密码修改
		else if (type.equals("password_update")) {
			String hql = "update Manager p set p.manPwd='"
					+ getAdminnewpassword() + "' where p.manAccount='"
					+ getUsername() + "'";
			boolean a = bookinformation.password_update(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}

		}

		// 客户座位安排
		else if (type.equals("get_booNumber")) { /* 2011 3 19 */
			// 定义一个类变量
			BookinformationServiceImpl booking = new BookinformationServiceImpl();
			// 得到座位号
			int FCYnumber = booking.getFCYNumber(getComCode(), getBooNo(),
					getBooBaddress(), getBooBtime(), getBooBerth());

			if (FCYnumber >= 0) {
				StringBuffer json = new StringBuffer();
				json.append("[");
				json.append("{'text':'").append(FCYnumber).append("','flag':'")
						.append(true).append("'}");
				json.append("]	");
				response.getWriter().write(json.toString());
			} else {
				response.getWriter().write("{success:true}");
			}

		} else if (type.equals("get_flifare")) {
			// 定义一个记录舱位价格的变量
			double fare;
			// 定义一个类变量
			BookinformationServiceImpl booking = new BookinformationServiceImpl();

			DecimalFormat df = new DecimalFormat("0.00");

			// 得到航班折扣
			double flidiscount = booking.get_fliFare(getComCode(), getBooNo(),
					getBooBaddress(), getBooBtime());
			/* System.out.println("flidiscount==>"+flidiscount); */

			if ("头等舱".equals(getBooBerth())) {
				double Ffare = booking.get_Ffare(getComCode(), getBooNo(),
						getBooBaddress(), getBooBtime());
				/* System.out.println("Ffare==>"+Ffare); */
				if (flidiscount != 0) {
					fare = Ffare * flidiscount;
				} else {
					fare = Ffare;
				}

			} else if ("经济舱".equals(getBooBerth())) {
				double Cfare = booking.get_Cfare(getComCode(), getBooNo(),
						getBooBaddress(), getBooBtime());
				/* System.out.println("Cfare==>"+Cfare); */

				if (flidiscount != 0) {
					fare = Cfare * flidiscount;
				} else {
					fare = Cfare;
				}

			} else {
				double Yfare = booking.get_Yfare(getComCode(), getBooNo(),
						getBooBaddress(), getBooBtime());
				/* System.out.println("Yfare==>"+Yfare); */

				if (flidiscount != 0) {
					fare = Yfare * flidiscount;
				} else {
					fare = Yfare;
				}
			}

			StringBuffer json = new StringBuffer();
			json.append("[");
			json.append("{'fliFare':'").append(new Double(df.format(fare)))
					.append("'}");
			json.append("]	");
			response.getWriter().write(json.toString());

		}

		return null;
	}

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

	public Long getBooAutoid() {
		return booAutoid;
	}

	public void setBooAutoid(Long booAutoid) {
		this.booAutoid = booAutoid;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getBooEveryday() {
		return booEveryday;
	}

	public void setBooEveryday(String booEveryday) {
		this.booEveryday = booEveryday;
	}

	public String getBooNo() {
		return booNo;
	}

	public void setBooNo(String booNo) {
		this.booNo = booNo;
	}

	public String getBooBaddress() {
		return booBaddress;
	}

	public void setBooBaddress(String booBaddress) {
		this.booBaddress = booBaddress;
	}

	public String getBooAaddress() {
		return booAaddress;
	}

	public void setBooAaddress(String booAaddress) {
		this.booAaddress = booAaddress;
	}

	public String getBooBtime() {
		return booBtime;
	}

	public void setBooBtime(String booBtime) {
		this.booBtime = booBtime;
	}

	public String getBooAtime() {
		return booAtime;
	}

	public void setBooAtime(String booAtime) {
		this.booAtime = booAtime;
	}

	public String getBooBerth() {
		return booBerth;
	}

	public void setBooBerth(String booBerth) {
		this.booBerth = booBerth;
	}

	public Short getBooNumber() {
		return booNumber;
	}

	public void setBooNumber(Short booNumber) {
		this.booNumber = booNumber;
	}

	public Double getBooFare() {
		return booFare;
	}

	public void setBooFare(Double booFare) {
		this.booFare = booFare;
	}

	public String getBooTime() {
		return booTime;
	}

	public void setBooTime(String booTime) {
		this.booTime = booTime;
	}

	public Byte getFlagPay() {
		return flagPay;
	}

	public void setFlagPay(Byte flagPay) {
		this.flagPay = flagPay;
	}

	public Byte getFlagType() {
		return flagType;
	}

	public void setFlagType(Byte flagType) {
		this.flagType = flagType;
	}

	public Byte getFlagPass() {
		return flagPass;
	}

	public void setFlagPass(Byte flagPass) {
		this.flagPass = flagPass;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getAdminnewpassword() {
		return adminnewpassword;
	}

	public void setAdminnewpassword(String adminnewpassword) {
		this.adminnewpassword = adminnewpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
