<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<spring:url value="/resources/core/css/main.css" var="mainCss" />
<spring:url
	value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	var="bootstrapCss" />
<spring:url value="/resources/core/css/simple-sidebar.css"
	var="sidebarCss" />

<!-- Bootstrap core CSS -->
<link href="${bootstrapCss}" rel="stylesheet" />
<!-- Custom project CSS -->
<link href="${mainCss}" rel="stylesheet" />

<link href="${sidebarCss}" rel="stylesheet" />

<style>
/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
.row.content {
	height: 1500px
}

/* Set gray background color and 100% height */
.sidenav {
	margin-top:50px;
	padding-top:10px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>
</head>
<body>

	<jsp:include page="nav_bar.jsp" />


	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-2 sidenav">

				<jsp:include page="filters.jsp" >
					<jsp:param name="is_search" value="true" />
					<jsp:param name="is_location" value="true" />
				</jsp:include>

			</div>

			<div class="col-sm-9">
				<h4>
					<small>RECENT PLACES</small>
				</h4>
				<hr>
				
				<c:forEach var="place" items="${placeList}">
					<spring:url value="/places/view-place-${place.id}" var="viewUrl" />
					<spring:url value="/places/edit-place-${place.id}" var="updateUrl" />
					<h2><a href="${viewUrl}">${place.title}</a>
					
						<sec:authorize access="isAuthenticated()">
							<button class="btn btn-primary"
								onclick="location.href='${updateUrl}'">Update</button>
						</sec:authorize>
					</h2>
					<h5>
						<span class="glyphicon glyphicon-globe"></span> ${place.country} / ${place.locationPath}
					</h5>
					<h5>
						<c:if test="${place.unesco}" >
							<span class="label label-danger">UNESCO</span>
						</c:if>
						<c:forEach var="placeType" items="${place.placeTypes}">
							<span class="label label-default">${placeType}</span>
						</c:forEach>
					</h5>
					<br>

						<c:forEach items="${place.placeImages}" var="img" varStatus="idx">

							<c:if test="${idx.index==0}">
								<img src="<spring:url value="${img.imageSrc}"/>" alt="${img.title}" class="img-responsive" style="max-width: 70%" >
							</c:if>
						</c:forEach>
						
					<br />
					<p>${place.description}</p>
					<br>
					<br>
					<hr>
				
				</c:forEach>
				
			</div>
		</div>
	</div>

	<footer class="container-fluid">
		<p>Footer Text</p>
	</footer>


	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"><\/script>')
	</script>

	<spring:url value="/resources/core/js/main.js" var="mainJs" />
	<spring:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"
		var="bootstrapJs" />

	<script src="${mainJs}"></script>
	<script src="${bootstrapJs}"></script>

</body>
</html>
