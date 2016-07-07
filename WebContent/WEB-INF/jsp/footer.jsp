 </div>
  <!-- /.content-wrapper -->
<div class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body…</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
      AdminQCM
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2016 <a href="#">ENI</a>.</strong> All rights reserved.
  </footer>
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="<%= request.getContextPath() %>/js/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
<!-- Bootstrap Dialog -->
<script src="<%= request.getContextPath() %>/plugins/bootstrapDialog/bootstrap-dialog.min.js"></script>
<!-- AdminLTE App -->
<script src="<%= request.getContextPath() %>/js/app.min.js"></script>
<!-- DataTables -->
<script src="<%= request.getContextPath() %>/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<%= request.getContextPath() %>/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/js/script.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var pathname = window.location.pathname;
	$('.sidebar-menu > li > a[href="'+pathname+'"]').parent().addClass('active');
})
</script>

</body>
</html>
