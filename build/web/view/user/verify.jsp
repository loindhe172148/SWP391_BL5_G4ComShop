<%-- 
    Document   : verify
    Created on : Aug 9, 2024, 9:12:35 AM
    Author     : HP
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<<<<<<< HEAD
<head>
    <meta charset="UTF-8">
    <title>Verify Account</title>
    <style>
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Verify Your Account</h1>

    <!-- Display any error messages from the servlet -->
    <c:if test="${not empty err}">
        <p class="error">${err}</p>
    </c:if>

    <!-- Verification form -->
    <form action="verify" method="post">
        <label for="passconfirm">Enter Passcode:</label>
        <input type="text" id="passconfirm" name="passconfirm" required><br><br>

        <input type="submit" value="Verify">
    </form>
</body>
=======
    <head>
        <meta charset="UTF-8">
        <title>Verify Account</title>
        <style>
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Verify Your Account</h1>

        <!-- Display any error messages from the servlet -->
        <c:if test="${not empty err}">
            <p class="error">${err}</p>
        </c:if>

        <!-- Verification form -->
        <form action="verify" method="post">
            <label for="passconfirm">Enter Passcode:</label>
            <input type="text" id="passconfirm" name="code" required><br><br>
            <input type="hidden" name="email" value="${email}">
            <input type="hidden" name="username" value="${username}">
            <input type="hidden" name="password" value="${password}">
            <input type="hidden" name="role" value="${role}">
            <input type="hidden" name="phone" value="${phone}">
            <input type="hidden" name="address" value="${address}">
            <input type="hidden" name="dob" value="${dob}">
            <input type="hidden" name="gender" value="${gender}">
            <input type="hidden" name="fullname" value="${fullname}">
            <input type="submit" value="Verify">
        </form>
    </body>
>>>>>>> main
</html>
