<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Beautiful Places</title>
	
	<spring:url value="/resources/core/css/main.css" var="mainCss" />
	<spring:url	value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
		var="bootstrapCss" />
	
	<!-- Bootstrap core CSS -->
	<link href="${bootstrapCss}" rel="stylesheet" />
	<!-- Custom project CSS -->
	<link href="${mainCss}" rel="stylesheet" />



</head>
<body>


    <jsp:include page="nav_bar.jsp"/>

	<div class="container" style="margin-top:50px;">

 		<div class="row">
        <div class="col-md-6">

		<table class="table">
			<thead>
			<tr>
				<th>Title</th>
				<th>Description</th>
				<th>Location</th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${not empty placeList}">
					<c:forEach items="${placeList}" var="place">
						<tr>
							<td>><a href="<c:url value='/places/view-place-${place.id}' />">${place.title}</a></td>
							<td>${place.description}</td>
							<td>${place.lat}, ${place.lon}</td>
							<td>><a href="<c:url value='/places/delete-place-${place.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan='4'>Nothing is found</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		
		</div>
		</div>

		<a class="btn btn-lg btn-primary" href="<c:url value='/new-place' />">Add New Place</a> <br>
		
	</div>



    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

	<spring:url value="/resources/core/js/main.js" var="mainJs" />
	<spring:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js" var="bootstrapJs" />

	<script src="${mainJs}"></script>
	<script src="${bootstrapJs}"></script>

</body>
</html>
