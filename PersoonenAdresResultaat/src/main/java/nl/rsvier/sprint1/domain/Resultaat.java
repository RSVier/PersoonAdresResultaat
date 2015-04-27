package nl.rsvier.sprint1.domain;

public class Resultaat {
	private int id;
	
	private String modulenaam;
	
	private float resultaat;
	
	private boolean voldoende;
	
	private int persoonId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModulenaam() {
		return modulenaam;
	}

	public void setModulenaam(String modulenaam) {
		this.modulenaam = modulenaam;
	}

	public float getResultaat() {
		return resultaat;
	}

	public void setResultaat(float resultaat) {
		this.resultaat = resultaat;
	}

	public boolean isVoldoende() {
		return voldoende;
	}

	public void setVoldoende(boolean voldoende) {
		this.voldoende = voldoende;
	}

	public int getPersoonId() {
		return persoonId;
	}

	public void setPersoonId(int persoonId) {
		this.persoonId = persoonId;
	}

}
