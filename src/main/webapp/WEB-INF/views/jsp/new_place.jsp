<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Beautiful Places</title>

<spring:url value="/resources/core/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />

</head>
<body>
<h2>Add new place</h2>


    <form:form method="POST" modelAttribute="place">
        <form:input type="hidden" path="id" id="id"/>
        <table>
            <tr>
                <td><label for="title">Title: </label> </td>
                <td><form:input path="title" id="title"/></td>
                <td><form:errors path="title" cssClass="error"/></td>
            </tr>
         
            <tr>
                <td><label for="description">Description: </label> </td>
                <td><form:input path="description" id="description"/></td>
                <td><form:errors path="description" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="lat">Latitude: </label> </td>
                <td><form:input path="lat" id="lat"/></td>
                <td><form:errors path="lat" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="lon">Longitude: </label> </td>
                <td><form:input path="lon" id="lon"/></td>
                <td><form:errors path="lon" cssClass="error"/></td>
            </tr>

     
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Create"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>
    <br/>
    <br/>
    Go back to <a href="<c:url value='/list' />">List of All Places</a>


</body>
</html>
