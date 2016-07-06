<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" method="POST" enctype="multipart/form-data" action="<%=request.getContextPath()%>/admin/gestionQuestion?addQuestion=Ajouter">
		<!-- text input -->
		<div class="form-group">
			<input type="text" class="form-control" name="nomQuestion" placeholder="Enonce" required="required">
			<div class="checkbox">
	            <label><input type="checkbox" name="typeQuestion">Multiple</label>
            </div>
            <div class="form-group">
            <input type="file" id="exampleInputFile" name="file">
            </div>
			<!-- select -->
			<div class="form-group">
				<select class="form-control" name="theme">
					<c:forEach items="${listeThemes}" var="theme">
						<option value="${theme.getId()}">${theme.getNom()}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<input type="submit" name="addQuestion" value="Ajouter">
	</form>
</div>