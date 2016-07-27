<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Login</title>
</head>
<body style="padding-top:70px;">
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="http://localhost:8080/FoundITApp">FoundIT Co.</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
              <li><a href="/FoundITApp/companyLogin">Company Login</a></li>
        </ul>
      </div>
    </nav>
    <div class="container">
    	<h2 align="center">User Login</h2>
		<form action="processLogin" method="post" id="loginForm">
			<c:if test="${not empty loginfail}">
				<div class="panel panel-danger">
					<div class="panel-heading"><h3>Login Failed</h3></div>
					<div class="panel-body">
						<b>The email and password you entered did not match our records. 
						<br/>Please try again.</b>
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="Email">Email Address</label>
				<input name="email" type="email" class="form-control" id="Email" placeholder="Email">
		  	</div>
			<div class="form-group">
				<label for="Password">Password</label>
				<input name="password" type="password" class="form-control" id="Password" placeholder="Password">
		  	</div>
			<div class="checkbox-inline">
				<label><input type="checkbox" name="remember"> Remember me&nbsp;.</label>
			</div>
			<label class="text-right"><a class="forgot" href="/forgot">Forgot password?</a></label>
			<br/>
		</form>
		<form method="post" action="registerForm" id="registerForm">
		</form>
		<button type="submit" class="btn btn-default" form="loginForm">Submit</button>
		<button type="submit" class="btn btn-default" form="registerForm">Sign Up</button>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>