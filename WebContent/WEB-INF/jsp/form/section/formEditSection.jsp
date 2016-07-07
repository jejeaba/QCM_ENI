<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" method="POST" action="<%= request.getContextPath() %>/admin/gestionSection">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomSection" placeholder="Nom" required="required" value="${section.getNom() }">
      <input type="hidden" name="idSection" value="${section.getId() }">
    </div>
    <!-- select -->
    <div class="form-group">
      <select class="form-control" name="theme">
      <c:forEach items="${listeThemes}" var="theme">
   		<c:choose>
   		<c:when test="${section.theme.getId() == theme.getId()}">
   			<option value="${theme.getId()}" selected> ${theme.getNom()}</option>
   		</c:when>
   		<c:otherwise><option value="${theme.getId()}"> ${theme.getNom()}</option></c:otherwise>
   		</c:choose>
      	
      </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <input type="number" class="form-control" name="nbQuestion" placeholder="Nombre de question" required="required" value="${section.getNb_questions() }">
    </div>
    <div class="form-group">
      <input type="number" class="form-control" name="duree" placeholder="Durée en minute" required="required" value="${section.getDuree() }">
    </div>
	<input class="btn btn-primary" type="submit" name="editSection" value="Modifier">
  </form>
</div>
