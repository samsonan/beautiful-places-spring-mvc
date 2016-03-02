<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

		<div class="container-fluid"  style="margin-top:50px;">
			<div class="row content">
				<div class="col-sm-1 sidenav">

					...

				</div>

				<div class="col-sm-8 text-left">
					<h1>
						<c:choose>
							<c:when test="${not empty place.title}">
								${place.title}
							</c:when>
							<c:otherwise>New Place</c:otherwise>
						</c:choose>
						</h1>
					<hr/>
  			 	    <form:form method="POST" modelAttribute="place" class="form-horizontal" role="form">
    	                <form:input type="hidden" path="id" id="id"/>
    	                <form:errors path="*" cssClass="alert alert-danger" element="div" />
						<div class="form-group">
							<label class="control-label col-sm-2" for="title">Name:</label>
							<div class="col-sm-10">
								<form:input class="form-control" path="title" id="title" placeholder="Name of the Place" />
								<form:errors path="title" cssClass="text-danger"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="description">Description:</label>
							<div class="col-sm-10">
								<form:textarea class="form-control" path="description" id="description" rows="3" placeholder="Describe the Place"/>
								<form:errors path="description" cssClass="text-danger"/>
							</div>
						</div>
						
						<div class="form-group">
							<form:errors path="placeTypes" cssClass="text-danger" style="margin-left:5%"/>
						</div> 
						<div class="form-group bg-success">
							<label class="control-label col-sm-2" for="type">Type (nature):</label>
							<div class="col-sm-5 bg-success">
									<div class="checkbox"><label><form:checkbox path="placeTypes" value="BEACH"/>Beach</label></div>
									<div class="checkbox"><label><form:checkbox path="placeTypes" value="LAKE"/>Lake</label></div>
									<div class="checkbox"><label><form:checkbox path="placeTypes" value="MOUNT"/>Mountain</label></div>
							</div>
							<div class="col-sm-5">
									<div class="checkbox"><label><form:checkbox path="placeTypes" value="RIVER"/>River</label></div>
									<div class="checkbox"><label><form:checkbox path="placeTypes" value="VOLC"/>Volcano</label></div>
							</div>
							
						</div>
						<div class="form-group bg-warning">
							<label class="control-label col-sm-2" for="type">Type (culture):</label>
							<div class="col-sm-5">
									<div class="checkbox"><label><form:checkbox path="placeTypes" value="TEMPLE"/>Temple</label></div>
									<div class="checkbox"><label><form:checkbox path="placeTypes" value="VILLAGE"/>Village</label></div>
							</div>
						</div> 
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label>
										<form:checkbox id="isUnescoChk" path="unesco"/>
										It is the UNESCO site</label>
								</div>
							</div>
						</div>
						<div class="form-group" id="unescoPanel">
							<label class="control-label col-sm-2" for="unesco_url">UNESCO page URL:</label>
							<div class="col-sm-10">
								<form:input class="form-control" path="unescoUrl" id="unescoUrl" placeholder="Link to this Place on UNESCO site"/>
								<form:errors path="unescoUrl" cssClass="text-danger"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="location">Location:</label>
							<div class="col-sm-10">
								<form:select class="form-control" id="zone" items="${zones}" path="zone" style="margin-bottom:5px;">
								</form:select>
								<form:select class="form-control" id="country" items="${countries}" path="country" style="margin-bottom:5px;">
								</form:select>
								<form:select class="form-control" id="location" items="${locations}" path="location">
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="lat">Latitude:</label>
							<div class="col-sm-2">
								<form:input class="form-control" id="lat" path="lat" placeholder="Latitude"/>
							</div>
							<div class="col-sm-8">
								<form:errors path="lat" cssClass="text-danger"/>							
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="lon">Longitude:</label>
							<div class="col-sm-2">
								<form:input class="form-control" id="lat" path="lon" placeholder="Longitude"/>
							</div>
							<div class="col-sm-8">
								<form:errors path="lon" cssClass="text-danger"/>							
							</div>
						</div>

	
						<div class="panel panel-default">
							<div class="panel-heading">
								Links
							</div>
							<div class="panel-body">
							
								<div class="form-group" style="margin-left:5%">
									<form:errors path="placeLinks" cssClass="text-danger"/>
								</div> 
								
								<c:set var="last_idx" value="${place.placeLinks.size()}" />
								
								<table class="table" id="LinksTable">
								    <thead>
								      <tr>
								        <th>Site Name</th>
								        <th>Site URL</th>
								        <th></th>
								      </tr>
								    </thead>
								    <tbody>

								

								<c:forEach items="${place.placeLinks}" var="link" varStatus="idx">
								      <tr>

								        <td><form:input path="placeLinks[${idx.index}].siteName" value="${link.siteName}" type="text" class="form-control" id="site_name" placeholder="Site Name"/></td>
								        <td><form:input path="placeLinks[${idx.index}].url" value="${link.url}" type="text" class="form-control" id="site_url" placeholder="Site URL" /></td>
								        <td><button type="button" class="btn btn-danger delLinkBtn" onclick="deleteRow(this)" value="Delete" >Delete</button></td>
								
								      </tr>
								</c:forEach>

								      
								      <tr>
								        <td><input name="placeLinks[<c:out value="${last_idx}" />].siteName" type="text" class="form-control" id="site_name" placeholder="Site Name"/></td>
								        <td><input name="placeLinks[<c:out value="${last_idx}" />].url" type="text" class="form-control"	id="site_url" placeholder="Site URL" /></td>
								        <td><button type="button" class="btn btn-danger delLinkBtn" onclick="deleteRow(this)" value="Delete" >Delete</button></td>
								      </tr>
								      	

								    </tbody>
								  </table>
								
								
								<div class="form-group">
									<div class="col-sm-offset-1 col-sm-10">
									<button type="button" class="btn btn-primary"  onclick="insertRow()">Add another link</button>
									</div>

								</div> 

							</div><!-- panel body -->
						
						</div>
						<div class="form-group">
							<p class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success" name="edit.save.exit"> Submit and Exit </button>
								<button type="submit" class="btn btn-default" name="edit.save.photos"> Submit and go to Photos </button>
								<button type="submit" class="btn btn-default" name="edit.cancel"> Cancel </button>
							</p>
						</div>

					</form:form>
				</div>

			</div>
		</div> <!-- container-fluid -->

		<script>
		
		console.log('location:'+$('#location').val);
		
		//requestDDValues(null, "zone");
		
		$('#zone').change(
			    function() {
			        var sel_zone = $('#zone option:selected').val();
			        $("#location").find('option').remove();   
			        requestDDValues(sel_zone, "country");
			    }
			);

		$('#country').change(
			    function() {
			        var sel_country = $('#country option:selected').val();
			        requestDDValues(sel_country, "location");
			    }
			);
		
		
		function requestDDValues(key, request_table) {

			var filter = {}
			
			if (key != null)
				filter["key"] = key;
			
			filter["reqTableName"] = request_table; 
			
			$.ajax({
				type : "POST",
				beforeSend: function (request)
	            {
	                request.setRequestHeader("X-CSRF-TOKEN", "${_csrf.token}");
	            },
				contentType : "application/json",
				url : "${home}/api/getLocationList",
				data : JSON.stringify(filter),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					display(data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		
		}		
		
		function display(data){
			
			if (data.tableName == "zone" || data.tableName == "country" || data.tableName == "location") {
				var $select = $("#"+data.tableName);
				$select.find('option').remove();   
				$select.append($("<option />").val("0").text("Select "+data.tableName));  
				$.each(data.locationMap, function(key, value) {
					$select.append($("<option />").val(key).text(value));
				});
			} 
			
		}
		
		
		</script>

		<script>
		
			var table = document.getElementById('LinksTable'),
				tbody = table.getElementsByTagName('tbody')[0];

		
			$("#isUnescoChk").checked?$('#unescoPanel').show():$('#unescoPanel').hide()
					
			$("#isUnescoChk").change(function(){
				if(this.checked)
		            $('#unescoPanel').fadeIn('fast');
		        else
		            $('#unescoPanel').fadeOut('fast');
		    });

			$(".delLinkBtn").prop('disabled', !canDeleteRow());

			function deleteRow(row)
			{
			    var i=row.parentNode.parentNode.rowIndex;
			    document.getElementById('LinksTable').deleteRow(i);
			    $(".delLinkBtn").prop('disabled', !canDeleteRow());
			}
			
			function insertRow()
			{
		       // deep clone the first row
			    var new_row = tbody.rows[0].cloneNode(true);
		       // get the total number of rows
			    var len = tbody.rows.length;
		       // set the innerHTML of the first row 

		       // grab the input from the first cell and update its ID and value
			    var inp1 = new_row.cells[0].getElementsByTagName('input')[0];
			    var inp2 = new_row.cells[1].getElementsByTagName('input')[0];
			    
			    inp1.id += len;
			    inp2.id += len;
			    
			    inp1.value = inp2.value = '';

			    // append the new row to the table
			    tbody.appendChild( new_row );
			    $(".delLinkBtn").prop('disabled', !canDeleteRow());
			}			

			function canDeleteRow() 
			{
				return tbody.rows.length > 1;
			}
			
	</script>		
		
	<script>
		$(function() {
  			$('#description').wysihtml5({
    			toolbar: {
      			fa: true
    			}
  			});
		});
	</script>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
