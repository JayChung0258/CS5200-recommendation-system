<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Usage Management</title>
</head>
<body>
    <h1>Usage Records</h1>
    
    <h2>Add Usage Record</h2>
    <form action="usage" method="post">
        <input type="hidden" name="action" value="add">
        <p>
            <label for="userID">User ID:</label>
            <input type="number" id="userID" name="userID" required>
        </p>
        <p>
            <label for="totalScreenTime">Total Screen Time (hours):</label>
            <input type="number" step="0.1" id="totalScreenTime" name="totalScreenTime" required>
        </p>
        <p>
            <input type="submit" value="Add Usage Record">
        </p>
    </form>
    
    <h2>Existing Usage Records</h2>
    <table border="1">
        <tr>
            <th>Usage ID</th>
            <th>User ID</th>
            <th>Total Screen Time (hours)</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${usageList}" var="usage">
            <tr>
                <td><c:out value="${usage.usageID}" /></td>
                <td><c:out value="${usage.userID}" /></td>
                <td><c:out value="${usage.totalScreenTime}" /></td>
                <td>
                    <form action="usage" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="usageID" value="${usage.usageID}">
                        <label for="newScreenTime">New Screen Time:</label>
                        <input type="number" step="0.1" name="totalScreenTime" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="usage" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="usageID" value="${usage.usageID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
