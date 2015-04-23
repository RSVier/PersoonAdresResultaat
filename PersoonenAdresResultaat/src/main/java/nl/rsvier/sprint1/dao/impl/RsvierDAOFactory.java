package nl.rsvier.sprint1.dao.impl;

import javax.persistence.EntityManager;

import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.dao.PersoonDAO;
import nl.rsvier.sprint1.dao.ResultaatDAO;

public class RsvierDAOFactory {
	
	private static RsvierDAOFactory instance;
	
	private EntityManager em;
	
	private RsvierDAOFactory() {}
	
	public static RsvierDAOFactory getInstance() {
		if (instance == null) {
			instance = new RsvierDAOFactory();
		}
		return instance;
	}
	
	public PersoonDAO getPersoonDAO() {
		return new PersoonDAOImpl(em);
	}
	
	public AdresDAO getAdresDAO() {
		return new AdresDAOImpl(em);
	}
	
	public ResultaatDAO getResultaatDAO() {
		return new ResultaatDAOImpl(em);
	}
}
