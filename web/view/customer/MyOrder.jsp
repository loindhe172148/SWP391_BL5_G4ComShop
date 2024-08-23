<%-- 
    Document   : MyOrder
    Created on : Aug 23, 2024, 3:19:38 PM
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

        <title>My Order</title>

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
        <!--style header-->
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
        <!--style popup-->
        <style>
            .popup {
                padding-top: 300px;
                display: none; /* Hidden by default */
                position: fixed;
                z-index: 1; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto; /* Enable scroll if needed */
                background-color: rgba(0,0,0,0.4); /* Black with opacity */
                align-items: center; /* Center the popup */
                justify-content: center; /* Center the popup */
            }

            .popup-content {
                background-color: #fff;
                margin: auto;
                padding: 20px;
                border: 1px solid #888;
                width: 300px; /* Adjust width */
                border-radius: 5px; /* Rounded corners */
                text-align: center; /* Center text */
                position: relative; /* Relative positioning for close button */
            }

            .popup-content h2 {
                margin-top: 0;
            }

            .close {
                position: absolute;
                top: 10px;
                right: 10px;
                font-size: 24px;
                font-weight: bold;
                cursor: pointer;
            }

            .button-group {
                margin-top: 20px;
            }

            .button-group button {
                margin: 0 5px;
            }
        </style>
        <style>
            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                list-style: none;
                padding: 0;
                margin: 20px 0;
            }

            .page-link {
                margin: 0 5px;
                text-decoration: none;
                color: #007bff; /* Màu sắc của liên kết trang */
            }

            .page-link button {
                border: 1px solid #007bff;
                background-color: #fff;
                color: #007bff;
                padding: 5px 10px;
                border-radius: 3px;
                cursor: pointer;
            }

            .page-link.active button {
                background-color: #007bff;
                color: #fff;
            }

            .page-link button:hover {
                background-color: #e9ecef;
            }
        </style>
    </head>
    <body>
        <!-- HEADER -->
        <jsp:include page="../../include/header-bar.jsp" />
        <!-- /HEADER -->


        <!-- BREADCRUMB -->
        <div id="breadcrumb" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <ul class="breadcrumb-tree">
                            <li><a href="${pageContext.request.contextPath}/productHome">Home</a></li>
                            <li class="active">My Order</li>
                        </ul>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /BREADCRUMB -->

        <!-- SECTION -->
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-1">
                        <!-- Placeholder for left margin -->
                    </div>
                    <div class="col-md-10">
                        <!-- Sắp xếp và phân trang -->
                        <div class="mb-3">
                            <!-- Tùy chọn sắp xếp khác không dùng thẻ a -->
                        </div>

                        <table class="table table-bordered table-striped table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>
                                        <form action="myorder" method="get" style="display:inline;">
                                            <input type="hidden" name="sortBy" value="id">
                                            <input type="hidden" name="order" value="${param.sortBy eq 'id' && param.order eq 'asc' ? 'desc' : 'asc'}">
                                            <button type="submit" class="btn btn-link" style="padding:0;">Order ID ${param.sortBy eq 'id' && param.order eq 'asc' ? '▲' : '▼'}</button>
                                        </form>
                                    </th>
                                    <th>
                                        <form action="myorder" method="get" style="display:inline;">
                                            <input type="hidden" name="sortBy" value="orderdate">
                                            <input type="hidden" name="order" value="${param.sortBy eq 'orderdate' && param.order eq 'asc' ? 'desc' : 'asc'}">
                                            <button type="submit" class="btn btn-link" style="padding:0;">Date ${param.sortBy eq 'orderdate' && param.order eq 'asc' ? '▲' : '▼'}</button>
                                        </form>
                                    </th>
                                    <th>
                                        <form action="myorder" method="get" style="display:inline;">
                                            <input type="hidden" name="sortBy" value="totalamount">
                                            <input type="hidden" name="order" value="${param.sortBy eq 'totalamount' && param.order eq 'asc' ? 'desc' : 'asc'}">
                                            <button type="submit" class="btn btn-link" style="padding:0;">Total Amount ${param.sortBy eq 'totalamount' && param.order eq 'asc' ? '▲' : '▼'}</button>
                                        </form>
                                    </th>
                                    <th>
                                        <form action="myorder" method="get" style="display:inline;">
                                            <input type="hidden" name="sortBy" value="statusid">
                                            <input type="hidden" name="order" value="${param.sortBy eq 'statusid' && param.order eq 'asc' ? 'desc' : 'asc'}">
                                            <button type="submit" class="btn btn-link" style="padding:0;">Status ${param.sortBy eq 'statusid' && param.order eq 'asc' ? '▲' : '▼'}</button>
                                        </form>
                                    </th>
                                    <th>Shipping Address</th>
                                    <th>View Order</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${orders}">
                                    <tr>
                                        <td>${order.id}</td>
                                        <td>${order.orderdate}</td>
                                        <td>${order.totalamount}</td>
                                        <td>${order.statusid}</td>
                                        <td>${order.shippingaddress}</td>
                                        <td>
                                            <form action="orderdetails" method="post" style="display:inline;">
                                                <input type="hidden" name="orderId" value="${order.id}">
                                                <button type="submit" class="btn btn-info btn-sm">View Order</button>
                                            </form>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${order.statusid == 'processing'}">
                                                    <form action="myorder?page=${pageNumber}&sortBy=${sortBy}&order=${order}" method="post" style="display:inline;">
                                                        <input type="hidden" name="orderId" value="${order.id}">
                                                        <input type="hidden" name="action" value="cancel">
                                                        <button type="button" class="btn btn-danger btn-sm" onclick="openPopup(${order.id})">Cancel</button>
                                                    </form>
                                                </c:when>
                                                <c:when test="${order.statusid == 'delivering'}">
                                                    <form action="myorder?page=${pageNumber}&sortBy=${sortBy}&order=${order}" method="post" style="display:inline;">
                                                        <input type="hidden" name="orderId" value="${order.id}">
                                                        <input type="hidden" name="action" value="confirmdelivery">
                                                        <button type="submit" class="btn btn-success btn-sm">Confirm Delivered</button>
                                                    </form>
                                                </c:when>
                                                <c:when test="${order.statusid == 'delivered'}">
                                                    <form action="myorder?page=${pageNumber}&sortBy=${sortBy}&order=${order}" method="post" style="display:inline;">
                                                        <input type="hidden" name="orderId" value="${order.id}">
                                                        <input type="hidden" name="action" value="feedback">
                                                        <button type="submit" class="btn btn-primary btn-sm">Feedback</button>
                                                    </form>
                                                    <form action="myorder?page=${pageNumber}&sortBy=${sortBy}&order=${order}" method="post" style="display:inline;">
                                                        <input type="hidden" name="orderId" value="${order.id}">
                                                        <input type="hidden" name="action" value="reorder">
                                                        <button type="submit" class="btn btn-secondary btn-sm">Reorder</button>
                                                    </form>
                                                </c:when>
                                                <c:when test="${order.statusid == 'canceled' || order.statusid == 'rejected' }">
                                                    <form action="myorder?page=${pageNumber}&sortBy=${sortBy}&order=${order}" method="post" style="display:inline;">
                                                        <input type="hidden" name="orderId" value="${order.id}">
                                                        <input type="hidden" name="action" value="reorder">
                                                        <button type="submit" class="btn btn-secondary btn-sm">Reorder</button>
                                                    </form>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- Phân trang -->
                        <div class="pagination">
                            <c:if test="${pageNumber > 1}">
                                <a href="myorder?page=${pageNumber - 1}&sortBy=${sortBy}&order=${order}" class="page-link"><button>&laquo; </button></a>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <a href="myorder?page=${i}&sortBy=${sortBy}&order=${order}" class="page-link ${i == pageNumber ? 'active' : ''}"><button >${i}</button></a>
                                </c:forEach>
                                <c:if test="${pageNumber < totalPages}">
                                <a href="myorder?page=${pageNumber + 1}&sortBy=${sortBy}&order=${order}" class="page-link"><button> &raquo;</button></a>
                            </c:if>
                        </div>

                    </div>


                    <!-- The Popup -->
                    <div id="cancelPopup" class="popup">
                        <div class="popup-content">
                            <span class="close" onclick="closePopup()">&times;</span>
                            <h2>Confirm Cancel</h2>
                            <p>Are you sure you want to cancel this order?</p>
                            <form id="cancelorder" method="post" action="myorder">
                                <input type="hidden" name="orderId" id="cancelOrderId">
                                <input type="hidden" name="action" value="cancel">
                                <button type="submit" class="btn btn-danger btn-sm">Yes</button>
                                <button type="button" class="btn btn-secondary btn-sm" onclick="closePopup()">No</button>
                            </form>
                        </div>
                    </div>


                    <div class="col-md-1">
                        <!-- Placeholder for right margin -->
                    </div>
                </div>
            </div>
        </div>

        <!-- /SECTION -->

        <!-- FOOTER -->
        <jsp:include page="../../include/footer-bar.jsp" />

        <!-- /FOOTER -->

        <!-- jQuery Plugins -->
        <script src="${pageContext.request.contextPath}/assets/electro/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/electro/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/electro/js/slick.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/electro/js/nouislider.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/electro/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/electro/js/main.js"></script>
        <script>
                                    function openPopup(orderId) {
                                        document.getElementById('cancelOrderId').value = orderId;
                                        document.getElementById('cancelPopup').style.display = 'block';
                                    }

                                    function closePopup() {
                                        document.getElementById('cancelPopup').style.display = 'none';
                                    }
        </script>
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

