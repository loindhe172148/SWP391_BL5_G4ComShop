<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            width: 100%;
            max-width: 400px;
        }
        .signup-container h2 {
            text-align: center;
        }
        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 15px;
        }
        .form-group label {
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input,
        .form-group select {
            padding: 10px;
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
            text-align: center;
        }
        .success-message {
            color: green;
            margin-bottom: 10px;
            text-align: center;
        }
        @media (min-width: 768px) {
            .form-group {
                flex-direction: row;
                align-items: center;
            }
            .form-group label {
                width: 30%;
                margin-bottom: 0;
                text-align: right;
                padding-right: 10px;
            }
            .form-group input,
            .form-group select {
                width: 70%;
            }
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

        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="${username}" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="pass" required>
        </div>
        
        <div class="form-group">
            <label for="confirmpassword">Confirm Password:</label>
            <input type="password" id="confirmpassword" name="confirmpassword" required>
        </div>

        <div class="form-group">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" value="${fullname}" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${email}" required>
        </div>

        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" value="${phone}" required>
        </div>

        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="add" value="${address}" required>
        </div>

        <div class="form-group">
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="${dob}" required>
        </div>

        <div class="form-group">
            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="1" <c:if test="${gender == 1}">selected</c:if>>Male</option>
                <option value="0" <c:if test="${gender == 0}">selected</c:if>>Female</option>
                <option value="2" <c:if test="${gender == 2}">selected</c:if>>Other</option>
            </select>
        </div>

        <input type="hidden" name="role" value="user"/>
        <input style="width: 100px; margin-left: 150px" type="submit" value="Sign Up">
    </form>
</div>
</body>
</html>