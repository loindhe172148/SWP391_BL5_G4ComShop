<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<%@page import="entity.Account"%>
<%@page import="entity.Product"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>My Cart</title>
        <!-- CSS Libraries -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
        <style>
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
            .product-img {
                width: 50px;
                height: 50px;
                object-fit: cover;
            }
            h1{
                margin-top: 20px;
                text-align: center;
            }
            .row{
                display: flex;
                justify-content: center;
            }
        </style>
    </head>
    <body>
        <%
            User loggedInUser = (User) request.getAttribute("user");
        %>    
                <!-- HEADER -->
        <jsp:include page="../../include/header-bar.jsp" />
        <!-- /HEADER -->


        <!-- NAVIGATION -->
        <nav id="navigation">
            <!-- container -->
            <div class="container">
                <!-- responsive-nav -->
                <div id="responsive-nav">
                    <h1 style="margin-top: 10px; text-align: center;">Your cart contact</h1>
                </div>
                <!-- /responsive-nav -->
            </div>
            <!-- /container -->
        </nav>
        <!-- /NAVIGATION -->

        <!-- BREADCRUMB -->
        <div id="breadcrumb" class="section">
            <!-- container -->

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
                                        <td>$ <%= String.format("%.1f", price) %></td>
                                        <td><%= quantityInCart %></td>
                                        <td>$ <%= String.format("%.1f", productTotal) %></td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                    <tr>
                                        <td colspan="3" class="text-end"><strong>Total Price:</strong></td>
                                        <td>$ <%= String.format("%.1f", totalPrice) %></td>
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
                            <div style="margin-top: 20px" class="d-flex justify-content-between">
                                <a style="margin-right: 20px" href="CartController" class="btn btn-primary">Change</a>
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
            <!-- /container -->
        </div>
        <!-- /BREADCRUMB -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->
        <footer id="footer">
            <div id="bottom-footer" class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <ul style="margin-left: 350px" class="header-links pull-left">
                                <li><a href="#"><i class="fa fa-phone"></i> 0333222454</a></li>
                                <li><a href="#"><i class="fa fa-envelope-o"></i>G4COMShop@gmail.com</a></li>
                                <li><a href="#"><i class="fa fa-map-marker"></i>14,ThaiThinh,Ha Noi</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </footer>

        <!-- jQuery Plugins -->
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/nouislider.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/main.js"></script>

    </body>
</html>
