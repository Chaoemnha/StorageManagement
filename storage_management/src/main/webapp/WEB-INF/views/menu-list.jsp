<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="col-lg-12 right_col" role="main">
	<div class="">
		<div class="x_panel">
			<div class="x_title">
				<h2>Menus List</h2>
				<div class="clearfix"></div>
				<a class="btn btn-app" href="<c:url value="/menu/permission"/>"><i
					class="fa fa-plus"></i>Permission</a>
			</div>
			<div class="x_content">
				<div class="container">
					<form:form modelAttribute="searchForm"
						cssClass="form-horizontal form-label-left"
						servletRelativeAction="/menu/list/1" method="POST">
						<div class="form-group">
							<div class="row">
								<label for="url" class="control-label col-md-3 col-sm-3 col-12">Url</label>
								<div class="col-md-6 col-sm-6 col-12">
									<form:input type="text" class="form-control"
										placeholder="/url/..." path="url" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-3 col-sm-3 col-12" for="name">Name
								</label>
								<div class="col-md-6 col-sm-6 col-12">
									<form:input type="text" class="form-control"
										placeholder="Name..." path="name" />
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
								<th rowspan="2" class="column-title align-middle">#</th>
								<th rowspan="2" class="column-title border-left align-middle">Url</th>
								<th rowspan="2" class="column-title border-left align-middle">Status</th>
								<th class="column-title border-left text-center" colspan="${roles.size()}">Role</th>
							</tr>
							<tr>
								<c:forEach var="role" items="${roles}">
									<th class="column-title border-left">${role.roleName}</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${menuList}" var="menu" varStatus="loop">
								<c:choose>
									<c:when test="${loop.index%2==0 }">
										<tr class="even pointer">
									</c:when>
									<c:otherwise>
										<tr class="odd pointer">
									</c:otherwise>
								</c:choose>
								<td class=" ">${pageInfo.getOffset()+loop.index+1 }</td>
								<td class=" ">${menu.url }</td>
								<c:choose>
									<c:when test="${menu.activeFlag == 1}">
										<td><a href="javascript:void(0);" onclick="confirmChange(${menu.id}, ${menu.activeFlag });" class="btn btn-round btn-success">Enable</a></td>
									</c:when>
									<c:otherwise>
										<td><a href="javascript:void(0);" onclick="confirmChange(${menu.id}, ${menu.activeFlag });" class="btn btn-round btn-danger">Disable</a></td>
									</c:otherwise>
								</c:choose>
								<c:forEach var="auth" items="${menu.mapAuth }">
									<c:choose>
										<c:when test="${auth.value==1 }">
											<td><i class="fa fa-check text-success"></i></td>
										</c:when>
										<c:otherwise>
											<td><i class="fa fa-times text-danger"></i></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
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
	function gotoPage(page) {
		document.getElementById('searchForm').action = '<c:url value=""/>'
				+ page;
		document.getElementById("searchForm").submit();
	}
	function confirmChange(id, flag){
		var msg = flag==1?'Do you want to disable this menu?':'Do you want to enable this menu?';
		if(confirm(msg)){
			window.location.href='<c:url value="/menu/change-status/"/>'+id;
			}
			}
	document.addEventListener("DOMContentLoaded", function() {
		processMessage();
	});
	function processMessage() {
		var msgError = "${msgError}";
		var msgSuccess = "${msgSuccess}";
		if (msgSuccess) {
			new PNotify({
				title : "Success",
				text : msgSuccess,
				type : 'success',
				styling : 'bootstrap3'
			});
		}
		if (msgError) {
			new PNotify({
				title : "Error",
				text : msgError,
				type : 'error',
				styling : 'bootstrap3'
			});
		}
	}
</script>