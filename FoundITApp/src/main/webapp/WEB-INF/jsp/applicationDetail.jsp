<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>${application.posting.positionTitle}</title>
</head>
<body style="padding-top: 70px;">
	<%@include file="/WEB-INF/jsp/navbar.jsp"%>
	<div class="container">
	<c:choose>
		<c:when test="${empty updatemsg}">
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${error == '1'}">
					<div class="panel panel-danger">
					<div class="panel-heading"><h5>Application Update</h5></div>
					<div class="panel-body">${updatemsg}.</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="panel panel-success">
					<div class="panel-heading"><h5>Application Update</h5></div>
					<div class="panel-body">${updatemsg}.</div>
					</div>
				</c:otherwise>
			</c:choose>
			
		</c:otherwise>
	</c:choose>
		<h3>${application.posting.positionTitle}</h3>
		<hr>
		Company: ${application.posting.companyname}<br>
		<br> Details: ${application.posting.details}<br />
		<br /> Salary Rate: ${application.posting.salaryRate}<br />
		<br /> Location: ${application.posting.location}<br />
		<br /> Application Status: ${application.status}<br />
		<br />
		<c:choose>
			<c:when test="${empty modify}">
				<div class="panel panel-default">
					<div class="panel-heading">Cover Letter</div>
					<div class="panel-body">${application.coverLetter}</div>
				</div>
				<div class="form-group">
					<form role="form" method="POST" action="updateApplication">
						<input type="hidden" name="appid" value="${application.appId}">
						<input type="hidden" name="status" value="${application.status}">
						<input type="hidden" name="coverletter" value="${application.coverLetter}">
						<button type="submit" class="btn btn-default">Update</button>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="form-group">
					<form role="form" method="POST" action="update">
						<input type="hidden" name="appid" value="${application.appId}">
						<label for="coverletter">Write Your Cover Letter Here: <i
							class="glyphicon glyphicon-pencil"></i></label> <label id="counter">${length}
							characters left</label>
						<textarea onkeyup="textCounter(this,'counter',1000);"
							class="form-control" rows="10" id="coverletter" maxlength="1000"
							name="coverletter">${application.coverLetter}</textarea>
						<br>
						<button type="submit" class="btn btn-success">Update</button>
						<input type="hidden" name="jobid" value="${jobid}">
						<script type="text/javascript">
							function textCounter(field, field2, maxlimit) {
								var countfield = document
										.getElementById(field2);
								if (field.value.length > maxlimit) {
									field.value = field.value.substring(0,
											maxlimit);
									return false;
								} else {
									countfield.value = maxlimit
											- field.value.length;
									document.getElementById('counter').innerHTML = countfield.value
											+ ' characters left';
								}
							}
						</script>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>