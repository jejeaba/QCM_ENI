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



import fr.eni.bo.Question;
import fr.eni.bo.Theme;
import fr.eni.utils.DynamicEntities;
import fr.eni.utils.DynamicEntities2;
import fr.eni.utils.GestionErreur;

/**
 * Servlet implementation class viewEditFormation
 */
public class viewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewQuestion() {
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
		int idQuestion = Integer.parseInt(request.getParameter("id"));
		List<Theme> listeThemes;
		Question question;
		
		DynamicEntities _db = new DynamicEntities();
		DynamicEntities2 _db2 = new DynamicEntities2();
		if ("edit".equals(request.getParameter("action"))){
			idQuestion = Integer.parseInt(request.getParameter("id"));
			
			question = _db2.set(Question.class).selectById(idQuestion);
			listeThemes = _db.set(Theme.class).selectAll();
			request.setAttribute("listeThemes", listeThemes);
			request.setAttribute("question", question);
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/question/formEditQuestion.jsp").forward(request, response);
			return;
		}else if ("delete".equals(request.getParameter("action"))){
			idQuestion = Integer.parseInt(request.getParameter("id"));
			question = _db.set(Question.class).selectById(idQuestion);
			request.setAttribute("question",question );
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/question/formDeleteQuestion.jsp").forward(request, response);
			return;
		}else if ("add".equals(request.getParameter("action"))){
			listeThemes = _db.set(Theme.class).selectAll();
			request.setAttribute("listeThemes", listeThemes);
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/question/formAddQuestion.jsp").forward(request, response);
			return; 	
		}		
	}
}
