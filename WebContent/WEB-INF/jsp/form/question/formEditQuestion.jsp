<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-body">
	<form role="form" method="POST" enctype="multipart/form-data" action="<%=request.getContextPath()%>/admin/gestionQuestion?addQuestion=Ajouter">
		<!-- text input -->
		<input type="hidden" name="id" value="${question.id}">
		<div class="form-group">
			<input type="text" class="form-control" value="${question.enonce}" name="nomQuestion" placeholder="Enonce" required="required">
		</div>
		
		<c:forEach var="i" begin="1" end="4">
   			<div class="row">
			<div class="form-group col-xs-9">
				<input type="text" class="form-control" name="reponses" value="<c:out value="${question.listReponse[i].libelle}"/>" placeholder="Réponse"/>
				<input type="hidden" name="idReponses" value="<c:choose>
															    <c:when test="${question.listReponse[i].id != 0}">
															       ${question.listReponse[i].id}
															    </c:when>
															    <c:otherwise>
															        0
															    </c:otherwise>
															</c:choose>">
			</div>
			<div class="form-group col-xs-3">
				<input type="checkbox" <c:if test="${question.listReponse[i].correct}"> checked </c:if> name="reponsesCheck_<c:out value="${i}"/>"><label for="reponsesCheck_<c:out value="${i}"/>"> Bonne réponse</label>
			</div>
		</div>
		</c:forEach>
		
		<div class="checkbox">
            <label><input type="checkbox" <c:if test="${question.type}"> checked </c:if> name="typeQuestion">Multiple</label>
        </div>
           
        <div class="form-group">
           <input type="file" id="exampleInputFile" name="file">
        </div>
		<!-- select -->
		<div class="form-group">
			<select class="form-control" name="theme" required>
				<c:forEach items="${listeThemes}" var="theme">
					<option <c:if test="${question.theme.id == theme.id}"> selected </c:if> value="${theme.getId()}">${theme.getNom()}</option>
				</c:forEach>
			</select>
		</div>
		<input type="submit" name="addQuestion" value="Editer">
	</form>
</div>

