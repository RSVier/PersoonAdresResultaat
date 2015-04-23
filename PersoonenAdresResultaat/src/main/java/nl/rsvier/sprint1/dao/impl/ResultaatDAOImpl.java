package nl.rsvier.sprint1.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import nl.rsvier.sprint1.dao.Resultaat;
import nl.rsvier.sprint1.dao.ResultaatDAO;

public class ResultaatDAOImpl implements ResultaatDAO {

	private EntityManager em;
	
	ResultaatDAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Resultaat> getAllResultaten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateResultaat(Resultaat resultaat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteResultaat(Resultaat resultaat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addResultaat(Resultaat resultaat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Resultaat getResultaat(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
