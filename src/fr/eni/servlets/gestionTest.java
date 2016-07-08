package fr.eni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bo.Section;
import fr.eni.bo.Test;
import fr.eni.utils.DynamicEntities;
import fr.eni.utils.DynamicEntities2;
import fr.eni.utils.GestionErreur;

/**
 * Servlet implementation class accueil
 */
public class gestionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionTest() {
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
		Test test;
		Section section;
		String nomTest;
		int seuilAcquis;
		int seuilEnCoursAcquis;
		int idTest;
		String success=null;
		String error=null;
		if("Ajouter".equals(request.getParameter("addTest"))){

			nomTest = request.getParameter("nomTest");
			seuilAcquis = Integer.parseInt(request.getParameter("seuilAcquis"));
			seuilEnCoursAcquis = Integer.parseInt(request.getParameter("seuilEnCoursAcquis"));
			List<Section> listeSections= new ArrayList<Section>() ;
			String[] tabSectionIds = request.getParameterValues("listeSections");
			for (String tabSectionId : tabSectionIds) {
				section = _db.set(Section.class).selectById(Integer.parseInt(tabSectionId));
				listeSections.add(section);
			}
			
			test = new Test(0,nomTest,seuilAcquis,seuilEnCoursAcquis,listeSections);
			if(_db.set(Test.class).insert(test)){
				success = "Le test à bien été ajoutée !";
			}else{
				error = "Le test n'a pas été ajoutée !";
			}
	
		}else if("Modifier".equals(request.getParameter("editTest"))){
			idTest = Integer.parseInt(request.getParameter("idTest"));
			test = new Test();
			if(_db.set(Test.class).update(test)){
				success = "Le test à bien été modifié !";
			}else{
				error = "Le test n'a pas été modifié !";
			}
		}else if("Supprimer".equals(request.getParameter("deleteTest"))){
			idTest = Integer.parseInt(request.getParameter("idTest"));
			test = new Test(idTest);
			if(_db.set(Test.class).delete(test)){
				success = "Le test à bien été supprimé !";
			}else{
				error = "Le test n'a pas été supprimé !";
			}
		}
		
		
		List<Test> listeTests = _db.set(Test.class).selectAll(); 
		request.setAttribute("listeTests",listeTests );
		request.setAttribute("success", success);
		request.setAttribute("error", error);
		
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionTest.jsp").forward(request, response);
		
	}
}
