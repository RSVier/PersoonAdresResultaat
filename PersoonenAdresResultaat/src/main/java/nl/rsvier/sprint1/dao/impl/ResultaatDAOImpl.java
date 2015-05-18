package nl.rsvier.sprint1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.rsvier.sprint1.dao.ResultaatDAO;
import nl.rsvier.sprint1.domain.Resultaat;

public class ResultaatDAOImpl implements ResultaatDAO {

   private Connection connection;

   ResultaatDAOImpl(Connection connection) {
      this.connection = connection;
   }

   @Override
   public List<Resultaat> getAll() {
      List<Resultaat> resultaten = new ArrayList<>();
      try {
         PreparedStatement st = connection.prepareStatement("select id, modulenaam, resultaat, voldoende, persoon_id from resultaat");
         ResultSet rs = st.executeQuery();
         while (rs.next()) {
            Resultaat resultaat = new Resultaat();
            resultaat.setId(rs.getInt(1));
            resultaat.setModulenaam(rs.getString(2));
            resultaat.setResultaat(rs.getFloat(3));
            resultaat.setVoldoende(rs.getBoolean(4));
            resultaat.setPersoonId(rs.getInt(5));

            resultaten.add(resultaat);
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return resultaten;
   }

   @Override
   public List<Resultaat> getAll(int persoonId) {
      List<Resultaat> resultaten = new ArrayList<>();
      try {
         PreparedStatement st = connection.prepareStatement("select id, modulenaam, resultaat, voldoende, persoon_id " + 
        		 												"from resultaat where persoon_id = ?");
         st.setInt(1, persoonId);
         ResultSet rs = st.executeQuery();
         while (rs.next()) {
            Resultaat resultaat = new Resultaat();
            resultaat.setId(rs.getInt(1));
            resultaat.setModulenaam(rs.getString(2));
            resultaat.setResultaat(rs.getFloat(3));
            resultaat.setVoldoende(rs.getBoolean(4));

            resultaten.add(resultaat);
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return resultaten;
   }

   @Override
   public void update(Resultaat resultaat) {
      try {
         PreparedStatement st = connection.prepareStatement("update resultaat set modulenaam = ?, resultaat = ?, "
               + " voldoende = ?, persoon_id = ? where id = ?");
         st.setString(1, resultaat.getModulenaam());
         st.setFloat(2, resultaat.getResultaat());
         st.setBoolean(3, resultaat.isVoldoende());
         st.setInt(4, resultaat.getPersoonId());
         st.setInt(5, resultaat.getId());
         st.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }

   @Override
   public void delete(Resultaat resultaat) {
      try {
         PreparedStatement st = connection.prepareStatement("delete from resultaat where id = ?");
         st.setInt(1, resultaat.getId());
         st.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }

   }

   @Override
   public int create(Resultaat resultaat) {
      ResultSet rs = null;
      int id = -1;
      try {
         PreparedStatement st = connection.prepareStatement("insert into resultaat (modulenaam, resultaat, persoon_id, " + "voldoende) values (?, ?, ?, ?)",
               Statement.RETURN_GENERATED_KEYS);
         st.setString(1, resultaat.getModulenaam());
         st.setFloat(2, resultaat.getResultaat());
         st.setInt(3, resultaat.getPersoonId());
         st.setBoolean(4, resultaat.isVoldoende());
         st.executeUpdate();

         // retrieve generated ID
         rs = st.getGeneratedKeys();
         if (rs.next()) {
            id = rs.getInt(1);
            resultaat.setId(id);
         }

      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return id;
   }

   @Override
   public Resultaat read(int id) {
      Resultaat resultaat = null;
      try {
         PreparedStatement st = connection.prepareStatement("select id, modulenaam, resultaat, voldoende, persoon_id from resultaat " + " where id = ?");
         st.setInt(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()) {
        	resultaat = new Resultaat();
            resultaat.setId(rs.getInt(1));
            resultaat.setModulenaam(rs.getString(2));
            resultaat.setResultaat(rs.getFloat(3));
            resultaat.setVoldoende(rs.getBoolean(4));
            resultaat.setPersoonId(rs.getInt(5));
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return resultaat;
   }
}
