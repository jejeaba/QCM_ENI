package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bo.Section;
import fr.eni.bo.Theme;
import fr.eni.utils.DynamicEntities;

/**
 * Servlet implementation class accueil
 */
public class gestionSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionSection() {
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
		Section section;
		int idSection;
		if("Ajouter".equals(request.getParameter("addSection"))){
			try {
				int idTheme = Integer.parseInt(request.getParameter("theme"));
				Theme theme = _db.set(Theme.class).selectById(idTheme);
				int duree = Integer.parseInt(request.getParameter("duree"));
				int nbQuestion = Integer.parseInt(request.getParameter("nbQuestion"));
				section = new Section(0,request.getParameter("nomSection"),theme,nbQuestion,duree);
				_db.set(Section.class).insert(section);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}else if("Modifier".equals(request.getParameter("editSection"))){
			idSection = Integer.parseInt(request.getParameter("idSection"));
			section = new Section();
			_db.set(Section.class).update(section);
		}else if("Supprimer".equals(request.getParameter("deleteSection"))){
			idSection = Integer.parseInt(request.getParameter("idSection"));
			section = new Section();
			Boolean ret = _db.set(Section.class).delete(section);
		}
		
		try {
			List<Section> listeSections = _db.set(Section.class).selectAll(); 
			request.setAttribute("listeSections",listeSections );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionSection.jsp").forward(request, response);
		
	}
}
