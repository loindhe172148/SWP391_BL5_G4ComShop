<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<%@page import="entity.Product"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Cart Contact</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
            }
            .table {
                margin-top: 20px;
            }
            .btn-primary {
                background-color: #4CAF50;
                border: none;
                color: white;
            }
            .btn-primary:hover {
                background-color: #45a049;
            }
            .btn-secondary {
                background-color: #6c757d;
                border: none;
                color: white;
            }
            .btn-secondary:hover {
                background-color: #5a6268;
            }
            .page-header {
                background: #4CAF50;
                color: white;
                padding: 50px 0;
            }
            .breadcrumb a {
                color: white;
            }
            .breadcrumb a:hover {
                text-decoration: underline;
            }
            .quantity-buttons {
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .quantity-buttons form {
                display: inline-block;
            }
            .sidebar {
                padding: 20px;
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 5px;
                margin-top: 20px;
            }
            .sidebar .list-group-item {
                border: none;
            }
            .sidebar h4 {
                margin-bottom: 20px;
            }
            .sidebar .list-group-item:hover {
                background-color: #f1f1f1;
            }
            .form-label {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <!-- Navbar Start -->
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <h2 class="m-0 text-primary"><i class="fa fa-laptop me-3"></i>CompStore</h2>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <a href="home" class="nav-item nav-link">Home</a>
                    <a href="about" class="nav-item nav-link">About Us</a>
                    <a href="products" class="nav-item nav-link">Products</a>
                    <a href="ViewCart" class="nav-item nav-link active">My Cart</a>
                    <a href="MyOrders" class="nav-item nav-link">My Orders</a>
                    <%
                        User loggedInUser = (User) request.getAttribute("user");
                        if (loggedInUser != null) {
                    %>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Hello, <%= loggedInUser.getAccount().getUsername() %></a>
                        <div class="dropdown-menu border-0 shadow">
                            <a href="ChangePassword.jsp" class="dropdown-item">Change Password</a>
                            <div class="dropdown-divider"></div>
                            <a href="Logout" class="dropdown-item">Logout</a>
                        </div>
                    </div>
                    <%
                        } else {
                    %>
                    <a href="login" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
                        <%
                            }
                        %>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <!-- Header Start -->
        <div class="container-fluid page-header text-center">
            <div class="container">
                <h1 class="display-3">Cart Contact</h1>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb justify-content-center">
                        <li class="breadcrumb-item"><a class="text-white" href="home.jsp">Home</a></li>
                        <li class="breadcrumb-item"><a class="text-white" href="ViewCart">My Cart</a></li>
                        <li class="breadcrumb-item text-white active" aria-current="page">Cart Contact</li>
                    </ol>
                </nav>
            </div>
        </div>
        <!-- Header End -->

        <div class="container">
            <div class="row">
                <!-- Main Content -->
                <div class="col-lg-8">
                    <%
                        Map<Integer, Map<Product, Double>> cartItems = (Map<Integer, Map<Product, Double>>) request.getAttribute("cartItems");
                        double totalPrice = 0.0;

                        if (cartItems == null || cartItems.isEmpty()) {
                    %>
                    <div class="alert alert-warning text-center">
                        Your cart is empty.
                    </div>
                    <%
                        } else {
                    %>
                    <form action="SubmitOrder" method="post">
                        <table class="table table-bordered text-center">
                            <thead>
                                <tr>
                                    <th>Item</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Map.Entry<Integer, Map<Product, Double>> entry : cartItems.entrySet()) {
                                        Map<Product, Double> productWithPrice = entry.getValue();
                                        for (Map.Entry<Product, Double> productEntry : productWithPrice.entrySet()) {
                                            Product product = productEntry.getKey();
                                            double price = productEntry.getValue();
                                            int quantityInCart = product.getQuantity();
                                            double productTotal = price * quantityInCart;
                                            totalPrice += productTotal;
                                %>
                                <tr>
                                    <td><%= product.getName() %></td>
                                    <td>$ <%= String.format("%.2f", price) %></td>
                                    <td><%= quantityInCart %></td>
                                    <td>$ <%= String.format("%.2f", productTotal) %></td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                                <tr>
                                    <td colspan="3" class="text-end"><strong>Total Price:</strong></td>
                                    <td>$ <%= String.format("%.2f", totalPrice) %></td>
                                </tr>
                            </tbody>
                        </table>

                        <!-- Receiver Information -->
                        <div class="mb-4">
                            <h4>Receiver Information</h4>
                            <div class="mb-3">
                                <label for="fullname" class="form-label">Full Name</label>
                                <input type="text" class="form-control" id="fullname" name="fullname" value="<%= loggedInUser != null ? loggedInUser.getName() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="gender" class="form-label">Gender</label>
                                <select class="form-select" id="gender" name="gender" required>
                                    <option value="Male" <%= loggedInUser != null && loggedInUser.getGender() == 1 ? "selected" : "" %>>Male</option>
                                    <option value="Female" <%= loggedInUser != null && loggedInUser.getGender() == 0 ? "selected" : "" %>>Female</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" value="<%= loggedInUser != null ? loggedInUser.getGmail() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="mobile" class="form-label">Mobile</label>
                                <input type="text" class="form-control" id="mobile" name="mobile" value="<%= loggedInUser != null ? loggedInUser.getPhone() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="address" class="form-label">Address</label>
                                <input type="text" class="form-control" id="address" name="address" value="<%= loggedInUser != null ? loggedInUser.getAddress() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="notes" class="form-label">Notes</label>
                                <textarea class="form-control" id="notes" name="notes" rows="3"></textarea>
                            </div>
                        </div>

                        <!-- Action Buttons -->
                        <div class="d-flex justify-content-between">
                            <a href="ViewCart" class="btn btn-secondary">Change</a>
                            <button type="submit" class="btn btn-primary">Submit Order</button>
                        </div>
                    </form>
                    <%
                        }
                    %>
                </div>

                <!-- Sidebar Content -->

            </div>
        </div>

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light footer pt-5 mt-5">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-white mb-3">Quick Links</h4>
                        <a class="btn btn-link" href="about">About Us</a>
                        <a class="btn btn-link" href="contact">Contact Us</a>
                        <a class="btn btn-link" href="privacy-policy">Privacy Policy</a>
                        <a class="btn btn-link" href="terms-conditions">Terms & Conditions</a>
                        <a class="btn btn-link" href="faq">FAQs & Help</a>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-white mb-3">Contact</h4>
                        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Tech Street, Silicon Valley, USA</p>
                        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                        <p class="mb-2"><i class="fa fa-envelope me-3"></i>support@compstore.com</p>
                        <div class="d-flex pt-2">
                            <a class="btn btn-outline-light btn-social" href="#"><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-outline-light btn-social" href="#"><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-outline-light btn-social" href="#"><i class="fab fa-youtube"></i></a>
                            <a class="btn btn-outline-light btn-social" href="#"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-white mb-3">Our Products</h4>
                        <div class="row g-2 pt-2">
                            <div class="col-4">
                                <img class="img-fluid bg-light p-1" src="img/product-1.jpg" alt="Product 1">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid bg-light p-1" src="img/product-2.jpg" alt="Product 2">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid bg-light p-1" src="img/product-3.jpg" alt="Product 3">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid bg-light p-1" src="img/product-4.jpg" alt="Product 4">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid bg-light p-1" src="img/product-5.jpg" alt="Product 5">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid bg-light p-1" src="img/product-6.jpg" alt="Product 6">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-white mb-3">Newsletter</h4>
                        <p>Stay updated with the latest products and offers.</p>
                        <div class="position-relative mx-auto" style="max-width: 400px;">
                            <input class="form-control border-0 w-100 py-3 ps-4 pe-5" type="text" placeholder="Your email">
                            <button type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">SignUp</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
    </body>
</html>
