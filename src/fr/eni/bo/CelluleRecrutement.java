/**
 * Classe en charge de .
 */
package fr.eni.bo;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class CelluleRecrutement extends Personnel{
	/**
	 * Constructeur.
	 */
	public CelluleRecrutement() {
		super();
	}
	
	public CelluleRecrutement(int id, String nom, String prenom,String email,String motdepasse) {
		super(id,nom,prenom,email,motdepasse);
	}
}
