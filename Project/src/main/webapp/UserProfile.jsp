<%@ include file="navbar.jsp" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8"/>
<title>Userprofile</title>
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
<h1>User Profiles</h1>
<h2>Add User Profile</h2>
<form action="profiles" method="post">
<input name="action" type="hidden" value="add"/>
<p>
<label for="userID">User ID:</label>
<input id="userID" name="userID" required="" type="number"/>
</p>
<p>
<label for="occupation">Occupation:</label>
<input id="occupation" name="occupation" required="" type="text"/>
</p>
<p>
<label for="maritalStatus">Marital Status:</label>
<input id="maritalStatus" name="maritalStatus" required="" type="text"/>
</p>
<p>
<label for="parentalStatus">Parental Status:</label>
<select id="parentalStatus" name="parentalStatus" required="">
<option value="true">Yes</option>
<option value="false">No</option>
</select>
</p>
<p>
<input type="submit" value="Add Profile"/>
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
<c:foreach items="${profileList}" var="profile">
<tr>
<td><c:out value="${profile.profileID}"></c:out></td>
<td><c:out value="${profile.userID}"></c:out></td>
<td><c:out value="${profile.occupation}"></c:out></td>
<td><c:out value="${profile.maritalStatus}"></c:out></td>
<td><c:out value="${profile.parentalStatus}"></c:out></td>
<td>
<form action="profiles" method="post">
<input name="action" type="hidden" value="update"/>
<input name="profileID" type="hidden" value="${profile.profileID}"/>
<label for="newOccupation">New Occupation:</label>
<input name="occupation" required="" type="text"/>
<input type="submit" value="Update"/>
</form>
</td>
<td>
<form action="profiles" method="post">
<input name="action" type="hidden" value="delete"/>
<input name="profileID" type="hidden" value="${profile.profileID}"/>
<input type="submit" value="Delete"/>
</form>
</td>
</tr>
</c:foreach>
</table></body>
</html>
