/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.dal.DBAcces;

/**
 * @author blemaitre2015
 * @date 6 juil. 2016
 * @version QCM_ENI - V1.0
 * @param <T>
 *
 */
public class DynamicEntities2 {
	
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

	public <T> List<T> select(){
		List<T> returnData = new ArrayList<T>();
		String query = QueryBuilder.select(this.entity);
		PreparedStatement cmd = null;
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				
			}
			return returnData;
		} catch (SQLException e) {
			//throw new Exception(String.format("Impossible d'effectuer une requête %1s sur l'entitée %2s. Erreur : %3s", queryType, this.entity.getSimpleName(), e.getMessage()));
		} finally {
			//cmd.getConnection().close();
			cmd = null;
		}		
	}
	
}
