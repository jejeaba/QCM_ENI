package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bo.Formation;
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
			idFormation = Integer.parseInt(request.getParameter("idFormation"));
			DynamicEntities _db = new DynamicEntities();
			try {
				Formation formation = _db.set(Formation.class).selectById(idFormation);
				request.setAttribute("formation",formation );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formEditFormation.jsp").forward(request, response);
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
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formDeleteFormation.jsp").forward(request, response);
				return;
		}else if ("add".equals(request.getParameter("action"))){
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/form/formAddFormation.jsp").forward(request, response);
			return; 	
		}
	}

}
