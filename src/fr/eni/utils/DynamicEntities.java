/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import fr.eni.dal.DBAcces;

/**
 * @author blemaitre2015
 * @date 4 juil. 2016
 * @version QCM_ENI - V1.0
 * @param <T>
 *
 */
public class DynamicEntities {

	private Class<?> entity;

	public DynamicEntities(){
	}
	
	public DynamicEntities(Class<?> entity){
		this.entity = entity;
	}
	
	public DynamicEntities set(Class<?> entity){
		this.entity = entity;
		return this;
	}
	
	private Object getPrimary(Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
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
	
	private Object setPrimary(Object obj, Object value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
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
	
	private List<String> getFields(){
		return getFields(true);
	}
	
	private List<String> getFields(boolean withPrimary){
		List<String> returnData  = new ArrayList<String>();
		Field[] fields = this.entity.getDeclaredFields();
		for (java.lang.reflect.Field field : fields) {
			if(field.getAnnotations().length > 0){
				DE de = (DE)field.getAnnotations()[0];
				if(withPrimary){
					returnData.add(field.getName());
				} else {
					if(!de.isPrimary()){
						returnData.add(field.getName());
					}
				}
			} else {
				returnData.add(field.getName());
			}
		}
		return returnData;
	}
	
	private List<String> getDataBaseFields(){
		return getDataBaseFields(true);	
	}
	
	private List<String> getDataBaseFields(boolean withPrimary){
		List<String> returnData  = new ArrayList<String>();
		List<String> fields = this.getDataBaseFieldsAndNull(withPrimary);
		for (String field : fields) {
			if(field != null){
				returnData.add(field);
			}
		}
		return returnData;		
	}
	
	private List<String> getDataBaseFieldsAndNull(){
		return getDataBaseFieldsAndNull(true);
	}
	
	private List<String> getDataBaseFieldsAndNull(boolean withPrimary){
		List<String> returnData  = new ArrayList<String>();
		Field[] fields = this.entity.getDeclaredFields();
		for (Field field : fields) {
			if(field.getAnnotations().length > 0){
				DE de = (DE)field.getAnnotations()[0];
				if(withPrimary){
					returnData.add(de.field());
				} else {
					if(!de.isPrimary()){
						returnData.add(de.field());					
					}
				}
			} else if(!Collection.class.isAssignableFrom(field.getType())){
				returnData.add(field.getName());
			} else{
				returnData.add(null);
			}
		}
		return returnData;
	}
	
	private List<Method> getMethods(){
		List<Method> returnData  = new ArrayList<Method>();
		Method[] methods = this.entity.getMethods();
		for (java.lang.reflect.Method method : methods) {
			if(method.getName().startsWith("get")){
				returnData.add(method);
			}
		}
		return returnData;
	}
	
	private Method getMethod(String fieldName) throws NoSuchMethodException, SecurityException{
		String methodName = "get" + StringUtils.capitalize(fieldName);
		List<Method> methods = this.getMethods();
		for (Method method : methods) {
			if(method.getName().equals(methodName)) return method;
		}
		return null;
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
	
	private Method setMethod(String fieldName) throws NoSuchMethodException, SecurityException{
		String methodName = "set" + StringUtils.capitalize(fieldName);
		List<Method> methods = this.setMethods();
		for (Method method : methods) {
			if(method.getName().equals(methodName)) return method;
		}
		return null;
	}

	public Object getValue(ResultSet rs, String fieldName, String dataBaseFieldName, Method method) throws Exception{
		boolean existInDatabase = fieldName.equals(dataBaseFieldName);
		Type type = method.getParameterTypes()[0];
		Class classe = type.getClass();
		// Collection
		if(Collection.class.isAssignableFrom((Class<?>) type)){
			/*
			DynamicEntities de = new DynamicEntities(classe);
			return de.selectAll();
			*/
			return null;
		}	
		// Objet primitif
		if((ClassUtils.isPrimitiveOrWrapper((Class<?>) type) || type.equals(String.class) ) && existInDatabase){
			return rs.getObject(fieldName);
		}else{
			DynamicEntities de = new DynamicEntities((Class<?>)type);
			return de.selectById(rs.getObject(dataBaseFieldName));
		}
	}
	
	public <T> List<T> selectAll() throws Exception{
		Statement cmd = null;
		List<T> returnData = new ArrayList<T>();
		List<String> dataBaseFields = this.getDataBaseFields();
		List<String> dataBaseFieldsAndNull = this.getDataBaseFieldsAndNull();
		List<String> fields = this.getFields();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT ");
		sbQuery.append(StringUtils.join(dataBaseFields, ", "));
		sbQuery.append(" FROM ").append(this.entity.getSimpleName().toUpperCase());
		String query = sbQuery.toString();
		try {
			cmd = DBAcces.getConnection().createStatement();
			ResultSet rs = cmd.executeQuery(query);
			while (rs.next()) {
				Constructor<?> constructor = this.entity.getConstructor(null);
				T obj = (T) constructor.newInstance(null);
				Class objClass = obj.getClass();
				int i = 0;
				for (String field : fields) {
					Method method = this.setMethod(field);
					String dataBaseFieldName = dataBaseFieldsAndNull.toArray()[i] != null ? dataBaseFieldsAndNull.toArray()[i].toString() : null;
					method.invoke(obj, getValue(rs, field, dataBaseFieldName, method));
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
	
	public <T> T selectById(Object id) throws Exception{
		PreparedStatement cmd = null;
		List<String> dataBaseFields = this.getDataBaseFields();
		List<String> dataBaseFieldsAndNull = this.getDataBaseFieldsAndNull();
		List<String> fields = this.getFields();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT ");
		sbQuery.append(StringUtils.join(dataBaseFields, ", "));
		sbQuery.append(" FROM ").append(this.entity.getSimpleName().toUpperCase());
		sbQuery.append(" WHERE id = ?");
		String query = sbQuery.toString();
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			cmd.setInt(1, (int)id);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				Constructor<?> constructor = this.entity.getConstructor(null);
				T obj = (T) constructor.newInstance(null);
				Class objClass = obj.getClass();
				int i = 0;
				for (String field : fields) {
					Method method = this.setMethod(field);
					String dataBaseFieldName = dataBaseFieldsAndNull.toArray()[i] != null ? dataBaseFieldsAndNull.toArray()[i].toString() : null;
					method.invoke(obj, getValue(rs, field, dataBaseFieldName, method));
					i++;
				}
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new Exception(String.format("Impossible d'effectuer une requête SELECT BY ID sur l'entitée %1s. Erreur : %2s", this.entity.getSimpleName(), e.getMessage()));
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}		
	}
	
	public <T> boolean insert(T obj) throws Exception{
		PreparedStatement cmd = null;
		List<String> dataBaseFields = this.getDataBaseFields(false);
		List<String> dataBaseFieldsAndNull = this.getDataBaseFieldsAndNull(false);
		List<String> fields = this.getFields(false);
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("INSERT INTO ");
		sbQuery.append(this.entity.getSimpleName().toUpperCase());		
		sbQuery.append(" (").append(StringUtils.join(dataBaseFields, ", ")).append(")");
		sbQuery.append(" VALUES ");
		List<String> marks = new ArrayList<String>();
		for (String dataBaseField : dataBaseFields) {
			marks.add("?");
		}
		sbQuery.append("(").append(StringUtils.join(marks,", ")).append(")");
		String query = sbQuery.toString();
		try {
			cmd = DBAcces.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			Class objClass = obj.getClass();
			int i = 0, j = 1;
			for (String field : fields) {
				Method method = this.getMethod(field);
				String dataBaseFieldName = dataBaseFieldsAndNull.toArray()[i] != null ? dataBaseFieldsAndNull.toArray()[i].toString() : null;
				if(field.equals(dataBaseFieldName)){
					Object o = method.invoke(obj);
					cmd.setObject(j, o);
					j++;
				}else{
					Object o = method.invoke(obj);
					Class classe = o.getClass();
					if(Collection.class.isAssignableFrom(classe)){
						/*
						DynamicEntities de = new DynamicEntities(classe);
						*/
						//return null;
					} else if ((ClassUtils.isPrimitiveOrWrapper(classe)|| classe.equals(String.class) )){
						//return rs.getObject(fieldName);
					} else {
						Object primary = getPrimary(o);
						cmd.setObject(j, primary);
						j++;
					}
				}
				i++;
			}
			cmd.executeUpdate();
			ResultSet rs = cmd.getGeneratedKeys();
			if(rs.next()){
				setPrimary(obj, rs.getObject(1));
			}
			return true;
		} catch (SQLException e) {
			throw new Exception(String.format("Impossible d'effectuer une requête INSERT sur l'entitée %1s. Erreur : %2s", this.entity.getSimpleName(), e.getMessage()));
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}	
	}
	
}
