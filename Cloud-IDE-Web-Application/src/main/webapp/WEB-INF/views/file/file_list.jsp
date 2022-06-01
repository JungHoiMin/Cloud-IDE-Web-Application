<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${user.username}님의파일목록</title>
<link rel="stylesheet" href="../resources/ciwa.css" type="text/css"></link>
</head>
<body>
	<header>파일 목록</header>
	<div style="padding: 40px 0px 10px 40px; float: left">
		<c:url value="/file/add" var="url" />
		<input type="button" onclick="location.href='${url}'" value="새로 만들기">
	</div>

	<div style="padding: 40px 40px 10px 0px; float: right">
		<c:url value="/user/userDelete" var="url" />
		<input type="button" onclick="location.href='${url}'" value="사용자 탈퇴">
	</div>

	<div style="padding: 40px 40px 10px 0px; float: right">
		<c:url value="/user/signin" var="url" />
		<input type="button" onclick="location.href='${url}'" value="로그아웃">
	</div>


	<div style="padding-left: 30px; clear: both;">
		<table>
			<tr>
				<th>파일명</th>
				<th>파일타입</th>
				<th>마지막 수정일</th>
			</tr>
			<c:forEach var="file" items="${fileList}">
				<tr>
					<td><c:out value="${file.title}" /></td>
					<td><c:out value="${file.type}" /></td>
					<td><c:out value="${file.modifyDate}" /></td>
					<td><c:url value="/file/read?title=${file.title}" var="url" /><a
						href="${url}">열기</a>
					<td><c:url value="/file/copy?title=${file.title}" var="url" /><a
						href="${url}">복사</a>
					<td><c:url value="/file/delete?title=${file.title}" var="url" /><a
						href="${url}">삭제</a>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>