/**
 *  Classe en charge de.
 */
package fr.eni.utils;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import fr.eni.dal.DBAcces;

/**
 * @author blemaitre2015
 * @date 4 juil. 2016
 * @version QCM_ENI - V1.0
 * @param <T>
 *
 */
public class DynamicEntities<T> {

	private Class entity;
	
	public DynamicEntities(Class entity){
		this.entity = entity;
	}
	
	private List<String> getFields(){
		List<String> returnData  = new ArrayList<String>();
		Method[] methods = this.entity.getMethods();
		for (java.lang.reflect.Method method : methods) {
			if(method.getName().startsWith("set")){
				returnData.add(method.getName().replace("set", "").toLowerCase());
			}
		}
		return returnData;
	}
	
	private String generateQuery(){
		List<String> fields = this.getFields();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		for (String field : fields) {
			
		}
		return null;
	}
	
	public List<T> selectAll() throws Exception{
		Statement cmd = null;
		List<T> returnData = new ArrayList<T>();
		//this.entity.
		try {
			cmd = DBAcces.getConnection().createStatement();
			/*
			ResultSet rs = cmd.executeQuery(RQ_SELECT);
			while (rs.next()) {
				int formationId = rs.getInt("id");
				String animateurNom = rs.getString("nom");
				String animateurPrenom = rs.getString("prenom");
				String animateurEmail = rs.getString("email");
				String animateurMotDePasse = rs.getString("motdepasse");
				animateurs.add(new Animateur(formationId, animateurNom, animateurPrenom, animateurEmail, animateurMotDePasse));
			}*/
			return returnData;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Impossible de récupérer les animateurs.");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}		
	}
	
}
