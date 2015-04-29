package nl.rsvier.sprint1.dao;

import java.util.List;

import nl.rsvier.sprint1.domain.Resultaat;

public interface ResultaatDAO {
   
        /**
         * Retrieve all results in the database.
         * @return a List of results
         */
	List<Resultaat> getAllResultaten();

	/**
	 * Retrieve all results belonging to a person with the given ID.
	 * @param persoonId the unique ID of the person required.
	 * @return a List of results associated with the given person.
	 */
	List<Resultaat> getAllResultaten(int persoonId);

	/**
	 * CRUD Update
	 * @param resultaat the result to be updated
	 */
	void updateResultaat(Resultaat resultaat);
	
	/**
	 * CRUD Delete
	 * @param resultaat the result to be deleted from the database
	 */
	void deleteResultaat(Resultaat resultaat);
	
	/**
	 * CRUD Create
	 * @param resultaat the result to be entered into the database
	 * @return the unique ID assigned to this result by the database
	 */
	int createResultaat(Resultaat resultaat);
	
	/**
	 * CRUD Read
	 * @param id the unique ID of the result required
	 * @return the result associated with the unique ID
	 */
	Resultaat readResultaat(int id);

}
