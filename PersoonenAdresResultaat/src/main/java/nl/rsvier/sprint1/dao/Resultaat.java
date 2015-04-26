package nl.rsvier.sprint1.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table 
public class Resultaat  implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String modulenaam;
	
	private float resultaat;
	
	private boolean voldoende;

	@ManyToOne
	private Persoon persoon;
	
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

	public Persoon getPersoon() {
		return persoon;
	}

	public void setPersoon(Persoon persoon) {
		this.persoon = persoon;
	}
}
