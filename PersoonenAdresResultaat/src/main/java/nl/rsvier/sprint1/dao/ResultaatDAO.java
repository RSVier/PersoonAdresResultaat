package nl.rsvier.sprint1.dao;

import java.util.List;

import nl.rsvier.sprint1.domain.Resultaat;

public interface ResultaatDAO extends GenericDAO <Resultaat> {

	/**
	 * Retrieve all results belonging to a person with the given ID.
	 * @param persoonId the unique ID of the person required.
	 * @return a List of results associated with the given person.
	 */
	List<Resultaat> getAll(int persoonId);
}
