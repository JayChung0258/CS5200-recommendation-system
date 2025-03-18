<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Recommendations</title>
</head>
<body>
    <h1>User Product Recommendations</h1>
    
    <form action="recommendations" method="get">
        <p>
            <label for="userID">Enter User ID:</label>
            <input type="number" id="userID" name="userID" required>
        </p>
        <p>
            <input type="submit" value="Get Recommendations">
        </p>
    </form>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;"><c:out value="${errorMessage}" /></p>
    </c:if>

    <c:if test="${not empty recommendations}">
        <h2>Recommendations for User ID: <c:out value="${userID}" /></h2>
        <ul>
            <c:forEach items="${recommendations}" var="recommendation">
                <li><c:out value="${recommendation}" /></li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>
