<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${file.title}복사</title>
<link rel="stylesheet" href="../resources/ciwa.css" type="text/css"></link>
</head>
<body>
	<header>파일 복사</header>
	<div align=center style="padding-top: 30px">
		<form name=form1 action="http://localhost:8080/ciwa/file/copy"
			method="post">
			<table>
				<tr>
					<th>새로운 파일 명</th>
					<td><input type="text" name="title" autofocus
						placeholder="공백없이 입력하세요"></td>
				</tr>
			</table>
			<dl>
				<dd>
					<input type="submit" name="submit" value="확인">
				</dd>
				<dd>
					<c:url value="/file/list" var="url" />
					<input type="button" onclick="location.href='${url}'" value="취소">
				</dd>
			</dl>
		</form>
	</div>
</body>
</html>