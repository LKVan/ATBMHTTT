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
	href="/Project_CK_LTWEB/themify-icons/themify-icons.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>


</head>
<body>
	<h2 class="text-center">Đơn đã mua</h2>
	<div class="container">
		<div>
			<a href="cart.jsp" class="btn btn-info mb-10" align="right"><i
				class="fa fa-angle-left"></i> Giỏ hàng</a>
		</div>

		<table id="cart" class="table table-hover table-condensed">
			<thead>
				<tr>
					<th style="width: 5%">Mã đơn</th>
					<th style="width: 20%">Ngày đặt</th>
					<th style="width: 8%">Chi tiết</th>
					<th style="width: 10%">Tình trạng</th>
				</tr>
			</thead>
			<jsp:useBean id="cart" scope="session" class="model.Cart" />
			<tbody>
				<c:forEach var="order" items="${listOrder}" varStatus="counter">
					<tr>
						<td>
							<div class="row">
								<div class="col-sm-10">
									<h4 class="nomargin">#${order.orderId}</h4>
								</div>
							</div>
						</td>
						<td>${order.date}</td>
						<td><a
							href="<%=request.getContextPath()%>/orderUser?action=detail&orderId=${order.orderId}"
							class="btn btn-primary"> <span>Chi tiết</span><i class="ti-eye" title="Chi tiết"></i></a></td>
						<c:if test="${order.status == 1 }">
							<td><span class="badge badge-danger">Đang chờ xác
									nhận</span></td>
						</c:if>
						<c:if test="${order.status == 2 }">
							<td><span class="badge badge-warning">Đang chờ vận
									chuyển</span></td>
						</c:if>
						<c:if test="${order.status == 3 }">
							<td><span class="badge badge-info">Đang vận chuyển</span></td>
						</c:if>
						<c:if test="${order.status == 4 }">
							<td><span class="badge badge-success">Đã giao</span></td>
						</c:if>

					</tr>

				</c:forEach>
			</tbody>
			<tfoot>
				<td><a href="HomeController" class="btn btn-warning"><i
						class="fa fa-angle-left"></i> Tiếp tục mua hàng</a></td>
				<td colspan="2" class="hidden-xs"></td>

			</tfoot>
		</table>
	</div>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>