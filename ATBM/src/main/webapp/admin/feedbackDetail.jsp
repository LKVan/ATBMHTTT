<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Detail Feedback</title>
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
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>

	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<c:url value="/" var="contextPath" />

		<div class="page-wrapper">

			<div class="container-fluid">


				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<h3 class="text-center">Chi tiết phản hồi</h3>
							<div class="form-group mb-4">
								<div class="col-sm-12" align="right">
									<a class="btn btn-primary"
										href="${contextPath}/ManagerFeedback"><b>Trở về</b></a>
								</div>
							</div>

							<div class="form-group mb-4">
								<label class="col-md-12 p-0"><b>Mã Phản Hồi</b></label>
								<div class="col-md-12 border-bottom p-0">
									<input type="text" class="form-control p-3 border-0" readonly
										value="${feed.id}">
								</div>
							</div>

							<div class="form-group mb-4">
								<label class="col-md-12 p-0"><b>Họ và tên</b></label>
								<div class="col-md-12 border-bottom p-0">
									<input type="text" placeholder="Johnathan Doe"
										class="form-control p-3 border-0" value="${feed.fullName}"
										name="userFullname" readonly>
								</div>
							</div>
							<div class="form-group mb-4">
								<label class="col-md-12 p-0"><b>Số điện thoại</b></label>
								<div class="col-md-12 border-bottom p-0">
									<input type="text" class="form-control p-3 border-0"
										value="${feed.phone}" name="userPhone" readonly>
								</div>
							</div>

							<div class="form-group mb-4">
								<label class="col-md-12 p-0"><b>Tiêu đề</b></label>
								<div class="col-md-12 border-bottom p-0">
									<input type="text" class="form-control p-3 border-0"
										value="${feed.title}" name="userPhone" readonly>
								</div>
							</div>

							<div class="form-group mb-4">
								<label class="col-md-12 p-0"><b>Nội Dung</b></label>
								<div class="col-md-12 border-bottom p-0">
									<textarea rows="5" class="form-control p-3 border-0"
										name="userAddress" readonly>${feed.descreption}</textarea>
								</div>
							</div>



						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Column -->
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
</body>

</html>