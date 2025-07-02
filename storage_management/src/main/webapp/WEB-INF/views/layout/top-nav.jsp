<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                                            <img src="https://gravatar.com/avatar/${userInfo.avatar}" alt="Gravatar">${userInfo.name}
                                            <span class=" fa fa-angle-down"></span>
                                        </a>
                                        <ul class="dropdown-menu dropdown-usermenu pull-right">
                                            <li><a href="javascript:;"> Profile</a></li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>Settings</span>
                                                </a>
                                            </li>
                                            <li><a href="javascript:;">Help</a></li>
                                            <li><a href="<c:url value="/logout"/>"><i class="fa fa-sign-out pull-right"></i> Log
                                                Out</a></li>
                                        </ul>
                                    </li>

                                    </ul>
                            </nav>
                        </div>

                    </div>