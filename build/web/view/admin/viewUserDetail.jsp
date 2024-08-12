<%@ page import="entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center text-primary">User Details</h2>
        <div class="card mt-4">
            <div class="card-body">
                <%
                    User user = (User) request.getAttribute("user");
                    if (user != null) {
                %>
                <p><strong>ID:</strong> <%= user.getId() %></p>
                <p><strong>Name:</strong> <%= user.getName() %></p>
                <p><strong>Email:</strong> <%= user.getGmail() %></p>
                <p><strong>Address:</strong> <%= user.getAddress() %></p>
                <p><strong>Gender:</strong> <%= user.isGender() ? "Male" : "Female" %></p>
                <p><strong>Phone:</strong> <%= user.getPhone() %></p>
                <p><strong>Date of Birth:</strong> <%= user.getBod() %></p>
                <p><strong>Status:</strong> <%= user.isStatus() ? "Active" : "Inactive" %></p>
              
                <% } else { %>
                <p>User not found.</p>
                <% } %>
            </div>
        </div>
        <a href="adminUserList" class="btn btn-secondary mt-3">Back to User List</a>
    </div>
</body>
</html>
