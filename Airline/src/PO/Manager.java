//±‡º≠’ﬂ£∫÷‹”¿∑·
package PO;

import java.util.Date;

public class Manager implements java.io.Serializable {

	// Fields

	private Integer manAutoid;
	private String manAccount;
	private String manPwd;
	private long manId;
	private String manSex;
	private String manTelnumber;
	private String manEmail;
	private String manRegister;
	private byte flag;

	// Constructors

	/** default constructor */
	public Manager() {
	}

	/** minimal constructor */
	public Manager(Integer manAutoid, String manAccount, String manPwd,
			long manId, String manSex, String manTelnumber, String manEmail,
			String manRegister) {
		this.manAutoid = manAutoid;
		this.manAccount = manAccount;
		this.manPwd = manPwd;
		this.manId = manId;
		this.manSex = manSex;
		this.manTelnumber = manTelnumber;
		this.manEmail = manEmail;
		this.manRegister = manRegister;
	}

	/** full constructor */
	public Manager(Integer manAutoid, String manAccount, String manPwd,
			long manId, String manSex, String manTelnumber, String manEmail,
			String manRegister, byte flag) {
		this.manAutoid = manAutoid;
		this.manAccount = manAccount;
		this.manPwd = manPwd;
		this.manId = manId;
		this.manSex = manSex;
		this.manTelnumber = manTelnumber;
		this.manEmail = manEmail;
		this.manRegister = manRegister;
		this.flag = flag;
	}

	// Property accessors

	public Integer getManAutoid() {
		return this.manAutoid;
	}

	public void setManAutoid(Integer manAutoid) {
		this.manAutoid = manAutoid;
	}

	public String getManAccount() {
		return this.manAccount;
	}

	public void setManAccount(String manAccount) {
		this.manAccount = manAccount;
	}

	public String getManPwd() {
		return this.manPwd;
	}

	public void setManPwd(String manPwd) {
		this.manPwd = manPwd;
	}

	public long getManId() {
		return this.manId;
	}

	public void setManId(long manId) {
		this.manId = manId;
	}

	public String getManSex() {
		return this.manSex;
	}

	public void setManSex(String manSex) {
		this.manSex = manSex;
	}

	public String getManTelnumber() {
		return this.manTelnumber;
	}

	public void setManTelnumber(String manTelnumber) {
		this.manTelnumber = manTelnumber;
	}

	public String getManEmail() {
		return this.manEmail;
	}

	public void setManEmail(String manEmail) {
		this.manEmail = manEmail;
	}

	public String getManRegister() {
		return this.manRegister;
	}

	public void setManRegister(String manRegister) {
		this.manRegister = manRegister;
	}

	public byte getFlag() {
		return this.flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

}