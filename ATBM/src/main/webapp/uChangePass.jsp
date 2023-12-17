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
<title>Edit Pass Word</title>
<link rel="canonical"
	href="https://www.wrappixel.com/templates/ample-admin-lite/" />
<!-- Favicon icon -->
<!-- Favicon icon -->

<!-- Custom CSS -->
<link href="admin/css/style.min.css" rel="stylesheet">
</head>

<body>

	<%
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		String rePass = request.getParameter("repass");
		if (oldPass == null)
			oldPass = "";
		if (newPass == null)
			newPass = "";
		if (rePass == null)
			rePass = "";
	%>

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
		<!-- ============================================================== -->
		<fmt:setLocale value="${sessionScope.langName}" />
		<fmt:setBundle basename="i18n.lang" var="lang" />
		<!-- ============================================================== -->

		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">

				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<form class="form-horizontal form-material"
								action="<%=request.getContextPath()%>/userChangePass"
								method="post">

								<div class="form-outline mt-3">
									<label class="form-label" for="form3Example4cg">Mật
										khẩu hiện tại(*):</label> <input type="password" class="form-control "
										name="oldPass" required value="<%=oldPass%>" />
								</div>
								<p style="color: red;">${messageOld}</p>

								<div class="form-outline mt-3">
									<label class="form-label" for="form3Example4cg">Mật
										khẩu mới(*):</label> <input type="password" class="form-control "
										name="newPass" required value="<%=newPass%>" />
								</div>

								<div class="form-outline mt-3">
									<label class="form-label" for="form3Example4cg">Nhập
										lại mật khẩu mới(*):</label> <input type="password"
										id="form3Example4cg" class="form-control " name="rePass"
										required value="<%=rePass%>" />
								</div>
								<p style="color: red;">${messageRe}</p>
								<div class="form-group mb-4">
									<div class="col-sm-12">
										<button class="btn btn-success" name="updateUser"
											type="submit">Cập nhật</button>
										<a class="btn btn-primary"
											href="<%=request.getContextPath()%>/HomeController">Quay
											lại</a>
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