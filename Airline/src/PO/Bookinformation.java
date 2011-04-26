//�༭�ߣ�������
package PO;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Bookinformation implements java.io.Serializable {

	// Fields

	private long booAutoid;
	private Flightcompany flightcompany;
	private String comCode;
	private String cusId;
	private String booEveryday;
	private String booNo;
	private String booBaddress;
	private String booAaddress;
	private Calendar booBtime;
	private Calendar booAtime;
	private String booBerth;
	private short booNumber;
	private double booFare;
	private Calendar booTime;
	private byte flagPay;
	private byte flagType;
	private byte flagPass;
	private Map refundrecords = new HashMap(0);

	// Constructors

	/** default constructor */
	public Bookinformation() {
	}

	/** minimal constructor */
	/*
	 * 2011-04-10 叶茂安 String--->Calendar
	 */
	public Bookinformation(long booAutoid, Flightcompany flightcompany,String comCode,
			String cusId, String booEveryday, String booNo, String booBaddress,
			String booAaddress, Calendar booBtime, Calendar booAtime, String booBerth,
			short booNumber, double booFare, Calendar booTime, byte flagPay,
			byte flagType, byte flagPass) {
		this.booAutoid = booAutoid;
		this.flightcompany = flightcompany;
		this.comCode=comCode;
		this.cusId = cusId;
		this.booEveryday = booEveryday;
		this.booNo = booNo;
		this.booBaddress = booBaddress;
		this.booAaddress = booAaddress;
		this.booBtime = booBtime;
		this.booAtime = booAtime;
		this.booBerth = booBerth;
		this.booNumber = booNumber;
		this.booFare = booFare;
		this.booTime = booTime;
		this.flagPay = flagPay;
		this.flagType = flagType;
		this.flagPass = flagPass;
	}

	/** full constructor */
	public Bookinformation(long booAutoid, Flightcompany flightcompany,
			String cusId, String booEveryday, String booNo, String booBaddress,
			String booAaddress, Calendar booBtime, Calendar booAtime, String booBerth,
			short booNumber, double booFare, Calendar booTime, byte flagPay,
			byte flagType, byte flagPass, Map refundrecords) {
		this.booAutoid = booAutoid;
		this.flightcompany = flightcompany;
		this.cusId = cusId;
		this.booEveryday = booEveryday;
		this.booNo = booNo;
		this.booBaddress = booBaddress;
		this.booAaddress = booAaddress;
		this.booBtime = booBtime;
		this.booAtime = booAtime;
		this.booBerth = booBerth;
		this.booNumber = booNumber;
		this.booFare = booFare;
		this.booTime = booTime;
		this.flagPay = flagPay;
		this.flagType = flagType;
		this.flagPass = flagPass;
		this.refundrecords = refundrecords;
	}

	// Property accessors

	public long getBooAutoid() {
		return this.booAutoid;
	}

	public void setBooAutoid(long booAutoid) {
		this.booAutoid = booAutoid;
	}

	public Flightcompany getFlightcompany() {
		return this.flightcompany;
	}

	public void setFlightcompany(Flightcompany flightcompany) {
		this.flightcompany = flightcompany;
	}

	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getBooEveryday() {
		return this.booEveryday;
	}

	public void setBooEveryday(String booEveryday) {
		this.booEveryday = booEveryday;
	}

	public String getBooNo() {
		return this.booNo;
	}

	public void setBooNo(String booNo) {
		this.booNo = booNo;
	}

	public String getBooBaddress() {
		return this.booBaddress;
	}

	public void setBooBaddress(String booBaddress) {
		this.booBaddress = booBaddress;
	}

	public String getBooAaddress() {
		return this.booAaddress;
	}

	public void setBooAaddress(String booAaddress) {
		this.booAaddress = booAaddress;
	}

	public Calendar getBooBtime() {
		return this.booBtime;
	}

	public void setBooBtime(Calendar booBtime) {
		this.booBtime = booBtime;
	}

	public Calendar getBooAtime() {
		return this.booAtime;
	}

	public void setBooAtime(Calendar booAtime) {
		this.booAtime = booAtime;
	}

	public String getBooBerth() {
		return this.booBerth;
	}

	public void setBooBerth(String booBerth) {
		this.booBerth = booBerth;
	}

	public short getBooNumber() {
		return this.booNumber;
	}

	public void setBooNumber(short booNumber) {
		this.booNumber = booNumber;
	}

	public double getBooFare() {
		return this.booFare;
	}

	public void setBooFare(double booFare) {
		this.booFare = booFare;
	}

	public Calendar getBooTime() {
		return this.booTime;
	}

	public void setBooTime(Calendar booTime) {
		this.booTime = booTime;
	}

	public byte getFlagPay() {
		return this.flagPay;
	}

	public void setFlagPay(byte flagPay) {
		this.flagPay = flagPay;
	}

	public byte getFlagType() {
		return this.flagType;
	}

	public void setFlagType(byte flagType) {
		this.flagType = flagType;
	}

	public byte getFlagPass() {
		return this.flagPass;
	}

	public void setFlagPass(byte flagPass) {
		this.flagPass = flagPass;
	}

	public Map getRefundrecords() {
		return this.refundrecords;
	}

	public void setRefundrecords(Map refundrecords) {
		this.refundrecords = refundrecords;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

}