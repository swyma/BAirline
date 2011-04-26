package Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import DAO.HotelinformationDAOImpl;
import DAO.IHotelinformationDAO;
import PO.Hotelinformation;

public class HotelinformationServiceImpl implements IActionService {

	// start、limit分页传送数据
	private int start;
	private int limit;
	// 字段信息
	private Integer hotelAutoid;
	private String hotelName;
	private Integer hotelLevel;
	private String hotelTel;
	private String hotelCity;
	private String hotelAddress;
	private String hotelPage;
	private String hotelPicture;
	private int flag;

	// 定义DAO接口实现类
	private IHotelinformationDAO hotelinformation = new HotelinformationDAOImpl();

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
			JSONArray json = JSONArray.fromObject(hotelinformation.QueryAll(
					getStart(), getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + hotelinformation.QueryCount()
							+ ",\"root\":" + json.toString() + "}");
		}
		// 增加信息
		else if (type.equals("add")) {
			System.out.println(getHotelName());
			Hotelinformation obj = new Hotelinformation();
			//obj.setHotelAddress(hotelAddress)
			obj.setHotelName(getHotelName());
			obj.setHotelLevel(getHotelLevel());
			obj.setHotelTel(getHotelTel());
			obj.setHotelCity(getHotelCity());
			obj.setHotelAddress(getHotelAddress());
			obj.setHotelPage(getHotelPage());
			obj.setHotelPicture(getHotelPicture());
			obj.setFlag((int)1);
			boolean a = hotelinformation.add(obj);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		// 更新信息
		else if (type.equals("update")) {
			String hql = "update Hotelinformation p set p.hotelName='"+getHotelName()
	
			+"',p.hotelLevel="+getHotelLevel() + ",p.hotelTel='" + getHotelTel()
					+ "',p.hotelCity='" + getHotelCity() + "',p.hotelAddress='"
					+ getHotelAddress() + "',p.hotelPage='" + getHotelPage()
					+ "',p.hotelPicture='" + getHotelPicture()
					+ "' where p.hotelAutoid=" + getHotelAutoid() + "";
			boolean a = hotelinformation.update(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		// 删除信息
		else if (type.equals("delete")) {
			String hql = "update Hotelinformation p set p.flag=0"
					+ " where p.hotelAutoid=" + getHotelAutoid();
			boolean a = hotelinformation.delete(hql);
			if (!a) {
				response.getWriter().write("{success:false}");
			} else {
				response.getWriter().write("{success:true}");
			}
		}
		// 查询信息
		else if (type.equals("query")) {
			// 查找所有信息
			String hql = "SELECT new map(p.hotelAutoid as hotelAutoid,p.hotelName as hotelName,p.hotelLevel as hotelLevel,"
					+ "p.hotelTel as hotelTel,p.hotelCity as hotelCity,p.hotelAddress as hotelAddress,"
					+ "p.hotelPage as hotelPage,p.hotelPicture as hotelPicture)"
					+ "FROM Hotelinformation p WHERE p.flag=1 and p.hotelLevel='"
					+ getHotelLevel()
					+ "'"
					+ " or  p.hotelCity= '"
					+ getHotelCity() + "'";
			System.out.println(hql);
			// 取相应的总数
			String count_sql = "select count(p.hotelAutoid) from Hotelinformation p "
					+ "WHERE p.flag=1 and p.hotelLevel='"+ getHotelLevel()+ "'"
					+ " or  p.hotelCity= '" + getHotelCity() + "'";
			System.out.println(count_sql);
			// 数据传送
			JSONArray json = JSONArray.fromObject(hotelinformation
					.QueryHotelinformation(hql));
			response.getWriter().write(
					"{\"totalCount\":" + hotelinformation.QueryCount(count_sql)
							+ ",\"root\":" + json.toString() + "}");
		} else if (type.equals("doorout")) {
			HttpSession session = request.getSession();
			session.invalidate();
		} else if (type.equals("login")) {
			String name_hql = "select count(p.hotelAutoid) from Hotelinformation p "
					+ "where p.flag=1 and p.hotelLevel='" + getHotelLevel() + "'";
			// 如果不存在该用户的话，DAO层返回的值为true，!a则为flase,为false时匹配密码
			boolean a = hotelinformation.checkName(name_hql);
			if (a) {
				response.getWriter().write("{success:false}");
			} else {
				String city_hql = "select p.hotelCity from Hotelinformation p "
						+ "where p.flag=1 and p.hotelCity='" + getHotelCity() + "'";
				String b = hotelinformation.checkCity(city_hql);
				if (!(b.equals(getHotelCity()))) {
					response.getWriter().write("{success:false}");
				} else {
					// 用于获取image.jsp的验证码
					String rand = (String) request.getSession(true)
							.getAttribute("yanzhengma");
					boolean c = rand.equals(getHotelCity());
					if (!c) {
						response.getWriter().write("{success:false}");
					} else {
						HttpSession session = request.getSession();
						session.setAttribute("name", getHotelName());
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

	public Integer getHotelAutoid() {
		return hotelAutoid;
	}

	public void setHotelAutoid(Integer hotelAutoid) {
		this.hotelAutoid = hotelAutoid;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public String getHotelTel() {
		return hotelTel;
	}

	public void setHotelTel(String hotelTel) {
		this.hotelTel = hotelTel;
	}

	public String getHotelCity() {
		return hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getHotelPage() {
		return hotelPage;
	}

	public void setHotelPage(String hotelPage) {
		this.hotelPage = hotelPage;
	}

	public String getHotelPicture() {
		return hotelPicture;
	}

	public void setHotelPicture(String hotelPicture) {
		this.hotelPicture = hotelPicture;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public IHotelinformationDAO getHotelinformation() {
		return hotelinformation;
	}

	public void setHotelinformation(IHotelinformationDAO hotelinformation) {
		this.hotelinformation = hotelinformation;
	}

	

}
