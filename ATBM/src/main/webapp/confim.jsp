<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css"
	href="themify-icons/themify-icons.css">


<title>Xác thực</title>
</head>
<body>

	<section class="vh-100 bg-image"
		style="background-image: url('Image/img4.webp');">
		<div class="mask d-flex align-items-center h-100 gradient-custom-3">
			<div class="container h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-9 col-lg-7 col-xl-6">
						<div class="card" style="border-radius: 15px;">
							<a href="forget.jsp" class="p-3"><i class="ti-angle-left"></i>Quay
								về</a>

							<div class="card-body p-3">
								<form action="confim" method="get">
									<div class="form-outline text-center">
										<input type="hidden" value="${userName}" name="userName">
										<label class="form-label mr-b-50" for="form3Example1cg">Đã
											có mã xác thực gồm 4 chữ số gửi đến email ${endCodeEmail}. </br>Vui
											lòng kiểm tra và nhập mã xác thực.
										</label>

										<div id="label1" class="mr-b-50">
											<label>Còn lại</label> <span id="m"></span>p<span id="s"></span>s
										</div>
										<div id="label2" class="mr-b-50 non-display">
											<b>Không tìm thấy mã.</b> <a
												href="forget?userName=${userName}">Gửi lại mã</a>
										</div>

										<div class="row" style="display: table; margin: auto;">
											<input class="box-item" name="box1" type="text" maxlength="1" />
											<input class="box-item" name="box2" type="text" maxlength="1" />
											<input class="box-item" name="box3" type="text" maxlength="1" />
											<input class="box-item" name="box4" type="text" maxlength="1" />
										</div>
									</div>
									<p style="color: red; text-align: center;">${message}</p>

									<div class="d-flex justify-content-center mt-4">
										<button type="submit"
											class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Xác
											nhận</button>
									</div>

								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script type="text/javascript" src="js/popper.min.js"></script>
	<script type="text/javascript" src="js/jquery.slim.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		var m = 1; // Phút
		var s = 30; // Giây

		var timeout = null; // Timeout

		function start() {

			if (s === -1) {
				m -= 1;
				s = 59;
			}

			// Nếu số phút = -1 tức là đã chạy ngược hết số phút, lúc này:
			//  - giảm số giờ xuống 1 đơn vị
			//  - thiết lập số phút lại 59

			// Nếu số giờ = -1 tức là đã hết giờ, lúc này:
			//  - Dừng chương trình
			if (m == -1) {
				clearTimeout(timeout);
				document.getElementById('label1').classList.add('non-display');
				document.getElementById('label2').classList.remove('non-display');
				return false;
			}

			/*BƯỚC 1: HIỂN THỊ ĐỒNG HỒ*/
			document.getElementById('m').innerText = m.toString();
			document.getElementById('s').innerText = s.toString();

			/*BƯỚC 1: GIẢM PHÚT XUỐNG 1 GIÂY VÀ GỌI LẠI SAU 1 GIÂY */
			timeout = setTimeout(function() {
				s--;
				start();
			}, 1000);
		}
		start();

		function stop() {
			clearTimeout(timeout);
		}

	</script>