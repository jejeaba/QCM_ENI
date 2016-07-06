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

import fr.eni.bo.Theme;
import fr.eni.dal.DBAcces;
import fr.eni.utils.DynamicEntities;

/**
 * Servlet implementation class viewEditFormation
 */
public class viewTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewTheme() {
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
		int idTheme;
		idTheme = Integer.parseInt(request.getParameter("id"));
		DynamicEntities _db = new DynamicEntities();
		
		Theme theme;
		try {
			theme = _db.set(Theme.class).selectById(idTheme);
			request.setAttribute("theme",theme );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("edit".equals(request.getParameter("action"))){
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/theme/formEditTheme.jsp").forward(request, response);
			return;
		}else if ("delete".equals(request.getParameter("action"))){
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/theme/formDeleteTheme.jsp").forward(request, response);
			return;
		}else if ("add".equals(request.getParameter("action"))){
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/theme/formAddTheme.jsp").forward(request, response);
			return; 	
		}
	}
}
