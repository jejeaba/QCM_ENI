<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" methode="POST" action="<%= request.getContextPath() %>/admin/gestionQuestion">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomQuestion" placeholder="Nom" required="required" value="${question.getNom() }">
      <input type="hidden" nom="idQuestion" value="${question.getId() }">
    </div>
	<input class="btn btn-default" type="submit" name="addQuestion" value="Modifier">
  </form>
</div>
