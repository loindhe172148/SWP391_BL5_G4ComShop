<%-- 
    Document   : ProductDetails
    Created on : Aug 18, 2024, 2:38:46 AM
    Author     : xuant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Product Details</title>

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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
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
    </head>
    <body>
        <!-- HEADER -->
        <jsp:include page="include/header-bar.jsp" />
        <!-- /HEADER -->

        <!-- NAVIGATION -->
        <!-- /NAVIGATION -->

        <!-- BREADCRUMB -->
        <div id="breadcrumb" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <ul class="breadcrumb-tree">
                            <li><a href="productHome">Home</a></li>
                            <li><a href="productHome">Product</a></li>
                            <li class="active">${productWithDetails.product.name}</li>
                        </ul>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /BREADCRUMB -->

        <!-- SECTION -->
        <!-- Product Main Image Section -->
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-5 col-md-push-1">
                        <div id="product-main-img">
                            <div class="product-preview">
                                <img src="${pageContext.request.contextPath}/assets/electro/img/${productWithDetails.product.image}" 
                                     alt="${productWithDetails.product.name}">
                            </div>
                        </div>
                    </div>
                    <!-- Product thumb imgs -->
                    <div class="col-md-1 col-md-pull-5">
                        <!-- Placeholder for additional thumbnails -->
                        <!--
                        <div id="product-imgs">
                            <div class="product-preview">
                                <img src="${pageContext.request.contextPath}/assets/electro/img/product01.png" alt="">
                            </div>
                            <div class="product-preview">
                                <img src="${pageContext.request.contextPath}/assets/electro/img/product03.png" alt="">
                            </div>
                            <div class="product-preview">
                                <img src="${pageContext.request.contextPath}/assets/electro/img/product06.png" alt="">
                            </div>
                            <div class="product-preview">
                                <img src="${pageContext.request.contextPath}/assets/electro/img/product08.png" alt="">
                            </div>
                        </div>
                        -->
                    </div>
                    <!-- /Product thumb imgs -->
                    <!-- Product Details Section -->
                    <div class="col-md-6">
                        <div class="product-details">
                            <form action="addtocart" method="post">
                                <input type="hidden" name="productDetailId" value="${productWithDetails.productDetails.id}">
                                <input type="hidden" name="customerId" value="${customerId}">
                                <input type="hidden" name="price" value="${productWithDetails.productDetails.salePrice}">
                                <input type="hidden" name="totalquantity" value="${productWithDetails.productDetails.quantity}">

                                <div id="price" style="display: none">${productWithDetails.productDetails.salePrice}</div>

                                <h2 class="product-name">${productWithDetails.product.title}</h2>

                                <div>
                                    <h3 class="product-price">${productWithDetails.productDetails.salePrice}$ 
                                        <del class="product-old-price">${productWithDetails.productDetails.originPrice}$</del>
                                    </h3>
                                    <span class="product-available">
                                        ${productWithDetails.productDetails.quantity > 0 ? 'In Stock: ' : 'Out of Stock:'} ${productWithDetails.productDetails.quantity}
                                    </span>
                                </div>

                                <div class="product-options">

                                    <p><strong>Discount</strong> 
                                        <c:set var="discount" value="${((productWithDetails.productDetails.originPrice - productWithDetails.productDetails.salePrice) / productWithDetails.productDetails.originPrice) * 100}" />
                                        <c:set var="formattedDiscount" value="${discount}" />
                                        <c:set var="discount" value="${formattedDiscount.toString().substring(0, 4)}" />
                                        <strong style="color: red; font-size: 1.2em;">${discount}%</strong> 
                                    </p>
                                    <p><strong>Color:</strong> ${productWithDetails.productDetails.color}</p>
                                    <p><strong>Brand:</strong> ${brandname.name}</p>
                                    <p><strong>Screen Size:</strong> ${productWithDetails.product.screenSize} (inches)</p>
                                    <p><strong>RAM:</strong> ${ramname.name} (${ramname.memory} GB)</p>
                                    <p><strong>CPU:</strong> ${cpuname.name} (${cpuname.generation})</p>
                                    <p><strong>Card:</strong> ${cardname.name} (${cardname.memory} GB)</p>
                                </div>

                                <div class="add-to-cart">
                                    <div class="qty-label">
                                        Quantity:
                                        <div class="input-number">
                                            <input type="number" name="quantity" id="quantity-input" value="1">
                                            <span class="qty-up">+</span>
                                            <span class="qty-down">-</span>
                                        </div>
                                    </div>
                                    <button type="submit" class="add-to-cart-btn">
                                        <i class="fa fa-shopping-cart"></i> Add to Cart
                                    </button>
                                    <c:if test="${not empty errorMessage}">
                                        <div class="alert alert-danger" style="margin-top: 10px;">
                                            <strong>Error!</strong> ${errorMessage}
                                        </div>
                                    </c:if>

                                    <!-- Success Message -->
                                    <c:if test="${not empty successMessage}">
                                        <div class="alert alert-success" style="margin-top: 10px;">
                                            <strong>Success!</strong> ${successMessage}
                                        </div>
                                    </c:if>
                                </div>

                                <ul class="product-links">
                                    <li>Category:</li>
                                    <li><a href="#">${productWithDetails.product.categoryId == '1' ? 'laptop' : 'accessory'}</a></li>
                                </ul>
                            </form>
                        </div>
                    </div>



                    <!-- Product Tab Section -->
                    <div class="col-md-12">
                        <div id="product-tab">
                            <ul class="tab-nav">
                                <li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
                                <li><a data-toggle="tab" href="#tab2">Details</a></li>
                                <!--                                <li><a data-toggle="tab" href="#tab3">Reviews</a></li>-->
                            </ul>

                            <div class="tab-content">
                                <div id="tab1" class="tab-pane fade in active">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <p>${productWithDetails.product.description}</p>
                                        </div>
                                    </div>
                                </div>

                                <div id="tab2" class="tab-pane fade in">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <p><strong>Brand:</strong> ${brandname.name} - ${brandname.description}</p>
                                            <p><strong>RAM:</strong> ${ramname.name} ${ramname.description} </p>
                                            <p><strong>CPU:</strong> ${cpuname.name} ${cpuname.description}</p>
                                            <p><strong>Card:</strong> ${cardname.name} ${cardname.description} </p>
                                        </div>
                                    </div>
                                </div>

                                <div id="tab3" class="tab-pane fade in">
                                    <div class="row">
                                        <!-- Rating -->
                                        <div class="col-md-3">
                                            <div id="rating">
                                                <div class="rating-avg">
                                                    <span>4.5</span>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                </div>
                                                <ul class="rating">
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div style="width: 80%;"></div>
                                                        </div>
                                                        <span class="sum">3</span>
                                                    </li>
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div style="width: 60%;"></div>
                                                        </div>
                                                        <span class="sum">2</span>
                                                    </li>
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div></div>
                                                        </div>
                                                        <span class="sum">0</span>
                                                    </li>
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div></div>
                                                        </div>
                                                        <span class="sum">0</span>
                                                    </li>
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div></div>
                                                        </div>
                                                        <span class="sum">0</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <!-- /Rating -->

                                        <!-- Reviews -->
                                        <div class="col-md-6">
                                            <div id="reviews">
                                                <ul class="reviews">
                                                    <li>
                                                        <div class="review-heading">
                                                            <h5 class="name">John</h5>
                                                            <p class="date">27 DEC 2018, 8:0 PM</p>
                                                            <div class="review-rating">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o empty"></i>
                                                            </div>
                                                        </div>
                                                        <div class="review-body">
                                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="review-heading">
                                                            <h5 class="name">John</h5>
                                                            <p class="date">27 DEC 2018, 8:0 PM</p>
                                                            <div class="review-rating">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o empty"></i>
                                                            </div>
                                                        </div>
                                                        <div class="review-body">
                                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="review-heading">
                                                            <h5 class="name">John</h5>
                                                            <p class="date">27 DEC 2018, 8:0 PM</p>
                                                            <div class="review-rating">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o empty"></i>
                                                            </div>
                                                        </div>
                                                        <div class="review-body">
                                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
                                                        </div>
                                                    </li>
                                                </ul>
                                                <ul class="reviews-pagination">
                                                    <li class="active">1</li>
                                                    <li><a href="#">2</a></li>
                                                    <li><a href="#">3</a></li>
                                                    <li><a href="#">4</a></li>
                                                    <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <!-- /Reviews -->

                                        <!-- Review Form -->
                                        <div class="col-md-3">
                                            <div id="review-form">
                                                <form class="review-form">
                                                    <input class="input" type="text" placeholder="Your Name">
                                                    <input class="input" type="email" placeholder="Your Email">
                                                    <textarea class="input" placeholder="Your Review"></textarea>
                                                    <div class="input-rating">
                                                        <span>Your Rating: </span>
                                                        <div class="stars">
                                                            <input id="star5" name="rating" value="5" type="radio"><label for="star5"></label>
                                                            <input id="star4" name="rating" value="4" type="radio"><label for="star4"></label>
                                                            <input id="star3" name="rating" value="3" type="radio"><label for="star3"></label>
                                                            <input id="star2" name="rating" value="2" type="radio"><label for="star2"></label>
                                                            <input id="star1" name="rating" value="1" type="radio"><label for="star1"></label>
                                                        </div>
                                                    </div>
                                                    <button class="primary-btn">Submit</button>
                                                </form>
                                            </div>
                                        </div>
                                        <!-- /Review Form -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /SECTION -->

        <!-- Section -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <div class="col-md-12">
                        <div class="section-title text-center">
                            <h3 class="title">Related Products</h3>
                        </div>
                    </div>

                    <!-- product -->
                    <c:choose>
                        <c:when test="${empty relatedProducts}">
                            <p>Không có sản phẩm liên quan nào.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="relatedProduct" items="${relatedProducts}">
                                <div class="col-md-3 col-xs-6">
                                    <div class="product">
                                        <div class="product-img">
                                            <img style="width: 100%;
                                                 height: 200px;
                                                 object-fit: cover;" src="${pageContext.request.contextPath}/assets/electro/img/${relatedProduct.product.image}" alt="${pageContext.request.contextPath}/${relatedProduct.product.image}">
                                            <div class="product-label">
                                                <c:choose>
                                                    <c:when test="${relatedProduct.productDetails.originPrice > relatedProduct.productDetails.salePrice}">
                                                        <c:set var="discount" value="${((relatedProduct.productDetails.originPrice - relatedProduct.productDetails.salePrice) / relatedProduct.productDetails.originPrice) * 100}" />
                                                        <c:set var="formattedDiscount" value="${discount}" />
                                                        <c:set var="discountStr" value="${formattedDiscount.toString().substring(0, 4)}" />
                                                        <span class="sale">-${discountStr}%</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="new">NEW</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category">${relatedProduct.product.categoryId == '1' ? 'laptop' : 'accessory'}</p>
                                            <h3 class="product-name"><a href="productdetails?id=${relatedProduct.productDetails.id}">${relatedProduct.product.title}</a></h3>
                                            <h4 class="product-price">${relatedProduct.productDetails.salePrice} <del class="product-old-price">${relatedProduct.productDetails.originPrice}</del></h4>
                                            <div class="product-rating">

                                            </div>
                                            <div class="product-btns">
                                                <button class="quick-view" onclick="window.location.href = 'productdetails?id=${relatedProduct.productDetails.id}'"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                            </div>
                                        </div>
                                        <div class="add-to-cart">
                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach> 
                            <!-- /product -->
                        </c:otherwise>
                    </c:choose>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /Section -->

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

    </body>
</html>

