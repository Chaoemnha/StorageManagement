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
												<form:form modelAttribute="modelForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/category/save" method="POST">
                                                <form:hidden path="id"/>
                                                <form:hidden path="createDate"/>
                                                <form:hidden path="activeFlag"/>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="first-name">Code <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Code..." path="code" disabled="${viewOnly}"/>
									                        <div class="has-error">
									                        <form:errors path="code" cssClass="help-block"></form:errors></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="last-name">Name <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Name..." path="name" disabled="${viewOnly}"/>
									                        <div class="has-error">
									                        <form:errors path="name" cssClass="help-block"></form:errors></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label for="middle-name"
                                                               class="control-label col-md-3 col-sm-3 col-12">Description</label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Description..." path="description" disabled="${viewOnly}"/>
									                        <div class="has-error">
									                        <form:errors path="description" cssClass="help-block"></form:errors></div>
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
                    	$("#categoryListId").addClass("current-page").siblings().removeClass("current-page");
                    	var parent = $("#categoryListId").siblings.removeClass("active");
                    	$("#categoryListId").parents().show();
                    });
                    function cancel() {
						window.location.href="<c:url value="/category/list"/>"
					}
                    </script>