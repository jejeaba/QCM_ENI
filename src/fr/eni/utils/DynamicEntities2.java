/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import fr.eni.annotations.JoinColumn;
import fr.eni.annotations.ManyToOne;
import fr.eni.annotations.OneToMany;
import fr.eni.annotations.PrimaryKey;
import fr.eni.dal.DBAcces;
import fr.eni.logs.MonLogger;

/**
 * @author blemaitre2015
 * @date 6 juil. 2016
 * @version QCM_ENI - V1.0
 * @param <T>
 *
 */
public class DynamicEntities2 {
	
	private final static Logger LOGGER = MonLogger.getLogger(DynamicEntities2.class.getName());
	
	private Class entity;

	public DynamicEntities2(){
		
	}
	
	public DynamicEntities2(Class entity){
		this.entity = entity;
	}
	
	public DynamicEntities2 set(Class entity){
		this.entity = entity;
		return this;
	}
	
	public <T> T selectAll(){
		return (T) select(null);
	}
	
	public <T> T selectById(Object value){
		String primaryKeyName = ReflexionUtils.getPrimaryKeyName(this.entity);
		Parameter param = new Parameter(this.entity, primaryKeyName, value);
		List<T> result = select("%1s", param);
		if(result.isEmpty()) return null;
		return result.get(0);
	}

