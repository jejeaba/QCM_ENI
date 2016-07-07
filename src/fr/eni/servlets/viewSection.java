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

import fr.eni.bo.Section;
import fr.eni.bo.Theme;
import fr.eni.dal.DBAcces;
import fr.eni.utils.DynamicEntities;

/**
 * Servlet implementation class viewEditFormation
 */
public class viewSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewSection() {
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
		int idSection = Integer.parseInt(request.getParameter("id"));
		DynamicEntities _db = new DynamicEntities();
		List<Theme> listeThemes;
		Section section;
		try {
			listeThemes = _db.set(Theme.class).selectAll();
			request.setAttribute("listeThemes", listeThemes);
			section = _db.set(Section.class).selectById(idSection);
			request.setAttribute("section",section );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ("edit".equals(request.getParameter("action"))){
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/section/formEditSection.jsp").forward(request, response);
			return;
		}else if ("delete".equals(request.getParameter("action"))){
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/section/formDeleteSection.jsp").forward(request, response);
			return;
		}else if ("add".equals(request.getParameter("action"))){

			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/section/formAddSection.jsp").forward(request, response);
			return; 	
		}
	}
}
