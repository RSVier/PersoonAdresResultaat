package nl.rsvier.sprint1.dao;

import java.util.List;

public interface ResultaatDAO {
	List<Resultaat> getAllResultaten();

	List<Resultaat> getAllResultaten(int persoonId);

	void updateResultaat(Resultaat resultaat);
	
	void deleteResultaat(Resultaat resultaat);
	
	void addResultaat(Resultaat resultaat);
	
	Resultaat getResultaat(int id);

}
