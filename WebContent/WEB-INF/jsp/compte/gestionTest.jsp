<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<section class="content-header">
	<h1>Gestion des Tests</h1>
</section>
<script>
	var title = "Test";
</script>
<section class="content">
	<div class="">
		<button type="button" class="btn btn-info" data-widget="remove"
			title="Ajouter un Test" onclick="add(event, this);">Ajouter
			Test</button>
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="gestionTest"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Nom du test</th>
							<th>Nombre de section</th>
							<th>Nombre de question</th>
							<th>Durée en minute</th>
							<th>Seuil acquis en %</th>
							<th>Seuil en cours d'acquisition en %</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listeTests}" var="test">
							<tr>
								<td><a href="#">${test.getNom()}</a></td>
								<td>${test.listSection.size}</td>
								<td>${test.listSection.size}</td>
								<td>?</td>
								<td>${test.getSeuilAcquis()}</td>
								<td>${test.getSeuilEnCoursAcquis()}</td>
								<td>
									<div class="tools">
										<a onclick="edit(event, this);" data-id="${test.getId()}" ><i class="fa fa-edit fa-2x"></i> </a>
										<a onclick="supp(event, this);" data-id="${test.getId()}"><i class="fa fa-trash-o fa-2x"></i></a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		</div>
</section>

<%@include file="../footer.jsp"%>
<script>
	$(function() {
		$("#gestionTest").DataTable();
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