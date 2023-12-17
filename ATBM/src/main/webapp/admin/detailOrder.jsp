<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Order Detail</title>

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
	<fmt:setLocale value="${sessionScope.langName}" />
	<fmt:setBundle basename="i18n.lang" var="lang" />
	<!-- ============================================================== -->
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<c:url value="/" var="contextPath" />
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
								<div class="alert alert-success">Xoá thành công</div>
							</c:if>
							<a href="${contextPath}/order?action=${previous }"
								class="btn btn-primary">Quay về</a>
							<h3 class="box-title text-uppercase text-center mb-3">Chi
								tiết đơn hàng</h3>

							<h5>
								<b>Mã đơn:</b> #${order.orderId }
							</h5>
							<h5>
								<b>Ngày đặt:</b> ${order.date }
							</h5>
							<h5>
								<b>Tài khoản đặt:</b> ${order.userName }
							</h5>
							<h5>
								<b>Trình trạng:</b>
								<c:if test="${order.status == 0}">
									<label class="badge badge-danger">Đơn hàng đã huỷ</label>
								</c:if>
								<c:if test="${order.status == 1}">
									<label class="badge badge-danger">Chờ xác nhận</label>
								</c:if>
								<c:if test="${order.status == 2}">
									<label class="badge badge badge-primary">Chờ vận chuyển</label>
								</c:if>
								<c:if test="${order.status == 3}">
									<label class="badge badge-info">Đang vận chuyển</label>
								</c:if>
								<c:if test="${order.status == 4}">
									<label class="badge badge-success">Đã giao</label>
								</c:if>
							</h5>
							<h5>
								<b>Phí vận chuyển:</b> ${priceTransport } VND
							</h5>

							<div class="table-responsive">
								<table class="table text-nowrap" id="myTable">
									<thead>
										<tr>
											<th class="border-top-0">Tên sản phẩm</th>
											<th class="border-top-0">Hình sản phẩm</th>
											<th class="border-top-0">Giá</th>

											<th class="border-top-0">Số lượng</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="orderItem" items="${order.listOrderItem}">
											<tr>
												<td>${orderItem.getProduct().getName()}</td>
												<td><img src="${orderItem.getProduct().getImage()}"
													style="width: 80px; height: 80px;"></td>
												<td>${orderItem.getProduct().formatPrice() }</td>
												<td>${orderItem.getQuanlity() }</td>
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