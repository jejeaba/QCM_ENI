<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" method="POST"
		action="<%=request.getContextPath()%>/admin/gestionTheme">
		<p>Voulez-vous supprimer le theme ${theme.getNom() } ?</p>
		<input type="hidden" name="idTheme" value="${theme.getId() }">
		<input type="submit" class="btn btn-danger pull-right" name="deleteTheme" value="Supprimer" >
	</form>
</div>