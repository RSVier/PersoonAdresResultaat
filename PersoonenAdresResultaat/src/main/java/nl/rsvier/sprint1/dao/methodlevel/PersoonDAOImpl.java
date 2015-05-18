package nl.rsvier.sprint1.dao.methodlevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import nl.rsvier.sprint1.dao.GenericDAO;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;

public class PersoonDAOImpl implements GenericDAO<Persoon> {

   private DataSource source;

   public PersoonDAOImpl(DataSource source) {
      this.source = source;
   }

   @Override
   public List<Persoon> getAll() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void update(Persoon persoon) {
      // TODO Auto-generated method stub

   }

   @Override
   public void delete(Persoon persoon) {
      // TODO Auto-generated method stub

   }

   @Override
   public int create(Persoon persoon) {
      int id = -1;
      Connection conn = source.getConnection();
      String sql = "insert into persoon (voornaam, achternaam, tussenvoegsel, geboortedatum, idAdres) values (?, ?, ?, ?, ?)";

      // voeg eerst adres toe aan DB
      Adres adres = persoon.getAdres();
      new AdresDAOImpl(source).create(adres);

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
   public Persoon read(int id) {
      Persoon persoon = new Persoon();
      persoon.setId(id);

      Connection conn = source.getConnection();
      String sql = "select voornaam, achternaam, tussenvoegsel, geboortedatum, adres from persoon where id = ?";
      try (PreparedStatement prstmt = conn.prepareStatement(sql)) {
         prstmt.setInt(1, id);
         ResultSet rs = prstmt.executeQuery();
         if(rs.next()) {
            persoon.setVoornaam(rs.getString(1));
            persoon.setAchternaam(rs.getString(2));
            persoon.setTussenvoegsel(rs.getString(3));
            persoon.setGeboortedatum(rs.getString(4));
            persoon.setAdres(new AdresDAOImpl(source).read(rs.getInt(5)));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {}
      }

      return persoon;
   }

}
