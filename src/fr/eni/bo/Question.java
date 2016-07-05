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
public class Question {
	@DE(isPrimary=true)
	private int id;
	@DE(field="THEME_id")
	private Theme theme;
	private String enonce;
	private String type;
	private String image;
	private List<Reponse> listReponse = new ArrayList<Reponse>();
	/**
	 * Constructeur.
	 */
	public Question() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur.
	 * @param id
	 * @param enonce
	 * @param type
	 * @param image
	 */
	public Question(int id, Theme theme, String enonce, String type, String image) {
		this.id = id;
		this.theme = theme;
		this.enonce = enonce;
		this.type = type;
		this.image = image;
	}
	
	public void addReponse(Reponse reponse){
		listReponse.add(reponse);
	}
	
	public void removeReponse(int i){
		listReponse.remove(i);
	}


	/**
	 * Getter pour listReponse.
	 * @return the listReponse
	 */
	public List<Reponse> getListReponse() {
		return listReponse;
	}

	/**
	 * Setter pour listReponse 
	 * @param listReponse the listReponse to set
	 */
	public void setListReponse(List<Reponse> listReponse) {
		this.listReponse = listReponse;
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
	 * Getter pour enonce.
	 * @return the enonce
	 */
	public String getEnonce() {
		return enonce;
	}

	/**
	 * Setter pour enonce 
	 * @param enonce the enonce to set
	 */
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	/**
	 * Getter pour type.
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter pour type 
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter pour image.
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Setter pour image 
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
}
