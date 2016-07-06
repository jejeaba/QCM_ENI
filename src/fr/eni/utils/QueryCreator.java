/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import fr.eni.annotations.ManyToOne;
import fr.eni.annotations.OneToMany;

/**
 * @author blemaitre2015
 * @date 5 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class QueryCreator {
	
	public static <T> String select(Class<T> classe){
		String entityName = classe.getSimpleName();
		Map<String, List<Field>> map = manyQuery(classe);
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT ");
		return null;
	}
	
	public static <T> Map<String, List<Field>> manyQuery(Class<T> classe){
		String entityName = classe.getSimpleName();
		Map<String, List<Field>> map = new HashMap<String, List<Field>>();
		Field[] fields = classe.getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(ManyToOne.class)){
				map.putAll(manyQuery(field.getType()));
			}else if(field.isAnnotationPresent(OneToMany.class)){
				// Plus tard
			}else{
				merge(map, entityName, field);
			}
		}
		return map;
	}
	
	public static Map<String, List<Field>> merge(Map<String, List<Field>> map, String key, Field value){
		if(map.containsKey(key)){
			map.get(key).add(value);
		}else{
			List<Field> fields = new ArrayList<Field>();
			fields.add(value);
			map.put(key, fields);
		}
		return map;
	}
	
	public static String delete(Object obj){
		String entityName = obj.getClass().getSimpleName();
		String primaryKeyName = ReflexionUtils.getPrimaryKeyName(obj);
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("DELETE FROM ");
		sbQuery.append(entityName.toUpperCase());	
		sbQuery.append(" WHERE ").append(primaryKeyName).append(" = ?");
		return sbQuery.toString();
	}
}
