package fr.eni.servlets;

import java.io.IOException;
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

import fr.eni.bo.Formateur;
import fr.eni.bo.Formation;
import fr.eni.dal.DBAcces;
import fr.eni.utils.DynamicEntities;
import fr.eni.utils.GestionErreur;

/**
 * Servlet implementation class viewEditFormation
 */
public class viewFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewFormation() {
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
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idFormation ;
		List<Formateur> listeFormateurs;
		DynamicEntities _db = new DynamicEntities();
		
		if ("edit".equals(request.getParameter("action"))){
			idFormation = Integer.parseInt(request.getParameter("id"));
			Formation formation = _db.set(Formation.class).selectById(idFormation);
			listeFormateurs = _db.set(Formateur.class).selectAll();
			request.setAttribute("listeFormateurs", listeFormateurs);
			request.setAttribute("formation",formation );
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formation/formEditFormation.jsp").forward(request, response);
			return;
		}else if ("delete".equals(request.getParameter("action"))){
			idFormation = Integer.parseInt(request.getParameter("id"));
			Formation formation = _db.set(Formation.class).selectById(idFormation);
			request.setAttribute("formation",formation );
				
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formation/formDeleteFormation.jsp").forward(request, response);
			return;
		}else if ("add".equals(request.getParameter("action"))){
			listeFormateurs = _db.set(Formateur.class).selectAll();
			request.setAttribute("listeFormateurs", listeFormateurs);

			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formation/formAddFormation.jsp").forward(request, response);
			return; 	
		}
		
	}
}
