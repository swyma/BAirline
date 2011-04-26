package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import DAO.*;

public class StatisticsServiceImpl implements IActionService  {
	private IStatisticsDAO Statistics=new StatisticsDAOImpl();
	
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
		if (type.equals("querymonth")) {
			JSONArray json = JSONArray.fromObject(Statistics.QueryMonth());
			response.getWriter().write(json.toString());
		}else if(type.equals("queryyear")){
			JSONArray json = JSONArray.fromObject(Statistics.QueryYear());
			response.getWriter().write(json.toString());			
		}
		return null;
	}

}
