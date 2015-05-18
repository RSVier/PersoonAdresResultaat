package nl.rsvier.sprint1.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nl.rsvier.sprint1.dao.GenericDAO;
import nl.rsvier.sprint1.dao.ResultaatDAO;
import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Persoon;

public class RsvierDAOFactory {
	
	private static final String MY_SQL_DB = "jdbc:mysql://localhost/rsvier";
	
	private static final String USERNAME = "root";
	
	private static RsvierDAOFactory instance;
	
	private Connection connection;
	
	private RsvierDAOFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static RsvierDAOFactory getInstance() {
		if (instance == null) {
			instance = new RsvierDAOFactory();
		}
		return instance;
	}
	
	public GenericDAO<Persoon> getPersoonDAO() {
		return new PersoonDAOImpl(connection);
	}
	
	public GenericDAO<Adres> getAdresDAO() {
		return new AdresDAOImpl(connection);
	}
	
	public ResultaatDAO getResultaatDAO() {
		return new ResultaatDAOImpl(connection);
	}
	
	public void openConnection() {
		try {
			connection = DriverManager.getConnection(MY_SQL_DB, USERNAME, null);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void startTransaction() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void confirmChanges() {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cancelChanges() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
