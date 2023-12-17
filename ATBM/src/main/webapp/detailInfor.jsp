<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Profile</title>

<link rel="icon" type="image/png" sizes="16x16"
	href="./admin/Image/favicon.png">
<!-- Custom CSS -->
<link href="admin/css/style.min.css" rel="stylesheet">
<link href="admin/css/bootstrap.min.css" rel="stylesheet">
<link href="admin/css/jquery.dataTables.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="themify-icons/themify-icons.css">

</head>

<body>

	<%
	java.time.LocalDate local = java.time.LocalDate.now();
	int day = local.getDayOfMonth();
	int month = local.getMonthValue();
	int year = local.getYear();
	%>
	<!-- ============================================================== -->
	<fmt:setLocale value="${sessionScope.langName}" />
	<fmt:setBundle basename="i18n.lang" var="lang" />
	<!-- ============================================================== -->
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<div class="page-wrapper">

			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<!-- Row -->
				<div class="row">
					<!-- Column -->

					<!-- Column -->
					<!-- Column -->
					<div class="col-lg-8 col-xlg-9 col-md-12">
						<div class="card">
							<div class="card-body">
								<form class="form-horizontal form-material">
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Họ và tên </b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="text" class="form-control p-0 border-0"
												value="${user.fullName }" name="fullName" readonly>
										</div>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Số điện thoại </b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="text" class="form-control p-0 border-0"
												name="phone" value="${ user.numberPhone}" readonly>
										</div>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Email </b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="email" value="${user.email}"
												class="form-control p-0 border-0" readonly>
										</div>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Địa chỉ </b></label>
										<div class="col-md-12 border-bottom p-0">
											<textarea rows="5" class="form-control p-0 border-0" readonly>${user.address}</textarea>
										</div>
									</div>

									<a
										href="<%=request.getContextPath()%>/changeInfor?action=edit&eUserId=${user.id}"
										class="btn btn-primary">Chỉnh sửa</a> <a class="btn btn-info"
										href="HomeController">Quay lại</a>
								</form>
							</div>
						</div>
					</div>
					<!-- Column -->
				</div>
				<!-- Row -->
			</div>
		</div>
		<!-- ============================================================== -->
		<!-- End Page wrapper  -->
		<!-- ============================================================== -->
	</div>

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
</body>

</html>