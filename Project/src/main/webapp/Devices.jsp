<%@ include file="navbar.jsp" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8"/>
<title>Devices</title>
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
<h1>Devices List</h1>
<h2>Add Device</h2>
<form action="devices" method="post">
<input name="action" type="hidden" value="add"/>
<p>
<label for="userID">User ID:</label>
<input id="userID" name="userID" required="" type="number"/>
</p>
<p>
<label for="deviceType">Device Type:</label>
<input id="deviceType" name="deviceType" required="" type="text"/>
</p>
<p>
<label for="internetSpeed">Internet Speed (Mbps):</label>
<input id="internetSpeed" name="internetSpeed" required="" step="0.01" type="number"/>
</p>
<p>
<label for="preferredDevice">Preferred Device for Entertainment:</label>
<input id="preferredDevice" name="preferredDevice" type="text"/>
</p>
<p>
<label for="dataPlan">Data Plan Used:</label>
<input id="dataPlan" name="dataPlan" type="text"/>
</p>
<p>
<input type="submit" value="Add Device"/>
</p>
</form>
<h2>Existing Devices</h2>
<table border="1">
<tr>
<th>Device ID</th>
<th>User ID</th>
<th>Device Type</th>
<th>Internet Speed (Mbps)</th>
<th>Preferred Device</th>
<th>Data Plan</th>
<th>Update</th>
<th>Delete</th>
</tr>
<c:foreach items="${devices}" var="device">
<tr>
<td><c:out value="${device.deviceID}"></c:out></td>
<td><c:out value="${device.userID}"></c:out></td>
<td><c:out value="${device.deviceType}"></c:out></td>
<td><c:out value="${device.internetSpeedMbps}"></c:out></td>
<td><c:out value="${device.preferredDeviceForEntertainment}"></c:out></td>
<td><c:out value="${device.dataPlanUsed}"></c:out></td>
<td>
<form action="devices" method="post">
<input name="action" type="hidden" value="update"/>
<input name="deviceID" type="hidden" value="${device.deviceID}"/>
<label for="newSpeed">New Internet Speed:</label>
<input name="internetSpeed" required="" step="0.01" type="number"/>
<input type="submit" value="Update"/>
</form>
</td>
<td>
<form action="devices" method="post">
<input name="action" type="hidden" value="delete"/>
<input name="deviceID" type="hidden" value="${device.deviceID}"/>
<input type="submit" value="Delete"/>
</form>
</td>
</tr>
</c:foreach>
</table></body>
</html>
