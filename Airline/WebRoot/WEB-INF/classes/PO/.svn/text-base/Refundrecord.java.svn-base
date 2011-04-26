//编辑者：周永丰
package PO;

public class Refundrecord implements java.io.Serializable {

	// Fields

	private long refAutoid;
	private Bookinformation bookinformation;
	//添加属性
	private long booAutoid;
	private short booNumber;

	// Constructors

	/** default constructor */
	public Refundrecord() {
	}

	/** minimal constructor */
	public Refundrecord(long refAutoid) {
		this.refAutoid = refAutoid;
	}

	/** full constructor */
	public Refundrecord(long refAutoid, Bookinformation bookinformation,
			long booAutoid,short booNumber) {
		this.refAutoid = refAutoid;
		this.bookinformation = bookinformation;
		this.booAutoid=booAutoid;
		this.booNumber = booNumber;
	}

	// Property accessors

	public long getRefAutoid() {
		return this.refAutoid;
	}

	public void setRefAutoid(long refAutoid) {
		this.refAutoid = refAutoid;
	}

	public Bookinformation getBookinformation() {
		return this.bookinformation;
	}

	public void setBookinformation(Bookinformation bookinformation) {
		this.bookinformation = bookinformation;
	}

	public short getBooNumber() {
		return this.booNumber;
	}

	public void setBooNumber(short booNumber) {
		this.booNumber = booNumber;
	}

	public long getBooAutoid() {
		return booAutoid;
	}

	public void setBooAutoid(long booAutoid) {
		this.booAutoid = booAutoid;
	}

}