<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
</head>
<link rel="stylesheet" type="text/css" href="css/product.css">
<script type="text/javascript" src="script/product.js"></script>
<body>
<div id="wrap" align="center">
	<h2>상품등록</h2>
	<form action="writeProduct.do" method="post"
		enctype="multipart/form-data" name="frm">
		<table border="1">
<!-- 			
			<tr>
				<th>상품코드</th>
				<td><input type="text" name="code" size="20"></td>
			</tr>
 -->
			<tr>
				<th>상 품 명</th>
				<td><input type="text" name="name" size="80"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price" size="10"> 원</td>
			</tr>
			<tr>
				<th>사진</th>
				<td><input type="file" name="pictureurl"></td>
			</tr>
			<tr>
				<th>설명</th>
				<td><textarea style="resize:none" cols="80" rows="5" name="description"></textarea></td>
			</tr>
			<tr>
				<th>등록일자</th>
				<td><input type="text" name=reg_date placeholder="예)0000-00-00"></td>
			</tr>
		</table>
		
		<input type="submit" value="등록" onclick="return checkProduct()"> <input type="reset"
			value="취소"> <input type="button" value="목록"
			onclick="location.href='productList.do'">
	</form>
	</div>
</body>
</html>