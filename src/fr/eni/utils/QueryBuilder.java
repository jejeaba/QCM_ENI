/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.eni.annotations.JoinColumn;
import fr.eni.annotations.ManyToOne;
import fr.eni.annotations.OneToMany;
import fr.eni.annotations.PrimaryKey;

/**
 * @author blemaitre2015
 * @date 6 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class QueryBuilder {	

	public static String select(Class classe, String extraQuery, Parameter...args){
		Field[] fields = classe.getDeclaredFields();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT ");
		sbQuery.append(StringUtils.join(selectQueryCreator(classe, fields, true),", "));
		sbQuery.append(" FROM ");
		sbQuery.append(fromQueryCreator(classe, fields));
		if(args.length > 0){
			sbQuery.append(whereQueryCreator(extraQuery, args));
		}
		return sbQuery.toString();
	}
	
	public static String whereQueryCreator(String extraQuery, Parameter...args){
		if(extraQuery == null) return null;
		List<String> whereQuery = new ArrayList<String>();
		for (Parameter arg : args) {
			String alias = getAlias(arg.getClasse());
			String fieldName = arg.getFieldName();
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append(alias);
			sbQuery.append(".");
			sbQuery.append(fieldName);
			sbQuery.append(" = ?");
			whereQuery.add(sbQuery.toString());
		}
		StringBuilder sbWhereQuery = new StringBuilder();
		sbWhereQuery.append(" WHERE ");
		sbWhereQuery.append(String.format(extraQuery, whereQuery.toArray()));
		return sbWhereQuery.toString();
	}
	
	public static String insert(Class classe){
		Field[] fields = classe.getDeclaredFields();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("INSERT INTO ");
		sbQuery.append(insertQueryCreator(classe, fields));
		sbQuery.append(" VALUES ");
		sbQuery.append(valuesQueryCreator(fields));
		return sbQuery.toString();
	}

	public static String update(Class classe){
		String className = classe.getSimpleName().toUpperCase();
		Field[] fields = classe.getDeclaredFields();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("UPDATE ");
		sbQuery.append(className);
		sbQuery.append(" SET ");
		sbQuery.append(updateQueryCreator(classe, fields));
		sbQuery.append(" WHERE ");
		String primaryKeyName = ReflexionUtils.getPrimaryKeyName(classe);
		sbQuery.append(primaryKeyName);
		sbQuery.append(" = ?");
		return sbQuery.toString();
	}
	
	public static String delete(Class classe){
		String className = classe.getSimpleName().toUpperCase();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("DELETE FROM ");
		sbQuery.append(className);
		sbQuery.append(" WHERE ");
		String primaryKeyName = ReflexionUtils.getPrimaryKeyName(classe);
		sbQuery.append(primaryKeyName);
		sbQuery.append(" = ?");
		return sbQuery.toString();
	}
	

	
	/*HELPERS*/
	
	private static List<String> selectQueryCreator(Class classe, Field[] fields, boolean firstPass){
		List<String> selectQuery = new ArrayList<String>();
		String alias = getAlias(classe);
		for (Field field : fields) {
			if(field.isAnnotationPresent(ManyToOne.class)){
				if(firstPass){
					Class elementClass = field.getType();
					Field[] elementFields = elementClass.getDeclaredFields();
					String dataBaseFieldName = field.getAnnotation(JoinColumn.class).name();
					selectQuery.add(rsCreator(alias, dataBaseFieldName));
					selectQuery.addAll(selectQueryCreator(elementClass, elementFields, false));
				}
			}else if(field.isAnnotationPresent(OneToMany.class)){
				if(firstPass){
					ParameterizedType genericType = (ParameterizedType) field.getGenericType();
					Class<?> elementClass = (Class<?>) genericType.getActualTypeArguments()[0];
					String elementA1ias = getAlias(elementClass);
					Field[] elementFields = elementClass.getDeclaredFields();
					String dataBaseFieldName = field.getAnnotation(JoinColumn.class).name();
					selectQuery.add(rsCreator(elementA1ias, dataBaseFieldName));
					selectQuery.addAll(selectQueryCreator(elementClass, elementFields, false));
				}
			}else{
				selectQuery.add(rsCreator(alias, field.getName()));
			}
		}
		return selectQuery;
	}
	
	private static String fromQueryCreator(Class classe, Field[] fields){
		boolean isFirst = true;
		// StringBuilder
		StringBuilder sbQuery = new StringBuilder();
		String className = classe.getSimpleName().toUpperCase();
		String alias = getAlias(classe);
		for (Field field : fields) {
			if(field.isAnnotationPresent(ManyToOne.class)){
				// Current class
				String foreignKeyName = field.getAnnotation(JoinColumn.class).name();
				// Foreign class
				Class elementClass = field.getType();
				String elementClassName = elementClass.getSimpleName().toUpperCase();
				String elementAlias = getAlias(elementClass);
				String elementPrimaryKeyName = ReflexionUtils.getPrimaryKeyName(elementClass);
				// Build
				if(isFirst){
					sbQuery.append(aliasBuilder(className, alias));
					isFirst = false;
				}
				sbQuery.append(" INNER JOIN ");
				sbQuery.append(aliasBuilder(elementClassName, elementAlias));
				sbQuery.append(" ON ");
				sbQuery.append(dotBuilder(alias, foreignKeyName));
				sbQuery.append(" = ");
				sbQuery.append(dotBuilder(elementAlias, elementPrimaryKeyName));
			}else if(field.isAnnotationPresent(OneToMany.class)){
				// Current class
				String primaryKeyName = ReflexionUtils.getPrimaryKeyName(classe);
				// Foreign class
				ParameterizedType genericType = (ParameterizedType) field.getGenericType();
				Class<?> elementClass = (Class<?>) genericType.getActualTypeArguments()[0];
				String elementClassName = elementClass.getSimpleName().toUpperCase();
				String elementAlias = getAlias(elementClass);
				String elementForeignKeyName = field.getAnnotation(JoinColumn.class).name();
				// Build
				if(isFirst){
					sbQuery.append(aliasBuilder(className, alias));
					isFirst = false;
				}
				sbQuery.append(" LEFT JOIN ");
				sbQuery.append(aliasBuilder(elementClassName, elementAlias));
				sbQuery.append(" ON ");
				sbQuery.append(dotBuilder(alias, primaryKeyName));
				sbQuery.append(" = ");
				sbQuery.append(dotBuilder(elementAlias, elementForeignKeyName));
			}else{
				
			}
		}
		return sbQuery.toString();
	}
	
	private static String updateQueryCreator(Class classe, Field[] fields){
		List<String> updateQuery = new ArrayList<String>();
		for (Field field : fields) {
			if(field.isAnnotationPresent(ManyToOne.class)){
				String fieldName = field.getAnnotation(JoinColumn.class).name();
				StringBuilder sbQuery = new StringBuilder();
				sbQuery.append(fieldName).append(" = ?");
				updateQuery.add(sbQuery.toString());
			}else if(!field.isAnnotationPresent(PrimaryKey.class) && 
					 !field.isAnnotationPresent(OneToMany.class)){
				StringBuilder sbQuery = new StringBuilder();
				sbQuery.append(field.getName()).append(" = ?");
				updateQuery.add(sbQuery.toString());
			}
		}
		return StringUtils.join(updateQuery,", ");
	}
	
	private static String insertQueryCreator(Class classe, Field[] fields){
		List<String> insertQuery = new ArrayList<String>();
		for (Field field : fields) {
			if(field.isAnnotationPresent(ManyToOne.class)){
				insertQuery.add(field.getAnnotation(JoinColumn.class).name());
			}else if(!field.isAnnotationPresent(PrimaryKey.class) && 
					 !field.isAnnotationPresent(OneToMany.class)){
				insertQuery.add(field.getName());
			}
		}
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(classe.getSimpleName().toUpperCase());
		sbQuery.append(" (");
		sbQuery.append(StringUtils.join(insertQuery,", "));
		sbQuery.append(")");
		return sbQuery.toString();
	}
	
	private static <T> String valuesQueryCreator(Field[] fields){
		List<String> valuesQuery = new ArrayList<String>();
		for (Field field : fields) {
			if(	field.isAnnotationPresent(ManyToOne.class) 
				|| (!field.isAnnotationPresent(PrimaryKey.class)
				&& !field.isAnnotationPresent(OneToMany.class))){
				valuesQuery.add("?");
			}
		}
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("(");
		sbQuery.append(StringUtils.join(valuesQuery,", "));
		sbQuery.append(")");
		return sbQuery.toString();
	}
	
	public static String getAlias(Class classe){
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
