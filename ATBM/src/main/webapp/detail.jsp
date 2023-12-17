
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Details Page</title>
<link rel="stylesheet" href="user/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section class="">
		<form method="POST" action="CartController">
			<div style="padding-top: 20px" class="flex-box">
				<div class="left">
					<input type="hidden" name="proId" value="${product.id}">
					<div class="small mb-1">SKU: ${product.id}</div>
					<div class="big-img">
						<img src="${product.image}">
					</div>
					<div class="images">
						<div class="small-img">
							<img src="${product.image}" onclick="showImg(this.src)">
						</div>
						<div class="small-img">
							<img src="${product.image}" onclick="showImg(this.src)">
						</div>
						<div class="small-img">
							<img src="${product.image}" onclick="showImg(this.src)">
						</div>
						<div class="small-img">
							<img src="${product.image}" onclick="showImg(this.src)">
						</div>
					</div>
				</div>

				<div class="right">
					<div class="pname">${product.name}</div>
					<div class="ratings">
						<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
							class="fas fa-star"></i> <i class="fas fa-star"></i> <i
							class="fas fa-star-half-alt"></i>
					</div>
					<p class="about">${product.getDescreption()}</p>
					<div class="price">Thông số</div>
					<p class="about">Chiều cao: ${product.height} centimetres
						&emsp; Cân nặng: ${product.length} gram &emsp;Độ dài:
						${product.width} centimetres</p>

					<div class="price">Giá : ${product.formatPrice()} vnđ</div>
					<div class="quantity">
						<p>Quantity :</p>
						<input name="inputQuantity" type="number" value="1">
					</div>
					<div class="btn-box">
						<button type="submit" class="cart-btn">Add to Cart</button>
					</div>
				</div>
			</div>

		</form>
		<div style="padding-top: 80px" class="container mt-3">
			<h3>Đánh giá sản phẩm</h3>

			<c:forEach var="comment" items="${listComment}">
				<div class="media border p-3">
					<img src="${comment.image}" class="align-self-start mr-3"
						style="width: 60px">
					<div class="media-body">
						<h5>${comment.name}</h5>
						<p>${comment.comment}</p>
						<p>${comment.time}</p>
					</div>
				</div>
			</c:forEach>



			<form method="POST" action="CommentController">
				<div class="mb-3">
					<input type="hidden" name="proId" value="${product.id}"> <label
						for="" class="form-label">Comment</label> <input type="text"
						class="form-control" name="comment" aria-describedby="">
					<div id="" class="form-text"></div>
				</div>
				<button type="submit" class="btn btn-primary">Comment</button>
			</form>


		</div>

	</section>


	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		let bigImg = document.querySelector('.big-img img');
		function showImg(pic) {
			bigImg.src = pic;
		}
	</script>
</body>
</html>


