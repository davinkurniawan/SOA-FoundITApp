
<div class="container">
	<c:choose>
		<c:when test="${empty user}">
			<%@include file="/WEB-INF/jsp/error.jsp"%>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="row page-header"></div>
				<div class="row">
					<h3>Welcome ${user.personalDetails.firstName}
						${user.personalDetails.lastName}!</h3><hr><br>
				</div>
				<div class="row">
					Current Job: ${user.currentPosition}
					<hr />
					Skills: ${user.skills}
					<hr />
					Experience: ${user.experience}
					<hr />
					Education: ${user.education}
					<hr />
					Address: ${user.personalDetails.address}
					<hr />
					Drivers License No: ${user.personalDetails.driverLicenseNo}
					<hr />
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>
