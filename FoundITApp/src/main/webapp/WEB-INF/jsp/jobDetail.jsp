<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>${posting.positionTitle}</title>
</head>
<body style="padding-top:70px;">
	<%@include file="/WEB-INF/jsp/navbar.jsp" %>
	<div class="container">
		<c:choose>
			<c:when test="${empty posting}">
				<%@include file="/WEB-INF/jsp/error.jsp" %>
			</c:when>
			<c:otherwise>
				<form class="form-inline" action="apply" id="applyForm">
					<input type="hidden" name="jobid" value="${posting.jobId}">
				</form>
				<form class="form-inline" action="save" id="saveForm">
				</form>
				<h3>${posting.positionTitle}</h3><hr>
				Company: ${posting.companyname}<br><br>
				Details: ${posting.details}<br/><br/>
				Salary Rate: ${posting.salaryRate}<br/><br/>
				Location: ${posting.location}<br/><br/>
				Status: ${posting.status}<br/><br/><br/>
				<button type="submit" class="btn btn-default" form="applyForm">Apply for this job</button>
			</c:otherwise>
		</c:choose>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>