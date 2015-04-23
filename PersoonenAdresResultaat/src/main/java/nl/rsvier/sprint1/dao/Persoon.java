package nl.rsvier.sprint1.dao;

public class Persoon {
	private int id;
	
	private String voornaam;
	
	private String achternaam;
	
	private String tussenvoegsel;
	
	private String geboortedatum;
	
	private Adres adres;
	
	private Resultaat[] resultaten;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	public String getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(String geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public Resultaat[] getResultaten() {
		return resultaten;
	}

	public void setResultaten(Resultaat[] resultaten) {
		this.resultaten = resultaten;
	}
}
