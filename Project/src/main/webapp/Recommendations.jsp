<%@ include file="navbar.jsp" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8"/>
<title>Recommendations</title>
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
<h1>User Product Recommendations</h1>
<form action="recommendations" method="get">
<p>
<label for="userID">Enter User ID:</label>
<input id="userID" name="userID" required="" type="number"/>
</p>
<p>
<input type="submit" value="Get Recommendations"/>
</p>
</form>
<c:if test="${not empty errorMessage}">
<p style="color: red;"><c:out value="${errorMessage}"></c:out></p>
</c:if>
<c:if test="${not empty recommendations}">
<h2>Recommendations for User ID: <c:out value="${userID}"></c:out></h2>
<ul>
<c:foreach items="${recommendations}" var="recommendation">
<li><c:out value="${recommendation}"></c:out></li>
</c:foreach>
</ul>
</c:if></body>
</html>
