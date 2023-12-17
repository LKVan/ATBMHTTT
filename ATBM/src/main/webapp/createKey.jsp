<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Signature...</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<%
	String userName = request.getParameter("userName");
	String password = request.getParameter("password");
	String signature = request.getParameter("signature");

	if (userName == null)
		userName = "";
	if (password == null)
		password = "";
	%>
	<div class="container-fluid">
		<div class="body">
			<div class="container">

				<h2 style="text-align: center; margin-top: 30px;">Tạo lại key</h2>
				<div class="alert alert-success text-center"
					<c:if test="${ sucess ==null}">style="display: none"</c:if>>${sucess}</div>

				<form action="<%=request.getContextPath()%>/createKey"
					method="post">
					<p style="color: red;">${message.get("keyError")}</p>
					<div class="form-outline">
						<label class="form-label" for="form3Example1cg">Tài
							khoản(*):</label> <input type="text" id="form3Example1cg"
							class="form-control " required name="userName"
							value="<%=userName%>" />
					</div>
					<p style="color: red;">${message.get("userError")}</p>

					<div class="form-outline mt-3">
						<label class="form-label" for="form3Example4cg">Mật
							khẩu(*):</label> <input type="password" id="form3Example4cg"
							class="form-control " name="password" required
							value="<%=password%>" />

					</div>
					<p style="color: red;">${message.get("passwordError")}</p>
					<div>
						<button type="submit" class="btn btn-success mb-4">Xác
							nhận</button>
					</div>
				</form>
			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>