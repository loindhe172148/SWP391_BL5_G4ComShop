<%-- 
    Document   : forgotpassword
    Created on : Aug 9, 2024, 9:09:49 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <style>
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Forgot Password</h1>

    <!-- Display any error messages from the servlet -->
    <c:if test="${not empty err}">
        <p class="error">${err}</p>
    </c:if>

    <!-- Forgot password request form -->
    <form action="forgot" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <input type="submit" value="Request Default Password">
    </form>
</body>
</html>
