<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Post a new job</title>
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
              <li><a href="/FoundITApp/companyHome">Cancel</a></li>
        </ul>
      </div>
    </nav>
    
    <div class="container">
		<c:if test="${not empty emptyparam}">
			<div class="panel panel-danger">
				<div class="panel-heading"><h3>New Posting Failed</h3></div>
				<div class="panel-body">
					<br/>Please fill in all values.<br/>
				</div>
			</div>
		</c:if>
		<form action="postNewJob" method="post">
			<input type="hidden" name="companyid" value="${company.profileId }">
			<h3>Job Details</h3>
			<div class="form-group">
				<label for="Positiontitle">Position Title</label>
				<input name="positiontitle" type="text" class="form-control" id="Positiontitle" placeholder="Position Title">
		  	</div>
		  	
			<div class="form-group">
				<label for="Salaryrate">Salary Rate</label>
				<input name="salaryrate" type="text" class="form-control" id="Salaryrate" placeholder="Salary Rate">
		  	</div>
		  	
			<div class="form-group">
				<label for="Location">Location</label>
				<input name="location" type="text" class="form-control" id="location" placeholder="Location">
		  	</div>
		  	
			<label for="Details">Details</label>
			<textarea name="details" rows="4" class="form-control" id="Details" placeholder="Details"></textarea>
		  	<br/>
			<button type="submit" class="btn btn-default">Post new job</button>
		</form>
	</div>
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>