package PO;

/**
 * Faretype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Faretype implements java.io.Serializable {

	// Fields

	private String farComcode;
	private Flightcompany flightcompany;
	private Integer farGoldscore;
	private double farGolddiscount;
	private Integer farSilscore;
	private double farSildiscount;
	private Integer farBroscore;
	private double farBrodiscount;

	// Constructors

	/** default constructor */
	public Faretype() {
	}

	/** minimal constructor */
	public Faretype(String farComcode, Flightcompany flightcompany,
			Integer farBroscore) {
		this.farComcode = farComcode;
		this.flightcompany = flightcompany;
		this.farBroscore = farBroscore;
	}

	/** full constructor */
	public Faretype(String farComcode, Flightcompany flightcompany,
			Integer farGoldscore, double farGolddiscount, Integer farSilscore,
			double farSildiscount, Integer farBroscore, double farBrodiscount) {
		this.farComcode = farComcode;
		this.flightcompany = flightcompany;
		this.farGoldscore = farGoldscore;
		this.farGolddiscount = farGolddiscount;
		this.farSilscore = farSilscore;
		this.farSildiscount = farSildiscount;
		this.farBroscore = farBroscore;
		this.farBrodiscount = farBrodiscount;
	}

	// Property accessors

	public String getFarComcode() {
		return this.farComcode;
	}

	public void setFarComcode(String farComcode) {
		this.farComcode = farComcode;
	}

	public Flightcompany getFlightcompany() {
		return this.flightcompany;
	}

	public void setFlightcompany(Flightcompany flightcompany) {
		this.flightcompany = flightcompany;
	}

	public Integer getFarGoldscore() {
		return this.farGoldscore;
	}

	public void setFarGoldscore(Integer farGoldscore) {
		this.farGoldscore = farGoldscore;
	}

	public double getFarGolddiscount() {
		return this.farGolddiscount;
	}

	public void setFarGolddiscount(double farGolddiscount) {
		this.farGolddiscount = farGolddiscount;
	}

	public Integer getFarSilscore() {
		return this.farSilscore;
	}

	public void setFarSilscore(Integer farSilscore) {
		this.farSilscore = farSilscore;
	}

	public double getFarSildiscount() {
		return this.farSildiscount;
	}

	public void setFarSildiscount(double farSildiscount) {
		this.farSildiscount = farSildiscount;
	}

	public Integer getFarBroscore() {
		return this.farBroscore;
	}

	public void setFarBroscore(Integer farBroscore) {
		this.farBroscore = farBroscore;
	}

	public double getFarBrodiscount() {
		return this.farBrodiscount;
	}

	public void setFarBrodiscount(double farBrodiscount) {
		this.farBrodiscount = farBrodiscount;
	}

}