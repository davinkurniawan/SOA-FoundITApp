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
<body>
	<%@include file="/WEB-INF/jsp/navbar.jsp" %>
	<div class="container">
	<%@include file="/WEB-INF/jsp/userdetail.jsp" %>	
	<form method="POST" action="viewApplications" id="view"></form>
	<form method="POST" action="updateProfileForm" id="update"></form>
		<button type="submit" class="btn btn-success" form="view">View my applications</button>
		<button type="submit" class="btn btn-success" form="update">Update my profile</button>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>