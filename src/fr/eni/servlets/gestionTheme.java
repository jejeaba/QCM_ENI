package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.bo.Theme;
import fr.eni.utils.DynamicEntities;

/**
 * Servlet implementation class accueil
 */
public class gestionTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionTheme() {
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
		Theme theme;
		int idTheme;
		if("Ajouter".equals(request.getParameter("addTheme"))){
			try {
				theme = new Theme(0,request.getParameter("nomTheme"));
				_db.set(Theme.class).insert(theme);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}else if("Modifier".equals(request.getParameter("editTheme"))){
				
		}else if("Supprimer".equals(request.getParameter("deleteTheme"))){
			idTheme = Integer.parseInt(request.getParameter("idTheme"));
			theme = new Theme(idTheme,"");
			Boolean ret = _db.set(Theme.class).delete(theme);
		}
		
		try {
			List<Theme> listeThemes = _db.set(Theme.class).selectAll(); 
			request.setAttribute("listeThemes",listeThemes );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionTheme.jsp").forward(request, response);
		
	}
}
