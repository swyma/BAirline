//编辑者：周永丰
package PO;

import java.util.Calendar;
import java.util.Date;

public class Flightinformation implements java.io.Serializable {

	// Fields

	private long fliAutoid;
	private Flightcompany flightcompany;
	//添加属性
	private String comCode;
	private Airtype airtype;
	//添加属性
	private String airCode;
	private String fliEveryday;
	private String fliNo;
	private double fliDiscount;
	private String fliBaddress;
	private String fliAaddress;
	private Calendar  fliBtime;
	private Calendar  fliAtime;
	private short fliFnumber;
	private short fliCnumber;
	private short fliYnumber;
	private double fliFfare;
	private double fliCfare;
	private double fliYfare;
	private String fliRefundtime;
	private String fliRefund;
	private byte flag;

	// Constructors

	/** default constructor */
	public Flightinformation() {
	}

	/** minimal constructor */
	public Flightinformation(long fliAutoid, Flightcompany flightcompany,String comCode,
			Airtype airtype, String airCode ,String fliEveryday, String fliNo,
			double fliDiscount, String fliBaddress, String fliAaddress,
			Calendar  fliBtime, Calendar  fliAtime, String fliRefundtime, String fliRefund,
			byte flag) {
		this.fliAutoid = fliAutoid;
		this.flightcompany = flightcompany;
		this.comCode=comCode;
		this.airtype = airtype;
		this.airCode=airCode;
		this.fliEveryday = fliEveryday;
		this.fliNo = fliNo;
		this.fliDiscount = fliDiscount;
		this.fliBaddress = fliBaddress;
		this.fliAaddress = fliAaddress;
		this.fliBtime = fliBtime;
		this.fliAtime = fliAtime;
		this.fliRefundtime = fliRefundtime;
		this.fliRefund = fliRefund;
		this.flag = flag;
	}

	/** full constructor */
	public Flightinformation(long fliAutoid, Flightcompany flightcompany,String comCode,
			Airtype airtype,String airCode, String fliEveryday, String fliNo,
			double fliDiscount, String fliBaddress, String fliAaddress,
			Calendar  fliBtime, Calendar  fliAtime, short fliFnumber, short fliCnumber,
			short fliYnumber, double fliFfare, double fliCfare,
			double fliYfare, String fliRefundtime, String fliRefund, byte flag) {
		this.fliAutoid = fliAutoid;
		this.flightcompany = flightcompany;
		this.comCode=comCode;
		this.airtype = airtype;
		this.airCode=airCode;
		this.fliEveryday = fliEveryday;
		this.fliNo = fliNo;
		this.fliDiscount = fliDiscount;
		this.fliBaddress = fliBaddress;
		this.fliAaddress = fliAaddress;
		this.fliBtime = fliBtime;
		this.fliAtime = fliAtime;
		this.fliFnumber = fliFnumber;
		this.fliCnumber = fliCnumber;
		this.fliYnumber = fliYnumber;
		this.fliFfare = fliFfare;
		this.fliCfare = fliCfare;
		this.fliYfare = fliYfare;
		this.fliRefundtime = fliRefundtime;
		this.fliRefund = fliRefund;
		this.flag = flag;
	}

	// Property accessors

	public long getFliAutoid() {
		return this.fliAutoid;
	}

	public void setFliAutoid(long fliAutoid) {
		this.fliAutoid = fliAutoid;
	}

	public Flightcompany getFlightcompany() {
		return this.flightcompany;
	}

	public void setFlightcompany(Flightcompany flightcompany) {
		this.flightcompany = flightcompany;
	}

	public Airtype getAirtype() {
		return this.airtype;
	}

	public void setAirtype(Airtype airtype) {
		this.airtype = airtype;
	}

	public String getFliEveryday() {
		return this.fliEveryday;
	}

	public void setFliEveryday(String fliEveryday) {
		this.fliEveryday = fliEveryday;
	}

	public String getFliNo() {
		return this.fliNo;
	}

	public void setFliNo(String fliNo) {
		this.fliNo = fliNo;
	}

	public double getFliDiscount() {
		return this.fliDiscount;
	}

	public void setFliDiscount(double fliDiscount) {
		this.fliDiscount = fliDiscount;
	}

	public String getFliBaddress() {
		return this.fliBaddress;
	}

	public void setFliBaddress(String fliBaddress) {
		this.fliBaddress = fliBaddress;
	}

	public String getFliAaddress() {
		return this.fliAaddress;
	}

	public void setFliAaddress(String fliAaddress) {
		this.fliAaddress = fliAaddress;
	}

	public Calendar  getFliBtime() {
		return this.fliBtime;
	}

	public void setFliBtime(Calendar  fliBtime) {
		this.fliBtime = fliBtime;
	}

	public Calendar  getFliAtime() {
		return this.fliAtime;
	}

	public void setFliAtime(Calendar  fliAtime) {
		this.fliAtime = fliAtime;
	}

	public short getFliFnumber() {
		return this.fliFnumber;
	}

	public void setFliFnumber(short fliFnumber) {
		this.fliFnumber = fliFnumber;
	}

	public short getFliCnumber() {
		return this.fliCnumber;
	}

	public void setFliCnumber(short fliCnumber) {
		this.fliCnumber = fliCnumber;
	}

	public short getFliYnumber() {
		return this.fliYnumber;
	}

	public void setFliYnumber(short fliYnumber) {
		this.fliYnumber = fliYnumber;
	}

	public double getFliFfare() {
		return this.fliFfare;
	}

	public void setFliFfare(double fliFfare) {
		this.fliFfare = fliFfare;
	}

	public double getFliCfare() {
		return this.fliCfare;
	}

	public void setFliCfare(double fliCfare) {
		this.fliCfare = fliCfare;
	}

	public double getFliYfare() {
		return this.fliYfare;
	}

	public void setFliYfare(double fliYfare) {
		this.fliYfare = fliYfare;
	}

	public String getFliRefundtime() {
		return this.fliRefundtime;
	}

	public void setFliRefundtime(String fliRefundtime) {
		this.fliRefundtime = fliRefundtime;
	}

	public String getFliRefund() {
		return this.fliRefund;
	}

	public void setFliRefund(String fliRefund) {
		this.fliRefund = fliRefund;
	}

	public byte getFlag() {
		return this.flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getAirCode() {
		return airCode;
	}

	public void setAirCode(String airCode) {
		this.airCode = airCode;
	}

}