package nl.rsvier.sprint1.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nl.rsvier.sprint1.dao.Resultaat;
import nl.rsvier.sprint1.dao.ResultaatDAO;

public class ResultaatDAOImpl implements ResultaatDAO {

	private EntityManager em;
	
	ResultaatDAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Resultaat> getAllResultaten() {
		Query query = em.createQuery("select resultaat from Resultaat as resultaat");
		List<Resultaat> resultaatList = query.getResultList();
		return resultaatList;
	}

	@Override
	public void updateResultaat(Resultaat resultaat) {
		em.merge(resultaat);
	}

	@Override
	public void deleteResultaat(Resultaat resultaat) {
		em.remove(resultaat);
	}

	@Override
	public void addResultaat(Resultaat resultaat) {
		em.persist(resultaat);
	}

	@Override
	public Resultaat getResultaat(int id) {
		Resultaat resultaat = em.find(Resultaat.class, id);
		return resultaat;
	}

}
