<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" method="POST"
		action="<%=request.getContextPath()%>/admin/gestionSection">
		<p>Voulez-vous supprimer la section ${section.getNom() } ?</p>
		<input type="hidden" name="idSection" value="${section.getId() }">
		<input type="submit" class="btn btn-danger pull-right" name="deleteSection" value="Supprimer" >
	</form>
</div>