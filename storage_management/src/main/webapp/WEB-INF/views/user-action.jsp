<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="col-lg-12 right_col" role="main">
                        <div class="">
                            <div class="page-title">
                                <div class="title_left">
                                <h2>${titlePage }</h2>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-12">
                                    <div class="x_panel">
                                        <div class="x_content">
                                            <br/>
												<form:form modelAttribute="modelForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/user/save" method="POST">
                                                <form:hidden path="id"/>
                                                <form:hidden path="activeFlag"/>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="name">Full Name<span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Your Full Name..." path="name" disabled="${viewOnly}"/>
									                        <div class="has-error">
									                        <form:errors path="name" cssClass="help-block"></form:errors></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="email">Email <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="example@mail.com..." path="email" disabled="${viewOnly}"/>
									                        <div class="has-error">
									                        <form:errors path="email" cssClass="help-block"></form:errors></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="productId">Role <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
															<c:choose>
																<c:when test="${!viewOnly}">
																	<form:select path="roleId" cssClass="form-control">
																		<form:options items="${mapRole}"/>
																	</form:select>
																	<div class="has-error">
																		<form:errors path="roleId" cssClass="help-block"></form:errors>
																	</div>
																</c:when>
																<c:otherwise>
																	<form:input path="userRoles.role.roleName" disabled="true" cssClass="form-control"/>
																</c:otherwise>
															</c:choose>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label for="userName"
                                                               class="control-label col-md-3 col-sm-3 col-12">Username<span class="required">*</span></label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Your Username..." path="userName" disabled="${viewOnly}"/>
									                        <div class="has-error">
									                        <form:errors path="userName" cssClass="help-block"></form:errors></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label for="password"
                                                               class="control-label col-md-3 col-sm-3 col-12">Password<span class="required">*</span></label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="password" class="form-control" placeholder="Your password..." path="password" disabled="${viewOnly}"/>
									                        <div class="has-error">
									                        <form:errors path="password" cssClass="help-block"></form:errors></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="ln_solid"></div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-md-6 col-sm-6 col-12 col-md-offset-3">
                                                            <button class="btn btn-primary" type="button" onclick="cancel()">Cancel
                                                            </button>
                                                            <c:if test="${!viewOnly }">
                                                            <button class="btn btn-primary" type="reset">Reset</button>
                                                            <button type="submit" class="btn btn-success">Submit
                                                            </button></c:if>
                                                        </div>
                                                    </div>
                                                </div>

                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <script type="text/javascript">
                    $(document).ready(function(){
                    	$("#userListId").addClass("current-page").siblings().removeClass("current-page");
                    	var parent = $("#userListId").siblings.removeClass("active");
                    	$("#userListId").parents().show();
                    });
                    function cancel() {
						window.location.href="<c:url value="/user/list"/>"
					}
                    </script>