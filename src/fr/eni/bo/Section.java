/**
 * Classe en charge de .
 */
package fr.eni.bo;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class Section {
	private int id;
	private int numeroSection;
	private int nbQuestion;
	/**
	 * Constructeur.
	 */
	public Section() {}
	/**
	 * Constructeur.
	 * @param id
	 * @param numeroSection
	 * @param nbQuestion
	 */
	public Section(int id, int numeroSection, int nbQuestion) {
		this.id = id;
		this.numeroSection = numeroSection;
		this.nbQuestion = nbQuestion;
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
	 * Getter pour numeroSection.
	 * @return the numeroSection
	 */
	public int getNumeroSection() {
		return numeroSection;
	}
	/**
	 * Setter pour numeroSection 
	 * @param numeroSection the numeroSection to set
	 */
	public void setNumeroSection(int numeroSection) {
		this.numeroSection = numeroSection;
	}
	/**
	 * Getter pour nbQuestion.
	 * @return the nbQuestion
	 */
	public int getNbQuestion() {
		return nbQuestion;
	}
	/**
	 * Setter pour nbQuestion 
	 * @param nbQuestion the nbQuestion to set
	 */
	public void setNbQuestion(int nbQuestion) {
		this.nbQuestion = nbQuestion;
	}
	
	
}
