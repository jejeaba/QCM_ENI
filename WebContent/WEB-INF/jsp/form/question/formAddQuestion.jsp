<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" methode="POST" action="<%= request.getContextPath() %>/gestionQuestion">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomQuestion" placeholder="Nom" required="required">
    </div>
    <input type="submit" name="addQuestion" value="Ajouter">
  </form>
</div>