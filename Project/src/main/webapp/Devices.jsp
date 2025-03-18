<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Devices Management</title>
</head>
<body>
    <h1>Devices List</h1>
    
    <h2>Add Device</h2>
    <form action="devices" method="post">
        <input type="hidden" name="action" value="add">
        <p>
            <label for="userID">User ID:</label>
            <input type="number" id="userID" name="userID" required>
        </p>
        <p>
            <label for="deviceType">Device Type:</label>
            <input type="text" id="deviceType" name="deviceType" required>
        </p>
        <p>
            <label for="internetSpeed">Internet Speed (Mbps):</label>
            <input type="number" step="0.01" id="internetSpeed" name="internetSpeed" required>
        </p>
        <p>
            <label for="preferredDevice">Preferred Device for Entertainment:</label>
            <input type="text" id="preferredDevice" name="preferredDevice">
        </p>
        <p>
            <label for="dataPlan">Data Plan Used:</label>
            <input type="text" id="dataPlan" name="dataPlan">
        </p>
        <p>
            <input type="submit" value="Add Device">
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
        <c:forEach items="${devices}" var="device">
            <tr>
                <td><c:out value="${device.deviceID}" /></td>
                <td><c:out value="${device.userID}" /></td>
                <td><c:out value="${device.deviceType}" /></td>
                <td><c:out value="${device.internetSpeedMbps}" /></td>
                <td><c:out value="${device.preferredDeviceForEntertainment}" /></td>
                <td><c:out value="${device.dataPlanUsed}" /></td>
                <td>
                    <form action="devices" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="deviceID" value="${device.deviceID}">
                        <label for="newSpeed">New Internet Speed:</label>
                        <input type="number" step="0.01" name="internetSpeed" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="devices" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="deviceID" value="${device.deviceID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
