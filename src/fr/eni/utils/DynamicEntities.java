/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.eni.dal.DBAcces;

/**
 * @author blemaitre2015
 * @date 4 juil. 2016
 * @version QCM_ENI - V1.0
 * @param <T>
 *
 */
public class DynamicEntities<T> {

	private Class<?> entity;
	
	public DynamicEntities(Class<?> entity){
		this.entity = entity;
	}
	
	private List<String> getFields(){
		List<String> returnData  = new ArrayList<String>();
		List<Method> methods = setMethods();
		for (java.lang.reflect.Method method : methods) {
			returnData.add(method.getName().replace("set", "").toLowerCase());
		}
		return returnData;
	}
	
	private List<Method> setMethods(){
		List<Method> returnData  = new ArrayList<Method>();
		Method[] methods = this.entity.getMethods();
		for (java.lang.reflect.Method method : methods) {
			if(method.getName().startsWith("set")){
				returnData.add(method);
			}
		}
		return returnData;
	}
	/*
	public Object getValue(ResultSet rs, Type type){
		if(int.class.equals(type.getClass())){
			return rs.get
		}
	}
	*/
	public List<T> selectAll() throws Exception{
		Statement cmd = null;
		List<T> returnData = new ArrayList<T>();
		List<Method> methods = this.setMethods();
		List<String> fields = this.getFields();
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(StringUtils.join(fields, ", "));
		query.append(" FROM ").append(this.entity.getSimpleName());
		String test = query.toString();
		try {
			cmd = DBAcces.getConnection().createStatement();
			ResultSet rs = cmd.executeQuery(query.toString());
			while (rs.next()) {
				Constructor<?> constructor = this.entity.getConstructor(null);
				T obj = (T) constructor.newInstance(null);
				int i = 0;
				for (Method method : methods) {
					method.invoke(obj, rs.getObject(fields.toArray()[i].toString()));
					i++;
				}
				returnData.add(obj);
			}
			return returnData;
		} catch (SQLException e) {
			throw new Exception(String.format("Impossible d'effectuer une requête SELECT ALL sur l'entitée %1s. Erreur : %2s", this.entity.getSimpleName(), e.getMessage()));
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}		
	}
	
}
