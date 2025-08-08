<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Asura storage management</title>
	<script src="<c:url value='/resources/vendors/jquery/dist/jquery.js'/>"></script>
    <!-- Bootstrap -->
    <link href="<c:url value='/resources/vendors/bootstrap/dist/css/bootstrap.min.css' />" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<c:url value='/resources/vendors/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <!-- NProgress -->
    <link href="<c:url value='/resources/vendors/nprogress/nprogress.css'/>" rel="stylesheet">
    <!-- jQuery custom content scroller -->
    <link href="<c:url value='/resources/vendors/animate.css/animate.min.css'/>" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="<c:url value='/resources/build/css/custom.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/build/css/style-extra-bs4.css'/>" rel="stylesheet">
</head>

<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
        <c:if test="${null != error}">
    <div class="alert alert-danger">${error}</div>
</c:if>
            <section class="login_content">
                <form:form modelAttribute="loginForm" servletRelativeAction="/login" method="POST">
                    <h1>Login Form</h1>
                    <div>
                        <form:input type="text" class="form-control" placeholder="Username" path="userName" id="u1"/>
                        <div class="has-error">
                        <form:errors path="userName" cssClass="help-block"></form:errors></div>
                    </div>
                    <div>
                        <form:password class="form-control" placeholder="Password" path="password" id="p1"/>
                        <div class="has-error">
                        <form:errors path="password" cssClass="help-block"></form:errors></div>
                    </div>
                    <div class="row">
                        <div class="col-4 text-left">
                            <a class="btn btn-secondary reset_pass" href="/storage_management/upadmin">Up to admin</a>
                        </div>
                        <div class="col-4 text-center">
                            <button class="btn btn-primary" type="submit">Log in</button>
                        </div>
                        <div class="col-4 text-right">
                            <a class="btn btn-secondary reset_pass" href="#">Lost your password?</a>
                        </div>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">New to site?
                            <a href="#signup" class="to_register"> Create Account </a>
                        </p>

                        <div class="clearfix"></div>
                        <br/>

                        <div>
                            <h1><i class="fa fa-paw"></i> Asura Storage Management!</h1>
                            <p>©2025 All Rights Reserved. Asura Storage Management! is a storage management service for enterprise. Privacy and
                                Terms</p>
                        </div>
                    </div>
                </form:form>
            </section>
        </div>

        <div id="register" class="animate form registration_form">
            <section class="login_content">
                <form:form modelAttribute="registerForm" servletRelativeAction="/processLogin" method="PUT">
                    <h1>Create Account</h1>
                    <div>
                        <form:input type="text" class="form-control" placeholder="Your Name..." path="name"/>
                        <div class="has-error">
                        <form:errors path="name" cssClass="help-block"></form:errors></div>
                    </div>
                    <div>
                        <form:input type="text" class="form-control" placeholder="Username" path="userName"/>
                        <div class="has-error">
                        <form:errors path="userName" cssClass="help-block"></form:errors></div>
                    </div>
                    <div>
                        <form:input type="email" class="form-control" placeholder="example@mail.com" path="email"/>
                        <div class="has-error">
                        <form:errors path="email" cssClass="help-block"></form:errors></div>
                    </div>
                    <div>
                        <form:password class="form-control" placeholder="Password" path="password"/>
                        <div class="has-error">
                        <form:errors path="password" cssClass="help-block"></form:errors></div>
                    </div>
                    <div>
                        <button class="btn btn-light"  type="submit">Submit</button>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">Already a member ?
                            <a href="#signin" class="to_register"> Log in </a>
                        </p>

                        <div class="clearfix"></div>
                        <br/>

                        <div>
                            <h1><i class="fa fa-paw"></i> Asura Storage Management!</h1>
                            <p>©2025 All Rights Reserved. Asura Storage Management! is a storage management service for enterprise. Privacy and
                                Terms</p>
                        </div>
                    </div>
                </form:form>
            </section>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('input[name="_method"]').attr("value", "POST");
})
</script>
</html>
