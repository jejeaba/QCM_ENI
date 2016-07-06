/**
 * Classe en charge de .
 */
package fr.eni.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public abstract class Eleve extends Utilisateur {
	private List<Resultat> resultats = new ArrayList<Resultat>();
	/**
	 * Constructeur.
	 */
	public Eleve() {
		super();
	}
	/**
	 * Constructeur.
	 * @param listResult
	 */
	public Eleve(List<Resultat> listResult) {
		this.resultats = listResult;
		
	}
	
	public void addResultat(Resultat resultat){
		this.resultats.add(resultat);
	}
	
	public Resultat getResultat(Test test){
		return null;
	}
	
	/**
	 * Getter pour resultats.
	 * @return the resultats
	 */
	public List<Resultat> getResultats() {
		return resultats;
	}
	/**
	 * Setter pour resultats 
	 * @param resultats the resultats to set
	 */
	public void setResultats(List<Resultat> resultats) {
		this.resultats = resultats;
	}
	
	
}
