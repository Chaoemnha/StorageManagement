<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                                            <h2>Product In Storage List
                                            </h2>
                                            <div class="clearfix"></div>
                                        </div>

                                        <div class="x_content">
											<div class="container">
											<form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/product-in-stock/list/1" method="POST">
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="first-name">Code 
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Code..." path="productInfo.code"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="last-name">Category 
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Category name..." path="productInfo.category.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="last-name">Name 
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Name..." path="productInfo.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-md-6 col-sm-6 col-12 col-md-offset-3">
                                                            <button type="submit" class="btn btn-success">Search
                                                            </button>
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
                                                        <th class="column-title">Image</th>
                                                        <th class="column-title">Quantity</th>
                                                        <th class="column-title">Price</th>
                                                    </tr>
                                                    </thead>

                                                    <tbody>
                                                    <c:forEach items="${products}" var="product" varStatus="loop">
                                                    <c:choose>
                                                    <c:when test="${loop.index%2==0 }"><tr class="even pointer"></c:when>
                                                    <c:otherwise><tr class="odd pointer"></c:otherwise></c:choose>
                                                        <td class=" ">${pageInfo.getOffset()+loop.index+1 }</td>
                                                        <td class=" ">${product.productInfo.category.name }</td>
                                                        <td class=" ">${product.productInfo.code }</td>
                                                        <td class=" ">${product.productInfo.name }</td>
                                                        <td class=" "><img src='<c:url value="${product.productInfo.imgUrl}"></c:url>' width="100px" height="100px"></td>
                                                        <td class=" ">${product.qty }</td>
                                                        <td class="price">${product.price }</td>
                                                    </tr></c:forEach>
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
      var baseUrl = '<c:url value="/category/delete/" />';
      window.location.href = baseUrl + id;
    }
  }
	function gotoPage(page){
		document.getElementById('searchForm').action = '<c:url value=""/>' + page;
		document.getElementById("searchForm").submit();
	}
	$(document).ready(function(){
		processMessage();
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