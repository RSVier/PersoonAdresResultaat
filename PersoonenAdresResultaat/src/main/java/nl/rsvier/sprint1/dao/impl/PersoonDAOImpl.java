package nl.rsvier.sprint1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.rsvier.sprint1.dao.Adres;
import nl.rsvier.sprint1.dao.AdresDAO;
import nl.rsvier.sprint1.dao.Persoon;
import nl.rsvier.sprint1.dao.PersoonDAO;
import nl.rsvier.sprint1.dao.Resultaat;
import nl.rsvier.sprint1.dao.ResultaatDAO;

public class PersoonDAOImpl implements PersoonDAO {
	private Connection connection;
	
	PersoonDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public List<Persoon> getAllPersonen() {
		List<Persoon> persoonList = new ArrayList<>();
		try {
			PreparedStatement st = connection.prepareStatement("select id, voornaam, achternaam, tussenvoegsel, "+
									"geboortedatum, adres_id from persoon");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Persoon persoon = new Persoon();
				int persoonId = rs.getInt(1); 
				persoon.setId(persoonId);
				persoon.setVoornaam(rs.getString(2));
				persoon.setAchternaam(rs.getString(3));
				persoon.setTussenvoegsel(rs.getString(4));
				persoon.setGeboortedatum(rs.getString(5));

				// get Adres
				int adresId = rs.getInt(6);
				RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
				AdresDAO adresDao = factory.getAdresDAO();
				Adres adres = adresDao.getAdres(adresId);
				persoon.setAdres(adres);
				
				// get Resultaten
				ResultaatDAO resultaatDao = factory.getResultaatDAO();
				List<Resultaat> listResultaat = resultaatDao.getAllResultaten(persoonId);
				Resultaat[] resultaten = (Resultaat[]) listResultaat.toArray();
				persoon.setResultaten(resultaten);
				
				persoonList.add(persoon);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return persoonList;
	}

	@Override
	public void updatePersoon(Persoon persoon) {
		try {
			PreparedStatement st = connection.prepareStatement("update persoon set voornaam = ?, "+
					" achternaam = ?, tussenvoegsel = ?, geboortedatum = ?, adres_id = ? where id = ?");
			st.setString(1, persoon.getVoornaam());
			st.setString(2, persoon.getAchternaam());
			st.setString(3, persoon.getTussenvoegsel());
			st.setString(4, persoon.getGeboortedatum());
			st.setInt(5, persoon.getAdres().getId());
			st.setInt(6, persoon.getId());
			st.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deletePersoon(Persoon persoon) {
		try {
			PreparedStatement st = connection.prepareStatement("delete from persoon where id = ?");
			st.setInt(1, persoon.getId());
			st.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void addPersoon(Persoon persoon) {
		try {
			PreparedStatement st = connection.prepareStatement("insert into persoon (id, voornaam, achternaam, "+
									"tussenvoegsel, geboortedatum, adres_id) values (?, ?, ?, ?, ?, ?)");
			st.setInt(1, persoon.getId());
			st.setString(2, persoon.getVoornaam());
			st.setString(3, persoon.getAchternaam());
			st.setString(4, persoon.getTussenvoegsel());
			st.setString(5, persoon.getGeboortedatum());
			st.setInt(6, persoon.getAdres().getId());
			st.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public Persoon getPersoon(int id) {
		Persoon persoon = new Persoon();
		try {
			PreparedStatement st = connection.prepareStatement("select id, voornaam, achternaam, tussenvoegsel, geboortedatum" +
								   "from persoon where id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int persoonId = rs.getInt(1);
				
				persoon.setId(persoonId);
				persoon.setVoornaam(rs.getString(2));
				persoon.setAchternaam(rs.getString(3));
				persoon.setTussenvoegsel(rs.getString(4));
				persoon.setGeboortedatum(rs.getString(5));
				
				// get Adres
				int adresId = rs.getInt(6);
				RsvierDAOFactory factory = RsvierDAOFactory.getInstance();
				AdresDAO adresDao = factory.getAdresDAO();
				Adres adres = adresDao.getAdres(adresId);
				persoon.setAdres(adres);
				
				// get Resultaten
				ResultaatDAO resultaatDao = factory.getResultaatDAO();
				List<Resultaat> listResultaat = resultaatDao.getAllResultaten(persoonId);
				Resultaat[] resultaten = (Resultaat[]) listResultaat.toArray();
				persoon.setResultaten(resultaten);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}		

		return persoon;
	}

}
