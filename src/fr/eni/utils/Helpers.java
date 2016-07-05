/**
 *  Classe en charge de.
 */
package fr.eni.utils;

/**
 * @author blemaitre2015
 * @date 4 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class Helpers {
	
	public static boolean isArray(Object obj)
	{
		return obj!=null && obj.getClass().isArray();
	}
}
