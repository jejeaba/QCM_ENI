<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="../header.jsp" %>
 <!-- Select2 -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/select2/select2.min.css">

    <section class="content-header">
      <h1>Gestion des formations</h1>
    </section>

    <section class="content">
    	<div class="contnaier">
    		<button type="button" class="btn btn-info" data-widget="remove" title="Ajouter Formation" data-original-title="Remove" data-toggle="modal" data-target="#ajoutStagiaire">Ajouter Stagiaire</button>
    		<div class="box">
            <!-- /.box-header -->
            <div class="box-body">
              <table id="gestionFormation" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Nom</th>
                  <th>Prénom</th>
                  <th>Tests</th>
                  <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>Halais</td>
                  <td>Jérémy</td>
                  <td><div class="tools">
                  	<a data-toggle="modal" data-target="#ajoutTest"><i class="fa fa-file-text-o fa-2x"></i></a></div>
                  </td>
                  <td><div class="tools">
                  	<a data-toggle="modal" data-target="#ResultatStagiaire"><i class="fa fa-list-alt fa-2x"></i></a>
                    <a data-toggle="modal" data-target="#modifStagiaire"><i class="fa fa-edit fa-2x"></i></a>
                    <a data-toggle="modal" data-target="#deleteStagiaire"><i class="fa fa-trash-o fa-2x"></i></a>
                  </div></td>
                </tr>
                </tbody>
              </table>
              <div >
              <button type="button" class="btn btn-info col-sm-3 " data-widget="remove" title="Affecter les tests" data-original-title="Remove">Affecter les tests à tout la promo</button>
            	<select class="js-example-basic-multiple col-sm-7 col-sm-offset-1" multiple="multiple">
				  <option value="AL">Test1</option>
				  <option value="WY">Test1</option>
				  <option value="AL">Test1</option>
				  <option value="WY">Test1</option>
				  <option value="AL">Test1</option>
				  <option value="WY">Test1</option>
				  <option value="AL">Test1</option>
				  <option value="WY">Test1</option>
				  <option value="AL">Test1</option>
				  <option value="WY">Test1</option>
				  <option value="AL">Test1</option>
				  <option value="WY">Test1</option>
				</select>
              </div>
              
            </div>
            <!-- /.box-body -->
    	</div>

        </div>
    </section>

  <%@include file="../footer.jsp" %>
  <!-- Select2 -->
  <script src="<%= request.getContextPath() %>/plugins/select2/select2.min.js"></script>
  
    <script>
  $(function () {
    $("#gestionFormation").DataTable();
//     $('#example2').DataTable({
//       "paging": true,
//       "lengthChange": false,
//       "searching": false,
//       "ordering": true,
//       "info": true,
//       "autoWidth": false
//     });
  
  $(".js-example-basic-multiple").select2({
	  placeholder: "Tests"
  } );

  });

</script>