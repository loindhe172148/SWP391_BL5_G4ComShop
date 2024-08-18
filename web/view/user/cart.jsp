<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Product"%>
<%@page import="java.util.Map"%>
<%@page import="entity.Account"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>My Cart</title>
        <!-- CSS Libraries -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <style>
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
            .btn-danger {
                background-color: #dc3545;
                border: none;
                color: white;
            }
            .btn-danger:hover {
                background-color: #c82333;
            }
            .page-header {
                background: #4CAF50;
            }
            h1, th, td {
                color: #333;
            }
            th {
                background-color: #f2f2f2;
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
                    <a href="accessories" class="nav-item nav-link">Accessories</a>
                    <a href="ViewCart" class="nav-item nav-link active">My Cart</a>
                    <a href="MyOrders" class="nav-item nav-link">My Orders</a>
                    <%
                        Account loggedInUser = (Account) session.getAttribute("acc");
                        if (loggedInUser != null) {
                    %>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Hello, <%= loggedInUser.getUsername() %></a>
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
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">My Cart</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center">
                                <li class="breadcrumb-item"><a class="text-white" href="home.jsp">Home</a></li>
                                <li class="breadcrumb-item text-white active" aria-current="page">My Cart</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End -->

        <!-- Cart Start -->
        <div class="container">
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
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th>Item</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Map.Entry<Integer, Map<Product, Double>> entry : cartItems.entrySet()) {
                            int productDetailId = entry.getKey();
                            for (Map.Entry<Product, Double> productEntry : entry.getValue().entrySet()) {
                                Product product = productEntry.getKey();
                                double salePrice = productEntry.getValue();
                                int quantityInCart = product.getQuantity();
                                double productTotal = salePrice * quantityInCart;
                                totalPrice += productTotal;
                    %>
                    <tr>
                        <td><%= product.getName() %></td>
                        <td>$ <%= String.format("%.2f", salePrice) %></td>
                        <td>
                            <div class="quantity-buttons">
                                <form action="CartQuantityController" method="post">
                                    <input type="hidden" name="itemId" value="<%= productDetailId %>">
                                    <input type="hidden" name="itemType" value="productDetail">
                                    <input type="hidden" name="action" value="decrease">
                                    <button type="submit" class="btn btn-sm btn-danger">-</button>
                                </form>
                                <span><%= quantityInCart %></span>
                                <form action="CartQuantityController" method="post">
                                    <input type="hidden" name="itemId" value="<%= productDetailId %>">
                                    <input type="hidden" name="itemType" value="productDetail">
                                    <input type="hidden" name="action" value="increase">
                                    <button type="submit" class="btn btn-sm btn-success">+</button>
                                </form>
                            </div>
                        </td>
                        <td>$ <%= String.format("%.2f", productTotal) %></td>
                        <td>
                            <form action="DeleteFromCart" method="post" style="display:inline;">
                                <input type="hidden" name="itemId" value="<%= productDetailId %>">
                                <input type="hidden" name="itemType" value="productDetail">
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    <tr>
                        <td colspan="3" class="text-end"><strong>Total Price:</strong></td>
                        <td colspan="2">$ <%= String.format("%.2f", totalPrice) %></td>
                    </tr>
                </tbody>
            </table>
            <div class="text-end">
                <form action="CartContactController" method="get">
                    <button type="submit" class="btn btn-primary">Proceed to Cart Contact</button>
                </form>
            </div>
            <%
                }
            %>
        </div>
        <!-- Cart End -->

        <!-- Footer Start -->
        <!-- Include your footer here -->
        <!-- Footer End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    </body>
</html>
