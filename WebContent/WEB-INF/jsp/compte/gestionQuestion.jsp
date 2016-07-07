<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<section class="content-header">
	<h1>Gestion des Questions</h1>
</section>
<script>
	var title = "Question";
</script>

<section class="content">
	  <c:if test="${not empty success}">
			<div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4><i class="icon fa fa-check"></i> Success!</h4>
                ${success}
              </div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4><i class="icon fa fa-warning"></i> Erreur!</h4>
                ${error}
              </div>
		</c:if>
		<button type="button" class="btn btn-info" data-widget="remove"
			title="Ajouter Question" onclick="add(event, this, BootstrapDialog.SIZE_NORMAL);">Ajouter
			Question</button>
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="gestionQuestions"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Enoncé</th>
							<th>Theme</th>
							<th>Type</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listeQuestions}" var="question">
							<tr>
								<td><a onclick="edit(event, this, BootstrapDialog.SIZE_NORMAL);" data-id="${question.getId()}" >${question.getEnonce()}</a></td>
								<td>${question.theme.getNom()}</td>
								<td>${question.getTypeFormat()}</td>
								<td>
									<div class="tools">
										<a onclick="edit(event, this, BootstrapDialog.SIZE_NORMAL);" data-id="${question.getId()}" ><i class="fa fa-edit fa-2x"></i> </a>
										<a onclick="supp(event, this);" data-id="${question.getId()}"><i class="fa fa-trash-o fa-2x"></i></a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</section>

<%@include file="../footer.jsp"%>
<script>
	$(function() {
		$("#gestionQuestions").DataTable({
	    	"language":{
	    		 "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json"
	    	}
	    });
		
	});
</script>