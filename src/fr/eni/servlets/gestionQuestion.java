package fr.eni.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import fr.eni.bo.Question;
import fr.eni.bo.Theme;
import fr.eni.utils.DynamicEntities;

/**
 * Servlet implementation class accueil
 */
public class gestionQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode en charge de .
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynamicEntities _db = new DynamicEntities();
		Question question;
		int idQuestion;
		if("Ajouter".equals(request.getParameter("addQuestion"))){
			try {
//				int idTheme = Integer.parseInt(request.getParameter("theme"));
//				boolean type = Boolean.parseBoolean(request.getParameter("typeQuestion"));
//				Theme theme = _db.set(Theme.class).selectById(idTheme);
				
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : items) {
					if(item.isFormField()){
						String nomChamp = item.getFieldName();
						String valeurChamp = item.getString();
					}else{
						String nomChamp = item.getFieldName();
						String nomDuFichier = FilenameUtils.getName(item.getName());
						InputStream contenuFichier = item.getInputStream();
						ecrireFichier(contenuFichier, nomDuFichier, "D:\\");
					}
				}
				question = new Question();
			//_db.set(Question.class).insert(question);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("Modifier".equals(request.getParameter("editQuestion"))){
				
		}else if("Supprimer".equals(request.getParameter("deleteQuestion"))){
			idQuestion = Integer.parseInt(request.getParameter("id"));
			question = new Question(idQuestion);
			Boolean ret = _db.set(Question.class).delete(question);
		}
		
		try {
			List<Question> listeQuestions = _db.set(Question.class).selectAll(); 
			request.setAttribute("listeQuestions",listeQuestions );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionQuestion.jsp").forward(request, response);
		
	}
	private void ecrireFichier( InputStream file , String nomFichier, String chemin ) throws IOException {
	    /* Prépare les flux. */
	    BufferedInputStream entree = null;
	    BufferedOutputStream sortie = null;
	    try {
	        /* Ouvre les flux. */
	        entree = new BufferedInputStream( file, 10240 );
	        sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
	        		10240 );
	 
	        /* ... */
	    } finally {
	        try {
	            sortie.close();
	        } catch ( IOException ignore ) {
	        }
	        try {
	            entree.close();
	        } catch ( IOException ignore ) {
	        }
	    }
	}
}
