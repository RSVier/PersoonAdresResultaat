package nl.rsvier.sprint1.dao;

import java.util.List;

import nl.rsvier.sprint1.domain.Persoon;

public interface PersoonDAO {
   
        /**
         * Retrieve all persons in the database.
         * @return List of persons
         */
	List<Persoon> getAllPersonen();
	
	/**
	 * CRUD Update.
	 * @param persoon the person to be updated.
	 */
	void updatePersoon(Persoon persoon);
	
	/**
	 * CRUD Delete.
	 * @param persoon
	 */
	void deletePersoon(Persoon persoon);
	
	/**
	 * CRUD Create.
	 * @param persoon the person to be added to the database.
	 * @return the unique ID that is assigned to this person by the database, returns -1 if the person already exists in the database
	 */
	int createPersoon(Persoon persoon);
	
	/**
	 * CRUD Read.
	 * @param id the unique ID of the person required.
	 * @return the person associated with the unique ID provided.
	 */
	Persoon readPersoon(int id);

}
