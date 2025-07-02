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

    <!-- Bootstrap -->
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

<body class="login">
<div>
    <div class="login_wrapper">
    <c:if test="${null != error}">
    <div class="alert alert-danger">${error}</div>
</c:if>
        <div id="register" class="animate form registration_form">
            <section class="login_content">
                <form:form modelAttribute="registerForm" servletRelativeAction="/processRegister" method="POST">
                    <h1>Create Account</h1>
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
                    <div class="col-6 text-Center">
                            <button class="btn btn-primary submit" type="submit">Submit</button>
                        </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">Already a member ?
                            <a href="/storage_management/login" class="to_register"> Log in </a>
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
</html>
