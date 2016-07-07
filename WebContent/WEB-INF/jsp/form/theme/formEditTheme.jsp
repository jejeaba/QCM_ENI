<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" method="POST" action="<%= request.getContextPath() %>/admin/gestionTheme">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomTheme" placeholder="Nom" required="required" value="${theme.getNom() }">
      <input type="hidden" name="idTheme" value="${theme.getId() }">
    </div>
	<input class="btn btn-primary" type="submit" name="editTheme" value="Modifier">
  </form>
</div>
