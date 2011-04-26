package PO;

import java.util.Date;

/**
 * Membership entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Membership implements java.io.Serializable {

	// Fields

	private Integer commentAutoid;
	private long customerId;
	private String customerName;
	private String commentContent;
	private String commentIp;
	private Integer flag;
	private Date commentTime;

	// Constructors

	/** default constructor */
	public Membership() {
	}

	/** minimal constructor */
	public Membership(Integer commentAutoid, long customerId,
			String customerName, String commentContent, Date commentTime) {
		this.commentAutoid = commentAutoid;
		this.customerId = customerId;
		this.customerName = customerName;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
	}

	/** full constructor */
	public Membership(Integer commentAutoid, long customerId,
			String customerName, String commentContent, String commentIp,
			Integer flag, Date commentTime) {
		this.commentAutoid = commentAutoid;
		this.customerId = customerId;
		this.customerName = customerName;
		this.commentContent = commentContent;
		this.commentIp = commentIp;
		this.flag = flag;
		this.commentTime = commentTime;
	}

	// Property accessors

	public Integer getCommentAutoid() {
		return this.commentAutoid;
	}

	public void setCommentAutoid(Integer commentAutoid) {
		this.commentAutoid = commentAutoid;
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentIp() {
		return this.commentIp;
	}

	public void setCommentIp(String commentIp) {
		this.commentIp = commentIp;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

}