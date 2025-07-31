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
                                            
                                            <div class="clearfix"></div><a class="btn btn-app" href="<c:url value="/category/add"/>"><i class="fa fa-plus"></i>Add</a>
                                        </div>

                                        <div class="x_content">

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
                                                        <td class=" ">${loop.index+1 }</td>
                                                        <td class=" ">${category.id }</td>
                                                        <td class=" ">${category.code }</td>
                                                        <td class=" ">${category.name }</td>
                                                        <td class=" ">${category.description }</td>
                                                        
                                                        <td><a href="<c:url value="/category/view/${category.id }"/>" class="btn btn-round btn-info">View</a></td>
                                                        <td><a href="<c:url value="/category/edit/${category.id }"/>" class="btn btn-round btn-primary">Edit</a></td>
                                                        <td>
  <a href="javascript:void(0)" onclick="confirmDelete(${category.id})" class="btn btn-round btn-danger">
    Delete
  </a>
</td>
                                                        
                                                    </tr></c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>


                                        </div>
                                    </div>
                                
                        </div>
                    </div>
                    <script type="text/javascript">
  function confirmDelete(id) {
    if (confirm("Do you want to delete this record?")) {
      // Gọi đúng URL bằng cách gán trước base path bằng JSTL
      var baseUrl = '<c:url value="/category/delete/" />';
      window.location.href = baseUrl + id;
    }
  }
</script>