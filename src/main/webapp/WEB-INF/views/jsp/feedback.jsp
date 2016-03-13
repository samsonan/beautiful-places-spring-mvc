<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp" />

<body>

	<div class="container" style="margin-top: 70px;">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		
		<form:form class="form-horizontal" role="form" method="post" modelAttribute="feedbackForm">
		    <div class="form-group">
		        <label for="name" class="col-sm-2 control-label">Name</label>
		        <div class="col-sm-10">
		            <form:input path="userName" class="form-control" id="name" name="name" placeholder="Your Name" value=""/>
					<form:errors path="userName" cssClass="text-danger" />
		        </div>
		    </div>
		    <div class="form-group">
		        <label for="email" class="col-sm-2 control-label">Email</label>
		        <div class="col-sm-10">
		             <form:input path="userEmail" class="form-control" id="email" name="email" placeholder="example@domain.com" value=""/>
					<form:errors path="userEmail" cssClass="text-danger" />
		        </div>
		    </div>
		    <div class="form-group">
		        <label for="message" class="col-sm-2 control-label">Message</label>
		        <div class="col-sm-10">
		            <form:textarea path="message" class="form-control" rows="4" name="message"></form:textarea>
					<form:errors path="message" cssClass="text-danger" />
		        </div>
		    </div>
		    <div class="form-group">
		        <div class="col-sm-10 col-sm-offset-2">
		            <input id="submit" name="submit" type="submit" value="Send" class="btn btn-primary">
		        </div>
		    </div>
		</form:form>

	</div>


	<jsp:include page="fragments/footer.jsp" />

</body>
</html>