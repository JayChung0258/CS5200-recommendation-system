<%@ include file="navbar.jsp" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8"/>
<title>Selfreport</title>
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
<h1>Self Report Records</h1>
<h2>Add Self Report Record</h2>
<form action="selfreport" method="post">
<input name="action" type="hidden" value="add"/>
<p>
<label for="userID">User ID:</label>
<input id="userID" name="userID" required="" type="number"/>
</p>
<p>
<label for="sleepQuality">Sleep Quality (1-10):</label>
<input id="sleepQuality" max="10" min="1" name="sleepQuality" required="" type="number"/>
</p>
<p>
<label for="socialIsolationFeeling">Social Isolation Feeling (1-10):</label>
<input id="socialIsolationFeeling" max="10" min="1" name="socialIsolationFeeling" required="" type="number"/>
</p>
<p>
<input type="submit" value="Add Self Report Record"/>
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
<c:foreach items="${selfReports}" var="selfReport">
<tr>
<td><c:out value="${selfReport.selfReportID}"></c:out></td>
<td><c:out value="${selfReport.userID}"></c:out></td>
<td><c:out value="${selfReport.sleepQuality}"></c:out></td>
<td><c:out value="${selfReport.socialIsolationFeeling}"></c:out></td>
<td>
<form action="selfreport" method="post">
<input name="action" type="hidden" value="update"/>
<input name="selfReportID" type="hidden" value="${selfReport.selfReportID}"/>
<label for="newSleepQuality">New Sleep Quality:</label>
<input max="10" min="1" name="sleepQuality" required="" type="number"/>
<input type="submit" value="Update"/>
</form>
</td>
<td>
<form action="selfreport" method="post">
<input name="action" type="hidden" value="delete"/>
<input name="selfReportID" type="hidden" value="${selfReport.selfReportID}"/>
<input type="submit" value="Delete"/>
</form>
</td>
</tr>
</c:foreach>
</table></body>
</html>
