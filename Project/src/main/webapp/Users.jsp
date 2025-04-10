<%@ include file="navbar.jsp" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8"/>
<title>Users</title>
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
<h1>Users List</h1>
<h2>Add User</h2>
<form action="users" method="post">
<input name="action" type="hidden" value="add"/>
<p>
<label for="age">Age:</label>
<input id="age" name="age" required="" type="number"/>
</p>
<p>
<label for="gender">Gender:</label>
<input id="gender" name="gender" required="" type="text"/>
</p>
<p>
<label for="country">Country:</label>
<input id="country" name="country" required="" type="text"/>
</p>
<p>
<input type="submit" value="Add User"/>
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
<c:foreach items="${users}" var="user">
<tr>
<td><c:out value="${user.userID}"></c:out></td>
<td><c:out value="${user.age}"></c:out></td>
<td><c:out value="${user.gender}"></c:out></td>
<td><c:out value="${user.country}"></c:out></td>
<td>
<form action="users" method="post">
<input name="action" type="hidden" value="update"/>
<input name="userID" type="hidden" value="${user.userID}"/>
<label for="newAge">New Age:</label>
<input name="age" required="" type="number"/>
<input type="submit" value="Update"/>
</form>
</td>
<td>
<form action="users" method="post">
<input name="action" type="hidden" value="delete"/>
<input name="userID" type="hidden" value="${user.userID}"/>
<input type="submit" value="Delete"/>
</form>
</td>
</tr>
</c:foreach>
</table></body>
</html>
