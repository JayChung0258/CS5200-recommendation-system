<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Social Media Usage Management</title>
</head>
<body>
    <h1>Social Media Usage Records</h1>
    
    <h2>Add Social Media Usage Record</h2>
    <form action="${pageContext.request.contextPath}/socialmedia" method="post">
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
            <label for="dailySocialMediaTime">Daily Social Media Time (hours):</label>
            <input type="number" step="0.1" id="dailySocialMediaTime" name="dailySocialMediaTime" required>
        </p>
        <p>
            <label for="dailyMessagingTime">Daily Messaging Time (hours):</label>
            <input type="number" step="0.1" id="dailyMessagingTime" name="dailyMessagingTime" required>
        </p>
        <p>
            <label for="platformsUsed">Number of Social Media Platforms Used:</label>
            <input type="number" id="platformsUsed" name="platformsUsed" required>
        </p>
        <p>
            <label for="primaryPlatform">Primary Social Media Platform:</label>
            <input type="text" id="primaryPlatform" name="primaryPlatform" required>
        </p>
        <p>
            <label for="primarySocialMediaGoal">Primary Social Media Goal:</label>
            <input type="text" id="primarySocialMediaGoal" name="primarySocialMediaGoal" required>
        </p>
        <p>
            <label for="notifications">Notifications Received Daily:</label>
            <input type="number" id="notifications" name="notifications" required>
        </p>
        <p>
            <label for="fatigueLevel">Social Media Fatigue Level (1-10):</label>
            <input type="number" id="fatigueLevel" name="fatigueLevel" min="1" max="10" required>
        </p>
        <p>
            <input type="submit" value="Add Social Media Usage Record">
        </p>
    </form>
    
    <h2>Existing Social Media Usage Records</h2>
    <table border="1">
        <tr>
            <th>Usage ID</th>
            <th>User ID</th>
            <th>Total Screen Time</th>
            <th>Daily Social Media Time</th>
            <th>Daily Messaging Time</th>
            <th>Platforms Used</th>
            <th>Primary Platform</th>
            <th>Primary Goal</th>
            <th>Notifications</th>
            <th>Fatigue Level</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${socialMediaList}" var="socialMedia">
            <tr>
                <td><c:out value="${socialMedia.usageID}" /></td>
                <td><c:out value="${socialMedia.userID}" /></td>
                <td><c:out value="${socialMedia.totalScreenTime}" /></td>
                <td><c:out value="${socialMedia.dailySocialMediaTime}" /></td>
                <td><c:out value="${socialMedia.dailyMessagingTime}" /></td>
                <td><c:out value="${socialMedia.socialMediaPlatformsUsed}" /></td>
                <td><c:out value="${socialMedia.primaryPlatform}" /></td>
                <td><c:out value="${socialMedia.primarySocialMediaGoal}" /></td>
                <td><c:out value="${socialMedia.notificationsReceivedDaily}" /></td>
                <td><c:out value="${socialMedia.socialMediaFatigueLevel}" /></td>
                <td>
                    <form action="${pageContext.request.contextPath}/socialmedia" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="usageID" value="${socialMedia.usageID}">
                        <label for="newDailyTime">New Daily Social Media Time:</label>
                        <input type="number" step="0.1" name="dailySocialMediaTime" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/socialmedia" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="usageID" value="${socialMedia.usageID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
