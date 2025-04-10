<%@ include file="navbar.jsp" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8"/>
<title>Lifestyle</title>
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
<h1>Lifestyle Usage Records</h1>
<h2>Add Lifestyle Usage Record</h2>
<form action="lifestyle" method="post">
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
<label for="averageSleepTime">Average Sleep Time (hours):</label>
<input id="averageSleepTime" name="averageSleepTime" required="" step="0.1" type="number"/>
</p>
<p>
<label for="physicalActivityTime">Physical Activity Time (hours):</label>
<input id="physicalActivityTime" name="physicalActivityTime" required="" step="0.1" type="number"/>
</p>
<p>
<label for="readingTime">Reading Time (hours):</label>
<input id="readingTime" name="readingTime" required="" step="0.1" type="number"/>
</p>
<p>
<label for="workStudyTime">Work/Study Time (hours):</label>
<input id="workStudyTime" name="workStudyTime" required="" step="0.1" type="number"/>
</p>
<p>
<label for="timeSpentInOnlineCommunities">Time in Online Communities (hours):</label>
<input id="timeSpentInOnlineCommunities" name="timeSpentInOnlineCommunities" required="" step="0.1" type="number"/>
</p>
<p>
<label for="timeOnEducationalPlatforms">Time on Educational Platforms (hours):</label>
<input id="timeOnEducationalPlatforms" name="timeOnEducationalPlatforms" required="" step="0.1" type="number"/>
</p>
<p>
<label for="newsConsumptionTime">News Consumption Time (hours):</label>
<input id="newsConsumptionTime" name="newsConsumptionTime" required="" step="0.1" type="number"/>
</p>
<p>
<label for="adInteractionCount">Ad Interaction Count:</label>
<input id="adInteractionCount" name="adInteractionCount" required="" type="number"/>
</p>
<p>
<input type="submit" value="Add Lifestyle Usage Record"/>
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
<c:foreach items="${lifestyleList}" var="lifestyle">
<tr>
<td><c:out value="${lifestyle.usageID}"></c:out></td>
<td><c:out value="${lifestyle.userID}"></c:out></td>
<td><c:out value="${lifestyle.totalScreenTime}"></c:out></td>
<td><c:out value="${lifestyle.averageSleepTime}"></c:out></td>
<td><c:out value="${lifestyle.physicalActivityTime}"></c:out></td>
<td><c:out value="${lifestyle.readingTime}"></c:out></td>
<td><c:out value="${lifestyle.workStudyTime}"></c:out></td>
<td><c:out value="${lifestyle.timeSpentInOnlineCommunities}"></c:out></td>
<td><c:out value="${lifestyle.timeOnEducationalPlatforms}"></c:out></td>
<td><c:out value="${lifestyle.newsConsumptionTime}"></c:out></td>
<td><c:out value="${lifestyle.adInteractionCount}"></c:out></td>
<td>
<form action="lifestyle" method="post">
<input name="action" type="hidden" value="update"/>
<input name="usageID" type="hidden" value="${lifestyle.usageID}"/>
<label for="newSleepTime">New Average Sleep Time:</label>
<input name="averageSleepTime" required="" step="0.1" type="number"/>
<input type="submit" value="Update"/>
</form>
</td>
<td>
<form action="lifestyle" method="post">
<input name="action" type="hidden" value="delete"/>
<input name="usageID" type="hidden" value="${lifestyle.usageID}"/>
<input type="submit" value="Delete"/>
</form>
</td>
</tr>
</c:foreach>
</table></body>
</html>
