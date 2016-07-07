/**
 *  Classe en charge de.
 */
package fr.eni.utils;

/**
 * @author blemaitre2015
 * @date 7 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class Parameter {

	private Class classe;
	private String fieldName;
	private Object value;
	
	public Parameter(Class classe, String fieldName, Object value){
		this.classe = classe;
		this.fieldName = fieldName;
		this.value = value;
	}

	/**
	 * Getter pour classe.
	 * @return the classe
	 */
	public Class getClasse() {
		return classe;
	}

	/**
	 * Getter pour fieldName.
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Getter pour value.
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	
	
}
