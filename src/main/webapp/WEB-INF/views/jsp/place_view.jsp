<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>

<title><c:out value="${place.title}" /> information</title>

<spring:url value="/resources/core/css/main.css" var="mainCss" />
<spring:url
	value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	var="bootstrapCss" />

<!-- Bootstrap core CSS -->
<link href="${bootstrapCss}" rel="stylesheet" />
<!-- Custom project CSS -->
<link href="${mainCss}" rel="stylesheet" />

<style>
  .carousel-inner {
      width: 100%;
      margin: auto;
	  max-height: 500px !important;
  }
  .crsl-image {
  		width: auto;
  		height:500px;
  		max-height:500px;
  }
  </style>
  
</head>

<body>

	<jsp:include page="nav_bar.jsp" />

	<div class="container-fluid" style="margin-top: 50px;">
		<div class="row content">
			<div class="col-sm-1 sidenav">


				External Links:
				<ul>
					<c:forEach items="${place.placeLinks}" var="link" varStatus="idx">
						<li><a href="${link.url}">${link.siteName}</a></li>
					</c:forEach>
				</ul>
				Coordinates:<br />
				<a href="<spring:url value="/map/?lat=${place.lat}&lon=${place.lon}"/>">
					<c:out value="${place.lat}" /> ; <c:out value="${place.lon}" />
				</a>
				<br/>
				<br/>
				Last Updated: <br/>
				<c:out value="${place.updated}" />

			</div>
			<div class="col-sm-8 text-left">
				<spring:url value="/places/edit-place-${place.id}" var="editPlaceUrl" />
			
				<h1>
					<c:out value="${place.title}" />
					<sec:authorize access="isAuthenticated()">
					<button class="btn btn-primary col-xs-offset-1" onclick="location.href='${editPlaceUrl}'">Edit</button>
					</sec:authorize>
					
				</h1>
				<h5>
					<span class="glyphicon glyphicon-globe"></span>
					<c:out value="${place.locationPath}" />
				</h5>
				<h5>
					<span class="label label-default"><c:out
							value="${place.placeTypes}" /></span>
				</h5>

				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<c:forEach items="${place.placeImages}" var="img" varStatus="idx">
							<li data-target="#myCarousel" data-slide-to="${idx.index}" <c:out value="${idx.index==0 ? 'class=active': ''}"/>></li>
						</c:forEach>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">

						<c:forEach items="${place.placeImages}" var="img" varStatus="idx">

							<div class="item <c:out value="${idx.index==0 ? 'active': ''}"/>">
								<c:choose>
									<c:when test="${img.contentType == 'URL'}">
										<img src="<spring:url value="${img.filename}"/>"
											alt="${img.title}" class="crsl-image">
									</c:when>
									<c:otherwise>
										<img src="<spring:url value="/images/${img.filename}"/>"
											alt="${img.title}" class="crsl-image">
									</c:otherwise>
								</c:choose>

								<div class="carousel-caption">
									<h3>${img.title}</h3>
									<p>${img.description}</p>
								</div>
							</div>
						</c:forEach>

					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>


				<br />
				<p>
					<c:out value="${place.description}" />
				</p>
				<hr />
			</div>
			<div class="col-sm-3 sidenav">
				<div class="well">
					<p>ADS</p>
				</div>
				<div class="well">
					<p>ADS</p>
				</div>
			</div>
		</div>
	</div>

	<footer class="container-fluid text-center">
		<p>Footer Text</p>
	</footer>

	<!-- Bootstrap core JavaScript
		================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="http://getbootstrap.com//assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>
