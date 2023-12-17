<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="model.User"%>

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
	href="admin/plugins/images/favicon.png">
<!-- Custom CSS -->
<link href="admin/css/style.min.css" rel="stylesheet">
</head>

<body>
<body>
	<%
		User user = (User) session.getAttribute("user");
		String userFullname = request.getParameter("userFullname");
		String userPhone = request.getParameter("userPhone");
		String userAddress = user.getAddress();
		String addressDetail = request.getParameter("addressDetail");

		String[] address = userAddress.split(",");
		String detail = address[0];
		String addressWard = address[1];
		String addressDistrict = address[2];
		String addressCity = address[3];


		if (userFullname == null)
			userFullname = "";
		if (userPhone == null)
			userPhone = "";
		if (addressDetail == null)
			addressDetail = "";
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
								action="<%=request.getContextPath()%>/changeInfor" method="post">

								<div class="form-group mb-4">
									<label class="col-md-12 p-0"><b>Họ và tên </b></label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" class="form-control p-0 border-0"
											value="${user.fullName}" name="userFullname">
									</div>
								</div>

								<div class="form-group mb-4">
									<label class="col-md-12 p-0"><b>Số điện thoại </b></label>
									<div class="col-md-12 border-bottom p-0">
										<input type="text" class="form-control p-0 border-0"
											value="${user.numberPhone}" name="userPhone">
									</div>
								</div>

								<div class="form-group">
									<label for="sel1"><b>Tỉnh/Thành Phố: </b></label> <select
										class="form-control" id="city" name="province"
										required="required">
										<option value="" selected><%=addressCity%></option>
									</select>
								</div>

								<div class="form-group">
									<label><b>Quận/Huyện: </b></label> <select class="form-control"
										id="district" name="district" required="required">
										<option value="" selected><%=addressDistrict%></option>
									</select>
								</div>

								<div class="form-group">
									<label><b>Phường/Xã: </b></label> <select class="form-control"
										id="ward" name="ward" required="required">
										<option value="" selected><%=addressWard%></option>
									</select>
								</div>

								<label class="form-label" for="form3Example4cdg"><b>Địa
										chỉ chi tiết: </b></label>
								<textarea rows=2 id="form3Example4cdg" class="form-control"
									name="addressDetail"><%=detail%></textarea>

								<div class="form-group mb-4">
									<div class="col-sm-12">
										<button type="submit" name="updateUser"
											class="btn btn-success" id="btn_update">Cập nhật</button>
										<a class="btn btn-primary"
											href="<%=request.getContextPath()%>/changeInfor">Quay lại</a>
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



	<script>
	var citis = document.getElementById("city");
var districts = document.getElementById("district");
var wards = document.getElementById("ward");
var Parameter = {
  url: "data/data.json",
  method: "GET",
  responseType: "application/json",
};
var promise = axios(Parameter);
promise.then(function (result) {
  renderCity(result.data);
});

function renderCity(data) {
  for (const x of data) {
    citis.options[citis.options.length] = new Option(x.Name, x.Id);

  }
  citis.onchange = function () {
    district.length = 1;
    ward.length = 1;
    if(this.value != ""){
      const result = data.filter(n => n.Id === this.value);

      for (const k of result[0].Districts) {
        district.options[district.options.length] = new Option(k.Name, k.Id);
      }
    }
  };
  district.onchange = function () {
    ward.length = 1;
    const dataCity = data.filter((n) => n.Id === citis.value);
    if (this.value != "") {
      const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;

      for (const w of dataWards) {
        wards.options[wards.options.length] = new Option(w.Name, w.Id);
      }
    }
  };
}
	</script>
</body>

</html>