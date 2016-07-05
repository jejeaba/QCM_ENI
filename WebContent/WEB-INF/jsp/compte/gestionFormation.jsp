<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="../header.jsp" %>

    <section class="content-header">
      <h1>Gestion des formations</h1>
    </section>

    <section class="content">
    	<div class="contnaier">
    		<button type="button" class="btn btn-info" data-widget="remove" data-toggle="tooltip" title="Ajouter Formation" data-original-title="Remove">Ajouter Formation</button>
    		<div class="box">
            <div class="box-header">
              <h3 class="box-title">Data Table With Full Features</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="gestionFormation" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Nom de la formation </th>
                  <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td><a href="#">CDI 15</a></td>
                  <td><div class="tools">
                    <a href="#"><i class="fa fa-edit"></i>
                    <a data-toggle="modal" data-target="#delete"><i class="fa fa-trash-o"></i></a>
                  </div></td>
                </tr>
                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
    	</div>
    	
        <div class="modal modal-danger fade" id="delete"  tabindex="-1" role="dialog" aria-labelledby="deleteLabel">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="deleteLabel">Suppresion</h4>
              </div>
              <div class="modal-body">
                <p>Voulez-vous supprimer la formation </p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-outline">Save changes</button>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
    </section>

  <%@include file="../footer.jsp" %>
  
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
  });
</script>