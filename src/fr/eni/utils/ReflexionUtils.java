/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.sun.corba.se.impl.naming.cosnaming.InternalBindingValue;

import fr.eni.annotations.OneToMany;
import fr.eni.annotations.PrimaryKey;
import sun.security.util.BigInt;

/**
 * @author blemaitre2015
 * @date 5 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class ReflexionUtils {
	
	public static <T> T constructor(Class<T> classe) throws Exception{
		return classe.getConstructor(null).newInstance(null);	
	}
	
	public static Field getPrimaryKey(Object obj){
		Field[] fields = getFields(obj);
		for (Field field : fields) {
			if(field.isAnnotationPresent(PrimaryKey.class)){
				return field;
			} 
		}
		return null;
	}
	
	public static String getPrimaryKeyName(Object obj){
		Field primaryKey = getPrimaryKey(obj);
		if(primaryKey == null) return null;		
		return primaryKey.getName();
	}
	
	public static Object getPrimary(Object obj) throws Exception{
		Field primaryKey = getPrimaryKey(obj);
		primaryKey.setAccessible(true);
		return primaryKey.get(obj);
	}	
	
	public static Object setPrimary(Object obj, Object value) throws Exception{
		Field primaryKey = getPrimaryKey(obj);
		primaryKey.setAccessible(true);
		primaryKey.set(obj, value);
		return obj;
	}
	
	public static Field[] getFields(Object obj){
		return obj.getClass().getDeclaredFields();
	}
	/*
	public static List<String> getDataBaseFields(Object obj){
		Field[] fields = getFields(obj);
		for (Field field : fields) {
			if(field.isAnnotationPresent(OneToMany.class)){
				
			}
		}
	}
	*/
	
	public static boolean isCollection(Type type){
		return Collection.class.isAssignableFrom((Class<?>) type);
	}
	
	public static Object parserExceptions(Object value){
		if(BigDecimal.class.isInstance(value)){
			return ((BigDecimal)value).intValue();
		}
		return value;
	}
}
