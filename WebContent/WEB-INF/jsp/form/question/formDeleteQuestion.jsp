<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" methode="POST" action="<%= request.getContextPath() %>/admin/gestionQuestion">
		<p>Voulez-vous supprimer la question ${question.getNom() } ?</p>
		<input type="hidden" name="idQuestion" value="${Question.getId() }">
		<input type="submit" class="btn btn-danger" name="deleteQuestion" value="Supprimer">
	</form>
</div>