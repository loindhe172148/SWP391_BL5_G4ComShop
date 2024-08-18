<%-- 
    Document   : login
    Created on : Aug 9, 2024, 9:10:39 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .login-container h2 {
            margin-bottom: 20px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container input[type="checkbox"] {
            margin-right: 5px;
        }
        .login-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="/SWP391_BL5_G4ComShop/login" method="post">
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${username}" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label>
            <input type="checkbox" name="rememberMe" ${rememberMe == 'on' ? 'checked' : ''}> Remember me
        </label>

        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
