<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
  <form role="form" methode="POST" action="<%= request.getContextPath() %>/gestionFormation">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomFormation" placeholder="Nom" required="required">
    </div>

    <!-- select -->
    <div class="form-group">
      <select class="js-example-basic-single" name="formateur" >
        <c:forEach items="${listeFormateurs}" var="formateur">
      	<option value="${formateur.getId()}">${formateur.getNom()} ${formateur.getPrenom()}</option>
      </c:forEach>
      </select>
    </div>
    <input type="submit" name="addFormation" value="Ajouter">
  </form>
</div>