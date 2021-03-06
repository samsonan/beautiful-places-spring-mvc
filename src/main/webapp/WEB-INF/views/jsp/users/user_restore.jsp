<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">
	
		<div id="restorebox" style="margin-top: 70px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Restore Password</div>
				</div>

				<div style="padding-top: 20px" class="panel-body">

					<c:if test="${not empty msg}">
						<div class="alert alert-${css} alert-dismissible" role="alert">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							${msg}
						</div>
					</c:if>

					<div style="display: none" id="restore-alert"
						class="alert alert-danger col-sm-12"></div>

					<h3> Enter your name OR email </h3>
					<c:url var="restoreUrl" value="/restore" />
					<form id="restoreform" action="${restoreUrl}" method="post" class="form-horizontal">

						<c:if test="${param.error != null}">
                        	<div class="alert alert-danger">
                            	<p>Invalid username and/or password.</p>
                            </div>
                        </c:if>
                        <c:if test="${param.success != null}">
                        	<div class="alert alert-success">
                            	<p>You have been logged out successfully.</p>
                            </div>
                        </c:if>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="username"
								type="text" class="form-control" name="username" value=""
								placeholder="name">
						</div>

						<div style="margin-bottom: 10px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span> <input id="email"
								type="text" class="form-control" name="email"
								placeholder="email">
						</div>

						<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />		

						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">
								<input type="submit" class="btn btn-success" value="Send Password">
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