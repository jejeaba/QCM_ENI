<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<section class="content-header">
	<h1>Gestion des formations</h1>
</section>

<section class="content">
	<div class="contnaier">
		<button type="button" class="btn btn-info" data-widget="remove"
			title="Ajouter Formation" onclick="addFormation(event, this);">Ajouter
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
								<td><a href="#">${formation.getNom()}</a></td>
								<td>${formation.responsable.getNom()} ${formation.responsable.getPrenom()}</td>
								<td>
									<div class="tools">
										<a onclick="editFormation(event, this);" data-id-formation="${formation.getId()}" ><i class="fa fa-edit"></i> 
										<a onclick="deleteFormation(event, this);" data-id-formation="${formation.getId()}"><i class="fa fa-trash-o"></i></a>
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
function editFormation(e, dom){
	e.preventDefault();
	var idFormation = $(dom).data("id-formation");
	console.log(idFormation);
	$.ajax({
	  url: "<%= request.getContextPath() %>/viewFormation",
	  method: 'POST',
	  data: {idFormation: idFormation, action: "edit"}
	}).done(function(view) {
		BootstrapDialog.show({
			title: 'Modifier formation',
	        message: view,
	        nl2br: false,
	        buttons: [{
	            label: 'Modifier'
	        }]
	    });   
	});
	 
}
function addFormation(e, dom){
	e.preventDefault();
	var idFormation = $(dom).data("id-formation");
	console.log(idFormation);
	$.ajax({
	  url: "<%= request.getContextPath() %>/viewFormation",
	  method: 'POST',
	  data: {idFormation: idFormation,action: "add"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			title: 'Ajouter formation',
	        message: view,
	        nl2br: false
	    });
		dialog.realize();
		dialog.getModalFooter().hide();
		dialog.open();
	});
	 
}
function deleteFormation(e, dom){
	e.preventDefault();
	var idFormation = $(dom).data("id-formation");
	console.log(idFormation);
	$.ajax({
	  url: "<%= request.getContextPath() %>/viewFormation",
	  method: 'POST',
	  data: {idFormation: idFormation, action: "delete"}
	}).done(function(view) {
		BootstrapDialog.show({
			title: 'Suppresion',
	        message: view,
	        nl2br: false,
	        buttons: [{
	            label: 'Supprimer'
	        }]
	    });   
	});
	 
}

	$(function() {
		$("#gestionFormation").DataTable();
		//     $('#example2').DataTable({
		//       "paging": true,
		//       "lengthChange": false,
		//       "searching": false,
		//       "ordering": true,
		//       "info": true,
		//       "autoWidth": false
		//     });
// 		$(".js-example-basic-single").select2({
// 			  placeholder: "Responsable de formation",
// 			allowClear: true
// 			});
		
	});
</script>