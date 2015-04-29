package nl.rsvier.sprint1.dao;

import java.util.List;

import nl.rsvier.sprint1.domain.Adres;

public interface AdresDAO {
        
        /**
         * Retrieve all adresses in the database.
         * @return List of adresses
         */
	List<Adres> getAllAdressen();
	
	/**
	 * CRUD Update.
	 * @param adres the address to be updated.
	 */
	void updateAdres(Adres adres);
	
	/**
	 * CRUD Delete.
	 * @param adres
	 */
	void deleteAdres(Adres adres);
	
	/**
	 * CRUD Create.
	 * @param adres the address to be inserted into the database
	 * @return the automatically assigned ID for this address, returns -1 if the address already existed in the database
	 */
	int createAdres(Adres adres);
	
	/**
	 * CRUD Read.
	 * @param id Unique identifier of the address requested from the database.
	 * @return the address associated with the unique ID.
	 */
	Adres readAdres(int id);
}
