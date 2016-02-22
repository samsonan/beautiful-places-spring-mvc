<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Beautiful Places</title>

<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	
<spring:url value="/resources/core/css/main.css" var="mainCss" />
<spring:url value="/resources/core/css/map.css" var="mapCss" />
<spring:url
	value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${mainCss}" rel="stylesheet" />
<link href="${mapCss}" rel="stylesheet" />

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" />

</head>
<body>

	<div class="container">

		<jsp:include page="nav_bar.jsp" />

		</div></nav> <!-- map plug-in bug workaround -->

		<div class="navbar-offset"></div>
		<div id="map-canvas">
			<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
		</div>
		<div class="row main-row">
			<div class="col-sm-4 col-md-2 sidebar sidebar-left pull-left">
				<div class="panel-group sidebar-body" id="accordion-left">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#layers"> <i
									class="fa fa-list-alt"></i> Filters
								</a> <span class="pull-right slide-submenu"> <i
									class="fa fa-chevron-left"></i> hide
								</span>
							</h4>
						</div>
						<div id="layers" class="panel-collapse collapse in">
							<div class="panel-body">
								<jsp:include page="filters.jsp" >
									<jsp:param name="is_search" value="false" />
									<jsp:param name="is_location" value="false" />
								</jsp:include>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="mini-submenu mini-submenu-left pull-left">
			<i class="fa fa-list-alt"></i>
		</div>
	</div>



	<script>
		markerLatLon = [
				<c:forEach items="${placeList}" var="s">[
						<c:out value="${s.lat}"/>, <c:out value="${s.lon}"/>],
				</c:forEach> ];

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
			var map = new google.maps.Map(
					document.getElementById("map-canvas"), mapOptions);

			var infowindow = new google.maps.InfoWindow();

			var marker, i;

			for (i = 0; i < markerLatLon.length; i++) {
				marker = new google.maps.Marker({
					position : new google.maps.LatLng(markerLatLon[i][0],
							markerLatLon[i][1]),
					map : map
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

	<spring:url value="/resources/core/js/ol.js" var="olJs" />
	<spring:url value="/resources/core/js/main.js" var="mainJs" />
	<spring:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"
		var="bootstrapJs" />

	<script src="${mainJs}"></script>
	<script src="${bootstrapJs}"></script>


	<script type="text/javascript">
		function applyMargins() {
			var leftToggler = $(".mini-submenu-left");
			if (leftToggler.is(":visible")) {
				$("#map .ol-zoom").css("margin-left", 0).removeClass(
						"zoom-top-opened-sidebar").addClass(
						"zoom-top-collapsed");
			} else {
				$("#map .ol-zoom").css("margin-left",
						$(".sidebar-left").width()).removeClass(
						"zoom-top-opened-sidebar").removeClass(
						"zoom-top-collapsed");
			}
		}

		function isConstrained() {
			return $(".sidebar").width() == $(window).width();
		}

		function applyInitialUIState() {
			if (isConstrained()) {
				$(".sidebar-left .sidebar-body").fadeOut('slide');
				$('.mini-submenu-left').fadeIn();
			}
		}

		$(function() {
			$('.sidebar-left .slide-submenu').on('click', function() {
				var thisEl = $(this);
				thisEl.closest('.sidebar-body').fadeOut('slide', function() {
					$('.mini-submenu-left').fadeIn();
					applyMargins();
				});
			});

			$('.mini-submenu-left').on('click', function() {
				var thisEl = $(this);
				$('.sidebar-left .sidebar-body').toggle('slide');
				thisEl.hide();
				applyMargins();
			});

			$(window).on("resize", applyMargins);

			applyInitialUIState();
			applyMargins();
		});
	</script>



</body>
</html>
