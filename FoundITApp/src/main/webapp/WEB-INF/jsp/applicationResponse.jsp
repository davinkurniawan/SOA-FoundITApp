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
		<c:choose>
			<c:when test="${not empty msg}">
				<div class="panel panel-danger">
					<div class="panel-heading"><h5>Application Failed</h5></div>
					<div class="panel-body">${msg}.</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="panel panel-success">
					<div class="panel-heading"><h5>Application Successful</h5></div>
					<div class="panel-body">Your application has been successful.</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>	
</html>