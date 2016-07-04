/**
 * Classe en charge de .
 */
package fr.eni.bo;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class Reponse {
	private int id;
	private String libelle;
	private boolean correct;
	
	/**
	 * Constructeur par default.
	 */
	public Reponse() {}
	/**
	 * Constructeur.
	 * @param id
	 * @param libelle
	 * @param correct
	 */
	public Reponse(int id, String libelle, boolean correct) {
		this.id = id;
		this.libelle = libelle;
		this.correct = correct;
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
	 * Getter pour libelle.
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * Setter pour libelle 
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * Getter pour correct.
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}
	/**
	 * Setter pour correct 
	 * @param correct the correct to set
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	
}
