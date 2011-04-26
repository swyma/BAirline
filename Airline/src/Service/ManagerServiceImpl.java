package Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import DAO.IManagerDAO;
import DAO.ManagerDAOImpl;
import PO.Manager;



public class ManagerServiceImpl implements IActionService {

	// start、limit分页传送数据
	private int start;
	private int limit;
	// 字段信息
	private Integer manAutoid;
	private String manAccount;
	private String manPwd;
	private String manId;
	private String manSex;
	private String manTelnumber;
	private String manEmail;
	private String manRegister;
	private String Code;

	// 定义DAO接口实现类
	private IManagerDAO manager = new ManagerDAOImpl();

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
			JSONArray json = JSONArray.fromObject(manager.QueryAll(getStart(),
					getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + manager.QueryCount() + ",\"root\":"
							+ json.toString() + "}");
		}
		// 增加信息
		else if (type.equals("add")) {
			Manager obj = new Manager();
			obj.setManAccount(getManAccount());
			obj.setManPwd(getManPwd());
			obj.setManId(Long.parseLong(getManId()));
			obj.setManSex(getManSex());
			obj.setManTelnumber(getManTelnumber());
			obj.setManEmail(getManEmail());
			obj.setManRegister(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			obj.setFlag((byte) 1);
			boolean a = manager.add(obj);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		// 更新信息
		else if (type.equals("update")) {
			String hql = "update Manager p set p.manPwd='" + getManPwd()
					+ "',p.manId=" + Long.parseLong(getManId()) + ",p.manSex='"
					+ getManSex() + "',p.manTelnumber='" + getManTelnumber()
					+ "',p.manEmail='" + getManEmail()
					+ "' where p.manAccount='" + getManAccount() + "'";
			boolean a = manager.update(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		// 删除信息
		else if (type.equals("delete")) {
			String hql = "update Manager p set p.flag=" + (byte) 0
					+ " where p.manAutoid=" + getManAutoid();
			boolean a = manager.delete(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		// 查询信息
		else if (type.equals("query")) {
			// 查找所有信息
			String hql = "SELECT new map(p.manAutoid as manAutoid,p.manAccount as manAccount,p.manPwd as manPwd,"
					+ "p.manId as manId,p.manSex as manSex,p.manTelnumber as manTelnumber,"
					+ "p.manEmail as manEmail,p.manRegister as manRegister)"
					+ "FROM Manager p WHERE p.flag=1 and p.manAccount='"
					+ getManAccount()
					+ "'"
					+ " or  p.manId= '"
					+ getManId()
					+ "'";
			System.out.println(hql);
			// 取相应的总数
			String count_sql = "select count(p.manAutoid) from Manager p "
					+ "WHERE p.flag=1 and p.manAccount='" + getManAccount()
					+ "'" + " or  p.manId= '" + getManId() + "'";
			System.out.println(count_sql);
			// 数据传送
			JSONArray json = JSONArray.fromObject(manager.QueryManager(hql));
			response.getWriter().write(
					"{\"totalCount\":" + manager.QueryCount(count_sql)
							+ ",\"root\":" + json.toString() + "}");
		}
		else if(type.equals("doorout")){
			  HttpSession session=request.getSession();
			  session.invalidate();
		}
		else if (type.equals("login")) {
			String name_hql = "select count(p.manAutoid) from Manager p "
				+ "where p.flag=1 and p.manAccount='" + getManAccount()
				+ "'";
		// 如果不存在该用户的话，DAO层返回的值为true，!a则为flase,为false时匹配密码
		boolean a = manager.checkName(name_hql);
		if (a) {
			response.getWriter().write("{success:false}");
		} else {
			String pwd_hql = "select p.manPwd from Manager p "
					+ "where p.flag=1 and p.manAccount='" + getManAccount()
					+ "'";
			String b = manager.checkPwd(pwd_hql);
			if (!(b.equals(getManPwd()))) {
				response.getWriter().write("{success:false}");
			}else{
				//用于获取image.jsp的验证码
				String rand = (String)request.getSession(true).getAttribute("yanzhengma");
				boolean c=rand.equals(getCode()); 
				if (!c) {
				  response.getWriter().write("{success:false}"); 
				  }else{
					  HttpSession session=request.getSession(); 
					  session.setAttribute("name",getManAccount());
			 		}
		 		}
			}
			
		}

		return null;
	}

	/* ====================setter and getter========================= */
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

	public Integer getManAutoid() {
		return manAutoid;
	}

	public void setManAutoid(Integer manAutoid) {
		this.manAutoid = manAutoid;
	}

	public String getManAccount() {
		return manAccount;
	}

	public void setManAccount(String manAccount) {
		this.manAccount = manAccount;
	}

	public String getManPwd() {
		return manPwd;
	}

	public void setManPwd(String manPwd) {
		this.manPwd = manPwd;
	}

	public String getManId() {
		return manId;
	}

	public void setManId(String manId) {
		this.manId = manId;
	}

	public String ManEmail() {
		return manSex;
	}

	public void setManSex(String manSex) {
		this.manSex = manSex;
	}

	public String getManTelnumber() {
		return manTelnumber;
	}

	public void setManTelnumber(String manTelnumber) {
		this.manTelnumber = manTelnumber;
	}

	public String getManEmail() {
		return manEmail;
	}

	public void setManEmail(String manEmail) {
		this.manEmail = manEmail;
	}

	public String getManRegister() {
		return manRegister;
	}

	public void setManRegister(String manRegister) {
		this.manRegister = manRegister;
	}

	public IManagerDAO getManager() {
		return manager;
	}

	public void setManager(IManagerDAO manager) {
		this.manager = manager;
	}

	public String getManSex() {
		return manSex;
	}
	
	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

}
