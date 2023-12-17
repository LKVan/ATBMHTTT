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
<title>Edit</title>
<link rel="canonical"
	href="https://www.wrappixel.com/templates/ample-admin-lite/" />
<!-- Favicon icon -->
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="./plugins/images/favicon.png">
<!-- Custom CSS -->
<link href="./admin/css/style.min.css" rel="stylesheet">
<link href="./admin/css/main.css" rel="stylesheet">
</head>

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
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->

		<div class="page-wrapper">

			<div class="container-fluid">

				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<form class="form-horizontal form-material" action="edit_product"
								method="POST" enctype="multipart/form-data">
								<div class="form-group mb-4">
									<label class="col-md-12 p-0">Mã</label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" class="form-control p-0 border-0" readonly
											name="productId" value="${product.id}">
									</div>
								</div>

								<div class="form-group mb-4">
									<label class="col-md-12 p-0">Tên sản phẩm</label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" placeholder="Johnathan Doe"
											class="form-control p-0 border-0" name="productName"
											value="${product.name}" required="required">
									</div>
								</div>

								<div class="form-group mb-4">
									<label class="col-md-12 p-0">Hình sản phẩm</label>
									<div class="col-md-12 border-bottom p-0">
										<img src="${product.image}" style="width: 80px; height: 80px;"
											id="picture" name="picture"> <input type="hidden"
											value="${product.image}" name="productImage"> <input
											type="file" class="btn btn-info" name="uploadImage"
											placeholder="Chọn hình ảnh" id="uploadImage" accept="image/*">
										<p class="mt-1 text-danger">${imageError}</p>
									</div>
								</div>

								<div class="form-group mb-4">
									<label class="col-md-12 p-0">Mô tả</label>
									<div class="col-md-12 border-bottom p-0">
										<textarea rows="5" class="form-control p-0 border-0"
											name="productDes" required="required">${product.descreption}</textarea>
									</div>
								</div>

								<div class="form-group mb-4">
									<div class="form-group ">
										<label for="example-email" class="col-md-12 p-0">Thông
											số</label>
									</div>
									<div class="justify_bettwen ml-3">
										<div class="item-para">
											<label class="">Chiều cao</label>
											<div class="col-md-12">
												<input type="number" value="${product.height}" name="height"
													required="required"> cm
											</div>
										</div>

										<div class="item-para">
											<label class="">Chiều dài</label>
											<div class="col-md-12">
												<input type="number" value="${product.length}" name="length"
													required="required"> cm
											</div>
										</div>

										<div class="item-para">
											<label class="">Chiều rộng</label>
											<div class="col-md-12">
												<input type="number" value="${product.width}" name="width"
													required="required"> cm
											</div>
										</div>

										<div class="item-para">
											<label class="">Cân nặng</label>
											<div class="col-md-12">
												<input type="number" value="${product.weigth}" name="weigth"
													required="required"> gram
											</div>
										</div>
									</div>
								</div>

								<div class="form-group mb-4">
									<label for="example-email" class="col-md-12 p-0">Giá</label>
									<div class="col-md-12 border-bottom p-0">
										<input type="number" class="form-control p-0 border-0"
											name="productPrice" value="${product.price}"
											required="required">
									</div>
								</div>
								<div class="form-group mb-4">
									<label class="col-sm-12">Loại Sản Phẩm</label>

									<div class="col-sm-12 border-bottom">
										<select name="productKind"
											class="form-select shadow-none p-0 border-0 form-control-line">
											<c:forEach var="cate" items="${listCate}">
												<option value="${cate.id}"
													<c:if test ="${product.catId == cate.id }">selected</c:if>>${cate.name}</option>

											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group mb-4">
									<div class="col-sm-12">
										<button class="btn btn-success" type="submit">Cập
											nhật</button>
										<a class="btn btn-primary"
											href="<%=request.getContextPath()%>/manager_product?action=main">Trở
											về</a>
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

	<script>

  uploadImage.onchange = evt => {

            const [file] = uploadImage.files

            if (file) {

                picture.src = URL.createObjectURL(file);

                // $("#preview").removeClass("hidden");

            }

        }

    </script>
</body>

</html>

