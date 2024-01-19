<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>

<style type="text/css">
.table {
	margin-top: 20px;
}

.mb-10 {
	margin-bottom: 10px;
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
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/themify-icons/themify-icons.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>


</head>
<body>
	<h2 class="text-center">Đơn đã mua</h2>
	<div class="container">
		<div>
			<a href="<%=request.getContextPath()%>/orderUser"
				class="btn btn-info mb-10" align="right"><i
				class="fa fa-angle-left"></i> Quay về</a>
		</div>

		<h5>
			<strong>Mã đơn: #${order.orderId }</strong>
		</h5>
		<h5>
			<strong>Ngày đặt: ${order.date }</strong>
		</h5>
		<h5>
			Trình trạng:
			<c:if test="${order.status == 1}">
				<label class="badge badge-danger">Đang chờ xác nhận</label>
			</c:if>
			<c:if test="${order.status == 2}">
				<label class="badge badge badge-primary">Đang chờ vận chuyển</label>
			</c:if>
			<c:if test="${order.status == 3}">
				<label class="badge badge-info">Đang vận chuyển</label>
			</c:if>
			<c:if test="${order.status == 4}">
				<label class="badge badge-success">Đã giao</label>
			</c:if>
		</h5>

		<table id="cart" class="table table-hover table-condensed">
			<thead>
				<tr>
					<th style="width: 10%">Tên sản phẩm</th>
					<th style="width: 10%">Hình</th>
					<th style="width: 8%">Giá</th>
					<th style="width: 10%">Số lượng</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="orderItem" items="${order.listOrderItem}"
					varStatus="counter">
					<tr>
						<td>
							<div class="row">
								<div class="col-sm-10">
									<h4 class="nomargin">${orderItem.getProduct().getName()}</h4>
								</div>
							</div>
						</td>
						<td><img src="${orderItem.getProduct().getImage()}"
							style="width: 80px; height: 80px;"></td>
						<td>${orderItem.getProduct().formatPrice() }</td>
						<td>${orderItem.getQuanlity() }</td>
						<c:if test="${orderItem.verify() == false}">
							<label class="badge badge-danger">Đã bị chỉnh sửa</label>
							<div>
								<form action="deleteOrder" method="post">
									<input type="hidden" name="orderId" value="${order.orderId }">
									<button class="btn btn-primary" type="submit" name="action" value="delete">
										<i class="fa fa-trash-o">Hủy đơn hàng</i>
									</button>
								</form>
							</div>
						</c:if>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>