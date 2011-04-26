//编辑者：周永丰
package PO;

import java.util.Date;

public class Customer implements java.io.Serializable {

	// Fields

	private long cusAutoid;
	private Faretype faretype;
	private String cusAccount;
	private String cusPwd;
	private long cusId;
	private String cusSex;
	private String cusTelnumber;
	private String cusEmail;
	private String cusTime;
	private Integer cusIntegral;
	//添加farId属性
	private short farId;
	private byte flag;

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** full constructor */
	public Customer(long cusAutoid, Faretype faretype, String cusAccount,
			String cusPwd, long cusId, String cusSex, String cusTelnumber,
			String cusEmail, String cusTime, Integer cusIntegral,short farId, byte flag) {
		this.cusAutoid = cusAutoid;
		this.faretype = faretype;
		this.cusAccount = cusAccount;
		this.cusPwd = cusPwd;
		this.cusId = cusId;
		this.cusSex = cusSex;
		this.cusTelnumber = cusTelnumber;
		this.cusEmail = cusEmail;
		this.cusTime = cusTime;
		this.cusIntegral = cusIntegral;
		this.farId=farId;
		this.flag = flag;
	}

	// Property accessors

	public long getCusAutoid() {
		return this.cusAutoid;
	}

	public void setCusAutoid(long cusAutoid) {
		this.cusAutoid = cusAutoid;
	}

	public Faretype getFaretype() {
		return this.faretype;
	}

	public void setFaretype(Faretype faretype) {
		this.faretype = faretype;
	}

	public String getCusAccount() {
		return this.cusAccount;
	}

	public void setCusAccount(String cusAccount) {
		this.cusAccount = cusAccount;
	}

	public String getCusPwd() {
		return this.cusPwd;
	}

	public void setCusPwd(String cusPwd) {
		this.cusPwd = cusPwd;
	}

	public long getCusId() {
		return this.cusId;
	}

	public void setCusId(long cusId) {
		this.cusId = cusId;
	}

	public String getCusSex() {
		return this.cusSex;
	}

	public void setCusSex(String cusSex) {
		this.cusSex = cusSex;
	}

	public String getCusTelnumber() {
		return this.cusTelnumber;
	}

	public void setCusTelnumber(String cusTelnumber) {
		this.cusTelnumber = cusTelnumber;
	}

	public String getCusEmail() {
		return this.cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusTime() {
		return this.cusTime;
	}

	public void setCusTime(String cusTime) {
		this.cusTime = cusTime;
	}

	public Integer getCusIntegral() {
		return this.cusIntegral;
	}

	public void setCusIntegral(Integer cusIntegral) {
		this.cusIntegral = cusIntegral;
	}

	public byte getFlag() {
		return this.flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public short getFarId() {
		return farId;
	}

	public void setFarId(short farId) {
		this.farId = farId;
	}

}