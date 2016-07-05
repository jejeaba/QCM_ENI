/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

/**
 * @author blemaitre2015
 * @date 5 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class ReflexionUtils {
	
	public static <T> T constructor(Class<T> classe) throws Exception{
		Constructor<T> constructor = classe.getConstructor(null);
		return (T) constructor.newInstance(null);	
	}
	
	public static Object getPrimary(Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(field.getAnnotations().length > 0){
				DE de = (DE)field.getAnnotations()[0];
				if(de.isPrimary()){
					String methodName = "get" + StringUtils.capitalize(field.getName());
					Method[] methods = obj.getClass().getMethods();
					for (Method method : methods) {
						if(method.getName().equals(methodName)) return method.invoke(obj);
					}
					return null;
				}
			} 
		}
		return null;
	}
	
	public static Object setPrimary(Object obj, Object value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(field.getAnnotations().length > 0){
				DE de = (DE)field.getAnnotations()[0];
				if(de.isPrimary()){
					String methodName = "set" + StringUtils.capitalize(field.getName());
					Method[] methods = obj.getClass().getMethods();
					for (Method method : methods) {
						if(method.getName().equals(methodName)){
							method.invoke(obj, value);
							return obj;
						}
					}
				}
			} 
		}
		return obj;
	}
}
