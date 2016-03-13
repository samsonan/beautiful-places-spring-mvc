<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/resources/core/css/bootstrap-multiselect.css" var="multiselectCss" />
<link href="${multiselectCss}" rel="stylesheet" />

<form:form id="filter-form" method="POST" modelAttribute="filters" role="form">

	<c:if test="${param.is_search and false}">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Search...">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</span>
		</div>
		<hr />
	</c:if>

	<c:if test="${param.is_location}">

		<div>
			<b>Location:</b>
		</div>
		
			<form:select class="form-control" id="region" path="region" items="${regions}" name="region" style="margin-bottom:5px;">
			</form:select>
			
			<form:select class="form-control" id="country" path="countries" items="${countries}" style="margin-bottom:5px;" multiple="multiple">
			</form:select>

		<hr>

	</c:if>

	<div class="checkbox">
		<label><form:checkbox path="unesco" name="unesco" id="unesco" />Show UNESCO
			sites only</label>
	</div>
	<hr />
	<div>
		
	</div>

	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title filter-panel-title">
						Natural Places 
				</h4>
			</div>
				<div class="panel-body">
					<div class="checkbox">
						<label><form:checkbox path="nature" name="nature" class="checkNature" id="checkNatureAll"/>Select / Unselect All</label>
					</div>
			        <div class="row">
			            <div class="col-md-6">
				
							<div class="checkbox">
								<label><form:checkbox path="naturalTypes"
										class="checkNature" value="FLORA_FAUNA" />Flora / Fauna</label>
							</div>
							<div class="checkbox">
								<label><form:checkbox path="naturalTypes"
										class="checkNature" value="DESERT" />Desert</label>
							</div>
							<div class="checkbox">
								<label><form:checkbox path="naturalTypes"
										class="checkNature" value="FOREST" />Forest / Jungle</label>
							</div>

							<div class="checkbox">
								<label><form:checkbox path="naturalTypes"
										class="checkNature" value="RIVER_LAKE" />River / Lake</label>
							</div>
							
						</div>
						<div class="col-md-6">
						
							<div class="checkbox">
								<label><form:checkbox path="naturalTypes"
										class="checkNature" value="COAST" />Marine / Coastal</label>
							</div>
							<div class="checkbox">
								<label><form:checkbox path="naturalTypes"
										class="checkNature" value="VOLC" />Volcaninc / Thermal</label>
							</div>
							<div class="checkbox">
								<label><form:checkbox path="naturalTypes"
										class="checkNature" value="MOUNT" />Mountain</label>
							</div>
							<div class="checkbox">
								<label><form:checkbox path="naturalTypes"
										class="checkNature" value="CAVE" />Karst / Caves</label>
							</div>
						</div>
					</div>
					<div class="checkbox">
						<label><form:checkbox path="naturalTypes"
								class="checkNature" value="NAT" />Other</label>
					</div>
					
				</div>
		</div>
	</div>

	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title filter-panel-title">
					Cultural Places
				</h4>
			</div>
				<div class="panel-body">
					<div class="checkbox">
						<label><form:checkbox path="culture" name="culture" class="checkCulture" id="checkCultureAll"/>Select / Unselect All</label>
					</div>
			        <div class="row">
			            <div class="col-md-6">
				            <div class="checkbox">
								<label><form:checkbox path="culturalTypes" class="checkCulture"
									value="RELIG"/>Religious structure</label>
							</div>
				            <div class="checkbox">
								<label><form:checkbox path="culturalTypes" class="checkCulture"
									value="URBAN"/>Urban Landscape</label>
							</div>
			            </div>
			            <div class="col-md-6">
							<div class="checkbox">
								<label><form:checkbox path="culturalTypes" class="checkCulture"
									value="ARCH"/>Archaeological site</label>
							</div>
				            <div class="checkbox">
								<label><form:checkbox path="culturalTypes" class="checkCulture"
									value="CULT_LND"/>Cultural landscape</label>
							</div>
										            
						</div>
			        </div>
		            <div class="checkbox">
						<label><form:checkbox path="culturalTypes" class="checkCulture"
							value="CULT"/>Other</label>
					</div>
				</div>
		</div>
	</div>

	<div class="btn-group" style="margin-top: 20px">
		<button type="submit" id="btn-filter-apply" class="btn btn-primary"  name="filter.apply">Apply</button>
		<button type="button" id="btn-filter-reset" class="btn btn-default" name="filter.reset">Reset</button>
		<!-- button class="btn btn-default" name="filter.save">Save</button-->
	</div>

</form:form>


<script>

	$('#region').change(
	    function() {
	        var sel_region = $('#region option:selected').val();
	        requestDDValues(sel_region, "country");
	    }
	);

	jQuery(document).ready(function($) {
		
//		$(".checkNature").prop('checked', true);
//		$(".checkCulture").prop('checked', true);

	});

		
	$('#btn-filter-reset').click(function(){
		$("#unesco").prop('checked', false);
		$(".checkNature").prop('checked', true);
		$(".checkCulture").prop('checked', true);
		
		$("#country").find('option').remove();
		$("#country").multiselect('rebuild');
		
		$('#region').val('0');
		
	});

</script>

<script>

function requestDDValues(key, request_table) {

	var filter = {}
	
	if (key != null)
		filter["key"] = key;
	
	filter["reqTableName"] = request_table; 
	
	if (key == 0){
		
		var $select = $("#"+request_table);
		$select.find('option').remove();
		
		return;
	}
	
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
	
	if (data.tableName == "region" || data.tableName == "country" || data.tableName == "location") {
		var $select = $("#"+data.tableName);
		
		$select.find('option').remove();
		//$select.append($("<option />").val("0").text("Select "+data.tableName));  
		$.each(data.locationMap, function(key, value) {
			$select.append($("<option />").val(key).text(value));
		});
		$select.multiselect('rebuild');
	} 
	
}

</script>

	
<script>

$(function() {

	$("#checkNatureAll").click(function() {
		$(".checkNature").prop('checked', $(this).prop('checked'));
	});

	$("#checkCultureAll").click(function() {
		$(".checkCulture").prop('checked', $(this).prop('checked'));
	});
	
});
</script>

<spring:url value="/resources/core/js/plugins/bootstrap-multiselect.js" var="multiselectJs"/>
<script src="${multiselectJs}"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#country').multiselect();
    });
</script>

