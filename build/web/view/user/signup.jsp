<%-- 
    Document   : signup
    Created on : Aug 9, 2024, 9:11:47 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<<<<<< HEAD
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
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
        .signup-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .signup-container h2 {
            margin-bottom: 20px;
        }
        .signup-container input[type="text"],
        .signup-container input[type="email"],
        .signup-container input[type="password"],
        .signup-container input[type="tel"],
        .signup-container input[type="date"],
        .signup-container select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .signup-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .signup-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
        .success-message {
            color: green;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="signup-container">
    <h2>Sign Up</h2>
    <form action="signup" method="post">
        <c:if test="${not empty err}">
            <div class="error-message">${err}</div>
        </c:if>
        
        <label for="name">Full Name:</label>
        <input type="text" id="name" name="name" value="${name}" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${email}" required>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${username}" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="pass" required>

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" value="${phone}" required>

        <label for="address">Address:</label>
        <input type="text" id="address" name="add" value="${address}" required>

        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" value="${dob}" required>

        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="Male" ${gender == 'Male' ? 'selected' : ''}>Male</option>
            <option value="Female" ${gender == 'Female' ? 'selected' : ''}>Female</option>
        </select>

        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="User" ${role == 'User' ? 'selected' : ''}>User</option>
            <option value="Admin" ${role == 'Admin' ? 'selected' : ''}>Admin</option>
        </select>

        <input type="submit" value="Sign Up">
    </form>
</div>
</body>
</html>

>>>>>>> d92e1a09e77352a74e3efffea7cd9d62b35e978c
