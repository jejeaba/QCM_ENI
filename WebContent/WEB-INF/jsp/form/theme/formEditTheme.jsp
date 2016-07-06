<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" methode="POST" action="<%= request.getContextPath() %>/gestionTheme">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomTheme" placeholder="Nom" required="required" value="${theme.getNom() }">
      <input type="hidden" nom="idTheme" value="${theme.getId() }">
    </div>
	<input class="btn btn-default" type="submit" name="addTheme" value="Modifier">
  </form>
</div>
