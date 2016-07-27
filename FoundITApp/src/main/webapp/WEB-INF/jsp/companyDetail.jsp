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
		<c:choose>
			<c:when test="${empty company}">
				<%@include file="/WEB-INF/jsp/error.jsp" %>
			</c:when>
			<c:otherwise>
				<h3>${company.name}</h3>
				<h4>${company.industry}</h4>
				Employees: ${company.employees}<br/>
				Location: ${company.location}<br/>
			</c:otherwise>
		</c:choose>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>