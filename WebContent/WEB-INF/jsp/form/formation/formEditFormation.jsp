<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" method="POST" action="<%= request.getContextPath() %>/admin/gestionFormation">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" placeholder="Nom" required="required" value="${formation.getNom() }">
  		<input type="hidden" name="idFormation" value="${formation.getId() }">
    </div>

    <!-- select -->
    <div class="form-group">
      <select class="form-control" name="formateur">
      <c:forEach items="${listeFormateurs}" var="formateur">
   		<c:choose>
   		<c:when test="${formation.responsable.getId() == formateur.getId()}">
   			<option value="${formateur.getId()}" selected> ${formateur.getNom()} ${formateur.getPrenom()}</option>
   		</c:when>
   		<c:otherwise><option value="${formateur.getId()}"> ${formateur.getNom()} ${formateur.getPrenom()}</option></c:otherwise>
   		</c:choose>
      	
      </c:forEach>
      </select>
    </div>
	<input class="btn btn-success btn-block" type="submit" name="addFormation" value="Modifier">
  </form>
</div>
