
<div class="box-body">
  <form role="form" action="<%= request.getContextPath() %>/gestionFormation">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomFormation" placeholder="Nom" required="required">
    </div>

    <!-- select -->
    <div class="form-group">
      <select class="form-control" name="formateur" >
        <c:forEach items="${listeFormateurs}" var="formateur">
      	<option>${Formateur.getNom()}</option>
      </c:forEach>
      </select>
    </div>
    <input type="submit" name="addFormation" value="Ajouter">
  </form>
</div>