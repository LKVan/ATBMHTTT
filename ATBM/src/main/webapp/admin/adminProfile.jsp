<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8">

<title>Profile</title>

<link rel="icon" type="image/png" sizes="16x16"
	href="./Image/favicon.png">
<!-- Custom CSS -->
<link href="./css/style.min.css" rel="stylesheet">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/jquery.dataTables.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="../themify-icons/themify-icons.css">

</head>

<body>

	<%
	java.time.LocalDate local = java.time.LocalDate.now();
	int day = local.getDayOfMonth();
	int month = local.getMonthValue();
	int year = local.getYear();
	%>
	<jsp:include page="adminHeader.jsp"></jsp:include>

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
			<c:url value="/" var="contextPath" />
			<div class="container-fluid">

				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-body">
								<c:if test="${message != null }">
									<div class="alert alert-success">${message }</div>
								</c:if>
								<c:if test="${error != null }">
									<div class="alert alert-danger">${error}</div>
								</c:if>
								<form action="${contextPath}/change_admin" method="POST"
									class="form-horizontal form-material">
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Họ và tên </b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="text" placeholder="Johnathan Doe"
												class="form-control p-0 border-0" value="${user.fullName }"
												name="fullName" id="fullName" readonly>
										</div>
									</div>
									<div class="form-group mb-4">
										<label for="example-email" class="col-md-12 p-0"><b>Tài
												khoản</b></label>
										<div class="col-md-12 border-bottom p-0">
											<input class="form-control p-0 border-0" name="userName"
												value="${user.userName}" readonly id="userName">
										</div>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Mật khẩu</b></label>
										<div class="col-md-12 border-bottom p-0"
											style="display: inline-flex;">
											<input type="password" value="${user.password}" id="password"
												class="form-control p-0 border-0" readonly>
										</div>
									</div>


									<div class="form-group mb-4" id="hidenChangePass"
										style="display: none;">
										<label class="col-md-12 p-0"><b>Nhập mật khẩu</b></label>
										<div class="col-md-12 border-bottom p-0"
											style="display: inline-flex;">
											<input type="password" name="oldPass" id="oldPass"
												class="form-control p-0 border-0" onchange="checkPass()">

										</div>
										<label class="col-md-12 p-0" id="checkpass"><b></b></label> <label
											class="col-md-12 p-0"><b>Nhập mật khẩu mới</b></label>
										<div class="col-md-12 border-bottom p-0"
											style="display: inline-flex;">
											<input type="password" name="newPass" id="newPass"
												class="form-control p-0 border-0">
										</div>
										<label class="col-md-12 p-0" id="checNewPass"><b></b></label>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Số điện thoại</b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="text" placeholder="123 456 7890"
												class="form-control p-0 border-0" name="phone" id="phone"
												value="${ user.numberPhone}" readonly>
										</div>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Email</b></label>
										<div class="col-md-12 border-bottom p-0">
											<input type="email" value="${user.email}"
												class="form-control p-0 border-0" readonly id="email"
												name="email">
										</div>
									</div>
									<div class="form-group mb-4">
										<label class="col-md-12 p-0"><b>Địa chỉ</b></label>
										<div class="col-md-12 border-bottom p-0">
											<textarea rows="5" class="form-control p-0 border-0" readonly>${user.address}</textarea>
										</div>
									</div>
									<div id="update" style="display: none;">
										<button type="submit" class="btn btn-success" id="btn_update">Cập
											nhật</button>
										<a class="btn btn-info" href="${contextPath}/admin">Quay
											lại</a>
									</div>
								</form>
								<button class="btn btn-primary" id="editInfor">Sửa
									Thông tin</button>
							</div>
						</div>
					</div>
					<!-- Column -->
				</div>
				<!-- Row -->
			</div>
		</div>
		<!-- ============================================================== -->
		<!-- End Page wrapper  -->
		<!-- ============================================================== -->
	</div>
	<jsp:include page="adminFooter.html"></jsp:include>

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

<script type="text/javascript">

	var check = false;
	//function edit(){
		//check = true;
		//$('#fullName').attr('readonly', false); 
		//$('#phone').attr('readonly', false); 
		//$('#email').attr('readonly', false);
		
//	}
	
	$('#editInfor').click(function(){
		check = true;
		$('#fullName').attr('readonly', false); 
		$('#phone').attr('readonly', false); 
		$('#email').attr('readonly', false);
		$('#update').css("display","block");
		$('#editInfor').css('display',"none");
	})
	
	var show = true;
		$('#password').focus(function (){
			if(check){
				if(!show){
					$('#hidenChangePass').css('display','none');
					show = true;
				}else {
					$('#hidenChangePass').css('display','block');
					show = false;
				}
			}
		});
		
		var check_pass = false;
		
		function checkPass(){
			var password = $('#oldPass').val();
			var userName = $('#userName').val();
			$.ajax({
				type: "POST",
				url : "<%=request.getContextPath()%>/check_pass",
				data:{
					userName:userName,
					pass:password
				},
				success: function(data){
					if(data == "subcess"){
						$('#checkpass').text("Mật khẩu chính xác");
						$('#checkpass').css("color","green");
						$('#btn_update').removeAttr('disabled');
						$('#newPass').focus();
						$('#btn_update').attr('disabled','disabled');
						
						$('#checNewPass').css("color","red");
						$('#checNewPass').text('Vui lòng nhập mật khẩu mới');
					}else if( data == "error"){
						$('#checkpass').text("Mật khẩu không chính xác");
						$('#checkpass').css("color","red");
						$('#btn_update').attr('disabled','disabled');
					}
				}
			});
		}
		
		$('#newPass').change(function(){
			var value = $('#newPass').val();
				
			if(value == ""){
				$('#checNewPass').css("color","red");
				$('#checNewPass').text('Vui lòng nhập mật khẩu mới');
				$('#btn_update').attr('disabled','disabled');
			}else if(value.length < 10){
				$('#checNewPass').css("color","red");
				$('#checNewPass').text('Vui lòng nhập mật khẩu dài hơn 10 kí tự');
				$('#btn_update').attr('disabled','disabled');
			}else {
				$('#btn_update').removeAttr('disabled');
				$('#checNewPass').text("");
				$('#checNewPass').css("color","none");
			}
		})
			
</script>


</html>