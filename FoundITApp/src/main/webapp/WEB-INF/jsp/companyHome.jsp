<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>FoundIT Co.</title>
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
          <a class="navbar-brand" href="/FoundITApp/home">FoundIT Co.</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
              <li><a href="/FoundITApp/logout">Sign out</a></li>
              <li><a href="newJobForm">Post a newJob</a></li>
        </ul>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
	<c:choose>
		<c:when test="${empty company}">
			<div class="container">
				<%@include file="/WEB-INF/jsp/error.jsp" %>
			</div>
		</c:when>
		<c:otherwise>	
			<div class="container">
				<h3>${company.name}</h3>
				<h4>${company.industry}</h4>
				<br/>
				<form class="form" action="companyUpdate" method="post">
					
					<div class="form-group">
						<label for="Employees">Number of Employees</label>
						<input name="employees" type="text" class="form-control" id="Employees" value="${company.employees}">
				  	</div>
				  	
					<div class="form-group">
						<label for="Location">Location</label>
						<input name="location" type="text" class="form-control" id="location" value="${company.location }">
				  	</div>
				  	<br/>
				  	<input type="hidden" name="id" value="${company.profileId}">
					<!-- <button type="submit" class="btn btn-default">Update details</button>-->
				  	
				</form>
				<br><br>
				<form class="form" action="companyViewApplications" method="post">
				<button type="submit" class="btn btn-default">View All Applications</button>
				</form>
			</div>

		</c:otherwise>
	</c:choose>
		
		
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>