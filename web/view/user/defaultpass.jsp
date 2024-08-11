<%-- 
    Document   : defaultpass
    Created on : Aug 9, 2024, 9:08:32 AM
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
=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verify Default Password</title>
    <style>
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Verify Default Password</h1>
    
    <!-- Display any error messages from the servlet -->
    <c:if test="${not empty err}">
        <p class="error">${err}</p>
    </c:if>

    <!-- Default password verification form -->
    <form action="DefaultPassController" method="post">
        <label for="de-pass">Enter Default Password:</label>
        <input type="password" id="de-pass" name="de-pass" required><br><br>
        <input type="submit" value="Verify">
    </form>
</body>
>>>>>>> d92e1a09e77352a74e3efffea7cd9d62b35e978c
</html>
