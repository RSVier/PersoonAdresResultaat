package nl.rsvier.sprint1.dao;

import java.util.List;

import nl.rsvier.sprint1.domain.Persoon;

public interface PersoonDAO {
	List<Persoon> getAllPersonen();
	
	void updatePersoon(Persoon persoon);
	
	void deletePersoon(Persoon persoon);
	
	void addPersoon(Persoon persoon);
	
	Persoon getPersoon(int id);

}
