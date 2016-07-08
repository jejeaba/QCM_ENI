package fr.eni.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import fr.eni.bo.Formateur;
import fr.eni.bo.Formation;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.DBAcces;
import fr.eni.utils.DynamicEntities;
import fr.eni.utils.GestionErreur;
import fr.eni.utils.ReflexionUtils;

/**
 * Servlet implementation class accueil
 */
public class gestionFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionFormation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			GestionErreur.redirectionErreur(e, request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			GestionErreur.redirectionErreur(e, request, response);
			return;
		}
	}

	/**
	 * Methode en charge de .
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynamicEntities _db = new DynamicEntities();
		Formateur formateur;
		Formation formation;
		String success=null;
		String error=null;
		if("Ajouter".equals(request.getParameter("addFormation"))){
			int formateurId = Integer.parseInt(request.getParameter("formateur"));
			Utilisateur utilisateur = _db.set(Utilisateur.class).selectById(formateurId);
			
			formation = new Formation(0,request.getParameter("nomFormation"),(Formateur) utilisateur);
		}
		List<Formation> listeFormations = new ArrayList<Formation>();	
		listeFormations = _db.set(Formation.class).selectAll();
		request.setAttribute("listeFormations",listeFormations );
		request.setAttribute("success", success);
		request.setAttribute("error", error);
		
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionFormation.jsp").forward(request, response);
		
	}
}
