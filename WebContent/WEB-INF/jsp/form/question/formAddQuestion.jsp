<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" method="POST" enctype="multipart/form-data" action="<%=request.getContextPath()%>/admin/gestionQuestion?addQuestion=Ajouter">
		<!-- text input -->
		<div class="form-group">
			<input type="text" class="form-control" name="nomQuestion" placeholder="Enonce" required="required">
		</div>
		
		<c:forEach var="i" begin="1" end="4">
   			<div class="row">
			<div class="form-group col-xs-9">
				<input type="text" class="form-control" name="reponses" placeholder="Réponse"/>
			</div>
			<div class="form-group col-xs-3">
				<input type="checkbox" name="reponsesCheck_<c:out value="${i}"/>"><label for="reponsesCheck_<c:out value="${i}"/>"> Bonne réponse</label>
			</div>
		</div>
		</c:forEach>
		
		<div class="checkbox">
            <label><input type="checkbox" name="typeQuestion">Multiple</label>
        </div>
           
        <div class="form-group">
           <input type="file" id="exampleInputFile" name="file" required>
        </div>
		<!-- select -->
		<div class="form-group">
			<select class="form-control" name="theme" required>
				<c:forEach items="${listeThemes}" var="theme">
					<option value="${theme.getId()}">${theme.getNom()}</option>
				</c:forEach>
			</select>
		</div>
		<input type="submit" class="btn btn-success btn-block" name="addQuestion" value="Ajouter">
	</form>
</div>

