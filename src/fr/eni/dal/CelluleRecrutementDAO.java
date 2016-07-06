/**
 * Classe en charge de .
 */
package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.bo.CelluleRecrutement;
import fr.eni.bo.Formateur;

/**
 * @author Administrateur
 * @date 6 juil. 2016
 * @version QCM_ENI - V1.0
 */
public class CelluleRecrutementDAO {
	public static CelluleRecrutement getByEmailMotDePasse(String email, String motdepasse) throws Exception{
		CelluleRecrutement cellule = null;
		
		PreparedStatement cmd=null;
		Connection cnx = null;
		try{
			cnx = DBAcces.getConnection();
			cmd = cnx.prepareStatement("SELECT CelluleRecrutement.id, nom, prenom , email, motdepasse FROM CelluleRecrutement, PERSONNEL, UTILISATEUR where CelluleRecrutement.id = PERSONNEL.id and CelluleRecrutement.id = UTILISATEUR.id and UTILISATEUR.motdepasse = ? and UTILISATEUR.email = ?");
			cmd.setString(1, motdepasse);
			cmd.setString(2, email);
			ResultSet rs = cmd.executeQuery();
	        while (rs.next()) {
	        	cellule = new CelluleRecrutement(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("motdepasse"));
	        }
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			throw new Exception("Problème de recupération du formateur");
		}finally{
			if(cnx != null) cnx.close();
			if(cmd != null) cmd.close();
		}
		return cellule;
	}
	public static void addCelluleRecrutement(CelluleRecrutement cellule) throws SQLException{
		PreparedStatement cmd=null;
		Connection cnx = null;
		try {
			cnx = DBAcces.getConnection();
			String request = "{call insertCelluleRecrutement(?,?,?,?)}";
			cmd =  cnx.prepareCall(request);
			cmd.setString(1, cellule.getNom());
			cmd.setString(2, cellule.getPrenom());
			cmd.setString(3, cellule.getEmail());
			cmd.setString(4, cellule.getMotdepasse());
			
			cmd.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(cmd != null) cmd.close();
			if(cnx != null) cnx.close();
		}
	}
}
