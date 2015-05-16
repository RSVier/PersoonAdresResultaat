package nl.rsvier.sprint1.dao.test;

import static org.junit.Assert.*;
import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.dao.PersoonDAO;
import nl.rsvier.sprint1.dao.impl.RsvierDAOFactory;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersoonDAOTest {
	private PersoonDAO persoonDao;
	private AdresDAO adresDao;

	@Before
	public void setUp() throws Exception {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		factory.openConnection();
		persoonDao = factory.getPersoonDAO();
		adresDao = factory.getAdresDAO();
	}

	@After
	public void tearDown() throws Exception {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		factory.closeConnection();
	}

	@Test
	public void createAndReadPersoonTest() {
		Persoon persoon = new Persoon();
		persoon.setVoornaam("Anna");
		persoon.setAchternaam("Grotenhuis");
		persoon.setTussenvoegsel("van");
		persoon.setGeboortedatum("12-02-1990");
		
		Adres adres = createAdres();
		persoon.setAdres(adres);
		int persoonId = persoonDao.createPersoon(persoon);
		
		Persoon persoon1 = persoonDao.readPersoon(persoonId);
		
		assertNotNull("persoon from database is null", persoon1);
		
		Adres adres1 = persoon1.getAdres();
		assertNotNull("adres from database is null", adres1);
		assertEquals("adres id is not as expected", adres.getId(), adres1.getId());
		
		assertEquals("voornaam is not as expected", persoon.getVoornaam(), persoon1.getVoornaam());
		assertEquals("achternaam is not as expected", persoon.getAchternaam(), persoon1.getAchternaam());
		assertEquals("toessenvoegsel is not as expected", persoon.getTussenvoegsel(), persoon1.getTussenvoegsel());
		assertEquals("geboortedatum is not as expected", persoon.getGeboortedatum(), persoon1.getGeboortedatum());
		
		persoonDao.deletePersoon(persoon);
		adresDao.deleteAdres(adres);
	}
	
	@Test
	public void updatePersoonTest() {
		Persoon persoon = new Persoon();
		persoon.setVoornaam("Anna");
		persoon.setAchternaam("Grotenhuis");
		persoon.setTussenvoegsel("van");
		persoon.setGeboortedatum("12-02-1990");
		
		Adres adres = createAdres();
		persoon.setAdres(adres);
		int persoonId = persoonDao.createPersoon(persoon);
		
		persoon.setVoornaam("Hanna");
		persoon.setAchternaam("Postma");
		persoon.setTussenvoegsel("");
		persoon.setGeboortedatum("12-02-1991");
		Adres adres1 = createAdres();
		persoon.setAdres(adres1);
		
		persoonDao.updatePersoon(persoon);
		
		Persoon persoon1 = persoonDao.readPersoon(persoonId);
		assertNotNull("persoon from database is null", persoon1);
		
	}
	
	private Adres createAdres() {
		Adres adres = new Adres();
		adres.setWoonplaats("Rijswijk");
		adres.setStraatnaam("Lindelaan");
		adres.setHuisnummer(9);
		adres.setToevoeging("A");
		adres.setPostcode("2497 CN");
		adresDao.createAdres(adres);
		return adres;
	}

}
