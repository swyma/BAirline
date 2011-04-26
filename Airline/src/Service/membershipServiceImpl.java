package Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.*;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;


import DAO.ImembershipDAO;
import DAO.membershipDAOImpl;
import PO.Membership;;



public class membershipServiceImpl implements IActionService {

	// start、limit分页传送数据
	private int start;
	private int limit;
	// 字段信息
	private Integer comment_autoid;
	private long customer_id;
	private String customer_name;
	private String comment_content;
	private Calendar comment_time;
	private String comment_IP;
	

	

	// 定义DAO接口实现类
	private ImembershipDAO membership = new membershipDAOImpl();
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
		if (type.equals("queryall")) {
			JSONArray json = JSONArray.fromObject(membership.QueryAll(getStart(),
					getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + membership.QueryCount() + ",\"root\":"
							+ json.toString() + "}");
		}
		
		// 更新信息
		else if (type.equals("update")) {
			String hql = "update Membership p set p.customerId='" + getCustomer_id()
			   + "',p.customerName='"
					+ getCustomer_name() + "',p.commentContent='" + getComment_content()
					+ "',p.commentTime='" + formatTime(getComment_time().toString())+"',p.commentIp='"+ getComment_IP()
					+ "' where p.customerId='" + getCustomer_id() + "'";
			boolean a = membership.update(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		// 删除信息
		else if (type.equals("delete")) {
			String hql = "update Membership p set p.flag=" + (int) 0
					+ " where p.customerId=" + getCustomer_id();
			boolean a = membership.delete(hql);
/*			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}*/
		}
		// 查询信息
		else if (type.equals("query")) {
			// 查找所有信息
			String hql = "SELECT new map(p.customerId as customer_id,p.customerName as customer_name,p.commentContent as comment_content,"
					+ "p.commentTime as comment_time,p.commentIp as comment_IP)"
					+ "FROM Membership p WHERE p.flag=1 and p.customerId='"
					+ getCustomer_id()
					+ "'";
			System.out.println(hql);
			// 取相应的总数
			String count_sql = "select count(p.customerId) from Membership p "
					+ "WHERE p.flag=1 and p.customerId='" + getCustomer_id()
					+ "'" ;
			System.out.println(count_sql);
			// 数据传送
			JSONArray json = JSONArray.fromObject(membership.Querymembership(hql));
			response.getWriter().write(
					"{\"totalCount\":" + membership.QueryCount(count_sql)
							+ ",\"root\":" + json.toString() + "}");
		}
		

		return null;
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

	StringBuffer sb = new StringBuffer();

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
	String years=time.substring(0, 4);
	String months=time.substring(5, 7);
	String days=time.substring(8, 10);
	String hours = time.substring(11, 13);
	String minutes = time.substring(14, 16);
	String seconds=time.substring(17, 19);
    
	System.out.println("seconds-----" + seconds);
	System.out.println("minutes-----" + minutes);
	System.out.println("hours-----" + hours);
	System.out.println("day-----" + days);
	System.out.println("months-----" + months);
	System.out.println("years-----" + years);
	// 将匹配的字符串加载到date中
	date.setYear(new Integer(years));
	date.setMonth(new Integer(months));
	date.setDate(new Integer(days));
	date.setHours(new Integer(hours));
	date.setMinutes(new Integer(minutes));
	date.setSeconds(new Integer(seconds));

	// Calendar对象
	Calendar cal = Calendar.getInstance();
	cal.clear(); // 如果不删除还会附带一些没用的时间信息
	cal.setTime(date);
	return cal;
	}// 将date加载到cal中，返回的结果是：2011-03-11 12：00：00

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

	
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Calendar getComment_time() {
		return comment_time;
	}
	public void setComment_time(Calendar comment_time) {
		this.comment_time = comment_time;
	}
	public ImembershipDAO getMembership() {
		return membership;
	}
	public void setMembership(ImembershipDAO membership) {
		this.membership = membership;
	}
	
	public Integer getComment_autoid() {
		return comment_autoid;
	}
	public void setComment_autoid(Integer comment_autoid) {
		this.comment_autoid = comment_autoid;
	}
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public String getComment_IP() {
		return comment_IP;
	}
	public void setComment_IP(String comment_IP) {
		this.comment_IP = comment_IP;
	}


	

		
}
