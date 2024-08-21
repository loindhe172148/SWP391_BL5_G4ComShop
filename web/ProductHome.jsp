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
    </head>
    <body>
        <div id="loginModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal('loginModal')">&times;</span>
                <form action="/SWP391_BL5_G4ComShop/login" method="post">
                    <c:if test="${not empty errorLogin}">
                        <div class="error-message">${errorLogin}</div>
                    </c:if>

                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" value="${username}" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" value="${password}" required>
                    </div>

                    <div class="form-group" style="flex-direction: row; align-items: center;">
                        <input  type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe == 'on' ? 'checked' : ''}>
                        <label style="margin:4px 0 0 0" for="rememberMe">Remember me</label>
                    </div>

                    <div style="display: flex;">
                        <button type="button" onclick="location.href = '/SWP391_BL5_G4ComShop/resetpass'" style="margin:0px 10px 10px 40px; background-color: #4CAF50;
        color: white;
        margin-top: 10px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        border: none;
        width: 180px;">Reset Password</button>
        <input style="margin: 10px" type="submit" value="Login">
                    </div>
                </form>
            </div>
        </div>
        <div id="signupModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal('signupModal')">&times;</span>
                <form action="signup" method="post">
                    <c:if test="${not empty errorSignup}">
                        <div class="error-message">${errorSignup}</div>
                    </c:if>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" value="${username}" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="pass" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmpassword">Confirm Password</label>
                        <input type="password" id="confirmpassword" name="confirmpassword" required>
                    </div>
                    <div class="form-group">
                        <label for="name">Full Name</label>
                        <input type="text" id="name" name="name" value="${fullname}" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" value="${email}" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="tel" id="phone" name="phone" value="${phone}" required>
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" id="address" name="add" value="${address}" required>
                    </div>
                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <input type="date" id="dob" name="dob" value="${dob}" required>
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender:</label>
                        <select id="gender" name="gender" required>
                            <option value="1" <c:if test="${gender == 1}">selected</c:if>>Male</option>
                            <option value="0" <c:if test="${gender == 0}">selected</c:if>>Female</option>
                            <option value="2" <c:if test="${gender == 2}">selected</c:if>>Other</option>
                            </select>
                        </div>
                        <input type="hidden" name="role" value="user"/>
                        <input type="submit" value="Sign Up">
                    </form>
                </div>
            </div>
            <header>
                <div style="background-color: #1a1818;" id="top-header">
                    <div class="container">
                        <div class="header-logo">
                            <img src="${pageContext.request.contextPath}/assets/electro/img/Screenshot 2024-08-18 035922.png" alt=""/>
                    </div>
                    <ul style="margin-top: 10px" class="header-links pull-right">
                        <li><a style="font-size: 20px" href="#" onclick="openModal('loginModal')"><i class="fa fa-sign-in"></i> Login</a></li>
                        <li><a style="font-size: 20px" href="#" onclick="openModal('signupModal')"><i class="fa fa-user-plus"></i> Sign up</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
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
        <div id="product-sale" class="section">
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
<style>
    .modal {
        display: none;
        position: fixed;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
    }

    .modal-content {
        background-color: #fefefe;
        margin: 5% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 90%;
        max-width: 400px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.5);
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }

    .modal-content form {
        display: flex;
        flex-direction: column;
    }

    .form-group {
        display: flex;
        flex-direction: column;
        margin: 0;
    }

    .form-group label {
        margin-bottom: 5px;
        font-weight: bold;
    }

    .modal-content input[type="text"],
    .modal-content input[type="password"],
    .modal-content input[type="email"],
    .modal-content input[type="tel"],
    .modal-content input[type="date"],
    .modal-content select {
        padding: 3px;
        margin-bottom: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
        width: 100%;
        box-sizing: border-box;
    }

    .modal-content input[type="checkbox"] {
        width: auto;
        margin-right: 10px;
    }

    .modal-content input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        margin-top: 10px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        border: none;
        width: 80px;
        margin-left: 40%;
    }

    .modal-content input[type="submit"]:hover {
        background-color: #45a049;
    }

    .error-message {
        color: red;
        font-weight: bold;
        margin-bottom: 15px;
    }
</style>
<script>
                            function openModal(modalId) {
                                document.getElementById(modalId).style.display = "block";
                            }

                            function closeModal(modalId) {
                                document.getElementById(modalId).style.display = "none";
                            }

                            window.onclick = function (event) {
                                var modals = ['loginModal', 'signupModal'];
                                modals.forEach(function (modalId) {
                                    var modal = document.getElementById(modalId);
                                    if (event.target === modal) {
                                        modal.style.display = "none";
                                    }
                                });
                            };
                            window.onload = function () {
                                showLoginModalWithError();
                            };
                            function showLoginModalWithError() {
                                var errorLogin = '${errorLogin}';
                                if (errorLogin) {
                                    document.getElementById('loginModal').style.display = 'block';
                                }
                            }
</script>
</html>