<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container" style="margin-top: 70px;">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<spring:url value="/users/add" var="addUserUrl" />
		
		<h1>All Users <button class="btn btn-primary col-xs-offset-1" onclick="location.href='${addUserUrl}'">Create New</button>
		</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Display Name</th>
					<th>Email</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>ROLE</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.role}</td>
					<td>
						<spring:url value="/users/${user.id}" var="userUrl" />
						<spring:url value="/users/${user.id}/delete" var="deleteUrl" />
						<spring:url value="/users/${user.id}/update" var="updateUrl" />
						<spring:url value="/users/${user.id}/send_reg" var="regMsgUrl" />
						<spring:url value="/users/${user.id}/send_reset" var="resMsgUrl" />
	
						<sec:authentication property="principal" var ="principal" />
	
						<c:if test="${user.name ne principal.username}"> 
	
							<button class="btn btn-primary"
								onclick="location.href='${updateUrl}'">Update</button>
							<button class="btn btn-danger"
								onclick="this.disabled=true;post('${deleteUrl}',{'${_csrf.parameterName}': '${_csrf.token}'})">Delete</button>
							<button class="btn btn-default"
								onclick="location.href='${regMsgUrl}'">Send Reg Msg</button>
							<button class="btn btn-default"
								onclick="location.href='${resMsgUrl}'">Send Pass Rst Msg</button>
							
						</c:if>
						
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>