package nl.rsvier.sprint1.dao.test;

import static org.junit.Assert.*;

import java.util.List;

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
		assertEquals("voornaam is not as expected", persoon.getVoornaam(), persoon1.getVoornaam());
		assertEquals("achternaam is not as expected", persoon.getAchternaam(), persoon1.getAchternaam());
		assertEquals("tussenvoegsel is not as expected", persoon.getTussenvoegsel(), persoon1.getTussenvoegsel());
		assertEquals("geboortedatun is not as expected", persoon.getGeboortedatum(), persoon1.getGeboortedatum());
		assertEquals("adres id is not as expected", persoon.getAdres().getId(), persoon1.getAdres().getId());
		
		persoonDao.deletePersoon(persoon);
		adresDao.deleteAdres(adres);
	}
	
	@Test
	public void deletePersoonTest() {
		Persoon persoon = new Persoon();
		persoon.setVoornaam("Anna");
		persoon.setAchternaam("Grotenhuis");
		persoon.setTussenvoegsel("van");
		persoon.setGeboortedatum("12-02-1990");
		
		Adres adres = createAdres();
		persoon.setAdres(adres);
		int persoonId = persoonDao.createPersoon(persoon);
		
		persoonDao.deletePersoon(persoon);
		Persoon persoon1 = persoonDao.readPersoon(persoonId);
		assertNull("persoon has not been deleted from the database",persoon1);
		
		adresDao.deleteAdres(adres);
	}
	
	@Test
	public void getAllPersonenTest() {
		Persoon persoon = new Persoon();
		persoon.setVoornaam("Natascha");
		persoon.setTussenvoegsel("van der");
		persoon.setAchternaam("Leden");
		persoon.setGeboortedatum("01-03-1994");
		
		Adres adres = createAdres();
		persoon.setAdres(adres);
		int persoonId = persoonDao.createPersoon(persoon);
		
		Persoon persoon1 = new Persoon();
		persoon1.setVoornaam("Martijn");
		persoon1.setTussenvoegsel("");
		persoon1.setAchternaam("Vos");
		persoon1.setGeboortedatum("01-03-1990");
		persoon1.setAdres(adres);
		int persoon1Id = persoonDao.createPersoon(persoon1);
		
		Persoon persoon2 = new Persoon();
		persoon2.setVoornaam("Wim");
		persoon2.setTussenvoegsel("");
		persoon2.setAchternaam("Verhuij");
		persoon2.setGeboortedatum("01-03-1980");
		persoon2.setAdres(adres);
		int persoon2Id = persoonDao.createPersoon(persoon2);
		
		Persoon persoon3 = new Persoon();
		persoon3.setVoornaam("Vera");
		persoon3.setTussenvoegsel("");
		persoon3.setAchternaam("Vos");
		persoon3.setGeboortedatum("01-03-1991");
		persoon3.setAdres(adres);
		int persoon3Id = persoonDao.createPersoon(persoon3);
		
		List<Persoon> list = persoonDao.getAllPersonen();
		assertNotNull("list from database is null", list);
		assertEquals("list's size is not as expected", list.size(), 4);
		
		for (Persoon prs: list) {
			int prsId = prs.getId();
			assertTrue("persoon id is not as expected", 
					prsId == persoonId || prsId == persoon1Id || prsId == persoon2Id || prsId == persoon3Id);
		}
		
		persoonDao.deletePersoon(persoon);
		persoonDao.deletePersoon(persoon1);
		persoonDao.deletePersoon(persoon2);
		persoonDao.deletePersoon(persoon3);
		adresDao.deleteAdres(adres);
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
