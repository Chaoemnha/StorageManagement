<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script><!-- 1000000 => 1,000,000 -->
<style>
.price {
	font-size: 14px;
}
</style>
<div class="col-lg-12 right_col" role="main">
	<div class="">
		<div class="x_panel">
			<div class="x_title">
				<h2>Goods Receipt</h2>
				<div class="clearfix"></div>
				<a class="btn btn-app" href="<c:url value="/goods-receipt/add"/>"><i
					class="fa fa-plus"></i>Add</a>
				<a class="btn btn-app" href="<c:url value="/goods-receipt/export"/>"><i
					class="fa fa-cloud-download"></i>Export</a>
			</div>

			<div class="x_content">
				<div class="container">
					<form:form modelAttribute="searchForm"
						cssClass="form-horizontal form-label-left"
						servletRelativeAction="/goods-receipt/list/1" method="POST">
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-3 col-sm-3 col-12" for="code">Code
								</label>
								<div class="col-md-6 col-sm-6 col-12">
									<form:input type="text" cssClass="form-control"
										placeholder="Code..." path="code" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-3 col-sm-3 col-12"
									for="fromDate">From Date</label>
								<div class="col-md-6 col-sm-6 col-12">
									<div class="input-group date" id='id_1'>
										<form:input path="fromDate" cssClass="form-control" id="fromDatePicker"/>
										<div class="input-group-addon input-group-append">
                                <div class="input-group-text">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                </div>
                            </div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-3 col-sm-3 col-12"
									for="toDate">To Date</label>
								<div class="col-md-6 col-sm-6 col-12">
									<div class="input-group date" id='id_5'>
										<form:input path="toDate" cssClass="form-control" id="toDatePicker"/>
										<div class="input-group-addon input-group-append">
                                <div class="input-group-text">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                </div>
                            </div>
									</div>
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
								<th class="column-title">Code</th>
								<th class="column-title">Qty</th>
								<th class="column-title">Price</th>
								<th class="column-title">Product</th>
								<th class="column-title">Update Date</th>
								<th class="column-title no-link last text-center" colspan="3"><span
									class="nobr">Action</span></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${invoices}" var="invoice"
								varStatus="loop">
								<c:choose>
									<c:when test="${loop.index%2==0}">
										<tr class="even pointer">
									</c:when>
									<c:otherwise>
										<tr class="odd pointer">
									</c:otherwise>
								</c:choose>
								<td class=" ">${pageInfo.getOffset()+loop.index+1}</td>
								<td class=" ">${invoice.code}</td>
								<td class=" ">${invoice.qty}</td>
								<!-- class price cho jquery thuc hien -->
								<td class="price">${invoice.price}</td>
								<td class=" ">${invoice.productInfo.name}</td>
								<td class="date">${invoice.updateDate}</td>

								<td><a
									href="<c:url value="/goods-receipt/view/${invoice.id }"/>"
									class="btn btn-round btn-info">View</a></td>
								<td><a
									href="<c:url value="/goods-receipt/edit/${invoice.id }"/>"
									class="btn btn-round btn-primary">Edit</a></td>
								<td><a href="javascript:void(0)"
									onclick="confirmDelete(${invoice.id})"
									class="btn btn-round btn-danger"> Delete </a></td>
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
  function confirmDelete(id) {
    if (confirm("Do you want to delete this record?")) {
      var baseUrl = '<c:url value="/goods-receipt/delete/" />';
      window.location.href = baseUrl + id;
    }
  }
	function gotoPage(page){
		$('#searchForm').attr('action', '<c:url value=""/>'+page);
		$('#searchForm').submit();
	}
	$(document).ready(function(){
		processMessage();
		$('#fromDatePicker').datetimepicker({
			format: 'YYYY-MM-DD HH:mm:ss'
		});
		$('#toDatePicker').datetimepicker({
			format: 'YYYY-MM-DD HH:mm:ss'
		});
		$('.price').each(function(){
			$(this).text(numeral($(this).text()).format('0,0'));
		})
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