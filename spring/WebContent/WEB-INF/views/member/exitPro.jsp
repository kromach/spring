<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 탈퇴 처리 </title>
	<link rel="stylesheet" href="../resources/style.css" />
</head>
<body>
	<c:if test="${check != 1}">
		<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>
	</c:if>	
	
	<c:if test="${check == 1}">
		<div id="contents">
			<h1>탈퇴되었습니다. 감사합니다.</h1>
			<p style="text-align: center;"><a href="main.git">메인으로 가기</a></p>
		</div>
	</c:if>
</body>
</html>