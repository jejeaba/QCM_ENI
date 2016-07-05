package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bo.Formateur;
import fr.eni.bo.Formation;
import fr.eni.utils.DynamicEntities;

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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Methode en charge de .
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DynamicEntities _db = new DynamicEntities();
		if("Ajouter".equals(request.getParameter("addFormation"))){
			Formateur formateur;
			try {
				formateur = _db.set(Formateur.class).selectById(Integer.parseInt(request.getParameter("formateur")));
				Formation formation = new Formation(0,request.getParameter("nomFormation"),formateur);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			List<Formateur> listeFormateurs = _db.set(Formateur.class).selectAll();
			List<Formation> listeFormations = _db.set(Formation.class).selectAll();
			request.setAttribute("listeFormateurs",listeFormateurs );
			request.setAttribute("listeFormations",listeFormations );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionFormation.jsp").forward(request, response);
		
	}
}
