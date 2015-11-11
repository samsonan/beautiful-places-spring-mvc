<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Beautiful Places</title>

<spring:url value="/resources/core/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />

</head>
<body>
<div class="container">

<table>
        <tr>
            <td>Title</td><td>Description</td><td></td><td></td>
        </tr>
	<c:choose>
	<c:when test="${not empty placeList}">
        <c:forEach items="${placeList}" var="place" >
        <tr>
            <td>><a href="<c:url value='/view-place-${place.id}' />">${place.title}</a></td>
            <td>${place.description}</td>
            <td>${employee.salary}</td>
            <td>><a href="<c:url value='/delete-place-${place.id}' />">Delete</a></td>
        </tr>
        </c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan='4'>Nothing is found</td></tr>
	</c:otherwise>
	</c:choose>
    </table>

    <a href="<c:url value='/new' />">Add New Place</a>

	<br>

</div>

<spring:url value="/resources/core/js/main.js" var="mainJs" />
<script src="${mainJs}"></script>

</body>
</html>
