package nl.rsvier.sprint1.dao.methodlevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import nl.rsvier.sprint1.dao.PersoonDAO;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;

public class PersoonDAOImpl implements PersoonDAO {

   private DataSource source;

   public PersoonDAOImpl(DataSource source) {
      this.source = source;
   }

   @Override
   public List<Persoon> getAllPersonen() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void updatePersoon(Persoon persoon) {
      // TODO Auto-generated method stub

   }

   @Override
   public void deletePersoon(Persoon persoon) {
      // TODO Auto-generated method stub

   }

   @Override
   public int createPersoon(Persoon persoon) {
      int id = -1;
      Connection conn = source.getConnection();
      String sql = "insert into persoon (voornaam, achternaam, tussenvoegsel, geboortedatum, idAdres) values (?, ?, ?, ?, ?)";

      // voeg eerst adres toe aan DB
      Adres adres = persoon.getAdres();
      new AdresDAOImpl(source).createAdres(adres);

      try (PreparedStatement prstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

         prstmt.setString(1, persoon.getVoornaam());
         prstmt.setString(2, persoon.getAchternaam());
         prstmt.setString(3, persoon.getTussenvoegsel());
         prstmt.setString(4, persoon.getGeboortedatum());
         prstmt.setInt(5, adres.getId());

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {}
      }
      return id;
   }

   @Override
   public Persoon readPersoon(int id) {
      Persoon persoon = new Persoon();
      persoon.setId(id);
      ResultSet rs = null;

      Connection conn = source.getConnection();
      String sql = "select voornaam, achternaam, tussenvoegsel, geboortedatum, adres from persoon where id = ?";
      try (PreparedStatement st = conn.prepareStatement(sql)) {
         st.setInt(1, id);
         rs = st.executeQuery();
         if (rs.next()) {
            persoon.setVoornaam(rs.getString(1));
            persoon.setAchternaam(rs.getString(2));
            persoon.setTussenvoegsel(rs.getString(3));
            persoon.setGeboortedatum(rs.getString(4));
            persoon.setAdres(new AdresDAOImpl(source).readAdres(rs.getInt(5)));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null) rs.close();
         } catch (SQLException e) {}
      }

      return persoon;
   }

}
