<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Registration Page</title>
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
  <!-- iCheck -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition register-page">
<div class="register-box">
  <div class="register-logo">
    <a href=""><b>Admin</b>QCM</a>
  </div>

  <div class="register-box-body">
    <p class="login-box-msg"></p>

    <form action="<%= request.getContextPath() %>/inscription" method="post">
      <div class="form-group has-feedback">
        <input type="text" class="form-control" name="nom" required placeholder="Nom">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="email" class="form-control" name="email" required placeholder="Email">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" required name="password" placeholder="Mot de passe">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password"  class="form-control" required name="password2" placeholder="Mot de passe">
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <select name="role" id="" class="form-control" required>
        	<option value="formateur">Formateur</option>
        	<option value="cellulerecrutement">Cellule de recrutement</option>
        </select>
      </div>
      
      <div class="row">
        <!-- /.col -->
        <div class="col-xs-5">
          <button type="submit" name="submit" value="submit" class="btn btn-primary btn-block btn-flat">S'enregistrer</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

    <a href="<%= request.getContextPath() %>/login" class="text-center">J'ai d�j� un compte</a>
  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 2.2.3 -->
<script src="<%= request.getContextPath() %>/js/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="<%= request.getContextPath() %>/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>
