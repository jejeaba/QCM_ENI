package fr.eni.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if("Ajouter".equals(request.getParameter("addFormation"))){
			int formateurId = Integer.parseInt(request.getParameter("formateur"));
			try {
				Utilisateur utilisateur = _db.set(Utilisateur.class).selectById(formateurId);
				
				formation = new Formation(0,request.getParameter("nomFormation"),(Formateur) utilisateur);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		PreparedStatement cmd = null;
		List<Formation> listeFormations = new ArrayList<Formation>();
		String query = "  SELECT * FROM getAllFormationWithResponsable";
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			//cmd.setInt(1, 1);
			//List<Object> returnData = new ArrayList<Object>();
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				formateur = new Formateur(
						rs.getInt("FORMATEUR_id"),
						rs.getString("formateur_nom"),
						rs.getString("formateur_prenom"),
						rs.getString("formateur_email"),
						rs.getString("formateur_motdepasse")
						);
				formation = new Formation(
						rs.getInt("id"),
						rs.getString("nom"),
						formateur);
				listeFormations.add(formation);
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}		
		
		try {
			request.setAttribute("listeFormations",listeFormations );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionFormation.jsp").forward(request, response);
		
	}
}
