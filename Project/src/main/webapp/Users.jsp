<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users Management</title>
</head>
<body>
    <h1>Users List</h1>
    
    <h2>Add User</h2>
    <form action="users" method="post">
        <input type="hidden" name="action" value="add">
        <p>
            <label for="age">Age:</label>
            <input type="number" id="age" name="age" required>
        </p>
        <p>
            <label for="gender">Gender:</label>
            <input type="text" id="gender" name="gender" required>
        </p>
        <p>
            <label for="country">Country:</label>
            <input type="text" id="country" name="country" required>
        </p>
        <p>
            <input type="submit" value="Add User">
        </p>
    </form>
    
    <h2>Existing Users</h2>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Country</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.userID}" /></td>
                <td><c:out value="${user.age}" /></td>
                <td><c:out value="${user.gender}" /></td>
                <td><c:out value="${user.country}" /></td>
                <td>
                    <form action="users" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="userID" value="${user.userID}">
                        <label for="newAge">New Age:</label>
                        <input type="number" name="age" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="users" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="userID" value="${user.userID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
