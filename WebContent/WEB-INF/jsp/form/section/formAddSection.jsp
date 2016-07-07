<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" method="POST" action="<%= request.getContextPath() %>/admin/gestionSection">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomSection" placeholder="Nom" required="required">
    </div>
    <!-- select -->
	<div class="form-group">
		<select class="form-control" name="theme">
			<c:forEach items="${listeThemes}" var="theme">
				<option value="${theme.getId()}">${theme.getNom()}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
      <input type="number" class="form-control" name="nbQuestion" placeholder="Nombre de question" required="required">
    </div>
    <div class="form-group">
      <input type="number" class="form-control" name="duree" placeholder="Durée en minute" required="required">
    </div>
    <input type="submit" name="addSection" value="Ajouter">
  </form>
</div>