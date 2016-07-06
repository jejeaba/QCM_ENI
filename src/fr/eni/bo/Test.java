/**
 * Classe en charge de .
 */
package fr.eni.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.annotations.OneToMany;

/**
 * @author Administrateur
 * @date 4 juil. 2016
 * @version QCM - V1.0
 */
public class Test {
	private int id;
	private String nom;
	private int seuilAcquis;
	private int seuilEnCoursAcquis;
	@OneToMany
	private List<Section> listSection = new ArrayList<Section>();
	
	/**
	 * Constructeur.
	 */
	public Test() {}
	/**
	 * Constructeur.
	 * @param id
	 * @param nom
	 * @param temps
	 * @param seuilAcquis
	 * @param seuilEnCoursAcquis
	 * @param listSection
	 */
	public Test(int id, String nom, int seuilAcquis,
			int seuilEnCoursAcquis, List<Section> listSection) {
		this(id);
		this.nom = nom;
		
		this.seuilAcquis = seuilAcquis;
		this.seuilEnCoursAcquis = seuilEnCoursAcquis;
		this.listSection = listSection;
	}
	public Test(int id) {
		super();
		this.id = id;
	}
	
	public void addSection(Section section){
		this.listSection.add(section);
	}
	
	public void removeSection(int i){
		this.listSection.remove(i);
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
	 * Getter pour seuilAcquis.
	 * @return the seuilAcquis
	 */
	public int getSeuilAcquis() {
		return seuilAcquis;
	}
	/**
	 * Setter pour seuilAcquis 
	 * @param seuilAcquis the seuilAcquis to set
	 */
	public void setSeuilAcquis(int seuilAcquis) {
		this.seuilAcquis = seuilAcquis;
	}
	/**
	 * Getter pour seuilEnCoursAcquis.
	 * @return the seuilEnCoursAcquis
	 */
	public int getSeuilEnCoursAcquis() {
		return seuilEnCoursAcquis;
	}
	/**
	 * Setter pour seuilEnCoursAcquis 
	 * @param seuilEnCoursAcquis the seuilEnCoursAcquis to set
	 */
	public void setSeuilEnCoursAcquis(int seuilEnCoursAcquis) {
		this.seuilEnCoursAcquis = seuilEnCoursAcquis;
	}
	/**
	 * Getter pour listSection.
	 * @return the listSection
	 */
	public List<Section> getListSection() {
		return listSection;
	}
	/**
	 * Setter pour listSection 
	 * @param listSection the listSection to set
	 */
	public void setListSection(List<Section> listSection) {
		this.listSection = listSection;
	}
	
	
}
