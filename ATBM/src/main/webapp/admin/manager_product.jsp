<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Manager Product</title>
<link rel="icon" type="image/png" sizes="16x16"
	href="./Image/favicon.png">
<!-- Custom CSS -->
<%--<link href="./css/style.min.css" rel="stylesheet">--%>
<link href="./admin/css/bootstrap.min.css" rel="stylesheet">
<link href="./admin/css/jquery.dataTables.min.css" rel="stylesheet">
<link href="./admin/css/main.css" rel="stylesheet">

<%--<link rel="stylesheet" type="text/css"--%>
<%--	href="../themify-icons/themify-icons.css">--%>
<%--</head>--%>
<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>

	<fmt:setLocale value="${sessionScope.langName}" />
	<fmt:setBundle basename="i18n.lang" var="lang" />

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
				<!--<c:if test="${message != null}">
				<div class="alert alert-success"
					style="display: none">
					<b>Thêm thành công</b>
				</div></c:if>-->

				<div class="row">
					<div class="col-sm-12">
						<div class="white-box">
							<c:if test="${access != null }">
								<div class="alert alert-success">Thêm thành công</div>
							</c:if>
							<c:if test="${update != null }">
								<div class="alert alert-success">Thay đổi thành công</div>
							</c:if>

							<h3 class="box-title text-uppercase text-center">Quản lí sản
								phẩm</h3>
							<!--  <a href="/Project_CK_LTWEB/manager_product?action=add"
								class="btn btn-success text-white mt-2 mb-2"
								style="text-align: end; margin-right: 20px;"><fmt:message
									key="product.add" bundle="${lang }"></fmt:message></a>-->

							<div class="justify_bettwen">
								<a href="<%=request.getContextPath()%>/add_product"
									class="btn btn-success text-white mt-2 mb-2"
									style="text-align: end; margin-right: 20px;">Thêm sản phẩm</a>

								<a
									href="<%=request.getContextPath()%>/manager_product?action=trash"
									class="btn btn-danger text-white mt-2 mb-2"
									style="text-align: end; margin-right: 20px;">Thùng rác</a>
							</div>
							<div class="table-responsive">
								<table class="table text-nowrap" id="myTable">
									<thead>
										<tr>
											<th class="border-top-0">Mã sản phẩm</th>
											<th class="border-top-0">Tên sản phẩm</th>
											<th class="border-top-0">Hình</th>
											<th class="border-top-0">Giá</th>
											<th class="border-top-0">Loại</th>
											<th class="border-top-0">Chức năng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="product" items="${listProduct }">
											<tr>
												<td>#${product.id }</td>
												<td>${product.name }</td>
												<td><img src="${product.image}"
													style="width: 80px; height: 80px;"></td>
												<td>${product.formatPrice() }VND</td>
												<td>
													<!--<c:if test="${product.catId == 1 }">Đồ ăn vặt</c:if>
													<c:if test="${product.catId == 2 }">Đồ ăn</c:if> <c:if
														test="${product.catId == 3 }">Nước Uống</c:if></td> -->
													${categories.get(product.catId-1).getName()}
												</td>
												<td>
													<!-- <a
													href="/Project_CK_LTWEB/manager_product?action=edit&proId=${product.id }"
													class="btn btn-primary"><i class="ti-pencil-alt"></i></a>  -->
													<a
													href="<%=request.getContextPath()%>/edit_product?proId=${product.id}"
													class="btn btn-primary" title="Sửa"><i
														class="ti-pencil-alt"></i></a> <a
													href="<%=request.getContextPath()%>/manager_product?action=comment&proId=${product.id}"
													class="btn btn-info" title="Xem đánh giá"><i
														class="ti-star"></i></a>


													<button onclick="changeStatus(this,${product.id})"
														class="btn btn-danger text-white" title="Xoá">
														<i class="ti-trash"></i>
													</button>
												</td>

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

	<div>
		<div id="toast_message"></div>
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
		function changeStatus(element,proId){
			element.closest("tr").classList.add("delete");
			$.ajax({
				url : "<%=request.getContextPath()%>/change_status",
				type:"POST",
				data:{
					id:proId,
					status:1
				},
				success: function(data){
					var table = $('#myTable').DataTable();
					var current = table.page.info().page;
					console.log(current);
					var rows = table
					    .rows( '.delete' )
					    .remove()
					    .page(current)
					    .draw('page');
					toast();
				
				}
			});
			

			
			
		}
		
		function toast(){
			const main = document.getElementById('toast_message');
			if(main){
				const toast = document.createElement('div');
				toast.classList.add('toast-item');
				toast.style.animation = ` fadeIn ease 0.3s,fadeOut linear 1s 2s forwards`;
				toast.innerHTML =`
					<div class="toast__icon"><i class="ti-check icon-subccess"></i></div>
					<div class="toast__body">
						<h3 class="toast__title">Success</h3>
						<p class="toast__msg">Xoá thành công</p>
					</div>
					<div class="toast__close"><i class="ti-close"></i></div>
				`;
				main.appendChild(toast);
				setTimeout(() => {
					main.removeChild(toast);
				}, 2000);
			}
		}
		
		
	</script>



</body>

</html>