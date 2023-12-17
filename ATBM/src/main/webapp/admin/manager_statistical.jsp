<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Statistical Product</title>
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
								sản phẩm bán ra</h3>


							<div class="table-responsive">
								<div
									style="display: flex; width: 100%; justify-content: space-between;">
									<a
										href="<%=request.getContextPath()%>/print?page=sta_product&date_start=${date_start }&date_end=${date_end}"
										class="btn btn-success text-white mt-2 mb-2"
										style="text-align: end; margin-right: 20px;">In ra file
										Excel</a>

									<form action="statistical" method="GET"
										style="margin: 8px 20px 8px 0px;">
										<label>Từ:</label> <input type="date" name="date_start"
											value="${date_start }"> <label>Đến:</label> <input
											type="date" name="date_end" value="${date_end}"> <input
											type="hidden" value="sta_product" name="page">
										<button type="submit" class="btn btn-primary ml-2">Xem
											Thống Kê</button>
									</form>
								</div>

								<table class="table text-nowrap" id="myTable">
									<thead>
										<tr>
											<th class="border-top-0">Mã</th>
											<th class="border-top-0">Tên sản phẩm</th>
											<th class="border-top-0">Số lượng bán ra</th>
											<th class="border-top-0">Tổng giá trị bán ra</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="product" items="${listProductReport }">
											<tr>
												<td>${product.id }</td>
												<td>${product.name }</td>
												<td style="padding: 0px 50px;">${product.quanliSoldOut}</td>
												<td style="padding: 0px 25px;">${product.price}</td>

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
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>
	<script>
		function changeStatus(proId){
			$.ajax({
				url : "<%=request.getContextPath()%>/change_status",
				data:{
					id:proId,
					status:1
				},
				success: function(data){
					
				}
			});
		}
	</script>



</body>

</html>