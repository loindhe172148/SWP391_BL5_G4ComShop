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
                margin-right: 5px;
            }
            .btn-success{
                margin-left: 5px;
                width: 24.5px;
                height: 28px;
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
            #breadcrumb{
                height: 440px;
            }
        </style>
    </head>
    <body>
        <!-- HEADER -->
        <jsp:include page="../../include/header-bar.jsp" />
        <!-- /HEADER -->

        <!-- NAVIGATION -->
        <nav id="navigation">
            <!-- container -->
            <div class="container">
                <!-- responsive-nav -->
                <div id="responsive-nav">
                    <h1 style="margin-top: 10px; text-align: center;">Your Shopping Cart</h1>
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
                            <th>Image</th>
                            <th>Item</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th style="width: 160px">Total</th>
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
                            <td><img src="${pageContext.request.contextPath}/assets/electro/img/<%= product.getImage() %>" class="product-img" alt="Product Image"></td>
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
                            <td colspan="4" class="text-end"><strong>Total Price:</strong></td>
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
