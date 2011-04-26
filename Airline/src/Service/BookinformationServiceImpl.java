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

//�༭�ߣ����
public class BookinformationServiceImpl implements
		IBookinformationActionService {
	// start��limit��ҳ��������
	private int start;
	private int limit;
	// �ֶ���Ϣ
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

	// ������Ϣ�޸�
	private String useraccount;
	private String userid;
	private String userphone;
	private String useremail;
	// �����޸�
	private String username;
	private String adminnewpassword;

	// ����DAO�ӿ�ʵ����
	private IBookinformationDAO bookinformation = new BookinformationDAOImpl();
	private IRefundRecordDAO refundrecord = new RefundRecordDAOImpl();
	private IFlightinformationDAO flightinformation = new FlightinformationDAOImpl();

	/*
	 * ʱ�䴦���� 2011-04-20 Ҷï��
	 */
	/*
	 * �������ĸ�ʽ��2011-04-10 13:21:31 ��ʽ��Ϊ 2011-09-01 12��30��00
	 */
	public Calendar localFormat(String time) {
		Date date = new Date();
		String year = time.substring(0, 4);
		String month = time.substring(5, 7);
		String day = time.substring(8, 10);
		String hours = time.substring(11, 13);
		String minutes = time.substring(14, 16);
		String secondes = time.substring(17, 19);

		// ��ƥ����ַ������ص�date��
		date.setYear(new Integer(year)-1900);
		date.setMonth(new Integer(month));
		date.setDate(new Integer(day));
		date.setHours(new Integer(hours));
		date.setMinutes(new Integer(minutes));
		date.setSeconds(new Integer(secondes));

		// Calendar����
		Calendar cal = Calendar.getInstance();
		cal.clear(); // �����ɾ�����ḽ��һЩû�õ�ʱ����Ϣ
		cal.setTime(date); // ��date���ص�cal�У����صĽ���ǣ�2011-03-11 12��00��00
		return cal;
	}

	/*
	 * formatTime:��ʽ��ʱ�� 2011-09-01 12��30��00
	 */
	public Calendar formatTime(String time) {

		Date date = new Date();
		String hours = time.substring(0, 2);
		String minutes = time.substring(3, 5);

		// ��ƥ����ַ������ص�date��
		date.setHours(new Integer(hours));
		date.setMinutes(new Integer(minutes));
		date.setSeconds(0);

		// Calendar����
		Calendar cal = Calendar.getInstance();
		cal.clear(); // �����ɾ�����ḽ��һЩû�õ�ʱ����Ϣ
		cal.setTime(date); // ��date���ص�cal�У����صĽ���ǣ�2011-03-11 12��00��00
		return cal;
	}

	// ȡ��F��λ������
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

	// ȡ��C��λ������
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

	// ȡ��Y��λ������
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

	// <���ڿͻ���Ʊ������ʾ 2011 4 8 >
	// ȡ��F��λ�ļ۸�
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

	// ȡ��C��λ�ļ۸�
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

	// ȡ��Y��λ�ļ۸�
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

	// ȡ�ú����ۿ�
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

	// ȡ����λ�ŷ���
	public int getFCYNumber(String comcode, String boono, String boobaddress,
			String boobtime, String booberth) {
		int FCYNumber = 0; // �������������λ��λ�ŵ���ʱ����
		// �õ�������λ��������Ϣ
		BookinformationServiceImpl booking = new BookinformationServiceImpl();
		int FNumber = booking
				.get_FNumber(comcode, boono, boobaddress, boobtime);
		int CNumber = booking
				.get_CNumber(comcode, boono, boobaddress, boobtime);
		int YNumber = booking
				.get_YNumber(comcode, boono, boobaddress, boobtime);

		/* 2011 3 25�޸� */

		/*
		 * ��Ʊʵ��˼�룺 �ͻ���Ʊ֮ǰ��ȥ��Ʊ���в鿴�Ƿ�������Ʊ������������Ʊ�ͻ�����λ���Ÿ��˿ͻ�, ���������˳���ſͻ���λ
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
			// �����Ʊ����û�пͻ���Ʊ����˳����ͻ�������λ��
			// ��ӡ��ʾ���������������Ϣ
			// �ڿͻ���һ�ζ�Ʊʱ����ɾ��֮ǰ��������Ʊ���е���Ϣ
			/*
			 * String sql=
			 * "delete from Refundrecord r,Bookinformation b where r.cusId=b.cusId and b.comCode='"
			 * +getComCode()
			 * +"' and b.booNo='"+getBooNo()+"' and b.booEveryday='"
			 * +getBooEveryday()
			 * +"' and b.booBaddress='"+getBooBaddress()+"' and b.booBtime='"
			 * +getBooBtime()+"'";
			 */

			// json ��ʽ: [{'id':'value' }] [{id:'value' }] [{'id':value}]
			if ("ͷ�Ȳ�".equals(booberth)) {
				if (FNumber > 0) {
					FCYNumber = FNumber;
				} else if (FNumber == 0) {
					FCYNumber = 0;
				}
			} else if ("���ò�".equals(booberth)) {
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
		// ��ȡ����
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();

		// ������Ӧ�ĸ�ʽ
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");

		// ��ѯ����
		if (type.equals("QueryAll")) {
			JSONArray json = JSONArray.fromObject(bookinformation.QueryAll(
					getStart(), getLimit()));
			response.getWriter().write(
					"{\"totalCount\":" + bookinformation.QueryCount()
							+ ",\"root\":" + json.toString() + "}");
		}
		// ������Ϣ
		else if (type.equals("add")) {
			Bookinformation obj = new Bookinformation();
			// ��Ʊ֮ǰ�ȼ����һ���������¿ͻ��Ƿ�Ʊ
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
				 * 2011-04-20 Ҷï�� �޸����ݣ�1����string ��Ϊ calendar 2����ȫ����
				 * 3����ʱ�䱾�ػ����������ݿ�
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
				if (!a) { // ���ܶ�Ʊ
					response.getWriter().write("{success:false}");
				} else {
					// ����Ʊ��ͬʱ���²�λ���� ��Ҫɾ����Ʊ������Ӧ�Ŀͻ���Ʊ��Ϣ ͬʱ��Ҫ���ͻ�������Ӧ�Ļ���

					// ����������Ʊ���е���Ϣ
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

					// �����ж��Ƿ�������Ʊ

					JSONArray json1 = JSONArray.fromObject(refundrecord
							.get_refundInfo(hql));

					// Object
					// booAutoid_number=json1.getJSONObject(0).get("count_number");
					Integer booAutoid_number = Integer.parseInt((json1
							.getJSONObject(0).get("count_number").toString()));

					if (booAutoid_number != 0) { // ���˶�Ʊʱ��ɾ����Ʊ������Ӧ����Ʊ��Ϣ
						Integer booautoid = Integer
								.parseInt((json1.getJSONObject(0).get(
										"boo_autoid").toString()));
						// ȡ����Ʊ������Ӧ����λ��
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

					// ���²�λ����
					int FNumber = get_FNumber(getComCode(), getBooNo(),
							getBooBaddress(), getBooBtime());
					int CNumber = get_CNumber(getComCode(), getBooNo(),
							getBooBaddress(), getBooBtime());
					int YNumber = get_YNumber(getComCode(), getBooNo(),
							getBooBaddress(), getBooBtime());
					if ("ͷ�Ȳ�".equals(getBooBerth())) {
						if (FNumber > 0) { // ���������λ��������ͻ���Ʊ
							FNumber = FNumber - 1;
							// ���±�flightinformation��F��λ��������Ϣ
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

					} else if ("���ò�".equals(getBooBerth())) {
						if (CNumber > 0) { // ���������λ��������ͻ���Ʊ
							CNumber = CNumber - 1;
							// ���±�flightinformation��C��λ��������Ϣ
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
						if (YNumber > 0) { // ���������λ��������ͻ���Ʊ
							YNumber = YNumber - 1;
							// ���±�flightinformation��Y��λ��������Ϣ
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

					// ������û��ʵ��
					/*
					 * //����Ӧ�Ŀͻ����ϻ��� ����û����ֱ����Ѿ��д˿ͻ�������и��¿ͻ�������Ϣ,�������ͻ�������Ϣ //
					 * ����1������ȡ���û����ֱ��пͻ����id String sql3=
					 * "select new map(count(p.cusId) as cusId) from Customer p,Bookinformation b where p.cusId=b.cusId and b.cusId='"
					 * +getCusId()+"'"; JSONArray
					 * json3=JSONArray.fromObject(bookinformation
					 * .search_cusId(sql3)); int
					 * cusid=Integer.parseInt(json3.getJSONObject
					 * (0).get("cusId").toString());
					 * System.out.println("cusid--->"+cusid);
					 * 
					 * if(cusid!=0){ //�ͻ��л��� ������ȡ������֮����»�����Ϣ
					 * //����2��ȡ����Ӧ�ͻ��ڿͻ����ֱ��е���Ϣ String
					 * sql2="select c.cusIntegral from Customer c where c.cusId='"
					 * +getCusId()+"'"; int
					 * integral=customer.GetcusIntegral(sql2);
					 * System.out.println("integral-->"+integral);
					 * 
					 * String
					 * sql5="update Customer p set p.cusIntegral='"+(integral
					 * +10)+"'where p.cusId='"+getCusId()+"'";
					 * customer.update(sql5); }else{ //���������Ϣ Customer cus=new
					 * Customer(); cus.setCusId(getCusId());
					 * cus.setCusIntegral(10); customer.add(cus);
					 * 
					 * }
					 */

				}
				String str = "��Ʊ�ɹ���";
				response.getWriter().write(str);
			}// �ͻ��Ѿ���Ʊ
			else {
				String str = "�Բ��𣬸ÿͻ��Ѿ���Ʊ!";
				response.getWriter().write(str);
			}
		}
		// ������Ϣ ��Ʊ
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

				// ��Ʊ��ͬʱ����Ʊ��Ϣ��������Ʊ����
				Refundrecord ref = new Refundrecord();
				ref.setBooAutoid(getBooAutoid());
				ref.setBooNumber(getBooNumber());
				boolean b = refundrecord.add(ref);
				if (!b) {
					response.getWriter().write("{success:false}");
				}

				// ͬʱ����Ӧ�ĺ�����Ϣ���޸���λ��Ϣ
				if ("ͷ�Ȳ�".equals(getBooBerth())) {
					if (FNumber >= 0) {
						FNumber = FNumber + 1;
						// ���±�flightinformation��F��λ��������Ϣ
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

				} else if ("���ò�".equals(getBooBerth())) {
					if (CNumber >= 0) {
						CNumber = CNumber + 1;
						// ���±�flightinformation��C��λ��������Ϣ
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
						// ���±�flightinformation��Y��λ��������Ϣ
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

				// ��Ʊ��ͬʱ��Ҫ��ȥ�ͻ���Ӧ�Ļ���

			}
		}
		/*
		 * // ��ö�Ʊ�ͻ������֤����
		else if (type.equals("search_cusId")) {
			String hql = "select p.cusId from Bookinformation p";
			List list = bookinformation.search_cusId(hql);
			StringBuffer json = new StringBuffer();
			// ƴ��һ��json��ʽ������
			json.append("[");
			for (int i = 0; i < list.size(); i++) {
				json.append("['").append(list.get(i)).append("','")
						.append(list.get(i)).append("']");
				if (i < list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");
			// ���ݴ���
			response.getWriter().write(json.toString());

		}
		*/
		// ����ض������Ŀͻ���Ϣ
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
		// ******************************* �����������չ�˾
		else if (type.equals("search_comName")) {
			String hql = "select p.comName from Flightcompany p where p.flag=1";
			List list = bookinformation.search_comName(hql);
			StringBuffer json = new StringBuffer();
			// ƴ��һ��json��ʽ������
			json.append("[");
			for (int i = 0; i < list.size(); i++) {
				json.append("['").append(list.get(i)).append("','")
						.append(list.get(i)).append("']");
				if (i < list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");
			// ���ݴ���
			response.getWriter().write(json.toString());
		}
		// ������Ϣ�޸�
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
		// �����޸�
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

		// �ͻ���λ����
		else if (type.equals("get_booNumber")) { /* 2011 3 19 */
			// ����һ�������
			BookinformationServiceImpl booking = new BookinformationServiceImpl();
			// �õ���λ��
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
			// ����һ����¼��λ�۸�ı���
			double fare;
			// ����һ�������
			BookinformationServiceImpl booking = new BookinformationServiceImpl();

			DecimalFormat df = new DecimalFormat("0.00");

			// �õ������ۿ�
			double flidiscount = booking.get_fliFare(getComCode(), getBooNo(),
					getBooBaddress(), getBooBtime());
			/* System.out.println("flidiscount==>"+flidiscount); */

			if ("ͷ�Ȳ�".equals(getBooBerth())) {
				double Ffare = booking.get_Ffare(getComCode(), getBooNo(),
						getBooBaddress(), getBooBtime());
				/* System.out.println("Ffare==>"+Ffare); */
				if (flidiscount != 0) {
					fare = Ffare * flidiscount;
				} else {
					fare = Ffare;
				}

			} else if ("���ò�".equals(getBooBerth())) {
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
