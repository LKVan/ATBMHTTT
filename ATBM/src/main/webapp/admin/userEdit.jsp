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
<title>Edit user</title>
<link rel="canonical"
	href="https://www.wrappixel.com/templates/ample-admin-lite/" />
<!-- Favicon icon -->
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="./plugins/images/favicon.png">
<!-- Custom CSS -->
<link href="./admin/css/style.min.css" rel="stylesheet">
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
		<!-- ============================================================== -->
		<fmt:setLocale value="${sessionScope.langName}" />
		<fmt:setBundle basename="i18n.lang" var="lang" />
		<!-- ============================================================== -->

		<div class="page-wrapper">
			<div class="container-fluid">


				<div class="col-md-12">

					<div class="card">
						<h3 class="box-title text-uppercase text-center mb-3 mt-3">Chỉnh
							sửa người dùng</h3>
						<div class="card-body">
							<form class="form-horizontal form-material" action="manager_user"
								method="post">
								<div class="form-group mb-4">
									<label class="col-md-12 p-0">Mã</label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" class="form-control p-0 border-0" readonly
											value="${eUser.id}" name="userId">
									</div>
								</div>

								<div class="form-group mb-4">
									<label class="col-md-12 p-0">Họ và tên</label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" placeholder="Johnathan Doe"
											class="form-control p-0 border-0" value="${eUser.fullName}"
											name="userFullname">
									</div>
								</div>
								<div class="form-group mb-4">
									<label class="col-md-12 p-0">Số điện thoại</label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" class="form-control p-0 border-0"
											value="${eUser.numberPhone}" name="userPhone">
									</div>
								</div>

								<div class="form-group mb-4">
									<label class="col-md-12 p-0">Địa chỉ</label>
									<div class="col-md-12 border-bottom p-0">
										<textarea rows="5" class="form-control p-0 border-0"
											name="userAddress">${eUser.address}</textarea>
									</div>
								</div>

								<div class="form-group mb-4">
									<label for="example-email" class="col-md-12 p-0">Tài
										khoản</label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" class="form-control p-0 border-0"
											value="${eUser.userName}" name="userName" readonly="readonly">
									</div>
								</div>

								<!--  
								<div class="form-group mb-4">
									<label for="example-email" class="col-md-12 p-0">Mật khẩu</label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" placeholder="johnathan@admin.com"
											class="form-control p-0 border-0" 
											value="${eUser.password}" name="userPassword" >
									</div>
								</div>-->

								<div class="form-group mb-4">
									<label class="col-sm-12">Vai trò</label>

									<div class="col-sm-12 border-bottom">
										<select name="userRole"
											class="form-select shadow-none p-0 border-0 form-control-line">
											<option value=1
												<c:if test ="${eUser.rolId == 1 }">selected</c:if>>Admin</option>
											<option value=2
												<c:if test ="${eUser.rolId == 2 }">selected</c:if>>User</option>
										</select>
									</div>
								</div>
								<div class="form-group mb-4">
									<div class="col-sm-12">
										<button class="btn btn-success" name="updateUser"
											type="submit">Cập nhật</button>
										<a class="btn btn-primary"
											href="<%=request.getContextPath()%>/manager_user">Trở về</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- Column -->
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
</body>

</html>

