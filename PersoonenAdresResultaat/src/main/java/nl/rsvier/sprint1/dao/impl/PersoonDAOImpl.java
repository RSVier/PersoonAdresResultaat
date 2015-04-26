package nl.rsvier.sprint1.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nl.rsvier.sprint1.dao.Persoon;
import nl.rsvier.sprint1.dao.PersoonDAO;

public class PersoonDAOImpl implements PersoonDAO {

	private EntityManager em;
	
	PersoonDAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Persoon> getAllPersonen() {
		Query query = em.createQuery("select persoon from Persoon as persoon");
		List<Persoon> persoonList = query.getResultList();
		return persoonList;
	}

	@Override
	public void updatePersoon(Persoon persoon) {
		em.merge(persoon);
	}

	@Override
	public void deletePersoon(Persoon persoon) {
		em.remove(persoon);
	}

	@Override
	public void addPersoon(Persoon persoon) {
		em.persist(persoon);
	}

	@Override
	public Persoon getPersoon(int id) {
		Persoon persoon = em.find(Persoon.class, id);
		return persoon;
	}

}
