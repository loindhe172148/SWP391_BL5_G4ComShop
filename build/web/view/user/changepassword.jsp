<%-- 
    Document   : changepassword
    Created on : Aug 8, 2024, 10:17:44 AM
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
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <style>
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
    <h1>Change Password</h1>
    
    <!-- Display any messages from the servlet -->
    <c:if test="${not empty err}">
        <p class="error">${err}</p>
    </c:if>
    <c:if test="${not empty success}">
        <p class="success">${success}</p>
    </c:if>

    <!-- Change password form -->
    <form action="ChangePasswordController" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="old-password">Old Password:</label>
        <input type="password" id="old-password" name="old-password" required><br><br>

        <label for="pass">New Password:</label>
        <input type="password" id="pass" name="pass" required><br><br>

        <input type="submit" value="Change Password">
    </form>
</body>
>>>>>>> d92e1a09e77352a74e3efffea7cd9d62b35e978c
</html>
