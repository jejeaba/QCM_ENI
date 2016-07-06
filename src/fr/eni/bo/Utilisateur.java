/**
 * Classe en charge de .
 */
package fr.eni.bo;

import fr.eni.annotations.PrimaryKey;
import fr.eni.utils.DE;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class Utilisateur {
	@PrimaryKey
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String motdepasse;
	/**
	 * Constructeur.
	 */
	public Utilisateur() {
		super();
	}
	/**
	 * Constructeur.
	 * @param id
	 * @param nom
	 * @param prenom
	 */
	public Utilisateur(int id, String nom, String prenom,String email,String motdepasse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motdepasse = motdepasse;
	}
	/**
	 * Getter pour id.
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Setter pour id 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Getter pour nom.
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Setter pour nom 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Getter pour prenom.
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Setter pour prenom 
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * Getter pour email.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter pour email.
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter pour motdepasse.
	 * @return the motdepasse
	 */
	public String getMotdepasse() {
		return motdepasse;
	}
	/**
	 * Setter pour motdepasse.
	 * @param motdepasse the motdepasse to set
	 */
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	
	
}
