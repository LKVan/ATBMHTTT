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


	<div class="container-fluid">
		<div class="body">
			<div class="container">

				<h2 style="text-align: center; margin-top: 30px;">CHỮ KÍ ĐIỆN
					TỬ</h2>
				<div class="alert alert-success text-center"
					<c:if test="${ sucess ==null}">style="display: none"</c:if>>${sucess}</div>

				<form action="<%=request.getContextPath()%>/HomeController"
					class="was-validated" method="post">
					<div class="form-group">
						<label for="uname">Chữ kí điện tử :</label> <input type="text"
							class="form-control" id="ckdt" value=${ckdt[0] } name="signature">
						<div class="valid-feedback">Vui lòng ghi nhớ.</div>
					</div>
					<div class="form-group">
						<label for="pwd">Public Key :</label> <input type="text"
							class="form-control" id="publicKey" value=${ckdt[1] }
							name="publicKey">
					</div>
					<div class="form-group">
						<label for="pwd">Private Key :</label> <input type="text"
							class="form-control" id="privateKey" value=${ckdt[2] }
							name="privateKey">
						<div class="valid-feedback">Vui lòng ghi nhớ.</div>
					</div>


					<button type="submit" class="btn btn-success mb-4 ">Đóng</button>
				</form>
			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>