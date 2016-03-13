<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form:form id="filter-form" method="POST" modelAttribute="filters" role="form">

	<div class="checkbox">
		<label><input type="checkbox" name="unesco" id="unesco" value="UNESCO"/>Show UNESCO
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
						<label><input type="checkbox"  name="nature" class="checkNature" id="checkNatureAll"/>Select / Unselect All</label>
					</div>
			        <div class="row">
			            <div class="col-md-6">
				
							<div class="checkbox">
								<label><input type="checkbox" name="naturalTypes[]"
										class="checkNature" value="FLORA_FAUNA" />Flora / Fauna</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" name="naturalTypes[]"
										class="checkNature" value="DESERT" />Desert</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" name="naturalTypes[]"
										class="checkNature" value="FOREST" />Forest / Jungle</label>
							</div>

							<div class="checkbox">
								<label><input type="checkbox" name="naturalTypes[]"
										class="checkNature" value="RIVER_LAKE" />River / Lake</label>
							</div>
							
						</div>
						<div class="col-md-6">
						
							<div class="checkbox">
								<label><input type="checkbox" name="naturalTypes[]"
										class="checkNature" value="COAST" />Marine / Coastal</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" name="naturalTypes[]"
										class="checkNature" value="VOLC" />Volcaninc / Thermal</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" name="naturalTypes[]"
										class="checkNature" value="MOUNT" />Mountain</label>
							</div>
							<div class="checkbox">
								<label><input type="checkbox" name="naturalTypes[]"
										class="checkNature" value="CAVE" />Karst / Caves</label>
							</div>
						</div>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" name="naturalTypes[]"
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
						<label><input type="checkbox"  name="culture" class="checkCulture" id="checkCultureAll"/>Select / Unselect All</label>
					</div>
			        <div class="row">
			            <div class="col-md-6">
				            <div class="checkbox">
								<label><input type="checkbox" name="culturalTypes[]" class="checkCulture"
									value="RELIG"/>Religious structure</label>
							</div>
				            <div class="checkbox">
								<label><input type="checkbox" name="culturalTypes[]" class="checkCulture"
									value="URBAN"/>Urban Landscape</label>
							</div>
			            </div>
			            <div class="col-md-6">
							<div class="checkbox">
								<label><input type="checkbox" name="culturalTypes[]" class="checkCulture"
									value="ARCH"/>Archaeological site</label>
							</div>
				            <div class="checkbox">
								<label><input type="checkbox" name="culturalTypes[]" class="checkCulture"
									value="CULT_LND"/>Cultural landscape</label>
							</div>
										            
						</div>
			        </div>
		            <div class="checkbox">
						<label><input type="checkbox" name="culturalTypes[]" class="checkCulture"
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

	jQuery(document).ready(function($) {

		$(".checkNature").prop('checked', true);
		$(".checkCulture").prop('checked', true);

		$("#filter-form").submit(function(event) {
	
			// Disble the filter button
			enableFilterButton(false);
		
			// Prevent the form from submitting via the browser.
			event.preventDefault();
	
			filterAjax();
	
		});
			

	});

	function filterAjax() {

		var filter = {}
		
		var values = new Array();
		$.each($("input[name='naturalTypes[]']:checked"), function() {
		  values.push($(this).val());
		});
		
		filter["naturalTypes"] = values;

		values = new Array();
		$.each($("input[name='culturalTypes[]']:checked"), function() {
			  values.push($(this).val());
		});
		
		filter["culturalTypes"] = values;
		
		if($("#unesco").is(':checked'))
			filter["unesco"] = true; 
		
		$.ajax({
			type : "POST",
			beforeSend: function (request)
            {
                request.setRequestHeader("X-CSRF-TOKEN", "${_csrf.token}");
            },
			contentType : "application/json",
			url : "${home}/api/getFilterResult",
			data : JSON.stringify(filter),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayMarkers(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//displayMarkers(e);
			},
			done : function(e) {
				console.log("DONE");
				enableFilterButton(true);
			}
		});
	
	}

	function enableFilterButton(flag) {
		$("#btn-filter-apply").prop("disabled", flag);
	}
	
	$('#btn-filter-reset').click(function(){
		$("#unesco").prop('checked', false);
		$(".checkNature").prop('checked', true);
		$(".checkCulture").prop('checked', true);
	});

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



