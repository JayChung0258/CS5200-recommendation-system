<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Self Report Management</title>
</head>
<body>
    <h1>Self Report Records</h1>
    
    <h2>Add Self Report Record</h2>
    <form action="selfreport" method="post">
        <input type="hidden" name="action" value="add">
        <p>
            <label for="userID">User ID:</label>
            <input type="number" id="userID" name="userID" required>
        </p>
        <p>
            <label for="sleepQuality">Sleep Quality (1-10):</label>
            <input type="number" id="sleepQuality" name="sleepQuality" min="1" max="10" required>
        </p>
        <p>
            <label for="socialIsolationFeeling">Social Isolation Feeling (1-10):</label>
            <input type="number" id="socialIsolationFeeling" name="socialIsolationFeeling" min="1" max="10" required>
        </p>
        <p>
            <input type="submit" value="Add Self Report Record">
        </p>
    </form>
    
    <h2>Existing Self Report Records</h2>
    <table border="1">
        <tr>
            <th>Self Report ID</th>
            <th>User ID</th>
            <th>Sleep Quality</th>
            <th>Social Isolation Feeling</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${selfReports}" var="selfReport">
            <tr>
                <td><c:out value="${selfReport.selfReportID}" /></td>
                <td><c:out value="${selfReport.userID}" /></td>
                <td><c:out value="${selfReport.sleepQuality}" /></td>
                <td><c:out value="${selfReport.socialIsolationFeeling}" /></td>
                <td>
                    <form action="selfreport" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="selfReportID" value="${selfReport.selfReportID}">
                        <label for="newSleepQuality">New Sleep Quality:</label>
                        <input type="number" min="1" max="10" name="sleepQuality" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="selfreport" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="selfReportID" value="${selfReport.selfReportID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
