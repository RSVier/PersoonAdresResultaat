package nl.rsvier.sprint1.dao;

import java.util.List;

public interface GenericDAO <T> {
 
	/**
     * Retrieve all results in the database.
     * @return a List of results
     */
	List<T> getAll ();
	
	/**
	 * CRUD Update
	 * @param resultaat the result to be updated
	 */
	void update (T object);

	/**
	 * CRUD Delete
	 * @param resultaat the result to be deleted from the database
	 */
	void delete (T object);

	/**
	 * CRUD Create
	 * @param resultaat the result to be entered into the database
	 * @return the unique ID assigned to this result by the database
	 */
	int create (T object);

	/**
	 * CRUD Read
	 * @param id the unique ID of the result required
	 * @return the result associated with the unique ID
	 */
	T read (int id);

}
