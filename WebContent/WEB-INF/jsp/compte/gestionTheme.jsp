<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<section class="content-header">
	<h1>Gestion des Thèmes</h1>
</section>
<script>
	var title = "Theme";
</script>
<section class="content">
	<div class="contnaier">
		<button type="button" class="btn btn-info" data-widget="remove"
			title="Ajouter un Theme" onclick="add(event, this);">Ajouter
			Theme</button>
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="gestionTheme"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Nom du theme</th>
							<th>Nombre de questions</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listeThemes}" var="theme">
							<tr>
								<td><a href="#">${theme.getNom()}</a></td>
								<td>${theme.listQuestion.size}</td>
								<td>
									<div class="tools">
										<a onclick="edit(event, this);" data-id="${theme.getId()}" ><i class="fa fa-edit fa-2x"></i> </a>
										<a onclick="supp(event, this);" data-id="${theme.getId()}"><i class="fa fa-trash-o fa-2x"></i></a>
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
		$("#gestionTheme").DataTable();
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