<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정 페이지</title>
</head>
<script type="text/javascript" src="script/product.js"></script>
<link rel="stylesheet" type="text/css" href="css/product.css">
<body>
<div id="wrap" align="center">
	<h2>상품 수정 페이지</h2>
	<form action="updateProduct.do" method="post" enctype="multipart/form-data" name="frm">
	<table>
		<tr>
			<td rowspan="6">
				<c:choose>
					<%-- 이미지가 서버에 존재하지 않는 경우 --%>
					<c:when test="${empty product.pictureurl}">
						<img src="image/no.png" width="200px">
					</c:when>
					<%-- 이미지가 서버에 존재하는 경우 --%>
					<c:otherwise>
						<img src="upload/${product.pictureurl}" width="200px">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<th>상품명</th>
			<td>
			<input type="text" name="name" value="${product.name}">
			</td>
		</tr>
		<tr>
			<th>가격</th>
			<td>
			<input type="text" name="price" value="${product.price}"></td>
		</tr>
		<tr>
			<th>사진</th>
			<td>
			<input type="file" name="pictureurl"><br></td>
		</tr>
		<tr>
			<th>설명</th>
			<td><textarea style="resize:none" cols="80" rows="5" value="${product.description}"></textarea></td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td><input type="text" name=reg_date value="${product.reg_date}"></td>
		</tr>

	</table>
<input type="hidden" name="code" value="${product.code}">
<input type="submit" value="수정">
<input type="reset" value="취소">
<input type="button" value="목록 이동" onclick="location.href='productList.do'">
</form>
</div>
</body>
</html>