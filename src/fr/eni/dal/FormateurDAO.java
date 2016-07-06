/**
 * Classe en charge de .
 */
package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.bo.Formateur;

/**
 * @author Administrateur
 * @date 6 juil. 2016
 * @version QCM_ENI - V1.0
 */
public class FormateurDAO {
	public static Formateur getByEmailMotDePasse(String email, String motdepasse) throws Exception{
		Formateur formateur = null;
		
		PreparedStatement cmd=null;
		Connection cnx = null;
		try{
			cnx = DBAcces.getConnection();
			cmd = cnx.prepareStatement("SELECT formateur.id, nom, prenom , email, motdepasse FROM FORMATEUR, PERSONNEL, UTILISATEUR where FORMATEUR.id = PERSONNEL.id and FORMATEUR.id = UTILISATEUR.id and UTILISATEUR.motdepasse = ? and UTILISATEUR.email = ?");
			cmd.setString(1, motdepasse);
			cmd.setString(2, email);
			ResultSet rs = cmd.executeQuery();
	        while (rs.next()) {
	        	formateur = new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("motdepasse"));
	        }
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			throw new Exception("Problème de recupération du formateur");
		}finally{
			if(cnx != null) cnx.close();
			if(cmd != null) cmd.close();
		}
		return formateur;
	}
	
	public static void addFormateur(Formateur formateur) throws SQLException{
		PreparedStatement cmd=null;
		Connection cnx = null;
		try {
			cnx = DBAcces.getConnection();
			String request = "{call insertFormateur(?,?,?,?)}";
			cmd = cnx.prepareCall(request);
			cmd.setString(1, formateur.getNom());
			cmd.setString(2, formateur.getPrenom());
			cmd.setString(3, formateur.getEmail());
			cmd.setString(4, formateur.getMotdepasse());
			
			cmd.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(cmd != null) cmd.close();
			if(cnx != null) cnx.close();
		}
	}
}
