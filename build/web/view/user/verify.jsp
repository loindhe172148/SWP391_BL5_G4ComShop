<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Verify Account</title>
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
                align-items: center;
            }
            .container label {
                margin-bottom: 10px;
                text-align: left;
                width: 100%;
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
            <h1>Verify Your Account</h1>

            <!-- Display any error messages from the servlet -->
            <c:if test="${not empty err}">
                <p class="error">${err}</p>
            </c:if>

            <!-- Verification form -->
            <form action="verify" method="post">
                <label for="passconfirm">Enter Passcode:</label>
                <input type="text" id="passconfirm" name="code" required><br>
                
                <!-- Hidden fields to pass data -->
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
        </div>
    </body>
</html>
