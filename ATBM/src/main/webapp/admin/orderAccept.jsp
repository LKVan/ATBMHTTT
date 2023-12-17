<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Order Accept</title>

<link rel="icon" type="image/png" sizes="16x16"
	href="./Image/favicon.png">
<!-- Custom CSS -->
<link href="./admin/css/style.min.css" rel="stylesheet">
<link href="./admin/css/bootstrap.min.css" rel="stylesheet">
<link href="./admin/css/jquery.dataTables.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="./themify-icons/themify-icons.css">

</head>

<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>
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
				<div class="row">
					<div class="col-sm-12">
						<div class="white-box">
							<c:if test="${access != null }">
								<div class="alert alert-success">Xác nhận thành công</div>
							</c:if>
							<c:if test="${delete != null }">
								<div class="alert alert-success">Huỷ đơn thành công</div>
							</c:if>
							<h3 class="box-title text-uppercase text-center mb-3">Đơn
								hàng chờ xác nhận</h3>
							<div class="alert alert-success"
								<c:if test="${ sucess ==null}">style="display: none"</c:if>>
								<b>${success}</b>
							</div>
							<div class="table-responsive">
								<table class="table text-nowrap" id="myTable">
									<thead>
										<tr>
											<th class="border-top-0">Mã đơn hàng</th>
											<th class="border-top-0">Tài khoản</th>
											<th class="border-top-0">Ngày Đặt</th>

											<th class="border-top-0">Chi tiết</th>

											<th class="border-top-0">Trình trạng</th>
											<th class="border-top-0">Chức năng</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="o" items="${listOrder}">
											<tr>
												<td>#${o.orderId}</td>
												<td>${o.userName }</td>
												<td>${o.date }</td>
												<td><a
													href="<%=request.getContextPath()%>/order?action=detail&orderId=${o.orderId }&previous=accept"
													class="btn btn-primary"><i class="ti-eye" title="Xem"></i></a></td>
												<td><span class="badge badge-danger">Chờ xác
														nhận</span></td>
												<td><a
													href="<%=request.getContextPath()%>/oderAction?action=confim&orderID=${o.orderId}"
													class="btn btn-primary">Xác nhận</a> <a
													href="<%=request.getContextPath()%>/oderAction?action=delete&orderID=${o.orderId}"
													class="btn btn-info btn-danger text-white">Huỷ</a></td>

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