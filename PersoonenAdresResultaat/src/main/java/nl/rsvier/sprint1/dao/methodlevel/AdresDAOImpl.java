package nl.rsvier.sprint1.dao.methodlevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.rsvier.sprint1.dao.GenericDAO;
import nl.rsvier.sprint1.domain.Adres;

public class AdresDAOImpl implements GenericDAO<Adres> {

   private DataSource source;

   public AdresDAOImpl(DataSource source) {
      this.source = source;
   }

   @Override
   public List<Adres> getAll() {
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement st = null;
      List<Adres> result = new ArrayList<>();
      try {
         conn = source.getConnection();
         try {
            String sql = "SELECT * FROM ADRES";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
               Adres adres = new Adres();
               adres.setStraatnaam(rs.getString(1));
               adres.setHuisnummer(rs.getInt(2));
               adres.setToevoeging(rs.getString(3));
               adres.setPostcode(rs.getString(4));
               adres.setWoonplaats(rs.getString(5));
               result.add(adres);
            }

         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            try {
               if (rs != null) rs.close();
               if (st != null) st.close();
            } catch (SQLException e) {}

         }

      } finally {
         try {
            if (conn != null) conn.close();
         } catch (SQLException e) {}
      }
      return result;
   }

   @Override
   public void update(Adres adres) {
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = source.getConnection();
         try {
            String sql = "UPDATE ADRES SET straatnaam = ?, huisnummer = ?, toevoeging = ?, postcode = ?, woonplaats = ? where id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, adres.getStraatnaam());
            st.setInt(2, adres.getHuisnummer());
            st.setString(3, adres.getToevoeging());
            st.setString(4, adres.getPostcode());
            st.setString(5, adres.getWoonplaats());
            st.setInt(6, adres.getId());
            st.executeUpdate();

         } catch (SQLException e) {
            e.printStackTrace();
         }

      } finally {
         try {
            if (conn != null) conn.close();
            if (st != null) st.close();
         } catch (SQLException e) {}
      }

   }

   @Override
   public void delete(Adres adres) {
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = source.getConnection();
         try {
            String sql = "DELETE FROM ADRES WHERE ID = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, adres.getId());
            st.executeUpdate();

         } catch (SQLException e) {
            e.printStackTrace();
         }

      } finally {
         try {
            if (conn != null) conn.close();
            if (st != null) st.close();
         } catch (SQLException e) {}
      }
   }

   @Override
   public int create(Adres adres) {
      int id = -1;
      ResultSet rs = null;
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = source.getConnection();
         try {
            String sql = "INSERT IGNORE INTO ADRES (straatnaam, huisnummer, toevoeging, postcode, woonplaats) VALUES (?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, adres.getStraatnaam());
            st.setInt(2, adres.getHuisnummer());
            st.setString(3, adres.getToevoeging());
            st.setString(4, adres.getPostcode());
            st.setString(5, adres.getWoonplaats());
            st.executeUpdate();

            // get Generated key
            rs = st.getGeneratedKeys();
            if (rs.next()) {
               id = rs.getInt(1);
            }
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            try {
               if (rs != null) rs.close();
               if (st != null) st.close();
            } catch (SQLException e) {}
         }

      } finally {
         try {
            if (conn != null) conn.close();
         } catch (SQLException e) {}
      }
      return id;

   }

   @Override
   public Adres read(int id) {

      return null;
   }

}
