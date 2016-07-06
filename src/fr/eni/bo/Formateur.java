/**
 * Classe en charge de .
 */
package fr.eni.bo;

import fr.eni.utils.DE;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
@HeritsFrom(classe="Personnel")
public class Formateur extends Personnel{
	/**
	 * Constructeur.
	 */
	public Formateur() {
		super();
	}
	public Formateur(int id, String nom, String prenom,String email,String motdepasse) {
		super(id,nom,prenom,email,motdepasse);
	}
}
