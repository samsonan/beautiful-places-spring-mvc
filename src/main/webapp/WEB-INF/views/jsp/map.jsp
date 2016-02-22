<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Beautiful Places</title>
	
	<spring:url value="/resources/core/css/main.css" var="mainCss" />
	<spring:url
		value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
		var="bootstrapCss" />
	
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${mainCss}" rel="stylesheet" />



</head>
<body>
	<div class="container">

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
							<td>><a href="<c:url value='/view-place-${place.id}' />">${place.title}</a></td>
							<td>${place.description}</td>
							<td>${place.lat}, ${place.lon}</td>
							<td>><a href="<c:url value='/delete-place-${place.id}' />">Delete</a></td>
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

		<a class="btn btn-lg btn-primary" href="<c:url value='/new' />">Add New Place</a> <br>
		
		<div id="map-container" class="col-md-6">
			<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
		</div>
		
	</div>


	<script>

		markerLatLon = [
			<c:forEach items="${placeList}" var="s">
	        	[<c:out value="${s.lat}"/>, <c:out value="${s.lon}"/>],
	        </c:forEach>
	    ];
	
        function initialize() {
            var mapOptions = {
                center: new google.maps.LatLng(-8, 112),
                zoom: 6
            };
			var map = new google.maps.Map(document.getElementById("map-container"), mapOptions);
			
			var infowindow = new google.maps.InfoWindow();

		    var marker, i;

		    for (i = 0; i < markerLatLon.length; i++) {  
		      marker = new google.maps.Marker({
		        position: new google.maps.LatLng(markerLatLon[i][0], markerLatLon[i][1]),
		        map: map
		      });
			
		      /*
		      google.maps.event.addListener(marker, 'click', (function(marker, i) {
		        return function() {
		          infowindow.setContent(locations[i][0]);
		          infowindow.open(map, marker);
		        }
		      })(marker, i)); 
		      */
		    }			
			
        }

        google.maps.event.addDomListener(window, 'load', initialize);
	
	</script>


	<spring:url value="/resources/core/js/main.js" var="mainJs" />
	<spring:url
		value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.js"
		var="bootstrapJs" />

	<script src="${mainJs}"></script>
	<script src="${bootstrapJs}"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDnycWatbGyK6ldFqErjFtko1yeMclNUOA&amp;sensor=true"></script>

</body>
</html>
