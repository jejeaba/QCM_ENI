/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;

import fr.eni.annotations.JoinColumn;
import fr.eni.annotations.ManyToOne;

/**
 * @author blemaitre2015
 * @date 6 juil. 2016
 * @version QCM_ENI - V1.0
 *
 */
public class ObjectBuilder {

	public static <T> T rs2Object(Class<T> classe, ResultSet rs){
		Field[] fields = classe.getFields();
		try {
			T object = classe.newInstance();
			for (Field field : fields) {
				field.setAccessible(true);
				if(field.isAnnotationPresent(ManyToOne.class)){
					String dataBaseFieldName = field.getAnnotation(JoinColumn.class).name();
					//field.set(object, rs.getObject(dataBaseFieldName));
				}else if(field.isAnnotationPresent(ManyToOne.class)){
					
				}else{
					field.set(object, rs.getObject(field.getName()));
				}
			}
			return object;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
