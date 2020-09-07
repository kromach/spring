<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> login pro </title>
</head>
<body>
 
	<c:if test="${sessionScope.memId != null}">
		<script>alert('접근할 수 없는 페이지입니다. 메인페이지로 이동합니다.'); location.href='main.git';</script>
	</c:if>
	
	<c:if test="${check != 1}">
		<script>alert('일치하는 정보가 없습니다. 다시 시도해주세요.'); history.go(-1);</script>
	</c:if>
		
	<c:if test="${check == 1}">
		<c:redirect url="main.git" /> <!-- context root는 쓰지 않는다. (/web/) -->
	</c:if>
</body>
</html>