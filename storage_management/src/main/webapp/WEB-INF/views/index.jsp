<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentelella Alela! | </title>

    <!-- Bootstrap -->
    <!-- Dinh nghia cac duong dan header bang the c:url, la 1 cong dung cua jstl -->
       <link href="<c:url value='/resources/vendors/bootstrap/dist/css/bootstrap.min.css' />" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<c:url value='/resources/vendors/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <!-- NProgress -->
    <link href="<c:url value='/resources/vendors/nprogress/nprogress.css'/>" rel="stylesheet">
    <!-- jQuery custom content scroller -->
    <link href="<c:url value='/resources/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css'/>" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="<c:url value='/resources/build/css/custom.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/build/css/style-extra-bs4.css'/>" rel="stylesheet">
</head>

<body class="nav-md footer_fixed">
<div class="body">
    <div class="main_container container-fluid">
        <div class="row">
            <div class="col-lg-2 col-md-2 left_col">
                <div class="left_col">
                    <div class="navbar nav_title" style="border: 0;">
                        <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Storage Management!</span></a>
                    </div>

                    <div class="clearfix"></div>

                    <!-- menu profile quick info -->
                    <div class="profile clearfix">
                        <div class="profile_pic">
                            <!-- Tat ca cac url deu p thay bang c:url <img src="images/img.jpg" alt="..." class="img-circle profile_img"> -->
                            <img src="<c:url value='/images/user.png'/>" alt="..." class="img-circle profile_img">
                        </div>
                        <div class="profile_info">
                            <span>Welcome,</span>
                            <h2>Luan Nguyen</h2>
                        </div>
                    </div>
                    <!-- /menu profile quick info -->

                    <br/>

                    <!-- sidebar menu -->
                    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                        <div class="menu_section">
                            <h3>General</h3>
                            <ul class="nav side-menu">
                                <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a href="index.html">Dashboard</a></li>
                                        <li><a href="index2.html">Dashboard2</a></li>
                                        <li><a href="index3.html">Dashboard3</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <!-- /sidebar menu -->

                    <!-- /menu footer buttons -->
                    <div class="sidebar-footer col-lg-2 hidden-small">
                        <a data-toggle="tooltip" data-placement="top" title="Settings">
                            <span class="fa fa-cog" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                            <span class="fa fa-arrows-alt" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="Lock">
                            <span class="fa fa-eye-slash" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                            <span class="fa fa-power-off" aria-hidden="true"></span>
                        </a>
                    </div>
                    <!-- /menu footer buttons -->
                </div>
            </div>
            <div class="col-md-10 col-lg-10 right_col_wrapper">
                <div class="row">
                    <!-- top navigation -->
                    <div class="col-lg-12 top_nav">
                        <div class="nav_menu">
                            <nav>
                                <div class="nav toggle">
                                    <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                                </div>

                                <ul class="nav navbar-nav navbar-right">
                                    <li class="">
                                        <a href="javascript:;" class="user-profile dropdown-toggle"
                                           data-toggle="dropdown" aria-expanded="false">
                                            <img src="images/img.jpg" alt="">John Doe
                                            <span class=" fa fa-angle-down"></span>
                                        </a>
                                        <ul class="dropdown-menu dropdown-usermenu pull-right">
                                            <li><a href="javascript:;"> Profile</a></li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span class="badge bg-red pull-right">50%</span>
                                                    <span>Settings</span>
                                                </a>
                                            </li>
                                            <li><a href="javascript:;">Help</a></li>
                                            <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log
                                                Out</a></li>
                                        </ul>
                                    </li>

                                    </ul>
                            </nav>
                        </div>

                    </div>
                    <!-- /top navigation -->

                    <!-- page content -->
                    <div class="col-lg-12 right_col" role="main">
                        <div class="">
                            <div class="page-title">
                                <div class="title_left">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /page content -->

                    <!-- footer content -->
                    <footer class="col-lg-12">
                        <div class="pull-right">
                        Storage Management Copyright Asura
                        </div>
                        <div class="clearfix"></div>
                    </footer>
                    <!-- /footer content -->
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="<c:url value='/resources/vendors/jquery/dist/jquery.min.js'/>"></script>
<!-- Popper -->
<script src="<c:url value='/resources/vendors/popper/popper.min.js'/>"></script>
<!-- Bootstrap -->
<script src="<c:url value='/resources/vendors/bootstrap/dist/js/bootstrap.min.js'/>"></script>
<!-- FastClick -->
<script src="<c:url value='/resources/vendors/fastclick/lib/fastclick.js'/>"></script>
<!-- NProgress -->
<script src="<c:url value='/resources/vendors/nprogress/nprogress.js'/>"></script>
<!-- jQuery custom content scroller -->
<script src="<c:url value='/resources/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js'/>"></script>

<!-- Custom Theme Scripts -->
<script src="<c:url value='/resources/build/js/custom.min.js'/>"></script>
</body>
</html>