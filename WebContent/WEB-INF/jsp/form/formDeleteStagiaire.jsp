<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" methode="POST" action="<%= request.getContextPath() %>/gestionFormation">
		<p>Voulez-vous supprimer la formation ${stagiaire.getNom() } ?</p>
		<input type="submit" name="deleteStagiaire" value="Supprimer">
	</form>
</div>