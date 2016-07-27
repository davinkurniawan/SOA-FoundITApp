<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Search Results</title>
</head>
<body style="padding-top:70px;">
	<%@include file="/WEB-INF/jsp/navbar.jsp" %>
	<div class="container">
	<div class="row page-header"> <h3>Search Results</h3> </div>
	<div class="row">
	<c:choose>
		<c:when test="${empty jobs}">
			<h4>Sorry, no matching datasets found!</h4>
		</c:when>
		<c:otherwise>
			
			   	<table style="width:100%" cellpadding="10" class="table table-hover">
			   	<thead>
				   	<tr>
				   		<th align="left">
				   			Position Title
				   		</th>
				   		<th align="left">
				   			Company
				   		</th>
				   		<th align="left">
				   			Location
				   		</th>
				   		<th align="left">
				   			Status
				   		</th>
				   	</tr>
			   	</thead>
			   	<tbody>
				   	<c:forEach var="i" items="${jobs}">
				   	<tr onclick="javascript:window.location.href='http://localhost:8080/FoundITApp/jobdetail?id=${i.jobId}&company=${i.companyname}'; return false;">
				   		<td>${i.positionTitle}</td>
				   		<td>${i.companyname}</td>
				   		<td>${i.location}</td>
				   		<td>${i.status}</td>
				   	</tr>
				   	</c:forEach>
			   	</tbody>
			   	</table>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
</body>
</html>