<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" methode="POST" action="<%= request.getContextPath() %>/admin/gestionTheme">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomTheme" placeholder="Nom" required="required">
    </div>
    <input type="submit" name="addTheme" value="Ajouter">
  </form>
</div>