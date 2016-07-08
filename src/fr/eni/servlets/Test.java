package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bo.Formateur;
import fr.eni.bo.Formation;
import fr.eni.bo.Question;
import fr.eni.bo.Reponse;
import fr.eni.bo.Theme;
import fr.eni.utils.DynamicEntities;
import fr.eni.utils.DynamicEntities2;
import fr.eni.utils.QueryBuilder;
import fr.eni.utils.QueryCreator;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DynamicEntities2 _db = new DynamicEntities2();
		Theme theme = _db.set(Theme.class).selectById(1);
		Question question = new Question();
		question.setTheme(theme);
		Reponse reponse = new Reponse();
		reponse.setCorrect(true);
		reponse.setLibelle("Test réponse");
		question.addReponse(reponse);
		question.setEnonce("Test question");
		_db.set(Question.class).insertBeta(question);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
