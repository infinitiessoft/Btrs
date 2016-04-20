<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta charset="utf-8">
<title>BTRS</title>
<meta name="description" content="">
<meta name="author" content="" />
<link rel="shortcut icon" href="img/favicon.png" type="image/icon">
<link rel="icon" href="img/favicon.png" type="image/icon">
<link href="js/lib/bootstrap/dist/css/bootstrap-theme.min.css"
	rel="stylesheet" />
<link href="js/lib/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" />



</head>
<style type="text/css">
body
{
    background: url('http://www.monday-8am.com/wp-content/uploads/2015/08/Business-trip.jpg') fixed;
    background-size: cover;
    padding: 0;
    margin: 0;
}
 
  .btn 
  {
  background-color: orange;
   outline:0;
   border: 1px solid green;
   border-top:none;
   border-bottom:none;
   border-left:none;
   border-right:none;
   box-shadow:inset 2px -3px rgba(0,0,0,0.15);
  }
  .btn:focus
  {
   outline:0;
   -webkit-outline:0;
   -moz-outline:0;
  }
  .fullscreen_bg {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-size: cover;
    background-position: 50% 50%;
    background-image: url('http://i.imgur.com/h26izWg.jpg');
    background-repeat:repeat;
  }
  .login-form {
    max-width: 280px;
    padding: 15px;
    margin: 0 auto;
      margin-top:50px;
  }
  .login-form .login-form-heading, .login-form {
    margin-bottom: 10px;
  }
  .login-form .form-control {
    position: relative;
    font-size: 16px;
    height: auto;
    padding: 10px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
  }
  .login-form .form-control:focus {
    z-index: 2;
  }
  .login-form input[type="text"] {
    margin-bottom: -1px;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
    border-top-style: solid;
    border-right-style: solid;
    border-bottom-style: none;
    border-left-style: solid;
    border-color: #000;
  }
  .login-form input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
    border-top-style: none;
    border-right-style: solid;
    border-bottom-style: solid;
    border-left-style: solid;
    border-color: rgb(0,0,0);
    border-top:1px solid rgba(0,0,0,0.08);
  }
  .login-form-heading {
    color: #fff;
    text-align: center;
    text-shadow: 0 2px 2px rgba(0,0,0,0.5);
  }
</style>
<body>
	
	<div class="container">
	<div class="col-sm-3">
	</div>
	<div class="col-sm-6">
	<div class="login-form">
		<form role="form" action="<c:url value="/login" />" method="POST">
			<div class="form-group">
				 <input type="text" name="username"
					value="" class="form-control" placeholder="Username">
			</div>
			<div class="form-group">
				 <input type="password" name="password"
					value="" class="form-control" placeholder="Password" />
			</div>
​
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
​
			<div class="btn-group btn-block">
				<button type="submit" value="Login" id="loginButton" class="btn btn-default btn-block" >Login</button>
			</div>
			 
		</form>
	</div>
	</div>
	</div>
	
</body>
</html>