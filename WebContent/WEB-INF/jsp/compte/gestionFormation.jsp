<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<section class="content-header">
	<h1>Gestion des formations</h1>
</section>
<script>
	var title = "Formation";
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
			title="Ajouter Formation" onclick="add(event, this);">Ajouter
			Formation</button>
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="gestionFormation"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Nom de la formation</th>
							<th>Formateurs</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listeFormations}" var="formation">
							<tr>
								<td><a onclick="edit(event, this);" data-id="${section.getId()}">${formation.getNom()}</a></td>
								<td>${formation.responsable.getNom()} ${formation.responsable.getPrenom()}</td>
								<td>
									<div class="tools">
										<a onclick="edit(event, this);" data-id="${formation.getId()}" ><i class="fa fa-edit fa-2x"></i> 
										<a onclick="supp(event, this);" data-id="${formation.getId()}"><i class="fa fa-trash-o fa-2x"></i></a>
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
		$("#gestionFormation").DataTable({
	    	"language":{
	    		 "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json"
	    	}
	    });
		
	});
</script>