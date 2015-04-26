package nl.rsvier.sprint1.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nl.rsvier.sprint1.dao.Adres;
import nl.rsvier.sprint1.dao.AdresDAO;

public class AdresDAOImpl implements AdresDAO {

	private EntityManager em;
	
	AdresDAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Adres> getAllAdressen() {
		Query query = em.createQuery("select adres from Adres as adres");
		List<Adres> adresList = query.getResultList();
		return adresList;
	}

	@Override
	public void updateAdres(Adres adres) {
		em.merge(adres);
	}

	@Override
	public void deleteAdres(Adres adres) {
		em.remove(adres);
	}

	@Override
	public void addAdres(Adres adres) {
		em.persist(adres);
	}

	@Override
	public Adres getAdres(int id) {
		Adres adres = em.find(Adres.class, id);
		return adres;
	}
}
