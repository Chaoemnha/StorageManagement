<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="col-lg-12 right_col" role="main">
                        <div class="">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2>Category List
                                            </h2>
                                            <div class="clearfix"></div><a class="btn btn-app" href="<c:url value="/product-info/add"/>"><i class="fa fa-plus"></i>Add</a>
                                        </div>

                                        <div class="x_content">
											<div class="container">
											<form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/category/list/1" method="POST">
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label for="middle-name"
                                                               class="control-label col-md-3 col-sm-3 col-12">ID</label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Id..." path="id"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="first-name">Code 
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Code..." path="code"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label col-md-3 col-sm-3 col-12"
                                                               for="last-name">Name 
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-12">
									                        <form:input type="text" class="form-control" placeholder="Name..." path="name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-md-6 col-sm-6 col-12 col-md-offset-3">
                                                            <button type="submit" class="btn btn-success">Submit
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
                                                        <th class="column-title">ID</th>
                                                        <th class="column-title">Code</th>
                                                        <th class="column-title">Name</th>
                                                        <th class="column-title">Description</th>
                                                        <th class="column-title no-link last text-center" colspan="3"><span
                                                                class="nobr">Action</span>
                                                        </th>
                                                    </tr>
                                                    </thead>

                                                    <tbody>
                                                    <c:forEach items="${categories}" var="category" varStatus="loop">
                                                    <c:choose>
                                                    <c:when test="${loop.index%2==0 }"><tr class="even pointer"></c:when>
                                                    <c:otherwise><tr class="odd pointer"></c:otherwise></c:choose>
                                                        <td class=" ">${pageInfo.getOffset()+loop.index+1 }</td>
                                                        <td class=" ">${category.id }</td>
                                                        <td class=" ">${category.code }</td>
                                                        <td class=" ">${category.name }</td>
                                                        <td class=" ">${category.description }</td>
                                                        
                                                        <td><a href="<c:url value="/product-info/view/${category.id }"/>" class="btn btn-round btn-info">View</a></td>
                                                        <td><a href="<c:url value="/product-info/edit/${category.id }"/>" class="btn btn-round btn-primary">Edit</a></td>
                                                        <td>
  <a href="javascript:void(0)" onclick="confirmDelete(${category.id})" class="btn btn-round btn-danger">
    Delete
  </a>
</td>
                                                        
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
      var baseUrl = '<c:url value="/product-info/delete/" />';
      window.location.href = baseUrl + id;
    }
  }
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