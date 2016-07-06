<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" methode="POST" action="<%= request.getContextPath() %>/admin/gestionQuestion">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomQuestion" placeholder="Nom" required="required" value="${question.getNom() }">
      <input type="hidden" nom="idQuestion" value="${question.getId() }">
      <div class="checkbox">
	            <label><input type="checkbox" name="type">Multiple</label>
            </div>
			<!-- select -->
    <div class="form-group">
      <select class="form-control" name="">
      <c:forEach items="${listeQuestions}" var="question">
   		<c:choose>
   		<c:when test="${question.theme.getId() == theme.getId()}">
   			<option value="${theme.getId()}" selected> ${theme.getNom()}</option>
   		</c:when>
   		<c:otherwise><option value="${theme.getId()}"> ${theme.getNom()}</option></c:otherwise>
   		</c:choose>
      	
      </c:forEach>
      </select>
    </div>
    </div>
	<input class="btn btn-default" type="submit" name="addQuestion" value="Modifier">
  </form>
</div>
