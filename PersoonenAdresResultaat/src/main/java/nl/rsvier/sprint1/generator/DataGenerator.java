package nl.rsvier.sprint1.generator;

import java.util.ArrayList;
import java.util.List;

import nl.rsvier.sprint1.dao.GenericDAO;
import nl.rsvier.sprint1.dao.ResultaatDAO;
import nl.rsvier.sprint1.dao.impl.RsvierDAOFactory;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;
import nl.rsvier.sprint1.domain.Resultaat;

public class DataGenerator {
	private static final String[] MODULES = {"Basis01", "Basis02", "Basis03", "Basis04", "OCA"};
	private static final String[] WOONPLAATS = {"Amsterdam", "Den Haag", "Rotterdam", "Zwolle", "Groningen",
		                                        "Hilversum", "Maastricht", "Leiden", "Breda", "Gouda"};
	private static final String[] STRAATNAAM = {"Lindelaan", "Douglaslaan", "Alexanderhof","Bunnikstraat",
		                                         "Eeldepad", "Frankenstraat", "Haverkamp", "Wrightlaan", "Plesmanlaan"};
	private static final String[] VOORNAAM = {"Amalia", "Sam", "Bas", "Noah", "Linda",
	                                           "Marijke", "Geert", "Bart", "Georg", "Marja"};
	private static final String[] ACHTERNAAM = {"Jansen", "Bakker", "Visser", "Mulder", "Bos", "Vos", 
	                                             "Peters", "Hendriks", "Dijkstra", "Smits", "Kok"};
	
	public void generateData () {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		factory.openConnection();
		factory.startTransaction();
		
		GenericDAO<Persoon> persoonDao = factory.getPersoonDAO();
		GenericDAO<Adres> adresDao = factory.getAdresDAO();
		ResultaatDAO resultaatDao = factory.getResultaatDAO();
		
		List<Adres> adresList = new ArrayList<>();
		for (int i = 0; i < 2500; i++) {
			Adres adres = new Adres();
			adres.setHuisnummer((int) (Math.random() * 250 + 1));
			adres.setWoonplaats(WOONPLAATS[i % 10]);
			adres.setStraatnaam(STRAATNAAM[i % 9]);
			adres.setPostcode("1234 AS");
			int adresId = adresDao.create(adres);
			adres.setId(adresId);
			
			adresList.add(adres);
		}
		System.out.println("2500 addresses added.");

		List<Persoon> persoonList = new ArrayList<>();
		for (int i = 0; i < 5000; i++) {
			Persoon persoon = new Persoon();
			persoon.setVoornaam(VOORNAAM[i % 10]);
			persoon.setAchternaam(ACHTERNAAM[i % 11]);
			persoon.setGeboortedatum("01-03-1990");
			
			persoon.setAdres(adresList.get(i / 4));
			
			int persoonId = persoonDao.create(persoon);
			persoon.setId(persoonId);
			persoonList.add(persoon);
		}
		System.out.println("5000 persons added.");
		
		for (int i = 0; i < persoonList.size(); i ++) {
			Persoon persoon = persoonList.get(i);
			Resultaat resultaat = new Resultaat();
			resultaat.setModulenaam(MODULES[i % 5]);
			resultaat.setVoldoende(((int) (Math.random()+0.5) == 1 ? true : false));
			if (resultaat.isVoldoende()) {
				resultaat.setResultaat((float)(5 + (Math.random() * 5)));
			}
			else {
				resultaat.setResultaat((float)(Math.random() * 5));
			}
			resultaat.setPersoonId(persoon.getId());
			resultaatDao.create(resultaat);
		}
		System.out.println("Persons are assigned with results.");
		
		factory.confirmChanges();
		factory.closeConnection();

	}
	
	public static void main (String[] args) {
		DataGenerator dg = new DataGenerator();
		dg.generateData();
		
	}
}
