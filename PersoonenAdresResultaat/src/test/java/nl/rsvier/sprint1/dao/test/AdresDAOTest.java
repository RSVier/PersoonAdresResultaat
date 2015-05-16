package nl.rsvier.sprint1.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.dao.impl.RsvierDAOFactory;
import nl.rsvier.sprint1.domain.Adres;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdresDAOTest {
	private AdresDAO adresDao;
	
	@Before
	public void setUp() throws Exception {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		factory.openConnection();
		adresDao = factory.getAdresDAO();
	}

	@After
	public void tearDown() throws Exception {
		RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
		factory.closeConnection();
	}

	@Test
	public void createAndReadAdresTest() {
		Adres adres = new Adres();
		adres.setWoonplaats("Rijswijk");
		adres.setStraatnaam("Lindelaan");
		adres.setHuisnummer(9);
		adres.setToevoeging("A");
		adres.setPostcode("2497 CN");
		int adresID = adresDao.createAdres(adres);
		
		Adres adres1 = adresDao.readAdres(adresID);
		
		assertNotNull("address from database is null", adres1);
		
		assertEquals("woonplaats is not as expected", adres.getWoonplaats(), adres1.getWoonplaats());
		assertEquals("straatnaam is not as expected", adres.getStraatnaam(), adres1.getStraatnaam());
		assertEquals("huisnummer is not as expected", adres.getHuisnummer(), adres1.getHuisnummer());
		assertEquals("toevoeging is not as expected", adres.getToevoeging(), adres.getToevoeging());
		assertEquals("postcode is not as expected", adres.getPostcode(), adres1.getPostcode());
		
		adresDao.deleteAdres(adres);
	}	
	
	@Test
	public void deleteAdresTest() {
		Adres adres = new Adres();
		adres.setWoonplaats("Nootdorp");
		adres.setStraatnaam("Bleriolaan");
		adres.setHuisnummer(2);
		adres.setToevoeging("B");
		adres.setPostcode("2497 SC");
		int adresID = adresDao.createAdres(adres);
		
		adresDao.deleteAdres(adres);
		
		Adres adres1 = adresDao.readAdres(adresID);
		assertNull("address has not been deleted from database", adres1);
	}
	
	@Test
	public void updateAdresTest() {
		Adres adres = new Adres();
		adres.setWoonplaats("Nootdorp");
		adres.setStraatnaam("Bleriolaan");
		adres.setHuisnummer(2);
		adres.setToevoeging("B");
		adres.setPostcode("2497 SC");
		int adresID = adresDao.createAdres(adres);
		
		adres.setWoonplaats("Den Haag");
		adres.setStraatnaam("Bleriosingel");
		adres.setHuisnummer(11);
		adres.setToevoeging("A");
		adres.setPostcode("2498 MP");
		
		adresDao.updateAdres(adres);
		
		Adres adres1 = adresDao.readAdres(adresID);
		
		assertNotNull("address from database is null", adres1);
		
		assertEquals("woonplaats is not as expected", adres.getWoonplaats(), adres1.getWoonplaats());
		assertEquals("straatnaam is not as expected", adres.getStraatnaam(), adres1.getStraatnaam());
		assertEquals("huisnummer is not as expected", adres.getHuisnummer(), adres1.getHuisnummer());
		assertEquals("toevoeging is not as expected", adres.getToevoeging(), adres.getToevoeging());
		assertEquals("postcode is not as expected", adres.getPostcode(), adres1.getPostcode());
		
		adresDao.deleteAdres(adres);
	}
	
	@Test
	public void getAllAdressenTest() {
		Adres adres = new Adres();
		adres.setWoonplaats("Nootdorp");
		adres.setStraatnaam("Bleriolaan");
		adres.setHuisnummer(2);
		adres.setToevoeging("B");
		adres.setPostcode("2497 SC");
		int adresID = adresDao.createAdres(adres);
		
		Adres adres1 = new Adres();
		adres1.setWoonplaats("Nootdorp");
		adres1.setStraatnaam("Bleriolaan");
		adres1.setHuisnummer(2);
		adres1.setToevoeging("B");
		adres1.setPostcode("2497 SC");
		int adres1ID = adresDao.createAdres(adres1);
		
		Adres adres2 = new Adres();
		adres2.setWoonplaats("Nootdorp");
		adres2.setStraatnaam("Bleriolaan");
		adres2.setHuisnummer(2);
		adres2.setToevoeging("B");
		adres2.setPostcode("2497 SC");
		int adres2ID = adresDao.createAdres(adres2);

		List<Adres> list = adresDao.getAllAdressen();
		
		assertNotNull("list from database is null", list);
		assertEquals("list's size is not as expected", list.size(), 3);
		
		for(Adres adr : list) {
			int adrId = adr.getId();
			assertTrue("adres ID from the list is not as expected", adrId == adresID || adrId == adres1ID || adrId == adres2ID);
		}
		
		adresDao.deleteAdres(adres);
		adresDao.deleteAdres(adres1);
		adresDao.deleteAdres(adres2);
	}
}
