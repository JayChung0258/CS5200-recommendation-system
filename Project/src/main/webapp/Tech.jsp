<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tech Awareness Management</title>
</head>
<body>
    <h1>Tech Awareness Records</h1>
    
    <h2>Add Tech Awareness Record</h2>
    <form action="tech" method="post">
        <input type="hidden" name="action" value="add">
        <p>
            <label for="userID">User ID:</label>
            <input type="number" id="userID" name="userID" required>
        </p>
        <p>
            <label for="techLevel">Tech Savviness Level (1-10):</label>
            <input type="number" id="techLevel" name="techLevel" min="1" max="10" required>
        </p>
        <p>
            <label for="digitalAwareness">Digital Well-being Awareness:</label>
            <input type="text" id="digitalAwareness" name="digitalAwareness" required>
        </p>
        <p>
            <input type="submit" value="Add Tech Awareness Record">
        </p>
    </form>
    
    <h2>Existing Tech Awareness Records</h2>
    <table border="1">
        <tr>
            <th>Tech ID</th>
            <th>User ID</th>
            <th>Tech Savviness Level</th>
            <th>Digital Well-being Awareness</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${techAwarenessList}" var="tech">
            <tr>
                <td><c:out value="${tech.techID}" /></td>
                <td><c:out value="${tech.userID}" /></td>
                <td><c:out value="${tech.techSavvinessLevel}" /></td>
                <td><c:out value="${tech.digitalWellBeingAwareness}" /></td>
                <td>
                    <form action="tech" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="techID" value="${tech.techID}">
                        <label for="newLevel">New Tech Savviness Level:</label>
                        <input type="number" min="1" max="10" name="techLevel" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="tech" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="techID" value="${tech.techID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>