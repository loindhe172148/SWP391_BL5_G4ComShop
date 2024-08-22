<%-- 
    Document   : ProductHome
    Created on : Aug 18, 2024, 10:52:29 AM
    Author     : LENOVO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>G4COMShop</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/electro/css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/electro/css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/electro/css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/electro/css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/electro/css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/electro/css/style.css"/>

    </head>
    <body>
        <header>
            <div style="background-color: #1a1818;" id="top-header">
                <div class="container">
                    <div class="header-logo">
                        <a href="#" class="logo">
                            <img src="${pageContext.request.contextPath}/assets/electro/img/Screenshot 2024-08-18 035922.png" alt="">
                        </a>
                    </div>
                    <ul style="margin-top: 10px"  class="header-links pull-right">
                        <li><a style="font-size: 20px" href="/SWP391_BL5_G4ComShop/logout"><i class="fa fa-sign-out"></i> Logout</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i style="font-size: 20px" class="fa fa-user-o"></i> My Account <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">View profile</a></li>
                                <li><a href="./user/">My Cart</a></li>
                                <li><a href="./user/changepass">Change Pass</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <style>
                .dropdown-menu {
                    min-width: 120px;
                    background: black;
                }

                .dropdown-menu li a {
                    padding: 10px 20px;
                    display: block;
                }

                .dropdown-menu li a:hover {
                    background-color: #f8f9fa;
                    color: #343a40;
                }
            </style>
            <nav id="navigation">
                <div style="display: flex; justify-content: center" class="container">
                    <div id="responsive-nav">
                        <ul class="main-nav nav navbar-nav">
                            <li class="active"><a href="/SWP391_BL5_G4ComShop">Home</a></li>
                            <li><a href="#new-product">New Laptops</a></li>
                            <li><a href="#product-list">Laptops</a></li>
                            <li><a href="#product-sale">Top Sellers</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="section">
                <div id ="new-product" class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="section-title">
                                <h3 class="title">New Laptops</h3>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="products-tabs">
                                    <div id="tab1" class="tab-pane active">
                                        <div class="products-slick" data-nav="#slick-nav-1">
                                            <c:forEach var="o" items="${productListnew}">
                                                <div class="product">
                                                    <div class="product-img">
                                                        <img src="${pageContext.request.contextPath}/${o.image}" alt="${o.name}" width="300" height="200">
                                                        <div class="product-label">
                                                            <span class="new">NEW</span>
                                                        </div>
                                                    </div>
                                                    <div class="product-body">
                                                        <p class="product-category">${o.title}</p>
                                                        <h3 class="product-name"><a href="productdetails?id=${o.productdetailID}">${o.name}</a></h3>
                                                        <h4 class="product-price">${o.salePrice}<del class="product-old-price">${o.originPrice}</del></h4>
                                                        <div class="product-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div id="slick-nav-1" class="products-slick-nav"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="product-list" class="section">
                <div class="container">
                    <div class="row">
                        <h3 class="title">All Laptops</h3>
                        <div id="aside" class="col-md-1">
                            <h4 style="margin-top: 15px">Brand</h4>
                            <form style="width: 100px;" action="fillterProduct" method="get">
                                <c:forEach items="${listB}" var="o"> 
                                    <a href="url">${o.brandName}</a></br>
                                </c:forEach>
                            </form>
                        </div>
                        <div class="col-md-11">
                            <div class="col-md-6">
                                <div class="header-search">
                                    <form>
                                        <select class="input-select">
                                            <option value="0">All Categories</option>
                                            <option value="1">Category 01</option>
                                            <option value="1">Category 02</option>
                                        </select>
                                        <input class="input" placeholder="Search here">
                                        <button class="search-btn">Search</button>
                                    </form>
                                </div>
                            </div>
                            <div id="store" class="col-md-12">
                                <div class="row">
                                    <c:forEach var="product" items="${productList}">
                                        <div class="col-md-4 col-xs-6">
                                            <div class="product">
                                                <div class="product-img">
                                                    <img src="${product.image}" alt="${product.name}" width="300" height="200">
                                                </div>
                                                <div class="product-body">
                                                    <p class="product-category">${product.title}</p>
                                                    <h3 class="product-name"><a href="productdetails?id=${o.productdetailID}">${product.name}</a></h3>
                                                    <h4 class="product-price">
                                                        ${product.salePrice} <del class="product-old-price">${product.originPrice}</del>
                                                    </h4>
                                                    <div class="product-rating">
                                                    </div>
                                                </div>
                                                <div class="add-to-cart">
                                                    <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id ="product-sale"class="section">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="section-title">
                                    <h3 class="title">Top Sellers</h3>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="products-tabs">
                                        <!-- tab -->
                                        <div id="tab2" class="tab-pane fade in active">
                                            <div class="products-slick" data-nav="#slick-nav-2">
                                                <c:forEach var="o" items="${topDiscountedProducts}">
                                                    <div class="product">
                                                        <div class="product-img">
                                                            <img src="${o.image}" alt="${o.name} width="300" height="200"">
                                                            <div class="product-label">
                                                                <span class="sale">
                                                                    - <fmt:formatNumber value="${(o.originPrice - o.salePrice) / o.originPrice * 100}" type="number" maxFractionDigits="0"/>%
                                                                </span>
                                                            </div>

                                                        </div>
                                                        <div class="product-body">
                                                            <p class="product-category">${o.title}</p>
                                                            <h3 class="product-name"><a href="productdetails?id=${o.productdetailID}">${o.name}</a></h3>
                                                            <h4 class="product-price">
                                                                ${o.salePrice} <del class="product-old-price">${o.originPrice}</del>
                                                            </h4>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div id="slick-nav-2" class="products-slick-nav"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
            <script src="${pageContext.request.contextPath}/assets/electro/js/jquery.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/slick.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/nouislider.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/jquery.zoom.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/main.js"></script>

    </body>
</html>