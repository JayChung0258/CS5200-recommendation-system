<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Lifestyle Usage Management</title>
</head>
<body>
    <h1>Lifestyle Usage Records</h1>
    
    <h2>Add Lifestyle Usage Record</h2>
    <form action="lifestyle" method="post">
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
            <label for="averageSleepTime">Average Sleep Time (hours):</label>
            <input type="number" step="0.1" id="averageSleepTime" name="averageSleepTime" required>
        </p>
        <p>
            <label for="physicalActivityTime">Physical Activity Time (hours):</label>
            <input type="number" step="0.1" id="physicalActivityTime" name="physicalActivityTime" required>
        </p>
        <p>
            <label for="readingTime">Reading Time (hours):</label>
            <input type="number" step="0.1" id="readingTime" name="readingTime" required>
        </p>
        <p>
            <label for="workStudyTime">Work/Study Time (hours):</label>
            <input type="number" step="0.1" id="workStudyTime" name="workStudyTime" required>
        </p>
        <p>
            <label for="timeSpentInOnlineCommunities">Time in Online Communities (hours):</label>
            <input type="number" step="0.1" id="timeSpentInOnlineCommunities" name="timeSpentInOnlineCommunities" required>
        </p>
        <p>
            <label for="timeOnEducationalPlatforms">Time on Educational Platforms (hours):</label>
            <input type="number" step="0.1" id="timeOnEducationalPlatforms" name="timeOnEducationalPlatforms" required>
        </p>
        <p>
            <label for="newsConsumptionTime">News Consumption Time (hours):</label>
            <input type="number" step="0.1" id="newsConsumptionTime" name="newsConsumptionTime" required>
        </p>
        <p>
            <label for="adInteractionCount">Ad Interaction Count:</label>
            <input type="number" id="adInteractionCount" name="adInteractionCount" required>
        </p>
        <p>
            <input type="submit" value="Add Lifestyle Usage Record">
        </p>
    </form>
    
    <h2>Existing Lifestyle Usage Records</h2>
    <table border="1">
        <tr>
            <th>Usage ID</th>
            <th>User ID</th>
            <th>Total Screen Time</th>
            <th>Average Sleep Time</th>
            <th>Physical Activity Time</th>
            <th>Reading Time</th>
            <th>Work/Study Time</th>
            <th>Online Community Time</th>
            <th>Educational Platform Time</th>
            <th>News Consumption Time</th>
            <th>Ad Interactions</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${lifestyleList}" var="lifestyle">
            <tr>
                <td><c:out value="${lifestyle.usageID}" /></td>
                <td><c:out value="${lifestyle.userID}" /></td>
                <td><c:out value="${lifestyle.totalScreenTime}" /></td>
                <td><c:out value="${lifestyle.averageSleepTime}" /></td>
                <td><c:out value="${lifestyle.physicalActivityTime}" /></td>
                <td><c:out value="${lifestyle.readingTime}" /></td>
                <td><c:out value="${lifestyle.workStudyTime}" /></td>
                <td><c:out value="${lifestyle.timeSpentInOnlineCommunities}" /></td>
                <td><c:out value="${lifestyle.timeOnEducationalPlatforms}" /></td>
                <td><c:out value="${lifestyle.newsConsumptionTime}" /></td>
                <td><c:out value="${lifestyle.adInteractionCount}" /></td>
                <td>
                    <form action="lifestyle" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="usageID" value="${lifestyle.usageID}">
                        <label for="newSleepTime">New Average Sleep Time:</label>
                        <input type="number" step="0.1" name="averageSleepTime" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="lifestyle" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="usageID" value="${lifestyle.usageID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
