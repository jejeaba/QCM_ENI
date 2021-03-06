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
public class Question {
	@PrimaryKey
	private int id;
	@ManyToOne()
	@JoinColumn(name="THEME_id")
	private Theme theme;
	private String enonce;
	private boolean type;
	private String image;
	@OneToMany()
	@JoinColumn(name="QUESTION_id")
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
	public Question(int id, Theme theme, String enonce, boolean type, String image) {
		this.id = id;
		this.theme = theme;
		this.enonce = enonce;
		this.type = type;
		this.image = image;
	}
	/**
	 * 
	 * Constructeur .
	 * @param id
	 */
	public Question(int id){
		this.id = id;
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
	public boolean getType() {
		return type;
	}
	
	public String getTypeFormat(){
		if(this.type){
			return "Multiple";
		}else{
			return "Unique";
		}
	}

	/**
	 * Setter pour type 
	 * @param type the type to set
	 */
	public void setType(boolean type) {
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
