<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="col-lg-12 right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h2>Update Permission</h2>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-12">
				<div class="x_panel">
					<div class="x_content">
						<br />
						<form:form modelAttribute="modelForm"
							cssClass="form-horizontal form-label-left"
							servletRelativeAction="/menu/update-permission" method="POST">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-12"
									for="roleId">Role <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:select path="roleId" cssClass="form-control">
										<form:options items="${mapRole}" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-12"
									for="menuId">Menu <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:select path="menuId" cssClass="form-control">
										<form:options items="${mapMenu}" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label for="permission"
									class="control-label col-md-3 col-sm-3 col-12">Permission</label>
								<div class="col-md-6 col-sm-6 col-12">
									<div class="radio">
										<label> <form:radiobutton path="permission" disabled="${viewOnly}" value="1" />		Yes
										</label>
									</div>
									<div class="radio">
										<label> <form:radiobutton path="permission" disabled="${viewOnly}" value="0" />		No
										</label>
									</div>
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="row">
									<div class="col-md-6 col-sm-6 col-12 col-md-offset-3">
										<button class="btn btn-primary" type="button"
											onclick="cancel()">Cancel</button>
										<c:if test="${!viewOnly }">
											<button type="submit" class="btn btn-success">Submit
											</button>
										</c:if>
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
	$(document).ready(
			function() {
				$("#categoryListId").addClass("current-page").siblings()
						.removeClass("current-page");
				var parent = $("#categoryListId").siblings
						.removeClass("active");
				$("#categoryListId").parents().show();
			});
	function cancel() {
		window.location.href = "<c:url value="/menu/list"/>"
	}
</script>