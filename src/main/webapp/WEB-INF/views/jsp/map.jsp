<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp" />

<spring:url value="/resources/core/css/gmaps-sidebar.css" var="mapCss" />
<link href="${mapCss}" rel="stylesheet" />

    <div id="sidebar" class="sidebar collapsed">
        <!-- Nav tabs -->
        <div class="sidebar-tabs">
            <ul role="tablist">
                <li><a href="#filter" role="tab"><i class="fa fa-filter"></i></a></li>
                <li><a href="#info" role="tab"><i class="fa fa-info"></i></a></li>
            </ul>
        </div>

        <!-- Tab panes -->
        <div class="sidebar-content">
            <div class="sidebar-pane" id="filter">
                <h1 class="sidebar-header">
                    Filters
                    <span class="sidebar-close"><i class="fa fa-caret-left"></i></span>
                </h1>

                <p></p>

				<jsp:include page="fragments/filters-ajax.jsp" >
					<jsp:param name="is_search" value="false" />
					<jsp:param name="is_location" value="false" />
				</jsp:include>
            </div>

            <div class="sidebar-pane" id="info">
                <h1 class="sidebar-header">Place Info<span class="sidebar-close"><i class="fa fa-caret-left"></i></span></h1>
                <p></p>
                <div id="placeInfoContent"><p>Select any place on the map!</p></div>
            </div>

        </div>
    </div>

		<div id="map-canvas">
			<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
		</div>

	<script>

		var markers = new Array();
		var map;

		function initialize() {
			var mapOptions = {

				<c:choose>
					<c:when test="${(not empty param.lat) and (not empty param.lon)}">
						center : new google.maps.LatLng(${param.lat}, ${param.lon}),
					</c:when>
					<c:otherwise>
						center : new google.maps.LatLng(-8, 112),
					</c:otherwise>
				</c:choose>
					
				zoom : 6
			};
			map = new google.maps.Map(
					document.getElementById("map-canvas"), mapOptions);

			filterAjax();
			
		}

		function bindInfoWindow(marker, map, infowindow, html) { 
			google.maps.event.addListener(marker, 'mouseover', function() { 
				infowindow.setContent(html); 
				infowindow.open(map, marker); 
			}); 
		} 		
		
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>

		
	<script>

	function requestPlaceInfoAjax(placeId) {
		
		console.log("requesting info for place id="+placeId);

		var search = {}
		
		search["placeId"] = placeId; 
		
		$.ajax({
			type : "GET",
			beforeSend: function (request)
            {
                request.setRequestHeader("X-CSRF-TOKEN", "${_csrf.token}");
            },
			contentType : "application/json",
			url : "${home}/api/places/"+placeId,
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayPlaceInfo(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	
	}	
	
	function displayMarkers(data) {
		
		removeMarkers();
		
		markers = new Array();
		
		for (i = 0; i < data.length; i++) {
		    var myLatlng = new google.maps.LatLng(data[i].lat, data[i].lon);
		    var marker = new google.maps.Marker({
		        position: myLatlng,
		        map: map,
		        title: data[i].title,
		        id: data[i].id
		    });

		    marker.addListener('click', function() {
		    	requestPlaceInfoAjax(marker.id);
				sidebar.open('info', null);
			});				

		    var infowindow = new google.maps.InfoWindow();

			var content = "<b>" + data[i].title + "</b><br/><p>"+data[i].placeTypes.toString().toLowerCase()+"</p>";
			
			 google.maps.event.addListener(marker, 'mouseover', (function (marker, content, infowindow) {
		            return function () {
		                infowindow.setContent(content);
		                infowindow.open(map, marker);
		            };
		        })(marker, content, infowindow));
		        google.maps.event.addListener(marker, 'mouseout', (function (marker, content, infowindow) {
		            return function () {
		                infowindow.close();
		            };
		        })(marker, content, infowindow));			
			
		    
		    markers.push(marker);
		}		
	}
	
	function removeMarkers() {

	    for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
	    }
	}	
	
	function displayPlaceInfo(data) {

		<spring:url value="/places/view-place-" var="viewUrl" />
		
		var content = "<h3><a href='${viewUrl}"+data.id+"'>"+data.title+"</a></h3>"+
        	"<hr/>"+
        	"<p><span class='label label-default'>"+data.placeTypes+"</span></p>"+
        	"<img src='http://griyasari.com/wp-content/uploads/2013/10/borobudur44.jpg' width='330' class='img-thumbnail' />"+
        	"<br/><br/>"+
        	"<p>"+data.description+"</p>"+
        	"<a href='${viewUrl}"+data.id+"' class='btn btn-default'>More!...</a>";
		
		$("#placeInfoContent").html ( content );
	}
	
	</script>



	<jsp:include page="fragments/footer.jsp" />

	<spring:url value="/resources/core/js/plugins/jquery-sidebar.js" var="sidebarJs"/>

	<script src="${sidebarJs}"></script>
	<script>

	var sidebar = $('#sidebar').sidebar();
	
	</script>
	
</body>
</html>
