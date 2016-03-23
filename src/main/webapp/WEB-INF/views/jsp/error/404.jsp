<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>404</h1>
		<p>The page or resource you're requesting is not found. We appreciate if you can press 'Contact Support' button below and explain how that happened.</p>
		<p>
			<spring:url value="/" var="baseUrl" />
		
			<a class="btn btn-primary btn-lg" href="${baseUrl}/feedback" role="button">Contact Support</a> 
			<a class="btn btn-primary btn-lg" href="${baseUrl}/map" role="button">Back to map</a>
		</p>
	</div>
</div>

	<hr>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>