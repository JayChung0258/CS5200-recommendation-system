<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Finance Management</title>
</head>
<body>
    <h1>Finance Records</h1>
    
    <h2>Add Finance Record</h2>
    <form action="finance" method="post">
        <input type="hidden" name="action" value="add">
        <p>
            <label for="userID">User ID:</label>
            <input type="number" id="userID" name="userID" required>
        </p>
        <p>
            <label for="monthlyIncome">Monthly Income (USD):</label>
            <input type="number" step="0.01" id="monthlyIncome" name="monthlyIncome" required>
        </p>
        <p>
            <label for="monthlyExpense">Monthly Expense (USD):</label>
            <input type="number" step="0.01" id="monthlyExpense" name="monthlyExpense" required>
        </p>
        <p>
            <input type="submit" value="Add Finance Record">
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
        <c:forEach items="${finances}" var="finance">
            <tr>
                <td><c:out value="${finance.financeID}" /></td>
                <td><c:out value="${finance.userID}" /></td>
                <td><c:out value="${finance.monthlyIncomeUSD}" /></td>
                <td><c:out value="${finance.monthlyExpenditureUSD}" /></td>
                <td>
                    <form action="finance" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="financeID" value="${finance.financeID}">
                        <label for="newIncome">New Monthly Income:</label>
                        <input type="number" step="0.01" name="monthlyIncome" required>
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <form action="finance" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="financeID" value="${finance.financeID}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
