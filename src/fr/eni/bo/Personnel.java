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
public abstract class Personnel extends Utilisateur{
	/**
	 * Constructeur.
	 */
	public Personnel() {
		super();
	}
	public Personnel(int id, String nom, String prenom,String email,String motdepasse) {
		super(id,nom,prenom,email,motdepasse);
	}
	
	public void inscription(Eleve eleve){
	}
}
