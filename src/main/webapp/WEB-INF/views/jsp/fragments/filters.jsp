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
		<label><form:checkbox path="unesco" value="UNESCO"/>Show UNESCO
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
					<form:checkbox path="nature" name="nature" class="checkNature" id="checkNatureAll"
						value="" /> <a data-toggle="collapse" href="#collapse_nat">
						Natural Places <i class="fa fa-chevron-down pull-right"></i></a>
				</h4>
			</div>
			<div id="collapse_nat" class="panel-collapse collapse">
				<div class="panel-body">
					<div class="checkbox">
						<label><form:checkbox path="naturalTypes"
								class="checkNature" value="BEACH" />Beach</label>
					</div>
					<div class="checkbox">
						<label><form:checkbox path="naturalTypes"
								class="checkNature" value="LAKE" />Lake</label>
					</div>
					<div class="checkbox">
						<label><form:checkbox path="naturalTypes"
								class="checkNature" value="MOUNT" />Mountain</label>
					</div>
					<div class="checkbox">
						<label><form:checkbox path="naturalTypes"
								class="checkNature" value="RIVER" />River</label>
					</div>
					<div class="checkbox">
						<label><form:checkbox path="naturalTypes"
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
					<form:checkbox path="culture"  name="culture" class="checkCulture" id="checkCultureAll"
						value="" /> <a data-toggle="collapse" href="#collapse_cult">
						Cultural Places <i class="fa fa-chevron-down pull-right"></i></a>
				</h4>
			</div>
			<div id="collapse_cult" class="panel-collapse collapse">
				<div class="panel-body">
					<div class="checkbox">
						<label><form:checkbox path="culturalTypes" class="checkCulture"
							value="TEMPLE"/>Temple</label>
					</div>
					<div class="checkbox">
						<label><form:checkbox path="culturalTypes" class="checkCulture"
							value="VILLAGE"/>Village</label>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="btn-group" style="margin-top: 20px">
		<button type="submit" id="btn-filter-apply" class="btn btn-primary" name="filter.apply">Apply</button>
		<button class="btn btn-default" name="filter.reset">Reset</button>
		<!-- button class="btn btn-default" name="filter.save">Save</button-->
	</div>

</form:form>
	
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