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
	href="./themify-icons/themify-icons.css">
<style type="text/css">
.container {
	border: 2px solid #dedede;
	background-color: #f1f1f1;
	border-radius: 5px;
	padding: 10px;
	margin: 10px 0;
}

/* Darker chat container */
.darker {
	border-color: #ccc;
	background-color: #ddd;
}

.warp {
	max-height: 300px;
	overflow-y: scroll;
}

#style-1::-webkit-scrollbar-track {
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
	background-color: #F5F5F5;
}

/* Clear floats */
.container::after {
	content: "";
	clear: both;
	display: table;
}

/* Style images */
.container img {
	float: left;
	max-width: 60px;
	width: 100%;
	margin-right: 20px;
	border-radius: 50%;
}

.container p {
	margin-left: 20px;
}

/* Style the right image */
.container img.right {
	float: right;
	margin-left: 20px;
	margin-right: 0;
}

/* Style time text */
.time-right {
	float: right;
	color: #aaa;
}

/* Style time text */
.time-left {
	float: left;
	color: #999;
}
</style>

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

							<a
								href="<%=request.getContextPath()%>/manager_product?action=main"
								class="btn btn-primary">Quay về</a>
							<h3 class="box-title text-uppercase text-center mb-3">Đánh
								giá sản phẩm</h3>

							<h5>
								<b>Mã sản phẩm:</b> #${product.id }
							</h5>
							<h5>
								<b>Tên sản phẩm :</b> ${product.name }
							</h5>
							<h5>
								<b>Hình ảnh:</b> <img src="${product.image}"
									style="width: 80px; height: 80px;">
							</h5>


							<div class="table-responsive">
								<h4 class="box-title text-uppercase text-center mb-3">Danh
									sách đánh giá</h4>
								<div class="warp" id="style-1">
									<c:forEach var="item" items="${listComment}">



										<div class="container">
											<label>${item.name}</label>
											<p>${item.comment }?</p>
											<span class="time-right">${item.time }</span>
										</div>


									</c:forEach>
								</div>
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