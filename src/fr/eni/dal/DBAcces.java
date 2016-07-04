/**
 *  Classe en charge de.
 */
package fr.eni.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author blemaitre2015
 * @date 27 juin 2016
 * @version TP1 - V1.0
 *
 */
public class DBAcces {

	public static Connection getConnection() throws SQLException{
		Connection cnx = null;
		// Charger l'annuaire JNDI
		InitialContext jndi = null;
		try{
			jndi = new InitialContext();
		}catch(NamingException ne){
			ne.printStackTrace();
			throw new SQLException("Impossible de se connecter à l'annuaire JNDI");
		}

		// Chercher le pool de connexions dans l'annuaire
		String jndiSourceName = "java:comp/env/jdbc/tp1";
		DataSource ds = null;
		try{
			ds = (DataSource)jndi.lookup(jndiSourceName);
		}catch(NamingException ne){
			ne.printStackTrace();
			throw new SQLException(String.format("Impossible de trouver %1 dans l'annuaire JNDI", jndiSourceName));
		}
		cnx = ds.getConnection();
		return cnx;
	}
}
