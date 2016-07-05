/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author blemaitre2015
 * @date 5 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class SQLUtils {
	
	public static String queryMarkers(boolean brackets, int count){
		List<String> markers = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			markers.add("?");
		}
		String strMarkers = StringUtils.join(markers,", ");
		if (brackets) return strMarkers;
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(strMarkers).append(")");
		return sb.toString();
	}
}
