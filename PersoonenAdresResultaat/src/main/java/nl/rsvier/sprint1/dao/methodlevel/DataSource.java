package nl.rsvier.sprint1.dao.methodlevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

   private static final String JDBC = "com.mysql.jdbc.Driver";
   private static final String DB = "jdbc:mysql://localhost/testDB";
   private static final String USER = "root";

   public DataSource() {
      try {
         Class.forName(JDBC);
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public Connection getConnection() {
      Connection connection = null;
      try {
         connection = DriverManager.getConnection(DB, USER, "");
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return connection;
   }

}
