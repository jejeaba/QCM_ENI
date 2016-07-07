<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/select2/select2.min.css">
<div class="box-body">
  <form role="form" method="POST" action="<%= request.getContextPath() %>/admin/gestionTest">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomTest" placeholder="Nom du test" required="required">
    </div>
     <div class="form-group">
      <select class="js-example-basic-multiple" multiple="multiple" style="width:100%" name="listeSections">
      <c:forEach items="${listeSections}" var="section">
      	<option value="${section.getId()}">${section.getNom()}</option>
      </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <input type="number" class="form-control" name="seuilAcquis" placeholder="seuil acquis" required="required">
    </div>
    <div class="form-group">
      <input type="number" class="form-control" name="seuilEnCoursAcquis" placeholder="Seuil en cours d'acquisition" required="required">
    </div>
    <input type="submit" name="addTest" value="Ajouter">
  </form>
</div>
 <script src="<%= request.getContextPath() %>/plugins/select2/select2.min.js"></script>
<script>
$(function() {
	$(".js-example-basic-multiple").select2({
		  placeholder: ""
		});
});
</script>