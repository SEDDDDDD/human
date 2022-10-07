<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 삭제 페이지</title>
</head>

<link rel="stylesheet" type="text/css" href="css/product.css">
<body>
<div id="wrap" align="center">
<form action="deleteProduct.do" method="post">
<h2>상품삭제</h2>
<table>
		<tr>
			<td rowspan="5" width="200px">
				<c:choose>
					<%-- 이미지가 서버에 존재하지 않는 경우 --%>
					<c:when test="${empty product.pictureurl}">
						<img src="image/no.png" width="150px">
					</c:when>
					<%-- 이미지가 서버에 존재하는 경우 --%>
					<c:otherwise>
						<img src="upload/${product.pictureurl}" width="200px">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<th width="80px">상품명</th>
			<td>${product.name}</td>
		</tr>
		<tr>
			<th>가격</th>
			<td>${product.price}</td>
		</tr>
		<tr>
			<th>설명</th>
			<td>${product.description}</td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td>${product.reg_date}</td>
		</tr>

	</table>
	<input type="hidden" name="code" value="${product.code}">
	<input type="submit" value="삭제">	
	<input type="button" value="목록 이동" onclick="location.href='productList.do'">
</form>
</div>
</body>
</html>