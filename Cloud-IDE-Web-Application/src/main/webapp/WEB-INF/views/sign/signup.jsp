<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IDE Service 회원가입</title>
<link rel="stylesheet" href="../resources/ciwa.css" type="text/css"></link>
</head>
<body>
	<div align=center>
		<header>회원가입</header>
		<form name=form1 action="http://localhost:8080/ciwa/user/signup"
			method="post">
			<table>
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" autofocus
						placeholder="공백없이 입력하세요"></td>
				</tr>
				<tr>
					<th>PASSWORD</th>
					<td><input type="text" name="passwd" placeholder="공백없이 입력하세요"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="username"
						placeholder="공백없이 입력하세요"></td>
				</tr>
			</table>
			<dl>
				<dd>
					<input type="submit" name="submit" value="회원가입">
				</dd>
				<dd>
					<c:url value="/user/signin" var="url" />
					<input type="button" onclick="location.href='${url}'" value="취소">
				</dd>
			</dl>
		</form>
	</div>
</body>
</html>