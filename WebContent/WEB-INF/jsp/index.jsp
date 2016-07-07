 <%@include file="header.jsp" %>

    <section class="content-header">
      <h1>Accueil </h1>
    </section>

    <section class="content">
    <div class="col-md-3 col-sm-6 col-xs-12">
          <a href="<%= request.getContextPath() %>/admin/gestionCandidat"><div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-users"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Candidats</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
       </a> </div>
		<div class="col-md-3 col-sm-6 col-xs-12">
          <a href="<%= request.getContextPath() %>/admin/gestionFormation"><div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-link"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Formations</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
       </a> </div>
       <div class="col-md-3 col-sm-6 col-xs-12">
          <a href="<%= request.getContextPath() %>/admin/gestionTest"><div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-check"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Tests</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
       </a> </div>
       <div class="col-md-3 col-sm-6 col-xs-12">
          <a href="<%= request.getContextPath() %>/admin/gestionSection"><div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-pie-chart"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Sections</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
       </a> </div>
       <div class="col-md-3 col-sm-6 col-xs-12">
          <a href="<%= request.getContextPath() %>/admin/gestionTheme"><div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-tags"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Thèmes</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
       </a> </div>
<div class="col-md-3 col-sm-6 col-xs-12">
          <a href="<%= request.getContextPath() %>/admin/gestionQuestion"><div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-question"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Questions</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
       </a> </div>
    </section>
  
  <%@include file="footer.jsp" %>
  