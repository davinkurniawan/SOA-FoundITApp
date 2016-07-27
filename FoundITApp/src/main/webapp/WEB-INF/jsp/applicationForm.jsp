<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Application</title>
</head>
<body style="padding-top: 70px;">
	<%@include file="/WEB-INF/jsp/navbar.jsp"%>
	<div class="container">
		<div class="form-group">
			<form role="form" method="POST" action="createApplication">
				<label for="coverletter">Write Your Cover Letter Here: <i
					class="glyphicon glyphicon-pencil"></i></label> <label id="counter">1000 characters left</label>
				<textarea onkeyup="textCounter(this,'counter',1000);"
					class="form-control" rows="10" id="coverletter"
					maxlength="1000" name="coverletter"></textarea><br>
				<button type="submit" class="btn btn-success">Apply Now</button>
				<input type="hidden" name="jobid" value="${jobid}">
				<script type="text/javascript">
                       function textCounter(field,field2,maxlimit)
                       {
                        var countfield = document.getElementById(field2);
                        if ( field.value.length > maxlimit ) {
                         field.value = field.value.substring( 0, maxlimit );
                         return false;
                        } else {
                         countfield.value = maxlimit - field.value.length;
                         document.getElementById('counter').innerHTML = countfield.value + ' characters left';
                        }
                       }
                </script>
			</form>
		</div>
	</div>
</html>