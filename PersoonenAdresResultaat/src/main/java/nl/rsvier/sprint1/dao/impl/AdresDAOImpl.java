package nl.rsvier.sprint1.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import nl.rsvier.sprint1.dao.Adres;
import nl.rsvier.sprint1.dao.AdresDAO;

public class AdresDAOImpl implements AdresDAO {

	private EntityManager em;
	
	AdresDAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Adres> getAllAdressen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAdres(Adres adres) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAdres(Adres adres) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAdres(Adres adres) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Adres getAdres(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
