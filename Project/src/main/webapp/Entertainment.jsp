<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Entertainment Usage Management</title>
</head>
<body>
    <h1>Entertainment Usage Records</h1>
    
    <h2>Add Entertainment Usage Record</h2>
    <form action="entertainment" method="post">
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
            <label for="dailyEntertainmentTime">Daily Entertainment Time (hours):</label>
            <input type="number" step="0.1" id="dailyEntertainmentTime" name="dailyEntertainmentTime" required>
        </p>
        <p>
            <label for="dailyVideoTime">Daily Video Content Time (hours):</label>
            <input type="number" step="0.1" id="dailyVideoTime" name="dailyVideoContentTime" required>
        </p>
        <p>
            <label for="dailyGamingTime">Daily Gaming Time (hours):</label>
            <input type="number" step="0.1" id="dailyGamingTime" name="dailyGamingTime" required>
        </p>
        <p>
            <label for="dailyMusicTime">Daily Music Listening Time (hours):</label>
            <input type="number" step="0.1" id="dailyMusicTime" name="dailyMusicListeningTime" required>
        </p>
        <p>
            <label for="subscriptionPlatforms">Number of Subscription Platforms:</label>
            <input type="number" id="subscriptionPlatforms" name="subscriptionPlatforms" required>
        </p>
        <p>
            <label for="preferredContent">Preferred Content Type:</label>
            <input type="text" id="preferredContent" name="preferredContentType" required>
        </p>
        <p>
            <label for="preferredPlatform">Preferred Entertainment Platform:</label>
            <input type="text" id="preferredPlatform" name="preferredEntertainmentPlatform" required>
        </p>
        <p>
            <input type="submit" value="Add Entertainment Usage Record">
        </p>
    </form>
    
    <h2>Existing Entertainment Usage Records</h2>
    <table border="1">
        <tr>
            <th>Usage ID</th>
            <th>User ID</th>
            <th>Total Screen Time</th>
            <th>Daily Entertainment Time</th>
            <th>Daily Video Content Time</th>
            <th>Daily Gaming Time</th>
            <th>Daily Music Listening Time</th>
            <th>Subscription Platforms</th>
            <th>Preferred Content Type</th>
            <th>Preferred Platform</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${entertainmentList}" var="entertainment">
            <tr>
                <td><c:out value="${entertainment.usageID}" /></td>
                <td><c:out value="${entertainment.userID}" /></td>
                <td><c:out value="${entertainment.totalScreenTime}" /></td>
                <td><c:out value="${entertainment.dailyEntertainmentTime}" /></td>
                <td><c:out value="${entertainment.dailyVideoContentTime}" /></td>
                <td><c:out value="${entertainment.dailyGamingTime}" /></td>
                <td><c:out value="${entertainment.dailyMusicListeningTime}" /></td>
                <td><c:out value="${entertainment.subscriptionPlatforms}" /></td>
                <td><c:out value="${entertainment.preferredContentType}" /></td>
                <td><c:out value="${entertainment.preferredEntertainmentPlatform}" /></td>
                <td>
                    <form action="entertainment" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="usageID" value="${entertainment.usageID}">
                        <label for="newDailyTime">New Daily Entertainment Time:</label>
                        <input type="number" step="0.1" name="dailyEntertainmentTime" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="entertainment" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="usageID" value="${entertainment.usageID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
