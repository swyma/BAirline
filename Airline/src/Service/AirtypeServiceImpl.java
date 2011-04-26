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
	// start��limit��ҳ��������
	private int start;
	private int limit;
	// �ֶ���Ϣ
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

	// ����DAO�ӿ�ʵ����
	private IAirtypeDAO airtype = new AirtypeDAOImpl();

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		// ��ȡ����
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();

		// ������Ӧ�ĸ�ʽ
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		// ��ѯ����
		if (type.equals("queryall")) {
			JSONArray json = JSONArray.fromObject(airtype.QueryAll(
					getStart(), getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + airtype.QueryCount()
							+ ",\"root\":" + json.toString() + "}");
		}
		// ������Ϣ
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
		
		/* =============��������ֵ��ѯ======== */
		else if (type.equals("query")) {
			// ��ѯ����
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
			/*======ȡ����Ӧ������========*/
			String count_sql = "select count(p.airAutoid) "
					+ "FROM Airtype p WHERE p.flag=1 "
					+ "and p.airCode like '%" + getAirCode()+ "%'" 
					+ "and p.airF like '%" + getAirF() + "%'"
					+ "and p.airC like '%" + getAirC() + "%'"
					+ "and p.airY like '%" + getAirY() + "%'"
					+ "and p.airTotalnumber >=" + getAirTotalnumber();
				
			// json���飬���ݴ���
			List a = airtype.QueryInf(hql);
			JSONArray json = JSONArray.fromObject(a);
			response.getWriter().write(
					"{\"totalCount\":"
							+ airtype.QueryCounts(count_sql)
							+ ",\"root\":" + json.toString() + "}");
			// 1��������ص���json���Ļ���������form���ύ����ʽ����Ϊform���ύ���ص��Ǳ��Ƿ�ɹ��ύ
			// 2�����Ҫ���ص���json��䣬���в�ѯ����Ľ����Ļ�������Ext.Ajax.request���ֽ�����ʽ��
			// 3��store.reload()ֻ����ˢ��ҳ�棬����¡�ɾ�����ݺ�ˢ�£��������ڲ�ѯ������ݽ���ˢ�£�
			// ��Ϊurl�����ǹ̶��ģ���Ȼ��Ҳ������store��url��������load��
			// response.getWriter().write("{success:true}");
		}
		
		// ������Ϣ
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
		// ɾ����Ϣ
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
