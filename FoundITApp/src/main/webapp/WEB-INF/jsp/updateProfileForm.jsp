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
	<%@include file="/WEB-INF/jsp/navbar.jsp"%>
    <div class="container">
		<form action="updateProfile" method="post">
			<div class="form-group">
				<label for="curjob">Current Job</label>
				<input name="curjob" type="text" class="form-control" id="curjob" value="${user.currentPosition}">
		  	</div>
    
			<div class="form-group">
				<label for="skills">Skills</label>
				<input name="skills" type="text" class="form-control" id="skills" value="${user.skills}">
		  	</div>
    		
			<div class="form-group">
				<label for="experience">Experience</label>
				<input name="experience" type="text" class="form-control" id="experience" value="${user.experience}">
		  	</div>
		  	
			<div class="form-group">
				<label for="education">Education</label>
				<input name="education" type="education" class="form-control" id="education" value="${user.education}">
		  	</div>
		  	<div class="form-group">
		  		<label for="address">Address</label>
		  		<input name="address" type="text" class="form-control" id="address" value="${user.personalDetails.address}"> 
		  	</div>
		  	<div class="form-group">
		  		<label for="driverslicenseno">Drivers License No.</label>
		  		<input name="dlno" type="text" class="form-control" id="dlno" value="${user.personalDetails.driverLicenseNo}"> 
		  	</div>	
			<button type="submit" class="btn btn-default">Update</button>
    	</form>
    </div>
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>