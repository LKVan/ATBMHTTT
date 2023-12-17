<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Manager Feedback</title>

<link rel="icon" type="image/png" sizes="16x16"
	href="./Image/favicon.png">
<!-- Custom CSS -->
<link href="./admin/css/style.min.css" rel="stylesheet">
<link href="./admin/css/bootstrap.min.css" rel="stylesheet">
<link href="./admin/css/jquery.dataTables.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="../themify-icons/themify-icons.css">

</head>

<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<c:url value="/" var="contextPath" />
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>

	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<div class="page-wrapper">

			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">

				<div class="alert alert-success"
					<c:if test="${ sucess ==null}">style="display: none"</c:if>>
					<b>${success}</b>
				</div>
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-sm-12">
						<div class="white-box">
							<h3 class="box-title text-uppercase text-center mb-3">Quản
								lí phản hồi</h3>
							<div class="table-responsive">
								<table class="table text-nowrap" id="myTable">
									<thead>
										<tr>
											<th class="border-top-0">Mã phản hồi</th>
											<th class="border-top-0">Họ và tên</th>
											<th class="border-top-0">Số điện thoại</th>
											<th class="border-top-0">Tiêu đề</th>
											<th class="border-top-0">Chức năng</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="feed" items="${listFeedback}">
											<tr>
												<td>#${feed.id}</td>
												<td>${feed.fullName }</td>
												<td>${feed.phone }</td>
												<td>${feed.title }</td>
												<td><a
													href="${contextPath}/ManagerFeedback?action=show&feedId=${feed.id}"
													class="btn btn-primary" title="Xem"><i class="ti-eye"></i></a>
													<a
													href="${contextPath}/ManagerFeedback?action=trash&feedId=${feed.id}"
													class="btn btn-danger text-white" title="Xoá"><i
														class="ti-trash"></i></a></td>

											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="adminFooter.html"></jsp:include>

	<script src="./admin/js/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="./admin/js/bootstrap.bundle.min.js"></script>
	<script src="./admin/js/app-style-switcher.js"></script>
	<!--Wave Effects -->
	<script src="./admin/js/waves.js"></script>
	<!--Menu sidebar -->
	<script src="./admin/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script src="./admin/js/custom.js"></script>
	<script src="./admin/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>
</body>

</html>