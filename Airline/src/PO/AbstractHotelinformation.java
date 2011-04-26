package PO;

/**
 * AbstractHotelinformation entity provides the base persistence definition of
 * the Hotelinformation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractHotelinformation implements java.io.Serializable {

	// Fields

	private Integer hotelAutoid;
	private String hotelName;
	private Integer hotelLevel;
	private String hotelTel;
	private String hotelCity;
	private String hotelAddress;
	private String hotelPage;
	private String hotelPicture;
	private Integer flag;

	// Constructors

	/** default constructor */
	public AbstractHotelinformation() {
	}

	/** minimal constructor */
	public AbstractHotelinformation(String hotelName, Integer hotelLevel,
			String hotelCity, String hotelAddress, Integer flag) {
		this.hotelName = hotelName;
		this.hotelLevel = hotelLevel;
		this.hotelCity = hotelCity;
		this.hotelAddress = hotelAddress;
		this.flag = flag;
	}

	/** full constructor */
	public AbstractHotelinformation(String hotelName, Integer hotelLevel,
			String hotelTel, String hotelCity, String hotelAddress,
			String hotelPage, String hotelPicture, Integer flag) {
		this.hotelName = hotelName;
		this.hotelLevel = hotelLevel;
		this.hotelTel = hotelTel;
		this.hotelCity = hotelCity;
		this.hotelAddress = hotelAddress;
		this.hotelPage = hotelPage;
		this.hotelPicture = hotelPicture;
		this.flag = flag;
	}

	// Property accessors

	public Integer getHotelAutoid() {
		return this.hotelAutoid;
	}

	public void setHotelAutoid(Integer hotelAutoid) {
		this.hotelAutoid = hotelAutoid;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getHotelLevel() {
		return this.hotelLevel;
	}

	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public String getHotelTel() {
		return this.hotelTel;
	}

	public void setHotelTel(String hotelTel) {
		this.hotelTel = hotelTel;
	}

	public String getHotelCity() {
		return this.hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelAddress() {
		return this.hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getHotelPage() {
		return this.hotelPage;
	}

	public void setHotelPage(String hotelPage) {
		this.hotelPage = hotelPage;
	}

	public String getHotelPicture() {
		return this.hotelPicture;
	}

	public void setHotelPicture(String hotelPicture) {
		this.hotelPicture = hotelPicture;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}