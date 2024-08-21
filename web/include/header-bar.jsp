<%-- 
    Document   : header-bar
    Created on : Aug 21, 2024, 10:11:03 AM
    Author     : xuant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <div id="loginModal" class="modal">
                <div class="modal-content">
                    <span class="close" onclick="closeModal('loginModal')">&times;</span>
                    <form action="/SWP391_BL5_G4ComShop/login" method="post">
                        <c:if test="${not empty error}">
                            <div class="error-message">${error}</div>
                        </c:if>

                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" id="username" name="username" value="${username}" required>
                        </div>

                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password" required>
                        </div>

                        <div class="form-group" style="flex-direction: row; align-items: center;">
                            <input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe == 'on' ? 'checked' : ''}>
                            <label for="rememberMe">Remember me</label>
                        </div>

                        <input type="submit" value="Login">
                    </form>
                </div>
            </div>
            <div id="signupModal" class="modal">
                <div class="modal-content">
                    <span class="close" onclick="closeModal('signupModal')">&times;</span>
                    <form action="signup" method="post">
                        <c:if test="${not empty err}">
                            <div class="error-message">${err}</div>
                        </c:if>
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" id="username" name="username" value="${username}" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="pass" required>
                        </div>
                        <div class="form-group">
                            <label for="confirmpassword">Confirm Password:</label>
                            <input type="password" id="confirmpassword" name="confirmpassword" required>
                        </div>
                        <div class="form-group">
                            <label for="name">Full Name:</label>
                            <input type="text" id="name" name="name" value="${fullname}" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" value="${email}" required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone:</label>
                            <input type="tel" id="phone" name="phone" value="${phone}" required>
                        </div>
                        <div class="form-group">
                            <label for="address">Address:</label>
                            <input type="text" id="address" name="add" value="${address}" required>
                        </div>
                        <div class="form-group">
                            <label for="dob">Date of Birth:</label>
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
                <ul class="header-links pull-left">
                    
                </ul>
                <ul class="header-links pull-right">
                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <!-- Nếu session tồn tại -->
                        <li><a href="logout"><i class="fa fa-sign-out"></i> Logout</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-user-o"></i> My Account <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">View profile</a></li>
                                <li><a href="./user/changepass">Change Pass</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <!-- Nếu session không tồn tại -->
                        <li><a href="#" onclick="openModal('loginModal')" ><i class="fa fa-sign-in"></i> Login</a></li>
                        <li><a href="#" onclick="openModal('signupModal')"><i class="fa fa-user-plus"></i> Signup</a></li>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </div>
    </div>


    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <a href="#" class="logo">
                            <img src="${pageContext.request.contextPath}/assets/electro/img/Screenshot 2024-08-18 035922.png" alt="">
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">

                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <!-- Cart -->
                        <div class="dropdown">
                            <a href="CartController">
                                <i class="fa fa-shopping-cart"></i>
                                <span>Your Cart</span>
                            </a>
                        </div>
                        <!-- /Cart -->

                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
</header>
