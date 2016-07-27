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
<title>Company Applications</title>
</head>
<body style="padding-top:70px;">
	<%@include file="/WEB-INF/jsp/navbar.jsp" %>
	<div class="container">
	<div class="row page-header"> <h3>Current Applications</h3> </div>
	<div class="row">
	<c:choose>
		<c:when test="${empty applications}">
			<h4>There is no applicants for your company!</h4>
		</c:when>
		<c:otherwise>
				
				<form class="form" action="autocheck" method="post">
				<span class="pull-right"><button type="submit" class="btn btn-default">Perform Auto Check</button></span>
				<br><br>
				</form>
			   	<table style="width:100%" cellpadding="10" class="table table-hover">
			   	<thead>
				   	<tr>
				   		<th align="left">
				   			Position Title
				   		</th>
				   		<th align="left">
				   			Applicant's Name
				   		</th>
				   		<th align="left">
				   			Current Position
				   		</th>
				   		<th align="left">
				   			Application Status
				   		</th>
				   		<th align="left">
				   			AutoCheck Message
				   		</th>
				   		<th align="left">
				   			AutoCheck Status
				   		</th>
				   	</tr>
			   	</thead>
			   	<tbody>
				   	<c:forEach var="i" items="${applications}">
				   	<tr>
				   		<td>${i.posting.positionTitle}</td>
				   		<td>${i.user.personalDetails.lastName}, ${i.user.personalDetails.firstName}</td>
				   		<td>${i.user.currentPosition}</td>
				   		<td>${i.status}</td>
				   		<td>${i.autoCheckResponse}</td>
				   		<td>${i.autoCheckDetails}</td>
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