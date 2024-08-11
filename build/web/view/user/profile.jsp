<%-- 
    Document   : profile
    Created on : Aug 9, 2024, 9:11:07 AM
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
    <meta charset="UTF-8">
    <title>Profile</title>
    <style>
        .error { color: red; }
        .avatar { width: 100px; height: 100px; object-fit: cover; }
    </style>
</head>
<body>
    <h1>Profile</h1>

    <!-- Display any error messages from the servlet -->
    <c:if test="${not empty err}">
        <p class="error">${err}</p>
    </c:if>

    <!-- Display user profile form -->
    <form action="ProfileController" method="post" enctype="multipart/form-data">
        <!-- Username -->
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user.username}" required><br><br>

        <!-- Full Name -->
        <label for="name">Full Name:</label>
        <input type="text" id="name" name="name" value="${user.name}" required><br><br>

        <!-- Gender -->
        <label for="gender">Gender:</label>
        <select id="gender" name="gender">
            <option value="Male" ${user.gender ? 'selected' : ''}>Male</option>
            <option value="Female" ${!user.gender ? 'selected' : ''}>Female</option>
        </select><br><br>

        <!-- Date of Birth -->
        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" value="${user.dob}"><br><br>

        <!-- Address -->
        <label for="add">Address:</label>
        <textarea id="add" name="add">${user.address}</textarea><br><br>

        <!-- Avatar -->
        <label for="avatar">Profile Picture:</label>
        <input type="file" id="avatar" name="avatar" accept="image/jpeg, image/png"><br><br>

        <!-- Display current avatar if available -->
        <c:if test="${not empty user.ava}">
            <img src="${pageContext.request.contextPath}/resources/uploads/${user.ava}" class="avatar" alt="Avatar">
            <input type="hidden" name="image-initiate" value="${user.ava}">
        </c:if>

        <!-- Submit Button -->
        <input type="submit" value="Update Profile">
    </form>
</body>
</html>
>>>>>>> d92e1a09e77352a74e3efffea7cd9d62b35e978c
