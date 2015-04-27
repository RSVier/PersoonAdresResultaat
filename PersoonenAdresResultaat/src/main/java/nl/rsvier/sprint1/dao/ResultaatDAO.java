package nl.rsvier.sprint1.dao;

import java.util.List;

import nl.rsvier.sprint1.domain.Resultaat;

public interface ResultaatDAO {
	List<Resultaat> getAllResultaten();

	List<Resultaat> getAllResultaten(int persoonId);

	void updateResultaat(Resultaat resultaat);
	
	void deleteResultaat(Resultaat resultaat);
	
	void addResultaat(Resultaat resultaat);
	
	Resultaat getResultaat(int id);

}
