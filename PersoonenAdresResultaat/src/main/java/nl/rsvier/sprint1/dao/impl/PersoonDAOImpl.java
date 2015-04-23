package nl.rsvier.sprint1.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import nl.rsvier.sprint1.dao.Persoon;
import nl.rsvier.sprint1.dao.PersoonDAO;

public class PersoonDAOImpl implements PersoonDAO {

	private EntityManager em;
	
	PersoonDAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Persoon> getAllPersonen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePersoon(Persoon persoon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersoon(Persoon persoon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPersoon(Persoon persoon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Persoon getPersoon(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
