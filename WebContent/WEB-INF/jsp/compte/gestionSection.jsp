<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<section class="content-header">
	<h1>Gestion des Sections</h1>
</section>
<script>
	var title = "Section";
</script>
<section class="content">
	<div class="">
		<button type="button" class="btn btn-info" data-widget="remove"
			title="Ajouter Section" onclick="add(event, this);">Ajouter
			Section</button>
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="gestionSection"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Nom de la section</th>
							<th>Thèmes</th>
							<th>Nombre de questions</th>
							<th>Durée en minute</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listeSections}" var="section">
							<tr>
								<td><a href="#">${section.getNom()}</a></td>
								<td>${section.theme.getNom()}</td>
								<td>${section.getNb_Questions()}</td>
								<td>${section.getDuree()}</td>
								<td>
									<div class="tools">
										<a onclick="edit(event, this);" data-id="${section.getId()}" ><i class="fa fa-edit fa-2x"></i> </a>
										<a onclick="supp(event, this);" data-id="${section.getId()}"><i class="fa fa-trash-o fa-2x"></i></a>
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
<script src="<%= request.getContextPath() %>/js/script.js"></script>
<script>
	$(function() {
		$("#gestionSection").DataTable();
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