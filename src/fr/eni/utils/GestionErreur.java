/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrateur
 * @date 7 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class GestionErreur {

	/**
	 * Méthode en charge de rediriger les erreurs.
	 * @param e Exception à traiter
	 * @param request requete
	 * @param response reponse
	 * @throws ServletException Exception de type Servlet
	 * @throws IOException Exception de type IO.
	 */
	public static void redirectionErreur(Exception e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Placer l'objet représentant l'exception dans le contexte de requete
		request.setAttribute("erreur", e);
		// Passer la main à la page de présentation des erreurs
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/erreur/erreur.jsp"); 
		dispatcher.forward(request, response);
		
	}
}
