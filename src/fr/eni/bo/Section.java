/**
 * Classe en charge de .
 */
package fr.eni.bo;

import fr.eni.annotations.JoinColumn;
import fr.eni.annotations.ManyToOne;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class Section {
	private int id;
	private String nom;
	@ManyToOne()
	@JoinColumn(name="THEME_id")
	private Theme theme;
	private int nb_questions;
	private int duree;
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
	public Section(int id, int numeroSection, int nb_questions) {
		this.id = id;
		this.nb_questions = nb_questions;
	}
	
	/**
	 * Constructeur .
	 * @param id
	 * @param nom
	 * @param theme
	 * @param nbQuestion
	 * @param duree
	 */
	public Section(int id, String nom, Theme theme, int nb_questions, int duree) {
		super();
		this.id = id;
		this.nom = nom;
		this.theme = theme;
		this.nb_questions = nb_questions;
		this.duree = duree;
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
	 * Getter pour nbQuestion.
	 * @return the nbQuestion
	 */
	public int getNb_Questions() {
		return nb_questions;
	}
	/**
	 * Setter pour nbQuestion 
	 * @param nbQuestion the nbQuestion to set
	 */
	public void setNb_Questions(int nb_questions) {
		this.nb_questions = nb_questions;
	}
	/**
	 * Getter pour nom.
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Setter pour nom.
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Getter pour theme.
	 * @return the theme
	 */
	public Theme getTheme() {
		return theme;
	}
	/**
	 * Setter pour theme.
	 * @param theme the theme to set
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	/**
	 * Getter pour duree.
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}
	/**
	 * Setter pour duree.
	 * @param duree the duree to set
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	
}
