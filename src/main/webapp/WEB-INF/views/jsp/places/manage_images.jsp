<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<spring:url value="/resources/core/css/fileinput.min.css"
	var="fileInputCss" />
<link href="${fileInputCss}" rel="stylesheet" />

	<div class="container-fluid top-container">
		<div class="row content">
			<div class="col-sm-1 sidenav">...</div>

			<div class="col-sm-8 text-left">
				<h1>
					Manage images for
					<c:out value="${place.title}" />
				</h1>
				<hr />

				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="lead">Upload image...</span>
					</div>
					<div class="upload-container">
						<form:form method="POST" modelAttribute="imageForm"
							enctype="multipart/form-data" class="form-horizontal">

							<form:errors path="*" cssClass="alert alert-danger" element="div" />

							<c:if test="false">
								<!-- EDIT function is not ready yet -->
								<div class="row">
									<div class="form-group col-md-12">
										<label class="col-md-2 control-lable text-right" for="file">Current
											image:</label>
										<div class="form-group col-md-5 centered">
											<img alt="" class="img-thumbnail"
												src="<spring:url value="${imageForm.editImageSrc}"/>">
										</div>
									</div>
								</div>
							</c:if>

							<div class="row">
								<div class="form-group col-md-12">
									<label class="col-md-2 control-lable text-right" for="file">...
										from file:</label>
									<div class="col-md-9">
										<form:input type="file" path="file" id="fileInput"
											data-show-upload="false" class="form-control input-sm file" />
										<div class="has-error">
											<form:errors path="file" cssClass="text-danger" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12">
									<label class="col-md-2 control-lable text-right" for="file">...
										from URL:</label>
									<div class="col-md-9">
										<form:input type="text" path="imageUrl" id="img_url"
											class="form-control input-sm" />
										<div class="has-error">
											<form:errors path="imageUrl" cssClass="text-danger" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12">
									<label class="col-md-2 control-lable text-right" for="title">Title</label>
									<div class="col-md-9">
										<form:input type="text" path="title" id="title"
											class="form-control input-sm" />
										<form:errors path="title" cssClass="text-danger" />
									</div>

								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12">
									<label class="col-md-2 control-lable text-right"
										for="description">Description</label>
									<div class="col-md-9">
										<form:input type="text" path="description" id="description"
											class="form-control input-sm" />
									</div>

								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-10">
									<input type="submit" value="Upload" class="btn btn-primary">
								</div>
							</div>

						</form:form>
					</div>
				</div>

				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead">List of Uploaded Images </span>
					</div>
					<div class="tablecontainer">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>No.</th>
									<th></th>
									<th>Description</th>
									<th width="100"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${images}" var="img" varStatus="counter">
									<tr>
										<td>${counter.index + 1}</td>
										<td><img alt="" class="img-thumbnail"
											src="<spring:url value="${img.imageSrc}"/>"></td>
										<td>${img.description}</td>
										<%
											/*
											EDIT function is not implemented yet
											<td><a href="<c:url value='/places/edit-image-${place.id}-${img.id}' />" class="btn btn-danger custom-width">Edit</a></td>
											*/
										%>
										<td><a
											href="<c:url value='/places/delete-image-${place.id}-${img.id}' />"
											class="btn btn-danger custom-width">Delete</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- main col -->
		</div>
	</div>

	<spring:url value="/resources/core/js/plugins/canvas-to-blob.min.js"
		var="canvasToBlobJs" />
	<spring:url value="/resources/core/js/fileinput.min.js"
		var="fileInputJs" />

	<script src="${canvasToBlobJs}"></script>
	<script src="${fileInputJs}"></script>

	<script>
		// initialize with defaults
		$("#fileInput").fileinput({
			previewSettings : {
				image : {
					width : "auto",
					height : "300px"
				}
			}
		});
	</script>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>