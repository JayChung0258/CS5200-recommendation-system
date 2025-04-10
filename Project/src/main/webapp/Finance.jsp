<%@ include file="navbar.jsp" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8"/>
<title>Finance</title>
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
<h1>Finance Records</h1>
<h2>Add Finance Record</h2>
<form action="finance" method="post">
<input name="action" type="hidden" value="add"/>
<p>
<label for="userID">User ID:</label>
<input id="userID" name="userID" required="" type="number"/>
</p>
<p>
<label for="monthlyIncome">Monthly Income (USD):</label>
<input id="monthlyIncome" name="monthlyIncome" required="" step="0.01" type="number"/>
</p>
<p>
<label for="monthlyExpense">Monthly Expense (USD):</label>
<input id="monthlyExpense" name="monthlyExpense" required="" step="0.01" type="number"/>
</p>
<p>
<input type="submit" value="Add Finance Record"/>
</p>
</form>
<h2>Existing Finance Records</h2>
<table border="1">
<tr>
<th>Finance ID</th>
<th>User ID</th>
<th>Monthly Income (USD)</th>
<th>Monthly Expense (USD)</th>
<th>Update</th>
<th>Delete</th>
</tr>
<c:foreach items="${finances}" var="finance">
<tr>
<td><c:out value="${finance.financeID}"></c:out></td>
<td><c:out value="${finance.userID}"></c:out></td>
<td><c:out value="${finance.monthlyIncomeUSD}"></c:out></td>
<td><c:out value="${finance.monthlyExpenditureUSD}"></c:out></td>
<td>
<form action="finance" method="post">
<input name="action" type="hidden" value="update"/>
<input name="financeID" type="hidden" value="${finance.financeID}"/>
<label for="newIncome">New Monthly Income:</label>
<input name="monthlyIncome" required="" step="0.01" type="number"/>
<input type="submit" value="Update"/>
</form>
</td>
<td>
<form action="finance" method="post">
<input name="action" type="hidden" value="delete"/>
<input name="financeID" type="hidden" value="${finance.financeID}"/>
<input type="submit" value="Delete"/>
</form>
</td>
</tr>
</c:foreach>
</table></body>
</html>
