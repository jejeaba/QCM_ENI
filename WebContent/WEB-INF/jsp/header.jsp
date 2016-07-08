<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin-QCM | ENI</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <script src="https://use.fontawesome.com/3a61301776.js"></script>
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/AdminLTE.min.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/skin-blue.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/datatables/dataTables.bootstrap.css">
    <!-- Dialog -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/bootstrapDialog/bootstrap-dialog.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini">QCM</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b>QCM</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="<%= request.getContextPath() %>/img/user.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs"><c:if test="${ !empty sessionScope.admin }"><c:out value="${sessionScope.admin.nom} ${sessionScope.admin.prenom }" /></c:if></span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="<%= request.getContextPath() %>/img/user.jpg" class="img-circle" alt="User Image">

                <p>
                  <c:if test="${ !empty sessionScope.admin }"><c:out value="${sessionScope.admin.nom} ${sessionScope.admin.prenom }" /></c:if>
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="<%= request.getContextPath() %>/admin/deconnection" class="btn btn-default btn-flat">Deconnection</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<%= request.getContextPath() %>/img/user.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><c:if test="${ !empty sessionScope.admin }"><c:out value="${sessionScope.admin.nom} ${sessionScope.admin.prenom }" /></c:if></p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li><a href="<%= request.getContextPath() %>/admin/gestionCandidat"><i class="fa fa-users"></i> <span>Candidats</span></a></li>
        <li><a href="<%= request.getContextPath() %>/admin/gestionFormation"><i class="fa fa-link"></i> <span>Formations</span></a></li>
        <li><a href="<%= request.getContextPath() %>/admin/gestionTest"><i class="fa fa-check"></i> <span>Tests</span></a></li>
        <li><a href="<%= request.getContextPath() %>/admin/gestionSection"><i class="fa fa-pie-chart"></i> <span>Sections</span></a></li>
        <li><a href="<%= request.getContextPath() %>/admin/gestionTheme"><i class="fa fa-tags"></i> <span>Thèmes</span></a></li>
        <li><a href="<%= request.getContextPath() %>/admin/gestionQuestion"><i class="fa fa-question"></i> <span>Questions</span></a></li>
        
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

