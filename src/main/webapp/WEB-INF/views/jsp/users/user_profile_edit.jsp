<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container" style="margin-top: 50px;">

		<h1><c:out value="${user.name}" /> User Profile</h1>
		<br />

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>	
		
		<c:if test="${user.status eq 0}">
			<div class="alert alert-info">
				You haven't confirmed your email address! Follow the link in registration email to do that or click 
				<a href="#"><strong>here</strong></a> to get another registration email.
			</div>
		</c:if>

		<form:form class="form-horizontal" method="post" modelAttribute="user">

			<form:hidden path="id" />

			<spring:bind path="name">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Name</label>
					<div class="col-sm-10">
						<form:input path="name" type="text" class="form-control" id="name" readonly="true"
							placeholder="Name" />
						<form:errors path="name" cssClass="text-danger" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<form:input path="email" class="form-control" id="email"
							placeholder="Email" />
						<form:errors path="email" cssClass="text-danger" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<form:password path="password" class="form-control" id="password" showPassword="true"
							placeholder="password" />
						<form:errors path="password"  cssClass="text-danger" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="confirmPassword">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Confirm Password</label>
					<div class="col-sm-10">
						<form:password path="confirmPassword" class="form-control" showPassword="true"
							id="password" placeholder="password" />
						<form:errors path="confirmPassword" cssClass="text-danger" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="firstName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">First Name</label>
					<div class="col-sm-10">
						<form:input path="firstName" class="form-control"
							id="firstName" placeholder="firstName" />
						<form:errors path="firstName" cssClass="text-danger" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="lastName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Last Name</label>
					<div class="col-sm-10">
						<form:input path="lastName" class="form-control"
							id="lastName" placeholder="lastName" />
						<form:errors path="lastName" cssClass="text-danger" />
					</div>
				</div>
			</spring:bind>
			
			<div class="form-group">
				<p class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Save changes</button>
					
				</p>
			</div>
		</form:form>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>