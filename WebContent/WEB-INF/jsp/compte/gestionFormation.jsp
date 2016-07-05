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
			title="Ajouter Formation" data-original-title="Remove"
			data-toggle="modal" data-target="#ajoutFormation">Ajouter
			Formation</button>
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="gestionFormation"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Nom de la formation</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listeFormations}" var="formation">
							<tr>
								<td><a href="#">${formation.getNom()}</a></td>
								<td>
									<div class="tools">
										<a haref="#" onclick="editFormation(event, this);" data-id-formation="${formation.getId()}" ><i class="fa fa-edit"></i> 
										<a data-toggle="modal"data-target="#deleteFormation"><i class="fa fa-trash-o"></i></a>
									</div>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<!-- /.box-body -->
		</div>
		<!--popu suppresion formation -->
		<div class="modal modal-danger fade" id="deleteFormation"
			tabindex="-1" role="dialog" aria-labelledby="deleteFormationLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="deleteFormationLabel">Suppresion</h4>
					</div>
					<div class="modal-body">
						<p>Voulez-vous supprimer la formation CDI15 ?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-outline pull-left"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-outline">Supprimer</button>
					</div>
				</div>
			</div>
		</div>

		<!--popu ajout formation -->
		<div class="modal fade" id="ajoutFormation" tabindex="-1"
			role="dialog" aria-labelledby="ajoutFormationLabel">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="ajoutFormationLabel">Ajout
							Formation</h4>
					</div>
					<div class="modal-body">
						<%@include file="../form/formAjoutFormation.jsp"%>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default pull-left"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Ajouter</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>

		<!--popu modif formation -->
		<div class="modal fade" id="modif" tabindex="-1" role="dialog"
			aria-labelledby="modifLabel">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="ajoutLabel">Modif Formation</h4>
					</div>
					<div class="modal-body">
						<%@include file="../form/formModifFormation.jsp"%>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default pull-left"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Modifier</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
</section>

<%@include file="../footer.jsp"%>

<script>
function editFormation(e, dom){
	e.preventDefault();
	var idFormation = $(dom).data("id-formation");
	console.log(idFormation);
	$.ajax({
	  url: "<%= request.getContextPath() %>/viewEditFormation",
	  method: 'POST',
	  data: "idFormation=" + idFormation
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
		
	});
</script>