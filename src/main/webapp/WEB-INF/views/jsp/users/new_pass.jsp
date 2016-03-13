<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<div id="signupbox" style=" margin-top: 70px"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Password Reset</div>
				</div>
				<div class="panel-body">
					<form:form id="signupform" modelAttribute="user" class="form-horizontal" role="form">

						<div id="signupalert" style="display: none"
							class="alert alert-danger">
							<p>Error:</p>
							<span></span>
						</div>

						<div class="form-group">
							<label for="password" class="col-md-3 control-label">Password</label>
							<div class="col-md-9">
								<form:input type="password" class="form-control" name="password" path="password"
									placeholder="Password"/>
								<form:errors path="password" cssClass="text-danger"/>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-md-3 control-label">Confirm password</label>
							<div class="col-md-9">
								<form:input type="password" class="form-control" name="confirmPassword" path="confirmPassword"
									placeholder="Confirm Password"/>
								<form:errors path="confirmPassword" cssClass="text-danger"/>
							</div>
						</div>

						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<button id="btn-signup" type="submit" class="btn btn-success">
									Sign Up
								</button>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Changed your mind? <a href="<c:url value='/login' />">Sign In Here with the old password</a>
								</div>
							</div>
						</div>

					</form:form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>