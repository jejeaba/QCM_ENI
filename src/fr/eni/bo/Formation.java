/**
 * Classe en charge de .
 */
package fr.eni.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.annotations.JoinColumn;
import fr.eni.annotations.ManyToOne;
import fr.eni.annotations.OneToMany;
import fr.eni.annotations.PrimaryKey;
import fr.eni.utils.DE;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class Formation {
	@PrimaryKey
	private int id;
	private String nom;
	@OneToMany
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	@ManyToOne
	@JoinColumn(name="FORMATEUR_id")
	private Formateur responsable;

	
	/**
	 * Constructeur.
	 */
	public Formation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur.
	 * @param id
	 * @param nom
	 * @param stagiaires
	 * @param responsable
	 */
	public Formation(int id, String nom, List<Stagiaire> stagiaires,
			Formateur responsable) {
		this(id,nom,responsable);
		this.stagiaires = stagiaires;
	}
	/**
	 * Constructeur .
	 * @param id
	 * @param nom
	 * @param responsable
	 */
	public Formation(int id, String nom, Formateur responsable) {
		this.id = id;
		this.nom = nom;
		this.responsable = responsable;
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
	 * Getter pour stagiaires.
	 * @return the stagiaires
	 */
	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	/**
	 * Setter pour stagiaires 
	 * @param stagiaires the stagiaires to set
	 */
	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	/**
	 * Getter pour responsable.
	 * @return the responsable
	 */
	public Formateur getResponsable() {
		return responsable;
	}

	/**
	 * Setter pour responsable 
	 * @param responsable the responsable to set
	 */
	public void setResponsable(Formateur responsable) {
		this.responsable = responsable;
	}
	
	
}
