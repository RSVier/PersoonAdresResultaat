package nl.rsvier.sprint1.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import nl.rsvier.sprint1.dao.GenericDAO;
import nl.rsvier.sprint1.dao.ResultaatDAO;
import nl.rsvier.sprint1.dao.impl.RsvierDAOFactory;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;
import nl.rsvier.sprint1.domain.Resultaat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResultaatDAOTest {
	private ResultaatDAO resultaatDao;
	private GenericDAO<Persoon> persoonDao;
	private GenericDAO<Adres> adresDao;

	@Before
	public void setUp() throws Exception {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		factory.openConnection();
		resultaatDao = factory.getResultaatDAO();
		persoonDao = factory.getPersoonDAO();
		adresDao = factory.getAdresDAO();
	}

	@After
	public void tearDown() throws Exception {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		factory.closeConnection();
	}

	@Test
	public void createAndReadResultaatTest() {
		Resultaat resultaat = new Resultaat();
		resultaat.setModulenaam("mod1");
		resultaat.setResultaat(7.8f);
		resultaat.setVoldoende(true);
		
		Persoon persoon = createPersoon();
		int persoonId = persoon.getId();
		resultaat.setPersoonId(persoonId);
		int resultaatId = resultaatDao.create(resultaat);
		
		Resultaat resultaat1 = resultaatDao.read(resultaatId);
		assertNotNull("resultaat from database is not null", resultaat1);
		assertEquals("modulenaam from database is not as expected", resultaat.getModulenaam(), resultaat1.getModulenaam());
		assertEquals("resultaat from database is not as expacted", resultaat.getResultaat(), resultaat1.getResultaat(), 0.01);
		assertEquals("persoon's id is not as expected", resultaat.getPersoonId(), resultaat1.getPersoonId());
		
		resultaatDao.delete(resultaat);
		persoonDao.delete(persoon);
	}
	
	@Test
	public void updateResultaatTest() {
		Resultaat resultaat = new Resultaat();
		resultaat.setModulenaam("mod1");
		resultaat.setResultaat(7.8f);
		resultaat.setVoldoende(true);
		
		Persoon persoon = createPersoon();
		int persoonId = persoon.getId();
		resultaat.setPersoonId(persoonId);
		int resultaatId = resultaatDao.create(resultaat);
		
		resultaat.setModulenaam("mod2");
		resultaat.setResultaat(4.8f);
		resultaat.setVoldoende(false);
		
		resultaatDao.update(resultaat);
		
		Resultaat resultaat1 = resultaatDao.read(resultaatId);
		assertNotNull("resultaat from database is not null", resultaat1);
		assertEquals("modulenaam from database is not as expected", resultaat.getModulenaam(), resultaat1.getModulenaam());
		assertEquals("resultaat from database is not as expacted", resultaat.getResultaat(), resultaat1.getResultaat(), 0.01);
		assertEquals("persoon's id is not as expected", resultaat.getPersoonId(), resultaat1.getPersoonId());
		
		resultaatDao.delete(resultaat);
		persoonDao.delete(persoon);
	}
	
	@Test
	public void deleteResultaatTest() {
		Resultaat resultaat = new Resultaat();
		resultaat.setModulenaam("mod1");
		resultaat.setResultaat(7.8f);
		resultaat.setVoldoende(true);
		
		Persoon persoon = createPersoon();
		int persoonId = persoon.getId();
		resultaat.setPersoonId(persoonId);
		int resultaatId = resultaatDao.create(resultaat);
		
		resultaatDao.delete(resultaat);
		Resultaat resultaat1 = resultaatDao.read(resultaatId);
		assertNull("resultaat has not been deleted fro database", resultaat1);
		
		persoonDao.delete(persoon);
	}
	
	@Test
	public void getAllResultatenTest() {
		Resultaat resultaat = new Resultaat();
		resultaat.setModulenaam("mod1");
		resultaat.setResultaat(7.8f);
		resultaat.setVoldoende(true);
		
		Persoon persoon = createPersoon();
		int persoonId = persoon.getId();
		resultaat.setPersoonId(persoonId);
		int resultaatId = resultaatDao.create(resultaat);
		
		Resultaat resultaat1 = new Resultaat();
		resultaat1.setModulenaam("mod2");
		resultaat1.setResultaat(7.0f);
		resultaat1.setVoldoende(true);
		resultaat1.setPersoonId(persoonId);
		int resultaat1Id = resultaatDao.create(resultaat1);
		
		Resultaat resultaat2 = new Resultaat();
		resultaat2.setModulenaam("mod3");
		resultaat2.setResultaat(4.0f);
		resultaat2.setVoldoende(false);
		resultaat2.setPersoonId(persoonId);
		int resultaat2Id = resultaatDao.create(resultaat2);
		
		List<Resultaat> list = resultaatDao.getAll();
		assertNotNull("list from database is null", list);
		assertEquals("list's size is not as expected", list.size(), 3);
		
		for (Resultaat rst: list) {
			int rstId = rst.getId();
			assertTrue("resultaat is not as expected", 
						rstId == resultaatId || rstId == resultaat1Id || rstId == resultaat2Id);
		}
		
		resultaatDao.delete(resultaat);
		resultaatDao.delete(resultaat1);
		resultaatDao.delete(resultaat2);
		
		persoonDao.delete(persoon);
	}
	
	@Test
	public void getAllResultatenByIdTest() {
		Resultaat resultaat = new Resultaat();
		resultaat.setModulenaam("mod1");
		resultaat.setResultaat(7.8f);
		resultaat.setVoldoende(true);
		
		Persoon persoon = createPersoon();
		int persoonId = persoon.getId();
		resultaat.setPersoonId(persoonId);
		int resultaatId = resultaatDao.create(resultaat);
		
		Resultaat resultaat1 = new Resultaat();
		resultaat1.setModulenaam("mod2");
		resultaat1.setResultaat(7.0f);
		resultaat1.setVoldoende(true);
		resultaat1.setPersoonId(persoonId);
		int resultaat1Id = resultaatDao.create(resultaat1);
		
		Resultaat resultaat2 = new Resultaat();
		resultaat2.setModulenaam("mod1");
		resultaat2.setResultaat(5.8f);
		resultaat2.setVoldoende(true);
		
		Persoon persoon1 = createPersoon();
		int persoon1Id = persoon1.getId();
		resultaat2.setPersoonId(persoon1Id);
		int resultaat2Id = resultaatDao.create(resultaat2);
		
		List<Resultaat> list = resultaatDao.getAll(persoonId);
		assertNotNull("list from database is null", list);
		assertEquals("list's size is not as expected", list.size(), 2);
		for (Resultaat rst: list) {
			int rstId = rst.getId();
			assertTrue("resultaat from database is not as expected", rstId == resultaatId || rstId == resultaat1Id);
		}
		
		List<Resultaat> list2 = resultaatDao.getAll(persoon1Id);
		assertNotNull("list from database is null", list2);
		assertEquals("list's size is not as expected", list2.size(), 1);
		for (Resultaat rst: list2) {
			int rstId = rst.getId();
			assertTrue("resultaat from database is not as expected", rstId == resultaat2Id);
		}
		
		resultaatDao.delete(resultaat);
		resultaatDao.delete(resultaat1);
		resultaatDao.delete(resultaat2);
		persoonDao.delete(persoon);
		persoonDao.delete(persoon1);

	}
	
	private Persoon createPersoon() {
		Persoon persoon = new Persoon();
		persoon.setVoornaam("Julius");
		persoon.setTussenvoegsel("");
		persoon.setAchternaam("Granger");
		persoon.setGeboortedatum("12-12-1989");
		
		Adres adres = new Adres();
		adres.setWoonplaats("Rijswijk");
		adres.setStraatnaam("Lindelaan");
		adres.setHuisnummer(9);
		adres.setToevoeging("A");
		adres.setPostcode("2497 CN");
		adresDao.create(adres);
		
		persoon.setAdres(adres);
		persoonDao.create(persoon);

		return persoon;
	}

}
