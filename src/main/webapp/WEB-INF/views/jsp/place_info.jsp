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
		<p><c:out value="${place.lat}"/></p>
		<p><c:out value="${place.lon}"/></p>
		<p>
			<c:choose>
				<c:when test="${not empty place.placeTypes}">
					<c:forEach items="${place.placeTypes}" var="type">
						${type.name}
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan='4'>Nothing is found</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</p>
	</div>

	<br>
	<hr>
    Go back to <a href="<c:url value='/list' />">List of All Places</a>
</div>

<spring:url value="/resources/core/js/main.js" var="mainJs" />
<script src="${mainJs}"></script>

</body>
</html>