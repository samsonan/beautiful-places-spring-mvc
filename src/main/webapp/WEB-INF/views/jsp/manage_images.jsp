<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
 
<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    	<title>Upload Photos</title>

		<spring:url value="/resources/core/css/main.css" var="mainCss" />
		<spring:url
			value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
			var="bootstrapCss" />
		<spring:url value="/resources/core/css/fileinput.min.css" var="fileInputCss" />
		
		<!-- Bootstrap core CSS -->
		<link href="${bootstrapCss}" rel="stylesheet" />
		<!-- Custom project CSS -->
		<link href="${mainCss}" rel="stylesheet" />
		<link href="${fileInputCss}" rel="stylesheet" />

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	
		<spring:url value="/resources/core/js/main.js" var="mainJs" />
		<spring:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"	var="bootstrapJs" />

		<spring:url value="/resources/core/js/plugins/canvas-to-blob.min.js" var="canvasToBlobJs"/>
		<spring:url value="/resources/core/js/fileinput.min.js"  var="fileInputJs"/>

		<script src="${mainJs}"></script>
		<script src="${bootstrapJs}"></script>		
		<script src="${canvasToBlobJs}"></script>
		<script src="${fileInputJs}"></script>
		
		<style type="text/css">
		.uploadcontainer {
			margin: 10px;
		}
		</style>

		
</head>

<body>

    	<jsp:include page="nav_bar.jsp" />

		<div class="container-fluid"  style="margin-top:50px;">
			<div class="row content">
				<div class="col-sm-1 sidenav">

					...

				</div>

				<div class="col-sm-8 text-left">
					<h1>Manage images for <c:out value="${place.title}"/></h1>
					<hr/>

			        <div class="panel panel-default">
			             
			            <div class="panel-heading"><span class="lead">New Image</span></div>
			            <div class="uploadcontainer">
			                <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
			             
			                    <div class="row">
			                        <div class="form-group col-md-12">
			                            <label class="col-md-2 control-lable" for="file">... from file:</label>
			                            <div class="col-md-9">
			                                <form:input type="file" path="file" id="fileInput" data-show-upload="false" class="form-control input-sm file"/>
			                                <div class="has-error">
			                                    <form:errors path="file" class="help-inline"/>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="form-group col-md-12">
			                            <label class="col-md-2 control-lable" for="file">... rom URL:</label>
			                            <div class="col-md-9">
			                                <form:input type="text" path="imageUrl" id="img_url" class="form-control input-sm"/>
			                                <div class="has-error">
			                                    <form:errors path="imageUrl" class="help-inline"/>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="form-group col-md-12">
			                            <label class="col-md-2 control-lable" for="description">Description</label>
			                            <div class="col-md-9">
			                                <form:input type="text" path="description" id="description" class="form-control input-sm"/>
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
			            <div class="panel-heading"><span class="lead">List of Images </span></div>
			            <div class="tablecontainer">
			                <table class="table table-hover">
			                    <thead>
			                        <tr>
			                            <th>No.</th>
			                            <th></th>
			                            <th>Type</th>
			                            <th>Description</th>
			                            <th width="100"></th>
			                            <th width="100"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    <c:forEach items="${images}" var="img" varStatus="counter">
			                        <tr>
			                            <td>${counter.index + 1}</td>
			                            <td>

<c:choose>
  <c:when test="${img.contentType == 'URL'}">
			                            	<img alt="" class="img-thumbnail" src="<spring:url value="${img.filename}"/>">
  </c:when>
  <c:otherwise>
			                            	<img alt="" class="img-thumbnail" src="<spring:url value="/images/${img.filename}"/>">
  </c:otherwise>
</c:choose>			                            
			                            </td>
			                            <td>${img.contentType}</td>
			                            <td>${img.description}</td>
			                            <td><a href="<c:url value='/places/edit-image-${place.id}-${img.id}' />" class="btn btn-danger custom-width">Edit</a></td>
			                            <td><a href="<c:url value='/places/delete-image-${place.id}-${img.id}' />" class="btn btn-danger custom-width">Delete</a></td>
			                        </tr>
			                    </c:forEach>
			                    </tbody>
			                </table>
			            </div>
        			</div>
        			
			        <div class="well">
			            Back to <a href="<c:url value='/map' />">Map</a>
			        </div>
    			</div> <!-- main col -->
    		</div>
    	</div>

		<script>
			// initialize with defaults
			$("#fileInput").fileinput( {previewSettings: 
						{ image: {width: "auto", height: "300px"}}} );	
			
		
		</script>

</body>


</html>