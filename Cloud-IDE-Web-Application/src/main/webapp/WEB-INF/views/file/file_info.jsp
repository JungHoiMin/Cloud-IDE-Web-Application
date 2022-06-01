<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새로 만들기</title>
<link rel="stylesheet" href="../resources/ciwa.css" type="text/css"></link>
</head>
<script type="text/javascript">
	function postForm(form) {
		form.action = 'http://localhost:8080/ciwa/file/execute';
		form.submit();
		return ture;
	}
</script>
<body>
	<div align=center style="padding-top: 30px">
		<form name="fileForm" action="http://localhost:8080/ciwa/file/save"
			method="post">
			<table>
				<tr>
					<th>파일 명</th>
					<td><input type="text" name="title" value="${file.title}"></td>
				</tr>
				<tr>
					<th>언어</th>
					<td>
						<select name="type">
							<option value="python">python</option>
							<option value="java">java</option>
						</select>
					</td>
					<th>사용자 ID</th>
					<td><c:out value="${user.id}"></c:out>
				</tr>
			</table>
			<br> <input type="submit" name="submit" value="저장">

			<c:url value="/file/list" var="url" />
			<input type="button" onclick="location.href='${url}'"
				value="파일 목록 보기"> <br>

			<fieldset>
				<legend>코드</legend>
				<textarea name="body" cols="105" rows="20"
					onkeydown="if(event.keyCode===9){var v=this.value,s=this.selectionStart,e=this.selectionEnd;this.value=v.substring(0, s)+'\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;return false;}"><c:out
						value="${body}" />
				</textarea>
			</fieldset>
		</form>
	</div>
</body>
</html>