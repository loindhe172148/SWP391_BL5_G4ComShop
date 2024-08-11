<%-- 
    Document   : verify
    Created on : Aug 9, 2024, 9:12:35 AM
    Author     : HP
--%>

<<<<<<< HEAD
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
=======
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
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
</html>

>>>>>>> d92e1a09e77352a74e3efffea7cd9d62b35e978c
