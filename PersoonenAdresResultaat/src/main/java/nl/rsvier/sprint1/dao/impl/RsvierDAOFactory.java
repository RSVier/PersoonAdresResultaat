package nl.rsvier.sprint1.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.dao.PersoonDAO;
import nl.rsvier.sprint1.dao.ResultaatDAO;

public class RsvierDAOFactory {
	
	private static RsvierDAOFactory instance;
	
	private EntityManager em;
	
	private RsvierDAOFactory() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("rsvier");
		em = factory.createEntityManager();
	}
	
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
	
	public void startTransaction() {
		em.getTransaction().begin();
	}
	
	public void confirmChanges() {
		em.getTransaction().commit();
	}
	
	public void cancelChanges() {
		em.getTransaction().rollback();
	}
}
