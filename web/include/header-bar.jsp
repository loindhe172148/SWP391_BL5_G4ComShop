<%-- 
    Document   : header-bar
    Created on : Aug 21, 2024, 10:11:03 AM
    Author     : xuant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<header>
    <div id="loginModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal('loginModal')">&times;</span>
            <form action="/SWP391_BL5_G4ComShop/login" method="post">
                <c:if test="${not empty errorLogin}">
                    <div id="loginErrorMessage" class="error-message">${errorLogin}</div>
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
                    <button type="button" onclick="openModal('resetPasswordModal')" style="margin:0px 10px 10px 40px; background-color: #4CAF50;
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
                    <div id="signupErrorMessage" class="error-message">${errorSignup}</div>
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
        <div id="resetPasswordModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal('resetPasswordModal')">&times;</span>
            <c:if test="${not empty errorReset}">
                <p class="error-message">${errorReset}</p>
            </c:if>
            <form action="resetpass" method="post">
                <label for="input">Enter your username or email</label>
                <input type="text" id="input" name="input" value="${input}" required>

                <input type="submit" value="Reset">
            </form>
        </div>
    </div>
    <%
        Boolean resetSuccess = (Boolean) request.getAttribute("resetSuccess");
    %>

    <script>
        <% if (Boolean.TRUE.equals(resetSuccess)) { %>
                showResetSuccessAlert();
        <% } %>
    </script>
    <div id="verificationModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal('verificationModal')">&times;</span>
            <c:if test="${not empty code}">
                <input type="hidden" class="code" id="code" />
            </c:if>
            <c:if test="${not empty errorVerify}">
                <div class="error-message">${errorVerify}</div>
            </c:if>

            <form action="verify" method="post">
                <label for="passconfirm">Enter Verify code:</label>
                <input type="text" id="passconfirm" name="code" required><br>

                <input type="hidden" name="email" value="${email}">
                <input type="hidden" name="username" value="${username}">
                <input type="hidden" name="password" value="${password}">
                <input type="hidden" name="role" value="${role}">
                <input type="hidden" name="phone" value="${phone}">
                <input type="hidden" name="address" value="${address}">
                <input type="hidden" name="dob" value="${dob}">
                <input type="hidden" name="gender" value="${gender}">
                <input type="hidden" name="fullname" value="${fullname}">

                <input type="submit" value="Verify">
            </form>
        </div>
    </div>
    <%
            Boolean verificationSuccess = (Boolean) request.getAttribute("verificationSuccess");
    %>

    <script>
        <% if (Boolean.TRUE.equals(verificationSuccess)) { %>
                showSignupSuccessAlert();
        <% } %>
    </script>
    <c:if test="${sessionScope.account eq null}">
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
    </c:if>
    <c:if test="${sessionScope.account ne null}">
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
                            <i style="font-size: 20px" class="fa fa-user-o"></i>
                            <span style="font-size: 20px;">My Account</span> <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">View profile</a></li>
                            <li><a href="CartController">My Cart</a></li>
                            <li><a href="./user/changepass">Change Pass</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div> 
    </c:if>
</header>
