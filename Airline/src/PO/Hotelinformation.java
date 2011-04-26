package PO;

/**
 * Hotelinformation entity. @author MyEclipse Persistence Tools
 */
public class Hotelinformation extends AbstractHotelinformation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Hotelinformation() {
	}

	/** minimal constructor */
	public Hotelinformation(String hotelName, Integer hotelLevel,
			String hotelCity, String hotelAddress, Integer flag) {
		super(hotelName, hotelLevel, hotelCity, hotelAddress, flag);
	}

	/** full constructor */
	public Hotelinformation(String hotelName, Integer hotelLevel,
			String hotelTel, String hotelCity, String hotelAddress,
			String hotelPage, String hotelPicture, Integer flag) {
		super(hotelName, hotelLevel, hotelTel, hotelCity, hotelAddress,
				hotelPage, hotelPicture, flag);
	}

}
