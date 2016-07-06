/**
 * Classe en charge de .
 */
package fr.eni.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.utils.DE;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class Theme {
	@DE(isPrimary=true)
	private int id;
	private String nom;
	private List<Question> listQuestion = new ArrayList<Question>();
	
	/**
	 * Constructeur.
	 */
	public Theme() {}
	/**
	 * Constructeur.
	 * @param id
	 * @param nom
	 * @param listQuestion
	 */
	public Theme(int id, String nom, List<Question> listQuestion) {
		this.id = id;
		this.nom = nom;
		this.listQuestion = listQuestion;
	}
	
	private void addQuestion(Question question){
		this.listQuestion.add(question);
	}
	private void removeQuestion(int i){
		this.listQuestion.remove(i);
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
	 * Getter pour listQuestion.
	 * @return the listQuestion
	 */
	public List<Question> getListQuestion() {
		return listQuestion;
	}
	/**
	 * Setter pour listQuestion 
	 * @param listQuestion the listQuestion to set
	 */
	public void setListQuestion(List<Question> listQuestion) {
		this.listQuestion = listQuestion;
	}
	
	
	
}
