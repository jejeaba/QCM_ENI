package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bo.CelluleRecrutement;
import fr.eni.bo.Formateur;
import fr.eni.dal.CelluleRecrutementDAO;
import fr.eni.dal.FormateurDAO;
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
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response){
		try {
			if("submit".equals(request.getParameter("submit"))){
				Boolean erreur = false;
				String email = request.getParameter("email");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String password = request.getParameter("password");
				String password2 = request.getParameter("password2");
				String role = request.getParameter("role");
				if(email.trim() == "" || nom.trim() == "" || password.trim() == "" || password2.trim() == "" || role == ""){
					request.setAttribute("erreur", "Tous les champs doivent être renseignés");
					erreur = true;
				}else if(!Validation.isValidEmailAddress(email)){
					request.setAttribute("erreurEmail", "L'email est incorrect");
					erreur = true;
				}else if(!password.equals(password2)){
					request.setAttribute("erreur", "Les mots de passe doivent être identitiques");
					erreur = true;
				}
				if(!erreur){
					if("formateur".equals(role)){
						Formateur formateur = new Formateur(0, nom, prenom, email, password);
						try {
							FormateurDAO.addFormateur(formateur);
							request.setAttribute("success", "Inscription réussi");
						} catch (SQLException e) {
							e.printStackTrace();
							request.setAttribute("erreur", "Erreur lors de l'insertion d'un formateur");
						}
					}else if("cellulerecrutement".equals(role)){
						CelluleRecrutement cellule = new CelluleRecrutement(0, nom, prenom, email, password);
						try {
							CelluleRecrutementDAO.addCelluleRecrutement(cellule);
							request.setAttribute("success", "Inscription réussi");
						} catch (SQLException e) {
							e.printStackTrace();
							request.setAttribute("erreur", "Erreur lors de l'insertion d'un formateur");
						}
					}
				}
			}
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/inscription.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
