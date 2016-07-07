package fr.eni.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import fr.eni.bo.Question;
import fr.eni.bo.Reponse;
import fr.eni.bo.Theme;
import fr.eni.utils.DynamicEntities;
import fr.eni.utils.DynamicEntities2;
import fr.eni.utils.GestionErreur;
import fr.eni.utils.ReflexionUtils;

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
			e.printStackTrace();
			GestionErreur.redirectionErreur(e, request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			GestionErreur.redirectionErreur(e, request, response);
			return;
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
		DynamicEntities2 _db2 = new DynamicEntities2();
		Question question;
		int idQuestion = 0;
		String success;
		String error;
		if("Ajouter".equals(request.getParameter("addQuestion"))){
			
			int idTheme = 0;
			boolean type = false;
			String nomDuFichier = null;
			String enonce = null;
			List<String> listReponse = new ArrayList<String>();
			HashMap<String, String> listReponseCheck = new HashMap<String, String>();
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if(item.isFormField()){
					String nomChamp = item.getFieldName();
					String valeurChamp = item.getString();
					
					if("theme".equals(nomChamp)){
						idTheme = Integer.parseInt(valeurChamp);
					}else if("typeQuestion".equals(nomChamp)){
						type = Boolean.parseBoolean(request.getParameter("typeQuestion"));
					}else if("nomQuestion".equals(nomChamp)){
						enonce = valeurChamp;
					}else if("reponses".equals(nomChamp)){
						listReponse.add(valeurChamp);
					}else if(nomChamp.contains("reponsesCheck_")){
						listReponseCheck.put(nomChamp.split("_")[1], valeurChamp);
					}
					
				}else{
					String nomChamp = item.getFieldName();
					nomDuFichier = FilenameUtils.getName(item.getName());
					InputStream contenuFichier = item.getInputStream();
					FileUtils.copyInputStreamToFile(contenuFichier, new File( "D:\\" + nomDuFichier ));
				}
			}
			
			List<Reponse> listeReponse = new ArrayList<Reponse>();
			int index = 1;
			for(String reponse : listReponse){
				if(!reponse.trim().isEmpty()){
					boolean check = false;
					if(listReponseCheck.containsKey(Integer.toString(index))){
						check = true;
					};
					listeReponse.add(new Reponse(0, reponse, check));
					index++;						
				}
			}
			
			Theme theme = _db.set(Theme.class).selectById(idTheme);
			question = new Question(0, theme, enonce, type, nomDuFichier);
			question.setListReponse(listeReponse);
			Object primaryKeyValue = ReflexionUtils.getPrimary(_db2.set(Question.class).insert(question));
			if(!ReflexionUtils.isEmptyOrNull(primaryKeyValue)){
				success = "La question à bien été ajoutée !";
			}else{
				error = "La question n'a pas été ajoutée !";
			}
		}else if("Modifier".equals(request.getParameter("editQuestion"))){
			
			int idTheme = 0;
			boolean type = false;
			String nomDuFichier = null;
			String enonce = null;
			List<String> listReponse = new ArrayList<String>();
			HashMap<String, String> listReponseCheck = new HashMap<String, String>();
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if(item.isFormField()){
					String nomChamp = item.getFieldName();
					String valeurChamp = item.getString();
					
					if("theme".equals(nomChamp)){
						idTheme = Integer.parseInt(valeurChamp);
					}else if("typeQuestion".equals(nomChamp)){
						type = Boolean.parseBoolean(request.getParameter("typeQuestion"));
					}else if("nomQuestion".equals(nomChamp)){
						enonce = valeurChamp;
					}else if("reponses".equals(nomChamp)){
						listReponse.add(valeurChamp);
					}else if(nomChamp.contains("reponsesCheck_")){
						listReponseCheck.put(nomChamp.split("_")[1], valeurChamp);
					}else if("id".equals(nomChamp)){
						idQuestion = Integer.parseInt(valeurChamp);
					}
					
				}else{
					String nomChamp = item.getFieldName();
					nomDuFichier = FilenameUtils.getName(item.getName());
					InputStream contenuFichier = item.getInputStream();
					FileUtils.copyInputStreamToFile(contenuFichier, new File( "D:\\" + nomDuFichier ));
				}
			}
			
			List<Reponse> listeReponse = new ArrayList<Reponse>();
			int index = 1;
			for(String reponse : listReponse){
				if(!reponse.trim().isEmpty()){
					boolean check = false;
					if(listReponseCheck.containsKey(Integer.toString(index))){
						check = true;
					};
					listeReponse.add(new Reponse(0, reponse, check));
					index++;						
				}
			}
			
			Theme theme = _db.set(Theme.class).selectById(idTheme);
			question = new Question(idQuestion, theme, enonce, type, nomDuFichier);
			question.setListReponse(listeReponse);
			
			if(_db2.set(Question.class).update(question)){
				success = "La question à bien été modifiée !";
			}else{
				error = "La question n'a pas été modifiée !";
			}
			
		}else if("Supprimer".equals(request.getParameter("deleteQuestion"))){
			idQuestion = Integer.parseInt(request.getParameter("idQuestion"));
			question = new Question(idQuestion);
			if(_db.set(Question.class).delete(question)){
				success = "La question à bien été supprimée !";
			}else{
				error = "La question n'a pas été supprimée !";
			}
		}
		

		List<Question> listeQuestions = _db.set(Question.class).selectAll(); 
		request.setAttribute("listeQuestions",listeQuestions );
		
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/compte/gestionQuestion.jsp").forward(request, response);	
	}
}