	private <T> List<T> select(String extraQuery, Parameter...args){
		String query = QueryBuilder.select(this.entity, extraQuery, args);
		PreparedStatement cmd = null;
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			prepareForSelect(cmd, args);
			LOGGER.info(String.format("Execution de la requête SELECT : %1s.", query));
			ResultSet rs = cmd.executeQuery();
			return convertListToObjects(this.entity, convertResultSetToList(rs), null, null, true);
		} catch (Exception e) {
			LOGGER.info(String.format("Impossible d'effectuer une requête SELECT sur l'entitée %1s. Erreur : %2s.", this.entity, e.getMessage()));
			return null;
		} finally {
			try {
				cmd.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.info(String.format("Une erreur est survenue lors de la fermeture de la connexion. Erreur : %1s.", e.getMessage()));				
			}
			cmd = null;
		}	
	}
	
	private <T> PreparedStatement prepareForSelect(PreparedStatement ps, Parameter...args) throws Exception{
		int i = 1;
		for (Parameter arg : args) {
			ps.setObject(1, arg.getValue());
			i++;
		}
		return ps;
	}
	
	public <T> T insert(T object){
		String query = QueryBuilder.insert(this.entity);
		LOGGER.info(String.format("Requête INSERT : %1s.", query));
		PreparedStatement cmd = null;
		try {
			cmd = DBAcces.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prepareForInsert(cmd, object);
			cmd.executeUpdate();
			ResultSet rs = cmd.getGeneratedKeys();
			injectPrimaryKey(rs, object);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(String.format("Impossible d'effectuer une requête INSERT sur l'entitée %1s. Erreur : %2s.", this.entity, e.getMessage()));
			return null;
		} finally {
			try {
				cmd.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.info(String.format("Une erreur est survenue lors de la fermeture de la connexion. Erreur : %1s.", e.getMessage()));				
			}
			cmd = null;
		}	
	}
	
	public <T> boolean delete(T object){
		String query = QueryBuilder.delete(this.entity);
		LOGGER.info(String.format("Requête DELETE : %1s.", query));
		PreparedStatement cmd = null;
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			prepareForDelete(cmd, object);
			return cmd.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(String.format("Impossible d'effectuer une requête DELETE sur l'entitée %1s. Erreur : %2s.", this.entity, e.getMessage()));
			return false;
		} finally {
			try {
				cmd.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.info(String.format("Une erreur est survenue lors de la fermeture de la connexion. Erreur : %1s.", e.getMessage()));				
			}
			cmd = null;
		}	
	}
	
	public <T> boolean update(T object){
		String query = QueryBuilder.update(this.entity);
		LOGGER.info(String.format("Requête UPDATE : %1s.", query));
		PreparedStatement cmd = null;
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			prepareForUpdate(cmd, object);
			return cmd.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(String.format("Impossible d'effectuer une requête UPDATE sur l'entitée %1s. Erreur : %2s.", this.entity, e.getMessage()));
			return false;
		} finally {
			try {
				cmd.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.info(String.format("Une erreur est survenue lors de la fermeture de la connexion. Erreur : %1s.", e.getMessage()));				
			}
			cmd = null;
		}	
	}
	
	private <T> T injectPrimaryKey(ResultSet rs, T object) throws Exception{
		if(rs.next()){
			ReflexionUtils.setPrimary(object, ReflexionUtils.parserExceptions(rs.getObject(1)));
		}
		return object;
	}
	
	private <T> PreparedStatement prepareForDelete(PreparedStatement ps, T object) throws Exception{
		ps.setObject(1, ReflexionUtils.getPrimary(object));
		return ps;
	}
	
	private <T> PreparedStatement prepareForUpdate(PreparedStatement ps, T object) throws Exception{
		Class classe = object.getClass();
		Field[] fields = classe.getDeclaredFields();
		Object primaryKeyValue = null;
		int i = 1;
		for (Field field : fields) {
			field.setAccessible(true);
			if(field.isAnnotationPresent(PrimaryKey.class)){
				primaryKeyValue = field.get(object);
			}else if(field.isAnnotationPresent(ManyToOne.class)){				
				Object foreignKeyValue = ReflexionUtils.getPrimary(field.get(object));
				ps.setObject(i, foreignKeyValue);
				i++;
			}else if(!field.isAnnotationPresent(OneToMany.class)){
				ps.setObject(i, field.get(object));
				i++;
			}
		}
		ps.setObject(i, primaryKeyValue);
		return ps;
	}
	
	private <T> PreparedStatement prepareForInsert(PreparedStatement ps, T object) throws Exception{
		Class classe = object.getClass();
		Field[] fields = classe.getDeclaredFields();
		int i = 1;
		for (Field field : fields) {
			field.setAccessible(true);
			if(field.isAnnotationPresent(ManyToOne.class)){				
				Object foreignKeyValue = ReflexionUtils.getPrimary(field.get(object));
				ps.setObject(i, foreignKeyValue);
				i++;
			}else if(!field.isAnnotationPresent(PrimaryKey.class) && 
					 !field.isAnnotationPresent(OneToMany.class)){
				ps.setObject(i, field.get(object));
				i++;
			}
		}
		return ps;
	}
	
	private <T> List<T> convertListToObjects(Class classe, HashMap<String,Set<HashMap<String,Object>>> data, Object primary, Object foreign, boolean repeat) throws InstantiationException, IllegalAccessException{
		List<T> returnData = new ArrayList<T>();
		Field[] fields = classe.getDeclaredFields();
		String alias = QueryBuilder.getAlias(classe);
		try {
			Set<HashMap<String,Object>> dataObject = data.get(alias);
			for (HashMap<String, Object> dbObject : dataObject) {
				T object = (T) classe.newInstance();
				Object elementPrimary = new Object();
				Object elementForeign = new Object();
				for (Field field : fields) {
					field.setAccessible(true);
					if(field.isAnnotationPresent(PrimaryKey.class)){
						elementPrimary = dbObject.get(field.getName());
						field.set(object, elementPrimary);
					}else if(field.isAnnotationPresent(ManyToOne.class)){
						Class elementClass = field.getType();
						String dataBaseFieldName = field.getAnnotation(JoinColumn.class).name();
						elementForeign = dbObject.get(dataBaseFieldName);
						if(repeat){
							Object value = convertListToObjects(elementClass, data, null, elementForeign, false).get(0);
							field.set(object, value);
						}
					}else if(field.isAnnotationPresent(OneToMany.class)){
						if(repeat){
							ParameterizedType genericType = (ParameterizedType) field.getGenericType();
							Class<?> elementClass = (Class<?>) genericType.getActualTypeArguments()[0];
							List<Object> value = convertListToObjects(elementClass, data, elementPrimary, null, false);
							field.set(object, value);
						}
					}else{
						field.set(object, dbObject.get(field.getName()));
					}
				}
				if(primary != null){
					if(primary.equals(elementForeign)){
						returnData.add(object);
					}
				} else if(foreign != null){
					if(foreign.equals(elementPrimary)){
						returnData.add(object);
					}
				}else{
					returnData.add(object);
				}
			}
			return returnData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String,Set<HashMap<String,Object>>> convertResultSetToList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    HashMap<String,Set<HashMap<String,Object>>> returnData = new HashMap<String,Set<HashMap<String,Object>>>();
	    while (rs.next()) {
	    	HashMap<String,HashMap<String,Object>> row = new HashMap<String,HashMap<String,Object>>();
	        for(int i=1; i<=columns; ++i) {
	        	String objectName = md.getColumnName(i).split("_")[0];
				String fieldName = md.getColumnName(i).substring(objectName.length()+1, md.getColumnName(i).length());
				Object value = rs.getObject(i);
				if(row.containsKey(objectName)){
					if(!row.get(objectName).containsKey(fieldName)){
						row.get(objectName).put(fieldName, value);
					}
				}else{
					HashMap<String,Object> newMap = new HashMap<String,Object>();
					newMap.put(fieldName, value);
					row.put(objectName, newMap);
				}				
	        }
	        for (Entry<String, HashMap<String, Object>> column : row.entrySet()) {
	        	if(!hashMapIsNull(column.getValue())){
					if(returnData.containsKey(column.getKey())){
						returnData.get(column.getKey()).add(column.getValue());
					}else{
						Set<HashMap<String,Object>> newList = new HashSet<HashMap<String,Object>>();
						newList.add(column.getValue());
						returnData.put(column.getKey(), newList);
					}
	        	}
			}
	    }
	    return returnData;
	}
	
	private boolean hashMapIsNull(HashMap<String, Object> hashmap){
		boolean returnValue = true;
		for (Entry<String, Object> column : hashmap.entrySet()) {
			if(column.getValue() != null){
				returnValue = false;
			}
		}
		return returnValue;
	}
	
}
