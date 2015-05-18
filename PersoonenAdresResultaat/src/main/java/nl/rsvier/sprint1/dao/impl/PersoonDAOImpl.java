package nl.rsvier.sprint1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nl.rsvier.sprint1.dao.GenericDAO;
import nl.rsvier.sprint1.dao.ResultaatDAO;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;
import nl.rsvier.sprint1.domain.Resultaat;

public class PersoonDAOImpl implements GenericDAO<Persoon> {
   private Connection connection;

   PersoonDAOImpl(Connection connection) {
      this.connection = connection;
   }

   @Override
   public List<Persoon> getAll() {
      List<Persoon> persoonList = new ArrayList<>();
      try {
         PreparedStatement st = connection.prepareStatement("select id, voornaam, achternaam, tussenvoegsel, " 
                              + "geboortedatum, adres_id from persoon");
         ResultSet rs = st.executeQuery();
         while (rs.next()) {
            Persoon persoon = new Persoon();
            int persoonId = rs.getInt(1);
            persoon.setId(persoonId);
            persoon.setVoornaam(rs.getString(2));
            persoon.setAchternaam(rs.getString(3));
            persoon.setTussenvoegsel(rs.getString(4));
            persoon.setGeboortedatum(rs.getString(5));

            // get Adres
            int adresId = rs.getInt(6);
            RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
            GenericDAO<Adres> adresDao = factory.getAdresDAO();
            Adres adres = adresDao.read(adresId);
            persoon.setAdres(adres);

            // get Resultaten
            ResultaatDAO resultaatDao = factory.getResultaatDAO();
            List<Resultaat> listResultaat = resultaatDao.getAll(persoonId);
            Resultaat[] resultaten = (Resultaat[]) listResultaat.toArray(new Resultaat[]{});
            persoon.setResultaten(resultaten);

            persoonList.add(persoon);
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return persoonList;
   }

   @Override
   public void update(Persoon persoon) {
      try {
         PreparedStatement st = connection.prepareStatement("update persoon set voornaam = ?, "
               + " achternaam = ?, tussenvoegsel = ?, geboortedatum = ?, adres_id = ? where id = ?");
         st.setString(1, persoon.getVoornaam());
         st.setString(2, persoon.getAchternaam());
         st.setString(3, persoon.getTussenvoegsel());
         st.setString(4, persoon.getGeboortedatum());
         st.setInt(5, persoon.getAdres().getId());
         st.setInt(6, persoon.getId());
         st.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }

   @Override
   public void delete(Persoon persoon) {
      try {
         PreparedStatement st = connection.prepareStatement("delete from persoon where id = ?");
         st.setInt(1, persoon.getId());
         st.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }

   }

   @Override
   public int create(Persoon persoon) {
      ResultSet rs = null;
      int id = -1;
      try {
         PreparedStatement st = connection.prepareStatement("insert into persoon (voornaam, achternaam, "
               + "tussenvoegsel, geboortedatum, adres_id) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
         st.setString(1, persoon.getVoornaam());
         st.setString(2, persoon.getAchternaam());
         st.setString(3, persoon.getTussenvoegsel());
         st.setString(4, persoon.getGeboortedatum());
         // TODO is nu kapot, hoe een goede flow te krijgen tussen creatie van nieuwe persoon/adres combinatie en het terughalen van de unique ID?
         if (persoon.getAdres() != null) {
            st.setInt(5, persoon.getAdres().getId());
         } else {
            st.setNull(5, Types.INTEGER);
         }
         st.executeUpdate();

         // Retrieve ID assigned by database.
         rs = st.getGeneratedKeys();
         if (rs.next()){
            id = rs.getInt(1);
            persoon.setId(id);
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return id;
   }

   @Override
   public Persoon read(int id) {
      Persoon persoon = null;
      try {
         PreparedStatement st = connection.prepareStatement("select id, voornaam, achternaam, tussenvoegsel, geboortedatum, adres_id " 
                                 + "from persoon where id = ?");
         st.setInt(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()) {
        	persoon = new Persoon(); 
            int persoonId = rs.getInt(1);

            persoon.setId(persoonId);
            persoon.setVoornaam(rs.getString(2));
            persoon.setAchternaam(rs.getString(3));
            persoon.setTussenvoegsel(rs.getString(4));
            persoon.setGeboortedatum(rs.getString(5));

            // get Adres
            int adresId = rs.getInt(6);
            RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
            GenericDAO<Adres> adresDao = factory.getAdresDAO();
            Adres adres = adresDao.read(adresId);
            persoon.setAdres(adres);

            // get Resultaten
            ResultaatDAO resultaatDao = factory.getResultaatDAO();
            List<Resultaat> listResultaat = resultaatDao.getAll(persoonId);
            Resultaat[] resultaten = (Resultaat[]) listResultaat.toArray(new Resultaat[]{});
            persoon.setResultaten(resultaten);
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }

      return persoon;
   }

}
