package fr.eni.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.bo.Question;
import fr.eni.bo.Theme;
import fr.eni.utils.DynamicEntities;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idQuestion = Integer.parseInt(request.getParameter("id"));
		List<Theme> listeThemes;
		Question question;
		
		DynamicEntities _db = new DynamicEntities();
		
		if ("edit".equals(request.getParameter("action"))){
			idQuestion = Integer.parseInt(request.getParameter("id"));
			try {
				question = _db.set(Question.class).selectById(idQuestion);
				listeThemes = _db.set(Theme.class).selectAll();
				request.setAttribute("listeFormateurs", listeThemes);
				request.setAttribute("question",question );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/question/formEditQuestion.jsp").forward(request, response);
			return;
		}else if ("delete".equals(request.getParameter("action"))){
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/question/formDeleteQuestion.jsp").forward(request, response);
			return;
		}else if ("add".equals(request.getParameter("action"))){
			
			try {
				listeThemes = _db.set(Theme.class).selectAll();
				request.setAttribute("listeThemes", listeThemes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/question/formAddQuestion.jsp").forward(request, response);
			return; 	
		}
	}
}
