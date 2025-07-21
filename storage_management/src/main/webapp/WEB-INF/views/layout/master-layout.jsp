<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Asura Storage Management! | </title>
	<!-- jQuery -->
	<script src="<c:url value='/resources/vendors/jquery/dist/jquery.js'/>"></script>
	<!-- Proper -->
	<script src="<c:url value='/resources/vendors/popper/popper.min.js'/>"></script>
    <!-- Bootstrap -->
    <link href="<c:url value='/resources/vendors/bootstrap/dist/css/bootstrap.min.css' />" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<c:url value='/resources/vendors/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <!-- NProgress -->
    <link href="<c:url value="/resources/vendors/nprogress/nprogress.css"/>" rel="stylesheet">
    <!-- PNotify -->
    <link href="<c:url value='/resources/vendors/pnotify/dist/pnotify.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/vendors/pnotify/dist/pnotify.buttons.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/vendors/pnotify/dist/pnotify.nonblock.css'/>" rel="stylesheet">
    <!-- bootstrap-datetimepicker. -->
	<!-- <link href="<c:url value="/resources/vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css"/>" rel="stylesheet"> -->
	<link href="<c:url value='/resources/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet">
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
                            <img src="https://gravatar.com/avatar/${userInfo.avatar}" alt="..." class="img-circle profile_img">
                        </div>
                        <div class="profile_info">
                            <span>Welcome,</span>
                            <h2>${userInfo.name}</h2>
                        </div>
                    </div>
                    <!-- /menu profile quick info -->

                    <br/>

                    <!-- sidebar menu -->
                    <tiles:insertAttribute name="sidebar"></tiles:insertAttribute>
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
                        <a data-toggle="tooltip" data-placement="top" title="Logout" href="<c:url value="/logout"></c:url>">
                            <span class="fa fa-power-off" aria-hidden="true"></span>
                        </a>
                    </div>
                    <!-- /menu footer buttons -->
                </div>
            </div>
            <div class="col-md-10 col-lg-10 right_col_wrapper">
                <div class="row">
                    <!-- top navigation -->
                    <tiles:insertAttribute name="top-nav"></tiles:insertAttribute>
                    <!-- /top navigation -->

                    <!-- page content -->
                    <tiles:insertAttribute name="body"></tiles:insertAttribute>
                    <!-- /page content -->
aw
                    <!-- footer content -->
                    <tiles:insertAttribute name="footer"></tiles:insertAttribute>
                    <!-- /footer content -->
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap -->
<script src="<c:url value='/resources/vendors/bootstrap/dist/js/bootstrap.min.js'/>"></script>
<!-- FastClick -->
<script src="<c:url value='/resources/vendors/fastclick/lib/fastclick.js'/>"></script>
<!-- NProgress -->
<script src="<c:url value='/resources/vendors/nprogress/nprogress.js'/>"></script>
<!-- jQuery custom content scroller -->
<script src="<c:url value="/resources/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"/>"></script>
<!-- PNotify -->
<script src="<c:url value='/resources/vendors/pnotify/dist/pnotify.nonblock.js'/>"></script>
<script src="<c:url value='/resources/vendors/pnotify/dist/pnotify.js'/>"></script>
<script src="<c:url value='/resources/vendors/pnotify/dist/pnotify.buttons.js'/>"></script>
<!--bootstrap-daterangepicker-->
<!-- <script src="<c:url value="/resources/vendors/moment/min/moment.min.js"/>"></script> -->
<!-- bootstrap-datetimepicker -->
<!-- <script src="<c:url value="/resources/vendors/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script> -->
<script src="<c:url value='/resources/cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.min.js'/>"></script>
<script src="<c:url value='/resources/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js'/>"></script>
<script src="<c:url value='/resources/bootstrap-datetimepicker/js/demo.js'/>"></script>
<!-- Custom Theme Scripts -->
<script src="<c:url value='/resources/build/js/custom.js'/>"></script>
<script>
  const observer = new MutationObserver(mutations => {
    mutations.forEach(mutation => {
      mutation.addedNodes.forEach(node => {
        if (node.nodeType === 1 && node.classList.contains('dark')) {
          node.remove();
        }
      });
    });
  });

  observer.observe(document.body, { childList: true, subtree: true });
</script>
</body>
</html>