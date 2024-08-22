
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
                    <i class="fa fa-home"></i> <span>DASHBOARD</span>
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
                    <i class="fa fa-laptop"></i>
                    <span>Product List</span>
                    <span class="pull-right-container">
                        <span class="label label-primary pull-right">2</span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="productlist"><i class="fa fa-circle-o"></i>All Product</a></li>
                    <li><a href="formadd"><i class="fa fa-circle-o"></i>Add Product</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-"></i>
                    <span>Accessory List</span>
                    <span class="pull-right-container">
                        <span class="label label-primary pull-right">4</span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="brandname"><i class="fa fa-circle-o"></i>Brand List</a></li>
                    <li><a href="accessoryRam"><i class="fa fa-circle-o"></i>RAM List</a></li>
                    <li><a href="accessoryCpu"><i class="fa fa-circle-o"></i>CPU List</a></li>
                    <li><a href="accessoryCard"><i class="fa fa-circle-o"></i>CARD List</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="">
                    <i class="fa fa-user"></i>
                    <span>Customer List</span>
                    <span class="pull-right-container">
                        <span class="label label-primary pull-right">2</span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href=""><i class="fa fa-circle-o"></i>All Customers</a></li>
                    <li><a href=""><i class="fa fa-circle-o"></i>Add Customer</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="">
                    <i class="fa fa-files-o"></i>
                    <span>Order List</span>
                    <span class="pull-right-container">
                        <span class="label label-primary pull-right">2</span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href=""><i class="fa fa-circle-o"></i>All Orders</a></li>
                    <li><a href=""><i class="fa fa-circle-o"></i>Add ... </a></li>
                </ul>
            </li>
            
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>Charts</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li>
                    <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li>
                    <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li>
                    <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li>
                </ul>
            </li>


            <li><a href="#"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
            <li class="header">LABELS</li>
            <!--            <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
                        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
                        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>