<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Profile Management</title>
</head>
<body>
    <h1>User Profiles</h1>

    <h2>Add User Profile</h2>
    <form action="profiles" method="post">
        <input type="hidden" name="action" value="add">
        <p>
            <label for="userID">User ID:</label>
            <input type="number" id="userID" name="userID" required>
        </p>
        <p>
            <label for="occupation">Occupation:</label>
            <input type="text" id="occupation" name="occupation" required>
        </p>
        <p>
            <label for="maritalStatus">Marital Status:</label>
            <input type="text" id="maritalStatus" name="maritalStatus" required>
        </p>
        <p>
            <label for="parentalStatus">Parental Status:</label>
            <select id="parentalStatus" name="parentalStatus" required>
                <option value="true">Yes</option>
                <option value="false">No</option>
            </select>
        </p>
        <p>
            <input type="submit" value="Add Profile">
        </p>
    </form>

    <h2>Existing User Profiles</h2>
    <table border="1">
        <tr>
            <th>Profile ID</th>
            <th>User ID</th>
            <th>Occupation</th>
            <th>Marital Status</th>
            <th>Parental Status</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${profileList}" var="profile">
            <tr>
                <td><c:out value="${profile.profileID}" /></td>
                <td><c:out value="${profile.userID}" /></td>
                <td><c:out value="${profile.occupation}" /></td>
                <td><c:out value="${profile.maritalStatus}" /></td>
                <td><c:out value="${profile.parentalStatus}" /></td>
                <td>
                    <form action="profiles" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="profileID" value="${profile.profileID}">
                        <label for="newOccupation">New Occupation:</label>
                        <input type="text" name="occupation" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="profiles" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="profileID" value="${profile.profileID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
