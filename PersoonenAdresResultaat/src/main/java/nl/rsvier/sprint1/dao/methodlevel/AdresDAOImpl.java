package nl.rsvier.sprint1.dao.methodlevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.domain.Adres;

public class AdresDAOImpl implements AdresDAO {

   private DataSource source;

   public AdresDAOImpl(DataSource source) {
      this.source = source;
   }

   @Override
   public List<Adres> getAllAdressen() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void updateAdres(Adres adres) {
      // TODO Auto-generated method stub

   }

   @Override
   public void deleteAdres(Adres adres) {
      // TODO Auto-generated method stub

   }

   @Override
   public int createAdres(Adres adres) {
      // TODO unfinished method
      int id = 12;
      ResultSet rs = null;
      try {
         Connection conn = source.getConnection();
         try {
            String sql = "";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            //get Generated key
            rs = st.getGeneratedKeys();
            if(rs.next()){
               id = rs.getInt(1);
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }

      } finally {
         // close connection
      }
      return id;

   }

   @Override
   public Adres readAdres(int id) {

      return null;
   }

}
