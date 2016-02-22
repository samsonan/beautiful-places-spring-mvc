<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-fixed-top navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/' />">Beautiful
				Places</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbar">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value='/welcome' />">Welcome</a></li>
				<li><a href="#">Weather</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Places <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/places/list_obsolete' />">View As List 1</a></li>
						<li><a href="<c:url value='/places/' />">View As List 2</a></li>
						<li><a href="<c:url value='/map' />">On the map</a></li>
						<li class="divider"></li>
						<li><a href="<c:url value='/places/add' />">Add New</a></li>
						<li><a href="<c:url value='/places/add-image-1' />">Img Upload</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="">About</a></li>
				<li><a href="<c:url value='/login' />">Sign In</a></li>
				<li><a href="<c:url value='/users/add' />">Sign Up</a></li>
				<li><a href="">Feedback</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
