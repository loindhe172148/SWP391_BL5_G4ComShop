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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    </head>
    <body>
        
        <jsp:include page="include/header-bar.jsp" />

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
                                                    <img src="${pageContext.request.contextPath}/assets/electro/img/${o.image}" alt="${o.name}" width="300" height="200">
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
                    <a class="title" href="productHome">
                        <h3>All Laptops</h3>
                    </a>

                    <div id="aside" class="col-md-1">
                        <h4 style="margin-top: 15px">Brand</h4>
                        <form style="width: 100px;" action="fillterProduct" method="get">
                            <c:forEach items="${listB}" var="o"> 
                                <a href="fillterProduct?idBrandName=${o.idBrandName}">${o.brandName}</a></br>
                            </c:forEach>
                        </form>
                    </div>
                    <div class="col-md-11">
                        <div class="col-md-6">
                            <div class="header-search">
                                <form action="searchProduct" method="get">
                                    <input class="input input-select" name="search" placeholder="Search here" value="${param.search}">
                                    <button class="search-btn" type="submit">Search</button>
                                </form>

                            </div>
                        </div>
                        <div id="store" class="col-md-12">
                            <div class="row">
                                <!-- Displaying the list of products -->
                                <c:forEach var="product" items="${productList}">
                                    <div class="col-md-4 col-xs-6">
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="${pageContext.request.contextPath}/assets/electro/img/${product.image}" alt="${product.name}" width="300" height="200">
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category">${product.title}</p>
                                                <h3 class="product-name"><a href="${pageContext.request.contextPath}/productdetails?id=${product.productdetailID}">${product.name}</a></h3>
                                                <h4 class="product-price">${product.salePrice} <del class="product-old-price">${product.originPrice}</del></h4>
                                                <div class="product-rating">
                                                    <!-- Rating stars can go here -->
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div id="store" class="col-md-12">
                                    <div class="row">
                                        <!-- Pagination controls -->
                                        <div class="store-pagination">
                                            <c:if test="${currentPage > 1}">
                                                <li class="page-item">
                                                    <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                                                        <span aria-hidden="true">«</span>

                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:forEach var="i" begin="1" end="${totalPages}" varStatus="status">
                                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                    <a class="page-link" href="?page=${i}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${currentPage < totalPages}">
                                                <li class="page-item">
                                                    <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                                                        <span aria-hidden="true">»</span>

                                                    </a>
                                                </li>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>


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
                                                        <img src="${pageContext.request.contextPath}/assets/electro/img/${o.image}" alt="${o.name} width="300" height="200"">
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
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
        .dropdown-menu {
            width: 120px;
            background: #1a1818;
        }
        .dropdown-menu li{
            width: 160px;
        }
        .dropdown-menu li a {
            padding: 10px 20px;
            display: block;

        }
        .dropdown-menu li a:hover {
            background-color: #f8f9fa;
            color: #343a40;

        }
        .custom-popup {
            border-radius: 15px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            background: #f9f9f9;
        }
        .custom-title {
            font-size: 1.8rem;
            font-weight: 600;
            color: #333;
        }
        .custom-content {
            font-size: 1rem;
            color: #555;
            line-height: 1.5;
        }
    </style>
    <script>
        function openModal(modalId) {
            var modals = ['loginModal', 'signupModal', 'resetPasswordModal'];
            modals.forEach(function (id) {
                var modal = document.getElementById(id);
                if (modalId !== id) {
                    modal.style.display = "none";
                }
            });
            document.getElementById(modalId).style.display = "block";
        }

        function closeModal(modalId) {
            document.getElementById(modalId).style.display = "none";
        }

        window.onclick = function (event) {
            var modals = ['loginModal', 'signupModal', 'resetPasswordModal'];
            modals.forEach(function (modalId) {
                var modal = document.getElementById(modalId);
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            });
        };
        window.onload = function () {
            showLoginModalWithError();
            showSignupModalWithError();
            showVerifyModalWithError();
            showResetModalWithError();
        };
        function showLoginModalWithError() {
            var errorLogin = '${errorLogin}';
            if (errorLogin) {
                document.getElementById('loginModal').style.display = 'block';
            }
        }
        function showVerifyModalWithError() {
            var code = '${code}';
            var errorVerify = '${errorVerify}';
            if (code || errorVerify) {
                document.getElementById('verificationModal').style.display = 'block';
            }
        }
        function showSignupModalWithError() {
            var errorSignup = '${errorSignup}';
            if (errorSignup) {
                document.getElementById('signupModal').style.display = 'block';
            }
        }
        function showResetModalWithError() {
            var errorReset = '${errorReset}';
            if (errorReset) {
                document.getElementById('resetPasswordModal').style.display = 'block';
            }
        }
        function showSignupSuccessAlert() {
            Swal.fire({
                icon: 'success',
                title: 'Verification Successful!',
                showConfirmButton: false,
                timer: 2000,
                customClass: {
                    popup: 'custom-popup',
                    title: 'custom-title',
                    content: 'custom-content'
                },
                width: '300px',
                padding: '1rem',
                backdrop: true,
                timerProgressBar: true
            });
        }
        function showResetSuccessAlert() {
            Swal.fire({
                icon: 'success',
                title: 'Reset Password Successful!',
                showConfirmButton: false,
                timer: 2000,
                customClass: {
                    popup: 'custom-popup',
                    title: 'custom-title',
                    content: 'custom-content'
                },
                width: '300px',
                padding: '1rem',
                backdrop: true,
                timerProgressBar: true
            });
        }
    </script>
</html>