package fr.eni.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bo.Formateur;
import fr.eni.bo.Formation;
import fr.eni.dal.DBAcces;
import fr.eni.utils.DynamicEntities;

/**
 * Servlet implementation class viewEditFormation
 */
public class viewFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewFormation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idFormation ;
		if ("edit".equals(request.getParameter("action"))){
			List<Formateur> listeFormateurs = listeFormateurs();
			idFormation = Integer.parseInt(request.getParameter("idFormation"));
			DynamicEntities _db = new DynamicEntities();
			try {
				Formation formation = _db.set(Formation.class).selectById(idFormation);
				request.setAttribute("listeFormateurs", listeFormateurs);
				request.setAttribute("formation",formation );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formation/formEditFormation.jsp").forward(request, response);
			return;
		}else if ("delete".equals(request.getParameter("action"))){
			idFormation = Integer.parseInt(request.getParameter("idFormation"));
				DynamicEntities _db = new DynamicEntities();
				try {
					Formation formation = _db.set(Formation.class).selectById(idFormation);
					request.setAttribute("formation",formation );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formation/formDeleteFormation.jsp").forward(request, response);
				return;
		}else if ("add".equals(request.getParameter("action"))){
			
			List<Formateur> listeFormateurs = listeFormateurs();
			request.setAttribute("listeFormateurs", listeFormateurs);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formation/formAddFormation.jsp").forward(request, response);
			return; 	
		}
	}
	
	public static List<Formateur> listeFormateurs(){
		Formateur formateur ;
		List<Formateur> listeFormateurs = new ArrayList<Formateur>();
		PreparedStatement cmd = null;
		String query = "  SELECT * FROM getAllFormateur";
		try {
			cmd = DBAcces.getConnection().prepareStatement(query);
			//cmd.setInt(1, 1);
			//List<Object> returnData = new ArrayList<Object>();
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				formateur = new Formateur(
						rs.getInt("id"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("motdepasse")
						);
				listeFormateurs.add(formateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cmd.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cmd = null;
		
		}
		return listeFormateurs;
	}
}
