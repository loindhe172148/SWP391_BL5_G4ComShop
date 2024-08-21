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
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

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
        <style>
            .dropdown-menu {
                min-width: 160px;
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
    </head>
    <body>
        <!-- HEADER -->
        <jsp:include page="include/header-bar.jsp" />
        <!-- /HEADER -->

        <!-- NAVIGATION -->
        <nav id="navigation">
            <!-- container -->
            <div class="container">
                <!-- responsive-nav -->
                <div id="responsive-nav">
                    <!-- NAV -->
                    <ul class="main-nav nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#new-product">New Laptops</a></li>

                        <li><a href="#product-list">Laptops</a></li>
                        <li><a href="#product-sale">Top Sale Laptops</a></li>
                    </ul>
                    <!-- /NAV -->
                </div>
                <!-- /responsive-nav -->
            </div>
            <!-- /container -->
        </nav>
        <!-- /NAVIGATION -->


        <!-- /SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 id="new-product" class="title">New Products</h3>
                            <div class="section-nav">
                                <ul class="section-tab-nav tab-nav">
                                    <li class="active"><a data-toggle="tab" href="#tab1">Laptops</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab1" class="tab-pane active">
                                    <div class="products-slick" data-nav="#slick-nav-1">
                                        <!-- product -->
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
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- /Products tab & slick -->

                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- SECTION -->
        <div id="product-list" class="section">
            <div class="container">
                <div class="row">
                    <div id="aside" class="col-md-3">
                        <form action="fillterProduct" method="get">
                            <label class="input-select" name="idBrandName" onchange="this.form.submit()">
                                <c:forEach items="${listB}" var="o"> 
                                    <option value="${o.idBrandName}"
                                            <c:if test="${o.idBrandName == param.idBrandName}">
                                                selected="selected"
                                            </c:if>
                                            >
                                        ${o.brandName}
                                    </option>
                                </c:forEach>
                            </label>
                        </form>
                        <div class="aside">
                            <h3 class="aside-title">Top selling</h3>
                            <c:forEach var="o" items="${topDiscountedProductsa}" >
                                <div class="product-widget">
                                    <div class="product-img">
                                        <img src="${o.image}" alt="${o.name}" width="125" height="100">
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
                        <!-- /aside Widget -->
                    </div>
                    <!-- /ASIDE -->
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
                    <div id="store" class="col-md-9">
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
            <div id ="product-sale"class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="section-title">
                                <h3 class="title">Top selling</h3>
                                <div class="section-nav">
                                    <ul class="section-tab-nav tab-nav">
                                        <li class="active"><a data-toggle="tab" href="#tab2">Laptops</a></li>

                                    </ul>
                                </div>
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

                                            <!-- /product -->


                                        </div>
                                        <div id="slick-nav-2" class="products-slick-nav"></div>
                                    </div>
                                    <!-- /tab -->
                                </div>
                            </div>
                        </div>
                        <!-- /Products tab & slick -->
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /SECTION -->



            <!-- NEWSLETTER -->
            <div id="newsletter" class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="newsletter">
                                <p>Sign Up for the <strong>NEWSLETTER</strong></p>
                                <form>
                                    <input class="input" type="email" placeholder="Enter Your Email">
                                    <button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
                                </form>
                                <ul class="newsletter-follow">
                                    <li>
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-instagram"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-pinterest"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /NEWSLETTER -->

            <!-- FOOTER -->
            <jsp:include page="include/footer-bar.jsp" />
            <!-- /FOOTER -->

            <!-- jQuery Plugins -->
            <script src="${pageContext.request.contextPath}/assets/electro/js/jquery.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/slick.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/nouislider.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/jquery.zoom.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/electro/js/main.js"></script>

    </body>
</html>


