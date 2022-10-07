<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
<h2>메인 페이지</h2>

회원 인증된 사용자에게 제공하는 페이지<br>

<form action="logout.do">
<table>
	<tr>
		<!-- 로그인 유저 이름/아이디 정보 표시 -->
		<%-- <td>안녕하세요. ${name}(${id})님</td> --%>
		<td>안녕하세요. ${loginUser.name}(${loginUser.userid})님</td>
	</tr>
	<tr>
		<td>
			<!-- 로그아웃, 회원정보변경 수행 -->
			<input type="submit" value="로그아웃">
			<input type="button" value="회원정보변경" onclick="location.href='updateMember.do?userId=${loginUser.userid}'">
		</td>
	</tr>
</table>
</form>
<h2><a href='productList.do'>상품페이지</a></h2>

</body>
</html>