<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<section class="content-header">
	<h1>Gestion des Themes</h1>
</section>
<script>
	var title = "Theme";
</script>
<section class="content">
	<div class="contnaier">
		<button type="button" class="btn btn-info" data-widget="remove"
			title="Ajouter un Theme" onclick="addTheme(event, this);">Ajouter
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
								<td>?</td>
								<td>
									<div class="tools">
										<a onclick="editTheme(event, this);" data-id-theme="${theme.getId()}" ><i class="fa fa-edit"></i> </a>
										<a onclick="deleteTheme(event, this);" data-id-theme="${theme.getId()}"><i class="fa fa-trash-o"></i></a>
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
function editTheme(e, dom){
	e.preventDefault();
	var idTheme = $(dom).data("id-theme");
	console.log(idTheme);
	$.ajax({
	  url: "<%= request.getContextPath() %>/view"+title,
	  method: 'POST',
	  data: {idTheme: idTheme, action: "edit"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			title: 'Modifier '+title,
	        message: view,
	        nl2br: false
	    });
		dialog.realize();
		dialog.getModalFooter().hide();
		dialog.open();
	});
	 
}
function addTheme(e, dom){
	e.preventDefault();
	var idTheme = $(dom).data("id-theme");
	console.log(idTheme);
	$.ajax({
	  url: "<%= request.getContextPath() %>/viewTheme",
	  method: 'POST',
	  data: {idTheme: idTheme,action: "add"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			title: 'Ajouter theme',
	        message: view,
	        nl2br: false
	    });
		dialog.realize();
		dialog.getModalFooter().hide();
		dialog.open();
	});
	 
}
function deleteTheme(e, dom){
	e.preventDefault();
	var idTheme = $(dom).data("id-theme");
	console.log(idTheme);
	$.ajax({
	  url: "<%= request.getContextPath() %>/viewTheme",
	  method: 'POST',
	  data: {idTheme: idTheme, action: "delete"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			type:BootstrapDialog.TYPE_DANGER,
			title: 'Suppression du theme',
			size: BootstrapDialog.SIZE_SMALL,
	        message: view,
	        nl2br: false
	    });
		dialog.realize();
		dialog.getModalFooter().hide();
		dialog.open();
	    });   
	 
}

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