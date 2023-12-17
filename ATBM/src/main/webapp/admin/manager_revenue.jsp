<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Statistical Revenue</title>
<link rel="icon" type="image/png" sizes="16x16"
	href="./Image/favicon.png">
<!-- Custom CSS -->
<link href="./admin/css/style.min.css" rel="stylesheet" type="text/css">
<link href="./admin/css/bootstrap.min.css" type="text/css"
	rel="stylesheet">
<link href="./admin/css/jquery.dataTables.min.css" type="text/css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="../themify-icons/themify-icons.css">

<link href="./admin/css/main.css" rel="stylesheet">

</head>

<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>

	<fmt:setLocale value="${sessionScope.langName}" />
	<fmt:setBundle basename="i18n.lang" var="lang" />

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
				<!--<c:if test="${message != null}">
				<div class="alert alert-success"
					style="display: none">
					<b><fmt:message key="message.subccess"
													bundle="${lang }"></fmt:message></b>
				</div></c:if>-->
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-sm-12">
						<div class="white-box">
							<c:if test="${message != null }">
								<div class="alert alert-danger">Vui lòng chọn thời gian
									xem thống kê chính xác</div>
							</c:if>

							<h3 class="box-title text-uppercase text-center">Thống kê
								doanh thu</h3>


							<div class="table-responsive">
								<div>
									<a
										href="<%=request.getContextPath()%>/print?page=revenue&date_start=${date_start_revenue }&date_end=${date_end_revenue}"
										class="btn btn-success text-white mt-2 mb-2"
										style="text-align: end; margin-right: 20px;">In ra file
										Excel</a>

									<form action="${pageContext.request.contextPath}/statistical"
										method="GET" style="margin: 8px 20px 8px 0px;">
										<div style="display: flex; width: 100%; justify-content: end;"
											class="mb-3">
											<input type="hidden" value="revenue" name="page">
											<div id="date">
												<label>Từ:</label> <input type="date"
													name="date_start_revenue" value="${date_start_revenue}">
												<label>Đến:</label> <input type="date"
													name="date_end_revenue" value="${date_end_revenue}">
											</div>

											<button type="submit" class="btn btn-primary ml-2">Xem
												Thống Kê</button>
										</div>

									</form>
								</div>

								<table class="table text-nowrap" id="myTable">
									<thead>
										<tr>
											<th class="border-top-0">Thời gian</th>
											<th class="border-top-0">Số lượng sản phẩm bán ra</th>
											<th class="border-top-0">Tổng tiền bán ra</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="revenue" items="${listRevenue }">
											<tr>
												<td>${revenue.date }</td>
												<td style="padding: 0px 50px;">${revenue.quanlity}</td>
												<td style="padding: 0px 25px;">${revenue.totalPrice}</td>

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

	<!-- ============================================================== -->
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
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>



</body>

</html>