//�༭�ߣ�������
package PO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Flightcompany implements java.io.Serializable {

	// Fields

	private Integer comAutoid;
	private String comCode;
	private String comName;
	private String comAddress;
	private String comRegister;
	private String comInformation;
	private byte flag;
	private Map flightinformations = new HashMap(0);
	private Map bookinformations = new HashMap(0);
	private Map faretypes = new HashMap(0);

	// Constructors

	public Map getFaretypes() {
		return faretypes;
	}

	public void setFaretypes(Map faretypes) {
		this.faretypes = faretypes;
	}

	/** default constructor */
	public Flightcompany() {
	}

	/** minimal constructor */
	public Flightcompany(Integer comAutoid, String comCode, String comName,
			String comAddress, String comRegister, String comInformation,
			byte flag) {
		this.comAutoid = comAutoid;
		this.comCode = comCode;
		this.comName = comName;
		this.comAddress = comAddress;
		this.comRegister = comRegister;
		this.comInformation = comInformation;
		this.flag = flag;
	}

	/** full constructor */
	public Flightcompany(Integer comAutoid, String comCode, String comName,
			String comAddress, String comRegister, String comInformation,
			byte flag, Map flightinformations, Map bookinformations) {
		this.comAutoid = comAutoid;
		this.comCode = comCode;
		this.comName = comName;
		this.comAddress = comAddress;
		this.comRegister = comRegister;
		this.comInformation = comInformation;
		this.flag = flag;
		this.flightinformations = flightinformations;
		this.bookinformations = bookinformations;
	}

	// Property accessors

	public Integer getComAutoid() {
		return this.comAutoid;
	}

	public void setComAutoid(Integer comAutoid) {
		this.comAutoid = comAutoid;
	}

	public String getComCode() {
		return this.comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComAddress() {
		return this.comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	public String getComRegister() {
		return this.comRegister;
	}

	public void setComRegister(String comRegister) {
		this.comRegister = comRegister;
	}

	public String getComInformation() {
		return this.comInformation;
	}

	public void setComInformation(String comInformation) {
		this.comInformation = comInformation;
	}

	public byte getFlag() {
		return this.flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public Map getFlightinformations() {
		return this.flightinformations;
	}

	public void setFlightinformations(Map flightinformations) {
		this.flightinformations = flightinformations;
	}

	public Map getBookinformations() {
		return this.bookinformations;
	}

	public void setBookinformations(Map bookinformations) {
		this.bookinformations = bookinformations;
	}

}