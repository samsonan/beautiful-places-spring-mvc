<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/core/js/main.js" var="mainJs" />
<spring:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"
		var="bootstrapJs" />

<script src="${mainJs}"></script>
<script src="${bootstrapJs}"></script>

<!-- wysiwyg plugin -->

<spring:url value="/resources/core/js/plugins/bootstrap3-wysihtml5.all.min.js" var="wysiwygAllJs"/>
<spring:url value="/resources/core/js/plugins/bootstrap3-wysihtml5.min.js" var="wysiwygJs"/>

<script src="${wysiwygAllJs}"></script>
<script src="${wysiwygJs}"></script>


