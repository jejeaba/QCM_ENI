package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.bo.Theme;
import fr.eni.utils.DynamicEntities;
import fr.eni.utils.GestionErreur;
import fr.eni.utils.DynamicEntities2;

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
		DynamicEntities2 _db2 = new DynamicEntities2();
		Theme theme;
		int idTheme;
		String success=null;
		String error=null;
		if("Ajouter".equals(request.getParameter("addTheme"))){
			theme = new Theme(0,request.getParameter("nomTheme"));
			if( _db.set(Theme.class).insert(theme)){
				success = "Le theme à bien été ajouté !";
			}else{
				error = "Le theme n'a pas été ajouté !";
			}
		}else if("Modifier".equals(request.getParameter("editTheme"))){
			idTheme = Integer.parseInt(request.getParameter("idTheme"));
			theme = new Theme(idTheme,request.getParameter("nomTheme"));
			if(_db.set(Theme.class).update(theme)){
				success = "Le theme à bien été modifié !";
			}else{
				error = "Le theme n'a pas été modifié !";
			}
			
		}else if("Supprimer".equals(request.getParameter("deleteTheme"))){
			idTheme = Integer.parseInt(request.getParameter("idTheme"));
			theme = new Theme(idTheme,"");
			if( _db.set(Theme.class).delete(theme)){
				success = "Le theme à bien été supprimé !";
			}else{
				error = "Le theme n'a pas été supprimé !";
			}
		}
		List<Theme> listeThemes = _db2.set(Theme.class).selectAll(); 
		request.setAttribute("listeThemes",listeThemes );
		request.setAttribute("success", success);
		request.setAttribute("error", error);
		
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionTheme.jsp").forward(request, response);
		
	}
}
