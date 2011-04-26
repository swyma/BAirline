package Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import DAO.*;
import PO.Faretype;
import PO.Flightcompany;

public class FaretypeServiceImpl implements IActionService {

	private int start;
	private int limit;
	private IFaretypeDAO faretype = new FaretypeDAOImpl();

	private String farComcode;
	private Integer farGoldscore;
	private double farGolddiscount;
	private Integer farSilscore;
	private double farSildiscount;
	private Integer farBroscore;
	private double farBrodiscount;

	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		// 接受数据
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		// 编制响应的格式
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");

		if (type.equals("queryall")) {
			JSONArray json = JSONArray.fromObject(faretype.queryall(
					));
			response.getWriter().write(
					"{\"totalCount\":" + faretype.QueryCount()
							+ ",\"root\":" + json.toString() + "}");
		}/*
		 * 查询所有航空公司的航空代码
		 */
		else if (type.equals("add_comcode")) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			List list = faretype.GetcomCode();
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
else if (type.equals("update")) {
			String hql="update Faretype p set p.farComcode='"+getFarComcode()+"',p.farGoldscore='"+getFarGoldscore()
			+"',p.farGolddiscount='"+getFarGolddiscount()+"',p.farSilscore='"+getFarSilscore()+"',p.farSildiscount='"+getFarSildiscount()
			+"',p.farBroscore='"+getFarBroscore()+"',p.farBrodiscount='"+getFarBrodiscount()+"' where p.farComcode='"+getFarComcode()+"'";
			boolean a=faretype.update(hql);
			System.out.println(hql);
			// 取相应的总数
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		else if (type.equals("del")) {
			String hql = "delete from Faretype p " 
			+ " where p.farComcode=" + getFarComcode();
			boolean a=faretype.del(hql);
			System.out.println(hql);
			if(!a)
			{
				response.getWriter().write("{success:false}");
			}
			else
			{
				response.getWriter().write("{success:true}");
			}
		}
		// 查询信息
		else if (type.equals("query")) {
			// 查找所有信息
			 String hql = "select new map(p.farComcode as farComcode,p.farGoldscore as farGoldscore,p.farGolddiscount as farGolddiscount,p.farSilscore as farSilscore,p.farSildiscount as farSildiscount,p.farBroscore as farBroscore,p.farBrodiscount as farBrodiscount)"
				 +"from Faretype p where p.farComcode='"+ getFarComcode()+"'";
			System.out.println(hql);
			// 取相应的总数
			String count_sql = "select count(p.farComcode) from Faretype p "
					+ "WHERE  p.farComcode='" + getFarComcode()
					+ "'" ;
			System.out.println(count_sql);
			// 数据传送
			JSONArray json = JSONArray.fromObject(faretype.QueryFaretype(hql));
			response.getWriter().write(
					"{\"totalCount\":" + faretype.QueryCount(count_sql)
							+ ",\"root\":" + json.toString() + "}");
		}
		
		else if (type.equals("add")) {
			Faretype obj = new Faretype();
			obj.setFarComcode(getFarComcode());
			obj.setFarGoldscore(getFarGoldscore());
			obj.setFarGolddiscount(getFarGolddiscount());
			obj.setFarSilscore(getFarSilscore());
			obj.setFarSildiscount(getFarSildiscount());
			obj.setFarBroscore(getFarBroscore());
			obj.setFarBrodiscount(getFarBrodiscount());
			boolean a = faretype.add(obj);
			if (!a) {
				response.getWriter().write("{success:false}");
			}
			else
			{
				response.getWriter().write("{success:true}");
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

	public IFaretypeDAO getFaretype() {
		return faretype;
	}

	public void setFaretype(IFaretypeDAO faretype) {
		this.faretype = faretype;
	}

	public String getFarComcode() {
		return farComcode;
	}

	public void setFarComcode(String farComcode) {
		this.farComcode = farComcode;
	}

	public Integer getFarGoldscore() {
		return farGoldscore;
	}

	public void setFarGoldscore(Integer farGoldscore) {
		this.farGoldscore = farGoldscore;
	}

	public double getFarGolddiscount() {
		return farGolddiscount;
	}

	public void setFarGolddiscount(double farGolddiscount) {
		this.farGolddiscount = farGolddiscount;
	}

	public Integer getFarSilscore() {
		return farSilscore;
	}

	public void setFarSilscore(Integer farSilscore) {
		this.farSilscore = farSilscore;
	}

	public double getFarSildiscount() {
		return farSildiscount;
	}

	public void setFarSildiscount(double farSildiscount) {
		this.farSildiscount = farSildiscount;
	}

	public Integer getFarBroscore() {
		return farBroscore;
	}

	public void setFarBroscore(Integer farBroscore) {
		this.farBroscore = farBroscore;
	}

	public double getFarBrodiscount() {
		return farBrodiscount;
	}

	public void setFarBrodiscount(double farBrodiscount) {
		this.farBrodiscount = farBrodiscount;
	}
}
