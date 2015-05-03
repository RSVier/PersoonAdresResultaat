package nl.rsvier.sprint1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.domain.Adres;

public class AdresDAOImpl implements AdresDAO {

   private Connection connection;

   AdresDAOImpl(Connection connection) {
      this.connection = connection;
   }

   @Override
   public List<Adres> getAllAdressen() {
      List<Adres> adresList = new ArrayList<>();
      try {
         PreparedStatement st = connection.prepareStatement("select id, straatnaam, huisnummer, toevoeging, postcode, woonplaats from adres");
         ResultSet rs = st.executeQuery();
         while (rs.next()) {
            Adres adres = new Adres();
            adres.setId(rs.getInt(1));
            adres.setStraatnaam(rs.getString(2));
            adres.setHuisnummer(rs.getInt(3));
            adres.setToevoeging(rs.getString(4));
            adres.setPostcode(rs.getString(5));
            adres.setWoonplaats(rs.getString(6));

            adresList.add(adres);
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return adresList;

   }

   @Override
   public void updateAdres(Adres adres) {
      try {
         PreparedStatement st = connection.prepareStatement("update adres set straatnaam = ?, huisnummer = ?, "
               + "toevoeging = ?, postcode = ?, woonplaats = ? where id = ?");
         st.setString(1, adres.getStraatnaam());
         st.setInt(2, adres.getHuisnummer());
         st.setString(3, adres.getToevoeging());
         st.setString(4, adres.getPostcode());
         st.setString(5, adres.getWoonplaats());
         st.setInt(6, adres.getId());
         st.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }

   @Override
   public void deleteAdres(Adres adres) {
      try {
         PreparedStatement st = connection.prepareStatement("delete from adres where id = ?");
         st.setInt(1, adres.getId());
         st.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }

   @Override
   public int createAdres(Adres adres) {
      ResultSet rs = null;
      int id = -1;
      try {
         
         PreparedStatement st = connection.prepareStatement("insert ignore into adres (straatnaam, huisnummer, "
               + "toevoeging, postcode, woonplaats) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
         st.setString(1, adres.getStraatnaam());
         st.setInt(2, adres.getHuisnummer());
         st.setString(3, adres.getToevoeging());
         st.setString(4, adres.getPostcode());
         st.setString(5, adres.getWoonplaats());
         st.executeUpdate();

         // retrieve Unique ID for newly created address.
         rs = st.getGeneratedKeys();
         if (rs.next()) {
            id = rs.getInt(1);
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return id;
   }
   
   /**
    * Retrieves the unique identifier for the adres object provided. Requires all fields of the adres object to be filled with valid data.
    * @param adres 
    * @return the ID associated with the adres. If the address is not found in the database, 0 is returned.
    */
   public int retrieveId(Adres adres) {
      int id = adres.getId();
      if (id != 0) { return id; }
      ResultSet rs = null;

      try {
         PreparedStatement st = connection
               .prepareStatement("select id from adres where (straatnaam = ? AND huisnummer = ? AND toevoeging = ? AND postcode = ? AND woonplaats = ?)");
         st.setString(1, adres.getStraatnaam());
         st.setInt(2, adres.getHuisnummer());
         st.setString(3, adres.getToevoeging());
         st.setString(4, adres.getPostcode());
         st.setString(5, adres.getWoonplaats());
         rs = st.executeQuery();
        
         if (rs.next()){
            id = rs.getInt(1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return id;
   }

   @Override
   public Adres readAdres(int id) {
      Adres adres = new Adres();
      try {
         PreparedStatement st = connection.prepareStatement("select id, straatnaam, huisnummer, toevoeging, postcode, woonplaats " + "from adres where id = ?");
         st.setInt(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()) {
            adres.setId(rs.getInt(1));
            adres.setStraatnaam(rs.getString(2));
            adres.setHuisnummer(rs.getInt(3));
            adres.setToevoeging(rs.getString(4));
            adres.setPostcode(rs.getString(5));
            adres.setWoonplaats(rs.getString(6));

         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return adres;
   }
}
