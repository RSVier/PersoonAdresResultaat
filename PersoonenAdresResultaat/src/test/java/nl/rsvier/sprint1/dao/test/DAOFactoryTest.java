package nl.rsvier.sprint1.dao.test;

import java.util.List;

import nl.rsvier.sprint1.dao.GenericDAO;
import nl.rsvier.sprint1.dao.ResultaatDAO;
import nl.rsvier.sprint1.dao.impl.RsvierDAOFactory;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;
import nl.rsvier.sprint1.domain.Resultaat;

public class DAOFactoryTest {

   public void testResultaat() {
      RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
      factory.openConnection();

      GenericDAO<Persoon> persoonDao = factory.getPersoonDAO();
      GenericDAO<Adres> adresDao = factory.getAdresDAO();
      ResultaatDAO resultaatDao = factory.getResultaatDAO();

      Persoon newPersoon = new Persoon();
      newPersoon.setId(1113);
      newPersoon.setAchternaam("Boer");
      newPersoon.setVoornaam("Nick");
      newPersoon.setTussenvoegsel("de");
      newPersoon.setGeboortedatum("11-10-1990");

      Adres adres1 = new Adres();
      adres1.setId(117);
      adres1.setWoonplaats("Gouda");
      adres1.setStraatnaam("DouglasLaan");
      adres1.setHuisnummer(10);
      adres1.setPostcode("1212 CC");
      adres1.setToevoeging("b");
      adresDao.create(adres1);

      newPersoon.setAdres(adres1);
      persoonDao.create(newPersoon);

      Resultaat resultaat1 = new Resultaat();
      resultaat1.setId(12);
      resultaat1.setModulenaam("module1");
      resultaat1.setResultaat(6.4f);
      resultaat1.setVoldoende(true);
      resultaat1.setPersoonId(1113);
      resultaatDao.create(resultaat1);

      System.out.println("Done.");

      factory.closeConnection();
   }

   public void testPersoon() {
      RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
      factory.openConnection();

      GenericDAO<Persoon> persoonDao = factory.getPersoonDAO();
      GenericDAO<Adres> adresDao = factory.getAdresDAO();

      Adres adres1 = new Adres();
      adres1.setWoonplaats("Amsterdam");
      adres1.setStraatnaam("Plesmanlaan");
      adres1.setHuisnummer(11);
      adres1.setPostcode("3312 CN");
      adres1.setToevoeging("a");
      adresDao.create(adres1);

      System.out.println("Adres ID=" + adres1.getId());

      Persoon newPersoon = new Persoon();
      newPersoon.setAchternaam("Smith");
      newPersoon.setVoornaam("Noah");
      newPersoon.setTussenvoegsel("van der");
      newPersoon.setGeboortedatum("13-12-2000");
      newPersoon.setAdres(adres1);

      persoonDao.create(newPersoon);

      factory.closeConnection();
   }

   public void testAdres() {
      RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
      factory.openConnection();

      GenericDAO<Adres> adresDao = factory.getAdresDAO();
      GenericDAO<Persoon> persoonDao = factory.getPersoonDAO();

      // add adres
      Adres newAdres = new Adres();
      newAdres.setWoonplaats("Rotterdam");
      newAdres.setStraatnaam("Rivier");
      newAdres.setHuisnummer(17);
      newAdres.setPostcode("3452DM");
      newAdres.setToevoeging("d");

      newAdres.setId(adresDao.create(newAdres));
      System.out.println("ID of newly created Adres:" + newAdres.getId());

      // get all adressen
      List<Adres> adressen = adresDao.getAll();
      System.out.println("Adressen: " + adressen.size());
      System.out.println(adressen);
      Adres adr = adressen.get(0);
   }

   public static void main(String[] args) {
      DAOFactoryTest test = new DAOFactoryTest();
      test.testAdres();
      test.testPersoon();
   }
}
