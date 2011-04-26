//±‡º≠’ﬂ£∫÷‹”¿∑·
package PO;

public class Bank implements java.io.Serializable {

	// Fields

	private long banAccount;
	private long banPwd;
	private double banMoney;

	// Constructors

	/** default constructor */
	public Bank() {
	}

	/** full constructor */
	public Bank(long banAccount, long banPwd, double banMoney) {
		this.banAccount = banAccount;
		this.banPwd = banPwd;
		this.banMoney = banMoney;
	}

	// Property accessors

	public long getBanAccount() {
		return this.banAccount;
	}

	public void setBanAccount(long banAccount) {
		this.banAccount = banAccount;
	}

	public long getBanPwd() {
		return this.banPwd;
	}

	public void setBanPwd(long banPwd) {
		this.banPwd = banPwd;
	}

	public double getBanMoney() {
		return this.banMoney;
	}

	public void setBanMoney(double banMoney) {
		this.banMoney = banMoney;
	}

}