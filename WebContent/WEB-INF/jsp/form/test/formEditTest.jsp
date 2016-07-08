<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/select2/select2.min.css">
<div class="box-body">
  <form role="form" method="POST" action="<%= request.getContextPath() %>/admin/gestionTest">
    <!-- text input -->
    <div class="form-group">
      <input type="text" class="form-control" name="nomTest" placeholder="Nom du test" required="required" value="${test.getNom()}">
    </div>
     <div class="form-group">
      <select class="js-example-basic-multiple" style="width:100%" name="listeSections" multiple="multiple">
      <c:forEach items="${listeSections}" var="section">
      	<c:choose>
	   		<c:when test="${test.section.getId() == section.getId()}">
	   			<option value="${section.getId()}" selected> ${section.getNom()}</option>
	   		</c:when>
   			<c:otherwise><option value="${section.getId()}"> ${section.getNom()}</option></c:otherwise>
   		</c:choose>
      </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <input type="number" class="form-control" name="seuilAcquis" placeholder="seuil acquis" required="required" value="${test.getSeuilAcquis()}">
    </div>
    <div class="form-group">
      <input type="number" class="form-control" name="seuilEnCoursAcquis" placeholder="Seuil en cours d'acquisition" required="required" value="${test.getSeuilEnCoursAcquis()}">
    </div>
    <input type="submit" class="btn btn-success btn-block" name="addTest" value="Ajouter">
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