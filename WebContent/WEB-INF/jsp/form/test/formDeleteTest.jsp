<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" method="POST"
		action="<%=request.getContextPath()%>/admin/gestionTest">
		<p>Voulez-vous supprimer le test ${test.getNom() } ?</p>
		<input type="hidden" name="idTest" value="${test.getId() }">
		<input type="submit" class="btn btn-danger pull-right" name="deleteTest" value="Supprimer" >
	</form>
</div>