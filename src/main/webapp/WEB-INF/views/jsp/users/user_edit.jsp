<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

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
			
			<spring:bind path="role">
		  		<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Role</label>
				<div class="col-sm-10">
					<form:radiobuttons path="role" items="${roleList}" 
                                element="label class='radio-inline'" />
					<br />
					<form:errors path="role" cssClass="text-danger" />
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

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>