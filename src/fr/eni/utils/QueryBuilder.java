/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.eni.annotations.JoinColumn;
import fr.eni.annotations.ManyToOne;
import fr.eni.annotations.OneToMany;

/**
 * @author blemaitre2015
 * @date 6 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class QueryBuilder {

	public static String select(Class classe){
		Field[] fields = classe.getDeclaredFields();
		List<String> selectQuery = new ArrayList<String>();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT ");
		sbQuery.append(StringUtils.join(selectQueryCreator(classe, fields),", "));
		sbQuery.append(" FROM ");
		sbQuery.append(StringUtils.join(fromQueryCreator(classe, fields, true)," INNER JOIN "));
		return sbQuery.toString();
	}
	
	private static List<String> selectQueryCreator(Class classe, Field[] fields){
		List<String> selectQuery = new ArrayList<String>();
		String alias = getAlias(classe);
		for (Field field : fields) {
			if(field.isAnnotationPresent(ManyToOne.class)){
				Class elementClass = field.getType();
				Field[] elementFields = elementClass.getDeclaredFields();
				selectQuery.addAll(selectQueryCreator(elementClass, elementFields));
			}else if(field.isAnnotationPresent(OneToMany.class)){
				
			}else{
				selectQuery.add(rsCreator(alias, field.getName()));
			}
		}
		return selectQuery;
	}
	
	private static List<String> fromQueryCreator(Class classe, Field[] fields, boolean isFirst){
		List<String> fromQuery = new ArrayList<String>();
		String className = classe.getSimpleName().toUpperCase();
		String alias = getAlias(classe);
		for (Field field : fields) {
			if(field.isAnnotationPresent(ManyToOne.class)){
				// Current class
				String foreignKeyName = field.getAnnotation(JoinColumn.class).name();
				// Foreign class
				Class elementClass = field.getType();
				String elementClassName = elementClass.getSimpleName().toUpperCase();
				Field[] elementFields = elementClass.getDeclaredFields();
				String elementAlias = getAlias(elementClass);
				String elementPrimaryKeyName = ReflexionUtils.getPrimaryKeyName(elementClass);
				// Build
				if(isFirst){
					fromQuery.add(aliasBuilder(className, alias));
					isFirst = false;
				}
				StringBuilder sbQuery = new StringBuilder();
				sbQuery.append(aliasBuilder(elementClassName, elementAlias));
				sbQuery.append(" ON ");
				sbQuery.append(dotBuilder(alias, foreignKeyName));
				sbQuery.append(" = ");
				sbQuery.append(dotBuilder(elementAlias, elementPrimaryKeyName));
				fromQuery.add(sbQuery.toString());
				fromQuery.addAll(fromQueryCreator(elementClass, elementFields, isFirst));
			}else if(field.isAnnotationPresent(OneToMany.class)){
				
			}else{
				
			}
		}
		return fromQuery;
	}
	
	private static String getAlias(Class classe){
		return classe.getSimpleName().toLowerCase();
	}
	
	private static String dotBuilder(String firstValue, String secondValue){
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(firstValue).append(".").append(secondValue);
		return sbQuery.toString();
	}
	
	private static String aliasBuilder(String firstValue, String secondValue){
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(firstValue).append(" AS ").append(secondValue);
		return sbQuery.toString();
	}
	
	private static String rsCreator(String firstValue, String secondValue){
		String result = dotBuilder(firstValue, secondValue);
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(firstValue).append("_").append(secondValue);
		return aliasBuilder(result, sbQuery.toString());
	}
	
}
