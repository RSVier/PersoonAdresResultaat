package nl.rsvier.sprint1.dao;

import java.util.List;

public interface AdresDAO {
	List<Adres> getAllAdressen();
	
	void updateAdres(Adres adres);
	
	void deleteAdres(Adres adres);
	
	void addAdres(Adres adres);
	
	Adres getAdres(int id);
}
