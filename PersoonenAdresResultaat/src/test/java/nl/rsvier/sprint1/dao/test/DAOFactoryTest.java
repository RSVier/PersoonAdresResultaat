package nl.rsvier.sprint1.dao.test;

import java.util.List;

import nl.rsvier.sprint1.dao.Adres;
import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.dao.Persoon;
import nl.rsvier.sprint1.dao.PersoonDAO;
import nl.rsvier.sprint1.dao.Resultaat;
import nl.rsvier.sprint1.dao.ResultaatDAO;
import nl.rsvier.sprint1.dao.impl.RsvierDAOFactory;

public class DAOFactoryTest {
	
	public void testResultaat() {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		PersoonDAO persoonDao = factory.getPersoonDAO();
		AdresDAO adresDao = factory.getAdresDAO();
		ResultaatDAO resultaatDao = factory.getResultaatDAO();
		
		factory.startTransaction();
		
		Persoon newPersoon = new Persoon();
		newPersoon.setAchternaam("Boer");
		newPersoon.setVoornaam("Nick");
		newPersoon.setTussenvoegsel("de");
		newPersoon.setGeboortedatum("11-10-1990");
		
		Adres adres1 = new Adres();
		adres1.setWoonplaats("Gouda");
		adres1.setStraatnaam("DouglasLaan");
		adres1.setHuisnummer(10);
		adres1.setPostcode("1212 CC");
		adres1.setToevoeging("b");
		
		adresDao.addAdres(adres1);
		newPersoon.setAdres(adres1);
		persoonDao.addPersoon(newPersoon);
		
		Resultaat resultaat1 = new Resultaat();
		resultaat1.setModulenaam("module1");
		resultaat1.setResultaat(6.4f);
		resultaat1.setVoldoende(true);
		resultaat1.setPersoon(newPersoon);
		resultaatDao.addResultaat(resultaat1);
		
		factory.confirmChanges();
	}
	
	public void testPersoon() {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		PersoonDAO persoonDao = factory.getPersoonDAO();
		AdresDAO adresDao = factory.getAdresDAO();
		
		Persoon newPersoon = new Persoon();
		newPersoon.setAchternaam("Smith");
		newPersoon.setVoornaam("Noah");
		newPersoon.setTussenvoegsel("van der");
		newPersoon.setGeboortedatum("13-12-2000");
		
		Adres adres1 = new Adres();
		adres1.setWoonplaats("Amsterdam");
		adres1.setStraatnaam("Plesmanlaan");
		adres1.setHuisnummer(11);
		adres1.setPostcode("3312 CN");
		adres1.setToevoeging("a");
		
		factory.startTransaction();
		adresDao.addAdres(adres1);
		newPersoon.setAdres(adres1);
		persoonDao.addPersoon(newPersoon);
		factory.confirmChanges();

	}
	
	public void testAdres() {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();

		AdresDAO adresDao = factory.getAdresDAO();

		// add adres
		
		Adres newAdres = new Adres();
		newAdres.setWoonplaats("Rotterdam");
		newAdres.setStraatnaam("Rivier");
		newAdres.setHuisnummer(17);
		newAdres.setPostcode("3452 DM");
		newAdres.setToevoeging("c");
		
		factory.startTransaction();
		adresDao.addAdres(newAdres);
		factory.confirmChanges();
		
		System.out.println("ID of newly creatde Adres:" + newAdres.getId());
		
		// delete adres
		factory.startTransaction();
		adresDao.deleteAdres(newAdres);
		factory.confirmChanges();
		
		// get adres
		Adres adres = adresDao.getAdres(123);
		System.out.println(adres.getPostcode());
		
		// get all adressen
		List<Adres> adressen = adresDao.getAllAdressen();
		System.out.println("Adressen: " + adressen.size());
		Adres adr = adressen.get(0);
		
		System.out.println("Straat: " + adr.getStraatnaam());

		
		PersoonDAO persoonDao = factory.getPersoonDAO();
		Persoon persoon = new Persoon();
		persoonDao.addPersoon(persoon);
	}
	
	public static void main(String[] args) {
		DAOFactoryTest test = new DAOFactoryTest();
		test.testResultaat();
	}
}
