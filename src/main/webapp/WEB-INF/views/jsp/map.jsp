<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp" />

<spring:url value="/resources/core/css/map.css" var="mapCss" />
<link href="${mapCss}" rel="stylesheet" />

		<div class="navbar-offset"></div>
		<div id="map-canvas">
			<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
		</div>
		<div class="row main-row" >
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
							<div class="panel-body" id="main-panel-body">
								<jsp:include page="fragments/filters.jsp" >
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

	<script>
	
		markerInfo = [
				<c:forEach items="${placeList}" var="s">[
						<c:out value="${s.lat}"/>, <c:out value="${s.lon}"/>, '<c:out value="${s.title}"/>',<c:out value="${s.id}"/>
						,'<c:out value="${s.placeTypes}"/>'],
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

			for (i = 0; i < markerInfo.length; i++) {
				marker = new google.maps.Marker({
					position : new google.maps.LatLng(markerInfo[i][0],
							markerInfo[i][1]),
					map : map,
					title: markerInfo[i][2],
					id: markerInfo[i][3]
				});

				marker.addListener('click', function() {
					window.location.href = '<spring:url value="/places/view-place-"/>'.concat(this.id);
				  });				

				// add an event listener for this marker
				bindInfoWindow(marker, map, infowindow, "<b>" + markerInfo[i][2] + "</b><br/><p>"+markerInfo[i][4]+"</p>"); 				
				
				marker.addListener('mouseout', function() {
				    infowindow.close();
				});				
			}
		}

		function bindInfoWindow(marker, map, infowindow, html) { 
			google.maps.event.addListener(marker, 'mouseover', function() { 
				infowindow.setContent(html); 
				infowindow.open(map, marker); 
			}); 
		} 		
		
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>

	<script type="text/javascript">

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
					
				});
			});

			$('.mini-submenu-left').on('click', function() {
				var thisEl = $(this);
				$('.sidebar-left .sidebar-body').toggle('slide');
				thisEl.hide();
				
			});

			applyInitialUIState();
			
		});
	</script>
	



	<jsp:include page="fragments/footer.jsp" />

</body>
</html>
