package nl.rsvier.sprint1.dao;

public class Adres {
	private int id;
	
	private String straatnaam;
	
	private int huisnumer;
	
	private String toevoeging;
	
	private String postcode;
	
	private String woonplaats;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStraatnaam() {
		return straatnaam;
	}

	public void setStraatnaam(String straatnaam) {
		this.straatnaam = straatnaam;
	}

	public int getHuisnumer() {
		return huisnumer;
	}

	public void setHuisnumer(int huisnumer) {
		this.huisnumer = huisnumer;
	}

	public String getToevoeging() {
		return toevoeging;
	}

	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}
}
