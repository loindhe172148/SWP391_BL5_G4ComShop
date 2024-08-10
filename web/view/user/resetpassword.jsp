<%-- 
    Document   : resetpassword
    Created on : Aug 10, 2024, 10:31:38 PM
    Author     : HP
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <style>
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
    <h1>Reset Password</h1>

    <!-- Display any error or success messages from the servlet -->
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${not empty reset_success}">
        <p class="success">${reset_success}</p>
    </c:if>

    <!-- Reset password form -->
    <form action="reset-password" method="post">
        <label for="pass">New Password:</label>
        <input type="password" id="pass" name="pass" required><br><br>

        <input type="submit" value="Reset Password">
    </form>
</body>
</html>

