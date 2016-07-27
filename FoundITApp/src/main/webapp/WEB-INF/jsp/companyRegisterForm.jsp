<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Sign up today!</title>
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
              <li><a href="/FoundITApp/companyLogin">Login</a></li>
        </ul>
      </div>
    </nav>
    
    <div class="container">
    	<h2 align="center">Sign up your Company</h2>
			<c:if test="${not empty userexist}">
				<div class="panel panel-danger">
					<div class="panel-heading"><h3>Register Failed</h3></div>
					<div class="panel-body">
						<b>The email you've entered already exist in our records.
						<br/>Please login or use a different email address.</b>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty emptyparam}">
				<div class="panel panel-danger">
					<div class="panel-heading"><h3>Register Failed</h3></div>
					<div class="panel-body">
						<br/>Please fill in all values.<br/>
					</div>
				</div>
			</c:if>
		<form action="companyRegister" method="post">
			<h3>Company Details</h3>
		  	
			<div class="form-group">
				<label for="Name">Company Name</label>
				<input name="name" type="text" class="form-control" id="Name" placeholder="Company Name">
		  	</div>
		  	
			<div class="form-group">
				<label for="Industry">Industry</label>
				<input name="industry" type="text" class="form-control" id="Industry" placeholder="Industry">
		  	</div>
		  	
			<div class="form-group">
				<label for="Employees">Number of Employees</label>
				<input name="employees" type="text" class="form-control" id="Employees" placeholder="Number of Employees">
		  	</div>
		  	
			<div class="form-group">
				<label for="Location">Location</label>
				<input name="location" type="text" class="form-control" id="location" placeholder="Location">
		  	</div>
			<hr>
			<h4>Manager Details</h4>
			<div class="form-group">
				<label for="Firstname">Given Name</label>
				<input name="firstname" type="text" class="form-control" id="Firstname" placeholder="Given Name">
		  	</div>
    
			<div class="form-group">
				<label for="Lastname">Family Name</label>
				<input name="lastname" type="text" class="form-control" id="Lastname" placeholder="Family Name">
		  	</div>
    		
			<div class="form-group">
				<label for="ManagerEmail">Manager Email Address</label>
				<input name="email" type="email" class="form-control" id="ManagerEmail" placeholder="Email">
		  	</div>
		  	
			<div class="form-group">
				<label for="Password">Password</label>
				<input name="password" type="password" class="form-control" id="Password" placeholder="Password">
		  	</div>
		  	<br>
			<button type="submit" class="btn btn-default">Sign up</button>
		</form>
	</div>
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>