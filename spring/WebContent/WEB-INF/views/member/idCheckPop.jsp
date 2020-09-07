<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> ID중복검사 </title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>
<body>
	<script>
		function returnId(id) {
			//opener.document.getElementById("id").value = id;
			opener.document.inputForm.id.value = "${trialId}";
			window.close();
		}
	</script>	
	<h1 align="center"> id 중복검사 </h1>
	<form action="#" method="get" name="checkForm">
		<table>
			<tr>
				<td>아이디*</td>
				<td><input type="text" name="id" value="${trialId}" />
					<input type="submit" value="검사" />
				</td>
			</tr>
		</table>
	</form>
	<div style="text-align: center;">
	
		<c:if test="${empty trialId}">
			<p>아이디 입력 후 검사 버튼을 클릭해주세요.</p>
		</c:if>
		<c:if test="${!empty trialId && result == 0}">
			<p>사용할 수 있는 아이디 입니다.</p>
			<input type="button" value="id사용" onclick="returnId();"/>
		</c:if>
		<c:if test="${result != 0}">
			<p>이미 존재하는 아이디 입니다. 다른 아이디를 입력 후 검사해보세요.</p>
		</c:if>
	</div>
</body>	
</html>