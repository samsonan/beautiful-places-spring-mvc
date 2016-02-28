<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

	<div class="container">
		<div id="loginbox" style="margin-top: 70px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div style="padding-top: 20px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					<c:url var="loginUrl" value="/login" />
					<form id="loginform" action="${loginUrl}" method="post" class="form-horizontal" role="form">

						<c:if test="${param.error != null}">
                        	<div class="alert alert-danger">
                            	<p>Invalid username and password.</p>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                        	<div class="alert alert-success">
                            	<p>You have been logged out successfully.</p>
                            </div>
                        </c:if>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="text" class="form-control" name="username" value=""
								placeholder="username or email">
						</div>

						<div style="margin-bottom: 10px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="password"
								placeholder="password">
						</div>

							<div class="checkbox">
								<label> <input id="login-remember" type="checkbox"
									name="remember-me" value="1"> Remember me
								</label>
							</div>


						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">
								<input type="submit" class="btn btn-success"> <a
									id="btn-fblogin" href="#" class="btn btn-primary">Login
									with Facebook</a>

							</div>
						</div>

						<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                                 
						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Don't have an account! <a href="<c:url value='/register' />">Sign Up Here </a>
								</div>
								<div
									style="padding-top: 15px; font-size: 85%">
									Forgot you password?! <a href="<c:url value='/restore' />">Restore It!</a>
								</div>
							</div>
						</div>
					</form>



				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>