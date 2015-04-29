package nl.rsvier.sprint1.generator;

import java.util.ArrayList;
import java.util.List;

import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.dao.PersoonDAO;
import nl.rsvier.sprint1.dao.ResultaatDAO;
import nl.rsvier.sprint1.dao.impl.RsvierDAOFactory;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;
import nl.rsvier.sprint1.domain.Resultaat;

public class DataGenerator {
	private static final String[] MODULES = {"Module1", "Module2", "Module3", "Module4", "Module5"};
	
	public void generateData () {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		factory.openConnection();
		
		PersoonDAO persoonDao = factory.getPersoonDAO();
		AdresDAO adresDao = factory.getAdresDAO();
		ResultaatDAO resultaatDao = factory.getResultaatDAO();
		
		List<Persoon> persoonList = new ArrayList<>();
		for (int i = 0; i < 5000; i++) {
			Persoon persoon = new Persoon();
			persoon.setId(i + 1);
			persoon.setVoornaam("Voornaam" + (i + 1));
			persoon.setAchternaam("Achternaam" + (i + 1));
			persoon.setGeboortedatum("01-03-1990");
			persoonDao.createPersoon(persoon);
			persoonList.add(persoon);
		}
		System.out.println("5000 persons added.");
		
		List<Adres> adresList = new ArrayList<>();
		for (int i = 0; i < 2500; i++) {
			Adres adres = new Adres();
			adres.setId(i + 1);
			adres.setHuisnummer((int) (Math.random() * 250 + 1));
			adres.setWoonplaats("Woonplaats" + (i + 1));
			adres.setStraatnaam("Straatnaam" + (i + 1));
			adres.setPostcode("1234 AS");
			adresDao.createAdres(adres);
			adresList.add(adres);
		}
		System.out.println("2500 addresses added.");

		
		for (int i = 0; i < persoonList.size(); i++) {
			Persoon persoon = persoonList.get(i);
			Adres adres = adresList.get(i / 4);
			persoon.setAdres(adres);
			persoonDao.updatePersoon(persoon);
		}
		System.out.println("Persons are assigned with addresses.");

		
		for (int i = 0; i < persoonList.size(); i ++) {
			Persoon persoon = persoonList.get(i);
			Resultaat resultaat = new Resultaat();
			resultaat.setId(i + 1);
			resultaat.setModulenaam(MODULES[i % 5]);
			resultaat.setResultaat((float) (Math.random() * 10 + 1));
			resultaat.setVoldoende(((int) (Math.random()+0.5) == 1 ? true : false));
			resultaat.setPersoonId(persoon.getId());
			resultaatDao.createResultaat(resultaat);
		}
		System.out.println("Persons are assigned with results.");

	}
	
	public static void main (String[] args) {
		DataGenerator dg = new DataGenerator();
		dg.generateData();
		
	}
}
