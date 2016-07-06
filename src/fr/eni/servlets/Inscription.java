package fr.eni.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.utils.Validation;

/**
 * Servlet implementation class inscription
 */
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
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
			HttpServletResponse response){
		try {
			if("submit".equals(request.getParameter("submit"))){
				String email = request.getParameter("email");
				String nom = request.getParameter("nom");
				String password = request.getParameter("password");
				String password2 = request.getParameter("password2");
				if(email.trim() == "" || nom.trim() == "" || password.trim() == "" || password2.trim() == ""){
					request.setAttribute("erreur", "Tous les champs doivent être renseignés");
				}else if(!Validation.isValidEmailAddress(email)){
					request.setAttribute("erreurEmail", "L'email est incorrect");
				}else if(!password.equals(password2)){
					request.setAttribute("erreur", "Les mots de passe doivent être identitiques");
				}
				
				// dao.inscription(email, motdepasse);
				// request.setAttribute("erreur", "Le login ou le mot de passe est faux");
				// getServletContext().getRequestDispatcher("/accueil").forward(request, response);
				
			}
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/inscription.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
