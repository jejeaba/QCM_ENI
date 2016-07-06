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
	<div class="contnaier">
		<button type="button" class="btn btn-info" data-widget="remove"
			title="Ajouter Question" onclick="add(event, this);">Ajouter
			Question</button>
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="gestionQuestions"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Enoncer</th>
							<th>Theme</th>
							<th>Type</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listeQuestions}" var="question">
							<tr>
								<td><a href="#">${question.getNom()}</a></td>
								<td>?</td>
								<td>
									<div class="tools">
										<a onclick="edit(event, this);" data-id="${question.getId()}" ><i class="fa fa-edit"></i> </a>
										<a onclick="remove(event, this);" data-id="${question.getId()}"><i class="fa fa-trash-o"></i></a>
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
<script src="<%= request.getContextPath() %>/js/script.js"></script>
<script>
	$(function() {
		$("#gestionQuestion").DataTable();
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