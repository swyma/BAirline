package Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import DAO.AirtypeDAOImpl;
import DAO.IAirtypeDAO;
import PO.Airtype;

public class AirtypeServiceImpl implements IActionService {
	// start、limit分页传送数据
	private int start;
	private int limit;
	// 字段信息
	private Integer airAutoid;
	private String airCode;
	private String airF;
	private String airFname;
	private short airFnumber;
	private String airC;
	private String airCname;
	private short airCnumber;
	private String airY;
	private String airYname;
	private short airYnumber;
	private short airTotalnumber;

	// 定义DAO接口实现类
	private IAirtypeDAO airtype = new AirtypeDAOImpl();

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
			JSONArray json = JSONArray.fromObject(airtype.QueryAll(
					getStart(), getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + airtype.QueryCount()
							+ ",\"root\":" + json.toString() + "}");
		}
		// 增加信息
		else if (type.equals("add")) {
			Airtype obj = new Airtype();
			obj.setAirCode(getAirCode());
			obj.setAirF(getAirF());
			obj.setAirFname(getAirFname());
			obj.setAirFnumber(getAirFnumber());
			obj.setAirC(getAirC());
			obj.setAirCname(getAirCname());
			obj.setAirCnumber(getAirCnumber());
			obj.setAirY(getAirY());
			obj.setAirYname(getAirYname());
			obj.setAirYnumber(getAirYnumber());
			obj.setAirTotalnumber(getAirTotalnumber());
			obj.setFlag((byte) 1);
			boolean a = airtype.add(obj);
			if (!a) {
				response.getWriter().write("{success:false}");
			}
		}
		
		/* =============根据输入值查询======== */
		else if (type.equals("query")) {
			// 查询数据
			String hql = "SELECT new map(p.airAutoid as airAutoid,p.airCode as airCode,"
				    + "p.airF as airF,p.airFname as airFname,p.airFnumber as airFnumber,"
				    + "p.airC as airC,p.airCname as airCname,p.airCnumber as airCnumber,"
				    + "p.airY as airY,p.airYname as airYname,p.airYnumber as airYnumber,"
				    + "p.airTotalnumber as airTotalnumber)"
				    + "FROM Airtype p WHERE p.flag=1 "
					+ "and p.airCode like '%" + getAirCode()+ "%'" 
					+ "and p.airF like '%" + getAirF() + "%'"
					+ "and p.airC like '%" + getAirC() + "%'"
					+ "and p.airY like '%" + getAirY() + "%'"
					+ "and p.airTotalnumber >=" + getAirTotalnumber();
			/*======取得相应的总数========*/
			String count_sql = "select count(p.airAutoid) "
					+ "FROM Airtype p WHERE p.flag=1 "
					+ "and p.airCode like '%" + getAirCode()+ "%'" 
					+ "and p.airF like '%" + getAirF() + "%'"
					+ "and p.airC like '%" + getAirC() + "%'"
					+ "and p.airY like '%" + getAirY() + "%'"
					+ "and p.airTotalnumber >=" + getAirTotalnumber();
				
			// json数组，数据传送
			List a = airtype.QueryInf(hql);
			JSONArray json = JSONArray.fromObject(a);
			response.getWriter().write(
					"{\"totalCount\":"
							+ airtype.QueryCounts(count_sql)
							+ ",\"root\":" + json.toString() + "}");
			// 1、如果返回的是json语句的话，不能用form表单提交的形式，因为form表单提交返回的是表单是否成功提交
			// 2、如果要返回的是json语句，进行查询方面的交互的话，得用Ext.Ajax.request这种交互方式。
			// 3、store.reload()只用于刷新页面，如更新、删除数据后刷新，不能用于查询后的数据进行刷新，
			// 因为url是你是固定的，当然，也可以用store的url进行重新load。
			// response.getWriter().write("{success:true}");
		}
		
		// 更新信息
		else if (type.equals("update")) {
			String hql = "update Airtype p set p.airCode='"+ getAirCode()
			        +"',p.airF='"+ getAirF()+"',p.airFname='"+ getAirFname()+"',p.airFnumber='"+ getAirFnumber()
			        +"',p.airC='"+ getAirC()+"',p.airCname='"+ getAirCname()+"',p.airCnumber='"+ getAirCnumber()
			        +"',p.airY='"+ getAirY()+"',p.airYname='"+ getAirYname()+"',p.airYnumber='"+ getAirYnumber()
			        +"',p.airTotalnumber='"+ getAirTotalnumber()
			        +"' where p.airAutoid="+ getAirAutoid();
			boolean a = airtype.update(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			}
		}
		// 删除信息
		else if (type.equals("delete")) {		
			String hql = "update Airtype p set p.flag=0 where p.airAutoid="+ getAirAutoid();
			System.out.println(hql);
			boolean a = airtype.delete(hql);
			if (!a) {
				System.out.println(getAirAutoid());
				response.getWriter().write("{success:false}");
			}
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
	public Integer getAirAutoid() {
		return this.airAutoid;
	}
	public void setAirAutoid(Integer airAutoid) {
		this.airAutoid = airAutoid;
	}
	public String getAirCode() {
		return this.airCode;
	}
	public void setAirCode(String airCode) {
		this.airCode = airCode;
	}
	public String getAirF() {
		return this.airF;
	}
	public void setAirF(String airF) {
		this.airF = airF;
	}
	public String getAirFname() {
		return this.airFname;
	}
	public void setAirFname(String airFname) {
		this.airFname = airFname;
	}
	public short getAirFnumber() {
		return this.airFnumber;
	}
	public void setAirFnumber(short airFnumber) {
		this.airFnumber = airFnumber;
	}
	public String getAirC() {
		return this.airC;
	}
	public void setAirC(String airC) {
		this.airC = airC;
	}
	public String getAirCname() {
		return this.airCname;
	}
	public void setAirCname(String airCname) {
		this.airCname = airCname;
	}
	public short getAirCnumber() {
		return this.airCnumber;
	}
	public void setAirCnumber(short airCnumber) {
		this.airCnumber = airCnumber;
	}
	public String getAirY() {
		return this.airY;
	}
	public void setAirY(String airY) {
		this.airY = airY;
	}
	public String getAirYname() {
		return this.airYname;
	}
	public void setAirYname(String airYname) {
		this.airYname = airYname;
	}
	public short getAirYnumber() {
		return this.airYnumber;
	}
	public void setAirYnumber(short airYnumber) {
		this.airYnumber = airYnumber;
	}
	public short getAirTotalnumber() {
		return this.airTotalnumber;
	}
	public void setAirTotalnumber(short airTotalnumber) {
		this.airTotalnumber = airTotalnumber;
	}

}
