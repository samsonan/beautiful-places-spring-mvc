<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<spring:url value="/resources/core/css/simple-sidebar.css"
	var="sidebarCss" />
<link href="${sidebarCss}" rel="stylesheet" />

	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-2 sidenav">

				<jsp:include page="../fragments/filters.jsp" >
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
					<spring:url value="/places/list" var="baseUrl" />
					<h2><a href="${viewUrl}">${place.title}</a>
					
						<sec:authorize access="isAuthenticated()">
							<button class="btn btn-primary"
								onclick="location.href='${updateUrl}'">Update</button>
						</sec:authorize>
					</h2>
					<h5>
						<span class="glyphicon glyphicon-globe"></span>
						<a href="${baseUrl}?zn=${place.locationDetails.zoneCode}">${place.locationDetails.zoneName}</a> / 
						<a href="${baseUrl}?ccode=${place.locationDetails.countryCode}">${place.locationDetails.countryName}</a> / 
						<a href="${baseUrl}?locid=${place.locationDetails.locationId}">${place.locationDetails.locationName}</a> 
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


	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
