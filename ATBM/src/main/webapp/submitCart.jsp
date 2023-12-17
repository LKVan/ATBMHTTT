<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Xác nhận đặt hàng</title>

<style type="text/css">
.btn-in_decrement {
	background-color: #ccc;
}

.quanlity {
	max-width: 70px !important;
	width: 50px !important;
	background-color: #fff !important;
}

.quanlity:hover {
	cusor: none;
}

#cart {
	margin-top: 20px;
}

.table {
	
}

;
tbody {
	
}

;
tr {
	
}

;
td, .table {
	
}

;
tfoot {
	
}

;
tr {
	
}

;
td {
	vertical-align: middle;
}

@media screen and (max-width: 600px) {
	table#cart tbody td .form-control {
		width: 20%;
		display: inline !important;
	}
	.actions .btn {
		width: 36%;
		margin: 1.5em 0;
	}
	.actions .btn-info {
		float: left;
	}
	.actions .btn-danger {
		float: right;
	}
	table#cart thead {
		display: none;
	}
	table#cart tbody td {
		display: block;
		padding: .6rem;
		min-width: 320px;
	}
	table#cart tbody tr td:first-child {
		background: #333;
		color: #fff;
	}
	table#cart tbody td:before {
		content: attr(data-th);
		font-weight: bold;
		display: inline-block;
		width: 8rem;
	}
	table#cart tfoot td {
		display: block;
	}
	table#cart tfoot td .btn {
		display: block;
	}
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link
	href="<%=request.getContextPath()%>/themify-icons/themify-icons.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</head>
<body>
	<h2 class="text-center">Xác nhận đặt hàng</h2>
	<div class="container">
		<div>
			<strong>Địa chỉ:</strong> ${user.address }
		</div>
		<table id="cart" class="table table-hover table-condensed">
			<thead>
				<tr>
					<th style="width: 50%">Tên sản phẩm</th>
					<th style="width: 10%">Giá</th>
					<th style="width: 8%; text-align: center;">Số lượng</th>
					<th style="width: 22%" class="text-center">Thành tiền</th>
					<th style="width: 10%"></th>
				</tr>
			</thead>
			<jsp:useBean id="cart" scope="session" class="model.Cart" />
			<c:forEach var="cartItem" items="${cart.list}" varStatus="counter">
				<form method="POST" action="CartController">
					<tbody>
						<tr>
							<td data-th="Product">
								<div class="row">
									<input type='hidden' name='stt'
										value='<c:out value="${counter.count}"/>'>
									<div class="col-sm-2 hidden-xs">
										<img src="${cartItem.getProduct().image}"
											class="img-responsive" width="100" height="100">
									</div>
									<input type="hidden" name="cart_pro_id"
										value="${cartItem.getProduct().id}" />
									<div class="col-sm-10">
										<h4 class="nomargin">${cartItem.getProduct().name}</h4>
										<p>${cartItem.getProduct().descreption}</p>
									</div>
								</div>
							</td>
							<td data-th="Price">${cartItem.formatPrice()}VNĐ.</td>
							<input type="hidden" name="price"
								value="${cartItem.getProduct().price}" />
							<td data-th="Quantity" style="display: inline-flex"><input
								class="form-control text-center quanlity" name="quan"
								value="${cartItem.quantity}" type="text" readonly> <!-- <button class="btn btn-success btn-block" name="action"
									value="update">
									<i class="fa fa-trash-o">Cập nhật</i>
								</button> --></td>
							<td data-th="Subtotal" class="text-center">${cartItem.formatTotal()}
								VNĐ</td>
							<td class="actions" data-th=""></td>
						</tr>
					</tbody>
				</form>
			</c:forEach>
			<tfoot>
				<form method="POST" action="CartController">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td class="hidden-xs"><strong>Phí vận chuyển:</strong>
							${priceTransport} VND</td>
						<td><input type="hidden" value=0 name="priceTransport"></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td class="hidden-xs"><strong>Dự kiến giao:</strong>${dateSend}</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td class="hidden-xs text-center"><strong>TỔNG:
								${cart.total + priceTransport} VNĐ.</strong></td>
						<td></td>
					</tr>
					<!-- <td><a href="HomeController" class="btn btn-warning">
				<i class="fa fa-angle-left"></i> Tiếp tục mua hàng</a></td> -->
					<tr>
						<td></td>
						<td><label class="form-label" for="form3Example4cg">Chữ
								kí(*):</label></td>
						<td><input type="text" id="form3Example4cg"
							class="form-control " name="signature" required value="" />
							<p style="color: red;">${message.get("signatureError")}</p></td>
						<td class="actions" data-th="">
							<button class="btn btn-danger" style="width: 100%;" name="action"
								value="oder" onclick="oder()">
								<i class="fa fa-edit">Đặt hàng</i>
							</button>
						</td>
						<td><a href="cart.jsp" class="btn btn-primary">Trở về</a></td>
					</tr>
				</form>

			</tfoot>
		</table>
	</div>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>