<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Beautiful Places</title>

<spring:url value="/resources/core/css/main.css" var="mainCss" />
<spring:url value="/resources/core/css/sign-in.css" var="signCss" />
<spring:url
	value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	var="bootstrapCss" />

<!-- Bootstrap core CSS -->
<link href="${bootstrapCss}" rel="stylesheet" />
<!-- Custom project CSS -->
<link href="${mainCss}" rel="stylesheet" />
<link href="${signCss}" rel="stylesheet" />

</head>
<body>

	<div class="container">
		<jsp:include page="nav_bar.jsp" />
	</div>

	<div class="container" style="margin-top: 50px;">

		<c:choose>
			<c:when test="${userForm['new']}">
				<h1>Add User</h1>
			</c:when>
			<c:otherwise>
				<h1>Update User</h1>
			</c:otherwise>
		</c:choose>
		<br />

		<spring:url value="/users" var="userActionUrl" />

		<form:form class="form-horizontal" method="post"
			modelAttribute="userForm" action="${userActionUrl}">

			<form:hidden path="id" />

			<spring:bind path="name">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Name</label>
					<div class="col-sm-10">
						<form:input path="name" type="text" class="form-control" id="name"
							placeholder="Name" />
						<form:errors path="name" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<form:input path="email" class="form-control" id="email"
							placeholder="Email" />
						<form:errors path="email" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<form:password path="password" class="form-control" id="password"
							placeholder="password" />
						<form:errors path="password" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="confirmPassword">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">confirm Password</label>
					<div class="col-sm-10">
						<form:password path="confirmPassword" class="form-control"
							id="password" placeholder="password" />
						<form:errors path="confirmPassword" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="firstName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">First Name</label>
					<div class="col-sm-10">
						<form:input path="firstName" class="form-control"
							id="firstName" placeholder="firstName" />
						<form:errors path="firstName" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="lastName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Last Name</label>
					<div class="col-sm-10">
						<form:input path="lastName" class="form-control"
							id="lastName" placeholder="lastName" />
						<form:errors path="lastName" class="control-label" />
					</div>
				</div>
			</spring:bind>
			
			<spring:bind path="role">
		  		<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Role</label>
				<div class="col-sm-10">
					<form:radiobuttons path="role" items="${roleList}" 
                                element="label class='radio-inline'" />
					<br />
					<form:errors path="role" class="control-label" />
				</div>
		  </div>
			</spring:bind>
		
			<div class="form-group">
				<p class="col-sm-offset-2 col-sm-10">
					<c:choose>
						<c:when test="${userForm['new']}">
							<button type="submit" class="btn btn-primary">Add
							</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-primary">Update
							</button>
						</c:otherwise>
					</c:choose>
					<a href="<c:url value='/users/list'/>" class="btn btn-default">Cancel</a>
					
				</p>
			</div>
		</form:form>

	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

	<spring:url value="/resources/core/js/main.js" var="mainJs" />
	<spring:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"
		var="bootstrapJs" />

	<script src="${mainJs}"></script>
	<script src="${bootstrapJs}"></script>

</body>
</html>