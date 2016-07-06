<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" methode="POST" action="<%= request.getContextPath() %>/admin/gestionFormation">
		<p>Voulez-vous supprimer la formation ${formation.getNom() } ?</p>
		<input type="submit" name="deleteFormation" value="Supprimer">
	</form>
</div>
