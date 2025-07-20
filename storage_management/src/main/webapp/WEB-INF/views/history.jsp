<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="col-lg-12 right_col" role="main">
	<div class="">
		<div class="x_panel">
			<div class="x_title">
				<h2>History List</h2>
				<div class="clearfix"></div>
			</div>

			<div class="x_content">
				<div class="container">
					<form:form modelAttribute="searchForm"
						cssClass="form-horizontal form-label-left"
						servletRelativeAction="/product-in-stock/list/1" method="POST">
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-3 col-sm-3 col-12"
									for="code">Code </label>
								<div class="col-md-6 col-sm-6 col-12">
									<form:input type="text" class="form-control"
										placeholder="Code..." path="productInfo.code" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-3 col-sm-3 col-12"
									for="category">Category </label>
								<div class="col-md-6 col-sm-6 col-12">
									<form:input type="text" class="form-control"
										placeholder="Category name..." path="productInfo.category.name" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-3 col-sm-3 col-12"
									for="action">Action </label>
								<div class="col-md-6 col-sm-6 col-12">
									<form:input type="text" class="form-control"
										placeholder="Action name..." path="actionName" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-3 col-sm-3 col-12"
									for="type">Type </label>
								<div class="col-md-6 col-sm-6 col-12">
									<form:select path="type" class="form-control">
										<form:options items="${mapType }"/>
									</form:select>>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-6 col-sm-6 col-12 col-md-offset-3">
									<button type="submit" class="btn btn-success">Search</button>
								</div>
							</div>
						</div>

					</form:form>
				</div>
				<div class="table-responsive">
					<table class="table table-striped jambo_table bulk_action">
						<thead>
							<tr class="headings">
								<th class="column-title">#</th>
								<th class="column-title">Category</th>
								<th class="column-title">Code</th>
								<th class="column-title">Name</th>
								<th class="column-title">Qty</th>
								<th class="column-title">Price</th>
								<th class="column-title">Type</th>
								<th class="column-title">Action</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${histories}" var="history" varStatus="loop">
								<c:choose>
									<c:when test="${loop.index%2==0}">
										<tr class="even pointer">
									</c:when>
									<c:otherwise>
										<tr class="odd pointer">
									</c:otherwise>
								</c:choose>
								<td class=" ">${pageInfo.getOffset()+loop.index+1}</td>
								<td class=" ">${history.productInfo.category.name}</td>
								<td class=" ">${history.productInfo.code}</td>
								<td class=" ">${history.productInfo.name}</td>
								<td class=" ">${history.qty}</td>
								<td class=" ">${history.price}</td>
								<c:choose>
									<c:when test="${history.type==1}">
										<td>Goods Receipt</td>
									</c:when>
									<c:otherwise>
										<td>Goods Issues</td>
									</c:otherwise>
								</c:choose>
								<td>${history.actionName}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="./layout/paging.jsp"></jsp:include>
				</div>


			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
	function gotoPage(page){
		document.getElementById('searchForm').action = '<c:url value=""/>' + page;
		document.getElementById("searchForm").submit();
	}
  document.addEventListener("DOMContentLoaded", function () {
	    processMessage();
	});
    function processMessage() {
      var msgError = "${msgError}";
      var msgSuccess = "${msgSuccess}";
      if (msgSuccess) {
          new PNotify({
              title: "Success",
              text: msgSuccess,
              type: 'success',
              styling: 'bootstrap3'
          });
      }
      if (msgError) {
        new PNotify({
          title: "Error",
          text: msgError,
          type: 'error',
          styling: 'bootstrap3'
        });
      }
    }
</script>