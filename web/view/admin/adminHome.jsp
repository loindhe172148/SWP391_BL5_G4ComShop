<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>User List</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <!-- Customized Bootstrap Stylesheet -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <style>
        body {
            margin: 20px;
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-size: 1.25rem;
            font-weight: 600;
        }
        .user-avatar {
            border-radius: 50%;
            border: 2px solid #007bff;
        }
        .table-hover tbody tr:hover {
            background-color: #f8f9fa;
        }
        .table thead {
            background-color: #007bff;
            color: white;
        }
        .table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container-fluid content">
        <div class="row flex-nowrap">
            <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                    <a href="adminDashboard" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                        <span class="fs-5 d-none d-sm-inline">Menu</span>
                    </a>
                    <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                      
                       
                        <li>
                            <a href="CustomerList" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Customer Manage</span>
                            </a>
                        </li>
                       
                        <li>
                            <a href="DoanhThuThang.jsp" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-calendar"></i> <span class="ms-1 d-none d-sm-inline">Monthly Revenue</span>
                            </a>
                        </li>
                        <li>
                            <a href="Top5User" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Top 5 Customers</span>
                            </a>
                        </li>
                    </ul>
                    <hr>
                    <div class="dropdown pb-4">
                        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30" class="rounded-circle">
                            <span class="d-none d-sm-inline mx-1">Admin</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item" href="#">New project...</a></li>
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Sign out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col py-3">
                <!-- Main Content Start -->
                <div id="customerManage" class="mt-5">
                    <h2 class="text-center text-primary mb-4">User List</h2>
                    <p class="text-center mb-4">Manage all users here. Below is the list of registered users.</p>
                    <div class="card mb-3 shadow-sm">
                        <div class="card-header">
                            <i class="fas fa-users"></i> User List
                        </div>
                        <div class="card-body">
                            <table class="table table-hover table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Address</th>
                                        <th>Gender</th>
                                        <th>Phone</th>
                                        <th>Date of Birth</th>
                                        <th>Status</th>
                                        <th>Avatar</th>
                                         <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
                                        for (User user : userList) {
                                    %>
                                    <tr>
                                        <td><%= user.getId() %></td>
                                        <td><%= user.getName() %></td>
                                        <td><%= user.getGmail() %></td>
                                        <td><%= user.getAddress() %></td>
                                        <td><%= user.isGender() ? "Male" : "Female" %></td>
                                        <td><%= user.getPhone() %></td>
                                        <td><%= user.getBod() %></td>
                                        <td><%= user.isStatus() ? "Active" : "Inactive" %></td>
                                        <td>
                                            <% if (user.getAva() != null && !user.getAva().isEmpty()) { %>
                                                <img src="<%= user.getAva() %>" alt="Avatar" width="50" height="50" class="user-avatar">
                                            <% } else { %>
                                                <span>No Avatar</span>
                                            <% } %>
                                        </td>
                                         <td>
                                            <a href="viewUserDetail?userId=<%= user.getId() %>" class="btn btn-info btn-sm">View Details</a>
                                        </td>
                                    </tr>
                                    <% 
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- Main Content End -->
            </div>
        </div>
    </div>
    <!-- Sidebar End -->

    <!-- Footer Start -->
   
    <!-- Footer End -->
</body>
</html>
