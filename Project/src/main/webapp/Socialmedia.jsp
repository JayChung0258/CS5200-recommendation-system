<%@ include file="navbar.jsp" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8"/>
<title>Socialmedia</title>
<style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f9f9f9; }
        h1, h2 { color: #333; }
        form { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        input[type="text"], input[type="number"], select { width: 100%; padding: 8px; margin: 6px 0 12px; border: 1px solid #ccc; border-radius: 4px; }
        input[type="submit"] { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }
        input[type="submit"]:hover { background-color: #45a049; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { text-align: left; padding: 10px; border-bottom: 1px solid #ddd; }
        tr:hover { background-color: #f1f1f1; }
    </style>
</head>
<body>
<br/>
<h1>Social Media Usage Records</h1>
<h2>Add Social Media Usage Record</h2>
<form action="${pageContext.request.contextPath}/socialmedia" method="post">
<input name="action" type="hidden" value="add"/>
<p>
<label for="userID">User ID:</label>
<input id="userID" name="userID" required="" type="number"/>
</p>
<p>
<label for="totalScreenTime">Total Screen Time (hours):</label>
<input id="totalScreenTime" name="totalScreenTime" required="" step="0.1" type="number"/>
</p>
<p>
<label for="dailySocialMediaTime">Daily Social Media Time (hours):</label>
<input id="dailySocialMediaTime" name="dailySocialMediaTime" required="" step="0.1" type="number"/>
</p>
<p>
<label for="dailyMessagingTime">Daily Messaging Time (hours):</label>
<input id="dailyMessagingTime" name="dailyMessagingTime" required="" step="0.1" type="number"/>
</p>
<p>
<label for="platformsUsed">Number of Social Media Platforms Used:</label>
<input id="platformsUsed" name="platformsUsed" required="" type="number"/>
</p>
<p>
<label for="primaryPlatform">Primary Social Media Platform:</label>
<input id="primaryPlatform" name="primaryPlatform" required="" type="text"/>
</p>
<p>
<label for="primarySocialMediaGoal">Primary Social Media Goal:</label>
<input id="primarySocialMediaGoal" name="primarySocialMediaGoal" required="" type="text"/>
</p>
<p>
<label for="notifications">Notifications Received Daily:</label>
<input id="notifications" name="notifications" required="" type="number"/>
</p>
<p>
<label for="fatigueLevel">Social Media Fatigue Level (1-10):</label>
<input id="fatigueLevel" max="10" min="1" name="fatigueLevel" required="" type="number"/>
</p>
<p>
<input type="submit" value="Add Social Media Usage Record"/>
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
<c:foreach items="${socialMediaList}" var="socialMedia">
<tr>
<td><c:out value="${socialMedia.usageID}"></c:out></td>
<td><c:out value="${socialMedia.userID}"></c:out></td>
<td><c:out value="${socialMedia.totalScreenTime}"></c:out></td>
<td><c:out value="${socialMedia.dailySocialMediaTime}"></c:out></td>
<td><c:out value="${socialMedia.dailyMessagingTime}"></c:out></td>
<td><c:out value="${socialMedia.socialMediaPlatformsUsed}"></c:out></td>
<td><c:out value="${socialMedia.primaryPlatform}"></c:out></td>
<td><c:out value="${socialMedia.primarySocialMediaGoal}"></c:out></td>
<td><c:out value="${socialMedia.notificationsReceivedDaily}"></c:out></td>
<td><c:out value="${socialMedia.socialMediaFatigueLevel}"></c:out></td>
<td>
<form action="${pageContext.request.contextPath}/socialmedia" method="post">
<input name="action" type="hidden" value="update"/>
<input name="usageID" type="hidden" value="${socialMedia.usageID}"/>
<label for="newDailyTime">New Daily Social Media Time:</label>
<input name="dailySocialMediaTime" required="" step="0.1" type="number"/>
<input type="submit" value="Update"/>
</form>
</td>
<td>
<form action="${pageContext.request.contextPath}/socialmedia" method="post">
<input name="action" type="hidden" value="delete"/>
<input name="usageID" type="hidden" value="${socialMedia.usageID}"/>
<input type="submit" value="Delete"/>
</form>
</td>
</tr>
</c:foreach>
</table></body>
</html>
