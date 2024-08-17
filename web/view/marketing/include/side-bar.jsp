
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/assets/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>Tên người dùng</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search st...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview">
                <a href="dashboard">
                    <i class="fa fa-dashboard"></i> <span>DASHBOARD</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
<!--                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/assets/index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
                    <li><a href="${pageContext.request.contextPath}/assets/index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
                </ul>-->
            </li>
            <li class="treeview">
                <a href="productlist">
                    <i class="fa fa-files-o"></i>
                    <span>Product List</span>
                    <span class="pull-right-container">
                        <span class="label label-primary pull-right">2</span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="productlist"><i class="fa fa-circle-o"></i>All Product</a></li>
                    <li><a href="addproduct"><i class="fa fa-circle-o"></i>Add Product</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="productlist">
                    <i class="fa fa-files-o"></i>
                    <span>Customer List</span>
                    <span class="pull-right-container">
                        <span class="label label-primary pull-right">2</span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="productlist"><i class="fa fa-circle-o"></i>All Customers</a></li>
                    <li><a href="addproduct"><i class="fa fa-circle-o"></i>Add Customer</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="productlist">
                    <i class="fa fa-files-o"></i>
                    <span>Order List</span>
                    <span class="pull-right-container">
                        <span class="label label-primary pull-right">2</span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="productlist"><i class="fa fa-circle-o"></i>All Orders</a></li>
                    <li><a href="addproduct"><i class="fa fa-circle-o"></i>Add ... </a></li>
                </ul>
            </li>
            
            <li><a href="#"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
            <li class="header">LABELS</li>
            <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>