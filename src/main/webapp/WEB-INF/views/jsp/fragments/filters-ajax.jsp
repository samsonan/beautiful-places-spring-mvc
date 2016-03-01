<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


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
		<div class="dropdown">
			<button class="btn btn-default dropdown-toggle" type="button"
				data-toggle="dropdown">
				Country and Region <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li class="dropdown-header">South America</li>
				<li><a href="#">Equador</a></li>
				<li><a href="#">Peru</a></li>
				<li class="divider"></li>
				<li class="dropdown-header">Asia</li>
				<li><a href="#">China</a></li>
				<li><a href="#">Indonesia</a></li>
				<li><a href="#">Thailand</a></li>
			</ul>
		</div>

		<hr>

	</c:if>

	<div class="checkbox">
		<label><input type="checkbox" name="unesco" id="unesco" value="UNESCO"/>Show UNESCO
			sites only</label>
	</div>
	<hr />
	<div>
		<h5>Place type:</h5>
	</div>

	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<input type="checkbox" name="nature" class="checkNature" id="checkNatureAll"
						value="" /> <a data-toggle="collapse" href="#collapse_nat">
						Natural Places <i class="fa fa-chevron-down pull-right"></i></a>
				</h4>
			</div>
			<div id="collapse_nat" class="panel-collapse collapse">
				<div class="panel-body">
					<div class="checkbox">
						<label><input type="checkbox" name="naturalTypes[]"
								class="checkNature" value="BEACH" />Beach</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" name="naturalTypes[]"
								class="checkNature" value="LAKE" />Lake</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" name="naturalTypes[]"
								class="checkNature" value="MOUNT" />Mountain</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" name="naturalTypes[]"
								class="checkNature" value="RIVER" />River</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" name="naturalTypes[]"
								class="checkNature" value="VOLC" />Volcano</label>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<input type="checkbox" name="culture" class="checkCulture" id="checkCultureAll"
						value="" /> <a data-toggle="collapse" href="#collapse_cult">
						Cultural Places <i class="fa fa-chevron-down pull-right"></i></a>
				</h4>
			</div>
			<div id="collapse_cult" class="panel-collapse collapse">
				<div class="panel-body">
					<div class="checkbox">
						<label><input type="checkbox" name="culturalTypes[]" class="checkCulture"
							value="TEMPLE"/>Temple</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" name="culturalTypes[]" class="checkCulture"
							value="VILLAGE"/>Village</label>
					</div>
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
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
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
