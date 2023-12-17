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
<link rel="stylesheet" type="text/css"
	href="themify-icons/themify-icons.css">


<title>Quên mật khẩu</title>
</head>
<body>
	<% String userName = request.getParameter("userName"); 
		if(userName == null) userName ="";
		
		String password = request.getParameter("password");
		if(password == null) password ="";
		
		String repass = request.getParameter("repass");
		if(repass == null) repass ="";

	
	%>

	<section class="vh-100 bg-image"
		style="background-image: url('Image/img4.webp');">
		<div class="mask d-flex align-items-center h-100 gradient-custom-3">
			<div class="container h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-9 col-lg-7 col-xl-6">
						<div class="card" style="border-radius: 15px;">
							<c:if test="${check == null }">
								<a href="login.jsp" class="p-3"><i class="ti-angle-left"></i>Quay
									về</a>
							</c:if>
							<div class="card-body p-3">
								<c:if test="${check == null }">
									<h2 class="text-uppercase text-center mb-5">Quên Mật Khẩu</h2>
									<form action="forget" method="get">
										<div class="form-outline">
											<label class="form-label" for="form3Example1cg">Nhập
												Tài khoản(*):</label> <input type="text" class="form-control "
												required name="userName" value="<%=userName %>" />
										</div>
										<p style="color: red;">${message}</p>

										<div class="d-flex justify-content-center mt-4">
											<button type="submit"
												class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Xác
												nhận</button>
										</div>

									</form>
								</c:if>

								<c:if test="${check != null }">
									<h2 class="text-uppercase text-center mb-5">Đổi mật khẩu</h2>
									<form action="change" method="post">
										<input type="hidden" value="${userName}" name="userName">
										<div class="form-outline">
											<label class="form-label" for="form3Example1cg">Nhập
												mật khẩu mới(*):</label> <input type="password"
												class="form-control " required name="password"
												value=<%=password %> />
										</div>
										<div class="form-outline">
											<label class="form-label" for="form3Example1cg">Nhập
												lại mật khẩu (*):</label> <input type="password"
												class="form-control " required name="repass"
												value=<%=repass %> />
										</div>
										<p style="color: red;">${message}</p>

										<div class="d-flex justify-content-center mt-4">
											<button type="submit"
												class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Xác
												nhận</button>
										</div>

									</form>
								</c:if>

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


</body>
</html>