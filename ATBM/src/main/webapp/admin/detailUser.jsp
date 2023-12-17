<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Detail User</title>

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
	<!-- ============================================================== -->
	<c:url value="/" var="contextPath" />
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>

	<%java.time.LocalDate local = java.time.LocalDate.now();
		int day = local.getDayOfMonth();
		int month = local.getMonthValue();
		int year = local.getYear();
	%>
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
					<div class="col-md-12">
						<div class="card">
							<div class="card-body">
								<form class="form-horizontal form-material">
									<div class="form-group mb-4">
										<div class="col-sm-12" align="right">
											<a class="btn btn-primary" href="${contextPath}/manager_user">Trở
												về</a>
										</div>
									</div>

									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Họ và tên</b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="text" placeholder="Johnathan Doe"
												class="form-control p-0 border-0" value="${eUser.fullName }"
												name="fullName" readonly>
										</div>
									</div>
									<div class="form-group mb-4">
										<label for="example-email" class="col-md-12 p-0"><b>Tài
												khoản</b></label>
										<div class="col-md-12 border-bottom p-0">
											<input class="form-control p-0 border-0" name="eUserName"
												value="${eUser.userName}" readonly>
										</div>
									</div>
									<!--  <div class="form-group mb-4">
                                        <label class="col-md-12 p-0"><b><fmt:message key="user.password"
										bundle="${lang }"></fmt:message></b></label>
                                        <div class="col-md-12 border-bottom p-0">
                                            <input type="password" value="${eUser.password}" class="form-control p-0 border-0" readonly>
                                        </div>
                                    </div>-->
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Số điện thoại</b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="text" placeholder="123 456 7890"
												class="form-control p-0 border-0" name="phone"
												value="${ eUser.numberPhone}" readonly>
										</div>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Email</b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="email" value="${eUser.email}"
												class="form-control p-0 border-0" readonly>
										</div>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Địa chỉ</b></label>
										<div class="col-md-12 border-bottom p-0">
											<textarea rows="5" class="form-control p-0 border-0" readonly>${eUser.address}</textarea>
										</div>
									</div>


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
</body>

</html>