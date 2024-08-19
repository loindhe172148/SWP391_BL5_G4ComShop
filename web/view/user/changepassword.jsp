<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .container h1 {
            margin-bottom: 20px;
        }
        .container form {
            display: flex;
            flex-direction: column;
        }
        .container label {
            margin-bottom: 5px;
            text-align: left;
        }
        .container input[type="text"],
        .container input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
        }
        .container input[type="submit"] {
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        .container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Change Password</h1>
        
        <!-- Display any messages from the servlet -->
        <c:if test="${not empty err}">
            <p class="error">${err}</p>
        </c:if>

        <!-- Change password form -->
        <form action="changepass" method="post">
            <label for="oldpass">Old Password</label>
            <input type="password" id="oldpass" name="oldpass" required>

            <label for="newpass">New Password</label>
            <input type="password" id="newpass" name="newpass" required>

            <label for="confirmpass">Confirm Password</label>
            <input type="password" id="confirmpass" name="confirmpass" required>

            <input style="width: 130px; margin-left: 140px" type="submit" value="Change Password">
        </form>
    </div>
</body>
</html>