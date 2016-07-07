<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" method="POST" action="<%= request.getContextPath() %>/admin/gestionFormation">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomFormation" placeholder="Nom" required="required">
    </div>

    <!-- select -->
   <div class="form-group">
      <select class="form-control">
      <c:forEach items="${listeFormateurs}" var="formateur">
      	<option>${formateur.getNom()} ${formateur.getPrenom()}</option>
      </c:forEach>
      </select>
    </div>
    <input type="submit" name="addFormation" value="Ajouter">
  </form>
</div>