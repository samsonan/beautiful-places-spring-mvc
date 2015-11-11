<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Beautiful Places</title>

<spring:url value="/resources/core/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />

</head>

<div class="container">

	Single Place View:
	<hr>
	<div class="row">
		<h2><c:out value="${place.title}"/></h2>
		<p><c:out value="${place.description}"/></p>
	</div>

	<br>
	<hr>
    Go back to <a href="<c:url value='/list' />">List of All Places</a>
</div>

<spring:url value="/resources/core/js/main.js" var="mainJs" />
<script src="${mainJs}"></script>

</body>
</html>