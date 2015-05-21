package nl.rsvier.sprint1.dao.impl;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.rsvier.sprint1.domain.*;
import nl.rsvier.sprint1.dao.*;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/Toevoegen.jsp";
	private static String LIST_USER = "/Personenlijst.jsp";
	private static String VIEW_RESULT = "/Resultaat.jsp";
	private RsvierDAOFactory factory;
    
    public UserController() {
        super();
        factory = RsvierDAOFactory.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");
		
		factory.openConnection();
		factory.startTransaction();
		
		GenericDAO<Persoon> persoonDao = factory.getPersoonDAO();
		
		if (action.equalsIgnoreCase("delete")){
            int persoonId = Integer.parseInt(request.getParameter("id"));
            Persoon persoon = persoonDao.read(persoonId);
            persoonDao.delete(persoon);
            forward = LIST_USER;
            request.setAttribute("list", persoonDao.getAll());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int persoonId = Integer.parseInt(request.getParameter("id"));
            Persoon persoon = persoonDao.read(persoonId);
            request.setAttribute("persoon", persoon);
        } else if (action.equalsIgnoreCase("result")) {
        	forward = VIEW_RESULT;
        	int persoonId = Integer.parseInt(request.getParameter("id"));
        	Persoon persoon = persoonDao.read(persoonId);
        	request.setAttribute("persoon", persoon);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            request.setAttribute("list", persoonDao.getAll());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
        factory.confirmChanges();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");
		
		factory.openConnection();
		factory.startTransaction();
		
		GenericDAO<Persoon> persoonDao = factory.getPersoonDAO();
		GenericDAO<Adres> adresDao = factory.getAdresDAO();
		ResultaatDAO resultaatDao = factory.getResultaatDAO();
		
		if (action.equalsIgnoreCase("addpersoon")){
			Persoon persoon = new Persoon();
			Adres adres = new Adres();
			adres.setStraatnaam(request.getParameter("straatnaam"));
			adres.setHuisnummer(Integer.parseInt(request.getParameter("huisnummer")));
			String toevoeging = request.getParameter("toevoeging");
			if(toevoeging == null || toevoeging.isEmpty()) {
				adres.setToevoeging(null);
			} else {
				adres.setToevoeging(toevoeging);
			}
			adres.setPostcode(request.getParameter("postcode"));
			adres.setWoonplaats(request.getParameter("woonplaats"));
			String adresId = request.getParameter("adresId");
			if(adresId == null || adresId.isEmpty()) {
				int adresID = adresDao.create(adres);
				adres.setId(adresID);
			} else {
				adres.setId(Integer.parseInt(adresId));
				adresDao.update(adres);
			}
			
			persoon.setVoornaam(request.getParameter("voornaam"));
			persoon.setAchternaam(request.getParameter("achternaam"));
			persoon.setTussenvoegsel(request.getParameter("toevoeging"));
			persoon.setGeboortedatum(request.getParameter("geboortedatum"));
			persoon.setAdres(adres);
	        String persoonId = request.getParameter("persoonId");
	        if(persoonId == null || persoonId.isEmpty())
	        {
	            int persoonID = persoonDao.create(persoon);
	            persoon.setId(persoonID);
	        }
	        else
	        {
	            persoon.setId(Integer.parseInt(persoonId));
	            persoonDao.update(persoon);
	        }
            forward = LIST_USER;
            request.setAttribute("list", persoonDao.getAll());    
        } else if(action.equalsIgnoreCase("addresultaat")) {
            Resultaat resultaat = new Resultaat();
            resultaat.setModulenaam(request.getParameter("modulenaam"));
            int persoonId = Integer.parseInt(request.getParameter("persoonId"));
            resultaat.setPersoonId(persoonId);
            float result = Float.parseFloat(request.getParameter("resultaat"));
            DecimalFormat df = new DecimalFormat("#.#");
    		df.setRoundingMode(RoundingMode.CEILING);
    		df.format(result);
    		resultaat.setResultaat(result);
    		String voldoende = request.getParameter("voldoende");
    		if(voldoende.equalsIgnoreCase("ja")) {
    			resultaat.setVoldoende(true);
    		} else {
    			resultaat.setVoldoende(false);
    		}
    		int resultaatID = resultaatDao.create(resultaat);
    		resultaat.setId(resultaatID);
    		forward = VIEW_RESULT;
    		Persoon persoon = persoonDao.read(persoonId);
        	request.setAttribute("persoon", persoon);
        } else {
        	String[] resultaten = request.getParameterValues("check");
        	if(resultaten != null) {
        		for(int i = 0; i < resultaten.length; i++) {
        			int resultaatID = Integer.parseInt(resultaten[i]);
            		Resultaat resultaat = resultaatDao.read(resultaatID);
            		resultaatDao.delete(resultaat);
        		}
        	}
        	forward = VIEW_RESULT;
        	Persoon persoon = persoonDao.read(Integer.parseInt(request.getParameter("persoonId")));
        	request.setAttribute("persoon", persoon);
        }
		
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
        factory.confirmChanges();
	}

}
