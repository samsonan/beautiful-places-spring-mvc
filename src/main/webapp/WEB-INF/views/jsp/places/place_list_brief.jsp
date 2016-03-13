<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

	<div class="container" style="margin-top: 50px;">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				${msg}
			</div>
		</c:if>

		<spring:url value="/places/add" var="addPlaceUrl" />
		
		<h1>
			<c:choose>
				<c:when test="${mode=='admin'}">All places</c:when>
				<c:otherwise>Places created by you</c:otherwise>
			</c:choose>
				
		<button class="btn btn-primary col-xs-offset-1" onclick="location.href='${addPlaceUrl}'">Add New Place</button>
		</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Title</th>
					<th>Description</th>
					<th>Tags</th>
					<th>Photos</th>
					<th>Location</th>
					<th>Coordinates</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="place" items="${placeList}">
				<tr>
					<td>${place.id}</td>
					<td><a href="<c:url value='/places/view-place-${place.id}' />">${place.title}</a></td>
					<td>${place.description}</td>
					<td>
					
						<c:if test="${place.unesco}" >
							<span class="label label-danger">UNESCO</span>
						</c:if>
						<c:forEach var="placeType" items="${place.placeTypes}">
							<span class="label label-default">${placeType}</span>
						</c:forEach>					
					
					</td>
					<td><a href="<c:url value='/places/add-image-${place.id}' />">${place.imageCount} (click to manage)</a></td>
					<td>${place.country.regionName} / ${place.country.subregionName} / ${place.country.name}</td>
					<td>
						<a href="<spring:url value="/map/?lat=${place.lat}&lon=${place.lon}"/>">
							<c:out value="${place.lat}" /> ; <c:out value="${place.lon}" />
						</a>
					</td>
					<td>${place.statusStr}</td>
					<td>
						<spring:url value="/places/edit-place-${place.id}" var="editUrl" />
						<spring:url value="/places/delete-place-${place.id}" var="deleteUrl" />
	
						<button class="btn btn-primary"
							onclick="location.href='${editUrl}'">Update</button>
						<button class="btn btn-danger"
							onclick="location.href='${deleteUrl}'">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>