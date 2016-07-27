<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Advanced Search</title>
</head>
<body style="padding-top:70px;">
	<%@include file="/WEB-INF/jsp/navbar.jsp" %>
	<div class="container">
		<form action="searchJobs">
			
			<div class="form-group">
				<label for="location">Location</label>
				<input name="location" type="text" class="form-control" id="location" placeholder="Location">
			</div>
			<div class="form-group">
				<label for="company">Company</label>
				<input name="company" type="text" class="form-control" id="company" placeholder="Company">
			</div>
			<div class="form-group">
				<label for="postiontitle">Position Title</label>
				<input name="positiontitle" type="text" class="form-control" id="positiontitle" placeholder="Position Title">
			</div>
			<div class="form-group">
				<label for="salaryrate">Salary Rate</label>
				<input name="salaryrate" type="text" class="form-control" id="salaryrate" placeholder="Salary Rate">
			</div>
			<div class="form-group">
				<label for="details">Details</label>
				<input name="details" type="text" class="form-control" id="details" placeholder="Details">
			</div>
			
			<div class="radio">
				<label>
					<input type="radio" name="status" id="optionsRadios1" value="open" checked>
					Open
				</label>
			</div>
			<div class="radio">
				<label>
					<input type="radio" name="status" id="optionsRadios2" value="close">
					Close
				</label>
			</div>
			<div class="radio">
				<label>
					<input type="radio" name="status" id="optionsRadios3" value="">
					Any
				</label>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
