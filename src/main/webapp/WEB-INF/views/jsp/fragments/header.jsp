<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags" %>

<head>
	<title>Beautiful Places</title>
	
	<spring:url value="/resources/core/css/main.css" var="mainCss" />
	<spring:url	value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css" var="bootstrapCss" />
	
	<link rel="stylesheet"
		href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" />

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	
	<!-- Bootstrap core CSS -->
	<link href="${bootstrapCss}" rel="stylesheet" />
	<!-- Custom project CSS -->
	<link href="${mainCss}" rel="stylesheet" />
	
	<spring:url value="/resources/core/css/bootstrap3-wysihtml5.min.css" var="wysiwygCss" />
	<link href="${wysiwygCss}" rel="stylesheet" />
	
	<c:url value="/logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:url var="home" value="/" scope="request" />

</head>

<body>

<nav class="navbar navbar-fixed-top navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#navbar" aria-expanded="false" aria-controls="navbar">
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
				<li><a href="#">Weather</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Places <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/places/' />">View As List</a></li>
						<li><a href="<c:url value='/map' />">On the map</a></li>
						<sec:authorize access="isAuthenticated()">
							<li class="divider"></li>
							<li><a href="<c:url value='/places/add' />">Add New Place</a></li>
							<li><a href="<c:url value='/places/me' />">My Places</a></li>
						</sec:authorize>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">

				<sec:authorize access="isAnonymous()">
					<li><a href="<c:url value='/login' />">Sign In</a></li>
					<li><a href="<c:url value='/register' />">Sign Up</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">
							Hello <sec:authentication property="principal.username" /> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<sec:authorize access="hasRole('ADMIN')">
								<li><a href="<c:url value='/users/list' />">Manage Users</a></li>
								<li><a href="<c:url value='/places/list_admin' />">Manage Places</a></li>
								<li class="divider"></li>
							</sec:authorize>
							<li><a href="<c:url value='/users/me' />">My Profile</a></li>
							<li><a href="javascript:formSubmit()">Log out</a></li>
						</ul>
					</li>



				</sec:authorize>
				<li><a href="">About</a></li>
				<li><a href="">Feedback</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
