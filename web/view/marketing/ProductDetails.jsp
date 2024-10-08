<%-- 
    Document   : form-page
    Created on : Aug 15, 2024, 11:23:03 PM
    Author     : xuant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Product Details</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- daterange picker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/daterangepicker/daterangepicker.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datepicker/datepicker3.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/iCheck/all.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <!-- Bootstrap time Picker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/timepicker/bootstrap-timepicker.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/select2/select2.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/dist/css/skins/_all-skins.min.css">
        <!-- bootstrap wysihtml5 - text editor -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!--header-->
            <jsp:include page="include/header-bar.jsp" />
            <!-- Left side column. contains the logo and sidebar -->
            <jsp:include page="include/side-bar.jsp" />

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Product Details
                        <small>View / Edit a product</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="dashboard"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="productlist">Product List</a></li>
                        <li class="active">Product Details</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title">Product Details</h3>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse">
                                    <i class="fa fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove">
                                    <i class="fa fa-remove"></i>
                                </button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <form action="updateproduct?id=${p_id}" method="post" enctype="multipart/form-data">
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <!-- Left padding (optional) -->
                                    </div>
                                    <div class="col-md-6">
                                        <!-- ID (Disabled) -->
                                        <div class="form-group">
                                            <div style="color: green">${message}</div>
                                            <div style="color: red">${err}</div>
                                            <label>Product ID: </label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-key"></i>
                                                </div>
                                                <input type="text" class="form-control" name="p_id" value="${p_id}" placeholder="Auto-generated ID" disabled style="width: 10%;">
                                                <input type="text" class="form-control" name="create_date" value="${createDate}"  disabled style="width: 35%;">
                                                <input type="text" class="form-control" name="update_date" value="${updateDate}"  disabled style="width: 35%;">
                                                <input type="text" class="form-control" name="quantity" value="${product_quantity}"  disabled style="width: 20%;">
                                            </div>

                                        </div>

                                        <!-- Name -->
                                        <div class="form-group">
                                            <label>Product Name: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-tag"></i>
                                                </div>
                                                <input type="text" class="form-control" name="product_name" placeholder="Enter product name" value="${product_name}">
                                            </div>
                                            <div class="text-danger">${errors.productNameError}</div>
                                        </div>

                                        <!-- Title -->
                                        <div class="form-group">
                                            <label>Title: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-paper-plane"></i>
                                                </div>
                                                <input type="text" class="form-control" name="title" placeholder="Enter a title" value="${product_title}">
                                            </div>
                                            <div class="text-danger">${errors.titleError}</div>
                                        </div>

                                        <!-- Description -->
                                        <div class="form-group">
                                            <label>Product Description: <span style="color: red;">*</span></label>
                                            <textarea class="textarea form-control" placeholder="Enter product description" name="description" 
                                                      style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;">${product_description}</textarea>
                                            <div class="text-danger">${errors.descriptionError}</div>
                                        </div>

                                        <!-- Category -->
                                        <div class="form-group">
                                            <label>Category: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-list-alt"></i>
                                                </div>
                                                <select class="form-control select2" name="product_category" style="width: 100%;">
                                                    <option selected disabled>Select Category</option>
                                                    <c:forEach var="cate" items="${categoryList}">
                                                        <option value="${cate.id}" ${cate.id == product_cate ? 'selected' : ''}>${cate.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="text-danger">${errors.categoryError}</div>
                                        </div>

                                        <!-- Brand -->
                                        <div class="form-group">
                                            <label>Brand: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-list-alt"></i>
                                                </div>
                                                <select class="form-control select2" name="product_brand" style="width: 100%;">
                                                    <option selected disabled>Select brand</option>
                                                    <c:forEach var="brand" items="${brandList}">
                                                        <option value="${brand.id}" ${brand.id == product_brandid ? 'selected' : ''}>${brand.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="text-danger">${errors.brandError}</div>
                                        </div>

                                        <!-- Screen Size -->
                                        <div class="form-group">
                                            <label>Screen Size: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-television"></i>
                                                </div>
                                                <input type="number" class="form-control" name="screen_size" placeholder="Enter screen size" value="${screen_size}" step="0.1">
                                            </div>
                                            <div class="text-danger">${errors.screenSizeError}</div>
                                        </div>

                                        <!-- RAM -->
                                        <div class="form-group">
                                            <label>RAM: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-building-o"></i>
                                                </div>
                                                <select class="form-control select2" name="ram" style="width: 100%;">
                                                    <option value="" selected disabled>Select RAM</option>
                                                    <c:forEach var="ram" items="${ramList}">
                                                        <option value="${ram.id}" ${ram.id == pd_ramid ? 'selected' : ''}>${ram.memory}GB - ${ram.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="text-danger">${errors.ramError}</div>
                                        </div>

                                        <!-- CPU -->
                                        <div class="form-group">
                                            <label>CPU: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-building"></i>
                                                </div>
                                                <select class="form-control select2" name="cpu" style="width: 100%;">
                                                    <option selected disabled>Select CPU</option>
                                                    <c:forEach var="cpu" items="${cpuList}">
                                                        <option value="${cpu.id}" ${cpu.id == pd_cpuid ? 'selected' : ''}>${cpu.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="text-danger">${errors.cpuError}</div>
                                        </div>

                                        <!-- Card -->
                                        <div class="form-group">
                                            <label>Graphics Card: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-credit-card"></i>
                                                </div>
                                                <select class="form-control select2" name="card" style="width: 100%;">
                                                    <option selected disabled>Select Graphics Card</option>
                                                    <c:forEach var="card" items="${cardList}">
                                                        <option value="${card.id}" ${card.id == pd_cardid ? 'selected' : ''}>${card.memory}GB - ${card.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="text-danger">${errors.cardError}</div>
                                        </div>

                                        <!-- Color -->
                                        <div class="form-group">
                                            <label>Color: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-paint-brush"></i>
                                                </div>
                                                <input type="text" class="form-control" name="color" placeholder="Enter product color" value="${color}">
                                            </div>
                                            <div class="text-danger">${errors.colorError}</div>
                                        </div>

                                        <!-- Origin Price -->
                                        <div class="form-group">
                                            <label>Origin Price: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-money"></i>
                                                </div>
                                                <input type="number" class="form-control" name="origin_price" placeholder="Enter origin price" value="${origin_price}">
                                            </div>
                                            <div class="text-danger">${errors.originPriceError}</div>
                                        </div>

                                        <!-- Sale Price -->
                                        <div class="form-group">
                                            <label>Sale Price: <span style="color: red;">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-tag"></i>
                                                </div>
                                                <input type="number" class="form-control" name="sale_price" placeholder="Enter sale price" value="${sale_price}">
                                            </div>
                                            <div class="text-danger">${errors.salePriceError}</div>
                                        </div>

                                        <!-- Product Image -->
                                        <div class="form-group">
                                            <label>Product Image: </label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-picture-o"></i>
                                                </div>
                                                <input type="file" class="form-control" name="product_image" id="productImage" accept="image/*" onchange="previewImage(event)">
                                            </div>
                                            <img id="imagePreview" src="${pageContext.request.contextPath}/assets/electro/img/${product_image}" alt="Image Preview" style="margin-top: 10px; max-width: 100%; height: auto;">
                                            <div class="text-danger">${errors.imageError}</div>
                                        </div>


                                        <!-- Status -->
                                        <div class="form-group">
                                            <label>Status:</label>
                                            <div class="input-group">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="product_status" value="Showing" ${product_status == null || product_status.equals("Showing") ? 'checked' : ''}>
                                                        Show
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="product_status" value="Hiding" ${product_status != null && product_status.equals("Hiding") ? 'checked' : ''}>
                                                        Hide
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="text-danger">${errors.statusError}</div>
                                        </div>

                                    </div>
                                    <div class="col-md-3">
                                        <!-- Right padding (optional) -->
                                    </div>
                                </div>
                            </div>
                            <!-- Buttons -->
                            <div class="box-footer" style="text-align: center;">
                                <button type="submit" class="btn btn-primary">Save Changes</button>
                                <a href="productdetails?id=${p_id}" class="btn btn-secondary">Cancel</a>

                            </div>
                        </form>

                    </div>
                    <!-- /.box -->
                </section>

                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <jsp:include page="include/footer-bar.jsp" />

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Create the tabs -->
                <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                    <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
                    <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <!-- Home tab content -->
                    <div class="tab-pane" id="control-sidebar-home-tab">
                        <h3 class="control-sidebar-heading">Recent Activity</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                        <p>Will be 23 on April 24th</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-user bg-yellow"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>

                                        <p>New phone +1(800)555-1234</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>

                                        <p>nora@example.com</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-file-code-o bg-green"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

                                        <p>Execution time 5 seconds</p>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <!-- /.control-sidebar-menu -->

                        <h3 class="control-sidebar-heading">Tasks Progress</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Custom Template Design
                                        <span class="label label-danger pull-right">70%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Update Resume
                                        <span class="label label-success pull-right">95%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-success" style="width: 95%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Laravel Integration
                                        <span class="label label-warning pull-right">50%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Back End Framework
                                        <span class="label label-primary pull-right">68%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <!-- /.control-sidebar-menu -->

                    </div>
                    <!-- /.tab-pane -->
                    <!-- Stats tab content -->
                    <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
                    <!-- /.tab-pane -->
                    <!-- Settings tab content -->
                    <div class="tab-pane" id="control-sidebar-settings-tab">
                        <form method="post">
                            <h3 class="control-sidebar-heading">General Settings</h3>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Report panel usage
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Some information about this general settings option
                                </p>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Allow mail redirect
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Other sets of options are available
                                </p>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Expose author name in posts
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Allow the user to show his name in blog posts
                                </p>
                            </div>
                            <!-- /.form-group -->

                            <h3 class="control-sidebar-heading">Chat Settings</h3>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Show me as online
                                    <input type="checkbox" class="pull-right" checked>
                                </label>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Turn off notifications
                                    <input type="checkbox" class="pull-right">
                                </label>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Delete chat history
                                    <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                                </label>
                            </div>
                            <!-- /.form-group -->
                        </form>
                    </div>
                    <!-- /.tab-pane -->
                </div>
            </aside>
            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 2.2.3 -->
        <script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
        <!-- Select2 -->
        <script src="${pageContext.request.contextPath}/assets/plugins/select2/select2.full.min.js"></script>
        <!-- InputMask -->
        <script src="${pageContext.request.contextPath}/assets/plugins/input-mask/jquery.inputmask.js"></script>
        <script src="${pageContext.request.contextPath}/assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="${pageContext.request.contextPath}/assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <!-- date-range-picker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/plugins/daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap datepicker -->
        <script src="${pageContext.request.contextPath}/assets/plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- bootstrap color picker -->
        <script src="${pageContext.request.contextPath}/assets/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <!-- bootstrap time picker -->
        <script src="${pageContext.request.contextPath}/assets/plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- SlimScroll 1.3.0 -->
        <script src="${pageContext.request.contextPath}/assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="${pageContext.request.contextPath}/assets/plugins/iCheck/icheck.min.js"></script>
        <!-- FastClick -->
        <script src="${pageContext.request.contextPath}/assets/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/assets/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="${pageContext.request.contextPath}/assets/dist/js/demo.js"></script>
        <!-- CK Editor -->
        <script src="https://cdn.ckeditor.com/4.5.7/standard/ckeditor.js"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="${pageContext.request.contextPath}/assets/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
        <script>
                                                    function previewImage(event) {
                                                        const fileInput = event.target;
                                                        const imagePreview = document.getElementById('imagePreview');

                                                        // Kiểm tra xem người dùng đã chọn file nào chưa
                                                        if (fileInput.files && fileInput.files[0]) {
                                                            const reader = new FileReader();

                                                            // Khi file được tải lên, cập nhật src của ảnh preview
                                                            reader.onload = function (e) {
                                                                imagePreview.src = e.target.result;
                                                                imagePreview.style.display = 'block';
                                                            };

                                                            reader.readAsDataURL(fileInput.files[0]);
                                                        }
                                                    }
        </script>

        <script>
            $(function () {
                //Initialize Select2 Elements
                $(".select2").select2();

                //Datemask dd/mm/yyyy
                $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                //Datemask2 mm/dd/yyyy
                $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
                //Money Euro
                $("[data-mask]").inputmask();

                //Date range picker
                $('#reservation').daterangepicker();
                //Date range picker with time picker
                $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
                //Date range as a button
                $('#daterange-btn').daterangepicker(
                        {
                            ranges: {
                                'Today': [moment(), moment()],
                                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                                'This Month': [moment().startOf('month'), moment().endOf('month')],
                                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                            },
                            startDate: moment().subtract(29, 'days'),
                            endDate: moment()
                        },
                        function (start, end) {
                            $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                        }
                );

                //Date picker
                $('#datepicker').datepicker({
                    autoclose: true
                });

                //iCheck for checkbox and radio inputs
                $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                    checkboxClass: 'icheckbox_minimal-blue',
                    radioClass: 'iradio_minimal-blue'
                });
                //Red color scheme for iCheck
                $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
                    checkboxClass: 'icheckbox_minimal-red',
                    radioClass: 'iradio_minimal-red'
                });
                //Flat red color scheme for iCheck
                $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                    checkboxClass: 'icheckbox_flat-green',
                    radioClass: 'iradio_flat-green'
                });

                //Colorpicker
                $(".my-colorpicker1").colorpicker();
                //color picker with addon
                $(".my-colorpicker2").colorpicker();

                //Timepicker
                $(".timepicker").timepicker({
                    showInputs: false
                });
            });
        </script>
    </body>
</html>
