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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import fr.eni.annotations.JoinColumn;
import fr.eni.annotations.ManyToOne;
import fr.eni.annotations.OneToMany;
import fr.eni.annotations.PrimaryKey;
import fr.eni.bo.HeritsFrom;
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
	
	private List<String> getFields(Class classe){
		return getFields(classe, true);
	}
	
	private List<String> getFields(Class classe, boolean withPrimary){
		List<String> returnData  = new ArrayList<String>();
		Field[] fields = classe.getDeclaredFields();
		for (java.lang.reflect.Field field : fields) {
			if(field.isAnnotationPresent(PrimaryKey.class)){
				if(withPrimary){
					returnData.add(field.getName());
				}			
			}else{
				returnData.add(field.getName());
			}
		}
		return returnData;
	}
	
	private List<String> getDataBaseFields(Class classe){
		return getDataBaseFields(classe, true);	
	}
	
	private List<String> getDataBaseFields(Class classe, boolean withPrimary){
		List<String> returnData  = new ArrayList<String>();
		List<String> fields = this.getDataBaseFieldsAndNull(classe, withPrimary);
		for (String field : fields) {
			if(field != null){
				returnData.add(field);
			}
		}
		return returnData;		
	}
	
	private List<String> getDataBaseFieldsAndNull(Class classe){
		return getDataBaseFieldsAndNull(classe, true);
	}
	
	private List<String> getDataBaseFieldsAndNull(Class classe, boolean withPrimary){
		List<String> returnData  = new ArrayList<String>();
		Field[] fields = classe.getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(PrimaryKey.class)){
				if(withPrimary){
					returnData.add(field.getName());
				}
			}else if(field.isAnnotationPresent(ManyToOne.class)){
				returnData.add(field.getAnnotation(JoinColumn.class).name());
			} else if(field.isAnnotationPresent(OneToMany.class)){
				returnData.add(null);
			}else{
				returnData.add(field.getName());
			}
		}
		return returnData;
	}
	
	private List<Method> getMethods(Class classe){
		List<Method> returnData  = new ArrayList<Method>();
		Method[] methods = classe.getMethods();
		for (java.lang.reflect.Method method : methods) {
			if(method.getName().startsWith("get")){
				returnData.add(method);
			}
		}
		return returnData;
	}
	
	private Method getMethod(Class classe, String fieldName) throws NoSuchMethodException, SecurityException{
		String methodName = "get" + StringUtils.capitalize(fieldName);
		List<Method> methods = this.getMethods(classe);
		for (Method method : methods) {
			if(method.getName().equals(methodName)) return method;
		}
		return null;
	}
	
	private List<Method> setMethods(Class classe){
		List<Method> returnData  = new ArrayList<Method>();
		Method[] methods = classe.getMethods();
		for (java.lang.reflect.Method method : methods) {
			if(method.getName().startsWith("set")){
				returnData.add(method);
			}
		}
		return returnData;
	}
	
	private Method setMethod(Class classe, String fieldName) throws NoSuchMethodException, SecurityException{
		String methodName = "set" + StringUtils.capitalize(fieldName);
		List<Method> methods = this.setMethods(classe);
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
			return de.selectById((int)rs.getObject(dataBaseFieldName));
		}
	}
	
	private List<Class> herits(Class classe){
		boolean pass = true;
		List<Class> classes = new ArrayList<Class>();
		classes.add(this.entity);
		while (pass) {
			classe = classe.getSuperclass();
			classes.add(classe);
			pass = classe.isAnnotationPresent(HeritsFrom.class);				
		}
		return classes;
	}
	
	public String fromQuery(Class classe){
		if(classe.isAnnotationPresent(HeritsFrom.class)){
			List<String> strFrom = new ArrayList<String>();
			List<Class> classes = herits(classe);
			for (int i = 0; i < classes.size(); i++) {
				Class current = (Class)classes.get(i);
				if(i == 0){
					strFrom.add(current.getSimpleName().toUpperCase());
				} else if (i > 0){
					Class before = (Class)classes.get(i - 1);
					Class last = (Class)classes.get(classes.size() - 1);
					String primary = ReflexionUtils.getPrimaryKeyName(last);
					StringBuilder sb = new StringBuilder();
					sb.append(current.getSimpleName().toUpperCase());
					sb.append(" ON ");
					sb.append(before.getSimpleName().toUpperCase()).append(".").append(primary);
					sb.append(" = ").append(current.getSimpleName().toUpperCase()).append(".").append(primary);
					strFrom.add(sb.toString());
				}
			}
			return StringUtils.join(strFrom, " INNER JOIN ");
		} else {
			return classe.getSimpleName().toUpperCase();
		}
	}
	
	public <T> List<T> select(String queryType, String extraQuery, Object...args) throws Exception{
		Class classe = this.entity;
		String fromQuery = fromQuery(classe);
		List<Class> classes = herits(classe);
		if(classes.size() > 1){
			classe = (Class)classes.get(classes.size()-1);
		}
		boolean haveExtraQuery = !"".equals(extraQuery) && extraQuery != null;
		PreparedStatement cmd = null;
		List<T> returnData = new ArrayList<T>();
		List<String> dataBaseFields = this.getDataBaseFields(classe);
		List<String> dataBaseFieldsAndNull = this.getDataBaseFieldsAndNull(classe);
		List<String> fields = this.getFields(classe);
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT ");
		List<String> selectQuery = new ArrayList<String>();
		for (String dataBaseField : dataBaseFields) {
			selectQuery.add(classe.getSimpleName().toUpperCase() + "." + dataBaseField);
		}
		sbQuery.append(StringUtils.join(selectQuery, ", "));
		sbQuery.append(" FROM ").append(fromQuery);
		if(haveExtraQuery){
			sbQuery.append(" WHERE ").append(extraQuery);
		}
		String query = sbQuery.toString();
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			if(haveExtraQuery){
				int i = 1;
				for (Object arg : args) {
					cmd.setObject(i, arg);
					i++;
				}
			}
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				T obj = (T)ReflexionUtils.constructor(this.entity);
				int i = 0;
				for (String field : fields) {
					Method method = this.setMethod(this.entity, field);
					String dataBaseFieldName = dataBaseFieldsAndNull.toArray()[i] != null ? dataBaseFieldsAndNull.toArray()[i].toString() : null;
					method.invoke(obj, getValue(rs, field, dataBaseFieldName, method));
					i++;
				}
				returnData.add(obj);
			}
			return returnData;
		} catch (SQLException e) {
			throw new Exception(String.format("Impossible d'effectuer une requête %1s sur l'entitée %2s. Erreur : %3s", queryType, this.entity.getSimpleName(), e.getMessage()));
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}		
	}
	
	public <T> List<T> selectAll() throws Exception{
		return select("SELECT ALL",null);
	}
	
	public <T> T selectById(int id) throws Exception{
		Class classe = this.entity;
		List<Class> classes = herits(classe);
		if(classes.size() > 1){
			classe = (Class)classes.get(classes.size()-1);
		}
		String primaryKeyName = ReflexionUtils.getPrimaryKeyName(classe);
		List<T> returnData = select("SELECT BY ID",classe.getSimpleName().toUpperCase() + "." + primaryKeyName + " = ?", id);
		if(!returnData.isEmpty()){
			return returnData.get(0);
		}
		return null; 
	}	
	
	public <T> boolean insert(T obj) throws Exception{
		Class classe = obj.getClass();
		PreparedStatement cmd = null;
		List<String> dataBaseFields = this.getDataBaseFields(classe,false);
		List<String> dataBaseFieldsAndNull = this.getDataBaseFieldsAndNull(classe,false);
		List<String> fields = this.getFields(classe,false);
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("INSERT INTO ");
		sbQuery.append(this.entity.getSimpleName().toUpperCase());		
		sbQuery.append(" (").append(StringUtils.join(dataBaseFields, ", ")).append(")");
		sbQuery.append(" VALUES ");
		sbQuery.append(SQLUtils.queryMarkers(true, dataBaseFields.size()));
		String query = sbQuery.toString();
		try {
			cmd = DBAcces.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int i = 0, j = 1;
			for (String field : fields) {
				Method method = this.getMethod(classe,field);
				String dataBaseFieldName = dataBaseFieldsAndNull.toArray()[i] != null ? dataBaseFieldsAndNull.toArray()[i].toString() : null;
				if(field.equals(dataBaseFieldName)){
					Object o = method.invoke(obj);
					cmd.setObject(j, o);
					j++;
				}else{
					Object o = method.invoke(obj);
					Class c = o.getClass();
					if(!Collection.class.isAssignableFrom(c) && !ClassUtils.isPrimitiveOrWrapper(c) && !c.equals(String.class)){
						Object primary = ReflexionUtils.getPrimary(o);
						cmd.setObject(j, primary);
						j++;
					}
				}
				i++;
			}
			cmd.executeUpdate();
			ResultSet rs = cmd.getGeneratedKeys();
			if(rs.next()){
				ReflexionUtils.setPrimary(obj, ReflexionUtils.parserExceptions(rs.getObject(1)));
			}
			return true;
		} catch (SQLException e) {
			throw new Exception(String.format("Impossible d'effectuer une requête INSERT sur l'entitée %1s. Erreur : %2s", this.entity.getSimpleName(), e.getMessage()));
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}	
	}
	
	public <T> boolean update(T obj) throws Exception{
		String entityName = obj.getClass().getSimpleName();
		PreparedStatement cmd = null;
		String primaryKeyName = ReflexionUtils.getPrimaryKeyName(obj);
		Object primaryKey = ReflexionUtils.getPrimary(obj);
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("UPDATE ");
		sbQuery.append(entityName.toUpperCase());	
		sbQuery.append(" WHERE ").append(primaryKeyName).append(" = ?");
		String query = sbQuery.toString();
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			cmd.setObject(1, primaryKey);
			cmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new Exception(String.format("Impossible d'effectuer une requête UPDATE sur l'entitée %1s. Erreur : %2s", entityName, e.getMessage()));
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}	
	}
	
	public <T> boolean delete(T obj) throws Exception{
		String entityName = obj.getClass().getSimpleName();
		PreparedStatement cmd = null;
		String primaryKeyName = ReflexionUtils.getPrimaryKeyName(obj);
		Object primaryKey = ReflexionUtils.getPrimary(obj);
		try {
			cmd = DBAcces.getConnection().prepareStatement(QueryCreator.delete(obj));
			cmd.setObject(1, primaryKey);
			cmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new Exception(String.format("Impossible d'effectuer une requête DELETE sur l'entitée %1s. Erreur : %2s", entityName, e.getMessage()));
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}	
	}
	
}
