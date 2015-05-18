package nl.rsvier.sprint1.dao.methodlevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void update(Adres adres) {
      // TODO Auto-generated method stub

   }

   @Override
   public void delete(Adres adres) {
      // TODO Auto-generated method stub

   }

   @Override
   public int create(Adres adres) {
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
   public Adres read(int id) {

      return null;
   }

}
