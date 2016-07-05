<div class="box-body">
  <form role="form">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" placeholder="Nom" required="required" value="${formation.getNom() }">
    </div>

    <!-- select -->
    <div class="form-group">
      <select class="form-control">
      <c:forEach items="${listeFormateurs}" var="formateur">
      	<option>${Formateur.getNom()}</option>
      </c:forEach>
      </select>
    </div>

  </form>
</div>
