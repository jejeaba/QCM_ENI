/**
 * Classe en charge de .
 */
package fr.eni.bo;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class Resultat {
	private int score;
	private Eleve eleve;
	private Test test;
	/**
	 * Constructeur.
	 */
	public Resultat() {}
	/**
	 * Constructeur.
	 * @param score
	 */
	public Resultat(int score) {
		this.score = score;
	}
	/**
	 * Getter pour score.
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Setter pour score 
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * Getter pour eleve.
	 * @return the eleve
	 */
	public Eleve getEleve() {
		return eleve;
	}
	/**
	 * Setter pour eleve 
	 * @param eleve the eleve to set
	 */
	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
	/**
	 * Getter pour test.
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}
	/**
	 * Setter pour test 
	 * @param test the test to set
	 */
	public void setTest(Test test) {
		this.test = test;
	}
	
	
}
