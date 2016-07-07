package fr.eni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bo.Section;
import fr.eni.bo.Test;
import fr.eni.utils.DynamicEntities;

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
		Test test;
		Section section;
		String nomTest;
		int seuilAcquis;
		int seuilEnCoursAcquis;
		int idTest;
		if("Ajouter".equals(request.getParameter("addTest"))){
			try {
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
				_db.set(Test.class).insert(test);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}else if("Modifier".equals(request.getParameter("editTest"))){
			idTest = Integer.parseInt(request.getParameter("idTest"));
			test = new Test();
			_db.set(Test.class).update(test);
		}else if("Supprimer".equals(request.getParameter("deleteTest"))){
			idTest = Integer.parseInt(request.getParameter("idTest"));
			test = new Test(idTest);
			Boolean ret = _db.set(Test.class).delete(test);
		}
		
		try {
			List<Test> listeTests = _db.set(Test.class).selectAll(); 
			request.setAttribute("listeTests",listeTests );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionTest.jsp").forward(request, response);
		
	}
}
