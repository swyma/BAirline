package Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import DAO.*;
import PO.Flightcompany;

public class FlightcompanyServiceImpl implements IActionService {
	// start、limit分页传送数据
	private int start;
	private int limit;
	// 字段信息
	private Integer comAutoid;
	private String comCode;
	private String comName;
	private String comAddress;
	private String comInformation;

	// 定义DAO接口实现类
	private IFlightcompanyDAO flightcompany = new FlightcompanyDAOImpl();

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
			JSONArray json = JSONArray.fromObject(flightcompany.QueryAll(
					getStart(), getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + flightcompany.QueryCount()
							+ ",\"root\":" + json.toString() + "}");
		}
		// 增加信息
		else if (type.equals("add")) {
			Flightcompany obj = new Flightcompany();
			obj.setComCode(getComCode());
			obj.setComAddress(getComAddress());
			obj.setComInformation(getComInformation());
			obj.setComName(getComName());
			obj.setComRegister(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
			obj.setFlag((byte) 1);
			boolean a = flightcompany.add(obj);
			if (!a) {
				response.getWriter().write("{success:false}");
			}
		}
		// 更新信息
		else if (type.equals("update")) {
			String hql = "update Flightcompany p set p.comCode='"
					+ getComCode() + "',p.comName='" + getComName()
					+ "',p.comAddress='" + getComAddress()
					+ "',p.comInformation='" + getComInformation()
					+ "' where p.comAutoid=" + getComAutoid();
			boolean a = flightcompany.update(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			}
		}
		// 删除信息
		else if (type.equals("delete")) {
			String hql = "delete from Flightcompany p where p.comAutoid="
					+ getComAutoid();
			boolean a = flightcompany.delete(hql);
			if (!a) {
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

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComAddress() {
		return comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	public String getComInformation() {
		return comInformation;
	}

	public void setComInformation(String comInformation) {
		this.comInformation = comInformation;
	}

	public Integer getComAutoid() {
		return comAutoid;
	}

	public void setComAutoid(Integer comAutoid) {
		this.comAutoid = comAutoid;
	}

}
